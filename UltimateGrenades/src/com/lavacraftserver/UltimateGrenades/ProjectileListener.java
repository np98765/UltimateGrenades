package com.lavacraftserver.UltimateGrenades;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ProjectileListener implements Listener{

	/* Not registered yet! Will replace the 3 listeners
	 * by combining them into one once tested!
	 */
	
	
	Plugin plugin;
	FileConfiguration config;

	public ProjectileListener(UltimateGrenades instance) {
		plugin = instance;
		config = plugin.getConfig();
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		Entity entity = e.getEntity();
		Projectile proj = (Projectile)entity;
		Entity shooter = proj.getShooter();

		if (shooter.getType().equals(EntityType.PLAYER)) {
			Player p = (Player)shooter;
			if (canThrow(p, proj.getType())) {
				int radius = plugin.getConfig().getInt("grenades.egg.radius");
				List<Entity> entitylist = entity.getNearbyEntities(radius, radius, radius);

				for (int i = 0; i < entitylist.size(); i++) {
					Entity realEntity = entitylist.get(i);

					if (realEntity instanceof LivingEntity) {
						LivingEntity newEnt = (LivingEntity)realEntity;
						String typeString = proj.getType().toString().toLowerCase().replaceAll("_", "");
						if (config.getStringList("grenades."+typeString+".grenade-effect").contains("confusion")) {
							System.out.println("CONFUSED!");
							int confusiontime = plugin.getConfig().getInt("grenades."+typeString+".grenade-effect.confusion-duration", 5) * 20;
							newEnt.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, confusiontime, 1));
						}
						if (config.getStringList("grenades."+typeString+".grenade-effect").contains("blindness")) {
							int blindnesstime = plugin.getConfig().getInt("grenades."+typeString+".grenade-effect.blindness-duration", 5) * 20;
							newEnt.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, blindnesstime, 1));
						}
						if (config.getStringList("grenades."+typeString+".grenade-effect").contains("real-explosion")) {
							Location loc = newEnt.getLocation();
							newEnt.getWorld().createExplosion(loc, 4.0F);
						}
						if (config.getStringList("grenades."+typeString+".grenade-effect").contains("fake-explosion")) {
							Location loc = newEnt.getLocation();
							newEnt.getWorld().createExplosion(loc, 0.0F, false);
						}
						if (config.getStringList("grenades."+typeString+".grenade-effect").contains("real-lightning")) {
							Location loc = newEnt.getLocation();
							newEnt.getWorld().strikeLightning(loc);
						}
						if (config.getStringList("grenades."+typeString+".grenade-effect").contains("fake-lightning")) {
							Location loc = newEnt.getLocation();
							newEnt.getWorld().strikeLightningEffect(loc);
						}
					}
				}
			}
		}
	}

	private boolean canThrow(Player p, EntityType gt) {
		switch(gt) {
		case EGG:
			if(GrenadeCommand.egg.get(p)) {
				return true;
			}
			return false;
		case ENDER_PEARL:
			if(GrenadeCommand.enderPearl.get(p)) {
				return true;
			}
			return false;
		case SNOWBALL:
			if(GrenadeCommand.snowball.get(p)) {
				return true;
			}
			return false;
		default:
			break;
		}
		return false;
	}

}
