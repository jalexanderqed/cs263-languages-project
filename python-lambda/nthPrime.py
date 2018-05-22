def nthPrime(n):
    if n == 1:
        return 2
    count = 1
    num = 3
    while (count < n):
        if is_prime(num):
            count +=1
        num +=2
    return num - 2
    
def is_prime(num):
    factor = 2
    while (factor * factor <= num):
        if num%factor == 0:
             return False
        factor +=1
    return True

print nthPrime(1)
print nthPrime(2)
print nthPrime(3)
print nthPrime(100)
print nthPrime(1000)
print nthPrime(10000)
print nthPrime(100000)
print nthPrime(1000000)
