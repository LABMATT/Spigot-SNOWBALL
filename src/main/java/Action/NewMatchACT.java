package Action;

import Mangers.XMLManger;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.io.FileNotFoundException;

public class NewMatchACT {

    // This function attempts to create a new match under the world name folder and a file called the match name. This will contain future spawan points and teams.
    public NewMatchACT(CommandSender sender, String mapname, String matchName) {

        // check if the strings are proplery entered correctly else return error.
        if(mapname.length() > 0 && matchName.length() > 0)
        {

            XMLManger xmlManger = new XMLManger();
            try {
                xmlManger.getXML("test").readXML();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            sender.sendMessage(ChatColor.RED + "World/Map Name and Match Name cannot be blank.");
        }

    }
}