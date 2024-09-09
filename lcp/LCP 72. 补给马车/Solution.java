class Solution {
    public int[] supplyWagon(int[] supplies) {
        for (int h = (supplies.length + 1) >> 1; h > 0; --h) {
            int n = supplies.length;
            int mi = 1 << 30;
            int k = 0;
            for (int i = 0; i < n - 1; ++i) {
                int x = supplies[i] + supplies[i + 1];
                if (mi > x) {
                    mi = x;
                    k = i;
                }
            }
            int[] t = new int[n - 1];
            for (int i = 0, j = 0; i < n; ++i, ++j) {
                if (i == k) {
                    t[j] = mi;
                    ++i;
                } else {
                    t[j] = supplies[i];
                }
            }
            supplies = t;
        }
        return supplies;
    }
}