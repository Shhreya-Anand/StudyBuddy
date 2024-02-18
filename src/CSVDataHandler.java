import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
//
public class CSVDataHandler {
//
    private static String fileDir = "./data/";
    public static ArrayList<String[]> readCSVFile(String filePath) throws IOException {
        ArrayList<String[]> lines = new ArrayList<>();

        // Open the file using try-with-resources to ensure it's closed properly
        try (Scanner scanner = new Scanner(Files.newBufferedReader(Paths.get(filePath)))) {

            // Read each line from the CSV file
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Split the line by commas
                String[] values = line.split(",");
//                // Add the values to the list
                lines.add(values);
            }
        }
//
        return lines;
    }

//
////    public static ArrayList<StockData> readStockMarketDataForTicker(String ticker) {
////        ArrayList<StockData> stockData = new ArrayList<>();
////        try {
////            List<String[]> csvData = readCSVFile(CSVDataHandler.fileDir + ticker + ".csv");
////
////            // Print the CSV data
////            csvData = csvData.subList(1, csvData.size());
////            for (String[] line : csvData) {
////                // for (String l : line) l = l.replaceAll("\\$", "");
////                Date date = new SimpleDateFormat("MM/dd/yyyy").parse(line[0].replaceAll("\\$", ""));
////                double close = Double.parseDouble(line[1].replaceAll("\\$", ""));
////                int volume = Integer.parseInt(line[2].replaceAll("\\$", ""));
////                double open = Double.parseDouble(line[3].replaceAll("\\$", ""));
////                double high = Double.parseDouble(line[4].replaceAll("\\$", ""));
////                double low = Double.parseDouble(line[5].replaceAll("\\$", ""));
////                StockData dataOfTheStock = new StockData(date, close, volume, open, high, low);
////                stockData.add(dataOfTheStock);
////            }
////
////        } catch (Exception e) {
////            System.out.println(e);
////        }
////        return stockData;
////    }
//
    public static List<String[]> readUserDataCSV(String filePath) throws IOException {
        List<String[]> csvData = readCSVFile(CSVDataHandler.fileDir + filePath + ".csv");
        csvData = csvData.subList(1, csvData.size());
        return csvData;
    }
