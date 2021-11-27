package main

import (
	"./context"
	"./server"
	"io/ioutil"
	"net/http"
)

func main() {
	httpServer := server.NewHttpServer("myServer")

	httpServer.Route(http.MethodGet, "/hello", func(ctx *context.Context) {
		body := ctx.R.Body
		// 这里假定所有的请求报文都是json格式
		_, err := ioutil.ReadAll(body)
		//var requestMap = make(map[string]interface{})
		if err != nil {
			ctx.W.WriteHeader(http.StatusInternalServerError)
			ctx.W.Write([]byte("这是正常返回了"))
		} else {
			ctx.W.WriteHeader(http.StatusOK)
			ctx.W.Write([]byte("服务端解析异常"))
		}
	})

	err := httpServer.Start(":8080")

	if err != nil {
		panic(err)
	}
	//http.HandleFunc("/hello", func(writer http.ResponseWriter, request *http.Request) {
	//	data := Data{Name: "yhhu", Age: 18}
	//	//response := new(Response)
	//	// 这里模拟的是get请求，localhost:8080/hello?id=xxx
	//	//id := request.FormValue("id")
	//	successResponse := model.SuccessResponse("请求成功", data)
	//	// 转为json
	//	json, _ := json2.Marshal(successResponse)
	//	io.WriteString(writer, string(json))
	//})
}
