package me.ranksystem.GUI;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.ranksystem.Core.Main;
import me.ranksystem.Utils.Chat;

public class GUI_RankList implements Listener {
	
	Main plugin = Main.getPlugin(Main.class);
	
	public static void sendGUI(Player sender) {
		Main plugin = Main.getPlugin(Main.class);
		File groups = new File("plugins//" + plugin.getName() + "//database//groups");
		YamlConfiguration yaml = YamlConfiguration.loadConfiguration(groups);
		Inventory inv = Bukkit.createInventory(null, 54, Chat.format(plugin.getConfig().getString("Settings.GUI.name")));
		
		for(String key : yaml.getConfigurationSection("Groups").getKeys(false)) {
			List<String> lore = yaml.getStringList(Chat.format("Groups." + key + ".inventory.lore"));
			createItem(Material.getMaterial(yaml.getString("Groups." + key + ".inventory.material")), yaml.getString("Groups." + key + ".inventory.displayname"), lore, 1, yaml.getInt("Groups." + key + ".inventory.id"), inv);
		}
		sender.openInventory(inv);
		
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		
		if(e.getView().getTitle()
				.equalsIgnoreCase(Chat.format(plugin.getConfig().getString("Settings.GUI.name")))) {
			if(e.getClickedInventory() != e.getWhoClicked().getInventory()) {
				e.setCancelled(true);
			}
		}
	}
	
	public static void createItem(Material mat, String name, List<String> lore, int amount, int id, Inventory inv) {
		ItemStack itemstack = new ItemStack(mat, amount, (short) id);
		ItemMeta itemmeta = itemstack.getItemMeta();
		ArrayList<String> itemlore = new ArrayList<String>();
		itemmeta.setDisplayName(Chat.format(name));
		itemlore.add(lore.toString().replace("&", "§"));
		itemmeta.setLore(lore);
		itemstack.setItemMeta(itemmeta);
		inv.addItem(itemstack);
	}
}
