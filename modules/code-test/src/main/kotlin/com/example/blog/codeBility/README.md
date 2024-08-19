# [CodeBility](https://app.codility.com/programmers/lessons/1-iterations/)



## [BinaryGap](https://app.codility.com/programmers/lessons/1-iterations/binary_gap/)

A *binary gap* within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.

For example, number 9 has binary representation `1001` and contains a binary gap of length 2. The number 529 has binary representation `1000010001` and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation `10100` and contains one binary gap of length 1. The number 15 has binary representation `1111` and has no binary gaps. The number 32 has binary representation `100000` and has no binary gaps.

Write a function:

> ```
> fun solution(N: Int): Int
> ```

that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.

For example, given N = 1041 the function should return 5, because N has binary representation `10000010001` and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.

Write an ***\*efficient\**** algorithm for the following assumptions:

> - N is an integer within the range [1..2,147,483,647].



## [CyclicRotation](https://app.codility.com/programmers/lessons/2-arrays/cyclic_rotation/)

An array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is moved to the first place. For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7] (elements are shifted right by one index and 6 is moved to the first place).

The goal is to rotate array A K times; that is, each element of A will be shifted to the right K times.

Write a function:

> ```
> fun solution(A: IntArray, K: Int): IntArray
> ```

that, given an array A consisting of N integers and an integer K, returns the array A rotated K times.

For example, given

```
    A = [3, 8, 9, 7, 6]    K = 3
```

the function should return [9, 7, 6, 3, 8]. Three rotations were made:

```
    [3, 8, 9, 7, 6] -> [6, 3, 8, 9, 7]    [6, 3, 8, 9, 7] -> [7, 6, 3, 8, 9]    [7, 6, 3, 8, 9] -> [9, 7, 6, 3, 8]
```

For another example, given

```
    A = [0, 0, 0]    K = 1
```

the function should return [0, 0, 0]

Given

```
    A = [1, 2, 3, 4]    K = 4
```

the function should return [1, 2, 3, 4]

Assume that:

> - N and K are integers within the range [0..100];
> - each element of array A is an integer within the range [−1,000..1,000].

In your solution, focus on ***\*correctness\****. The performance of your solution will not be the focus of the assessment.



## [OddOccurrencesInArray](https://app.codility.com/programmers/lessons/2-arrays/odd_occurrences_in_array/)

A non-empty array A consisting of N integers is given. The array contains an odd number of elements, and each element of the array can be paired with another element that has the same value, except for one element that is left unpaired.

For example, in array A such that:

```
  A[0] = 9  A[1] = 3  A[2] = 9  A[3] = 3  A[4] = 9  A[5] = 7  A[6] = 9
```

> - the elements at indexes 0 and 2 have value 9,
> - the elements at indexes 1 and 3 have value 3,
> - the elements at indexes 4 and 6 have value 9,
> - the element at index 5 has value 7 and is unpaired.

Write a function:

> ```
> fun solution(A: IntArray): Int
> ```

that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.

For example, given array A such that:

```
  A[0] = 9  A[1] = 3  A[2] = 9  A[3] = 3  A[4] = 9  A[5] = 7  A[6] = 9
```

the function should return 7, as explained in the example above.

Write an ***\*efficient\**** algorithm for the following assumptions:

> - N is an odd integer within the range [1..1,000,000];
> - each element of array A is an integer within the range [1..1,000,000,000];
> - all but one of the values in A occur an even number of times.



### 시간 복잡도 줄이는법

나는 Map으로 문제를 해결하려고 했지만, 공간 복잡도 가 O(n) 여서 2초이상 걸리는 이슈가 있었다.

GPT에게 확인한 결과 xor를 하면 공간복잡도를 줄일 수 있다고 하였다.

```kotlin
fun solution(A: IntArray): Int {
    var unpaired = 0

    for (number in A) {
        unpaired = unpaired xor number
    }

    return unpaired
}
```

### 설명:

- `xor` 연산을 사용하여 배열의 모든 요소를 하나씩 XOR합니다.
- 짝을 이루는 모든 요소는 XOR 연산 후 0이 됩니다.
- 결국 짝을 이루지 않는 요소 하나만 남게 되며, 그것이 곧 정답입니다.



