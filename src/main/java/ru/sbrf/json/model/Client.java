package ru.sbrf.json.model;

import java.util.Arrays;

public class Client {
    private String clientITN;
    private String clientWayId;
    private String[] cardsClient;
    private String docNumber;
    private String lastNamePrev;
    private String lastNameCurr;
    private String firstNamePrev;
    private String firstNameCurr;
    private String middleNamePrev;
    private String middleNameCurr;
    private String birthDatePrev;
    private String birthDateCurr;

    public String getClientITN() {
        return clientITN;
    }

    public void setClientITN(String clientITN) {
        this.clientITN = clientITN;
    }

    public String getClientWayId() {
        return clientWayId;
    }

    public void setClientWayId(String clientWayId) {
        this.clientWayId = clientWayId;
    }

    public String[] getCardsClient() {
        return cardsClient;
    }

    public void setCardsClient(String[] cardsClient) {
        this.cardsClient = cardsClient;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getLastNamePrev() {
        return lastNamePrev;
    }

    public void setLastNamePrev(String lastNamePrev) {
        this.lastNamePrev = lastNamePrev;
    }

    public String getLastNameCurr() {
        return lastNameCurr;
    }

    public void setLastNameCurr(String lastNameCurr) {
        this.lastNameCurr = lastNameCurr;
    }

    public String getFirstNamePrev() {
        return firstNamePrev;
    }

    public void setFirstNamePrev(String firstNamePrev) {
        this.firstNamePrev = firstNamePrev;
    }

    public String getFirstNameCurr() {
        return firstNameCurr;
    }

    public void setFirstNameCurr(String firstNameCurr) {
        this.firstNameCurr = firstNameCurr;
    }

    public String getMiddleNamePrev() {
        return middleNamePrev;
    }

    public void setMiddleNamePrev(String middleNamePrev) {
        this.middleNamePrev = middleNamePrev;
    }

    public String getMiddleNameCurr() {
        return middleNameCurr;
    }

    public void setMiddleNameCurr(String middleNameCurr) {
        this.middleNameCurr = middleNameCurr;
    }

    public String getBirthDatePrev() {
        return birthDatePrev;
    }

    public void setBirthDatePrev(String birthDatePrev) {
        this.birthDatePrev = birthDatePrev;
    }

    public String getBirthDateCurr() {
        return birthDateCurr;
    }

    public void setBirthDateCurr(String birthDateCurr) {
        this.birthDateCurr = birthDateCurr;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientITN='" + clientITN + '\'' +
                ", clientWayId='" + clientWayId + '\'' +
                ", cardsClient=" + Arrays.toString(cardsClient) +
                ", docNumber='" + docNumber + '\'' +
                ", lastNamePrev='" + lastNamePrev + '\'' +
                ", lastNameCurr='" + lastNameCurr + '\'' +
                ", firstNamePrev='" + firstNamePrev + '\'' +
                ", firstNameCurr='" + firstNameCurr + '\'' +
                ", middleNamePrev='" + middleNamePrev + '\'' +
                ", middleNameCurr='" + middleNameCurr + '\'' +
                ", birthDatePrev='" + birthDatePrev + '\'' +
                ", birthDateCurr='" + birthDateCurr + '\'' +
                '}';
    }
}
