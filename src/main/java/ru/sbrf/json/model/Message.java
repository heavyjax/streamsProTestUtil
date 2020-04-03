package ru.sbrf.json.model;

import java.util.Arrays;

public class Message {
    private String msg;
    private String msgType;
    private String[] mbcCardList;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String[] getMbcCardList() {
        return mbcCardList;
    }

    public void setMbcCardList(String[] mbcCardList) {
        this.mbcCardList = mbcCardList;
    }

    @Override
    public String toString() {
        return "Message{" +
                "msg='" + msg + '\'' +
                ", msgType='" + msgType + '\'' +
                ", mbcCardList=" + Arrays.toString(mbcCardList) +
                '}';
    }
}
