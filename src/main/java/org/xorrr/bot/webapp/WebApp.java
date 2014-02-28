package org.xorrr.bot.webapp;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.Inject;

public class WebApp {

    private Server server;
    private ServletContextHandler context;

    @Inject
    public WebApp(Server server, ServletContextHandler context) {
        this.server = server;
        this.context = context;
    }

    public void launchServer() {
        context.setContextPath("/");
    }

}
