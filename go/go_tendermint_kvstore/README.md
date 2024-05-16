# Tendermint Go 로컬에서 실행해보기 
## 1. Tendermint 설치 
https://docs.tendermint.com/v0.34/introduction/install.html

## 2. Go built-in 코드 작성 
1. 현재 디렉토리 코드 참고 (https://docs.tendermint.com/v0.34/tutorials/go-built-in.html)
2. `./tendermint.sh` 스크립트 실행



### rpc response example 
```
$ curl -s 'localhost:26657/broadcast_tx_commit?tx="tendermint=rocks"'
{
  "jsonrpc": "2.0",
  "id": -1,
  "result": {
    "check_tx": {
      "code": 0,
      "data": null,
      "log": "",
      "info": "",
      "gas_wanted": "1",
      "gas_used": "0",
      "events": [],
      "codespace": ""
    },
    "deliver_tx": {
      "code": 0,
      "data": null,
      "log": "",
      "info": "",
      "gas_wanted": "0",
      "gas_used": "0",
      "events": [],
      "codespace": ""
    },
    "hash": "1B3C5A1093DB952C331B1749A21DCCBB0F6C7F4E0055CD04D16346472FC60EC6",
    "height": "114"
  }
}
```

```
$ curl -s 'localhost:26657/abci_query?data="tendermint"'
{
  "jsonrpc": "2.0",
  "id": -1,
  "result": {
    "response": {
      "code": 0,
      "log": "exists",
      "info": "",
      "index": "0",
      "key": "dGVuZGVybWludA==",
      "value": "cm9ja3M=",
      "proofOps": null,
      "height": "0",
      "codespace": ""
    }
  }
}
```