package horse.boo.bot.services;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Objects;

@Component
public class RoleSelectService extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Choose your programming languages.");
        eb.addField("Please, choose only those languages that you have mastered at the junior+ level.",
                "", true);
        eb.setColor(Color.BLUE);
        EmbedBuilder ebb = new EmbedBuilder();
        ebb.setTitle("Choose your direction.");
        ebb.addField("Please select the areas in which you are developing.",
                "Choose no more than three, or choose none.", true);
        ebb.setColor(Color.BLUE);

        if (event.getMessage().getContentRaw().equals("/Create") &&
                event.getMessage().getAuthor().getIdLong() == 320332718921482241L) {
            event.getChannel().sendMessage("Линк на эту конфу —\n" +
                    "https://discord.gg/XsEGAzj6fq").queue();
            event.getChannel().sendMessage(" ").setEmbeds(eb.build()).addActionRow(
                    Button.secondary("1", "Java/Kotlin"),
                    Button.secondary("2", "С/С++"),
                    Button.secondary("3", "Python"),
                    Button.secondary("4", "JS/TS"),
                    Button.secondary("5", "C#")).addActionRow(
                    Button.secondary("6", "Ruby"),
                    Button.secondary("7", "Go"),
                    Button.secondary("8", "Scala"),
                    Button.secondary("9", "PHP"),
                    Button.secondary("10", "SQL")
            ).queue();
            event.getChannel().sendMessage(" ").setEmbeds(ebb.build())
                    .addActionRow(
                            Button.secondary("101", "Frontend"),
                            Button.secondary("102", "Backend"),
                            Button.secondary("103", "Gamedev/Unity"),
                            Button.secondary("104", "Android"),
                            Button.secondary("105", "FinTech/Banking"))
                    .addActionRow(
                            Button.secondary("106", "DevOps"),
                            Button.secondary("107", "QA"),
                            Button.secondary("108", "Gamedev/Unreal"),
                            Button.secondary("109", "FullStack"),
                            Button.secondary("1010", "iOS"))
                    .addActionRow(
                            Button.secondary("1011", "InfoSec"),
                            Button.secondary("1012", "ML/Data"),
                            Button.secondary("1013", "IoT"),
                            Button.secondary("1014", "Web designer (UI/UX)"),
                            Button.secondary("1015", "System Administrator")
                    ).queue();
        }
    }

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (event.getComponentId().equals(Objects.requireNonNull(event.getButton()).getId()) &&
                !(Objects.requireNonNull(event.getMember()).getRoles().contains(Objects.requireNonNull(
                        event.getGuild()).getRolesByName(event.getButton().getLabel(), true).get(0)))) {
            Objects.requireNonNull(event.getGuild()).addRoleToMember(Objects.requireNonNull(event.getMember()),
                    event.getGuild().getRolesByName(event.getButton().getLabel(), true).get(0)).queue();
            event.reply("Welcome to the " + event.getButton().getLabel()).setEphemeral(true).queue();
        } else if (event.getComponentId().equals(Objects.requireNonNull(event.getButton()).getId()) &&
                (Objects.requireNonNull(event.getMember()).getRoles().contains(Objects.requireNonNull(
                        event.getGuild()).getRolesByName(event.getButton().getLabel(), true).get(0)))) {
            event.getGuild().removeRoleFromMember(Objects.requireNonNull(event.getMember()),
                    event.getGuild().getRolesByName(event.getButton().getLabel(), true).remove(0)).queue();
            event.reply("Delete " + event.getButton().getLabel()).setEphemeral(true).queue();
        }
    }
}
