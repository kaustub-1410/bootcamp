import java.util.HashMap;

public class DictionaryExample {
    public static void main(String[] args) {

        // Creating a dictionary
        HashMap<String, Integer> dictionary = new HashMap<>();

        // Adding key-value pairs
        dictionary.put("Apple", 100);
        dictionary.put("Banana", 50);
        dictionary.put("Mango", 80);

        // Accessing a value
        System.out.println(dictionary.get("Apple"));

        // Updating a value
        dictionary.put("Apple", 120);

        // Removing a key
        dictionary.remove("Banana");

        // Printing all entries
        System.out.println(dictionary);

        // Checking if key exists
        System.out.println(dictionary.containsKey("Mango"));
    }
}