### XOR 연산의 중요한 특성

1. **자기 자신과의 XOR**:
   - `a ^ a = 0`
   - 동일한 값끼리 XOR 연산을 하면 0이 됩니다.
2. **0과의 XOR**:
   - `a ^ 0 = a`
   - 0과 어떤 숫자를 XOR하면 그 숫자 자체가 나옵니다.
3. **교환 법칙**:
   - XOR 연산은 교환 법칙이 성립합니다. 즉, 순서에 상관없이 같은 결과를 얻습니다.
   - `a ^ b ^ a = b ^ (a ^ a) = b ^ 0 = b`

### XOR을 이용한 문제 해결 방법

이 문제에서는 배열에 있는 모든 숫자를 한 번씩 XOR 연산합니다. 짝이 맞는 숫자는 XOR 연산으로 0이 되고, 짝이 없는 숫자만 남게 됩니다.

예를 들어, 배열 `A = [9, 3, 9, 3, 9, 7, 9]`가 있을 때 XOR 연산을 순서대로 적용해 봅시다:

1. `0 ^ 9 = 9`
2. `9 ^ 3 = 10`
3. `10 ^ 9 = 3`
4. `3 ^ 3 = 0`
5. `0 ^ 9 = 9`
6. `9 ^ 7 = 14`
7. `14 ^ 9 = 7`

결국 남는 값은 7이며, 이는 배열에서 유일하게 짝이 없는 숫자입니다.

### `Map`과 XOR의 차이점

- Map 방식

  :

  - 각 요소를 키로 저장하고, 해당 키가 이미 존재하면 삭제하는 방식입니다. 결국 남아있는 키가 짝이 없는 요소입니다.
  - 이 방식은 O(N)의 시간 복잡도와 O(N)의 공간 복잡도를 가집니다.

- XOR 방식

  :

  - 각 요소를 순서대로 XOR 연산합니다. 짝이 있는 요소는 XOR 연산으로 인해 사라지고, 유일하게 남은 값이 짝이 없는 요소입니다.
  - 이 방식은 O(N)의 시간 복잡도와 O(1)의 공간 복잡도를 가집니다.

### XOR 연산의 비트 연산

우선, 9와 3을 이진수로 표현해봅시다:

- 9 (십진수) = `1001` (이진수)
- 3 (십진수) = `0011` (이진수)

이제, 각 비트를 XOR 연산해보겠습니다:

```
  1001  (9 in binary)
^ 0011  (3 in binary)
---------
  1010  (result in binary, which is 10 in decimal)
```

### XOR 연산 단계:

- 첫 번째 비트: `1 ^ 0 = 1`
- 두 번째 비트: `0 ^ 0 = 0`
- 세 번째 비트: `0 ^ 1 = 1`
- 네 번째 비트: `1 ^ 1 = 0`

### 요약

XOR 방식은 데이터를 저장하지 않고 연산의 특성을 이용해 문제를 해결합니다. 따라서 공간 복잡도를 크게 줄이고 효율적으로 문제를 해결할 수 있습니다. 이 접근법은 매우 유용하고, 특히 짝이 맞지 않는 요소를 찾는 문제에서 자주 사용됩니다.



## [FrogJmp](https://app.codility.com/programmers/lessons/3-time_complexity/frog_jmp/)

A small frog wants to get to the other side of the road. The frog is currently located at position X and wants to get to a position greater than or equal to Y. The small frog always jumps a fixed distance, D.

Count the minimal number of jumps that the small frog must perform to reach its target.

Write a function:

> ```
> fun solution(X: Int, Y: Int, D: Int): Int
> ```

that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or greater than Y.

For example, given:

```
  X = 10  Y = 85  D = 30
```

the function should return 3, because the frog will be positioned as follows:

> - after the first jump, at position 10 + 30 = 40
> - after the second jump, at position 10 + 30 + 30 = 70
> - after the third jump, at position 10 + 30 + 30 + 30 = 100

