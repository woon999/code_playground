package main

import (
	"fmt"
	"sync"
	"time"
)

const numPhilosophers = 5

type Fork struct{ sync.Mutex }

type Philosopher struct {
	id        int
	leftFork  *Fork
	rightFork *Fork
}

func (p *Philosopher) eat() {
	for {
		p.leftFork.Lock()
		//time.Sleep(10 * time.Millisecond) // This sleep simulates some delay and makes deadlock more obvious
		p.rightFork.Lock()

		fmt.Printf("Philosopher %d is eating.\n", p.id)
		time.Sleep(time.Second)

		p.rightFork.Unlock()
		p.leftFork.Unlock()

		fmt.Printf("Philosopher %d is thinking.\n", p.id)
		time.Sleep(time.Second)
	}
}

func main() {
	forks := make([]*Fork, numPhilosophers)
	for i := 0; i < numPhilosophers; i++ {
		forks[i] = &Fork{}
	}

	philosophers := make([]*Philosopher, numPhilosophers)
	for i := 0; i < numPhilosophers; i++ {
		philosophers[i] = &Philosopher{
			id:        i,
			leftFork:  forks[i],
			rightFork: forks[(i+1)%numPhilosophers],
		}
		go philosophers[i].eat()
	}

	select {}
}
