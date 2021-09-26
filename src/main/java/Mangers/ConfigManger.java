/*
Config manger is in charge of writing all infomation to the configs.
 */

package Mangers;

import labmatt.space.Snowball;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManger {

    private static FileConfiguration fileConfig;
    private static File fileName;

    private Snowball plugin;

    public ConfigManger(Snowball plugin) {
        this.plugin = plugin;
    }


    //Get the config file from string.
    public FileConfiguration getOldConfig(String getFile) {
        Logout log = new Logout(plugin);

        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, " ");
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Old config manger called. Please use newer getConfig to manipulate configs.");

        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Old Config is Reading config for < " + fileName + " >.");

        getFile = getFile + ".yml";
        fileName = new File(plugin.getDataFolder(), getFile);

        createConfig(new File(getFile));

        try {
            fileConfig = YamlConfiguration.loadConfiguration(fileName);
            log.debugOut(this.getClass().getName(), ChatColor.YELLOW, "Getting old config file.");
        } catch (NullPointerException e) {
            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "Old Config Unable to load config < " + getFile + " >.");
        }
        log.debugOut(this.getClass().getName(), ChatColor.YELLOW, " ");

        return fileConfig;
    }


    //Saves the config file.
    public void saveConfig() {
        Logout log = new Logout(plugin);
        try {
            log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Saving file " + fileName);
            fileConfig.save(fileName);
        } catch (IOException e) {
            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "Error saving config file.");
        }
    }


    //Create new config file form string.
    private void createConfig(File newFileName) {
        Logout log = new Logout(plugin);

        if (!plugin.getDataFolder().exists()) {
            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "Main data folder does not exist. Creating it now.");
            boolean newDir = plugin.getDataFolder().mkdir();
            if (!newDir) {
                plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error Getting plugin folder.");
            }
        }


        if (!newFileName.exists()) {

            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "No config file <" + newFileName + ">. Attempting to create it.");

            try {
                log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Creating new config file <" + newFileName + ">.");

                boolean newDir = newFileName.createNewFile();

                if (newDir) {
                    fileConfig.save(newFileName);
                } else {
                    plugin.getServer().getConsoleSender().sendMessage(ChatColor.DARK_RED + "Error Getting creating Directory. <" + newFileName + ">.");
                }
            } catch (IOException e) {

                log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "There was an error creating config file <" + newFileName + ">. <" + e.getMessage() + ">.");

            }
        }


        try {

            fileConfig = YamlConfiguration.loadConfiguration(newFileName);

        } catch (NullPointerException e) {
            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "Unable to load config <" + newFileName + ">.");
        }
    }


    public boolean isFile(File name) {
        File file = new File(plugin.getDataFolder() + File.separator + name + ".yml");
        return file.exists();
    }

    //Get the config file from string.
    public FileConfiguration getConfig(File getFile) {
        Logout log = new Logout(plugin);

        fileName = new File(plugin.getDataFolder() + File.separator + getFile + ".yml");

        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Getting config " + getFile);
        try {

            fileConfig = YamlConfiguration.loadConfiguration(fileName);
            createConfig(fileName);
            return fileConfig;
        } catch (NullPointerException e) {

            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "Unable to load config <" + getFile + ">.");
            return null;
        }
    }


    public void deleteFile() {
        Logout log = new Logout(plugin);
        log.debugOut(this.getClass().getName(), ChatColor.AQUA, "Deleting file " + fileName);
        if (!fileName.delete()) {
            log.debugOut(this.getClass().getName(), ChatColor.DARK_RED, "There was an error deleting file " + fileName);
        }
    }

}
