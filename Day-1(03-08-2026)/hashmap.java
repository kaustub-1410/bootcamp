import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {

        HashMap<String, Integer> map = new HashMap<>();

        // Adding elements
        map.put("Apple", 10);
        map.put("Banana", 20);
        map.put("Mango", 30);

        // Accessing value
        System.out.println(map.get("Apple"));

        // Updating value
        map.put("Apple", 50);

        // Removing element
        map.remove("Banana");

        // Printing HashMap
        System.out.println(map);
    }
}