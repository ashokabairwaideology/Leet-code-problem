func isStraight(nums []int) bool {
	vis := map[int]bool{}
	mi, mx := 20, -1
	for _, x := range nums {
		if x == 0 {
			continue
		}
		if vis[x] {
			return false
		}
		vis[x] = true
		mi = min(mi, x)
		mx = max(mx, x)
	}
	return mx-mi <= 4
}