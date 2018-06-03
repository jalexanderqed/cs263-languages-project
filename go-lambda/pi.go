package main

import (
	"fmt"
	"github.com/aws/aws-lambda-go/lambda"
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

func calcPi(iterations int) float64 {
	multiplier := 1.0
	posOrNeg := -1.0
	denom := 3.0
	for i := 0; i < iterations; i++ {
		multiplier = multiplier + posOrNeg/denom
		denom = denom + 2
		posOrNeg = posOrNeg * -1
	}
	return 4.0 * multiplier
}

func Handler(input RequestClass) (ResponseClass, error) {
	response := ResponseClass{}
	start := time.Now()
	response.OutputDouble = calcPi(input.InputInt)
	end := time.Now()
	response.LongTime1 = int64(end.Sub(start) / time.Millisecond)
	response.OutputString = fmt.Sprintf("(go) calculated Pi with %d iterations", input.InputInt)

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
