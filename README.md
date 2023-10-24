# Simple Search Engine

The Simple Search Engine is a Java-based project that allows you to create an inverted index from a list of documents and perform searches using TF-IDF (Term Frequency-Inverse Document Frequency) scoring. This project is designed to help you understand the basics of text-based searching and information retrieval.

## Features

- Create an inverted index from a list of documents.
- text_dataset file acts as the input file where each new line is then interpreted as a new document
- Perform searches for single terms in the document set.
- The TF score is calculated using the "term frequency adjusted for document length" approach 
- The IDF score is calculated using the Standard/Logarithmic formula ( https://en.wikipedia.org/wiki/Tf%E2%80%93idf )
- Retrieve a list of matching documents sorted by TF-IDF score.

## Getting Started

### Prerequisites

- Java Development Kit (JDK)
- Git (optional for version control)
- A code editor (e.g., Visual Studio Code, IntelliJ IDEA)

### Installation

1. Clone or download the project from the GitHub repository.

```bash
git clone https://github.com/your-username/simple-search-engine.git

PS. This repo was created as an assignment.
