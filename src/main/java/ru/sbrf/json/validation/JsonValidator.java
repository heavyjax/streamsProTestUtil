package ru.sbrf.json.validation;

import ru.sbrf.db.model.EvntMsg;
import ru.sbrf.json.model.JsonMessage;

public class JsonValidator {
    public boolean validate(final JsonMessage jsonMessage, final EvntMsg evntMsg) {
        boolean isValid = false;

        System.out.println("JSON message validation started");
        System.out.println("=================================================================================");

        isValid = evntMsg.getId() == jsonMessage.getTable().getId() &&
                evntMsg.getUsageActionOid() == jsonMessage.getTable().getUsageActionOid() &&
                evntMsg.getMsgTemplate() == jsonMessage.getTable().getMessageTemplate() &&
                evntMsg.getPriority() == jsonMessage.getTable().getPriority() &&
                evntMsg.getGroupNumber() == jsonMessage.getTable().getGroupNumber() &&
                (evntMsg.getAddressData().equals(jsonMessage.getTable().getAddressData())) &&
                (evntMsg.getDeliveryChannel().equals(jsonMessage.getTable().getDeliveryChannel())) &&
                (evntMsg.getCode().equals(jsonMessage.getTable().getCode())) &&
                (evntMsg.getDateFrom() != null ? evntMsg.getDateFrom().equals(jsonMessage.getTable().getDateFrom()) : evntMsg.getDateFrom() == jsonMessage.getTable().getDateFrom()) &&
                (evntMsg.getDateTo() != null ? evntMsg.getDateTo().equals(jsonMessage.getTable().getDateTo()) : evntMsg.getDateTo() == jsonMessage.getTable().getDateTo()) &&
                (evntMsg.getMessageDetails().equals(jsonMessage.getTable().getMessageDetails())) &&
                (getMessageString(evntMsg).equals(jsonMessage.getTable().getMessageString())) &&
                (evntMsg.getStatus().equals(jsonMessage.getTable().getStatus())) &&
                (evntMsg.getSendingChannel().equals(jsonMessage.getTable().getSendingChannel())) &&
                (evntMsg.getSendingDate() != null ? evntMsg.getSendingDate().equals(jsonMessage.getTable().getSendingDate()) : evntMsg.getSendingDate() == jsonMessage.getTable().getSendingDate()) &&
                (evntMsg.getSendingDetails().equals(jsonMessage.getTable().getSendingDetails())) &&
                (evntMsg.getRefNumber().equals(jsonMessage.getTable().getRefNumber())) &&
                (evntMsg.getSubject().equals(jsonMessage.getTable().getSubject()));

        if (isValid) {
            System.out.println("JSON message is valid");
            System.out.println("=================================================================================");
        } else {
            System.out.println("JSON message is not valid");
            System.out.println("TEST FAILED...");
            System.out.println("=================================================================================");
        }

        return  isValid;
    }

    private String getMessageString(final EvntMsg evntMsg) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(evntMsg.getMessageString1());
        stringBuilder.append(evntMsg.getMessageString2());
        stringBuilder.append(evntMsg.getMessageString3());
        stringBuilder.append(evntMsg.getMessageString4());
        stringBuilder.append(evntMsg.getMessageString5());
        stringBuilder.append(evntMsg.getMessageString6());
        return stringBuilder.toString();
    }
}
