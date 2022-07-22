import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NasaContentExtractor implements ContentExtractor {

    public List<Content> contentExtractor(String infoJson) {
        // Parse data: extract important info
        var parser = new JsonParser();

        List<Map<String, String>> attributesList = parser.parse(infoJson);

        List<Content> contents = new ArrayList<>();

        // Populate the content list
        for (Map<String, String> attributes : attributesList) {
            String title = attributes.get("title");
            String urlImage = attributes.get("url");

            var content = new Content(title, urlImage);

            contents.add(content);
        }
        return contents;
    }
    
}
