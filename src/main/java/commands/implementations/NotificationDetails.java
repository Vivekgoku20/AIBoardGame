package commands.implementations;

import user.User;

public class NotificationDetails {
    User receiver;
    String message;

    public NotificationDetails(User user, String message) {
        this.receiver = user;
        this.message = message;
    }

    public User getReciever(){
        return receiver;
    }
    public String getMessage(){
        return message;
    }
}
