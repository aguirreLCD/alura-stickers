public class Content {

    // final
    private final String title;
    private final String urlImage;

    // create a class; this is from the object
    public Content(String title, String urlImage) {
        this.title = title;
        this.urlImage = urlImage;
    }

    public String getTitle() {
        return title;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
