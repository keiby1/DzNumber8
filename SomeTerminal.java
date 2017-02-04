package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by lushi on 28.11.2016.
 */
public class SomeTerminal implements Terminal, Serializable {

    @Override
    public void CheckScore(Card card) {
        //метод для проверки баланса карты
        System.out.println("Баллас: " + card.GetBalance());
    }

    @Override
    public void GetMoney(Card card) {
        /*метод для снятия наличных с карты. В методе предусмотрена проверка снимаемой сумы(он должна быть кратной 100,
        и проверка о достаточности средств на карте.
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сумму: ");
        try {
            int money = sc.nextInt();
            if (card.GetBalance() - money >= 0)
                if (money % 100 == 0)
                    card.Reduce(money);
                else
                    System.out.println("Введена недопустимая сумма!");
            else
                throw new LackMoneyException();
        } catch (LackMoneyException e) {
            e.Warning();
            e.Recommendation();
        }
    }

    @Override
    public void PutMoney(Card card) {
        /*Метод для внесения наличных на карту.
         В методе предусмотрена проверка, является ли вносимая сумма кратной 100
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сумму: ");
        int money = sc.nextInt();
        if (money % 100 == 0)
            card.Increase(money);
        else
            System.out.println("Введена недопустимая сумма!");
    }

    @Override
    public void CreateClient() {
        /*
        Метод для создания нового клиента. Принимает данные введённые с консоли и проверяет, нет ли этого пользователя в БД.
        Поле Серия и Номер паспорта - необходимо для однозначного определени повторяющегося пользователя.
        В случае, еслт в БД клиента нет, информация о нем дописывается в файл.
        В противном случае программа кинет исключение.
         */
        Scanner sc = new Scanner(System.in);
        Client client;
        String Name, Surname, Pasport;
        System.out.print("Введите Фамилию: ");
        Surname = sc.nextLine();
        System.out.print("Введите Имя: ");
        Name = sc.nextLine();
        System.out.print("Введите Серию и номер паспорта: ");
        Pasport = sc.nextLine();

        try {
            client = new Client(Name, Surname, Pasport);
            if (CheckClient(Name, Surname, Pasport))
                throw new DuplicateException();
                // else client.SaveInfoBinFile();     //сохранение в бинарный файл
            else client.SaveInfo();          //сохранение в символьный файл
        } catch (DuplicateException e) {
            e.Warning();
            e.Recommendation();
        }
    }

