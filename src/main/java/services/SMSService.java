package services;

import commands.implementations.NotificationDetails;
import commands.implementations.SMSCommand;
import user.User;

public class SMSService {
    public void sendSMS(User user, String message ) {
        //todo: mail is sent here
    }
    public Void send(SMSCommand command){
        sendSMS( command.getReciever(), command.getMessage());
        return null;
    }
}
