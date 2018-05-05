def pi(iterations):
    multiplier = 1.0
    posOrNeg = -1.0
    denom = 3.0
    for i in range(iterations):
        multiplier = multiplier + posOrNeg/denom
        denom = denom + 2
        posOrNeg = posOrNeg * -1
    return 4.0 * multiplier

print pi(10000)
