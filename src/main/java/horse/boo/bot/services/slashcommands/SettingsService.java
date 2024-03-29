package horse.boo.bot.services.slashcommands;


import horse.boo.bot.database.enums.Languages;
import horse.boo.bot.database.repository.ConfigRepository;
import horse.boo.bot.database.repository.LocaleRepository;
import horse.boo.bot.database.table.ConfigsTable;
import horse.boo.bot.services.enums.Options;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Component
public class SettingsService extends ListenerAdapter {
    //TODO: Вынести сюда все шаги по настройке.

    private final Logger logger = LoggerFactory.getLogger(SettingsService.class);
    private final ConfigRepository configRepository;
    private final LocaleRepository localeRepository;

    public SettingsService(ConfigRepository configRepository,
                           LocaleRepository localeRepository) {
        this.configRepository = configRepository;
        this.localeRepository = localeRepository;
    }

    /**
     * @param event - Срабатывает при вызове команды "languageSelect".
     *              Возвращает эмбед с кнопками для выбора языка видимый для всех.
     */
    public void languageSelect(@NotNull SlashCommandInteractionEvent event) {


        MessageEmbed eb = new EmbedBuilder()
                .setAuthor("Welcome to the Setup wizard!")
                .addField("To continue - please select a language.", "There are currently 4 languages available.", true)
                .setColor(Color.magenta)
                .build();

        event.reply("").setEmbeds(eb).addActionRow(
                        Button.danger("language.english", "English"),
                        Button.danger("language.russian", "Russian"),
                        Button.danger("language.ukrainian", "Ukrainian"),
                        Button.danger("language.china", "China"))
                .queue();
    }


    private <T> void exec(Consumer<T> configSetup, Options adminChannel, SlashCommandInteractionEvent event, EmbedBuilder eb) {
        if (event.getOption(adminChannel.optionName) != null) {
            T value = (T) adminChannel.mapping.apply(event.getOption(adminChannel.optionName));
            configSetup.accept(value);
            eb.addField("", adminChannel.text.replace("%%", value + ""), false);
        }
    }

    /**
     * @param event - Срабатывает при вызове команды "setup" и вводе одного или нескольких параметров для изменения.
     *              Возвращает эмбед со списком изменённых параметров конфига.
     *              Выводит информацию в канал логов в виде отдельных эмбедов для каждого параметра и в логи бота.
     */
    public void setup(@NotNull SlashCommandInteractionEvent event) {
        var guild = event.getGuild();
        if (guild == null) {
            throw new IllegalStateException("Processing slash commands outside guild is not possible" + event);
        }
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("BOT SETTINGS UPDATED");

        ConfigsTable config = configRepository.findByGuildId(guild.getIdLong()).orElseGet(() -> new ConfigsTable(guild));

        config.setGuildId(guild.getIdLong());
        config.setGuildName(guild.getName());
        //todo get list of added options and iterate on it will be simpler (?)
        exec(config::setAdminChannelId, Options.ADMIN_CHANNEL, event, eb);
        config.setBotId(guild.getSelfMember().getIdLong());
        exec(config::setUnrelatedEmoteCount, Options.UNRELATED_COUNT, event, eb);
        exec(config::setUnrelatedEmoteId, Options.UNRELATED_EMOTE, event, eb);
        exec(config::setWelcomeChannelId, Options.JOIN_CHANNEL, event, eb);
        exec(config::setGoodbyeChannelId, Options.LEAVE_CHANNEL, event, eb);
        exec(config::setBotInfoChannelId, Options.BOT_INFO_CHANNEL, event, eb);
        exec(config::setUnrelatedDeleteTimeSec, Options.UNRELATED_DELETE, event, eb);

        exec(config::setFunctionMusicPlayer, Options.MUSIC_PLAYER, event, eb);
        exec(config::setFunctionRememberingRoles, Options.REMEMBERING_ROLES, event, eb);
        exec(config::setFunctionDiceRoller, Options.ROLL_THE_DICE, event, eb);
        exec(config::setFunctionUnrelatedDeleter, Options.UNRELATED_REMOVER, event, eb);

        Stream.of(Options.MUSIC_PLAYER, Options.REMEMBERING_ROLES)
                .forEach(e -> unimplementedFunctional(e, event, eb));

        eb.setColor(Color.YELLOW);
        eb.setTimestamp(OffsetDateTime.now());

        configRepository.save(config);
        event.replyEmbeds(eb.build()).setEphemeral(true).queue();
        Objects.requireNonNull(guild.getTextChannelById(config.getLogChannelId())).sendMessageEmbeds(eb.build()).queue();

        setupLog(event, eb);
    }


    /**
     * @param option
     * @param event
     * @param eb     (EmbedBuilder) - билдер в который будет добавлена строка.
     */
    private void unimplementedFunctional(Options option, @NotNull SlashCommandInteractionEvent event, EmbedBuilder eb) {
        //TODO: Убрать заглушку реализовав функционалы
        if (event.getOption(option.optionName) != null) {
            if (Objects.requireNonNull(event.getOption(option.optionName)).getAsBoolean())
                eb.setAuthor("Attention! At the moment, the functionality " + option.optionName +
                        " does not work. Wait in the next versions.");

        }
    }


    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        var guild = event.getGuild();
        assert guild != null;
        ConfigsTable config = configRepository.findByGuildId(guild.getIdLong()).orElseGet(() -> new ConfigsTable(guild));
        String componentId = event.getComponentId();
        Languages lang = null;
        EmbedBuilder eb = new EmbedBuilder();
        switch (componentId) {
            case "language.english" -> {
                eb.setTitle("The English language of the customizer is selected.").setColor(Color.blue);
                lang = Languages.ENGLISH;
            }
            case "language.russian" -> {
                eb.setTitle("Выбран русский язык настройщика.").setColor(Color.green);
                lang = Languages.RUSSIAN;
            }
            case "language.ukrainian" -> {
                eb.setTitle("Обрано українську мову настроювача.").setColor(Color.yellow);
                lang = Languages.UKRAINE;
            }
            case "language.china" -> {
                eb.setTitle("选择定制器的中文语言。").setColor(Color.red);
                lang = Languages.CHINA;
            }
            default -> {
                return;
            }

        }
        event.getMessage().delete().queue();
        event.getChannel().sendMessageEmbeds(eb.build()).delay(7, TimeUnit.SECONDS).flatMap(Message::delete).queue();
        config.setBotLanguage(lang.getLanguage());
        event.deferEdit().queue();
        configRepository.save(config);
        logger.info("The bot language is set in the guild (" + guild.getName() + "(" + guild.getIdLong() + ")): " + lang);
    }

    private void setupLog(@NotNull SlashCommandInteractionEvent event, @NotNull EmbedBuilder eb) {
        StringBuilder log = new StringBuilder();
        log.append("CONFIG UPDATED: \n");
        log.append("#################################### \n");
        log.append("######## \n");
        log.append("Guild name: ").append(Objects.requireNonNull(event.getGuild()).getName()).append("\n");
        log.append("Guild id: ").append(event.getGuild().getIdLong()).append("\n");
        log.append("Guild id: ").append(event.getGuild().getIdLong()).append("\n");
        log.append("Update time: ").append(OffsetDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").withZone(ZoneOffset.UTC))).append("\n");
        log.append("######## \n");
        log.append("Has been updated: \n");
        eb.build().getFields().forEach(field -> log.append("*  ").append(field.getValue()).append("\n"));
        log.append("#################################### \n");
        logger.info(log.toString());
    }


}

