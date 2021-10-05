package main

import (
	"fmt"
	"testing"
)

func TestCommonChild(t *testing.T) {
	cases := []struct {
		s1   string
		s2   string
		want int32
	}{
		{
			s1:   "",
			s2:   "",
			want: 0,
		},
		{
			s1:   "ABC",
			s2:   "DEF",
			want: 0,
		},
		{
			s1:   "ABCD",
			s2:   "ABDC",
			want: 3,
		},
		{
			s1:   "HARRY",
			s2:   "SALLY",
			want: 2,
		},
		{
			s1:   "SHINCHAN",
			s2:   "NOHARAAA",
			want: 3,
		},
		{
			s1:   "ELGGYJWKTDHLXJRBJLRYEJWVSUFZKYHOIKBGTVUTTOCGMLEXWDSXEBKRZTQUVCJNGKKRMUUBACVOEQKBFFYBUQEMYNENKYYGUZSP",
			s2:   "FRVIFOVJYQLVZMFBNRUTIYFBMFFFRZVBYINXLDDSVMPWSQGJZYTKMZIPEGMVOUQBKYEWEYVOLSHCMHPAZYTENRNONTJWDANAMFRX",
			want: 27,
		},
	}
	for i, c := range cases {
		t.Run(fmt.Sprintf("%d:(%q,%q)", i, c.s1, c.s2), func(t *testing.T) {
			if got, want := commonChild2(c.s1, c.s2), c.want; got != want {
				t.Errorf("got %d, want %d", got, want)
			}
		})
	}
}
