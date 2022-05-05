package me.ranksystem.Utils;

import me.ranksystem.Core.Main;

public class Messages {
	
	public static String Prefix = Chat.format(Main.getPlugin(Main.class).getConfig().getString("Prefix"));
	
	

}
/////////////////////////////////////////////////////////
//Chat Uses;                       //
//%prefix%: get the rank prefix.                 //
//%suffix%: get the rank suffix.                 //
//%rank%: get the rank name.                     // 
//%player%: get the playername.                  // 
//%message%: get the chat message.               //
/////////////////////////////////////////////////////////