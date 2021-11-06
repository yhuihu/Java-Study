package main

import (
	"fmt"
	"time"
)

func worker(id int, c chan int) {
	for n := range c {
		fmt.Printf("worker %d receive message %d\n", id, n)
	}
}

func createWorker(id int) chan int {
	c := make(chan int)
	// 通道如果要使用，必须要有一个接收者或者使用BufferChannel
	go worker(id, c)
	return c
}

func channelDemo() {
	var channels [10]chan int
	for i := 0; i < 10; i++ {
		channels[i] = createWorker(i)
	}

	for i := 0; i < 10; i++ {
		channels[i] <- 'a' + i
	}

	for i := 0; i < 10; i++ {
		channels[i] <- 'A' + i
	}
}

func main() {
	// 最简易的channel
	channelDemo()
	// 带缓冲区的channel
	bufferChannelDemo()
	// 关闭通道
	closeChannel()
}

func closeChannel() {
	c := make(chan int)
	go worker(99, c)
	c <- 'a'
	c <- 'b'
	c <- 'c'
	c <- 'd'
	close(c)
	time.Sleep(time.Millisecond)
}

// 带有缓冲区的channel不需要有接收者就可以往channel里添加数据
func bufferChannelDemo() {
	c := make(chan int, 3)
	c <- 1
	c <- 2
	c <- 3
	// 这里会报错，因为缓冲区的大小只有3
	//c<-4
	go worker(0, c)
	time.Sleep(time.Millisecond)
}
