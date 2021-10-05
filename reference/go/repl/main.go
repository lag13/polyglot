package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
	"strings"
)

func tokenize(s string) []string {
	tokens := []string{}
	for _, token := range strings.Split(strings.Trim(s, "\n\t "), " ") {
		if token == "" {
			continue
		}
		tokens = append(tokens, token)
	}
	return tokens
}

func main() {
	reader := bufio.NewReaderSize(os.Stdin, 1024)
	keepLooping := true
	doEndLoop := func([]string) {
		keepLooping = false
	}
	doEchoCmd := func(args []string) {
		fmt.Println(strings.Join(args, " "))
	}
	dispatcher := map[string]func([]string){
		"exit": doEndLoop,
		"echo": doEchoCmd,
	}
	for keepLooping {
		input, err := reader.ReadString('\n')
		if err != nil {
			if err == io.EOF {
				break
			}
			panic(err)
		}
		tokens := tokenize(input)
		if len(tokens) == 0 {
			continue
		}
		cmd := strings.ToLower(tokens[0])
		if fn, ok := dispatcher[cmd]; !ok {
			fmt.Printf("command %q not found\n", cmd)
		} else {
			fn(tokens[1:])
		}
	}
}
