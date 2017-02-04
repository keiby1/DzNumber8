package com.company;

import java.util.Scanner;

public class Main {
    public static void Task1(Card card) {
        Increaser increaser = new Increaser(card);
        Decreaser decreaser = new Decreaser(card);

        increaser.setPriority(2);
        decreaser.setPriority(8);

        increaser.start();
        //increaser.interrupt();
        decreaser.start();
        //decreaser.interrupt();
    }

    public static void Task2(Card card) {
            new Thread(new SequentialIncreaser(card)).start();
            new Thread(new SequentialDecreaser(card)).start();
    }

    public static void Task3() {
        Account account = new Account();
        SynchronizedAccount synchronizedAccount = new SynchronizedAccount(account);
        new Thread(new NewThread(synchronizedAccount)).start();
        new Thread(new ExtraThread(synchronizedAccount)).start();
    }

    public static void main(String[] args) {
        SomeTerminal terminal = new SomeTerminal();
        Scanner sc = new Scanner(System.in);
        Card testCard = new Card(500);

        while (true) {

            System.out.println("1-Есть Клиент/Карта");
            System.out.println("2-Нет Клиента/Карты");
            System.out.println("3-Нити задание 1");
            System.out.println("4-Нити задание 2");
            System.out.println("5-Нити задание 3");
            System.out.print("6-Выход из приложения\n>>");


            int key = sc.nextInt();
            switch (key) {
                case 1:
                    terminal.Welcome();
                    break;
                case 2:
                    terminal.CreatingClientOrCard();
                    break;
                case 3:
                    Task1(testCard);
                    break;
                case 4:
                    Task2(testCard);
                    break;
                case 5:
                    Task3();
                    break;
                case 6:
                    return;

            }
        }
    }
}
