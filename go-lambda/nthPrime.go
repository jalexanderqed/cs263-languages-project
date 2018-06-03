package main

import (
	"fmt"
	"github.com/aws/aws-lambda-go/lambda"
	"math"
	"os"
	"strconv"
	"time"
)

type RequestClass struct {
	InputInt    int    `json:"inputInt"`
	InputString string `json:"inputString"`
}

type ResponseClass struct {
	OutputInt    int     `json:"outputInt"`
	Time1        float64 `json:"time1"`
	Time2        float64 `json:"time2"`
	LongTime1    int64   `json:"longTime1"`
	LongTime2    int64   `json:"longTime2"`
	OutputDouble float64 `json:"outputDouble"`
	OutputString string  `json:"outputString"`
}

func isPrime(value int) bool {
	for i := 2; i <= int(math.Floor(math.Sqrt(float64(value)))); i++ {
		if value%i == 0 {
			return false
		}
	}
	return value > 1
}

func nthPrime(n int) int {
	if n == 1 {
		return 2
	}
	count := 1
	num := 3
	for count < n {
		if isPrime(num) {
			count = count + 1
		}
		num = num + 2
	}
	return num - 2
}

func Handler(input RequestClass) (ResponseClass, error) {
	response := ResponseClass{}
	start := time.Now()
	response.OutputInt = nthPrime(input.InputInt)
	end := time.Now()
	response.LongTime1 = int64(end.Sub(start) / time.Millisecond)
	response.OutputString = fmt.Sprintf("(go) calculated %d prime", input.InputInt)

	return response, nil
}

func main() {
	if len(os.Args) == 3 {
		var req RequestClass
		input, err := strconv.Atoi(os.Args[1])
		req.InputInt = input
		req.InputString = os.Args[2]
		output, err := Handler(req)
		err = err
		fmt.Println(output)
	} else {
		lambda.Start(Handler)
	}
}
