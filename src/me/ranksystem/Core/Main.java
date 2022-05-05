package me.ranksystem.Core;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.ranksystem.Commands.CMD_rank;
import me.ranksystem.Configuration.Config;
import me.ranksystem.GUI.GUI_RankList;
import me.ranksystem.Listeners.Listener_Chat;
import me.ranksystem.Listeners.Listener_RegisterPlayer;
import me.ranksystem.Utils.Chat;
import me.ranksystem.Utils.Messages;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		registerCommands();
		registerGUI();
		Config config = new Config();
		config.loadingConfig();
		config.loadingDatabase();
		registerListeners();
		Bukkit.getConsoleSender().sendMessage(Chat.format(Messages.Prefix + "Turned RankSystem &aON&7."));
	}
	

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(Chat.format(Messages.Prefix + "Turned RankSystem &cOFF&7."));
	}
	
	private void registerListeners() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new Listener_RegisterPlayer(), this);
		pm.registerEvents(new Listener_Chat(), this);
	}
	private void registerGUI() {
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new GUI_RankList(), this);
		
	}

	private void registerCommands() {
		getCommand("rank").setExecutor(new CMD_rank());
		
	}
}
