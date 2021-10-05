package main

import "fmt"

type stringSet map[string]struct{}

func allSubstrs(s string) stringSet {
	res := stringSet{"": struct{}{}}
	for i := 0; i < len(s)-1; i++ {
		for j := i + 1; j <= len(s); j++ {
			res[s[i:j]] = struct{}{}
		}
	}
	return res
}

func intersection(xs stringSet, ys stringSet) stringSet {
	res := stringSet{}
	for x := range xs {
		if _, exists := ys[x]; exists {
			res[x] = struct{}{}
		}
	}
	return res
}

func longestCommonSubstrs(s1 string, s2 string) stringSet {
	commonSubstrs := intersection(allSubstrs(s1), allSubstrs(s2))
	longestSubstrLen := 0
	for substr := range commonSubstrs {
		if len(substr) > longestSubstrLen {
			longestSubstrLen = len(substr)
		}
	}
	longestSubstrs := stringSet{}
	for substr := range commonSubstrs {
		if len(substr) == longestSubstrLen {
			longestSubstrs[substr] = struct{}{}
		}
	}
	return longestSubstrs
}

func main() {
	fmt.Println(allSubstrs("hello"))
}
