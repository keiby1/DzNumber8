package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by lushi on 28.11.2016.
 */
public class Card implements Serializable {
    private String Number, PIN;     //номер и ПИН карты
    private int Balance;            //баланс карты

    public Card(String number, String pin) { //конструктор для создания новой карты
        Number = number;
        PIN = pin;
        Balance = 0;
    }

    public Card(String number, String pin, String balance) { //конструктор для карты, которая уже есть в БД
        Number = number;
        PIN = pin;
        Balance = Integer.parseInt(balance);
    }

    Card(int balance) {
        Balance = balance;
    }

    public String GetNumber() {//получение номера карты
        return Number;
    }

    public synchronized int GetBalance() {//мтеод для получения баланса
        return Balance;
    }

    public synchronized void Increase(int money) {//метод для увеличения баланса
        Balance += money;
    }

    public synchronized void Reduce(int money) {//метод для уменьшения баланса
        Balance -= money;
    }

    public void SaveInfo() {//метод для сохранения данных о карте в БД.
        try (FileWriter fw = new FileWriter("Card.txt", true)) {
            fw.write(Number + "\r\n" + PIN + "\r\n" + Balance + "\r\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SaveInfoBinFile() {
        InputOutputStream ioStream = new InputOutputStream("CardBin.txt");
        try {
            ioStream.write(Number + "\r\n" + PIN + "\r\n" + Balance + "\r\n");
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
