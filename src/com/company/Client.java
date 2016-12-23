package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lushi on 28.11.2016.
 */
public class Client implements Serializable {
    private String Name, SurName;       //имя и фамилия клиента
    private String SeriaNumberPasport;  //серия и номер паспорта

    public Client(String name, String surName, String pasport) {
        /*
        Конструктор класса. Заполняет поля значениями из параметров
         */
        Name = name;
        SurName = surName;
        SeriaNumberPasport = pasport;
    }

    public String GetName() {//получение имени клиента
        return Name;
    }

    public String GetSurName() {//получение фамилии
        return SurName;
    }

    public String GetPasport() {//получение серии номера паспорта
        return SeriaNumberPasport;
    }

    public void SaveInfo() { //сохраняет данные о клиенте в БД
        try (FileWriter fw = new FileWriter("Client.txt", true)) {
            fw.write(Name + "\r\n" + SurName + "\r\n" + SeriaNumberPasport + "\r\n");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void SaveInfoBinFile() {
        InputOutputStream ioStream = new InputOutputStream("ClientBin.txt");
        try {
            ioStream.write(Name + "\r\n" + SurName + "\r\n" + SeriaNumberPasport + "\r\n");
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
