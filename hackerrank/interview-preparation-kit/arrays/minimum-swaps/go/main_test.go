package main

import (
	"fmt"
	"os"
	"testing"
)

func mustReadFile(f string) []byte {
	b, err := os.ReadFile(f)
	if err != nil {
		panic(err)
	}
	return b
}

func mustStrToNums(s string) []int32 {
	nums, err := strToNums(s)
	if err != nil {
		panic(err)
	}
	return nums
}

func TestMinimumSwaps(t *testing.T) {
	cases := []struct {
		input []int32
		want  int32
	}{
		{
			input: nil,
			want:  0,
		},
		{
			input: []int32{},
			want:  0,
		},
		{
			input: []int32{1},
			want:  0,
		},
		{
			input: []int32{2, 1},
			want:  1,
		},
		{
			input: []int32{1, 3, 5, 2, 4, 6, 7},
			want:  3,
		},
		{
			input: []int32{7, 1, 3, 2, 4, 5, 6},
			want:  5,
		},
		{
			input: mustStrToNums(string(mustReadFile("large-input.txt"))),
			want:  49990,
		},
	}
	for i, c := range cases {
		t.Run(fmt.Sprintf("%d", i), func(t *testing.T) {
			if got, want := minimumSwaps(c.input), c.want; got != want {
				t.Errorf("got %d, want %d", got, want)
			}
		})
	}
}
