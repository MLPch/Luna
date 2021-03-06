package horse.boo.bot.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Nonnull;
import java.awt.*;
import java.time.OffsetDateTime;

public class MemberLeaveEvent extends ListenerAdapter {

    @Value("${channel.JoinAndLeaveChannel}")
    private long joinAndLeaveChannel;

    @Override
    @Deprecated
    public void onGuildMemberRemove(@Nonnull GuildMemberRemoveEvent event) {
        boolean stopped = true;
        User user = event.getUser();
        String img = user.getEffectiveAvatarUrl();
        String ping = user.getAsMention();
        TextChannel channel = event.getGuild().getTextChannelById(joinAndLeaveChannel);

        EmbedBuilder eb = new EmbedBuilder();
        eb.addField("Maybe it's for the best?", "", true);
        eb.setColor(Color.black);
        eb.setThumbnail(img);
        eb.setTimestamp(OffsetDateTime.now());
        eb.setFooter("Rolled out of the IT.Pony", event.getGuild().getIconUrl());


        while (stopped) {
            try {
                Thread.sleep(700);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            assert channel != null;
            channel.sendMessage(ping + " left us!").setEmbeds(eb.build()).queue();
            stopped = false;
        }

    }
}