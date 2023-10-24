package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class for building an inverted index from a list of documents.
 */
public class InvertedIndexBuilder {
    
    /**
    * Cleanse and remove specified characters and symbols from a string.
    *
    * @param input The input string to be cleansed.
    * @return A new string with the specified characters and symbols removed.
    */
    private static String cleanseTerm(String input)
    {
        String newStr = input.replaceAll("[, . : ;\"]", "");
        newStr = newStr.replaceAll("\\p{P}","");
        newStr = newStr.replaceAll("\t","");
        return newStr;
    }

    /**
     * Check if a string contains at least one digit character.
     *
     * @param input The input string to check for the presence of digits.
     * @return true if the input string contains at least one digit character, false otherwise.
     */
    private static boolean isDigit(String input)
    {
        String regex = "(.)*(\\d)(.)*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        boolean isMatched = matcher.matches();
        return isMatched;
    }

    /**
     * Build an inverted index from a list of documents.
     *
     * @param documents A list of documents to build the inverted index from.
     * @return An inverted index represented as a map of terms to documents.
     */
    public static Map<String,List<Document>> createInvertedIndex(List<Document> documents) {
        Map<String, List<Document>> invertedIndex = new HashMap<>(); 
        
        for(Document doc : documents){
                
            String[] terms = doc.text.split("\\s");
            Set<String> uniqueTerms = new HashSet<>(Arrays.asList(terms));

            for (String term : uniqueTerms) {
                term = cleanseTerm(term);
                if(isDigit(term))
                    continue;
                
                if(term.length() == 0)
                    continue;

                invertedIndex.computeIfAbsent(term, k -> new ArrayList<>()).add(doc);
            }
        }
        return invertedIndex;
    }
}
