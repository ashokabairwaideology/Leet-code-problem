---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2728.Count%20Houses%20in%20a%20Circular%20Street/README_EN.md
tags:
    - Array
    - Interactive
---

<!-- problem:start -->

# [2728. Count Houses in a Circular Street 🔒](https://leetcode.com/problems/count-houses-in-a-circular-street)

[中文文档](/solution/2700-2799/2728.Count%20Houses%20in%20a%20Circular%20Street/README.md)

## Description

<!-- description:start -->

<p>You are given an object <code>street</code> of class <code>Street</code> that represents a circular street and a positive integer <code>k</code> which represents a maximum bound for the number of houses in that street (in other words, the number of houses is less than or equal to <code>k</code>). Houses&#39; doors could be open or closed initially.</p>

<p>Initially, you are standing in front of a door to a house on this street. Your task is to count the number of houses in the street.</p>

<p>The class <code>Street</code> contains the following functions which may help you:</p>

<ul>
	<li><code>void openDoor()</code>: Open the door of the house you are in front of.</li>
	<li><code>void closeDoor()</code>: Close the door of the house you are in front of.</li>
	<li><code>boolean isDoorOpen()</code>: Returns <code>true</code> if the door of the current house is open and <code>false</code> otherwise.</li>
	<li><code>void moveRight()</code>: Move to the right house.</li>
	<li><code>void moveLeft()</code>: Move to the left house.</li>
</ul>

<p>Return <code>ans</code> <em>which represents the number of houses on this street.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> street = [0,0,0,0], k = 10
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are 4 houses, and all their doors are closed. 
The number of houses is less than k, which is 10.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> street = [1,0,1,1,0], k = 5
<strong>Output:</strong> 5
<strong>Explanation:</strong> There are 5 houses, and the doors of the 1st, 3rd, and 4th house (moving in the right direction) are open, and the rest are closed.
The number of houses is equal to k, which is 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == number of houses</code></li>
	<li><code>1 &lt;= n &lt;= k &lt;= 10<sup>3</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
# Definition for a street.
# class Street:
#     def openDoor(self):
#         pass
#     def closeDoor(self):
#         pass
#     def isDoorOpen(self):
#         pass
#     def moveRight(self):
#         pass
#     def moveLeft(self):
#         pass
class Solution:
    def houseCount(self, street: Optional["Street"], k: int) -> int:
        for _ in range(k):
            street.openDoor()
            street.moveLeft()
        ans = 0
        while street.isDoorOpen():
            street.closeDoor()
            street.moveLeft()
            ans += 1
        return ans
```

#### Java

```java
/**
 * Definition for a street.
 * class Street {
 *     public Street(int[] doors);
 *     public void openDoor();
 *     public void closeDoor();
 *     public boolean isDoorOpen();
 *     public void moveRight();
 *     public void moveLeft();
 * }
 */
class Solution {
    public int houseCount(Street street, int k) {
        while (k-- > 0) {
            street.openDoor();
            street.moveLeft();
        }
        int ans = 0;
        while (street.isDoorOpen()) {
            ++ans;
            street.closeDoor();
            street.moveLeft();
        }
        return ans;
    }
}
```

#### C++

```cpp
/**
 * Definition for a street.
 * class Street {
 * public:
 *     Street(vector<int> doors);
 *     void openDoor();
 *     void closeDoor();
 *     bool isDoorOpen();
 *     void moveRight();
 *     void moveLeft();
 * };
 */
class Solution {
public:
    int houseCount(Street* street, int k) {
        while (k--) {
            street->openDoor();
            street->moveLeft();
        }
        int ans = 0;
        while (street->isDoorOpen()) {
            ans++;
            street->closeDoor();
            street->moveLeft();
        }
        return ans;
    }
};
```

#### Go

```go
/**
 * Definition for a street.
 * type Street interface {
 *     OpenDoor()
 *     CloseDoor()
 *     IsDoorOpen() bool
 *     MoveRight()
 *     MoveLeft()
 * }
 */
func houseCount(street Street, k int) (ans int) {
	for ; k > 0; k-- {
		street.OpenDoor()
		street.MoveLeft()
	}
	for ; street.IsDoorOpen(); street.MoveLeft() {
		ans++
		street.CloseDoor()
	}
	return
}
```

#### TypeScript

```ts
/**
 * Definition for a street.
 * class Street {
 *     constructor(doors: number[]);
 *     public openDoor(): void;
 *     public closeDoor(): void;
 *     public isDoorOpen(): boolean;
 *     public moveRight(): void;
 *     public moveLeft(): void;
 * }
 */
function houseCount(street: Street | null, k: number): number {
    while (k-- > 0) {
        street.openDoor();
        street.moveLeft();
    }
    let ans = 0;
    while (street.isDoorOpen()) {
        ++ans;
        street.closeDoor();
        street.moveLeft();
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
