/**
 * @param {number[][]} grid
 * @return {number}
 */
var maxValue = function (grid) {
    const m = grid.length;
    const n = grid[0].length;
    const f = new Array(2).fill(0).map(() => new Array(n + 1).fill(0));
    for (let i = 1; i <= m; ++i) {
        for (let j = 1; j <= n; ++j) {
            f[i & 1][j] = Math.max(f[(i & 1) ^ 1][j], f[i & 1][j - 1]) + grid[i - 1][j - 1];
        }
    }
    return f[m & 1][n];
};
