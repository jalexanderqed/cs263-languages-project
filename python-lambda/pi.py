import time
import sys


def calcPi(iterations):
    multiplier = 1.0
    posOrNeg = -1.0
    denom = 3.0
    for i in range(iterations):
        multiplier = multiplier + posOrNeg / denom
        denom = denom + 2
        posOrNeg = posOrNeg * -1
    result = 4.0 * multiplier
    return result


def run(event, context):
    iterations = event['inputInt']
    start = int(round(time.time() * 1000))
    result = calcPi(iterations)
    end = int(round(time.time() * 1000))
    return {
        'outputDouble': result,
        'longTime1': end - start,
        'outputString': "(python) calculated Pi with " + str(iterations) + " iterations"
    }


if __name__ == "__main__":
    print(run({
        'inputInt': int(sys.argv[1]),
        'inputString': sys.argv[2]
    }, {}))
