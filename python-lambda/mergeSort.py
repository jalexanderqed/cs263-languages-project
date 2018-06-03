import threading
import time
import random
import sys

THREAD_DEPTH = 3


def mergeSort(arr, ret, depth):
    length = len(arr)
    if length <= 1:
        ret.append(arr)
        return
    if length == 2:
        if arr[1] < arr[0]:
            t = arr[1]
            arr[1] = arr[0]
            arr[0] = t
            ret.append(arr)
            return

    middle = (int)(length / 2)
    leftRes = []
    rightRes = []
    if depth <= THREAD_DEPTH:
        left = threading.Thread(target=mergeSort, args=(arr[:middle], leftRes, depth + 1))
        left.start()
        mergeSort(arr[middle:], rightRes, depth + 1)
        left.join()
    else:
        mergeSort(arr[middle:], rightRes, depth + 1)
        mergeSort(arr[:middle], leftRes, depth + 1)
    final = merge(leftRes[0], rightRes[0])
    ret.append(final)
    return


def merge(left, right):
    leftLen = len(left)
    rightLen = len(right)
    lPos = 0
    rPos = 0
    merged = []

    while lPos < leftLen or rPos < rightLen:
        if rPos >= rightLen:
            merged.append(left[lPos])
            lPos += 1
        elif lPos >= leftLen:
            merged.append(right[rPos])
            rPos += 1
        elif left[lPos] < right[rPos]:
            merged.append(left[lPos])
            lPos += 1
        else:
            merged.append(right[rPos])
            rPos += 1
    return merged


def doMergeSort(toMerge, depth):
    ret = []
    mergeSort(toMerge, ret, depth)
    return ret[0]


def run(event, context):
    elements = event['inputInt']

    start2 = int(round(time.time() * 1000))
    arr = []
    for i in range(elements):
        arr.append(random.randint(0, 2000000))

    start = int(round(time.time() * 1000))
    arr = doMergeSort(arr, 0)
    end = int(round(time.time() * 1000))
    return {
        'longTime1': end - start,
        'longTime2': end - start2,
        'outputString': "(python) perfomed merge sort with " + str(elements) + " elements"
    }


if __name__ == "__main__":
    print(run({
        'inputInt': int(sys.argv[1]),
        'inputString': sys.argv[2]
    }, {}))
