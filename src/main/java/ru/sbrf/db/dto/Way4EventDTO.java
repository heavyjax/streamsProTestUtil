package ru.sbrf.db.dto;

import ru.sbrf.db.model.EvntMsg;
import ru.sbrf.db.model.Way4Event;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Way4EventDTO {
    private DateFormat dateFormat;

    public Way4EventDTO() {
        dateFormat = new SimpleDateFormat("dd.MM.yy");
    }

    public Way4Event getWay4Event(EvntMsg evntMsg) {

        return new Way4Event(
                evntMsg.getId(),
                evntMsg.getAddressData(),
                evntMsg.getDeliveryChannel(),
                evntMsg.getCode(),
                dateFormat.format(evntMsg.getDateFrom()),
                evntMsg.getMessageString1(),
                evntMsg.getMessageString2(),
                evntMsg.getMessageString3(),
                evntMsg.getMessageString4(),
                evntMsg.getMessageString5(),
                evntMsg.getMessageString6(),
                evntMsg.getStatus()
        );
    }
}
