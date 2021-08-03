package main

import (
	"fmt"
	"github.com/spf13/viper"
)

func main() {
	viper.SetDefault("hello", "hello")
	viper.SetDefault("world", "world")
	viper.SetDefault("mapTest", map[string]string{"tag": "tags", "category": "categories"})
	fmt.Println(viper.GetString("hello") + " " + viper.GetString("world"))
	fmt.Println(viper.GetStringMapString("mapTest")["tag"])
	fmt.Println("--------------------------------------------------------------------------")
	viper.SetConfigFile("./config/application.yml")
	// 读取配置文件
	err := viper.ReadInConfig()
	// 处理读取配置文件的错误
	if err != nil {
		panic(fmt.Errorf("打开配置文件失败: %s \n", err))
	}
	fmt.Println(viper.Get("settings.application.mode"))
}
