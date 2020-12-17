package ru.sbrf;


import ru.sbrf.db.dao.MonitoringDAO;
import ru.sbrf.db.dao.Way4DAO;
import ru.sbrf.db.dto.EvntMsgDTO;
import ru.sbrf.db.model.EvntMsg;
import ru.sbrf.file.FileReader;
import ru.sbrf.json.JsonHelper;
import ru.sbrf.json.model.JsonMessage;
import ru.sbrf.json.validation.JsonValidator;
import ru.sbrf.kafka.consumer.MessageReceiver;
import ru.sbrf.kafka.producer.MessageSender;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Main {
    //private static final String configPath = "C:\\Users\\Maxim\\Google Диск\\Dev\\StreamsProTestUtil\\config.properties";
    //private static final String jsonFilePath = "C:\\Users\\Maxim\\Google Диск\\Dev\\StreamsProTestUtil\\Message.json";

    public static void main(String[] args) {

        if (args.length != 4 || !args[0].equals("-cf") || !args[2].equals("-jp")) {
            System.out.println("Please, specify path to config.properties file using -cp flag");
            System.out.println("Please, specify path to json message file using -jp flag");
            System.exit(0);
        }

        String configPath = args[1];
        String jsonFilePath = args[3];

        try {
//            Way4DAO way4DAO = new Way4DAO("C:\\Users\\Maxim\\Google Диск\\Dev\\StreamsProTestUtil\\config.properties");
//            way4DAO.insertEvntMsg();

            EvntMsgDTO evntMsgDTO = new EvntMsgDTO("C:\\Users\\Maxim\\Google Диск\\Dev\\StreamsProTestUtil\\config.properties");
            EvntMsg evntMsg = evntMsgDTO.getEvntMsg();

            JsonHelper jsonHelper = new JsonHelper();
            String kafkaMessage = jsonHelper.getJsonKafkaMessage(evntMsg);

            //String kafkaMessage = "{\"table\":{\"id\":21999,\"usageActionOid\":635882160030,\"addressData\":\"null\",\"deliveryChannel\":\"SBRF_I\",\"code\":\"null\",\"dateFrom\":\"2019-01-01 00:00:00\",\"dateTo\":\"2012-12-12 00:00:00\",\"messageDetails\":\"null\",\"messageString\":\"I^5336690143307272^2019-07-20 18:32:30^2^^nullnullnullnullnull\",\"messageTemplate\":2744711940,\"status\":\"W\",\"sendingChannel\":\"null\",\"sendingDate\":null,\"sendingDetails\":\"null\",\"refNumber\":\"null\",\"subject\":\"X\",\"priority\":0,\"groupNumber\":0,\"nodeIdent\":null},\"operation\":{\"type\":\"I\",\"cardNumber\":\"5336690143307272\",\"tranAmount\":null,\"tranCurrency\":null,\"tranTime\":\"2019-07-20 18:32:30\",\"authCode\":null,\"tranType\":null,\"replyCode\":null,\"merchant\":null,\"authType\":null,\"commissionAmount\":null,\"tranId\":null,\"sourceRegNum\":null,\"sourceNumber\":null,\"sicCode\":null,\"docId\":null,\"paymentId\":null,\"postpone\":null,\"cardCreditLimit\":null,\"lockoutCode\":null},\"card\":{\"cardBalance\":null,\"cardCurrency\":null,\"rbsNumber\":\"\",\"cardStatus\":\"\",\"prevCardNumber\":\"\",\"product\":\"\",\"cardExpire\":\"\",\"contractNumber\":\"\",\"tokenReferenceID\":null},\"message\":{\"msg\":null,\"msgType\":\"2\",\"mbcCardList\":[\"\"]},\"client\":{\"clientITN\":\"\",\"clientWayId\":\"\",\"cardsClient\":null,\"docNumber\":null,\"lastNamePrev\":null,\"lastNameCurr\":null,\"firstNamePrev\":null,\"firstNameCurr\":null,\"middleNamePrev\":null,\"middleNameCurr\":null,\"birthDatePrev\":null,\"birthDateCurr\":null}}";

            MessageSender messageSender = new MessageSender(configPath);
            messageSender.sendMessage("W4S.BALANCE", kafkaMessage, "AP");

            Callable<String> callable = new MessageReceiver(configPath);
            FutureTask<String> future = new FutureTask<>(callable);
            new Thread(future).start();
            String jsonMessageFromKafka = future.get();

            if (jsonMessageFromKafka != null) {
                FileReader fr = new FileReader(jsonFilePath);
                String jsonMesageFromFile = fr.readFile(jsonFilePath);

                boolean messagesAreEquals = jsonHelper.compareJsonMessages(jsonMessageFromKafka, jsonMesageFromFile);

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
