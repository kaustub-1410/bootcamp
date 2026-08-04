public class CountZeros {
    public static int countZeros(int[] arr) {
        int low = 0, high = arr.length - 1;
        int firstZero = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == 0) {
                firstZero = mid;
                high = mid - 1; // search for earlier zero
            } else {
                low = mid + 1;
            }
        }

        if (firstZero == -1)
            return 0; // no zero found

        return arr.length - firstZero;
    }

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 1, 0, 0, 0};

        System.out.println("Number of zeros: " + countZeros(arr));
    }
}