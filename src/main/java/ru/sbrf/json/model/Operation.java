package ru.sbrf.json.model;

public class Operation {
    private String type;
    private String cardNumber;
    private Long tranAmount;
    private String tranCurrency;
    private String tranTime;
    private String authCode;
    private String tranType;
    private String replyCode;
    private String merchant;
    private String authType;
    private Long commissionAmount;
    private String tranId;
    private String sourceRegNum;
    private String sourceNumber;
    private String sicCode;
    private String docId;
    private String paymentId;
    private String postpone;
    private Long cardCreditLimit;
    private Long lockoutCode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Long getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(Long tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getTranCurrency() {
        return tranCurrency;
    }

    public void setTranCurrency(String tranCurrency) {
        this.tranCurrency = tranCurrency;
    }

    public String getTranTime() {
        return tranTime;
    }

    public void setTranTime(String tranTime) {
        this.tranTime = tranTime;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getAuthType() {
        return authType;
    }

    public void setAuthType(String authType) {
        this.authType = authType;
    }

    public Long getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(Long commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getSourceRegNum() {
        return sourceRegNum;
    }

    public void setSourceRegNum(String sourceRegNum) {
        this.sourceRegNum = sourceRegNum;
    }

    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber;
    }

    public String getSicCode() {
        return sicCode;
    }

    public void setSicCode(String sicCode) {
        this.sicCode = sicCode;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPostpone() {
        return postpone;
    }

    public void setPostpone(String postpone) {
        this.postpone = postpone;
    }

    public Long getCardCreditLimit() {
        return cardCreditLimit;
    }

    public void setCardCreditLimit(Long cardCreditLimit) {
        this.cardCreditLimit = cardCreditLimit;
    }

    public Long getLockoutCode() {
        return lockoutCode;
    }

    public void setLockoutCode(Long lockoutCode) {
        this.lockoutCode = lockoutCode;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "type='" + type + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", tranAmount=" + tranAmount +
                ", tranCurrency='" + tranCurrency + '\'' +
                ", tranTime='" + tranTime + '\'' +
                ", authCode='" + authCode + '\'' +
                ", tranType='" + tranType + '\'' +
                ", replyCode='" + replyCode + '\'' +
                ", merchant='" + merchant + '\'' +
                ", authType='" + authType + '\'' +
                ", commissionAmount=" + commissionAmount +
                ", tranId='" + tranId + '\'' +
                ", sourceRegNum='" + sourceRegNum + '\'' +
                ", sourceNumber='" + sourceNumber + '\'' +
                ", sicCode='" + sicCode + '\'' +
                ", docId='" + docId + '\'' +
                ", paymentId='" + paymentId + '\'' +
                ", postpone='" + postpone + '\'' +
                ", cardCreditLimit=" + cardCreditLimit +
                ", lockoutCode=" + lockoutCode +
                '}';
    }
}
