package com.lavacraftserver.UltimateGrenades;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnderpearlListener implements Listener {
	
	private UltimateGrenades plugin;
	
	public EnderpearlListener(UltimateGrenades instance) {
		plugin = instance;
	}
	
	HashMap<Player, Boolean> map = com.lavacraftserver.UltimateGrenades.GrenadeCommand.enderPearl;
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		Entity entity = e.getEntity();
		Projectile proj = (Projectile)entity;
		Entity shooter = proj.getShooter();
		
		if (entity.getType() == EntityType.ENDER_PEARL) {
			if (shooter.getType() == EntityType.PLAYER) {
				Player p = (Player)shooter;
				
				if (map.containsKey(p)) {
					int radius = plugin.getConfig().getInt("grenades.enderpearl.radius");
					List<Entity> entitylist = entity.getNearbyEntities(radius, radius, radius);
					
					for (int i = 0; i < entitylist.size(); i++) {
						Entity realEntity = entitylist.get(i);

						if (realEntity instanceof LivingEntity) {
							LivingEntity newEnt = (LivingEntity)realEntity;
							if (plugin.config.getStringList("grenades.enderpearl.grenade-effect").contains("confusion")) {
								System.out.println("CONFUSED!");
								int confusiontime = plugin.getConfig().getInt("grenades.enderpearl.grenade-effect.confusion-duration", 5) * 20;
								newEnt.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, confusiontime, 1));
							}
							if (plugin.config.getStringList("grenades.enderpearl.grenade-effect").contains("blindness")) {
								int blindnesstime = plugin.getConfig().getInt("grenades.enderpearl.grenade-effect.blindness-duration", 5) * 20;
								newEnt.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, blindnesstime, 1));
							}
							if (plugin.config.getStringList("grenades.enderpearl.grenade-effect").contains("real-explosion")) {
								Location loc = newEnt.getLocation();
								newEnt.getWorld().createExplosion(loc, 4.0F);
							}
							if (plugin.config.getStringList("grenades.enderpearl.grenade-effect").contains("fake-explosion")) {
								Location loc = newEnt.getLocation();
								newEnt.getWorld().createExplosion(loc, 0.0F, false);
							}
							if (plugin.config.getStringList("grenades.enderpearl.grenade-effect").contains("real-lightning")) {
								Location loc = newEnt.getLocation();
								newEnt.getWorld().strikeLightning(loc);
							}
							if (plugin.config.getStringList("grenades.enderpearl.grenade-effect").contains("fake-lightning")) {
								Location loc = newEnt.getLocation();
								newEnt.getWorld().strikeLightningEffect(loc);
							}
						}
					}
				}
			}
		}
	}
}
