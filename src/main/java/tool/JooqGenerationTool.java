package tool;

import org.jooq.codegen.GenerationTool;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JooqGenerationTool {
    public static void main(String[] args) throws Exception {
        GenerationTool.generate(
                Files.readString(
                        Paths.get("C:\\Users\\karau\\IdeaProjects\\DiscordBots\\Servers\\IT.Pony()\\Luna\\src\\main\\resources\\jooq-config.xml")
                )
        );
    }
}
