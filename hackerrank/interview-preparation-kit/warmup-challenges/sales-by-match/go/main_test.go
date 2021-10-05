package main

import (
	"fmt"
	"testing"
)

func TestCountPairs(t *testing.T) {
	cases := []struct {
		num  []int
		want int
	}{
		{
			num:  nil,
			want: 0,
		},
		{
			num:  []int{},
			want: 0,
		},
		{
			num:  []int{1, 2, 3, 4, 5},
			want: 0,
		},
		{
			num:  []int{1, 1},
			want: 1,
		},
		{
			num:  []int{1, 1, 1, 2, 2, 3, 4, 5, 5, 5, 5},
			want: 4,
		},
	}
	for i, c := range cases {
		t.Run(fmt.Sprintf("%d:%v", i, c.num), func(t *testing.T) {
			if got, want := CountPairs(c.num), c.want; got != want {
				t.Errorf("got %v, want %v", got, want)
			}
		})
	}
}
