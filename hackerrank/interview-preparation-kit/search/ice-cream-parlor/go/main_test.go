package main

import (
	"fmt"
	"reflect"
	"testing"
)

func TestWhatFlavors(t *testing.T) {
	cases := []struct {
		costs []int32
		money int32
		want  []int32
	}{}
	for i, c := range cases {
		t.Run(fmt.Sprintf("%d", i), func(t *testing.T) {
			if got, want := whatFlavors(c.costs, c.money), c.want; !reflect.DeepEqual(got, want) {
				t.Errorf("got %v, want %v", got, want)
			}
		})
	}
}
