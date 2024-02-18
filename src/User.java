import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String firstName;
    private String lastName;
    private String uid;
    private String userName;
    private String passwordHashed;
    private AccountType accountType;

    private Double balance;

    private String approval;

//    private Map<String, Stock> stocks;

    private Double realisedGains;

    enum AccountType {
        PORTFOLIO_MANAGER,
        NOT_PORTFOLIO_MANAGER,
        DERIVATIVE
    }

    public User(
            String firstName,
            String lastName,
            String uid,
            String userName,
            String passwordHashed,
            AccountType accountType,
            Double balance,
            String approval,
            Double realisedGains
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.uid = uid;
        this.userName = userName;
        this.passwordHashed = passwordHashed;
        this.accountType = accountType;
        this.balance = balance;
//        this.stocks = new HashMap<>();
        this.approval = approval;
        this.realisedGains = realisedGains;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHashed() {
        return passwordHashed;
    }

    public void setPasswordHashed(String passwordHashed) {
        this.passwordHashed = passwordHashed;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String toCSVString() {
        return String.join(",", firstName, lastName, uid, userName, passwordHashed, balance.toString(), approval, realisedGains.toString());
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
//
//    public Map<String, Stock> getStocks() {
//        return stocks;
//    }
//
//    public void setStocks(Map<String, Stock> stocks) {
//        this.stocks = stocks;
//    }

    public void persistUser() throws IOException {
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    public Double getRealisedGains() {
        return realisedGains;
    }

    public void setRealisedGains(Double realisedGains) {
        this.realisedGains = realisedGains;
    }

    public String toString() {
        StringBuilder userStocks = new StringBuilder();
        userStocks.append("<html>Ticker -- # Shares -- Price<br/>-------------------------------------------<br/>");
//        for (String t : this.stocks.keySet()) {
//            userStocks.append(t + " -- " + this.stocks.get(t).getQuantity() + " shares | $" + this.stocks.get(t).getPricePerStock()).append("<br/>");
//        }
        userStocks.append("</html>");
        System.out.println(userStocks.toString());
        return userStocks.toString();
    }

}
