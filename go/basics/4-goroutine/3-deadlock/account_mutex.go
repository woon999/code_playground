package main

import (
	"fmt"
	"sync"
)

type Account struct {
	balance int
	mutex   sync.Mutex
}

func (a *Account) Deposit(amount int) {
	a.mutex.Lock()         // Acquire the lock
	defer a.mutex.Unlock() // Ensure the lock is released when Deposit returns

	a.balance += amount
	fmt.Println("Deposit ", a.balance)
}

func (a *Account) Withdraw(amount int) {
	a.mutex.Lock()         // Acquire the lock
	defer a.mutex.Unlock() // Ensure the lock is released when Withdraw returns

	a.balance -= amount
	fmt.Println("Withdraw", a.balance)
}

func (a *Account) Balance() int {
	a.mutex.Lock()         // Acquire the lock
	defer a.mutex.Unlock() // Ensure the lock is released when Balance returns

	return a.balance
}

func main() {
	var wg sync.WaitGroup
	account := &Account{balance: 1000}

	// Start 100 goroutines to deposit and withdraw money concurrently.
	for i := 0; i < 100; i++ {
		wg.Add(1)
		go func() {
			defer wg.Done()
			account.Deposit(50)  // 50 * 100 = 5000
			account.Withdraw(30) // 30 * 100 = 3000
		}()
	}

	wg.Wait() // Wait for all goroutines to finish

	fmt.Printf("Final balance: $%d\n", account.Balance()) // 3000
}
