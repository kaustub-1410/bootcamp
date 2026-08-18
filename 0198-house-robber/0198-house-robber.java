class Solution {
   public int rob(int[] nums) {
       // Think outside the box, prevMax and twoMaxAgo are OUTSIDE nums AKA they are both 0s
       int prevMax = 0;
       int twoMaxAgo = 0;

       for(int houseMoney : nums){
           // This ensures EVERY max is indeed the max at latest house it has visited
           int temp = Math.max(prevMax, houseMoney + twoMaxAgo);
           twoMaxAgo = prevMax;
           prevMax = temp;
       }

       // When we are outside the boundaries of nums prevMax is literally the max because now we are essentially adding nothing
       return prevMax;
   }
}
