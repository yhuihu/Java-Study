package main

import (
	"example.com/golang/http/context"
	"example.com/golang/http/filter"
	"example.com/golang/http/server"
	"fmt"
	"io/ioutil"
	"net/http"
)

func main() {

	filters := make([]filter.Builder, 0)
	filters = append(filters, func(next filter.Filter) filter.Filter {
		return func(c *context.Context) {
			fmt.Println("\n开始执行了")
			fmt.Println("第一层拦截器")
			fmt.Println("哈哈哈")
			next(c)
		}
	})
	httpServer := server.NewHttpServer("myServer", filters...)

	httpServer.Route(http.MethodGet, "/hello", func(ctx *context.Context) {
		body := ctx.R.Body
		// 这里假定所有的请求报文都是json格式
		_, err := ioutil.ReadAll(body)
		if err != nil {
			ctx.FailResponse("服务端解析异常", nil)
		} else {
			ctx.SuccessResponse("这是正常返回了", nil)
		}
	})

	err := httpServer.Start(":8080")

	if err != nil {
		panic(err)
	}
}
