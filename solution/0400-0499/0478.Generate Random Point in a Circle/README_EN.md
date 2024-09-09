---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0478.Generate%20Random%20Point%20in%20a%20Circle/README_EN.md
tags:
    - Geometry
    - Math
    - Rejection Sampling
    - Randomized
---

<!-- problem:start -->

# [478. Generate Random Point in a Circle](https://leetcode.com/problems/generate-random-point-in-a-circle)

[中文文档](/solution/0400-0499/0478.Generate%20Random%20Point%20in%20a%20Circle/README.md)

## Description

<!-- description:start -->

<p>Given the radius and the position of the center of a circle, implement the function <code>randPoint</code> which generates a uniform random point inside the circle.</p>

<p>Implement the <code>Solution</code> class:</p>

<ul>
	<li><code>Solution(double radius, double x_center, double y_center)</code> initializes the object with the radius of the circle <code>radius</code> and the position of the center <code>(x_center, y_center)</code>.</li>
	<li><code>randPoint()</code> returns a random point inside the circle. A point on the circumference of the circle is considered to be in the circle. The answer is returned as an array <code>[x, y]</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input</strong>
[&quot;Solution&quot;, &quot;randPoint&quot;, &quot;randPoint&quot;, &quot;randPoint&quot;]
[[1.0, 0.0, 0.0], [], [], []]
<strong>Output</strong>
[null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]

<strong>Explanation</strong>
Solution solution = new Solution(1.0, 0.0, 0.0);
solution.randPoint(); // return [-0.02493, -0.38077]
solution.randPoint(); // return [0.82314, 0.38945]
solution.randPoint(); // return [0.36572, 0.17248]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;&nbsp;radius &lt;= 10<sup>8</sup></code></li>
	<li><code>-10<sup>7</sup> &lt;= x_center, y_center &lt;= 10<sup>7</sup></code></li>
	<li>At most <code>3 * 10<sup>4</sup></code> calls will be made to <code>randPoint</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def __init__(self, radius: float, x_center: float, y_center: float):
        self.radius = radius
        self.x_center = x_center
        self.y_center = y_center

    def randPoint(self) -> List[float]:
        length = math.sqrt(random.uniform(0, self.radius**2))
        degree = random.uniform(0, 1) * 2 * math.pi
        x = self.x_center + length * math.cos(degree)
        y = self.y_center + length * math.sin(degree)
        return [x, y]
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