Write an ***\*efficient\**** algorithm for the following assumptions:

> - X, Y and D are integers within the range [1..1,000,000,000];
> - X ≤ Y.



## [PermMissingElem](https://app.codility.com/programmers/lessons/3-time_complexity/perm_missing_elem/)

An array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.

Your goal is to find that missing element.

Write a function:

> ```
> fun solution(A: IntArray): Int
> ```

that, given an array A, returns the value of the missing element.

For example, given array A such that:

```
  A[0] = 2  A[1] = 3  A[2] = 1  A[3] = 5
```

the function should return 4, as it is the missing element.

Write an ***\*efficient\**** algorithm for the following assumptions:

> - N is an integer within the range [0..100,000];
> - the elements of A are all distinct;
> - each element of array A is an integer within the range [1..(N + 1)].



## [TapeEquilibrium](https://app.codility.com/programmers/lessons/3-time_complexity/tape_equilibrium/)

A non-empty array A consisting of N integers is given. Array A represents numbers on a tape.

Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].

The *difference* between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|

In other words, it is the absolute difference between the sum of the first part and the sum of the second part.

For example, consider array A such that:

```
  A[0] = 3  A[1] = 1  A[2] = 2  A[3] = 4  A[4] = 3
```

We can split this tape in four places:

> - P = 1, difference = |3 − 10| = 7
> - P = 2, difference = |4 − 9| = 5
> - P = 3, difference = |6 − 7| = 1
> - P = 4, difference = |10 − 3| = 7

Write a function:

> ```
> class Solution { public int solution(int[] A); }
> ```

that, given a non-empty array A of N integers, returns the minimal difference that can be achieved.

For example, given:

```
  A[0] = 3  A[1] = 1  A[2] = 2  A[3] = 4  A[4] = 3
```

the function should return 1, as explained above.

Write an ***\*efficient\**** algorithm for the following assumptions:

> - N is an integer within the range [2..100,000];
> - each element of array A is an integer within the range [−1,000..1,000].



## [FrogRiverOne](https://app.codility.com/programmers/lessons/4-counting_elements/frog_river_one/)

A small frog wants to get to the other side of a river. The frog is initially located on one bank of the river (position 0) and wants to get to the opposite bank (position X+1). Leaves fall from a tree onto the surface of the river.

You are given an array A consisting of N integers representing the falling leaves. A[K] represents the position where one leaf falls at time K, measured in seconds.

The goal is to find the earliest time when the frog can jump to the other side of the river. The frog can cross only when leaves appear at every position across the river from 1 to X (that is, we want to find the earliest moment when all the positions from 1 to X are covered by leaves). You may assume that the speed of the current in the river is negligibly small, i.e. the leaves do not change their positions once they fall in the river.

For example, you are given integer X = 5 and array A such that:

```
  A[0] = 1  A[1] = 3  A[2] = 1  A[3] = 4  A[4] = 2  A[5] = 3  A[6] = 5  A[7] = 4
```

In second 6, a leaf falls into position 5. This is the earliest time when leaves appear in every position across the river.

Write a function:

> ```
> fun solution(X: Int, A: IntArray): Int
> ```

that, given a non-empty array A consisting of N integers and integer X, returns the earliest time when the frog can jump to the other side of the river.

If the frog is never able to jump to the other side of the river, the function should return −1.

For example, given X = 5 and array A such that:

```
  A[0] = 1  A[1] = 3  A[2] = 1  A[3] = 4  A[4] = 2  A[5] = 3  A[6] = 5  A[7] = 4
```

the function should return 6, as explained above.

Write an ***\*efficient\**** algorithm for the following assumptions:

> - N and X are integers within the range [1..100,000];
> - each element of array A is an integer within the range [1..X].



## [PermCheck](https://app.codility.com/programmers/lessons/4-counting_elements/perm_check/)

A non-empty array A consisting of N integers is given.

A *permutation* is a sequence containing each element from 1 to N once, and only once.

For example, array A such that:

```
    A[0] = 4    A[1] = 1    A[2] = 3    A[3] = 2
```

is a permutation, but array A such that:

