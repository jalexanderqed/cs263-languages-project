import threading

def mergeSort(arr, ret):
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
    left = threading.Thread(target=mergeSort, args=(arr[:middle], leftRes))
    right = threading.Thread(target=mergeSort, args=(arr[middle:], rightRes))
    left.start()
    right.start()
    left.join()
    right.join()
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

def doMergeSort(toMerge):
    ret = []
    mergeSort(toMerge, ret)
    return ret[0]

def main():
    toMerge = [4, 9, 6, 8, 1, 3, 2, 19, 16, 0, 20, 5, 7]
    print doMergeSort(toMerge)

if __name__ == "__main__":
    main()
