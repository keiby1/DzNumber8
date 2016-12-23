package com.company;

import javafx.beans.binding.StringBinding;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by lushi on 29.11.2016.
 */
public class InputOutputStream implements Serializable {
    // класс для чтения файла
    private InputStream inputstream;

    // класс для записи в файл
    private OutputStream outputStream;

    // путь к файлу
    private String path;

    public InputOutputStream(String path) {
        this.path = path;
    }

    // чтение файла
    public ArrayList<String> read() throws IOException {
        /*
        Метод возвращает список состоящий из строк считанных из файла
         */
        ArrayList<String> tmp = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        inputstream = new FileInputStream(path);
        int data = inputstream.read();
        char content;
        while (data != -1) {
            content = (char) data;
            sb.append(content);
            data = inputstream.read();
            if (content == '\n' && content != '\r') {
                tmp.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }
        inputstream.close();
        return tmp;
    }

    // запись в файл
    public void write(String st) throws IOException {
        outputStream = new FileOutputStream(path, true);
        outputStream.write(st.getBytes());
        outputStream.close();
    }
}
