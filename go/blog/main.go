package main

import (
	handle "blog/common"
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
)

func init() {
	log.SetPrefix("TRACE: ")
	log.SetFlags(log.Ldate | log.Ltime | log.Llongfile)
}

func pingFunc(context *gin.Context) error {
	context.JSONP(http.StatusOK, gin.H{
		"message": "ok",
	})
	log.Println("服务端进程心跳检测")
	return nil
}

func main() {
	ginServer := gin.Default()
	// 心跳
	ginServer.GET("ping", handle.Wrapper(pingFunc))
	// 图标
	ginServer.GET("favicon.ico", func(context *gin.Context) {})
	// 绑定端口
	err := ginServer.Run(":9999")
	// 启动异常处理
	if err != nil {
		log.Panicln("启动服务端进程出现错误：", err)
		return
	}
}
