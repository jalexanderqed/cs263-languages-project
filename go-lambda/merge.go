package main

import (
	"fmt"
	"time"
	"github.com/aws/aws-lambda-go/lambda"
	"math/rand"
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

func mergeSortSync(arr []int, depth int) []int {
	const THREAD_DEPTH = 3

	if len(arr) <= 1 {
		return arr
	}

	if len(arr) == 2 {
		if arr[1] < arr[0] {
			t := arr[1]
			arr[1] = arr[0]
			arr[0] = t
			return arr
		}
	}

	split := len(arr) / 2

	left := make([]int, split)
	right := make([]int, len(arr)-split)

	copy(left, arr[:split])
	copy(right, arr[split:])

	ch := make(chan []int)
	var leftRes []int

	if depth <= THREAD_DEPTH {
		go mergeSortAsync(left, depth+1, ch)
	} else {
		leftRes = mergeSortSync(left, depth+1)
	}

	rightRes := mergeSortSync(right, depth+1)

	if depth <= THREAD_DEPTH {
		leftRes = <-ch
	}

	lPos := 0
	rPos := 0
	cPos := 0

	for lPos < len(leftRes) || rPos < len(rightRes) {
		if rPos >= len(rightRes) {
			arr[cPos] = leftRes[lPos]
			lPos++
		} else if lPos >= len(leftRes) {
			arr[cPos] = rightRes[rPos]
			rPos++
		} else if leftRes[lPos] < rightRes[rPos] {
			arr[cPos] = leftRes[lPos]
			lPos++
		} else {
			arr[cPos] = rightRes[rPos]
			rPos++
		}
		cPos++
	}

	return arr
}

func mergeSortAsync(arr []int, depth int, c chan []int) {
	c <- mergeSortSync(arr, depth)
}

func Handler(input RequestClass) (ResponseClass, error) {
	response := ResponseClass{}

	rand.Seed(time.Now().UTC().UnixNano())
	start2 := time.Now()

	arr := make([]int, input.InputInt)
	for i := 0; i < input.InputInt; i++ {
		//arr[i] = rand.Intn(2147483647)
		arr[i] = rand.Intn(100)
	}

	ch := make(chan []int)

	start := time.Now()
	go mergeSortAsync(arr, 0, ch)
	result := <-ch
	end := time.Now()

	result[1] = result[0]

	response.LongTime1 = int64(end.Sub(start) / time.Millisecond)
	response.LongTime2 = int64(end.Sub(start2) / time.Millisecond)

	response.OutputString = fmt.Sprintf("perfomed merge sort with %d elements", input.InputInt)

	return response, nil
}

func main() {
	lambda.Start(Handler)
}
