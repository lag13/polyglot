package main

import (
	"bufio"
	"fmt"
	"io"
	"math"
	"os"
	"strconv"
	"strings"
)

func maxHourglassSum(arr [][]int32) int32 {
	hourglassSum := math.Inf(-1)
	for row := 0; row < len(arr)-2; row++ {
		for col := 0; col < len(arr)-2; col++ {
			newHourglassSum := float64(arr[row][col] +
				arr[row][col+1] +
				arr[row][col+2] +
				arr[row+1][col+1] +
				arr[row+2][col] +
				arr[row+2][col+1] +
				arr[row+2][col+2])
			if newHourglassSum > hourglassSum {
				hourglassSum = newHourglassSum
			}
		}
	}
	return int32(hourglassSum)
}

func tokenize(s string) []string {
	tokens := []string{}
	for _, token := range strings.Split(strings.Trim(s, "\n\r\t "), " ") {
		if token == "" {
			continue
		}
		tokens = append(tokens, token)
	}
	return tokens
}

func readLine(r *bufio.Reader) (string, error) {
	s, err := r.ReadString('\n')
	if err != nil && err != io.EOF {
		return "", err
	}
	return s, nil
}

func readArr(r *bufio.Reader) ([][]int32, error) {
	arr := [][]int32{}
	for i := 0; i < 6; i++ {
		s, err := readLine(r)
		if err != nil {
			return nil, err
		}
		tokens := tokenize(s)
		nums := []int32{}
		for _, token := range tokens {
			num, err := strconv.ParseInt(token, 10, 32)
			if err != nil {
				return nil, err
			}
			nums = append(nums, int32(num))
		}
		arr = append(arr, nums)
	}
	return arr, nil
}

func do(r *bufio.Reader) error {
	arr, err := readArr(r)
	if err != nil {
		return err
	}
	fmt.Println(maxHourglassSum(arr))
	return nil
}

func main() {
	r := bufio.NewReaderSize(os.Stdin, 1024)
	if err := do(r); err != nil {
		panic(err)
	}
}