```
    A[0] = 4    A[1] = 1    A[2] = 3
```

is not a permutation, because value 2 is missing.

The goal is to check whether array A is a permutation.

Write a function:

> ```
> fun solution(A: IntArray): Int
> ```

that, given an array A, returns 1 if array A is a permutation and 0 if it is not.

For example, given array A such that:

```
    A[0] = 4    A[1] = 1    A[2] = 3    A[3] = 2
```

the function should return 1.

Given array A such that:

```
    A[0] = 4    A[1] = 1    A[2] = 3
```

the function should return 0.

Write an ***\*efficient\**** algorithm for the following assumptions:

> - N is an integer within the range [1..100,000];
> - each element of array A is an integer within the range [1..1,000,000,000].



### 코드 문제점

나는 Set을 사용해서 문제를 풀었지만, 아깝게 시간복잡도 이슈로 2문제를 틀리게 되었다.

해당 문제를 엄청빠르게 풀기 위해서는 BooleanArray를 사용하면 아주 빠르게 문제를 풀 수 있었다.

```
fun solution2(A: IntArray): Int {
    val n = A.size
    val seen = BooleanArray(n + 1) // 숫자를 체크할 배열 생성

    for (num in A) {
        if (num in 1..n && !seen[num]) {
            seen[num] = true
        } else {
            return 0 // 범위 밖이거나 중복이 있는 경우 0 반환
        }
    }

    return 1 // 모든 숫자가 정확히 한 번씩 등장했다면 1 반환
}
```

### 코드 설명

1. **`BooleanArray(n + 1)` 사용**: 배열의 크기가 `n + 1`인 `seen` 배열을 사용하여, 1부터 `n`까지의 숫자가 등장했는지를 체크합니다. 인덱스 `0`은 사용하지 않기 때문에 크기를 `n + 1`로 설정합니다.
2. **반복문**: `A` 배열의 각 요소를 확인하면서 해당 숫자가 1부터 `N` 사이에 있는지, 그리고 이전에 등장하지 않았는지를 확인합니다. 조건을 만족하지 않으면 즉시 `0`을 반환합니다.
3. **결과 반환**: 모든 숫자가 조건을 만족하면 `1`을 반환합니다.



### **장점 및 특징**

1. **메모리 효율성**
   - `BooleanArray`는 각 요소를 1비트로 저장하므로 메모리 사용이 매우 효율적입니다. 이는 배열이 `true`와 `false` 값만을 가질 수 있는 경우에 적합합니다.
2. **빠른 접근 및 수정**
   - 배열은 메모리의 연속 블록에 저장되므로, 인덱스를 통한 접근 및 수정이 매우 빠릅니다. O(1) 시간 복잡도로 요소를 읽거나 쓸 수 있습니다.
3. **단순한 데이터 구조**
   - `BooleanArray`는 단순한 불리언 값을 저장하므로, 복잡한 연산이나 메타데이터 관리가 필요 없습니다. 이로 인해 코드가 간결하고 이해하기 쉽습니다.
4. **기본 제공되는 메서드**
   - `BooleanArray`는 배열에 대한 기본적인 연산 메서드(`size`, `indices`, `iterator` 등)를 제공합니다. 또한, 확장 함수도 제공되므로 다양한 배열 작업을 쉽게 수행할 수 있습니다.
5. **효율적인 초기화**
   - 배열을 생성할 때 람다식을 사용하여 초기값을 설정할 수 있습니다. 예를 들어, 인덱스에 기반하여 값이 결정되도록 할 수 있습니다.
6. **기본 타입 배열의 이점**
   - `BooleanArray`는 객체 배열이 아닌 기본 타입 배열이기 때문에, `Boolean` 클래스의 박싱과 언박싱 작업이 필요 없어서 성능이 향상됩니다.

### **예제**

#### **사용 예제 1: 존재 여부 추적**

다음은 배열 `A`에 1부터 `N`까지의 모든 숫자가 있는지를 확인하는 예제입니다:

