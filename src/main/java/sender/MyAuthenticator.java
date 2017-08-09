package sender;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Created by Moroz on 08.08.2017.
 */
public class MyAuthenticator extends Authenticator {
    private String user;
    private String password;

    public MyAuthenticator(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public PasswordAuthentication getPasswordAuthentication() {
        String user = this.user;
        String password = this.password;
        return new PasswordAuthentication(user, password);
    }
}