package src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The DocumentReader class is responsible for reading documents from a text file
 * and creating Document objects from the file content.
 * It provides a method to read and parse documents into a list of Document instances.
 */
public class DocumentReader {
    // File location for reading documents
    private static final String FILE_LOCATION = "text_dataset.txt";

    /**
    * Read and parse documents from a text file.
    *
    * @return A list of Document objects, each representing a document from the file.
    */
    public static List<Document> readDocumentsFromFile(){
        BufferedReader bufferedReader = null;
        List<Document> documents = new ArrayList<>();
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_LOCATION));
            String line = bufferedReader.readLine();
            int docId = 1;
        
            while(line != null){
                // Create a Document object with a unique identifier and the content from the file
                Document doc = new Document(docId++, line);
                documents.add(doc);
                line = bufferedReader.readLine();
            }

            bufferedReader.close();
            
        } catch (FileNotFoundException e) {
            // Handle the case where the file does not exist.
            System.err.println("Error: The file '" + FILE_LOCATION + "' was not found.");
        } catch (IOException e) {
            // Handle any other potential IOException during file reading.
            System.err.println("Error: An error occurred while reading the file.");
            e.printStackTrace();
        } 

        return documents;
    }
}
