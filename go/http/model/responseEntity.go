package model

import (
	"net/http"
	"strconv"
)

type ResponseEntity struct {
	Code    string      `json:"code"`
	Message string      `json:"message"`
	Data    interface{} `json:"data"`
}

func SuccessResponse(message string, data interface{}) *ResponseEntity {
	return &ResponseEntity{
		Code:    strconv.Itoa(http.StatusOK),
		Message: message,
		Data:    data,
	}
}

func FailResponse(message string, data interface{}) *ResponseEntity {
	return &ResponseEntity{
		Code:    strconv.Itoa(http.StatusInternalServerError),
		Message: message,
		Data:    data,
	}
}

func NewResponse(code string, message string, data interface{}) *ResponseEntity {
	return &ResponseEntity{
		Code:    code,
		Message: message,
		Data:    data,
	}
}
