package main

import (
	json2 "encoding/json"
	"fmt"
	"io"
	"log"
	"net/http"
)

type Data struct {
	Name string
	Age  int
}

type Response struct {
	Code  int
	Param string
	Msg   string
	Data  []Data
}

func main() {
	defer func() {
		if err := recover(); err != nil {
			fmt.Println("出现异常情况！")
			log.Fatal(err)
		}
	}()
	http.HandleFunc("/hello", func(writer http.ResponseWriter, request *http.Request) {
		data := Data{Name: "yhhu", Age: 18}
		response := new(Response)
		// 这里模拟的是get请求，localhost:8080/hello?id=xxx
		id := request.FormValue("id")

		response.Code = http.StatusOK
		response.Data = append(response.Data, data)
		response.Param = id
		// 转为json
		json, _ := json2.Marshal(response)
		io.WriteString(writer, string(json))
	})
	http.ListenAndServe(":8080", nil)
}
