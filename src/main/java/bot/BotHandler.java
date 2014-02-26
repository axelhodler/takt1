package bot;

import org.pircbotx.hooks.ListenerAdapter;
import org.pircbotx.hooks.types.GenericMessageEvent;

public class BotHandler extends ListenerAdapter {

    @Override
    public void onGenericMessage(final GenericMessageEvent event)
            throws Exception {
        event.getMessage();
    }
}
