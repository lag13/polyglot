package main

import (
	"fmt"
	"reflect"
	"testing"
)

func whatFlavors(costs []int32, money int32) []int32 {
	return nil
}

func main() {
	// var heyThere int
	// var heyThereWoa int
	// fmt.Println(heyThereWoa)
	fmt.Println("hello")
}

func Testsomethin(t *testing.T) {
	cases := []struct {
		input int64
		want  string
	}{
		{
			input: "you're",
			want:  "great",
		},
	}
	// ASDFSDTODO: Allow variable number of arguments
	// TODO: Look for the "package" keyword and if it's _test then We keep somethin otherwise we lowercase the first letter of somethin
	for i, c := range cases {
		t.Run(fmt.Sprintf("%d:(%v)", i, c.input), func(t *testing.T) {
			if got := somethin(c.input); !reflect.DeepEqual(got, c.want) {
				t.Errorf("got %v, want %v", got, c.want)
			}
		})
	}
}
