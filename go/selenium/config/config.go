package config

import (
	"fmt"
	"github.com/spf13/viper"
	"os"
)

const (
	DRIVER_PATH = "go.selenium.driverPath"
	SAVE_PATH   = "go.selenium.savePath"
	URL         = "go.selenium.url"
)

var VIPER *viper.Viper

func init() {
	viper.SetConfigFile("./config/application.yml")
	err := viper.ReadInConfig()
	if err != nil {
		fmt.Println("读取配置文件错误！" + err.Error())
		os.Exit(0)
	}
	VIPER = viper.GetViper()
}
