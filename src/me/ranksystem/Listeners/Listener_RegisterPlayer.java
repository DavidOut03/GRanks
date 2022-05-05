package me.ranksystem.Listeners;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.ranksystem.Core.Main;
import me.ranksystem.Utils.Ranks;

public class Listener_RegisterPlayer implements Listener {

	Main plugin = Main.getPlugin(Main.class);
	File groups = new File("plugins//" + plugin.getName() + "//database//groups");
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(!yaml.getConfigurationSection("Users").contains(e.getPlayer().getUniqueId().toString())) {
			Ranks.registerPlayer(e.getPlayer().getUniqueId());
		}
		
	}
}
