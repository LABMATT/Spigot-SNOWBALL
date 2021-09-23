package labmatt.space;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Snowball extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SNOWBAll is attempting to start.");

        //RegisterCommands registerCommands = new RegisterCommands(this);
        new RegisterCommands(this);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "SNOWBALL is online!");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
