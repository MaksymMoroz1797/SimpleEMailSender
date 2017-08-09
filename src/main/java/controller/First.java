package controller;

import org.apache.log4j.Logger;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/FirstServlettest")
public class First extends HttpServlet {
    private final Logger log = Logger.getLogger("Servlet");
    public void init() throws ServletException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.info("In SendControllet servlet");
        response.setContentType("text/html");
        response.getWriter().print("This is " + this.getClass().getName()
                + ", using the GET method");
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.getWriter().print("This is " + this.getClass().getName()
                + ", using the POST method");
    }
    public void destroy() {
        super.destroy();
    }
}