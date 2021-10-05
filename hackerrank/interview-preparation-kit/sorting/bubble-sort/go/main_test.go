package main

import (
	"fmt"
	"testing"
)

func TestBubbleSortWithStats(t *testing.T) {
	cases := []struct {
		nums []int32
		want bubbleSortStats
	}{
		{
			nums: []int32{1, 2},
			want: bubbleSortStats{
				NumSwaps:  0,
				FirstElem: 1,
				LastElem:  2,
			},
		},
		{
			nums: []int32{6, 4, 1},
			want: bubbleSortStats{
				NumSwaps:  3,
				FirstElem: 1,
				LastElem:  6,
			},
		},
	}
	for i, c := range cases {
		t.Run(fmt.Sprintf("%d:%v", i, c.nums), func(t *testing.T) {
			if got, want := bubbleSortWithStats(c.nums), c.want; got != want {
				t.Errorf("got %+v, want %+v", got, want)
			}
		})
	}
}
