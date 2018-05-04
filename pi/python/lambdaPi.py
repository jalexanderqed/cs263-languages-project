'''
class RequestClass:
    def __init__(self, inputInt, inputString):
        self.inputInt = inputInt
        self.inputString = inputString
'''

def piLambda(event, context):
    iterations = event['inputInt']
    multiplier = 1.0
    posOrNeg = -1.0
    denom = 3.0
    for i in range(iterations):
        multiplier = multiplier + posOrNeg/denom
        denom = denom + 2
        posOrNeg = posOrNeg * -1
    result = 4.0 * multiplier
    return {
        'outputInt' : result
    }

