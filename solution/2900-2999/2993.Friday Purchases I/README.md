---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2900-2999/2993.Friday%20Purchases%20I/README.md
tags:
    - 数据库
---

<!-- problem:start -->

# [2993. 发生在周五的交易 I 🔒](https://leetcode.cn/problems/friday-purchases-i)

[English Version](/solution/2900-2999/2993.Friday%20Purchases%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>表：&nbsp;<code>Purchases</code></p>

<pre>
+---------------+------+
| Column Name   | Type |
+---------------+------+
| user_id       | int  |
| purchase_date | date |
| amount_spend  | int  |
+---------------+------+
(user_id, purchase_date, amount_spend) 是该表的主键(具有唯一值的列)。
purchase_date 的范围从 2023 年 11 月 1 日到 2023 年 11 月 30 日，并包括这两个日期。
每一行包含 user id, purchase date，和 amount spend。
</pre>

<p>编写一个解决方案，计算用户在 <strong>2023 年 11 月&nbsp;</strong>的 <strong>每个星期五</strong> 的 <strong>总花费</strong>。输出所有在&nbsp;<strong>周五&nbsp;</strong>有购买记录的周。</p>

<p>按照每月的周次序 <strong>升序</strong> 排列结果表。</p>

<p>结果格式如下示例所示。</p>

<p>&nbsp;</p>

<p><b>示例 1：</b></p>

<pre>
<b>输入：</b>
Purchases table:
+---------+---------------+--------------+
| user_id | purchase_date | amount_spend |
+---------+---------------+--------------+
| 11      | 2023-11-07    | 1126         |
| 15      | 2023-11-30    | 7473         |
| 17      | 2023-11-14    | 2414         |
| 12      | 2023-11-24    | 9692         |
| 8       | 2023-11-03    | 5117         |
| 1       | 2023-11-16    | 5241         |
| 10      | 2023-11-12    | 8266         |
| 13      | 2023-11-24    | 12000        |
+---------+---------------+--------------+
<b>输出：</b>
+---------------+---------------+--------------+
| week_of_month | purchase_date | total_amount |
+---------------+---------------+--------------+
| 1             | 2023-11-03    | 5117         |
| 4             | 2023-11-24    | 21692        |
+---------------+---------------+--------------+ 
<b>解释：</b>
- 在 2023 年 11 月的第一周，于 2023-11-03 星期五发生了总额为 $5,117 的交易。
- 对于 2023 年 11 月的第二周，于 2023-11-10 星期五没有交易。
- 同样，在 2023 年 11 月的第三周，于 2023-11-17 星期五没有交易。
- 在 2023 年 11 月的第四周，于 2023-11-24 星期五发生了两笔交易，分别为 $12,000 和 $9,692，总计为 $21,692。
输出表按照 week_of_month 升序排列。</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：日期函数

我们用到的日期函数有：

-   `DATE_FORMAT(date, format)`：将日期格式化为字符串
-   `DAYOFWEEK(date)`：返回日期对应的星期几，1 代表星期日，2 代表星期一，以此类推
-   `DAYOFMONTH(date)`：返回日期对应的月份中的第几天

我们先用 `DATE_FORMAT` 函数将日期格式化为 `YYYYMM` 的形式，然后筛选出 2023 年 11 月且是星期五的记录，然后将记录按照 `purchase_date` 分组，计算出每个星期五的总消费金额。

<!-- tabs:start -->

#### MySQL

```sql
# Write your MySQL query statement below
SELECT
    CEIL(DAYOFMONTH(purchase_date) / 7) AS week_of_month,
    purchase_date,
    SUM(amount_spend) AS total_amount
FROM Purchases
WHERE DATE_FORMAT(purchase_date, '%Y%m') = '202311' AND DAYOFWEEK(purchase_date) = 6
GROUP BY 2
ORDER BY 1;
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
