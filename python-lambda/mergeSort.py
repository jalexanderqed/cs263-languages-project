def mergeSort(arr):
    length = len(arr)
    if length <= 1:
        return arr
    if length == 2:
        if arr[1] < arr[0]:
            t = arr[1]
            arr[1] = arr[0]
            arr[0] = t
            return arr
    
    middle = (int)(length / 2)
    left = mergeSort (arr[:middle])
    right = mergeSort(arr[middle:])
    return merge(left, right)

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

toMerge = [4, 9, 6, 8, 1, 3, 2, 19, 16, 0, 20, 5, 7]

print mergeSort(toMerge)
