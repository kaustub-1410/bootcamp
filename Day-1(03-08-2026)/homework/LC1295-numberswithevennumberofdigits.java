public class EvenDigitNumbers {

    public static int findNumbers(int[] nums) {
        int count = 0;

        for (int num : nums) {
            int digits = 0;
            num = Math.abs(num);

            if (num == 0) {
                digits = 1;
            } else {
                while (num > 0) {
                    digits++;
                    num /= 10;
                }
            }

            if (digits % 2 == 0) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[] nums = {12, 345, 2, 6, 7896};

        System.out.println(findNumbers(nums)); // Output: 2
    }
}