package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

type runeSet map[rune]struct{}

func makeSet(xs string) runeSet {
	set := runeSet{}
	for _, x := range xs {
		set[x] = struct{}{}
	}
	return set
}

func subset(xs runeSet, ys runeSet) bool {
	for x := range xs {
		if _, exists := ys[x]; exists {
			return true
		}
	}
	return false
}

func shareCommonSubstr(s1 string, s2 string) bool {
	return subset(makeSet(s1), makeSet(s2))
}

func readLine(r *bufio.Reader) (string, error) {
	s, err := r.ReadString('\n')
	if err != nil {
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
	if shareCommonSubstr(s1, s2) {
		fmt.Println("YES")
	} else {
		fmt.Println("NO")
	}
	return nil
}

func main() {
	r := bufio.NewReaderSize(os.Stdin, 1024)
	if err := do(r); err != nil {
		panic(err)
	}
}
