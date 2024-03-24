package commands.builder;

import commands.implementations.NotificationDetails;
import commands.implementations.SMSCommand;
import user.User;

public class SMSCommandBuilder {
    NotificationBuilder notificationBuilder = new NotificationBuilder();
    String link;
    String templateId;//HELLO <FIRSTNAME>, It's great to see you are back playing <BOARD_GAME_TYPE>!
    String templateString;
    public SMSCommandBuilder user( User user ){
        notificationBuilder.user(user);
        return this;
    }
    public SMSCommandBuilder message( String message ){
        notificationBuilder.message(message);
        return this;
    }
}
