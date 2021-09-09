package main

import (
	seleniumUtil "example.com/selenium/util"
	"fmt"
	"github.com/tebeka/selenium"
	"os"
	"sync"
)

func main() {
	group := sync.WaitGroup{}
	crawler, err2 := seleniumUtil.NewCrawler()
	defer crawler.Shutdown()
	handleError(err2)
	webDriver, err2 := crawler.NewRemote()
	handleError(err2)
	err := webDriver.Get("https://www.hermes.cn/")
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(0)
	}
	element, _ := webDriver.FindElement(selenium.ByID, "id")
	element.Click()
	group.Wait()
	select {}
}

func handleError(err error) {
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(0)
	}
}
