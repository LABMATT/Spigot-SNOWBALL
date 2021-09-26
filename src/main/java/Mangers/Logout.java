package Mangers;

import labmatt.space.Snowball;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Logout {

    private static boolean debug = false;

    private static boolean bordcastToOp = false;

    private static String prevSender = "";

    private Snowball plugin;

    public Logout(Snowball instance) {
        this.plugin = instance;
    }

    public void activateDebug(boolean active) {
        debug = active;
    }

    public void debugOut(String className, ChatColor clr, String message) {
        bordcastToOp = plugin.getConfig().getBoolean("debugToOps");

        if (debug) {
            if (!prevSender.equals(className)) {
                System.out.println(" ");
            }
            plugin.getServer().getConsoleSender().sendMessage(ChatColor.AQUA + className + ": " + clr + message);
            prevSender = className;
        }

        if (bordcastToOp) {
            for (Player plr : plugin.getServer().getOnlinePlayers()) {
                if (plr.isOp()) {
                    plr.sendMessage(ChatColor.AQUA + className + ": " + clr + message);
                }
            }
        }
    }

    public void LMSCPRINT(CommandSender sender, ChatColor clr, String message) {
        if (sender == null) {
            debugOut("Unknown Sender", ChatColor.DARK_RED, message);
        } else {
            sender.sendMessage(clr + message);
        }
    }
}
