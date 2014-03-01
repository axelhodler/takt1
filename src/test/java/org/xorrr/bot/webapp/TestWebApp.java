package org.xorrr.bot.webapp;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Server.class)
public class TestWebApp {

    Server server;

    @Mock
    HttpServletRequest req;
    @Mock
    HttpServletResponse resp;
    @Mock
    PrintWriter pw;
    @Mock
    ServletContextHandler context;

    private WebApp webApp;

    @Before
    public void setUp() throws Exception {
        server = PowerMockito.mock(Server.class);

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

        //TODO not properly tested
        verify(context, times(1)).addServlet(any(ServletHolder.class), anyString());
    }

    @Test
    public void serverIsStarted() throws Exception {
        webApp.launchServer();

        verify(server, times(1)).start();
    }

    @Test
    public void serverIsJoined() throws Exception {
        webApp.launchServer();

        verify(server, times(1)).join();
    }

    @Test
    public void getRequestReturnsSomething() throws Exception {
        when(resp.getWriter()).thenReturn(pw);

        webApp.doGet(req, resp);

        verify(pw, times(1)).print(":)");
    }
}
