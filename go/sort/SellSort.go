package main

import "fmt"

func sort(arr []int) {
	deep := len(arr)
	for deep > 1 {
		deep = deep / 2
		for i := 0; i < deep; i++ {
			for j := i + deep; j < len(arr); j = j + deep {
				tmpValue := (arr)[j]
				var k int
				for k = j - deep; k >= 0 && ((arr)[k] > tmpValue); k = k - deep {
					// 由于前面的比后面大，因此将后面的值修改为前一个区间的值
					(arr)[k+deep] = (arr)[k]
				}
				// 这个区间排完序了，这时将最小的一个修改为临时值
				arr[k+deep] = tmpValue
			}
		}
	}

}

func main() {
	arr := []int{5, 3, 9, 12, 6, 1, 7, 2, 4, 11, 8, 10}
	sort(arr)
	fmt.Println(arr)
	//arr :=[]int{0,3,6,2,8}
	//fmt.Println(arr)
}