    @Override
    public void DeleteClient(Client client) {
        /*
        Метод для удаления клиента из БД. Удаление Клиента не влечёт за собой удаление карт.
        Реализуется за счёт перезаписи всех клиентов файла, кроме указанного.
         */
        ArrayList<Client> clientsList = new ArrayList<Client>();
        try (BufferedReader r = new BufferedReader(new FileReader("Client.txt"))) {
            String readName, readSurName, readPasport;
            boolean CMPname, CMPsurName, CMPpasport;
            while (((readName = r.readLine()) != null) && ((readSurName = r.readLine()) != null) && ((readPasport = r.readLine()) != null)) {
                CMPname = !readPasport.equals(client.GetPasport());
                CMPsurName = !readName.equals(client.GetName());
                CMPpasport = !readSurName.equals(client.GetSurName());
                if (CMPname && CMPsurName && CMPpasport) {
                    clientsList.add(new Client(readName, readSurName, readPasport));
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileWriter fw = new FileWriter("Client.txt")) {
            fw.write("");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String DelClient = client.GetPasport();
        String tmpPasport;
        for (int i = 0; i < clientsList.size(); i++) {
            tmpPasport = clientsList.get(i).GetPasport();
            if (!tmpPasport.equals(DelClient)) {
                clientsList.get(i).SaveInfo();
            }
        }
    }

    @Override
    public void CreateCard() {
        /*
        Метод для создания карты. Пользователю предоставляется возможность выбрать(ввести) номер карты и пин для нее.
        В случае если карта с введенны номером уже зарегистрирована, будет брошено исключение.
         */
        Scanner sc = new Scanner(System.in);
        String number, pin;
        try {
            System.out.println("Введите желаемый номер карты: ");
            number = sc.nextLine();
            if (!CheckCard(number)) {
                System.out.println("Введите желаемый PIN: ");
                pin = sc.nextLine();
                Card card = new Card(number, pin, "0");
                card.SaveInfo();          //сохранение информации в символьный файл
                //card.SaveInfoBinFile();     //сохранение информации в бинарный файл
            } else
                throw new DuplicateException();
        } catch (DuplicateException e) {
            System.out.println("Карта с таким номер уже существует!");
        }
    }

    @Override
    public void DeleteCard(Card card) {
        /*
        Метод удаления карты.
         */
        ArrayList<Card> cardList = new ArrayList<Card>();
        try (BufferedReader r = new BufferedReader(new FileReader("Card.txt"))) {
            String readNumber, readPin, readBalance;
            while (((readNumber = r.readLine()) != null) && ((readPin = r.readLine()) != null)) {
                readBalance = r.readLine();
                if (!readNumber.equals(card.GetNumber())) {
                    cardList.add(new Card(readNumber, readPin, readBalance));
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        try (FileWriter fw = new FileWriter("Card.txt")) {
            fw.write("");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        String DelCardNumber = card.GetNumber();
        String tmpNumber;
        for (int i = 0; i < cardList.size(); i++) {
            tmpNumber = cardList.get(i).GetNumber();
            if (!tmpNumber.equals(DelCardNumber)) {
                cardList.get(i).SaveInfo();
            }
        }
    }

    public void CreatingClientOrCard() {
        /*
        Метод представляющий собой подменю теринала, в котором пользователь выбирает, что нужно создать
        нового клиента или новую карту.
         */
        Scanner sc = new Scanner(System.in);
        int key;
        boolean returnBack = false;
        while (!returnBack) {
            System.out.println("1-Создать клиента");
            System.out.println("2-Создать Карту");
            System.out.println("3-Назад");
            key = sc.nextInt();

            switch (key) {
                case 1:
                    CreateClient();
                    break;
                case 2:
                    CreateCard();
                    break;
                case 3:
                    returnBack = true;
                    break;
            }
        }
    }

    private boolean CheckCard(String number, String pin, Client client) {
        /*
        Метод авторизации. В данном методе проверяется есть ли в БД карта с введённым номером,
         затем проверяется совпадает ли ПИН карты с введённым.
         В случае успеха отображается главное меню терминала.
         В противном случае будет брошено исключение.
         */
        boolean finedCard = false;
        try {
            try (BufferedReader r = new BufferedReader(new FileReader("Card.txt"))) {
                String readNumber, readPin, readBalance;
                while (((readNumber = r.readLine()) != null) && ((readPin = r.readLine()) != null) && ((readBalance = r.readLine()) != null)) {
                    if (readNumber.equals(number)) {
                        if (readPin.equals(pin)) {
                            Card card = new Card(number, pin, readBalance);
                            Menu(card, client);
                            finedCard = true;
                            break;
                        } else {
                            finedCard = false;
                            throw new IncorrectPINException();
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
            if (!finedCard) {
                System.out.println("Карта не найдена!");
            }
        } catch (IncorrectPINException e) {
            e.Warning();
        }
        return finedCard;
    }

    private boolean CheckCard(String number) {
        /*
        Метод проверки есть ли в БД искомая карта.
         */
        boolean finedCard = false;
        try {
            try (BufferedReader r = new BufferedReader(new FileReader("Card.txt"))) {
                String readNumber, readPin, readBalance;
                while (((readNumber = r.readLine()) != null) && ((readPin = r.readLine()) != null) && ((readBalance = r.readLine()) != null)) {
                    if (readNumber.equals(number)) {
                        finedCard = true;
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return finedCard;
    }

    private boolean CheckClient(String name, String surName, String pasport) {
        /*
        Мето для проверки, есть ли в БД искомый клиент.
         */
        try (BufferedReader r = new BufferedReader(new FileReader("Client.txt"))) {
            String readName, readSurName, readPasport;
            while (((readName = r.readLine()) != null) && ((readSurName = r.readLine()) != null)) {
                readPasport = r.readLine();
                if (readName.equals(name) && readSurName.equals(surName)) {
                    if (readPasport.equals(pasport))
                        return true;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return false;
    }

    public void Welcome() {
        /*Метод отображающий меню авторизации терминала.
        Требуется ввести данные о карте т клиенте. Терминал проверяет в БД существование карты и клиента,
        а затем сверяет введенные номер/пин.
        В случае успешной авторизации вызывается метод Menu отображающий меню действий, которые доступны пользователю.
         */
        String name, surname, pasport, number, pin;
        int counterBeforeBan = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите Имя: ");
        name = sc.nextLine();
        System.out.println("Введите Фамилию: ");
        surname = sc.nextLine();
        System.out.println("Введите Серию и номер паспорта: ");
        pasport = sc.nextLine();

        try {
            if (CheckClient(name, surname, pasport)) {                //проверка существует ли клиент
                Client client = new Client(name, surname, pasport);

                while (true) {
                    System.out.println("Введите Номер карты: ");
                    number = sc.nextLine();
                    System.out.println("Введите PIN: ");
                    pin = sc.nextLine();
                    counterBeforeBan++;

                    if (CheckCard(number, pin, client))//проверка существования карты и корректности пина
                        break;

                    if (counterBeforeBan == 3) {
                        throw new BlockingAccountException();
                    }
                }
            } else
                System.out.println("Клиент не найден!");
        } catch (BlockingAccountException e) {
            e.Warning();
            e.Recommendation();
            e.Ban(3000);
        }

    }

    private void Menu(Card card, Client client) {
        /*
        Главное меню терминала.Становится доступно только после успешной авторизации.
         */
        Scanner sc = new Scanner(System.in);
        int key;
        boolean returnBack = false;
        while (!returnBack) {
            System.out.println("1-Проверить состояние счёта");
            System.out.println("2-Снять деньги");
            System.out.println("3-Положить деньги");
            System.out.println("4-Удалить клиента");
            System.out.println("5-Удалить карту");
            System.out.print("6-Выход\n>>");
            key = sc.nextInt();

            switch (key) {
                case 1:
                    CheckScore(card);
                    break;
                case 2:
                    GetMoney(card);
                    break;
                case 3:
                    PutMoney(card);
                    break;
                case 4:
                    DeleteClient(client);
                    System.out.println("Клиент удалён!");
                    returnBack = true;
                    break;
                case 5:
                    DeleteCard(card);
                    System.out.println("Карта удалена!");
                    returnBack = true;
                    break;
                case 6:
                    DeleteCard(card);
                    card.SaveInfo();
                    returnBack = true;
                    break;
            }
        }
    }
}
