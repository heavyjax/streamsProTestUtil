package ru.sbrf.db.model;

import java.util.Date;

public class Way4Event {

    private long id;
    private String addressData;
    private String deliveryChannel;
    private String code;
    private String dateFrom;
    private String messageDetails;
    private String messageString1;
    private String messageString2;
    private String messageString3;
    private String messageString4;
    private String messageString5;
    private String messageString6;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressData() {
        return addressData;
    }

    public void setAddressData(String addressData) {
        this.addressData = addressData;
    }

    public String getDeliveryChannel() {
        return deliveryChannel;
    }

    public void setDeliveryChannel(String deliveryChannel) {
        this.deliveryChannel = deliveryChannel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getMessageDetails() {
        return messageDetails;
    }

    public void setMessageDetails(String messageDetails) {
        this.messageDetails = messageDetails;
    }

    public String getMessageString1() {
        return messageString1;
    }

    public void setMessageString1(String messageString1) {
        this.messageString1 = messageString1;
    }

    public String getMessageString2() {
        return messageString2;
    }

    public void setMessageString2(String messageString2) {
        this.messageString2 = messageString2;
    }

    public String getMessageString3() {
        return messageString3;
    }

    public void setMessageString3(String messageString3) {
        this.messageString3 = messageString3;
    }

    public String getMessageString4() {
        return messageString4;
    }

    public void setMessageString4(String messageString4) {
        this.messageString4 = messageString4;
    }

    public String getMessageString5() {
        return messageString5;
    }

    public void setMessageString5(String messageString5) {
        this.messageString5 = messageString5;
    }

    public String getMessageString6() {
        return messageString6;
    }

    public void setMessageString6(String messageString6) {
        this.messageString6 = messageString6;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Way4Event(long id, String addressData, String deliveryChannel, String code, String dateFrom, String messageString1, String messageString2, String messageString3, String messageString4, String messageString5, String messageString6, String status) {
        this.id = id;
        this.addressData = addressData;
        this.deliveryChannel = deliveryChannel;
        this.code = code;
        this.dateFrom = dateFrom;
        this.messageString1 = messageString1;
        this.messageString2 = messageString2;
        this.messageString3 = messageString3;
        this.messageString4 = messageString4;
        this.messageString5 = messageString5;
        this.messageString6 = messageString6;
        this.status = status;
    }
}