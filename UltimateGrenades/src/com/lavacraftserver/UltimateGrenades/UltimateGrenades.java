package com.lavacraftserver.UltimateGrenades;

import java.io.File;
import java.io.InputStream;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class UltimateGrenades extends JavaPlugin implements Listener {
	
	public FileConfiguration config = null;
	private File configFile = null; 
	
	@Override
	public void onEnable() {
		
		configFile = new File("plugins" + File.separator + "UltimateGrenades" + File.separator + "config.yml");
		config = YamlConfiguration.loadConfiguration(configFile);
		 
		InputStream defaultStream = this.getResource("config.yml");
		if (defaultStream != null) {
		    YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(defaultStream);
		    config.setDefaults(defaultConfig);
		}
		
		if (!configFile.exists()) {
		getConfig().options().copyDefaults(true);
		saveConfig();
		}

		getServer().getPluginManager().registerEvents(new EggListener(this), this);
		getServer().getPluginManager().registerEvents(new EnderpearlListener(this), this);
		getServer().getPluginManager().registerEvents(new SnowballListener(this), this);
		getCommand("grenade").setExecutor(new GrenadeCommand());
		
		getLogger().info("UltimateGrenades by np98765 and MrBluebear3 has been enabled!");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("UltimateGrenades by np98765 and MrBluebear3 has been disabled!");
	}
	
}
