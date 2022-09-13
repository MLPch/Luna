package horse.boo.bot.events;

import horse.boo.database.config.SqlDatasourceConfig;
import horse.boo.database.model.Pony;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import horse.boo.database.repository.PonyRepository;

import java.util.Optional;

public class BadWordsCollectorEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        if (event.getMessage().getContentRaw().equals("/ScanBW") &&
                event.getMessage().getAuthor().getIdLong() == 320332718921482241L) {
            System.out.println("Scanning in progress....................");
            event.getChannel().sendMessage("Scanning in progress....................").complete();

//
//            DataSource dataSource = DatasourceConfig.createDataSource();
//
//            DSL.using(dataSource, SQLDialect.POSTGRES).insertInto(Ponies.PONIES).set(Ponies.PONIES.PONIES_ID, 6).set(Ponies.PONIES.PONIES_NAME, "Jira2").set(Ponies.PONIES.PONIES_AGE, 213).execute();
//
//
            PonyRepository repository = SqlDatasourceConfig.ponyRepository();
            repository.addNextLineInBase(repository.findAll().size() + 1, "Pony", repository.findAll().size() + 2);
//            List<Pony> ponies = repository.findAll();
//            ponies.forEach(System.out::println);
//
            Optional<Pony> pony = repository.findById("33");
            System.out.println(pony.toString());
            event.getChannel().sendMessage(repository.findAll().toString()).complete();
            event.getChannel().sendMessage(pony.toString()).complete();
        }

    }
}
