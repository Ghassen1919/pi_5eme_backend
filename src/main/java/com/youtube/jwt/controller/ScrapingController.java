package com.youtube.jwt.controller;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ScrapingController {
    private static final String OPEN_EXCHANGE_API_KEY = "795e92d51e52cbd67b79eb24";

    public static ResponseEntity<BigDecimal> convertUSDToTND(double amount) {
        // Set up the API endpoint
        String apiEndpoint = "https://open.er-api.com/v6/latest/USD";

        // Set up the request headers and parameters
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");

        // Make the API request
        ResponseEntity<Map> responseEntity = new RestTemplate().getForEntity(apiEndpoint, Map.class, headers);

        // Check if the request was successful
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Get the exchange rates from the response
            Map<String, Object> responseData = responseEntity.getBody();
            Map<String, Double> rates = (Map<String, Double>) responseData.get("rates");

            // Get the exchange rate for TND
            Double exchangeRateTND = rates.get("TND");

            // Convert the amount to TND
            double amountInTND1 = amount * exchangeRateTND;
            BigDecimal amountInTND = new BigDecimal(amountInTND1);
            // Return the converted amount
            return ResponseEntity.ok(amountInTND);
        } else {
            // Handle API error (e.g., log, return error response)
            return ResponseEntity.status(responseEntity.getStatusCode()).build();
        }
    }



    @GetMapping("/table-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/bourse/matieres-premieres/").get();

            // Find the unique <h2> element that precedes the desired table
            Element uniqueH2 = document.selectFirst(".card-title:containsOwn(Principales Matières Premières)");

            // Check if the <h2> element is found
            if (uniqueH2 != null) {
                // Navigate to the parent container of the <h2> element and find the associated table
                Element table = uniqueH2.parent().nextElementSibling().selectFirst("table[class=table table--small table--bordered table--responsive]");

                // Initialize a list to store the scraped data
                List<Map<String, Object>> tableData = new ArrayList<>();

                // Check if the table is found
                if (table != null) {
                    // Select the table rows
                    Elements rows = table.select("tbody tr");

                    // Iterate through the rows and extract data
                    for (Element row : rows) {
                        Elements columns = row.select("td");

                        // Extract data from each column
                        String column1 = columns.get(0).text();
                        String column2 = columns.get(1).text();
                        String column3 = columns.get(2).text();
                        String column4 = columns.get(3).text();
                        String column5 = columns.get(4).text();
                        String column6 = columns.get(5).text();
                        String column7 = columns.get(6).text();

                        // Create a map to represent a row of data
                        Map<String, Object> rowData = new HashMap<>();
                        rowData.put("column1", column1);
                        rowData.put("column2", column2);
                        rowData.put("column3", column3);
                        rowData.put("column4", column4);
                        rowData.put("column5", column5);
                        rowData.put("column6", column6);
                        rowData.put("column7", column7);






                        // Add more columns as needed

                        // Add the row data to the list
                        tableData.add(rowData);
                    }
                }

                // Return the scraped data in the response

                return ResponseEntity.ok(tableData);

            } else {
                // Handle case where <h2> element is not found
                return ResponseEntity.status(404).build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }

    }
    @GetMapping("/table1-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData1() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/bourse/matieres-premieres/").get();

            // Find the unique <h2> element that precedes the desired table
            Element uniqueH2 = document.selectFirst(".card-title:containsOwn(Toutes les Matières Premières)");

            // Check if the <h2> element is found
            if (uniqueH2 != null) {
                // Navigate to the parent container of the <h2> element and find the associated table
                Element table = uniqueH2.parent().nextElementSibling().selectFirst("table[class=table table--small table--bordered table--responsive]");

                // Initialize a list to store the scraped data
                List<Map<String, Object>> tableData = new ArrayList<>();

                // Check if the table is found
                if (table != null) {
                    // Select the table rows
                    Elements rows = table.select("tbody tr");

                    // Iterate through the rows and extract data
                    for (Element row : rows) {
                        Elements columns = row.select("td");

                        // Extract data from each column
                        String column1 = columns.get(0).text();
                        String column2 = columns.get(1).text();
                        String column3 = columns.get(2).text();
                        String column4 = columns.get(3).text();
                        String column5 = columns.get(4).text();
                        String column6 = columns.get(5).text();
                        String column7 = columns.get(6).text();


                        // Add more columns as needed

                        // Create a map to represent a row of data
                        Map<String, Object> rowData = new HashMap<>();
                        rowData.put("column1", column1);
                        rowData.put("column2", column2);
                        rowData.put("column3", column3);
                        rowData.put("column4", column4);
                        rowData.put("column5", column5);
                        rowData.put("column6", column6);
                        rowData.put("column7", column7);





                        // Add more columns as needed

                        // Add the row data to the list
                        tableData.add(rowData);
                    }
                }

                // Return the scraped data in the response

                return ResponseEntity.ok(tableData);

            } else {
                // Handle case where <h2> element is not found
                return ResponseEntity.status(404).build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }

    }
    @GetMapping("/table2-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData2() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/bourse/cryptomonnaies/").get();

            // Find the unique <h2> element that precedes the desired table;

            // Check if the <h2> element is found

            // Navigate to the parent container of the <h2> element and find the associated table
            Element table = document.selectFirst("table[class=stocks_table table table--small table--bordered table--hover table--fixed table--stock table--centered]");

            // Initialize a list to store the scraped data
            List<Map<String, Object>> tableData = new ArrayList<>();

            // Check if the table is found
            if (table != null) {
                // Select the table rows
                Elements rows = table.select("tbody tr");

                // Iterate through the rows and extract data for the first 10 rows
                for (int i = 0; i < Math.min(rows.size(), 10); i++) {
                    Element row = rows.get(i);
                    Elements columns = row.select("td");

                    // Extract data from each column
                    String column0 = columns.get(0).text();
                    String column1 = columns.get(1).text();
                    String column2 = columns.get(2).text();
                    String column3 = columns.get(3).text();
                    String column4 = columns.get(4).text();
                    String column5 = columns.get(5).text();
                    String numericValue = column2.replaceAll("[^0-9.,]", "").replace(",", ".");
                    float a =Float.parseFloat(numericValue);
                    ResponseEntity<BigDecimal> convertedAmount = convertUSDToTND(a);                        // Add more columns as needed
                    String column8 = convertedAmount.getBody().setScale(4, BigDecimal.ROUND_HALF_EVEN) + " TND";

                    // Add more columns as needed

                    // Create a map to represent a row of data
                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("column0", column0);
                    rowData.put("column1", column1);
                    rowData.put("column2", column2);
                    rowData.put("column3", column3);
                    rowData.put("column4", column4);
                    rowData.put("column5", column5);
                    rowData.put("column8", column8);
                    // Add more columns as needed

                    // Add the row data to the list
                    tableData.add(rowData);
                }
            }

            // Return the scraped data in the response
            return ResponseEntity.ok(tableData);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/table3-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData3() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/bourse/actions/").get();

            // Find the unique <h2> element that precedes the desired table;

            // Check if the <h2> element is found

            // Navigate to the parent container of the <h2> element and find the associated table
            Element table = document.selectFirst("table[class=stocks_table table table--small table--bordered table--hover table--fixed table--stock table--centered]");

            // Initialize a list to store the scraped data
            List<Map<String, Object>> tableData = new ArrayList<>();

            // Check if the table is found
            if (table != null) {
                // Select the table rows
                Elements rows = table.select("tbody tr");

                // Iterate through the rows and extract data for the first 10 rows
                for (int i = 0; i < Math.min(rows.size(), 20); i++) {
                    Element row = rows.get(i);
                    Elements columns = row.select("td");

                    // Extract data from each column
                    String column0 = columns.get(0).text();
                    String column1 = columns.get(1).text();
                    String column2 = columns.get(2).text();
                    String column3 = columns.get(3).text();
                    String column4 = columns.get(4).text();
                    String column5 = columns.get(5).text();


                    // Add more columns as needed

                    // Create a map to represent a row of data
                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("column0", column0);
                    rowData.put("column1", column1);
                    rowData.put("column2", column2);
                    rowData.put("column3", column3);
                    rowData.put("column4", column4);
                    rowData.put("column5", column5);


                    // Add more columns as needed

                    // Add the row data to the list
                    tableData.add(rowData);
                }
            }

            // Return the scraped data in the response
            return ResponseEntity.ok(tableData);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/table4-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData4() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/bourse/matieres-premieres/").get();

            // Find the unique <h2> element that precedes the desired table
            Element uniqueH2 = document.selectFirst(".card-title:containsOwn(Principales Matières Premières)");

            // Check if the <h2> element is found
            if (uniqueH2 != null) {
                // Navigate to the parent container of the <h2> element and find the associated table
                Element table = uniqueH2.parent().nextElementSibling().selectFirst("table[class=table table--small table--bordered table--responsive]");

                // Initialize a list to store the scraped data
                List<Map<String, Object>> tableData = new ArrayList<>();

                // Check if the table is found
                if (table != null) {
                    // Select the table rows
                    Elements rows = table.select("tbody tr");

                    // Iterate through the rows and extract data
                    for (int i = 0; i < Math.min(rows.size(), 2); i++) {
                        Element row = rows.get(i);
                        Elements columns = row.select("td");

                        // Extract data from each column
                        String column1 = columns.get(0).text();
                        String column2 = columns.get(1).text();
                        String column3 = columns.get(2).text();
                        String column4 = columns.get(3).text();
                        String column5 = columns.get(4).text();
                        String column6 = columns.get(5).text();
                        String column7 = columns.get(6).text();
                        String numericValue = column2.replaceAll("[^0-9.,]", "").replace(",", ".");
                        float a =Float.parseFloat(numericValue);
                        ResponseEntity<BigDecimal> convertedAmount = convertUSDToTND(a);                        // Add more columns as needed
                        String column8 = convertedAmount.getBody().setScale(4, BigDecimal.ROUND_HALF_EVEN) + " TND";
                        // Create a map to represent a row of data
                        Map<String, Object> rowData = new HashMap<>();
                        rowData.put("column1", column1);
                        rowData.put("column2", column2);
                        rowData.put("column3", column3);
                        rowData.put("column4", column4);
                        rowData.put("column5", column5);
                        rowData.put("column6", column6);
                        rowData.put("column7", column7);
                        rowData.put("column8", column8);





                        // Add more columns as needed

                        // Add the row data to the list
                        tableData.add(rowData);
                    }
                }

                // Return the scraped data in the response

                return ResponseEntity.ok(tableData);

            } else {
                // Handle case where <h2> element is not found
                return ResponseEntity.status(404).build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }

    }
    @GetMapping("/table5-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData5() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/bourse/cryptomonnaies/").get();

            // Find the unique <h2> element that precedes the desired table;

            // Check if the <h2> element is found

            // Navigate to the parent container of the <h2> element and find the associated table
            Element table = document.selectFirst("table[class=stocks_table table table--small table--bordered table--hover table--fixed table--stock table--centered]");

            // Initialize a list to store the scraped data
            List<Map<String, Object>> tableData = new ArrayList<>();

            // Check if the table is found
            if (table != null) {
                // Select the table rows
                Elements rows = table.select("tbody tr");

                // Iterate through the rows and extract data for the first 10 rows
                for (int i = 0; i < Math.min(rows.size(), 2); i++) {
                    Element row = rows.get(i);
                    Elements columns = row.select("td");

                    // Extract data from each column
                    String column0 = columns.get(0).text();
                    String column1 = columns.get(1).text();
                    String column2 = columns.get(2).text();
                    String column3 = columns.get(3).text();
                    String column4 = columns.get(4).text();
                    String column5 = columns.get(5).text();
                    String numericValue = column2.replaceAll("[^0-9.,]", "").replace(",", ".");
                    float a =Float.parseFloat(numericValue);
                    ResponseEntity<BigDecimal> convertedAmount = convertUSDToTND(a);                        // Add more columns as needed
                    String column8 = convertedAmount.getBody().setScale(4, BigDecimal.ROUND_HALF_EVEN) + " TND";

                    // Add more columns as needed

                    // Create a map to represent a row of data
                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("column0", column0);
                    rowData.put("column1", column1);
                    rowData.put("column2", column2);
                    rowData.put("column3", column3);
                    rowData.put("column4", column4);
                    rowData.put("column5", column5);
                    rowData.put("column8", column8);
                    // Add more columns as needed

                    // Add the row data to the list
                    tableData.add(rowData);
                }
            }

            // Return the scraped data in the response
            return ResponseEntity.ok(tableData);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/table6-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData6() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/bourse/actions/").get();

            // Find the unique <h2> element that precedes the desired table;

            // Check if the <h2> element is found

            // Navigate to the parent container of the <h2> element and find the associated table
            Element table = document.selectFirst("table[class=stocks_table table table--small table--bordered table--hover table--fixed table--stock table--centered]");

            // Initialize a list to store the scraped data
            List<Map<String, Object>> tableData = new ArrayList<>();

            // Check if the table is found
            if (table != null) {
                // Select the table rows
                Elements rows = table.select("tbody tr");

                // Iterate through the rows and extract data for the first 10 rows
                for (int i = 0; i < Math.min(rows.size(), 2); i++) {
                    Element row = rows.get(i);
                    Elements columns = row.select("td");

                    // Extract data from each column
                    String column0 = columns.get(0).text();
                    String column1 = columns.get(1).text();
                    String column2 = columns.get(2).text();
                    String column3 = columns.get(3).text();
                    String column4 = columns.get(4).text();
                    String column5 = columns.get(5).text();
                    String numericValue = column2.replaceAll("[^0-9.,]", "").replace(",", ".");
                    float a =Float.parseFloat(numericValue);
                    ResponseEntity<BigDecimal> convertedAmount = convertUSDToTND(a);                        // Add more columns as needed
                    String column8 = convertedAmount.getBody().setScale(4, BigDecimal.ROUND_HALF_EVEN) + " TND";

                    // Add more columns as needed

                    // Create a map to represent a row of data
                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("column0", column0);
                    rowData.put("column1", column1);
                    rowData.put("column2", column2);
                    rowData.put("column3", column3);
                    rowData.put("column4", column4);
                    rowData.put("column5", column5);
                    rowData.put("column8", column8);

                    // Add more columns as needed

                    // Add the row data to the list
                    tableData.add(rowData);
                }
            }

            // Return the scraped data in the response
            return ResponseEntity.ok(tableData);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/table7-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData7() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/actualite-bourse/matieres-premieres/").get();

            // Find the unique <h2> element that precedes the desired table;

            // Check if the <h2> element is found

            // Navigate to the parent container of the <h2> element and find the associated table
            Element table = document.selectFirst("table[class=table table--small table--bordered table--fixed table--hover]");

            // Initialize a list to store the scraped data
            List<Map<String, Object>> tableData = new ArrayList<>();

            // Check if the table is found
            if (table != null) {
                // Select the table rows
                Elements rows = table.select("tbody tr");

                // Iterate through the rows and extract data for the first 10 rows
                for (int i = 0; i < Math.min(rows.size(), 45); i++) {
                    Element row = rows.get(i);
                    Elements columns = row.select("td");

                    // Extract data from each column
                    String column0 = columns.get(0).text();
                    String column1 = columns.get(1).text();
                    String column2 = columns.get(2).text();

                    // Add more columns as needed

                    // Create a map to represent a row of data
                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("column0", column0);
                    rowData.put("column1", column1);
                    rowData.put("column2", column2);

                    // Add more columns as needed

                    // Add the row data to the list
                    tableData.add(rowData);
                }
            }

            // Return the scraped data in the response
            return ResponseEntity.ok(tableData);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/table8-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData8() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/actualite-bourse/cryptomonnaies/").get();

            // Find the unique <h2> element that precedes the desired table;

            // Check if the <h2> element is found

            // Navigate to the parent container of the <h2> element and find the associated table
            Element table = document.selectFirst("table[class=table table--small table--bordered table--fixed table--hover]");

            // Initialize a list to store the scraped data
            List<Map<String, Object>> tableData = new ArrayList<>();

            // Check if the table is found
            if (table != null) {
                // Select the table rows
                Elements rows = table.select("tbody tr");

                // Iterate through the rows and extract data for the first 10 rows
                for (int i = 0; i < Math.min(rows.size(), 25); i++) {
                    Element row = rows.get(i);
                    Elements columns = row.select("td");

                    // Extract data from each column
                    String column0 = columns.get(0).text();
                    String column1 = columns.get(1).text();
                    String column2 = columns.get(2).text();

                    // Add more columns as needed

                    // Create a map to represent a row of data
                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("column0", column0);
                    rowData.put("column1", column1);
                    rowData.put("column2", column2);

                    // Add more columns as needed

                    // Add the row data to the list
                    tableData.add(rowData);
                }
            }

            // Return the scraped data in the response
            return ResponseEntity.ok(tableData);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }
    }
    @GetMapping("/table9-data")
    public ResponseEntity<List<Map<String, Object>>> getTableData9() {
        try {
            // Connect to the website and get the HTML document
            Document document = Jsoup.connect("https://www.zonebourse.com/actualite-bourse/societes/").get();

            // Find the unique <h2> element that precedes the desired table;

            // Check if the <h2> element is found

            // Navigate to the parent container of the <h2> element and find the associated table
            Element table = document.selectFirst("table[class=table table--small table--bordered table--fixed table--hover]");

            // Initialize a list to store the scraped data
            List<Map<String, Object>> tableData = new ArrayList<>();

            // Check if the table is found
            if (table != null) {
                // Select the table rows
                Elements rows = table.select("tbody tr");

                // Iterate through the rows and extract data for the first 10 rows
                for (int i = 0; i < Math.min(rows.size(), 25); i++) {
                    Element row = rows.get(i);
                    Elements columns = row.select("td");

                    // Extract data from each column
                    String column0 = columns.get(0).text();
                    String column1 = columns.get(1).text();
                    String column2 = columns.get(2).text();

                    // Add more columns as needed

                    // Create a map to represent a row of data
                    Map<String, Object> rowData = new HashMap<>();
                    rowData.put("column0", column0);
                    rowData.put("column1", column1);
                    rowData.put("column2", column2);

                    // Add more columns as needed

                    // Add the row data to the list
                    tableData.add(rowData);
                }
            }

            // Return the scraped data in the response
            return ResponseEntity.ok(tableData);

        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions appropriately (e.g., log, return error response)
            return ResponseEntity.status(500).build();
        }
    }
}
