package user;

import java.util.concurrent.TimeUnit;

public class User {
    private String ID;
    long lastActiveTime;
    public boolean activeAfter(int threshold, TimeUnit timeUnit) {
        return System.currentTimeMillis() - lastActiveTime > timeUnit.toMillis(threshold);
    }

}
