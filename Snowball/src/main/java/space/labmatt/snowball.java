package space.labmatt;

import Commands.RegisterCommands;
import Events.RegisterEvents;
import Game.GameManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class snowball extends JavaPlugin {

    @Override
    public void onEnable() {

        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SNOWBALL BY LABMATT IS STARTING.");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "Product of Matthew Lewington www.labmatt.space");

        GameManager gameManager = new GameManager(this);
        gameManager.getServerPlayers();

        // Register commands and events
        new RegisterCommands();
        new RegisterEvents(this, gameManager);
    }

    @Override
    public void onDisable() {

        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SNOWBALL SHUTDOWN COMPLETE.");
    }
}
