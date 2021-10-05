package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strings"
)

func max(x int32, y int32) int32 {
	if x > y {
		return x
	}
	return y
}

func commonChild(s1 string, s2 string) int32 {
	if s1 == "" || s2 == "" {
		return 0
	}
	if s1[0] == s2[0] {
		return 1 + commonChild(s1[1:], s2[1:])
	}
	return max(commonChild(s1, s2[1:]), commonChild(s1[1:], s2))
}

func commonChild2(s1 string, s2 string) int32 {
	prev := make([]int32, len(s2)+1)
	curr := make([]int32, len(s2)+1)
	for _, charS1 := range s1 {
		for j, charS2 := range s2 {
			if charS1 == charS2 {
				curr[j+1] = prev[j] + 1
			} else {
				curr[j+1] = max(prev[j+1], curr[j])
			}
		}
		curr, prev = prev, curr
	}
	return prev[len(prev)-1]
}

func readLine(r *bufio.Reader) (string, error) {
	s, err := r.ReadString('\n')
	if err != nil && err != io.EOF {
		return "", err
	}
	return strings.Trim(s, "\n"), nil
}

func do(r *bufio.Reader) error {
	s1, err := readLine(r)
	if err != nil {
		return err
	}
	s2, err := readLine(r)
	if err != nil {
		return err
	}
	fmt.Println(commonChild2(s1, s2))
	return nil
}

func main() {
	r := bufio.NewReaderSize(os.Stdin, 5000)
	if err := do(r); err != nil {
		panic(err)
	}
}
