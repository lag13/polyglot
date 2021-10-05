package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strconv"
	"strings"
)

func frequencies(nums []int) map[int]int {
	res := map[int]int{}
	for _, num := range nums {
		res[num] = res[num] + 1
	}
	return res
}

func CountPairs(nums []int) int {
	res := 0
	for _, count := range frequencies(nums) {
		res = res + count/2
	}
	return res
}

func readInt(r *bufio.Reader) (int, error) {
	input, err := r.ReadString('\n')
	if err != nil {
		return 0, err
	}
	return strconv.Atoi(input[:len(input)-1])
}

func tokenize(s string) []string {
	tokens := []string{}
	for _, token := range strings.Split(strings.Trim(s, " \t\n\r"), " ") {
		if token == "" {
			continue
		}
		tokens = append(tokens, token)
	}
	return tokens
}

func do(r *bufio.Reader) error {
	if _, err := r.ReadString('\n'); err != nil {
		return err
	}
	input, err := r.ReadString('\n')
	if err != nil && err != io.EOF {
		return err
	}
	nums := []int{}
	for _, token := range tokenize(input) {
		num, err := strconv.Atoi(token)
		if err != nil {
			return err
		}
		nums = append(nums, num)
	}
	fmt.Println(CountPairs(nums))
	return nil
}

func main() {
	r := bufio.NewReaderSize(os.Stdin, 1024)
	if err := do(r); err != nil {
		panic(err)
	}
}
