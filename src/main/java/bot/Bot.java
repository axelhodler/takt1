package bot;

import org.pircbotx.PircBotX;
import org.pircbotx.hooks.Listener;
import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.events.MessageEvent;

public class Bot extends ListenerAdapter implements Listener {
    
    private static ConfigHelper configHelper;
    private TitleGrabber titleGrabber = new TitleGrabber();
    private UrlGrabber urlGrabber = new UrlGrabber();
    
    public void launchBot() throws Exception {
		
	configHelper = new ConfigHelper();
	
	PircBotX bot = new PircBotX();
	
	bot.setName(configHelper.getBotName());
	bot.setLogin(configHelper.getIdentName());
	bot.setVerbose(true);
	
	bot.getListenerManager().addListener(new Bot());
	
	bot.connect(configHelper.getServer());
	
	bot.identify(configHelper.getIdentifyPassword());
	// sleep 5secs before joining the channel
	Thread.sleep(5000);
	bot.joinChannel(configHelper.getChannel());
    }
    
    @Override
    public void onMessage(MessageEvent event) throws Exception {
	String message = event.getMessage();
	String url = urlGrabber.grabUrl(message);
	
	if (url != null) {
	    String title = titleGrabber.grabTitle(url);
	    if (title != null) {
		event.getBot().sendMessage(event.getChannel(), title);
	    }
	}
    }
}
