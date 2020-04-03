package ru.sbrf.json.model;

import java.util.Date;

public class Table {
    private long id;
    private long usageActionOid;
    private String addressData;
    private String deliveryChannel;
    private String code;
    private Date dateFrom;
    private Date dateTo;
    private String messageDetails;
    private String messageString;
    private long messageTemplate;
    private String status;
    private String sendingChannel;
    private Date sendingDate;
    private String sendingDetails;
    private String refNumber;
    private String subject;
    private long priority;
    private long groupNumber;
    private String nodeIdent;

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

    public String getMessageString() {
        return messageString;
    }

    public void setMessageString(String messageString) {
        this.messageString = messageString;
    }

    public long getMessageTemplate() {
        return messageTemplate;
    }

    public void setMessageTemplate(long messageTemplate) {
        this.messageTemplate = messageTemplate;
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

    public String getNodeIdent() {
        return nodeIdent;
    }

    public void setNodeIdent(String nodeIdent) {
        this.nodeIdent = nodeIdent;
    }

    @Override
    public String toString() {
        return "Table{" +
                "id=" + id +
                ", usageActionOid=" + usageActionOid +
                ", addressData='" + addressData + '\'' +
                ", deliveryChannel='" + deliveryChannel + '\'' +
                ", code='" + code + '\'' +
                ", dateFrom=" + dateFrom +
                ", dateTo=" + dateTo +
                ", messageDetails='" + messageDetails + '\'' +
                ", messageString='" + messageString + '\'' +
                ", messageTemplate=" + messageTemplate +
                ", status='" + status + '\'' +
                ", sendingChannel='" + sendingChannel + '\'' +
                ", sendingDate=" + sendingDate +
                ", sendingDetails='" + sendingDetails + '\'' +
                ", refNumber='" + refNumber + '\'' +
                ", subject='" + subject + '\'' +
                ", priority=" + priority +
                ", groupNumber=" + groupNumber +
                ", nodeIdent='" + nodeIdent + '\'' +
                '}';
    }
}
