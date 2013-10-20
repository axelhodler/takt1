import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import webapp.KeepWebappAliveThread;
import bot.Bot;

public class Main extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	    throws ServletException, IOException {
	resp.getWriter().print(":)");
    }

    public static void main(String[] args) throws Exception {

	Bot bot = new Bot();
	bot.launchBot();

	KeepWebappAliveThread t = new KeepWebappAliveThread();

	Server server = new Server(Integer.valueOf(System.getenv("PORT")));
	ServletContextHandler context = new ServletContextHandler(
		ServletContextHandler.SESSIONS);
	context.setContextPath("/");
	server.setHandler(context);
	context.addServlet(new ServletHolder(new Main()), "/*");
	server.start();
	server.join();
    }
}
