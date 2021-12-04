package model

import (
	"net/http"
)

type ResponseEntity struct {
	Code    int         `json:"code"`
	Message string      `json:"message"`
	Data    interface{} `json:"data"`
}

func SuccessResponse(message string, data interface{}) *ResponseEntity {
	return &ResponseEntity{
		Code:    http.StatusOK,
		Message: message,
		Data:    data,
	}
}

func FailResponse(message string, data interface{}) *ResponseEntity {
	return &ResponseEntity{
		Code:    http.StatusInternalServerError,
		Message: message,
		Data:    data,
	}
}

func NewResponse(code int, message string, data interface{}) *ResponseEntity {
	return &ResponseEntity{
		Code:    code,
		Message: message,
		Data:    data,
	}
}
