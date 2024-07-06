import java.util.HashMap;
import java.util.Map;

public class ATM {
    private static final double ATM_BALANCE_LIMIT = 1000000;
    private CardDatabase cardDatabase = new CardDatabase();
    private Map<String, Integer> failedAttempts = new HashMap<>();
    
    public boolean authenticate(String cardNumber, String pin) {
        if (!cardDatabase.isValidCardNumber(cardNumber)) {
            System.out.println("---------------------");
            System.out.println("Неверный номер карты.");
            return false;
        }
        
        if (isCardBlocked(cardNumber)) {
            System.out.println("--------------------------------------");
            System.out.println("Карта заблокирована. Попробуйте позже.");
            return false;
        }
        
        if (!cardDatabase.isValidPin(cardNumber, pin)) {
            registerFailedAttempt(cardNumber);
            System.out.println("-----------------");
            System.out.println("Неверный ПИН-код.");
            return false;
        }
        
        resetFailedAttempts(cardNumber);
        return true;
    }
    
    public void checkBalance(String cardNumber) {
        System.out.println("---------------------");
        System.out.println("Ваш баланс: " + cardDatabase.getBalance(cardNumber));
    }
    
    public void withdraw(String cardNumber, double amount) {
        double currentBalance = cardDatabase.getBalance(cardNumber);
        if (amount > currentBalance) {
            System.out.println("Недостаточно средств на счету.");
        } else if (amount > ATM_BALANCE_LIMIT) {
            System.out.println("Превышен лимит снятия средств.");
        } else {
            cardDatabase.updateBalance(cardNumber, currentBalance - amount);
            System.out.println("------------------------------------------------------");
            System.out.println("Снятие успешно. Ваш новый баланс: " + cardDatabase.getBalance(cardNumber));
        }
    }
    
    public void deposit(String cardNumber, double amount) {
        if (amount > ATM_BALANCE_LIMIT) {
            System.out.println("--------------------------");
            System.out.println("Превышен лимит пополнения.");
        } else {
            double currentBalance = cardDatabase.getBalance(cardNumber);
            cardDatabase.updateBalance(cardNumber, currentBalance + amount);
            System.out.println("----------------------------------------------------------");
            System.out.println("Пополнение успешно. Ваш новый баланс: " + cardDatabase.getBalance(cardNumber));
        }
    }
    
    private void registerFailedAttempt(String cardNumber) {
        int attempts = failedAttempts.getOrDefault(cardNumber, 0);
        attempts++;
        failedAttempts.put(cardNumber, attempts);
        if (attempts >= 3) {
            cardDatabase.blockCard(cardNumber);
        }
    }
    
    private void resetFailedAttempts(String cardNumber) {
        failedAttempts.remove(cardNumber);
    }
    
    private boolean isCardBlocked(String cardNumber) {
        return cardDatabase.isCardBlocked(cardNumber);
    }
}