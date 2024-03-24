package commands.implementations;

import events.Event;
import user.User;

public class SMSCommand  {
    NotificationDetails details;
    User reciever;
    String message;
    String link;
    String templateId;//HELLO <FIRSTNAME>, It's great to see you are back playing <BOARD_GAME_TYPE>!
    String templateString;

    public SMSCommand(Event event) {
        details = new NotificationDetails( event.getUser(), event.getMessage());
    }

    public NotificationDetails getDetails() {
        return details;
    }

    public String getMessage() {
        return details.getMessage();
    }

    public User getReciever() {
        return details.getReciever();
    }
}
