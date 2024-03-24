package commands.implementations;

import events.Event;
import user.User;

public class EmailCommand {
    public NotificationDetails details;
    String link;
    String templateId;//HELLO <FIRSTNAME>, It's great to see you are back playing <BOARD_GAME_TYPE>!
    String templateString;

    public EmailCommand(NotificationDetails notificationDetails, String link) {
        details = notificationDetails;
    }

    public EmailCommand(Event event) {
        details = new NotificationDetails( event.getUser(), event.getMessage());
        this.link = event.getLink();
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
