package main

import (
	"fmt"
	"testing"
)

func TestShareCommonSubstr(t *testing.T) {
	cases := []struct {
		s1   string
		s2   string
		want bool
	}{
		{
			s1:   "abcd",
			s2:   "efgh",
			want: false,
		},
		{
			s1:   "hey",
			s2:   "buddy",
			want: true,
		},
	}
	for i, c := range cases {
		t.Run(fmt.Sprintf("%d:(%q,%q)", i, c.s1, c.s2), func(t *testing.T) {
			if got, want := shareCommonSubstr(c.s1, c.s2), c.want; got != want {
				t.Errorf("got %v, want %v", got, want)
			}
		})
	}
}
