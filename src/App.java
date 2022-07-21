import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");

        // Access IMDB API (top 250 movies), by HTTP conection (GET protocol), to get the data
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_isk10nto";
        
        // Access API, by HTTP conection (GET protocol), to get the data
        String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";

        var fetchedClient = new FetchClient();
        String infoJson = fetchedClient.requestData(url);
        
        // Parse data: extract important info
        var parser = new JsonParser();

        List<Map<String, String>> contentList = parser.parse(infoJson);
        
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

            
        }
    }
}
