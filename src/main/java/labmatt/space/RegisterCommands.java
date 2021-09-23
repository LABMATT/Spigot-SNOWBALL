package labmatt.space;

import Commands.NewLobby;

public class RegisterCommands {

    private final Snowball plugin;

    RegisterCommands(Snowball snowball) {
        this.plugin = snowball;

        new NewLobby(plugin);
    }
}
