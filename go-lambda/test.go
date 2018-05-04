package main

import (
       "fmt"
	"github.com/aws/aws-lambda-go/lambda"
)

type RequestClass struct{
	InputInt int `json:"inputInt"`
	InputString string `json:"inputString"`
}

func Handler(input RequestClass) (string, error) {
	return fmt.Sprintf("inputInt was: %d", input.InputInt), nil
}

func main() {
	lambda.Start(Handler)
}
