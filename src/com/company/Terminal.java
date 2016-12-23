package com.company;

/**
 * Created by lushi on 28.11.2016.
 */
public interface Terminal {
    /*
    Интерфейс Териминал. Содержит методы для проверки счёта, снятия и внесения средств на карту,
    создания и удаления клиентов и карт.
     */
    void CheckScore(Card card);

    void GetMoney(Card card);

    void PutMoney(Card card);

    void CreateClient();

    void DeleteClient(Client client);

    void CreateCard();

    void DeleteCard(Card card);
}
