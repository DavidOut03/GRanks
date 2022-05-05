package me.ranksystem.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import me.ranksystem.Core.Main;
import me.ranksystem.Utils.Chat;
import me.ranksystem.Utils.Messages;
import me.ranksystem.Utils.Ranks;

public class Config {

	Main plugin = Main.getPlugin(Main.class);
	
	public void loadingConfig() {
		if(!new File("plugins//" + plugin.getName() +"//config.yml").exists()) {
			plugin.saveDefaultConfig();
			
			Bukkit.getConsoleSender().sendMessage(Chat.format(Messages.Prefix + "&aCreated config."));
		} else {
			plugin.saveConfig();
			plugin.reloadConfig();
			Bukkit.getConsoleSender().sendMessage(Chat.format(Messages.Prefix + "&7Loaded config."));
		}
	}
	
	public void loadingDatabase() {
		File database = new File("plugins//" + plugin.getName() + "//database");
		File groups = new File("plugins//" + plugin.getName() + "//database//groups");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
		
		if(!database.exists()) {
			database.mkdirs();
		}
		
		if(!groups.exists()) {
		try {
			groups.createNewFile();
			yaml.createSection("Groups");
			String message = "default";
			yaml.set("Groups."+ message + ".default", true);
			yaml.set("Groups."+ message + ".prefix", "&8[&7default&8]");
			yaml.set("Groups."+ message + ".suffix", "");
			yaml.set("Groups."+ message + ".inventory.displayname", "&7" + message);
			yaml.set("Groups."+ message + ".inventory.material", Material.PAPER.toString());
			yaml.set("Groups."+ message + ".inventory.id", 0);
			ArrayList<String> lore = new ArrayList<String>();
			lore.add("Put here the lore.");
			yaml.set("Groups." +  message + ".inventory.lore", lore);
			yaml.set("Groups."+ message + ".permissions", "");
			yaml.createSection("Users");
			yaml.save(groups);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		}
	}
}
