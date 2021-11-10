package main

import (
	"flag"
	"fmt"
	"os"
)

func main() {
	// 对于flag而言，第一个字段为命名，第二个字段为默认值，第三个值为帮助信息
	name := flag.String("name", "张三", "姓名")
	age := flag.Int("age", 18, "年龄")
	married := flag.Bool("married", false, "婚否")
	delay := flag.Duration("d", 0, "时间间隔")
	flag.Parse()
	fmt.Println("os args is", os.Args)
	fmt.Println(*name, *age, *married, *delay)
}
