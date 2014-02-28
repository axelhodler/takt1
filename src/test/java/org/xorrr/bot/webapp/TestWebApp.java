package org.xorrr.bot.webapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TestWebApp {

    @Mock
    Server server;
    @Mock
    ServletContextHandler context;

    @Test
    public void contextPathIsSet() {
        WebApp webApp = new WebApp(server, context);

        webApp.launchServer();

        verify(context, times(1)).setContextPath("/");
    }

}
