import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM();

        while (true) {
            System.out.println("----------------------------------------------------");
            System.out.println("Введите номер карты (16 цифр без пробелов и дефисов):");
            String cardNumber = scanner.nextLine();
            System.out.println("--------------------");
            System.out.println("Введите ПИН-код:");
            String pin = scanner.nextLine();

            if (atm.authenticate(cardNumber, pin)) {
                int choice;
                do {
                    System.out.println("--------------------");
                    System.out.println("Меню:");
                    System.out.println("1. Проверить баланс");
                    System.out.println("2. Снять средства");
                    System.out.println("3. Пополнить баланс");
                    System.out.println("4. Выход");
                    System.out.println("Выберите опцию:");
                    choice = scanner.nextInt();

                    switch (choice) {
                        case 1:
                            atm.checkBalance(cardNumber);
                            break;
                        case 2:
                            System.out.println("Введите сумму для снятия:");
                            double amountToWithdraw = scanner.nextDouble();
                            atm.withdraw(cardNumber, amountToWithdraw);
                            break;
                        case 3:
                            System.out.println("Введите сумму для пополнения:");
                            double amountToDeposit = scanner.nextDouble();
                            atm.deposit(cardNumber, amountToDeposit);
                            break;
                        case 4:
                            System.out.println("Выход из системы...");
                            break;
                        default:
                            System.out.println("--------------------------");
                            System.out.println("Такая опция отсутствует :)");
                    }
                } while (choice != 4);
            } else {
                System.out.println("Ошибка. Пожалуйста, проверьте номер карты и ПИН-код.");
            }
            scanner.nextLine();
        }
    }
}
