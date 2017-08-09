package controller;

import sender.MyAuthenticator;
import sender.Sender;

import javax.mail.Authenticator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * Created by Moroz on 08.08.2017.
 */
@WebServlet("/SendMessage")
public class SendContriller extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        final Logger log = Logger.getLogger("Servlet");
        log.info("In SendControllet servlet");
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

        String adressTo = request.getParameter("adress");
        String subject = request.getParameter("subject");
        String content = request.getParameter("msg");
        Sender sender = Sender.factory(login, auth, props);
        log.info(adressTo + "\n" + subject + "\n" + content);
        sender.setMessage(adressTo, subject, content);
        new Thread(sender).start();
        try {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (ServletException e) {
            log.error(e);
        } catch (IOException e) {
            log.error(e);
        }
    }


}
