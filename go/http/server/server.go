package server

import (
	"example.com/golang/http/context"
	"example.com/golang/http/filter"
	"fmt"
	"net/http"
)

// Routable 路由表
type Routable interface {
	Route(method string, pattern string, handleFunc func(ctx *context.Context))
}

type Handler interface {
	ServerHTTP(c *context.Context)
	Routable
}

type Server interface {
	Routable
	Start(address string) error
}

type sdkHttpServer struct {
	Name    string
	handler Handler
	root    filter.Filter
}

func (s *sdkHttpServer) Route(method string, pattern string, handleFunc func(ctx *context.Context)) {
	s.handler.Route(method, pattern, handleFunc)
}

func (s *sdkHttpServer) Start(address string) error {
	http.HandleFunc("/", func(writer http.ResponseWriter, request *http.Request) {
		c := context.NewContext(writer, request)
		s.root(c)
	})
	return http.ListenAndServe(address, nil)
}

var _ Server = &sdkHttpServer{}

func NewHttpServer(name string, filterBuilders ...filter.Builder) Server {
	handler := NewHandlerBaseOnMap()

	// 必须要经过的拦截器
	var rootFilter filter.Filter = func(c *context.Context) {
		fmt.Println("进入业务处理逻辑")
		handler.ServerHTTP(c)
	}

	for i := len(filterBuilders) - 1; i >= 0; i-- {
		filterBuilder := filterBuilders[i]
		// 一层层往外包，最后调用的是handler.ServerHTTP(c)
		rootFilter = filterBuilder(rootFilter)
	}
	return &sdkHttpServer{
		Name:    name,
		handler: handler,
		// 这里使用自己实现的MetricsFilterBuilder保证方法一定被计时
		root: filter.MetricsFilterBuilder(rootFilter),
	}
}

type HandlerBasedOnMap struct {
	// key 为method+url
	handlers map[string]func(ctx *context.Context)
}

func (h *HandlerBasedOnMap) ServerHTTP(c *context.Context) {
	key := h.key(c.R.Method, c.R.URL.Path)
	if handler, ok := h.handlers[key]; ok {
		handler(c)
	} else {
		c.W.WriteHeader(http.StatusNotFound)
		_, err := c.W.Write([]byte("source not exist"))
		if err != nil {
			fmt.Println("message handle error")
		}
	}
}

func (h *HandlerBasedOnMap) Route(method string, pattern string, handleFunc func(ctx *context.Context)) {
	key := h.key(method, pattern)
	if h.handlers == nil {
		h.handlers = make(map[string]func(ctx *context.Context))
	}
	if h.handlers[key] != nil {
		panic("地址重复！")
	}
	h.handlers[key] = handleFunc
}

func (h *HandlerBasedOnMap) key(method string, pattern string) string {
	return method + "#" + pattern
}

func NewHandlerBaseOnMap() Handler {
	return &HandlerBasedOnMap{
		handlers: make(map[string]func(ctx *context.Context)),
	}
}
