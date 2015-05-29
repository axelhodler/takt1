package org.xorrr.bot.webapp;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class BotWebInterface extends HttpServlet {

    private static final long serialVersionUID = -7961354394795268555L;

    private Server server;
    private ServletContextHandler context;

    public BotWebInterface(Server server, ServletContextHandler context) {
        this.server = server;
        this.context = context;
    }

    public void launchServer() {
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(this), "/*");
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        resp.getWriter().print(":)");
    }
}
