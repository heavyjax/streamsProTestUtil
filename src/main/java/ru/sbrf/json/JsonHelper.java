package ru.sbrf.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import ru.sbrf.db.model.EvntMsg;
import ru.sbrf.json.model.*;
import ru.sbrf.util.Util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class JsonHelper {

    private final ObjectMapper mapper;

    public JsonHelper() {
        DateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.mapper = new ObjectMapper();
        mapper.setDateFormat(formattedDate);
    }

    public JsonMessage getObjectFromKafkaMessage(String kafkaMessage) throws IOException {
        return mapper.readValue(kafkaMessage, JsonMessage.class);
    }

    public String getJsonKafkaMessage(final EvntMsg evntMsg) throws JsonProcessingException {
        JsonMessage jsonMessage = new JsonMessage();
        Table table = fillerTableSection(evntMsg);
        jsonMessage.setTable(table);

        System.out.println("Message will be send into kafka: " + mapper.
                writeValueAsString(fillerMsgSection(jsonMessage, table.getMessageString())));

        return mapper.writeValueAsString(fillerMsgSection(jsonMessage, table.getMessageString()));
    }

    private static Table fillerTableSection(EvntMsg msg){
        Table table = new Table();

        StringBuilder str = new StringBuilder();
        str.append(msg.getMessageString1()).
                append(msg.getMessageString2()).
                append(msg.getMessageString3()).
                append(msg.getMessageString4()).
                append(msg.getMessageString5()).
                append(msg.getMessageString6());

        table.setId(msg.getId());
        table.setUsageActionOid(msg.getUsageActionOid());
        table.setAddressData(msg.getAddressData());
        table.setDeliveryChannel(msg.getDeliveryChannel());
        table.setCode(msg.getCode());
        table.setDateFrom(msg.getDateFrom());
        table.setDateTo(msg.getDateTo());
        table.setMessageDetails(msg.getMessageDetails());
        table.setMessageString(str.toString());
        table.setMessageTemplate(msg.getMsgTemplate());
        table.setStatus(msg.getStatus());
        table.setSendingChannel(msg.getSendingChannel());
        table.setSendingDate(msg.getSendingDate());
        table.setSendingDetails(msg.getSendingDetails());
        table.setRefNumber(msg.getRefNumber());
        table.setSubject(msg.getSubject());
        table.setPriority(msg.getPriority());
        return table;
    }

    private static JsonMessage fillerMsgSection(JsonMessage json, final String msg){
        String[] msgDetails = msg.split("\\^");
        switch (msgDetails[0]){
            case "A":
                return fillAType(json, msgDetails);
            case "C":
                return fillCType(json, msgDetails);
            case "F":
                return fillFType(json, msgDetails);
            case "AP":
                return fillAPType(json, msgDetails);
            case "S":
                return fillSType(json, msgDetails);
            case "I":
                return fillIType(json, msgDetails);
            case "B":
                return fillBType(json, msgDetails);
            case "N":
                return fillNType(json, msgDetails);
            case "LC":
                return fillLCType(json, msgDetails);
            case "LA":
                return fillLAType(json, msgDetails);
            default:
                //unhandled message
                return json;
        }
    }

    private static JsonMessage fillAType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        operation.setTranAmount(msgDetails.length > 2 ? Long.parseLong(Util.amountFormat(msgDetails[2])) : 0L);
        operation.setTranCurrency(msgDetails.length > 3 ? msgDetails[3] : "");
        operation.setTranTime(msgDetails.length > 4 ? msgDetails[4] : "");
        operation.setAuthCode(msgDetails.length > 5 ? msgDetails[5] : "");
        operation.setReplyCode(msgDetails.length > 6 ? msgDetails[6] : "");
        operation.setTranType(msgDetails.length > 7 ? msgDetails[7] : "");
        operation.setMerchant(msgDetails.length > 8 ? msgDetails[8] : "");
        operation.setAuthType(msgDetails.length > 9 ? msgDetails[9] : "");
        card.setCardBalance(msgDetails.length > 10 ?
                ("0".equals(msgDetails[6]) ? Long.parseLong(Util.amountFormat(msgDetails[10])) : 0L) : null);
        card.setCardCurrency(msgDetails.length > 11 ? ("0".equals(msgDetails[6]) ? msgDetails[11] : "0") : "");
        operation.setCommissionAmount(msgDetails.length > 12 ?
                ("".equals(Util.amountFormat(msgDetails[12])) ?
                        null : Long.parseLong(Util.amountFormat(msgDetails[12])))
                : null);
        operation.setTranId(msgDetails.length > 13 ?
                (StringUtils.isBlank(msgDetails[13]) ? "-1" : msgDetails[13]) : "");

        if (msgDetails.length > 14){
            operation.setSourceRegNum(msgDetails.length > 14 ? msgDetails[14]:"");
            client.setClientITN(msgDetails.length > 15 ? msgDetails[15]:"");
            operation.setSourceNumber(msgDetails.length > 16 ? msgDetails[16]:"");
            operation.setSicCode(msgDetails.length > 17 ? msgDetails[17]:"");
        }

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillFType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        operation.setTranAmount(msgDetails.length > 2 ?
                ("".equals(Util.amountFormat(msgDetails[2])) ?
                        null : Long.parseLong(Util.amountFormat(msgDetails[2]))) : null);
        operation.setTranCurrency(msgDetails.length > 3 ? msgDetails[3] : "");
        operation.setTranTime(msgDetails.length > 4 ? msgDetails[4] : "");
        operation.setAuthCode(msgDetails.length > 5 ? msgDetails[5] : "");
        operation.setReplyCode(msgDetails.length > 6 ? msgDetails[6] : "");
        operation.setTranType(msgDetails.length > 7 ? msgDetails[7] : "");
        operation.setMerchant(msgDetails.length > 8 ? msgDetails[8] : "");
        card.setCardBalance(msgDetails.length > 9 ?
                (msgDetails[6].equals("0") ? Long.parseLong(Util.amountFormat(msgDetails[9])) : 0L) : null);
        card.setCardCurrency(msgDetails.length > 10 ? (msgDetails[6].equals("0") ? msgDetails[10] : "0") : "");
        operation.setCommissionAmount(msgDetails.length > 11 ?
                Long.parseLong(Util.amountFormat(msgDetails[11])) : null);
        operation.setPaymentId(msgDetails.length > 12 ? msgDetails[12] : "");
        operation.setPostpone(msgDetails.length > 13 ? msgDetails[13] : "");

        if (msgDetails.length > 14){
            operation.setSourceRegNum(msgDetails.length > 14 ? msgDetails[14]:"");
            client.setClientITN(msgDetails.length > 15 ? msgDetails[15]:"");
            operation.setSourceNumber(msgDetails.length > 16 ? msgDetails[16]:"");
            operation.setSicCode(msgDetails.length > 17 ? msgDetails[17]:"");
        }

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillSType(JsonMessage json, String[] msgDetails) {

        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        operation.setTranAmount(msgDetails.length > 2 ? Long.parseLong(Util.amountFormat(msgDetails[2])) : null);
        operation.setTranCurrency(msgDetails.length > 3 ? msgDetails[3] : "");
        operation.setTranTime(msgDetails.length > 4 ? msgDetails[4] : "");
        operation.setTranType(msgDetails.length > 5 ? msgDetails[5] : "");
        card.setCardBalance(msgDetails.length > 6 ? Long.parseLong(Util.amountFormat(msgDetails[6])) : null);
        operation.setCardCreditLimit(msgDetails.length > 7 ? Long.parseLong(Util.amountFormat(msgDetails[7])) : null);

        if (msgDetails.length > 8){
            operation.setSourceRegNum(msgDetails.length > 8 ? msgDetails[8]:"");
            client.setClientITN(msgDetails.length > 9 ? msgDetails[9]:"");
            operation.setSourceNumber(msgDetails.length > 10 ? msgDetails[10]:"");
            operation.setSicCode(msgDetails.length > 11 ? msgDetails[11]:"");
        }

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillCType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Message msg = new Message();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        msg.setMsg(msgDetails.length > 2 ? msgDetails[2] : "");

        json.setOperation(operation);
        json.setMessage(msg);
        return json;
    }

    private static JsonMessage fillAPType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Message msg = new Message();
        Card card = new Card();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        msg.setMsg(msgDetails.length > 2 ? msgDetails[2] : "");
        card.setTokenReferenceID(msgDetails.length > 3 ? msgDetails[3] : "");

        json.setOperation(operation);
        json.setMessage(msg);
        json.setCard(card);
        return json;
    }

    private static JsonMessage fillIType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Message msg = new Message();
        Client client = new Client();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        operation.setTranTime(msgDetails.length > 2 ? msgDetails[2] : "");
        operation.setTranType(msgDetails.length > 3 ? msgDetails[3] : "");
        client.setCardsClient(msgDetails.length > 4 ? msgDetails[4].split(",") : new String[0]);
        card.setRbsNumber(msgDetails.length > 6 ? msgDetails[6] : "");
        card.setPrevCardNumber(msgDetails.length > 7 ? msgDetails[7] : "");
        client.setClientITN(msgDetails.length > 8 ? msgDetails[8] : "");
        client.setClientWayId(msgDetails.length > 9 ? msgDetails[9] : "");
        card.setCardStatus(msgDetails.length > 10 ? msgDetails[10] : "");
        card.setProduct(msgDetails.length > 11 ? msgDetails[11] : "");
        card.setCardExpire(msgDetails.length > 12 ? msgDetails[12] : "");
        card.setContractNumber(msgDetails.length > 13 ? msgDetails[13] : "");

        json.setOperation(operation);
        json.setCard(card);
        json.setMessage(msg);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillBType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        operation.setTranTime(msgDetails.length > 2 ? msgDetails[2] : "");
        operation.setLockoutCode(msgDetails.length > 3 ? Long.parseLong(msgDetails[3]) : null);
        card.setCardStatus(msgDetails.length > 4 ? msgDetails[4] : "");
        client.setCardsClient(msgDetails.length > 5 ? msgDetails[5].split(",") : new String[0]);

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillNType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Message msg = new Message();
        Client client = new Client();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        operation.setTranTime(msgDetails.length > 2 ? msgDetails[2] : "");
        operation.setTranType(msgDetails.length > 3 ? msgDetails[3] : "");
        card.setRbsNumber(msgDetails.length > 4 ? msgDetails[4] : "");
        card.setPrevCardNumber(msgDetails.length > 5 ? msgDetails[5] : "");
        client.setClientITN(msgDetails.length > 6 ? msgDetails[6] : "");
        client.setClientWayId(msgDetails.length > 7 ? msgDetails[7] : "");
        card.setCardStatus(msgDetails.length > 8 ? msgDetails[8] : "");
        card.setProduct(msgDetails.length > 9 ? msgDetails[9] : "");
        card.setCardExpire(msgDetails.length > 10 ? msgDetails[10] : "");
        card.setContractNumber(msgDetails.length > 11 ? msgDetails[11] : "");

        json.setOperation(operation);
        json.setCard(card);
        json.setMessage(msg);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillLCType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        operation.setTranTime(msgDetails.length > 2 ? msgDetails[2] : "");
        card.setCardStatus(msgDetails.length > 3 ? msgDetails[3] : "");
        client.setClientITN(msgDetails.length > 4 ? msgDetails[4] : "");
        client.setClientWayId(msgDetails.length > 5 ? msgDetails[5] : "");

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillLAType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(msgDetails.length > 0 ? msgDetails[0] : "");
        operation.setCardNumber(msgDetails.length > 1 ? msgDetails[1] : "");
        operation.setTranTime(msgDetails.length > 2 ? msgDetails[2] : "");
        card.setCardStatus(msgDetails.length > 3 ? msgDetails[3] : "");
        client.setClientITN(msgDetails.length > 4 ? msgDetails[4] : "");
        client.setClientWayId(msgDetails.length > 5 ? msgDetails[5] : "");

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }
}
