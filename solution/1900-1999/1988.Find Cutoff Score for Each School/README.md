---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1988.Find%20Cutoff%20Score%20for%20Each%20School/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [1988. 找出每所学校的最低分数要求 🔒](https://leetcode.cn/problems/find-cutoff-score-for-each-school)

[English Version](/solution/1900-1999/1988.Find%20Cutoff%20Score%20for%20Each%20School/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Schools</code></p>

<pre>
+-------------+------+
| Column Name | Type |
+-------------+------+
| school_id   | int  |
| capacity    | int  |
+-------------+------+
school_id 是该表具有唯一值的列。
此表包含了一些学校的容纳能力。容纳能力指的是学校能够接纳学生的最大数量。
</pre>

<p>&nbsp;</p>

<p>表：&nbsp;<code>Exam</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| score         | int  |
| student_count | int  |
+---------------+------+
score 是该表具有唯一值的列。
表中每一行表示有 student_count 名学生在考试中至少获得了 score 分。
表中的数据在逻辑上是正确的，即记录了高 score 的行相比记录了低 score 的行拥有相同或更少的 student_count。也就是说，对于表中的 i 行与 j 行，如果 score<sub>i</sub> &gt; score<sub>j，那么 </sub>student_count<sub>i</sub> &lt;= student_count<sub>j</sub>
</pre>

<p>每年，学校会公布学生申请所需的<strong>最低分数要求</strong>。学校根据所有学生的考试成绩来决定其最低分数要求。</p>

<ol>
	<li>学校希望确保即使 <strong>每</strong> 一个满足分数要求的学生都申请该学校，学校也有足够的能力接纳每一个学生。</li>
	<li>学校也希望&nbsp;<strong>尽可能多&nbsp;</strong>的学生能申请该学校。</li>
	<li>学校&nbsp;<strong>必须&nbsp;</strong>使用在&nbsp;<code>Exam</code> 表中的 score 来作为最低分数要求。</li>
</ol>

<p>编写一个解决方案，报告每所学校的&nbsp;<strong>最低分数要求</strong>。如果同时有多个 score 值满足上述要求，则选择其中&nbsp;<strong>最小的</strong>一个。如果数据不足以决定&nbsp;<strong>最低分数要求</strong>，那么输出&nbsp;<code>-1</code>。</p>

<p>返回的结果表可以按&nbsp;<strong>任意顺序&nbsp;</strong>排序。</p>

<p>结果格式如下例所示：</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入:</strong>
Schools 表:
+-----------+----------+
| school_id | capacity |
+-----------+----------+
| 11        | 151      |
| 5         | 48       |
| 9         | 9        |
| 10        | 99       |
+-----------+----------+
Exam 表:
+-------+---------------+
| score | student_count |
+-------+---------------+
| 975   | 10            |
| 966   | 60            |
| 844   | 76            |
| 749   | 76            |
| 744   | 100           |
+-------+---------------+
<strong>输出:</strong>
+-----------+-------+
| school_id | score |
+-----------+-------+
| 5         | 975   |
| 9         | -1    |
| 10        | 749   |
| 11        | 744   |
+-----------+-------+
<b>解释：</b> 
- School 5：学校的容纳能力为 48 。选择 975 作为最低分数要求，因为学校最多会收到 10 份申请，这在学校的容纳能力以内。
- School 10：学校的容纳能力为 99 。可以选择 844 或 749 作为最低分数要求，因为学校最多会收到 76 份申请，这在学校的容纳能力以内。又因为 749 是所有可选项中最小的，因此我们选择 749 。
- School 11：学校的容纳能力为 151 。选择 744 作为最低分数要求，因为学校最多会收到 100 份申请，这在学校的容纳能力以内。
- School 9：给出的数据不足以确定最低分数要求。如果选择 975 作为最低分数要求，学校可能会收到 10 份申请，然而学校的容纳能力只有 9 。我们没有关于更高分数的信息，因此我们返回 -1 。
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT school_id, MIN(IFNULL(score, -1)) AS score
FROM
    Schools AS s
    LEFT JOIN Exam AS e ON s.capacity >= e.student_count
GROUP BY school_id;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
