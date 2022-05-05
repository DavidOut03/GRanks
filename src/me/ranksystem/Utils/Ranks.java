package me.ranksystem.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ranksystem.Core.Main;

public class Ranks {

	static Main plugin = Main.getPlugin(Main.class);
	
	public static void CreateRank(CommandSender sender, String message) {
		File groups = new File("plugins//" + plugin.getName() + "//database//groups");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
		
		if(!yaml.getConfigurationSection("Groups").contains(message)) {
			try {
				yaml.set("Groups."+ message + ".default", false);
				yaml.set("Groups."+ message + ".prefix", "&8[&7default&8]");
				yaml.set("Groups."+ message + ".suffix", "");
				yaml.set("Groups."+ message + ".inventory.displayname", "&7" + message);
				yaml.set("Groups."+ message + ".inventory.material", Material.PAPER.toString());
				yaml.set("Groups."+ message + ".inventory.id", 0);
				ArrayList<String> lore = new ArrayList<String>();
				lore.add("Put here the lore.");
				yaml.set("Groups." +  message + ".inventory.lore", lore);
				yaml.set("Groups."+ message + ".permissions", "");
				yaml.save(groups);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			sender.sendMessage(Chat.format("&cThis rank does already exist."));
		}
		
	}
	public static void RemoveRank(CommandSender sender, String message) {
		File groups = new File("plugins//" + plugin.getName() + "//database//groups");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
		
		if(yaml.getConfigurationSection("Groups").contains(message)) {
			try {
				yaml.set("Groups."+ message, null);
				yaml.save(groups);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		} else {
			sender.sendMessage(Chat.format("&cThis rank does't exist."));
		}
	}
	
	public static void registerPlayer(UUID uuid) {
		File groups = new File("plugins//" + plugin.getName() + "//database//groups");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
		if(!yaml.getConfigurationSection("Users").contains(uuid.toString())) {
		try {
			for(String s : yaml.getConfigurationSection("Groups").getKeys(false)) {
				if(yaml.getBoolean("Groups." + s + ".default") == true) {
					yaml.set("Users." + uuid + ".rank", s);
				}
			}
			yaml.save(groups);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		}
	}
	
	public static String getRank(UUID uuid) {
		File groups = new File("plugins//" + plugin.getName() + "//database//groups");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
		return yaml.getString("Users." + uuid + ".rank");
	}
	
	public static String getPrefix(UUID uuid, String rank) {
		File groups = new File("plugins//" + plugin.getName() + "//database//groups");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
		if(yaml.getConfigurationSection("Groups").contains(rank)) {
			return yaml.getString("Groups." + rank + ".prefix");
		} else {
			return Chat.format("&cThis rank does not exist");
		}
	}
	
	public static String getSuffix(UUID uuid, String rank) {
		File groups = new File("plugins//" + plugin.getName() + "//database//groups");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
		if(yaml.getConfigurationSection("Groups").contains(rank)) {
			return yaml.getString("Groups." + rank + ".suffix");
		} else {
			return Chat.format("&cThis rank does not exist");
		}
	}
}
