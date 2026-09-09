class Solution {
    public long countCommas(long n) {
        long ans = 0;
        
        for (int commas = 1; ; commas++) {
            long start = pow10(3L * commas);
            
            if (start > n) break;
            
            long end;
            if (3L * (commas + 1) >= 18) {
                end = Long.MAX_VALUE;
            } else {
                end = pow10(3L * (commas + 1)) - 1;
            }
            
            long count = Math.min(n, end) - start + 1;
            ans += count * commas;
        }
        
        return ans;
    }
    
    private long pow10(long exp) {
        long res = 1;
        for (int i = 0; i < exp; i++) {
            res *= 10;
        }
        return res;
    }
}