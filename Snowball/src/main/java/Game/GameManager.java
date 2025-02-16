package Game;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import space.labmatt.snowball;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class GameManager {

    public HashMap<String, SnowballPlayer> playerList = new HashMap<>();
    private snowball plugin;

    public GameManager(snowball snowball) {
        this.plugin = snowball;
    }

    /**
     * If the server is already running then grab the players currently on it.
     */
    public void getServerPlayers() {

        // Make sure there are players online.
        if (plugin.getServer().getOnlinePlayers().isEmpty()) {

            return;
        }


        for (Player player : plugin.getServer().getOnlinePlayers()) {

            SnowballPlayer snowballPlayer = new SnowballPlayer();
            snowballPlayer.player = player;

            playerList.put(player.getName(), snowballPlayer);
        }

        plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Added players that were online to the Game Manger. Amount: " + playerList.size());
    }

    /**
     * Using the players username get there snowball count.
     * @param player the player wer are trying to get.
     * @return returns the players snowball count.
     */
    public synchronized SnowballPlayer getPlayer(Player player) {

        // Gets the player if they exist.
        return playerList.get(player.getName());
    }
}
