package main

import (
	"fmt"
	"testing"
)

func TestMaxHourglassSum(t *testing.T) {
	cases := []struct {
		input [][]int32
		want  int32
	}{
		{
			input: [][]int32{
				{1, 1, 1, 0, 0, 0},
				{0, 1, 0, 0, 0, 0},
				{1, 1, 1, 0, 0, 0},
				{0, 0, 2, 4, 4, 0},
				{0, 0, 0, 2, 0, 0},
				{0, 0, 1, 2, 4, 0},
			},
			want: 19,
		},
	}
	for i, c := range cases {
		t.Run(fmt.Sprintf("%d", i), func(t *testing.T) {
			if got, want := maxHourglassSum(c.input), c.want; got != want {
				t.Errorf("got %d, want %d", got, want)
			}
		})
	}
}
