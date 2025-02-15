package Events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import space.labmatt.snowball;

public class EventSnowball implements Listener {

    private snowball plugin = null;

    EventSnowball(snowball plugin) {
        this.plugin = plugin;
    }

    // When a snowball is breaks on a player then trigger this event.
    @EventHandler
    public void onInteract(ProjectileHitEvent event) {

        // When a player get hit by a snowball then trigger snowball history.
        Player playerShooter = (Player) event.getEntity().getShooter();
        Player playerHit = (Player) event.getHitEntity();

        // If either the player hit or the player shot is null then end the event here.
        if(playerShooter == null || playerHit == null) {

            return;
        }

        // Notify the player who threw the snowball who they hit.
        playerShooter.sendMessage(ChatColor.BLUE + "You hit <" + playerHit.getName() + ">.");

        Location location = new Location(playerHit.getWorld(), playerHit.getRespawnLocation().getBlockX(), playerHit.getRespawnLocation().getBlockY(), playerHit.getRespawnLocation().getBlockZ());
        playerHit.teleport(location);
        playerHit.playSound(location, "block.note_block.harp", 3.0F, 2.0F);
        playerShooter.playSound(location, "block.amethyst_block.place", 3.0F, 2.0F);


    }
}
