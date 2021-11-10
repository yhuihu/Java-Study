package main

import (
	"fmt"
	"regexp"
)

type person struct {
	name string
	// 校验为邮箱
	email string `validate:"email"`
	// 校验年龄大于0
	age int `validate:"gt=0"`
}

// 邮箱校验
func validateEmail(email string) bool {
	if pass, _ := regexp.MatchString(`^([\w._]{2,10})@(\w+).([a-z]{2,4})$`, email); pass {
		return true
	}
	return false
}

func validate(v interface{}) bool {
	return false
}

func main() {
	fmt.Println(validateEmail("123@qq.com"))
	ch := make(chan int)
	ch <- 1
}
