package main
import "fmt"
import "math"

func nthPrime (n int) int {
     if n==1 {
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

func isPrime(value int) bool {
     for i:=2; i <= int(math.Floor(math.Sqrt(float64(value)))); i++ {
     	 if value%i == 0 {
     	    return false
 	 }
     }
     return value > 1
}

func main() {
   fmt.Println("---TRUE---");
   fmt.Println(isPrime(3))
   fmt.Println(isPrime(2))
   fmt.Println(isPrime(541))
   fmt.Println(isPrime(29))   
   fmt.Println(isPrime(11))
   fmt.Println("---FALSE---");
   fmt.Println(isPrime(8))
   fmt.Println(isPrime(81))
   fmt.Println(isPrime(105))
   fmt.Println(isPrime(121))
   fmt.Println(isPrime(27))

   fmt.Println("---PRIMES---")
   fmt.Println(nthPrime(1))
   fmt.Println(nthPrime(2))
   fmt.Println(nthPrime(4))
   fmt.Println(nthPrime(100))	

}