package ru.sbrf.db.dto;

import ru.sbrf.db.model.EvntMsg;
import ru.sbrf.util.Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;

public class EvntMsgDTO {

    private final Properties fileProps;

    public EvntMsgDTO(final String configPath) {
        this.fileProps = Util.getProperties(configPath);
    }

    public EvntMsg getEvntMsg() throws ParseException {
        EvntMsg evntMsg = new EvntMsg();
        evntMsg.setId(Long.parseLong(fileProps.getProperty("ID")));
        evntMsg.setUsageActionOid(Long.parseLong(fileProps.getProperty("USAGE_ACTION_OID")));
        evntMsg.setAddressData(fileProps.getProperty("ADDRESS_DATA"));
        evntMsg.setDeliveryChannel(fileProps.getProperty("DELIVERY_CHANNEL"));
        evntMsg.setCode(fileProps.getProperty("CODE"));
        evntMsg.setDateFrom("null".equals(fileProps.getProperty("DATE_FROM")) ? null :
                new SimpleDateFormat("dd.MM.yy").parse(fileProps.getProperty("DATE_FROM")));
        evntMsg.setDateTo("null".equals(fileProps.getProperty("DATE_TO")) ? null :
                new SimpleDateFormat("dd.MM.yy").parse(fileProps.getProperty("DATE_TO")));
        evntMsg.setMessageDetails(fileProps.getProperty("MESSAGE_DETAILS"));
        evntMsg.setMessageString1(fileProps.getProperty("MESSAGE_STRING_1"));
        evntMsg.setMessageString2(fileProps.getProperty("MESSAGE_STRING_2"));
        evntMsg.setMessageString3(fileProps.getProperty("MESSAGE_STRING_3"));
        evntMsg.setMessageString4(fileProps.getProperty("MESSAGE_STRING_4"));
        evntMsg.setMessageString5(fileProps.getProperty("MESSAGE_STRING_5"));
        evntMsg.setMessageString6(fileProps.getProperty("MESSAGE_STRING_6"));
        evntMsg.setMsgTemplate(Long.parseLong(fileProps.getProperty("MSG_TEMPLATE")));
        evntMsg.setStatus(fileProps.getProperty("STATUS"));
        evntMsg.setSendingChannel(fileProps.getProperty("SENDING_CHANNEL"));
        evntMsg.setSendingDate("null".equals(fileProps.getProperty("SENDING_DATE")) ? null :
                new SimpleDateFormat("dd.MM.yy").parse(fileProps.getProperty("SENDING_DATE")));
        evntMsg.setSendingDetails(fileProps.getProperty("SENDING_DETAILS"));
        evntMsg.setRefNumber(fileProps.getProperty("REF_NUMBER"));
        evntMsg.setSubject(fileProps.getProperty("SUBJECT"));
        evntMsg.setPriority(Long.parseLong(fileProps.getProperty("PRIORITY")));
        evntMsg.setGroupNumber(Long.parseLong(fileProps.getProperty("GROUP_NUMBER")));
        evntMsg.setSubject(fileProps.getProperty("PARTITION_KEY"));
        return evntMsg;
    }
}
