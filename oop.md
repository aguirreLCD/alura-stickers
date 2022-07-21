Exemplo de Polimorfismo:

inputStream é uma classe Abstrata que representa um stream de bites;

This abstract class is the superclass of all classes representing an input stream of bytes.

Applications that need to define a subclass of InputStream must always provide a method that returns the next byte of input.

o inputStream pode ser "tratado" de várias formas: independente de ser um file, uma socket, um array....
várias formas de se referenciar ao mesmo objeto;

desacoplamento;

mecanismos de polimorfismo;

// read image from static dir
        // InputStream inputStream = new FileInputStream(new File("input/movie1.jpg"));
        
// read image from url
        InputStream inputStream = new URL("https://m.media-amazon.com/images/M/MV5BMDFkYTc0MGEtZmNhMC00ZDIzLWFmNTEtODM1ZmRlYWMwMWFmXkEyXkFqcGdeQXVyMTMxODk2OTU@.jpg")
        .openStream();
        