import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Task {
    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();

        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/file.txt"))) {

            stream.forEach(line -> {

                String[] words = line.split(" ");

                for (String s :
                        words) {
                    Integer i  = map.get(s);
                    map.put(s, (i == null) ? 1 : i + 1);
                }
            });

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(System.out::println);

            String s = null;
            int p = 0;
            for (String w : map.keySet()) {
                if (p < map.get(w)) {
                    p = map.get(w);
                    s = w;
                }
            }
            System.out.println("\nСамое повторяющееся слово: \"" + s + "\", встречается " + map.get(s) + " раз");




        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
