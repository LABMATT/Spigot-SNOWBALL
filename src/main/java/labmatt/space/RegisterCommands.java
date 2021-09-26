package labmatt.space;

import Commands.NewMatch;

public class RegisterCommands {

    private final Snowball plugin;

    RegisterCommands(Snowball snowball) {
        this.plugin = snowball;

        new NewMatch(plugin);
    }
}
