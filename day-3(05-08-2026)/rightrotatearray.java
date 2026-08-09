import java.util.Scanner;

public class RightRotateArray {

    static void reverse(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    static void rightRotate(int[] arr, int d) {
        int n = arr.length;
        d = d % n;

        // Reverse entire array
        reverse(arr, 0, n - 1);

        // Reverse first d elements
        reverse(arr, 0, d - 1);

        // Reverse remaining elements
        reverse(arr, d, n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input size
        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        // Input elements
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Input rotations
        System.out.print("Enter number of right rotations: ");
        int d = sc.nextInt();

        rightRotate(arr, d);

        // Print rotated array
        System.out.println("Array after right rotation:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        sc.close();
    }
}