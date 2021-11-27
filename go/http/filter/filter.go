package filter

import (
	"../context"
	"fmt"
	"time"
)

type FilterBuilder func(next Filter) Filter

type Filter func(c *context.Context)

func MetricsFilterBuilder(next Filter) Filter {
	return func(c *context.Context) {
		start := time.Now().Nanosecond()
		next(c)
		end := time.Now().Nanosecond()
		fmt.Printf("执行时间：%d", end-start)
	}
}
