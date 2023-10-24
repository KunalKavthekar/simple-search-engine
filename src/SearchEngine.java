package src;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * A search engine for performing searches on documents using TF-IDF scoring.
 */
public class SearchEngine {
    private Map<String, List<Document>> invertedIndex;

    /**
     * Constructor to create a SearchEngine with a pre-built inverted index.
     *
     * @param invertedIndex An inverted index map of terms to documents.
     */
    public SearchEngine(Map<String, List<Document>> invertedIndex) {
        this.invertedIndex = invertedIndex;
    }

    /**
     * Calculate the Inverse Document Frequency (IDF) score for a given term.
     *
     * @param term The term for which the IDF score is calculated.
     * @return The IDF score for the term, which measures the uniqueness of the term across the document collection.
     */
    private double calculateIDFScore(String term) {
        int documentFrequency = invertedIndex.get(term).size();
        int totalDocuments = invertedIndex.values().stream().mapToInt(List::size).sum();

        return Math.log((double) (totalDocuments + 1) / (documentFrequency + 1));
    }

    /**
     * Calculate the Term Frequency (TF) score for a given term in a document.
     *
     * @param term     The term for which the TF score is calculated.
     * @param document The document in which the term frequency is counted.
     * @return The TF score for the term in the document, which is the ratio of the term frequency to the total terms in the document.
     */
    private double calculateTFScore(String term, Document document) {
        String[] terms = document.text.split("\\s");
        
        long termFrequency = Arrays.stream(terms).filter(t -> t.equals(term)).count();

        return (double) termFrequency / terms.length;
    }

    /**
     * Search for documents containing the given query and return them sorted by TF-IDF score.
     *
     * @param query The query string to search for in the documents.
     * @return A documents matching the query and their TF-IDF scores, sorted by TF-IDF score.
     */
    public Map<Document, Double> search(String query) {
        String[] queryTerms = query.split("\\s");
        Map<Document, Double> documentScores = new HashMap<>();

        for (String term : queryTerms) {
            if (invertedIndex.containsKey(term)) {
                List<Document> matchingDocuments = invertedIndex.get(term);
                double idf = calculateIDFScore(term);

                for (Document doc : matchingDocuments) {
                    double tf = calculateTFScore(term, doc);
                    double tfidf = tf * idf;
                    documentScores.put(doc, documentScores.getOrDefault(doc, 0.0) + tfidf);
                }
            }
        }

        // Sort the map by TF-IDF scores in descending order
        return documentScores.entrySet().stream()
                .sorted(Map.Entry.<Document, Double>comparingByValue().reversed())
                .collect(LinkedHashMap::new, (map, entry) -> map.put(entry.getKey(), entry.getValue()), Map::putAll);
    }
}
