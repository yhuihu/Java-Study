package pingHandle

import (
	"github.com/gin-gonic/gin"
	"log"
	"net/http"
)

func PingFunc(context *gin.Context) error {
	context.JSONP(http.StatusOK, gin.H{
		"message": "ok",
	})
	log.Println("服务端进程心跳检测")
	return nil
}
