import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");

        // Access IMDB API (top 250 movies), by HTTP conection (GET protocol), to get the data
        String url = "https://imdb-api.com/en/API/Top250Movies/k_isk10nto";
        URI address = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(address).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        // Store data in a string 
        String body = response.body();
        // Printing on terminal
        // System.out.println(body);

        // Parse data: extract only title, image, imDbRating
        // Using regex instead of installing any lib (parser)
        // (jackson is the popular lib for json)
        var parser = new JsonParser();
        List<Map<String, String>> moviesList = parser.parse(body);
        // System.out.println(moviesList.size());

        // Show data
        var generator = new StickersGenerator();

        for (Map<String,String> movie : moviesList) {

            String urlImage = movie.get("image");

            String title = movie.get("title");
            
            InputStream inputStream = new URL(urlImage).openStream();
            
            String fileName = title + ".png";

            generator.create(inputStream, fileName); 

            System.out.println(title);
            System.out.println();

            // System.out.println(movie.get("imDbRating"));
            // System.out.println();
        }
    }
}
