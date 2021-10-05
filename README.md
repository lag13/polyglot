# polyglot

Implementing algorithms in multiple languages.

## Rationale

I wanted one place (a monorepo of sorts I suppose) to store
implementations of various algorithms (whether that be problems from
hackerrank or more fundamental things) in multiple languages. Could be
useful for reference purposes.

I suppose "rosetta" could have been another fun name for this repo. Or
if there is a famous library known for haing texts in multiple
languages that could also be sweet to use.

## Lanugages
How I develop in each language

### Clojure

lein new app my-cool-app
cd my-cool-app
(in emacs editor)
- M-x cider-jack-in
- C-c C-t C-p ;; Run unit tests

adding a new dependency:
- add the dependency to project.clj
- M-x cider-quit
- M-x cider-jack-in

### Go

mkdir my-cool-app
cd my-cool-app
touch main.go # create main.go however you see fit
go mod init
touch main_test.go # create a test file however you see fit
go test
go run main.go

adding a new dependency: Usually it happens automatically via the
`goimports` command that I have configured to run on save but
sometimes it seems to have trouble and I end up running `go mod tidy`.
