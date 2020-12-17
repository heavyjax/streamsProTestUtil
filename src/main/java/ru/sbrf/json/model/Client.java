package ru.sbrf.json.model;

import java.util.Arrays;

public class Client {
    private String clientITN;
    private String clientWayId;
    private String[] cardsClient;
    private String regNumber;
    private String lastName;
    private String firstName;
    private String middleName;
    private String birthDate;
    private String gender;

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

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientITN='" + clientITN + '\'' +
                ", clientWayId='" + clientWayId + '\'' +
                ", cardsClient=" + Arrays.toString(cardsClient) +
                ", regNumber='" + regNumber + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
