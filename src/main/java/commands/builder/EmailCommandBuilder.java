package commands.builder;

import commands.implementations.EmailCommand;
import user.User;

public class EmailCommandBuilder {
    NotificationBuilder notificationBuilder = new NotificationBuilder();
    String link;
    String templateId;//HELLO <FIRSTNAME>, It's great to see you are back playing <BOARD_GAME_TYPE>!
    String templateString;
    public EmailCommandBuilder link( String ling ){
        this.link = link;
        return this;
    }
    public EmailCommand build(){
        //complex logic like handling user,etc, can be handled here
        return new EmailCommand( notificationBuilder.build(), link );
    }
    public EmailCommandBuilder user( User user ){
        notificationBuilder.user(user);
        return this;
    }
    public EmailCommandBuilder message( String message ){
        notificationBuilder.message(message);
        return this;
    }
}
