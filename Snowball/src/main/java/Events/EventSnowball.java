package Events;

import Game.GameManager;
import Game.SnowballPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import space.labmatt.snowball;

public class EventSnowball implements Listener {

    private snowball plugin = null;
    private GameManager gameManager;

    EventSnowball(snowball plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
    }

    // When a snowball is breaks on a player then trigger this event.

    /**
     * When A showball breaks then triggers this event.
     * Make sure its a snowball,
     * Make sure there players,
     * Make sure player is not killing them self.
     *
     * @param event
     */
    @EventHandler
    public void onInteract(ProjectileHitEvent event) {

        // Check if its a snowball
        if (!event.getEntity().getName().equals("Snowball")) {

            return;
        }

        // Check if is a player
        if (!(event.getHitEntity() instanceof Player)) {

            return;
        }

        // When a player get hit by a snowball then trigger snowball history.
        Player playerShooter = (Player) event.getEntity().getShooter();
        Player playerHit = (Player) event.getHitEntity();

        // If either the player hit or the player shot is null then end the event here.
        if (playerShooter == null || playerHit == null) {

            return;
        }

        // Check to see if a player has shot them self.
        if (playerShooter == playerHit) {

            //return;
        }


        SnowballPlayer gameMangerPlayerShooter = gameManager.getPlayer(playerShooter);
        SnowballPlayer gameManagerPlayerHit = gameManager.getPlayer(playerHit);

        // If the player has been killed in 5 seconds
        int cooldown = 5000;
        boolean spawnProtectionEnabled = true;
        long timePassedSinceRespawn = Math.subtractExact(System.currentTimeMillis(), gameManagerPlayerHit.respawnTime);
        if (timePassedSinceRespawn < cooldown && spawnProtectionEnabled) {

            long timeRemaing = Math.subtractExact(cooldown / 1000, timePassedSinceRespawn / 1000);
            playerShooter.sendMessage(ChatColor.RED + "This player is spawn protected for " + timeRemaing + " seconds.");
            return;
        }


        Location location = new Location(playerHit.getWorld(), playerHit.getRespawnLocation().getBlockX(), playerHit.getRespawnLocation().getBlockY(), playerHit.getRespawnLocation().getBlockZ());
        playerHit.teleport(location);
        playerShooter.playSound(location, "block.note_block.harp", 3.0F, 2.0F);
        playerHit.playSound(location, "entity.player.attack.strong", 3.0F, 2.0F);


        gameManagerPlayerHit.deaths++;
        gameMangerPlayerShooter.kills++;

        gameManagerPlayerHit.respawnTime = System.currentTimeMillis();

        // Notify the player who threw the snowball who they hit.
        playerShooter.sendMessage(ChatColor.GRAY + "You powned " + playerHit.getName() + ". Kills: " + gameMangerPlayerShooter.kills + " Deaths: " + gameManagerPlayerHit.deaths);
    }
}
