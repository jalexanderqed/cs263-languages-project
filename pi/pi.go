package main

import "fmt"

func calcPi(iterations int) float64 {
     multiplier := 1.0
     posOrNeg := -1.0
     denom := 3.0
     for i := 0; i <= iterations; i++ {
     	 multiplier = multiplier + posOrNeg / denom
	 denom = denom + 2
	 posOrNeg = posOrNeg * -1
     }
     return 4.0 * multiplier
}

func main() {
     fmt.Println("Value of pi is",calcPi(1000))
}
