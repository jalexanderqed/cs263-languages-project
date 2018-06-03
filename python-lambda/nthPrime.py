import time
import sys


def is_prime(num):
    factor = 2
    while (factor * factor <= num):
        if num % factor == 0:
            return False
        factor += 1
    return True


def nthPrime(n):
    if n == 1:
        return 2
    count = 1
    num = 3
    while (count < n):
        if is_prime(num):
            count += 1
        num += 2
    return num - 2


def run(event, context):
    iterations = event['inputInt']
    start = int(round(time.time() * 1000))
    result = nthPrime(iterations)
    end = int(round(time.time() * 1000))
    return {
        'outputInt': result,
        'longTime1': end - start,
        'outputString': "(python) calculated  " + str(iterations) + "th prime"
    }


if __name__ == "__main__":
    print(run({
        'inputInt': int(sys.argv[1]),
        'inputString': sys.argv[2]
    }, {}))
