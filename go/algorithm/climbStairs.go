package main

import "fmt"

//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
//每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
//注意：给定 n 是一个正整数。

func main() {
	fmt.Println(climbStairs(4))
}

func climbStairs(n int) int {
	var local = make([]int, n+1)
	if n == 1 {
		return 1
	}
	local[1] = 1
	local[2] = 2
	for i := 3; i <= n; i++ {
		local[i] = local[i-1] + local[i-2]
	}
	return local[n]
}
