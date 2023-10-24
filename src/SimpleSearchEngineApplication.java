package src;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The main class for the Simple Search Engine application. 
 * It reads documents from a text file, creates an inverted index, 
 * and allows users to perform searches to retrieve documents with TF-IDF scores.
 */
public class SimpleSearchEngineApplication {

    public static void main(String[] args) {

        List<Document> documents = DocumentReader.readDocumentsFromFile();

        Map<String, List<Document>> invertedIndex = InvertedIndexBuilder.createInvertedIndex(documents);

        SearchEngine searchEngine = new SearchEngine(invertedIndex);

        System.out.println("Please enter your query below: ");
        Scanner input = new Scanner(System.in);
        Map<Document, Double> results = searchEngine.search(input.nextLine());

        if(!results.isEmpty()) {
            for (Map.Entry<Document, Double> result : results.entrySet()) {
                Document doc = result.getKey();
                double tfidf = result.getValue();
                System.out.println("TF-IDF Score:  " + tfidf + " --> Document: " + doc.text + " (id:" + doc.id + ")");
            }
        } else {
            System.out.println("provided search term was not found in the dataset");
        }
        
        input.close();
    }    
}
