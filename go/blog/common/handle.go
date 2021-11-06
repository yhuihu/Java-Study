package handle

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

// api错误的结构体
type APIException struct {
	Code      int    `json:"-"`
	ErrorCode int    `json:"errorCode"`
	Msg       string `json:"msg"`
	Request   string `json:"request"`
}

// 实现接口
func (e *APIException) Error() string {
	return e.Msg
}

func newAPIException(code int, errorCode int, msg string) *APIException {
	return &APIException{
		Code:      code,
		ErrorCode: errorCode,
		Msg:       msg,
	}
}

type HandlerFunc func(c *gin.Context) error

func Wrapper(handler HandlerFunc) func(c *gin.Context) {
	return func(c *gin.Context) {
		var (
			err error
		)
		err = handler(c)
		if err != nil {
			var apiException *APIException
			if h, ok := err.(*APIException); ok {
				apiException = h
			} else if e, ok := err.(error); ok {
				if gin.Mode() == "debug" {
					// 错误
					apiException = UnknownError(e.Error())
				} else {
					// 未知错误
					apiException = UnknownError(e.Error())
				}
			} else {
				apiException = ServerError()
			}
			apiException.Request = c.Request.Method + " " + c.Request.URL.String()
			c.JSON(apiException.Code, apiException)
			return
		}
	}
}

const (
	serverError = 1000 // 系统错误

	notFound = 1001 // 401错误

	undefinedError = 1002 // 未知错误

	paramError = 1003 // 参数错误

	authError = 1004 // 错误

)

// ServerError 500 错误处理
func ServerError() *APIException {
	return newAPIException(http.StatusInternalServerError, serverError, http.StatusText(http.StatusInternalServerError))
}

// NotFound 404 错误
func NotFound() *APIException {
	return newAPIException(http.StatusNotFound, notFound, http.StatusText(http.StatusNotFound))
}

// UnknownError 未知错误
func UnknownError(message string) *APIException {
	return newAPIException(http.StatusForbidden, undefinedError, message)
}

// ParameterError 参数错误
func ParameterError(message string) *APIException {
	return newAPIException(http.StatusBadRequest, paramError, message)
}
