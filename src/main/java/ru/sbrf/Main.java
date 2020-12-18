package ru.sbrf;


import org.apache.kafka.common.protocol.types.Field;
import ru.sbrf.db.dao.MonitoringDAO;
import ru.sbrf.db.dao.Way4DAO;
import ru.sbrf.db.dto.EvntMsgDTO;
import ru.sbrf.db.dto.Way4EventDTO;
import ru.sbrf.db.model.EvntMsg;
import ru.sbrf.db.model.Way4Event;
import ru.sbrf.file.FileReader;
import ru.sbrf.json.JsonHelper;
import ru.sbrf.json.model.JsonMessage;
import ru.sbrf.json.validation.JsonValidator;
import ru.sbrf.kafka.consumer.MessageReceiver;
import ru.sbrf.kafka.producer.MessageSender;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Main {
    private static final String configPath = "C:\\Users\\Maxim\\Google Диск\\Dev\\StreamsProTestUtil\\config.properties";
    private static final String jsonFilePath = "C:\\Users\\Maxim\\Google Диск\\Dev\\StreamsProTestUtil\\Message.json";

    public static void main(String[] args) {

//        if (args.length != 4 || !args[0].equals("-cf") || !args[2].equals("-jp")) {
//            System.out.println("Please, specify path to config.properties file using -cp flag");
//            System.out.println("Please, specify path to json message file using -jp flag");
//            System.exit(0);
//        }
//
//        String configPath = args[1];
//        String jsonFilePath = args[3];

        try {
//            Way4DAO way4DAO = new Way4DAO("C:\\Users\\Maxim\\Google Диск\\Dev\\StreamsProTestUtil\\config.properties");
//            way4DAO.insertEvntMsg();

            EvntMsgDTO evntMsgDTO = new EvntMsgDTO("C:\\Users\\Maxim\\Google Диск\\Dev\\StreamsProTestUtil\\config.properties");
            EvntMsg evntMsg = evntMsgDTO.getEvntMsg();

            Way4EventDTO way4EventDTO = new Way4EventDTO();
            Way4Event way4Event = way4EventDTO.getWay4Event(evntMsg);

            JsonHelper jsonHelper = new JsonHelper();
            String kafkaMessage = jsonHelper.getJsonKafkaMessage(way4Event, "uiduiduid", "node1");

            //String kafkaMessage1 = "{\"requestChainUid\":\"uiduiduid\",\"nodeIdent\":\"node1\",\"table\":{\"id\":23999,\"usageActionOid\":null,\"addressData\":\"null\",\"deliveryChannel\":\"KF050-STATUS\",\"code\":\"null\",\"dateFrom\":\"01.01.19\",\"dateTo\":null,\"messageDetails\":null,\"messageString\":null,\"messageTemplate\":null,\"status\":\"W\",\"sendingChannel\":null,\"sendingDate\":null,\"sendingDetails\":null,\"refNumber\":null,\"subject\":null,\"priority\":null,\"groupNumber\":null,\"nodeIdent\":\"node1\"},\"operation\":{\"type\":\"LA\",\"cardNumber\":\"0910-P-1382878998\",\"tranAmount\":null,\"tranCurrency\":null,\"tranTime\":\"2020-04-16 15:28:14\",\"authCode\":null,\"tranType\":null,\"replyCode\":null,\"merchant\":null,\"authType\":null,\"commissionAmount\":null,\"tranId\":null,\"sourceRegNum\":null,\"sourceNumber\":null,\"sicCode\":null,\"docId\":null,\"paymentId\":null,\"postpone\":null,\"cardCreditLimit\":null,\"lockoutCode\":null,\"stlAmount\":null,\"stlCurrency\":null,\"refundAmount\":null,\"refundCurrency\":null,\"docPrevId\":null,\"tranTypeId\":null,\"sourceChannel\":null,\"countryCode\":null},\"card\":{\"cardBalance\":null,\"cardCurrency\":null,\"rbsNumber\":null,\"cardStatus\":\"Q\",\"prevCardNumber\":null,\"product\":null,\"cardExpire\":null,\"contractNumber\":null,\"tokenReferenceID\":null,\"cbCode\":null,\"cardDateOpen\":null,\"addInfo\":null,\"cardDateClose\":null,\"contractId\":null,\"level\":null,\"trLastName\":null,\"trFirstName\":null},\"message\":null,\"client\":{\"clientITN\":\"129871927987324\",\"clientWayId\":\"8719279172937nullnullnullnullnull\",\"clientWayDate\":null,\"cardsClient\":[],\"lastName\":null,\"firstName\":null,\"middleName\":null,\"birthDate\":null,\"birthPlace\":null,\"regNumber\":null,\"regNumberDetails\":null,\"gender\":null,\"type\":null,\"companyName\":null,\"profession\":null,\"email\":null,\"mobilePhone\":null,\"homePhone\":null,\"workPhone\":null,\"cbCode\":null}}";

            MessageSender messageSender = new MessageSender(configPath);
            messageSender.sendMessage("W4S.BALANCE", kafkaMessage, "AP");

            Callable<String> callable = new MessageReceiver(configPath);
            FutureTask<String> future = new FutureTask<>(callable);
            new Thread(future).start();
            String jsonMessageFromKafka = future.get();

            if (jsonMessageFromKafka != null) {
                String jsonFromKafka = jsonHelper.exclude(jsonMessageFromKafka, "requestChainUid");
                FileReader fr = new FileReader(jsonFilePath);
                String jsonMesageFromFile = fr.readFile(jsonFilePath);
                String jsonFromFile = jsonHelper.exclude(jsonMesageFromFile, "requestChainUid");

                boolean messagesAreEquals = jsonHelper.compareJsonMessages(jsonFromKafka, jsonFromFile);

                if (messagesAreEquals) {
                    System.out.println("TEST SUCCESS...");
                    System.out.println("=================================================================================");
                } else {
                    System.out.println("TEST FAILED...");
                    System.out.println("=================================================================================");
                }
            }

//            boolean jsonMessageisValid = false;
//
//            if (receivedKafkaMessage != null) {
//                JsonMessage jsonMessage = jsonHelper.getObjectFromKafkaMessage(receivedKafkaMessage);
//                JsonValidator jsonValidator = new JsonValidator();
//                jsonMessageisValid = jsonValidator.validate(jsonMessage, evntMsg);
//            }
//
//            if (jsonMessageisValid) {
//                MonitoringDAO monitoringDAO = new MonitoringDAO(configPath);
//                monitoringDAO.selectEvntMetaInfo();
//            }
        } catch (Exception e) {
            System.out.println("TEST FAILED...");
            System.out.println("=================================================================================");
            e.printStackTrace();
        }
    }


}
