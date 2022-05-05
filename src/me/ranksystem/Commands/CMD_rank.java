package me.ranksystem.Commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.ranksystem.Core.Main;
import me.ranksystem.GUI.GUI_RankList;
import me.ranksystem.Utils.Chat;
import me.ranksystem.Utils.Messages;
import me.ranksystem.Utils.Ranks;

public class CMD_rank implements CommandExecutor {

	Main plugin = Main.getPlugin(Main.class);
	File groups = new File("plugins//" + plugin.getName() + "//database//groups");
	YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
	
		if(cmd.getName().equalsIgnoreCase("rank")) {
			if(sender.hasPermission("granks.manage") || sender.hasPermission("granks.*") || sender.isOp()) {
				if(args.length == 0) {
					sender.sendMessage(Chat.format((Messages.Prefix + "&cUse&7: /rank [create, remove, list] [name]")));
				} else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("create") || args[0].equalsIgnoreCase("remove")) {
						sender.sendMessage(Chat.format((Messages.Prefix + "&cUse&7: /rank [create, remove, list] [name]")));
					} else if(args[0].equalsIgnoreCase("list")) {
						if(plugin.getConfig().getBoolean("Settings.GUI.enabled") == true) {
						if(sender instanceof Player) {
							GUI_RankList.sendGUI((Player) sender);
							} else {
								sender.sendMessage(Messages.Prefix + ": " + yaml.getConfigurationSection("Groups").getKeys(false).toString());
							}
						} else {
							sender.sendMessage(Messages.Prefix + ": " + yaml.getConfigurationSection("Groups").getKeys(false).toString());
						}
					}
				} else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("create")) {
						Ranks.CreateRank(sender, args[1]);
					} else if(args[0].equalsIgnoreCase("remove")) {
						Ranks.RemoveRank(sender, args[1]);	
					} else {
						sender.sendMessage(Chat.format((Messages.Prefix + "&cUse&7: /rank [create, remove, list] [name]")));
					}
				}
			}
		}
		return false;
		
	}
	

	

}
