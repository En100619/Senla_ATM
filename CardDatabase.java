import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CardDatabase {
    private Map<String, Card> cards = new HashMap<>();
    private static final String FILE_NAME = "cards.txt";
    
    public CardDatabase() {
        loadCards();
    }
    
    public boolean isValidCardNumber(String cardNumber) {
        return cards.containsKey(cardNumber);
    }
    
    public boolean isValidPin(String cardNumber, String pin) {
        Card card = cards.get(cardNumber);
        return card != null && card.getPin().equals(pin);
    }
    
    public double getBalance(String cardNumber) {
        Card card = cards.get(cardNumber);
        return card != null ? card.getBalance() : 0;
    }
    
    public void updateBalance(String cardNumber, double newBalance) {
        Card card = cards.get(cardNumber);
        if (card != null) {
            card.setBalance(newBalance);
            saveCards();
        }
    }
    
    public void blockCard(String cardNumber) {
        Card card = cards.get(cardNumber);
        if (card != null) {
            card.block();
            saveCards();
        }
    }
    
    public boolean isCardBlocked(String cardNumber) {
        Card card = cards.get(cardNumber);
        if (card != null && card.isBlocked()) {
            long currentTime = System.currentTimeMillis();
            if ((currentTime - card.getBlockedTime()) >= 86400000) {
                card.unblock();
                saveCards();
                return false;
            }
            return true;
        }
        return false;
    }
    
    private void loadCards() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                String cardNumber = parts[0];
                String pin = parts[1];
                double balance = Double.parseDouble(parts[2]);
                boolean isBlocked = Boolean.parseBoolean(parts[3]);
                long blockedTime = Long.parseLong(parts[4]);
                Card card = new Card(cardNumber, pin, balance);
                if (isBlocked) {
                    card.block();
                }
                cards.put(cardNumber, card);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void saveCards() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Card card : cards.values()) {
                bw.write(card.getCardNumber() + " " + card.getPin() + " " + card.getBalance() + " " +
                         card.isBlocked() + " " + card.getBlockedTime());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}