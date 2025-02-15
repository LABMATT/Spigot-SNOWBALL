package Events;

import space.labmatt.snowball;

public class RegisterEvents {

    public RegisterEvents(snowball plugin) {

        // Register events that will accouter during the game.
        plugin.getServer().getPluginManager().registerEvents(new EventSnowball(plugin), plugin);
    }
}
