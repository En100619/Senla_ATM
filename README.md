# Senla ATM
Техническое задание SENLA

![ATM_1](https://github.com/En100619/Senla_ATM/assets/108071851/21a34d12-82e8-41ae-ae5d-7d971bdde3b0)

**Клонировать репозиторий**
```
git clone https://github.com/En100619/Senla_ATM.git
```

### Основные функции программы

1. **Авторизация пользователя:**
   - Пользователь вводит номер карты и ПИН-код.
   - Если номер карты не соответствует формату или ПИН-код неверный, пользователь получает сообщение об ошибке.
   - Если карта заблокирована (после трех неверных попыток ввода ПИН-кода), пользователь не сможет войти до истечения суток с момента блокировки.

2. **Проверка баланса:**
   - После успешной авторизации пользователь может проверить баланс своей карты.

3. **Снятие средств:**
   - Пользователь может снять средства со счета, если сумма на счете достаточна и не превышает лимит средств в банкомате.
   - Если на счете недостаточно средств или сумма превышает лимит, пользователь получает соответствующее сообщение.

4. **Пополнение баланса:**
   - Пользователь может пополнить баланс карты, если сумма пополнения не превышает 1 000 000.

### Основные классы

1. **Main.java:**
   - Основной класс для запуска программы. Он управляет консольным меню и взаимодействием с пользователем.

2. **ATM.java:**
   - Класс, представляющий банкомат. Содержит методы для авторизации пользователя, проверки баланса, снятия и пополнения средств.
   - Управляет блокировкой карты в случае трех неверных попыток ввода ПИН-кода.

3. **Card.java:**
   - Класс, представляющий банковскую карту. Содержит информацию о номере карты, ПИН-коде, балансе и статусе блокировки.

4. **CardDatabase.java:**
   - Класс для управления базой данных карт. Считывает данные из файла, проверяет корректность ввода, сохраняет изменения в файл.
   - Обрабатывает блокировку карт и проверяет истечение времени блокировки.

5. **Util.java:**
   - Утилитный класс для вспомогательных функций, таких как проверка формата номера карты.

### Файл данных cards.txt
Файл `cards.txt` используется для хранения информации о картах. Каждая строка файла содержит следующие данные:
- Номер карты.
- ПИН-код.
- Баланс.
- Статус блокировки (true или false).
- Время блокировки (в миллисекундах).

### Пример работы программы

1. Программа запрашивает ввод номера карты и ПИН-кода.
2. Пользователь вводит данные. Если данные верны, открывается меню:
   - Проверить баланс.
   - Снять средства.
   - Пополнить баланс.
   - Выход.
3. Пользователь выбирает одну из опций и выполняет соответствующее действие.
4. После завершения работы с банкоматом пользователь может выйти из системы.

### Обработка ошибок

Программа включает обработку ошибок, таких как:
- Неправильный формат номера карты.
- Неверный ПИН-код.
- Превышение лимита снятия или пополнения.
- Блокировка карты после трех неверных попыток ввода ПИН-кода.

### Дополнительные функции

- Автоматическая разблокировка карты через сутки после блокировки.
- Хранение состояния банкомата в текстовом файле для сохранения данных между запусками программы.
