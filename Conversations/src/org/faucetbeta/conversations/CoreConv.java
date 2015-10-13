package org.faucetbeta.conversations;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversation;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class CoreConv extends JavaPlugin{
	@Override
	public void onEnable(){
		getLogger().info("Conversations has now activated!");
		getLogger().info("Loading revision: " + this.getDescription().getVersion());
	}
	@Override
	public void onDisable(){
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("startconversation")){
			// start a conversation
			if(!sender.hasPermission("conversationsbeta.startconversation")){
				sender.sendMessage(ChatColor.RED + "[Conversations] Sorry, you don't have the permission of: conversationsbeta.startconversation");
				return true;
			}
			if(args.length > 1){
				sender.sendMessage(ChatColor.RED + "[Conversations] Sorry, it looks like you entered the command incorrectly. Try again!");
				return true;
			}
			if(args.length < 1){
				sender.sendMessage(ChatColor.RED + "[Conversations] Sorry, it looks like you entered the command incorrectly. Try again!");
				return true;
			}
			Player specifiedPlayer = Bukkit.getPlayer(args[0]);
			if(specifiedPlayer == null){
				sender.sendMessage(ChatColor.RED + "[Conversations] Sorry, you're player that you specified is not valid.");
				return true;
			}
			Conversation conversationPlayer = new Conversation(this, specifiedPlayer, null);
			specifiedPlayer.sendMessage(ChatColor.GOLD + "[Conversations] You have been forced to accept a conversation!");
			if(!(sender instanceof Player)){
				specifiedPlayer.sendMessage(ChatColor.RED + "[Conversations] You're conversation that you were forced to accept has been cancelled.");
				sender.sendMessage(ChatColor.RED + "[Conversations] Sorry, you are a CONSOLE instance, and cannot start a conversation.");
				return true;
			}
			Player senderPlayer = (Player) sender;
			senderPlayer.beginConversation(conversationPlayer);
			//sender.sendMessage(ChatColor.BLUE + "Andrew is nice.");
			sender.sendMessage(ChatColor.GREEN + "[Conversations] Conversation successfully initalized!");
			return true;
		}
		if(cmd.getName().equalsIgnoreCase("istalking")){
			// find if specified player is talking
			if(!sender.hasPermission("conversationsbeta.istalking")){
				sender.sendMessage(ChatColor.RED + "[Conversations] Sorry, you don't have the permission of: conversationsbeta.istalking");
				return true;
			}
			if(args.length > 1){
				sender.sendMessage(ChatColor.RED + "[Conversations] Sorry, it looks like you entered the command incorrectly. Try again!");
				return true;
			}
			if(args.length < 1){
				sender.sendMessage(ChatColor.RED + "[Conversations] Sorry, it looks like you entered the command incorrectly. Try again!");
				return true;
			}
			Player specifiedPlayer = Bukkit.getPlayer(args[0]);
			if(specifiedPlayer == null){
				sender.sendMessage(ChatColor.RED + "[Conversations] Sorry, you're player that you specified is not valid.");
				return true;
			}
			boolean isConversing = specifiedPlayer.isConversing();
			if(isConversing){
				sender.sendMessage(ChatColor.GOLD + "[Conversations] Player " + specifiedPlayer.getPlayerListName() + " is conversing!");
				return true;
			}
			if(!isConversing){
				sender.sendMessage(ChatColor.GOLD + "[Conversations] Player " + specifiedPlayer.getPlayerListName() + " is not conversing.");
				return true;
			}
			sender.sendMessage(ChatColor.DARK_RED + "[ConversationsStablizer] Sorry, something went wrong when issuing the stablizer.COMMANDENGINE.");
			return true;
		}
		return false; // everything fails.
	}
}
