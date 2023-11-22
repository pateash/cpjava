package live.ashish.cpjava.systemdesign.prettyjson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static live.ashish.cpjava.systemdesign.prettyjson.CustomJsonPrettyPrint.customPrettyPrint;

// we want to implement a class which converts an object to json with proper formatting etc.
public class Main {

    public static void main(String[] args) {
        // Create an example Java object
        ExampleObject exampleObject = new ExampleObject("John Doe", 30, "john.doe@example.com");

//        String googleGsonJson = prettyPrintJsonGson(exampleObject);
//        System.out.println("Google Version");
//        System.out.println(googleGsonJson);
        // our custom should be somewhat similar to google Gson library

        System.out.println("Our Custom Version");

        // indent is being used recursively to add indentation (spaces) before writing anything
        String customPrettyPrint = customPrettyPrint(exampleObject, 0);
        // Print the result
        System.out.println(customPrettyPrint);
    }

    private static String prettyPrintJsonGson(ExampleObject exampleObject) {
                // Initialize Gson with pretty printing
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Convert Java object to JSON and pretty-print
        String json = gson.toJson(exampleObject);
        // Print the pretty-printed JSON
        return json;
    }

}

@Data @AllArgsConstructor
class Animal{
    private String name;
}
@Data
class ExampleObject {
    private String name;
    private int age;
    private String email;
    private Animal another = new Animal("cat");
    private Map<String,Integer> map = new HashMap<>();
    private List<String> l = Arrays.asList("hello", "How are you?");

    // Constructors, getters, and setters (omitted for brevity)

    public ExampleObject(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
    }
}
