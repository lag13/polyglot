package main

import (
	"fmt"
	"reflect"
	"testing"
)

func TestLongestCommonSubstrs(t *testing.T) {
	cases := []struct {
		s1   string
		s2   string
		want stringSet
	}{
		{
			s1:   "",
			s2:   "",
			want: stringSet{"": struct{}{}},
		},
		{
			s1:   "abc",
			s2:   "def",
			want: stringSet{"": struct{}{}},
		},
		{
			s1:   "hey",
			s2:   "hey buddy, come here often?",
			want: stringSet{"hey": struct{}{}},
		},
		{
			s1:   "heyyou",
			s2:   "youhey",
			want: stringSet{"hey": struct{}{}, "you": struct{}{}},
		},
	}
	for i, c := range cases {
		t.Run(fmt.Sprintf("%d:(%q,%q)", i, c.s1, c.s2), func(t *testing.T) {
			if got, want := longestCommonSubstrs(c.s1, c.s2), c.want; !reflect.DeepEqual(got, want) {
				t.Errorf("got %#v, want %#v", got, want)
			}
		})
	}
}
