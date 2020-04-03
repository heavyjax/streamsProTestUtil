package ru.sbrf.db.model;

import java.util.Date;

public class EvntMsg {
    public static final String TABLE_NAME = "EVNT_MSG";
    private long id;
    private long usageActionOid;
    private String addressData;
    private String deliveryChannel;
    private String code;
    private Date dateFrom;
    private Date dateTo;
    private String messageDetails;
    private String messageString1;
    private String messageString2;
    private String messageString3;
    private String messageString4;
    private String messageString5;
    private String messageString6;
    private long msgTemplate;
    private String status;
    private String sendingChannel;
    private Date sendingDate;
    private String sendingDetails;
    private String refNumber;
    private String subject;
    private long priority;
    private long groupNumber;
    private String partitionKey;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUsageActionOid() {
        return usageActionOid;
    }

    public void setUsageActionOid(long usageActionOid) {
        this.usageActionOid = usageActionOid;
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

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
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

    public long getMsgTemplate() {
        return msgTemplate;
    }

    public void setMsgTemplate(long msgTemplate) {
        this.msgTemplate = msgTemplate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSendingChannel() {
        return sendingChannel;
    }

    public void setSendingChannel(String sendingChannel) {
        this.sendingChannel = sendingChannel;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public String getSendingDetails() {
        return sendingDetails;
    }

    public void setSendingDetails(String sendingDetails) {
        this.sendingDetails = sendingDetails;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public long getGroupNumber() {
        return groupNumber;
    }

    public void setGroupNumber(long groupNumber) {
        this.groupNumber = groupNumber;
    }

    public String getPartitionKey() {
        return partitionKey;
    }

    public void setPartitionKey(String partitionKey) {
        this.partitionKey = partitionKey;
    }

    @Override
    public String toString() {
        return "EvntMsg{" +
                "id=" + id +
                ", usageActionOid=" + usageActionOid +
                ", addressData='" + addressData + '\'' +
                ", deliveryChannel='" + deliveryChannel + '\'' +
                ", code='" + code + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", messageDetails='" + messageDetails + '\'' +
                ", messageString1='" + messageString1 + '\'' +
                ", messageString2='" + messageString2 + '\'' +
                ", messageString3='" + messageString3 + '\'' +
                ", messageString4='" + messageString4 + '\'' +
                ", messageString5='" + messageString5 + '\'' +
                ", messageString6='" + messageString6 + '\'' +
                ", msgTemplate=" + msgTemplate +
                ", status='" + status + '\'' +
                ", sendingChannel='" + sendingChannel + '\'' +
                ", sendingDate=" + sendingDate +
                ", sendingDetails='" + sendingDetails + '\'' +
                ", refNumber='" + refNumber + '\'' +
                ", subject='" + subject + '\'' +
                ", priority=" + priority +
                ", groupNumber=" + groupNumber +
                ", partitionKey='" + partitionKey + '\'' +
                '}';
    }
}
