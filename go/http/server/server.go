package server

import (
	"../context"
	"fmt"
	"net/http"
)

// Routable 路由表
type Routable interface {
	Route(method string, pattern string, handleFunc func(ctx *context.Context))
}

type Handler interface {
	http.Handler
	Routable
}

type Server interface {
	Routable
	Start(address string) error
}

type sdkHttpServer struct {
	Name    string
	handler Handler
}

func (s *sdkHttpServer) Route(method string, pattern string, handleFunc func(ctx *context.Context)) {
	s.handler.Route(method, pattern, handleFunc)
}

func (s *sdkHttpServer) Start(address string) error {
	http.Handle("/", s.handler)
	return http.ListenAndServe(address, nil)
}

var _ Server = &sdkHttpServer{}

func NewHttpServer(name string) Server {
	return &sdkHttpServer{
		Name:    name,
		handler: NewHandlerBaseOnMap(),
	}
}

type HandlerBasedOnMap struct {
	// key 为method+url
	handlers map[string]func(ctx *context.Context)
}

func (h *HandlerBasedOnMap) ServeHTTP(writer http.ResponseWriter, request *http.Request) {
	key := h.key(request.Method, request.URL.Path)
	if handler, ok := h.handlers[key]; ok {
		handler(context.NewContext(writer, request))
	} else {
		writer.WriteHeader(http.StatusNotFound)
		_, err := writer.Write([]byte("source not exist"))
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
