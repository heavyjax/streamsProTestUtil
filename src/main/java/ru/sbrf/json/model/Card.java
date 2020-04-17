package ru.sbrf.json.model;

public class Card {
    private Long cardBalance;
    private String cardCurrency;
    private String rbsNumber;
    private String cardStatus;
    private String prevCardNumber;
    private String product;
    private String cardExpire;
    private String contractNumber;
    private String tokenReferenceID;

    public Long getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Long cardBalance) {
        this.cardBalance = cardBalance;
    }

    public String getCardCurrency() {
        return cardCurrency;
    }

    public void setCardCurrency(String cardCurrency) {
        this.cardCurrency = cardCurrency;
    }

    public String getRbsNumber() {
        return rbsNumber;
    }

    public void setRbsNumber(String rbsNumber) {
        this.rbsNumber = rbsNumber;
    }

    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getPrevCardNumber() {
        return prevCardNumber;
    }

    public void setPrevCardNumber(String prevCardNumber) {
        this.prevCardNumber = prevCardNumber;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getCardExpire() {
        return cardExpire;
    }

    public void setCardExpire(String cardExpire) {
        this.cardExpire = cardExpire;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public String getTokenReferenceID() {
        return tokenReferenceID;
    }

    public void setTokenReferenceID(String tokenReferenceID) {
        this.tokenReferenceID = tokenReferenceID;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardBalance='" + cardBalance + '\'' +
                ", cardCurrency='" + cardCurrency + '\'' +
                ", rbsNumber='" + rbsNumber + '\'' +
                ", cardStatus='" + cardStatus + '\'' +
                ", prevCardNumber='" + prevCardNumber + '\'' +
                ", product='" + product + '\'' +
                ", cardExpire='" + cardExpire + '\'' +
                ", contractNumber='" + contractNumber + '\'' +
                ", tokenReferenceID='" + tokenReferenceID + '\'' +
                '}';
    }
}
