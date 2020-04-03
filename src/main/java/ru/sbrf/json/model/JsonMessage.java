package ru.sbrf.json.model;

public class JsonMessage {
    private Table table;
    private Operation operation;
    private Card card;
    private Message message;
    private Client client;

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "JsonMessage{" +
                "table=" + table +
                ", operation=" + operation +
                ", card=" + card +
                ", message=" + message +
                ", client=" + client +
                '}';
    }
}
