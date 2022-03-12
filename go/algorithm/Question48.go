package main

//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
//
//
// 示例 2：
//
//
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
//
//
//
//
// 提示：
//
//
// n == matrix.length == matrix[i].length
// 1 <= n <= 20
// -1000 <= matrix[i][j] <= 1000
//
//
//
// Related Topics 数组 数学 矩阵 👍 1204 👎 0

func main() {
	matrix := [][]int{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}
	rotate(matrix)

}

func rotate(matrix [][]int) {
	// 1.根据对角线先转换矩阵
	for row, value := range matrix {
		for col, innerValue := range value {
			// 只需要遍历1/2
			if col <= row {
				// 2.暂存临时值
				tmpVal := matrix[col][row]
				// 3.对角线转换
				matrix[col][row] = innerValue
				matrix[row][col] = tmpVal
			}
		}
	}
	// 4. 每行数据调转
	for index, _ := range matrix {
		for i, j := 0, len(matrix)-1; i < j; i, j = i+1, j-1 {
			tmp := matrix[index][j]
			matrix[index][j] = matrix[index][i]
			matrix[index][i] = tmp
		}
	}
}
