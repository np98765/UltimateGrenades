package com.lavacraftserver.UltimateGrenades;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class LogoutListener implements Listener{
	
	
	/** Removes the player from the HashMaps when they quit
	 * to prevent a memory leak
	 * @param event
	 */
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player p = event.getPlayer();
		if(GrenadeCommand.egg.containsKey(p)) {
			GrenadeCommand.egg.remove(p);
		}
		if(GrenadeCommand.enderPearl.containsKey(p)) {
			GrenadeCommand.enderPearl.remove(p);
		}
		if(GrenadeCommand.snowball.containsKey(p)) {
			GrenadeCommand.snowball.remove(p);
		}
	}
	
}
