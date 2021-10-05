package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
)

type bubbleSortStats struct {
	NumSwaps  int
	FirstElem int32
	LastElem  int32
}

func bubbleSortWithStats(nums []int32) bubbleSortStats {
	stats := bubbleSortStats{}
	for i := 0; i < len(nums); i++ {
		for j := 0; j < len(nums)-1; j++ {
			if nums[j] > nums[j+1] {
				tmp := nums[j]
				nums[j] = nums[j+1]
				nums[j+1] = tmp
				stats.NumSwaps++
			}
		}
	}
	stats.FirstElem = nums[0]
	stats.LastElem = nums[len(nums)-1]
	return stats
}

func tokenize(s string) []string {
	tokens := []string{}
	for _, token := range strings.Split(strings.Trim(s, "\n\t "), " ") {
		if token == "" {
			continue
		}
		tokens = append(tokens, token)
	}
	return tokens
}

func do(r *bufio.Reader) error {
	// trash first input which is just a number which we don't
	// really have to care about.
	if _, err := r.ReadString('\n'); err != nil {
		return err
	}
	arrStr, err := r.ReadString('\n')
	if err != nil && err != io.EOF {
		return err
	}
	nums := []int32{}
	for _, token := range tokenize(arrStr) {
		num, err := strconv.ParseInt(token, 10, 64)
		if err != nil {
			return err
		}
		nums = append(nums, int32(num))
	}
	stats := bubbleSortWithStats(nums)
	fmt.Println("Array is sorted in", stats.NumSwaps, "swaps")
	fmt.Println("First Element:", stats.FirstElem)
	fmt.Println("Last Element:", stats.LastElem)
	return nil
}

func main() {
	r := bufio.NewReaderSize(os.Stdin, 1024)
	if err := do(r); err != nil {
		panic(err)
	}
}
