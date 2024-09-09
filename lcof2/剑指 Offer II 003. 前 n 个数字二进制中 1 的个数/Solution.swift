class Solution {
    func countBits(_ n: Int) -> [Int] {
        if n == 0 {
            return [0]
        }
        var f = [Int](repeating: 0, count: n + 1)
        for i in 1...n {
            f[i] = f[i & (i - 1)] + 1
        }
        return f
    }
}
