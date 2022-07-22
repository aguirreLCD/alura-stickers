import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello, World!");

        // Access IMDB API (top 250 movies), by HTTP conection (GET protocol), to get the data
        String url = "https://imdb-api.com/en/API/Top250Movies/k_isk10nto";
        
        // Access NASA API, by HTTP conection (GET protocol), to get the data
        // String url = "https://api.mocki.io/v2/549a5d8b/NASA-APOD";

        var fetchedClient = new FetchClient();
        String infoJson = fetchedClient.requestData(url);
        
        // Show data NASA
        // NasaContentExtractor extractor = new NasaContentExtractor();
        // List<Content> contents = extractor.ContentExtractor(infoJson);

        // Show data IMDB
        ImdbContentExtractor extractor = new ImdbContentExtractor();
        List<Content> contents = extractor.contentExtractor(infoJson);

        var generator = new StickersGenerator();
 
        for (int i = 0; i < 5; i++) {

            Content content = contents.get(i);

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
        
            String fileName = "output/" + content.getTitle() + ".png";

            generator.create(inputStream, fileName); 

            System.out.println(content.getTitle());
            System.out.println();
        }
    }
}
