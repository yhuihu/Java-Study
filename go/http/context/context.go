package context

import (
	"encoding/json"
	"example.com/golang/http/model"
	"fmt"
	"net/http"
)

type Context struct {
	// 接口不需要指针
	W http.ResponseWriter
	// 结构体用指针
	R *http.Request
}

func NewContext(writer http.ResponseWriter, request *http.Request) *Context {
	return &Context{
		R: request,
		W: writer,
	}
}

func (ctx *Context) SuccessResponse(message string, data interface{}) {
	response := model.SuccessResponse(message, data)
	ctx.writeResponse(response)

}

func (ctx *Context) FailResponse(message string, data interface{}) {
	response := model.FailResponse(message, data)
	ctx.writeResponse(response)
}

func (ctx *Context) ManualResponse(code int, message string, data interface{}) {
	response := model.NewResponse(code, message, data)
	ctx.writeResponse(response)
}

func (ctx *Context) writeResponse(response *model.ResponseEntity) {
	ctx.W.WriteHeader(response.Code)
	marshal, err := json.Marshal(response)
	if err != nil {
		fmt.Println("序列化返回体出现异常:", err)
		return
	}
	_, err = ctx.W.Write(marshal)
	if err != nil {
		fmt.Println("返回消息给前端出现异常：:", err)
	}
}
