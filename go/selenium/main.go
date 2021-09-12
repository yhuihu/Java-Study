package main

import (
	"example.com/selenium/config"
	seleniumUtil "example.com/selenium/util"
	"fmt"
	"github.com/tebeka/selenium"
	"os"
)

func main() {
	crawler, err2 := seleniumUtil.NewCrawler()
	defer crawler.Shutdown()
	handleError(err2)
	webDriver, err2 := crawler.NewRemote()
	handleError(err2)
	err := webDriver.Get(config.VIPER.GetString(config.URL))
	handleError(err)
	element, _ := webDriver.FindElement(selenium.ByID, "id")
	element.Click()
	select {}
}

func handleError(err error) {
	if err != nil {
		fmt.Println(err.Error())
		os.Exit(0)
	}
}
