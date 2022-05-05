package me.ranksystem.Listeners;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.ranksystem.Core.Main;
import me.ranksystem.Utils.Chat;
import me.ranksystem.Utils.Ranks;

public class Listener_Chat implements Listener {

	Main plugin = Main.getPlugin(Main.class);
	File groups = new File("plugins//" + plugin.getName() + "//database//groups.yml");
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String rank = Ranks.getRank(p.getUniqueId()).toString();
		String message = e.getMessage().toString();
		String format = yaml.getString("Settings.chat-format").replace("%rank%", rank).replace("%player%", p.getName()).replace("%message%", message);
		if(plugin.getConfig().getBoolean("Settings.chat-enabled") == true) {
		e.setFormat(Chat.format(yaml.getString("Settings.chat-format") + e.getMessage()));
		} else {
			e.setFormat(Chat.format("&c" + p.getName() + "&8: " + e.getMessage()));
		}
		
	}
}
