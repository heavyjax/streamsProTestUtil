package ru.sbrf.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import ru.qatools.json2pojo.beans.*;
import ru.sbrf.db.model.Way4Event;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

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

    public String getJsonKafkaMessage(Way4Event way4Event, String uid, String node) throws JsonProcessingException {
        JsonMessage jsonMessage = new JsonMessage();
        jsonMessage.setRequestChainUid(uid);
        jsonMessage.setNodeIdent(node);

        Table table = fillerTableSection(way4Event);
        table.setNodeIdent(node);
        jsonMessage.setTable(table);

        StringBuilder str = new StringBuilder();
        str.append(way4Event.getMessageString1()).
                append(StringUtils.isEmpty(way4Event.getMessageString2())? "" : way4Event.getMessageString2()).
                append(StringUtils.isEmpty(way4Event.getMessageString3())? "" : way4Event.getMessageString3()).
                append(StringUtils.isEmpty(way4Event.getMessageString4())? "" : way4Event.getMessageString4()).
                append(StringUtils.isEmpty(way4Event.getMessageString5())? "" : way4Event.getMessageString5()).
                append(StringUtils.isEmpty(way4Event.getMessageString6())? "" : way4Event.getMessageString6());

                mapper.writerWithDefaultPrettyPrinter().
                        writeValueAsString(fillerMsgSection(jsonMessage, str.toString()));
        return mapper.writeValueAsString(fillerMsgSection(jsonMessage, str.toString()));
    }

    public boolean compareJsonMessages(String message1, String message2) {
        JsonNode tree1 = null;
        JsonNode tree2 = null;
        try {
            tree1 = mapper.readTree(message1);
            tree2 = mapper.readTree(message2);
            System.out.println("Messages are equals: " + tree1.equals(tree2));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Message 1: " + message1);
        System.out.println("Message 2: " + message2);

        return tree1.equals(tree2);
    }

    public String exclude(String kafkaMessage, String node) {
        String[] strArr = kafkaMessage.split(",");
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String s: strArr) {
            if (!s.contains(node)) {
                sb.append(s);
                sb.append(",");
            }
        }
        return sb.substring(0, sb.toString().length() - 1);
    }

    private static Table fillerTableSection(Way4Event msg){
        Table table = new Table();

        table.setId(msg.getId());
        //table.setUsageActionOid(msg.getUsageActionOid());
        table.setAddressData(msg.getAddressData());
        table.setDeliveryChannel(msg.getDeliveryChannel());
        table.setCode(msg.getCode());
        table.setDateFrom(msg.getDateFrom());
        //table.setDateTo(msg.getDateTo());
        table.setMessageDetails(msg.getMessageDetails());
        //table.setMessageString(str.toString());
        //table.setMessageTemplate(msg.getMsgTemplate());
        table.setStatus(msg.getStatus());
        //table.setSendingChannel(msg.getSendingChannel());
        //table.setSendingDate(msg.getSendingDate());
        //table.setSendingDetails(msg.getSendingDetails());
        //table.setRefNumber(msg.getRefNumber());
        //table.setSubject(msg.getSubject());
        //table.setPriority(msg.getPriority());
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
            case "W":
                return fillNType(json, msgDetails);
            case "LC":
                return fillLCType(json, msgDetails);
            case "LA":
                return fillLAType(json, msgDetails);
            case "X":
                return fillXType(json, msgDetails);
            case "Y":
                return fillYType(json, msgDetails);
            case "T":
                return fillTType(json, msgDetails);
            default:
                //unhandled message
                return json;
        }
    }

    private static JsonMessage fillAType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(getStrValueByIndex(msgDetails, 0));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1));
        operation.setTranAmount(getAmountValueByIndex(msgDetails, 2 ));
        operation.setTranCurrency(getStrValueByIndex(msgDetails, 3 ));
        operation.setTranTime(getStrValueByIndex(msgDetails, 4 ));
        operation.setAuthCode(getStrValueByIndex(msgDetails, 5 ));
        operation.setReplyCode(getStrValueByIndex(msgDetails, 6 ));
        operation.setTranType(getStrValueByIndex(msgDetails, 7 ));
        operation.setMerchant(getStrValueByIndex(msgDetails, 8 ));
        operation.setAuthType(getStrValueByIndex(msgDetails, 9 ));
        card.setCardBalance(getAmountValueByIndex(msgDetails, 10));
        card.setCardCurrency(getStrValueByIndex(msgDetails, 11));
        operation.setCommissionAmount(getAmountValueByIndex(msgDetails,12));
        operation.setTranId(getStrValueByIndex(msgDetails, 13 ,"-1"));

        operation.setSourceRegNum(getStrValueByIndex(msgDetails, 14 ));
        operation.setSicCode(getStrValueByIndex(msgDetails, 15 ));
        operation.setDocId(getStrValueByIndex(msgDetails, 16 ));
        client.setClientITN(getStrValueByIndex(msgDetails, 17 ));
        operation.setSourceNumber(getStrValueByIndex(msgDetails, 18 ));

        operation.setStlAmount(getAmountValueByIndex(msgDetails, 19 ));
        operation.setStlCurrency(getStrValueByIndex(msgDetails, 20 ));
        operation.setRefundAmount(getAmountValueByIndex(msgDetails, 21));
        operation.setRefundCurrency(getStrValueByIndex(msgDetails, 22 ));
        operation.setDocPrevId(getStrValueByIndex(msgDetails, 23 ));
        operation.setTranTypeId(getStrValueByIndex(msgDetails, 24 ));
        operation.setSourceChannel(getStrValueByIndex(msgDetails, 25 ));
        operation.setCountryCode(getStrValueByIndex(msgDetails, 26 ));


        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }



    private static JsonMessage fillFType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1 ));
        operation.setTranAmount(getAmountValueByIndex(msgDetails, 2 ));
        operation.setTranCurrency(getStrValueByIndex(msgDetails, 3 ));
        operation.setTranTime(getStrValueByIndex(msgDetails, 4 ));
        operation.setAuthCode(getStrValueByIndex(msgDetails, 5 ));
        operation.setReplyCode(getStrValueByIndex(msgDetails, 6 ));
        operation.setTranType(getStrValueByIndex(msgDetails, 7 ));
        operation.setMerchant(getStrValueByIndex(msgDetails, 8 ));
        card.setCardBalance(getAmountValueByIndex(msgDetails,9));
        card.setCardCurrency(getStrValueByIndex(msgDetails, 10));
        operation.setCommissionAmount(getAmountValueByIndex(msgDetails, 11));
        operation.setPaymentId(getStrValueByIndex(msgDetails, 12 ));
        operation.setPostpone(getStrValueByIndex(msgDetails, 13 ));

        operation.setSourceRegNum(getStrValueByIndex(msgDetails, 14 ));
        operation.setSicCode(getStrValueByIndex(msgDetails, 15 ));
        operation.setDocId(getStrValueByIndex(msgDetails, 16 ));
        client.setClientITN(getStrValueByIndex(msgDetails, 17 ));
        operation.setSourceNumber(getStrValueByIndex(msgDetails, 18 ));

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillSType(JsonMessage json, String[] msgDetails) {

        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1 ));
        operation.setTranAmount(getAmountValueByIndex(msgDetails, 2 ));
        operation.setTranCurrency(getStrValueByIndex(msgDetails, 3 ));
        operation.setTranTime(getStrValueByIndex(msgDetails, 4 ));
        operation.setTranType(getStrValueByIndex(msgDetails, 5 ));
        card.setCardBalance(getAmountValueByIndex(msgDetails, 6));
        operation.setCardCreditLimit(getAmountValueByIndex(msgDetails, 7));

        operation.setSourceRegNum(getStrValueByIndex(msgDetails, 8));
        operation.setSicCode(getStrValueByIndex(msgDetails, 9));
        operation.setDocId(getStrValueByIndex(msgDetails, 10 ));
        client.setClientITN(getStrValueByIndex(msgDetails, 11 ));
        operation.setSourceNumber(getStrValueByIndex(msgDetails, 12 ));

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }


    private static JsonMessage fillCType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Message msg = new Message();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1 ));
        msg.setMsg(getStrValueByIndex(msgDetails, 2 ));

        json.setOperation(operation);
        json.setMessage(msg);
        return json;
    }

    private static JsonMessage fillAPType(JsonMessage json, String[] msgDetails) {

        Operation operation = new Operation();
        Message msg = new Message();
        Card card = new Card();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1 ));
        msg.setMsg(getStrValueByIndex(msgDetails, 2 ));
        card.setTokenReferenceID(getStrValueByIndex(msgDetails, 3 ));

        json.setOperation(operation);
        json.setMessage(msg);
        json.setCard(card);
        return json;
    }


    private static JsonMessage fillIType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1 ));
        operation.setTranTime(getStrValueByIndex(msgDetails, 2 ));
        operation.setTranType(getStrValueByIndex(msgDetails, 3 ));
        client.setCardsClient(getListValueByIndex(msgDetails, 4));

        card.setCardStatus(getStrValueByIndex(msgDetails, 6));
        card.setProduct(getStrValueByIndex(msgDetails, 7));
        card.setCardExpire(getStrValueByIndex(msgDetails, 8));
        card.setRbsNumber(getStrValueByIndex(msgDetails, 9 ));
        card.setPrevCardNumber(getStrValueByIndex(msgDetails, 10 ));
        client.setClientWayId(getStrValueByIndex(msgDetails, 11 ));
        client.setClientITN(getStrValueByIndex(msgDetails, 12));
        card.setContractNumber(getStrValueByIndex(msgDetails, 13));

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    private static JsonMessage  fillBType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1));
        operation.setTranTime(getStrValueByIndex(msgDetails, 2));
        operation.setTranType(getStrValueByIndex(msgDetails, 3));
        card.setCardStatus(getStrValueByIndex(msgDetails, 4));
        client.setCardsClient(getListValueByIndex(msgDetails, 5 ));
        //6 -- repeated 'B  '
        client.setClientITN(getStrValueByIndex(msgDetails, 7 ));
        client.setClientWayId(getStrValueByIndex(msgDetails, 8 ));

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

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1 ));
        operation.setTranType(getStrValueByIndex(msgDetails, 3 ));

        card.setCardDateOpen(getStrValueByIndex(msgDetails, 2 ));
        card.setCardStatus(getStrValueByIndex(msgDetails, 4));
        card.setProduct(getStrValueByIndex(msgDetails, 5 ));
        card.setCardExpire(getStrValueByIndex(msgDetails, 6 ));
        card.setRbsNumber(getStrValueByIndex(msgDetails, 7 ));
        card.setPrevCardNumber(getStrValueByIndex(msgDetails, 8 ));
        client.setClientWayId(getStrValueByIndex(msgDetails, 9 ));
        client.setClientITN(getStrValueByIndex(msgDetails, 10 ));
        card.setContractNumber(getStrValueByIndex(msgDetails, 11 ));
        card.setCardBalance(getAmountValueByIndex(msgDetails,12));

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

        operation.setType(getStrValueByIndex(msgDetails, 0));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1 ));
        operation.setTranTime(getStrValueByIndex(msgDetails, 2 ));
        card.setCardStatus(getStrValueByIndex(msgDetails, 3 ));
        client.setClientITN(getStrValueByIndex(msgDetails, 4 ));
        client.setClientWayId(getStrValueByIndex(msgDetails, 5 ));

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillLAType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1 ));
        operation.setTranTime(getStrValueByIndex(msgDetails, 2 ));
        card.setCardStatus(getStrValueByIndex(msgDetails, 3 ));
        client.setClientITN(getStrValueByIndex(msgDetails, 4 ));
        client.setClientWayId(getStrValueByIndex(msgDetails, 5 ));

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }


    private static JsonMessage fillXType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Client client = new Client();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setTranTime(getStrValueByIndex(msgDetails, 1));
        client.setClientWayId(getStrValueByIndex(msgDetails, 2));
        client.setClientITN(getStrValueByIndex(msgDetails, 3 ));
        client.setFirstName(getStrValueByIndex(msgDetails, 4 ));
        client.setLastName(getStrValueByIndex(msgDetails, 5));
        client.setMiddleName(getStrValueByIndex(msgDetails, 6 ));
        client.setRegNumber(getStrValueByIndex(msgDetails, 7 ));
        client.setGender(getStrValueByIndex(msgDetails, 8 ));
        client.setBirthDate(getStrValueByIndex(msgDetails, 9 ));

        client.setClientWayDate(getStrValueByIndex(msgDetails, 10 ));
        client.setBirthPlace(getStrValueByIndex(msgDetails, 11 ));
        client.setRegNumberDetails(getStrValueByIndex(msgDetails, 12 ));
        client.setCompanyName(getStrValueByIndex(msgDetails, 13 ));
        client.setProfession(getStrValueByIndex(msgDetails, 14 ));
        client.setWorkPhone(getStrValueByIndex(msgDetails, 15 ));
        client.setHomePhone(getStrValueByIndex(msgDetails, 16 ));
        client.setMobilePhone(getStrValueByIndex(msgDetails, 17 ));
        client.setEmail(getStrValueByIndex(msgDetails, 18 ));
        client.setCbCode(getStrValueByIndex(msgDetails, 19 ));
        client.setType(getStrValueByIndex(msgDetails, 20 ));


        json.setOperation(operation);
        json.setClient(client);
        return json;
    }


    private static JsonMessage fillYType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setTranTime(getStrValueByIndex(msgDetails, 1 ));
        client.setClientWayId(getStrValueByIndex(msgDetails, 2 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 3 ));

        card.setRbsNumber(getStrValueByIndex(msgDetails, 4 ));
        card.setCardDateOpen(getStrValueByIndex(msgDetails, 5 ));
        card.setCardExpire(getStrValueByIndex(msgDetails, 6 ));
        card.setCardCurrency(getStrValueByIndex(msgDetails, 7 ));
        card.setAddInfo(getStrValueByIndex(msgDetails, 8 ));
        card.setCbCode(getStrValueByIndex(msgDetails, 9 ));
        card.setContractId(getStrValueByIndex(msgDetails, 10 ));
        card.setCardDateClose(getStrValueByIndex(msgDetails, 11 ));
        card.setProduct(getStrValueByIndex(msgDetails, 12 ));

        client.setType(getStrValueByIndex(msgDetails, 13 ));
        card.setTrFirstName(getStrValueByIndex(msgDetails, 14 ));
        card.setTrLastName(getStrValueByIndex(msgDetails, 15 ));

        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    private static JsonMessage fillTType(JsonMessage json, String[] msgDetails) {
        Operation operation = new Operation();
        Card card = new Card();
        Client client = new Client();

        operation.setType(getStrValueByIndex(msgDetails, 0 ));
        operation.setCardNumber(getStrValueByIndex(msgDetails, 1 ));
        card.setLevel(getStrValueByIndex(msgDetails, 2 ));
        operation.setTranTime(getStrValueByIndex(msgDetails, 3 ));
        client.setClientITN(getStrValueByIndex(msgDetails, 4 ));


        json.setOperation(operation);
        json.setCard(card);
        json.setClient(client);
        return json;
    }

    // move to utils
    private static Long amountFormat(String amount){
        final String q = amount;
        try {
            amount = amount.replaceAll(" ", "");

            if (StringUtils.isBlank(amount))
                return null;
            if ("0.0".equals(amount) || "0".equals(amount))
                return 0l;

            if (amount.charAt(amount.length() - 3) == ','){
                amount = amount.replaceAll("\\.", "");
                amount = amount.replace(",", ".");
            }

            else if (amount.charAt(amount.length() - 3) == '.'){
                amount = amount.replaceAll(",", "");
            }

            BigDecimal amt = new BigDecimal(amount);
            return amt.multiply(new BigDecimal(100)).longValue();
        } catch ( Exception e){
            System.out.println("Wrong number format: " + amount + "//" + q);
            return null;
        }
    }


    private static String getStrValueByIndex(String[] msgDetails, int i, String nullVal) {
        if (msgDetails.length > i && !StringUtils.isBlank(msgDetails[i])){
            return msgDetails[i];
        }
        return nullVal;
    }

    private static String getStrValueByIndex(String[] msgDetails, int i) {
        return getStrValueByIndex(msgDetails, i, null);
    }


    private static Long getAmountValueByIndex(String[] msgDetails, int i) {
        if (msgDetails.length > i && !StringUtils.isBlank(msgDetails[i])){
            return amountFormat(msgDetails[i]);
        }
        return null;
    }

    private static String[] getArrayValueByIndex(String[] msgDetails, int i) {
        if (msgDetails.length > i && !StringUtils.isBlank(msgDetails[i])){
            return msgDetails[i].split(",");
        }
        return null;
    }

    private static List<String> getListValueByIndex(String[] msgDetails, int i) {
        if (msgDetails.length > i && !StringUtils.isBlank(msgDetails[i])){
            return Arrays.asList(msgDetails[i].split(","));
        }
        return null;
    }
}
