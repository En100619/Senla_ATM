public class Card {
    private String cardNumber;
    private String pin;
    private double balance;
    private boolean isBlocked;
    private long blockedTime;
    
    public Card(String cardNumber, String pin, double balance) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.balance = balance;
        this.isBlocked = false;
        this.blockedTime = 0;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    public String getPin() {
        return pin;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public boolean isBlocked() {
        return isBlocked;
    }
    
    public void block() {
        this.isBlocked = true;
        this.blockedTime = System.currentTimeMillis();
    }
    
    public void unblock() {
        this.isBlocked = false;
        this.blockedTime = 0;
    }
    
    public long getBlockedTime() {
        return blockedTime;
    }
}