import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.util.Properties;

public class QuizQuestionGenerator {

    public static void main(String[] args) {
        // Set up pipeline properties
        Properties props = new Properties();
        // Set the list of annotators to run
        props.setProperty("annotators", "tokenize");
        // Example of how to customize the PTBTokenizer
        props.setProperty("tokenize.options", "splitHyphenated=false,americanize=false");

        // Initialize a variable to hold the text content
        String text = "";

        // Read the content from the file
        try {
            text = new String(Files.readAllBytes(Paths.get("./data/python.txt")));
        } catch (IOException e) {
            e.printStackTrace();
            return; // Exit if there's an error reading the file
        }

        // Build pipeline
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Create a document object
        CoreDocument doc = new CoreDocument(text);

        // Annotate
        pipeline.annotate(doc);

        // Prepare to write tokens to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./data/tokens.txt"))) {
            // Write tokens to file
            for (CoreLabel tok : doc.tokens()) {
                writer.write(String.format("%s\t%d\t%d\n", tok.word(), tok.beginPosition(), tok.endPosition()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
