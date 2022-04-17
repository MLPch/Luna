package horse.boo.bot.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BadWordsCollectorEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().equals("/ScanBW") &&
                event.getMessage().getAuthor().getIdLong() == 320332718921482241L) {
            System.out.println("Scanning in progress....................");
            event.getChannel().sendMessage("Scanning in progress....................").complete();
        }

    }
}
