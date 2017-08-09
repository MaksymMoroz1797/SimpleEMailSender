package sender;

import org.apache.log4j.Logger;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Moroz on 08.08.2017.
 */
public class Sender implements Runnable {

    public static Sender factory(String from, Authenticator auth, Properties properties) {
        if (auth == null || properties == null)
            throw new NullPointerException();
        Sender instance = new Sender(from, auth, properties);
        return instance;
    }

    private final Authenticator auth;

    private final Properties properties;

    private final String from;

    private String to = null;

    private String subject = null;

    private String content = null;

    private Sender(String from, Authenticator auth, Properties properties) {
        this.from = from;
        this.auth = auth;
        this.properties = (Properties) properties.clone();
    }

    public void setMessage(String to, String subject, String content) {
        if (to == null || subject == null || content == null)
            throw new NullPointerException();
        this.to = to;
        this.subject = subject;
        this.content = content;
    }

    @Override
    public void run() {
        if (to == null || subject == null || content == null)
            return;
        Session session = Session.getDefaultInstance(properties, auth);
        Message message = new MimeMessage(session);
        InternetAddress fAdress = null;
        InternetAddress tAdress = null;
        try {
            fAdress = new InternetAddress(from);
            tAdress = new InternetAddress(to);
        } catch (AddressException e) {
            System.err.println(e);
        }
        try {
            message.setFrom(fAdress);
            message.setRecipient(Message.RecipientType.TO, tAdress);
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println(e);
        }
    }
    public static void main(String[] args) {
        final Logger log = Logger.getLogger(Sender.class); log.info("1111111111111111111111111111111");
        ResourceBundle resources = ResourceBundle.getBundle("properties", Locale.ENGLISH);
        String login = resources.getString("mail.user.login");
        String password = resources.getString("mail.user.password");
        Authenticator auth = new MyAuthenticator(login, password);
        String smtpHost = resources.getString("mail.smtp.host");
        String smtpPort = resources.getString("mail.smtp.port");
        String ssl = resources.getString("mail.smtp.ssl");
        Properties props = new Properties();
        props.put("mail.smtp.port", smtpPort);
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.ssl.enable", ssl);
        props.put("mail.smtp.auth", "true");
        props.put("mail.mime.charset", "UTF-8");
        String adressTo = login;
        String subject = "Test";
        String content = "Hello!";
        Sender sender = Sender.factory("maximuss12435@gmail.com", auth, props);
        sender.setMessage(adressTo, subject, content);
        //new Thread(sender).start();
    }
}
