package Commands;

import Action.NewMatchACT;
import labmatt.space.Snowball;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class NewMatch implements CommandExecutor {

    private Snowball plugin;

    public NewMatch(Snowball plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("newmatch")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        System.out.println("done!");

        // Checks the command name
        if(command.getName().equalsIgnoreCase("newmatch"))
        {
                if ((sender instanceof Player) && sender.isOp()) {

                    // Create a new match with map name and match name.
                    if(args.length == 2)
                    {
                        new NewMatchACT(sender, args[0], args[1]);
                    } else
                    {
                        return false;
                    }

                    return true;
                } else
                {
                    sender.sendMessage(ChatColor.RED + "You must be admin to use this command.");
                    return true;
                }
        } else {
            return false;
        }
    }
}