//
    public static void writeUserCSVFile(String filePath, User user) throws IOException {
        if (user == null) {
            return;
        }
        // Prepare the lines to write
        String csvString = user.toCSVString();
        // Write to the CSV file
        Files.write(Paths.get(CSVDataHandler.fileDir + filePath + ".csv"), Collections.singleton(csvString),
                StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
    }

    public static void updateUserCSVFile(String filePath, User user) throws IOException {
        if (user == null) {
            return;
        }
        // get all data
        List<String[]> all = CSVDataHandler.readUserDataCSV(filePath);
        int sz = all.size();

    /*    // System.out.println("LOOP WILL RUN " + sz);
        for (int i = 0; i < sz; i++) {
            if (all.get(i).length > 2 && all.get(i)[2].equalsIgnoreCase(user.getUid())) {
                String usrCSV = user.toCSVString();
                if (user.getAccountType() == User.AccountType.NOT_PORTFOLIO_MANAGER) {
                    usrCSV = ((UserCustomerEntity) user).toCSVString();
                }
                all.set(i, usrCSV.split(","));
            }
        }
        */
        // clear file and just add headers again
        Files.write(Paths.get(CSVDataHandler.fileDir + filePath + ".csv"), new byte[0]);

        String csvStringHeader = "firstName,lastName,uid,userName,passwordHashed,balance,accountState,realisedGains";

        Files.write(Paths.get(CSVDataHandler.fileDir + filePath + ".csv"), Collections.singleton(csvStringHeader),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        for (String[] row : all) {
            String csvString = String.join(",", row);
            // System.out.println("WRITING: " + csvString);
            Files.write(Paths.get(CSVDataHandler.fileDir + filePath + ".csv"), Collections.singleton(csvString),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }

    }

////    public static void writeStockListForUser(String filePath, User user) throws IOException {
////        if (user == null) {
////            return;
////        }
////        // Prepare the lines to write
////        UserCustomerEntity usr = ((UserCustomerEntity) user);
////        ArrayList<String> csvStrings = usr.toStringStocks();
////
////        List<String[]> all = CSVDataHandler.readStockListForAllUsers(filePath);
////        int sz = all.size();
////
////        ArrayList<String[]> objectsToRemove = new ArrayList<>();
////
////        // remove all old data for user
////        for (int i = 0; i < all.size(); i++) {
////            if (all.get(i).length > 0 && all.get(i)[0].equalsIgnoreCase(user.getUid())) {
////                System.out.println(all.get(i)[0]);
////                objectsToRemove.add(all.get(i));
////            }
////        }
////
////        for (String[] i : objectsToRemove) {
////            all.remove(i);
////        }
////
////        for (String[] row : all) {
////            String csvString = String.join(",", row);
////            csvStrings.add(csvString);
////        }
////
////        // clear file and just add headers again
////        Files.write(Paths.get(CSVDataHandler.fileDir + filePath + ".csv"), new byte[0]);
////
////        String csvStringHeader = "UID,Ticker,Date,Close/Last,Volume,Open,High,Low,Quantity,PricePerStock";
////        Files.write(Paths.get(CSVDataHandler.fileDir + filePath + ".csv"), Collections.singleton(csvStringHeader),
////                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
////
////        // Write to the CSV file
////        for (String csvString : csvStrings) {
////            Files.write(Paths.get(CSVDataHandler.fileDir + filePath + ".csv"), Collections.singleton(csvString),
////                    StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
////        }
////    }
//
/*     public static User getUserFromCSV(String uid) throws IOException, ParseException {
        List<String[]> all = CSVDataHandler.readUserDataCSV("customerTable");
        for (String[] row : all) {
            if (row[2].equalsIgnoreCase(uid)) {
                UserFactory customerEntityFactory = new UserCustomerEntityProvider();
                return customerEntityFactory.createUser(row[0], row[1], row[2], row[3], row[4],
                        User.AccountType.NOT_PORTFOLIO_MANAGER, Double.parseDouble(row[5]), row[6], Double.parseDouble(row[7]));
            }
        }
        return null;
    }

    public static List<String[]> readStockListForAllUsers(String filePath) throws IOException {
        List<String[]> csvData = readCSVFile(CSVDataHandler.fileDir + filePath + ".csv");
        csvData = csvData.subList(1, csvData.size());
        return csvData;
    }
*/    

////    public static Map<String, Stock> readStockListForUser(String filePath, User user)
////            throws IOException, ParseException {
////        Map<String, Stock> stocks = new HashMap<>();
////        List<String[]> csvData = readCSVFile(CSVDataHandler.fileDir + filePath + ".csv");
////
////        // Print the CSV data
////        csvData = csvData.subList(1, csvData.size());
////        for (String[] line : csvData) {
////            // for (String l : line) l = l.replaceAll("\\$", "");
////
////            String uid = line[0];
////
////            if (uid.equals(user.getUid())) {
////                String ticker = line[1];
////                Date date = new SimpleDateFormat("MM/dd/yyyy").parse(line[2].replaceAll("\\$", ""));
////                int quantity = Integer.parseInt(line[8].replaceAll("\\$", ""));
////                double pricePerStock = Double.parseDouble(line[9].replaceAll("\\$", ""));
////                Stock temp = new Stock(ticker, quantity, pricePerStock);
////                stocks.put(ticker, temp);
////            }
////        }
////
////        return stocks;
////    }
//
/*     public static Double getRealisedGainForCustomer(UserCustomerEntity user) throws IOException {
        List<String[]> csvData = CSVDataHandler.readUserDataCSV("customerTable");

        for (String[] line : csvData) {
            if (line[2].equals(user.getUid())) {
                return Double.parseDouble(line[7]);
            }
        }
        return 0.00;
    }

    public static String getFileDir() {
        return fileDir;
    }
*/
}