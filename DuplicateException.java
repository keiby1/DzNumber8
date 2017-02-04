package com.company;

/**
 * Created by lushi on 28.11.2016.
 */
public class DuplicateException extends Exception {
    public void Warning() {
        System.out.println("Пользователь уже существует!");
    }

    public void Recommendation() {
        System.out.println("Войдите под существующим аккаунтом!");
    }
}
