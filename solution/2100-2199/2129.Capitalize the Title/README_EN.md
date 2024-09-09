---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2100-2199/2129.Capitalize%20the%20Title/README_EN.md
rating: 1274
source: Biweekly Contest 69 Q1
tags:
    - String
---

<!-- problem:start -->

# [2129. Capitalize the Title](https://leetcode.com/problems/capitalize-the-title)

[中文文档](/solution/2100-2199/2129.Capitalize%20the%20Title/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>title</code> consisting of one or more words separated by a single space, where each word consists of English letters. <strong>Capitalize</strong> the string by changing the capitalization of each word such that:</p>

<ul>
	<li>If the length of the word is <code>1</code> or <code>2</code> letters, change all letters to lowercase.</li>
	<li>Otherwise, change the first letter to uppercase and the remaining letters to lowercase.</li>
</ul>

<p>Return <em>the <strong>capitalized</strong> </em><code>title</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> title = &quot;capiTalIze tHe titLe&quot;
<strong>Output:</strong> &quot;Capitalize The Title&quot;
<strong>Explanation:</strong>
Since all the words have a length of at least 3, the first letter of each word is uppercase, and the remaining letters are lowercase.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> title = &quot;First leTTeR of EACH Word&quot;
<strong>Output:</strong> &quot;First Letter of Each Word&quot;
<strong>Explanation:</strong>
The word &quot;of&quot; has length 2, so it is all lowercase.
The remaining words have a length of at least 3, so the first letter of each remaining word is uppercase, and the remaining letters are lowercase.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> title = &quot;i lOve leetcode&quot;
<strong>Output:</strong> &quot;i Love Leetcode&quot;
<strong>Explanation:</strong>
The word &quot;i&quot; has length 1, so it is lowercase.
The remaining words have a length of at least 3, so the first letter of each remaining word is uppercase, and the remaining letters are lowercase.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= title.length &lt;= 100</code></li>
	<li><code>title</code> consists of words separated by a single space without any leading or trailing spaces.</li>
	<li>Each word consists of uppercase and lowercase English letters and is <strong>non-empty</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

Directly simulate the process. Split the string by spaces to get each word, then convert each word to the appropriate case as per the problem statement. Finally, join the words with spaces.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the string `title`.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def capitalizeTitle(self, title: str) -> str:
        words = [w.lower() if len(w) < 3 else w.capitalize() for w in title.split()]
        return " ".join(words)
```

#### Java

```java
class Solution {
    public String capitalizeTitle(String title) {
        List<String> ans = new ArrayList<>();
        for (String s : title.split(" ")) {
            if (s.length() < 3) {
                ans.add(s.toLowerCase());
            } else {
                ans.add(s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase());
            }
        }
        return String.join(" ", ans);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string capitalizeTitle(string title) {
        transform(title.begin(), title.end(), title.begin(), ::tolower);
        istringstream ss(title);
        string ans;
        while (ss >> title) {
            if (title.size() > 2) {
                title[0] = toupper(title[0]);
            }
            ans += title;
            ans += " ";
        }
        ans.pop_back();
        return ans;
    }
};
```

#### Go

```go
func capitalizeTitle(title string) string {
	title = strings.ToLower(title)
	words := strings.Split(title, " ")
	for i, s := range words {
		if len(s) > 2 {
			words[i] = strings.Title(s)
		}
	}
	return strings.Join(words, " ")
}
```

#### TypeScript

```ts
function capitalizeTitle(title: string): string {
    return title
        .split(' ')
        .map(s =>
            s.length < 3 ? s.toLowerCase() : s.slice(0, 1).toUpperCase() + s.slice(1).toLowerCase(),
        )
        .join(' ');
}
```

#### C#

```cs
public class Solution {
    public string CapitalizeTitle(string title) {
        List<string> ans = new List<string>();
        foreach (string s in title.Split(' ')) {
            if (s.Length < 3) {
                ans.Add(s.ToLower());
            } else {
                ans.Add(char.ToUpper(s[0]) + s.Substring(1).ToLower());
            }
        }
        return string.Join(" ", ans);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
