package main

import (
	"context"
	"encoding/hex"
	"fmt"
	"math/big"
	"strings"

	"log"
	"os"

	"github.com/ethereum/go-ethereum/accounts/abi/bind"
	"github.com/ethereum/go-ethereum/common"
	"github.com/ethereum/go-ethereum/core/types"
	"github.com/ethereum/go-ethereum/ethclient"
	"github.com/joho/godotenv"
	"github.com/liyue201/erc20-go/erc20"
)

func init() {
	err := godotenv.Load(".env")
	if err != nil {
		log.Fatalf("Error loading .env file: %v", err)
	}
}

// This is the main function of the program
func BlockListener() error {
	mainnetURL := os.Getenv("MAINNET_ETH")

	client, err := ethclient.Dial(mainnetURL)
	if err != nil {
		log.Fatalf("Unable to connect to network:%v\n", err)
	}

	headers := make(chan *types.Header)
	fmt.Println("headers:", headers)
	sub, err := client.SubscribeNewHead(context.Background(), headers)
	if err != nil {
		log.Fatalf("Unable to subscribe to network:%v\n", err)
	}

	for {
		select {
		case err := <-sub.Err():
			log.Fatalf("Error:%v\n", err)
		case header := <-headers:
			fmt.Println("----------------------------------")

			log.Printf("New block:%v\n", header.Number.String())

			block, err := client.BlockByHash(context.Background(), header.Hash())
			if err != nil {
				log.Fatalf("Unable to get block:%v\n", err)
			}

			fmt.Println("block hash:", block.Hash().Hex())
			fmt.Println("-----block parent hash:", block.ParentHash().Hex())
			fmt.Println("-----state root:", block.Root().Hex())
			fmt.Println("block number:", block.Number().Uint64())
			fmt.Println("block time:", block.Time())
			fmt.Println("block nonce:", block.Nonce())
			fmt.Println("block difficulty:", block.Difficulty().Uint64())
			fmt.Println("Gas Used:", block.GasUsed())
			fmt.Println("Gas Limit:", block.GasLimit())

			// Get the base fee per gas if it's an EIP-1559 block
			baseFee := block.BaseFee()
			if baseFee != nil {
				fmt.Println("Base Fee Per Gas:", baseFee.String())
			}

			uncleLen := len(block.Uncles())
			fmt.Println("Number of Uncles:", uncleLen)
			if uncleLen > 0 {
				for _, uncle := range block.Uncles() {
					uncleFee := float64(uncle.Number.Uint64()+8-block.Number().Uint64()*2) / 8.0
					fmt.Println("Uncle Block Number:", uncle.Number.String())
					fmt.Println("Uncle Miner Address:", uncle.Coinbase.Hex())
					fmt.Print("Unlce Block Reward:", uncleFee, " ETH\n")
				}

			}

			fmt.Println("Number of Transactions:", len(block.Transactions()))
			for _, tx := range block.Transactions() {
				fmt.Println("$$$$$$$$$$$$")

				if tx.To() != nil {
					fmt.Println("Transaction To:", tx.To().Hex())
				} else {
					fmt.Println("Contract Creation Transaction")
					contractAddress := getContractAddress(client, tx)
					fmt.Println("Contract Address:", contractAddress)
				}

				fmt.Println("Transaction Hash:", tx.Hash().Hex())
				// fmt.Println("Transaction Value:", tx.Value().String())
				// fmt.Println("Transaction Gas Limit:", tx.Gas())
				// fmt.Println("Transaction Gas Price:", tx.GasPrice().Uint64())
				// fmt.Println("Transaction Gas Fee:", tx.GasFeeCap().Uint64())
				// fmt.Println("Transaction Gas Tip:", tx.GasTipCap().Uint64())
				// fmt.Println("Transaction Nonce:", tx.Nonce())

				realUsedGas := realGasUsed(client, tx)
				fmt.Println("Transaction Real Gas Used:", realUsedGas)
				realUsedGasPrice := realGasPrice(block.BaseFee().Uint64(), tx.GasTipCap().Uint64(), tx.GasFeeCap().Uint64())
				fmt.Println("Transaction Real Gas Price:", realUsedGasPrice)

				// function call tx
				if len(tx.Data()) > 0 {
					to, value := erc20Transfer(hex.EncodeToString(tx.Data()))

					if to != "" {
						symbol, name, decimal := getContractInfo(client, tx.To())
						fmt.Println("ERC20 Contract Symbol:", symbol)
						fmt.Println("ERC20 Contract Name:", name)
						fmt.Println("ERC20 Contract Decimal:", decimal)
						fmt.Println("ERC20 Transfer To:", to)
						fmt.Println("ERC20 Transfer Value:", value) // (value / 10^decimal)
					}
				}
			}

		}
	}
}

func getContractInfo(client *ethclient.Client, to *common.Address) (string, string, uint8) {
	instance, err := erc20.NewGGToken(*to, client)

	if err != nil {
		log.Fatalf("Unable to bind to deployed instance of contract:%v\n", err)
	}

	symbol, err := instance.Symbol(&bind.CallOpts{})
	if err != nil {
		log.Fatalf("Unable to get symbol:%v\n", err)
	}

	name, err := instance.Name(&bind.CallOpts{})
	if err != nil {
		log.Fatalf("Unable to get name:%v\n", err)
	}

	decimal, err := instance.Decimals(&bind.CallOpts{})
	if err != nil {
		log.Fatalf("Unable to get decimal:%v\n", err)
	}

	return symbol, name, decimal
}

func erc20Transfer(data string) (string, string) {
	if len(data) != 136 {
		return "", "0"
	}

	methodId := data[:8]
	to := data[32:72]
	value := data[72:136]

	if methodId != "a9059cbb" { // not erc20
		return "", "0"
	}

	i := new(big.Int)
	valueStr := strings.TrimLeft(value, "0")
	i.SetString(valueStr, 16)

	return to, i.String()
}

func getContractAddress(client *ethclient.Client, tx *types.Transaction) string {
	receipt, err := client.TransactionReceipt(context.Background(), tx.Hash())
	if err != nil {
		log.Fatalf("Unable to get receipt:%v\n", err)
	}

	return receipt.ContractAddress.Hex()
}

func realGasUsed(client *ethclient.Client, tx *types.Transaction) uint64 {
	receipt, err := client.TransactionReceipt(context.Background(), tx.Hash())
	if err != nil {
		log.Fatalf("Unable to get receipt:%v\n", err)
	}

	return receipt.GasUsed
}

func realGasPrice(baseGasFee uint64, gasTip uint64, gasFeeCap uint64) uint64 {
	if baseGasFee+gasTip > gasFeeCap {
		return gasFeeCap
	} else {
		return baseGasFee + gasTip
	}
}
