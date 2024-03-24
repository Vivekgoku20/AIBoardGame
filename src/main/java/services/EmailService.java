package services;

import commands.implementations.EmailCommand;
import commands.implementations.NotificationDetails;
import user.User;

public class EmailService {

    public void sendEmail( User user, String message ) {
        //todo: mail is sent here
    }
    public Void send(EmailCommand command){
        sendEmail( command.getReciever(), command.getMessage());
        return null;
    };
}
