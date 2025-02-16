package Events;

import Game.GameManager;
import space.labmatt.snowball;

public class RegisterEvents {

    public RegisterEvents(snowball plugin, GameManager gameManager) {

        // Register events that will accouter during the game.
        plugin.getServer().getPluginManager().registerEvents(new EventSnowball(plugin, gameManager), plugin);
    }
}
