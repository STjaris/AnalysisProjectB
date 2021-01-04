package com.vijfmusketiers.ProjectB.messageQueue.emergency.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.vijfmusketiers.ProjectB.emergencyContact.EmergencyContact;
import com.vijfmusketiers.ProjectB.emergencyContact.repository.EmergencyContactRepository;

public class EmergencyService {

    private static final String ACCOUNT_SID = "ACb25f88c5ffb5f1bafba933392e566b1c";
    private static final String AUTH_TOKEN = "5151858654826e5175b65b5ac8654a90";

    public void send(String client, String phoneNumber, String messageSMS, String xCo, String yCo, boolean sendSMS) {

        System.out.println(phoneNumber);
        System.out.println(messageSMS);
        System.out.println(yCo);
        System.out.println(xCo);

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        System.out.println("WERKT!");

        String bodySMS = String.format("Client: %s \nMessage: %s \ncoordinates: https://www.google.com/maps/search/?api=1&query=%s,%s", client, messageSMS, xCo, yCo);
        System.out.println(bodySMS);

        if (sendSMS) {
            Message message = Message
                    .creator(new PhoneNumber(phoneNumber), // to
                            new PhoneNumber("+12067757092"), // from
                            bodySMS)
                    .create();

            System.out.println(message.getSid());
        }
    }
}