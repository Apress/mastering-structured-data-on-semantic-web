/* Listing 7-35. A SeRQL Query using the Sesame Repository API */

String query = "SELECT * FROM {x} p {y}";
QueryResultsTable resultsTable = myRepository.performTableQuery(QueryLanguage.SERQL, query);

int rowCount = resultsTable.getRowCount();
int columnCount = resultsTable.getColumnCount();

for (int row = 0; row < rowCount; row++) {
    for (int column = 0; column < columnCount; column++) {
        Value value = resultsTable.getValue(row, column);

        if (value != null) {
            System.out.print(value.toString());
        }
        else {
            System.out.print("null");
        }

        System.out.print("\t");
    }

    System.out.println();
}

/* Listing 7-36. Changing Multiple Property Values using the Sesame Graph API */

myRepository.addGraph(QueryLanguage.SERQL, "CONSTRUCT {X} <http://purl.org/spar/pso/PublicationStatus> {\"published\"} " + "FROM {X} <http://purl.org/spar/pso/PublicationStatus> {\"draft\"}");

/* Listing 7-37. Removing All Triples with the Obsolete Property Value */

myRepository.removeGraph(QueryLanguage.SERQL, "CONSTRUCT * " + "FROM {X} <http://purl.org/spar/pso/PublicationStatus> {\"draft\"}";