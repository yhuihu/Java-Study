package context

import "net/http"

func NewContext(writer http.ResponseWriter, request *http.Request) *Context {
	return &Context{
		R: request,
		W: writer,
	}
}

type Context struct {
	// 接口不需要指针
	W http.ResponseWriter
	// 结构体用指针
	R *http.Request
}
