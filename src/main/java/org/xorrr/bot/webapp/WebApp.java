package org.xorrr.bot.webapp;

import javax.servlet.http.HttpServlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.google.inject.Inject;

public class WebApp extends HttpServlet {

    private static final long serialVersionUID = -7961354394795268555L;

    private Server server;
    private ServletContextHandler context;

    @Inject
    public WebApp(Server server, ServletContextHandler context) {
        this.server = server;
        this.context = context;
    }

    public void launchServer() {
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(this), "/*");
    }

}
