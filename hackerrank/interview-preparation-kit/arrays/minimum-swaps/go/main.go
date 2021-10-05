package main

import (
	"fmt"
	"os"
	"strconv"
	"strings"
)

func swap(arr []int32, i int32, j int32) []int32 {
	temp := arr[i]
	arr[i] = arr[j]
	arr[j] = temp
	return arr
}

func indexOf(arr []int32, elem int32) int32 {
	for i := int32(0); i < int32(len(arr)); i++ {
		if arr[i] == elem {
			return i
		}
	}
	return -1
}

func strToNums(s string) ([]int32, error) {
	nums := []int32{}
	tokens := strings.Split(strings.Trim(s, " \r\n\t"), " ")
	for _, token := range tokens {
		if token == "" {
			continue
		}
		num, err := strconv.ParseInt(token, 10, 32)
		if err != nil {
			return nil, err
		}
		nums = append(nums, int32(num))
	}
	return nums, nil
}

func minimumSwaps(arr []int32) int32 {
	numSwaps := int32(0)
	for i := int32(0); i < int32(len(arr)); i++ {
		if arr[i] == i+1 {
			continue
		}
		arr = swap(arr, i, indexOf(arr, i+1))
		numSwaps++
	}
	return numSwaps
}

func do() error {
	b, err := os.ReadFile("large-input.txt")
	if err != nil {
		return err
	}
	nums, err := strToNums(string(b))
	if err != nil {
		return err
	}
	fmt.Println(minimumSwaps(nums))
	return nil
}

func main() {
	if err := do(); err != nil {
		panic(err)
	}
}