```kotlin
fun isPermutation(A: IntArray): Int {
    val n = A.size
    val seen = BooleanArray(n + 1) // 인덱스 0은 사용하지 않음

    for (num in A) {
        if (num in 1..n) {
            seen[num] = true
        } else {
            return 0 // 범위를 벗어난 값이 있을 경우
        }
    }

    for (i in 1..n) {
        if (!seen[i]) {
            return 0 // 모든 숫자가 나타나지 않은 경우
        }
    }

    return 1 // 모든 숫자가 정확히 한 번씩 등장
}

```
#### **사용 예제 2: 짝수 인덱스 값만 `true`로 설정**

다음은 짝수 인덱스의 값만 `true`로 설정하는 예제입니다:

```kotlin
val booleanArray = BooleanArray(10) { index -> index % 2 == 0 }

// 출력
for (i in booleanArray.indices) {
    println("Index $i: ${booleanArray[i]}")
}

```

이 예제에서는 짝수 인덱스는 `true`, 홀수 인덱스는 `false`로 초기화됩니다.

### **결론**

`BooleanArray`는 메모리 효율적이고 빠른 성능을 제공하며, 불리언 값을 저장하는 데 최적화된 데이터 구조입니다. 배열의 연속적 메모리 블록 덕분에 메모리 접근이 빠르고 간단한 연산을 수행할 때 유용합니다.

## [MaxCounters](https://app.codility.com/programmers/lessons/4-counting_elements/max_counters/)

You are given N counters, initially set to 0, and you have two possible operations on them:

> - *increase(X)* − counter X is increased by 1,
> - *max counter* − all counters are set to the maximum value of any counter.

A non-empty array A of M integers is given. This array represents consecutive operations:

> - if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
> - if A[K] = N + 1 then operation K is max counter.

For example, given integer N = 5 and array A such that:

```
    A[0] = 3    A[1] = 4    A[2] = 4    A[3] = 6    A[4] = 1    A[5] = 4    A[6] = 4
```

the values of the counters after each consecutive operation will be:

```
    (0, 0, 1, 0, 0)    (0, 0, 1, 1, 0)    (0, 0, 1, 2, 0)    (2, 2, 2, 2, 2)    (3, 2, 2, 2, 2)    (3, 2, 2, 3, 2)    (3, 2, 2, 4, 2)
```

The goal is to calculate the value of every counter after all operations.

Write a function:

> ```
> fun solution(N: Int, A: IntArray): IntArray
> ```

that, given an integer N and a non-empty array A consisting of M integers, returns a sequence of integers representing the values of the counters.

Result array should be returned as an array of integers.

For example, given:

```
    A[0] = 3    A[1] = 4    A[2] = 4    A[3] = 6    A[4] = 1    A[5] = 4    A[6] = 4
```

the function should return [3, 2, 2, 4, 2], as explained above.

Write an ***\*efficient\**** algorithm for the following assumptions:

> - N and M are integers within the range [1..100,000];
> - each element of array A is an integer within the range [1..`N + 1`].



### 문제 풀이

해당 문제에서 `N+1` 일때 나는 `map`을 다시 셋팅해서 이슈가 있었다.

`N+1` 일때는 `lastUpdate `값만 따로 추출 해놓고, 마지막의 한번만 셋팅 해주는게 시간 복잡도로써 이슈가 없다.

그리고 `N+1`이 아닐때는 `index`로 추출한 값이 `lastUpdate` 값이랑 같으면 해당 값으로 셋팅 후 기존 로직 수행

```kotlin
        fun solution(N: Int, A: IntArray): IntArray {
            val counter = IntArray(N)
            var maxCounter = 0
            var lastUpdate = 0

            A.forEach {
                if (N + 1 == it) {
                    lastUpdate = maxCounter
                } else {
                    val index = it - 1
                    if (counter[index] < lastUpdate) {
                        counter[index] = lastUpdate
                    }

                    counter[index]++

                    if (counter[index] > maxCounter) {
                        maxCounter = counter[index]
                    }
                }
            }

            counter.forEachIndexed { index, it ->
                if (lastUpdate > it) counter[index] = lastUpdate
            }

            return counter
        }
```

