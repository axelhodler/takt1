package org.xorrr.bot.webapp;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Before;
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

    private WebApp webApp;

    @Before
    public void setUp() {
        webApp = new WebApp(server, context);
    }

    @Test
    public void contextPathIsSet() {
        webApp.launchServer();

        verify(context, times(1)).setContextPath("/");
    }

    @Test
    public void handlerIsSet() {
        webApp.launchServer();

        verify(server, times(1)).setHandler(context);
    }

    @Test
    public void servletIsSet() {
        webApp.launchServer();

        //TODO see PowerMock for mocking constructors
        verify(context, times(1)).addServlet(any(ServletHolder.class), anyString());
    }
}
