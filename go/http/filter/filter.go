package filter

import (
	"../context"
	"fmt"
	"time"
)

type Builder func(next Filter) Filter

type Filter func(c *context.Context)

func MetricsFilterBuilder(next Filter) Filter {
	return func(c *context.Context) {
		start := time.Now()
		next(c)
		fmt.Printf("执行时间：%d ms", time.Since(start)/time.Millisecond)
	}
}
