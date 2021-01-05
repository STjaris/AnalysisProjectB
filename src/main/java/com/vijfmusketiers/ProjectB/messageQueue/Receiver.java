package com.vijfmusketiers.ProjectB.messageQueue;

import com.vijfmusketiers.ProjectB.emergencyContact.EmergencyContact;
import com.vijfmusketiers.ProjectB.emergencyContact.service.EmergencyContactServiceInterface;
import com.vijfmusketiers.ProjectB.messageQueue.emergency.service.EmergencyService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.CountDownLatch;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    private final EmergencyService emergencyService = new EmergencyService();
    private final EmergencyContactServiceInterface emergencyContactServiceInterface;

    public Receiver(EmergencyContactServiceInterface emergencyContactServiceInterface) {
        this.emergencyContactServiceInterface = emergencyContactServiceInterface;
    }

    @RabbitListener(queues = "emergency")
    public void receiveMessage(byte[] message) throws ParseException {
        JSONObject messageInJson = this.ByteToJson(message);
        byte[] emergency = messageInJson.getAsString("emergency").getBytes();
        // Get user ID
        String userID = this.ByteToJson(emergency).getAsString("user_id");

        // Get Message
        String messageSMS = this.ByteToJson(emergency).getAsString("message");

        // Get getSMSTrueOrFalse
        boolean sendSMS = Boolean.parseBoolean(this.ByteToJson(emergency).getAsString("sendSMS"));

        System.out.println(sendSMS);

        // Get Route coords
        byte[] route = this.ByteToJson(emergency).getAsString("route").getBytes();
        String yCo = this.ByteToJson(route).getAsString("yCo");
        String xCo = this.ByteToJson(route).getAsString("xCo");

        List<EmergencyContact> ECLst = emergencyContactServiceInterface.getAllEmergencyContactsByUserId(Long.parseLong(userID));
        ECLst.sort(Comparator.comparing(EmergencyContact::getOrderBy));

        int i = 0;
        for (EmergencyContact ec : ECLst) {
            if(ec.getAvailable() == 1) {
                i++;
                if (ec.getOrderBy() <= i || i == 1) {
                    // Get EmergencyContact from the User.
                    String phoneNumber = ec.getPhoneNumber();

                    // Get client name
                    String client = String.format("%S %S", ECLst.get(i).getUser().getFirstName(), ECLst.get(i).getUser().getLastName());

                    System.out.println(String.format("Received <%s>", messageInJson));

                    // send sms
                    emergencyService.send(client, phoneNumber, messageSMS, xCo, yCo, sendSMS);
                    latch.countDown();
                    break;
                }
            }
        }
    }


    public CountDownLatch getLatch() {
        return latch;
    }

    public JSONObject ByteToJson(byte[] message) throws ParseException {
        JSONParser parser = new JSONParser(JSONParser.MODE_JSON_SIMPLE);
        return (JSONObject) parser.parse(new String(message));
    }
}
