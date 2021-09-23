package Commands;

import labmatt.space.Snowball;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class NewLobby implements CommandExecutor {

    private Snowball plugin;

    public NewLobby(Snowball plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("lmscClean")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // Checks the command name
        if(command.getName().equalsIgnoreCase(""))
        {
            try {
                if (!(sender instanceof Player) || sender.isOp()) {

                } catch

            return true;
        } else {
            return false;
        }
    }
}