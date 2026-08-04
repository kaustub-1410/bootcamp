public class FloorCeil {

    static void findFloorCeil(int[] arr, int x) {
        int floor = -1, ceil = -1;
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] == x) {
                floor = ceil = arr[mid];
                break;
            } else if (arr[mid] < x) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                ceil = arr[mid];
                high = mid - 1;
            }
        }

        System.out.println("Floor = " + floor);
        System.out.println("Ceil = " + ceil);
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12};
        int x = 7;

        findFloorCeil(arr, x);
    }
}