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
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_isk10nto";
        
        String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";
        
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

        List<Map<String, String>> contentList = parser.parse(body);
        
        // System.out.println(contentList.size());

        // Show data
        var generator = new StickersGenerator();
 
        for (int i = 0; i < 10; i++) {
        
            Map<String,String> content = contentList.get(i);

            String urlImage = content.get("url").replaceAll("(@+)(.*).jpg$", "$1.jpg");

            String title = content.get("title");
            
            String fileName = title + ".png";
            
            InputStream inputStream = new URL(urlImage).openStream();

            generator.create(inputStream, fileName); 

            System.out.println(title);
            System.out.println();

            System.out.println(content.get("imDbRating"));
            System.out.println();
        }
    }
}
