package src;

/**
 * Represents a document with an identifier and text content.
 */
class Document {
    int id;
    String text;

    /**
     * Constructor to create a new Document.
     *
     * @param id   The identifier of the document.
     * @param text The text content of the document.
     */
    Document(int id, String text) {
        this.id = id;
        this.text = text;
    }
}
