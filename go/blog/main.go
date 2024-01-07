package main

import (
	handle "blog/common"
	"blog/handle"
	"github.com/gin-gonic/gin"
	"log"
)

func init() {
	log.SetPrefix("TRACE: ")
	log.SetFlags(log.Ldate | log.Ltime | log.Llongfile)
}

func main() {
	ginServer := gin.Default()
	// 心跳
	ginServer.GET("ping", handle.Wrapper(pingHandle.PingFunc))
	// 图标
	ginServer.GET("favicon.ico", func(context *gin.Context) {})

	ginServer.POST("doPost", handle.Wrapper(func(c *gin.Context) error {
		return nil
	}))
	// 绑定端口
	err := ginServer.Run(":9999")
	// 启动异常处理
	if err != nil {
		log.Panicln("启动服务端进程出现错误：", err)
		return
	}
}
