package fr.pyer.my_plugin;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class PluginListener implements Listener {
	//check if the player has a snowball in hand and is right clicking if yes :
	//cancels the event and build a JSON msg on which you can click to open an URL
	@EventHandler
	public void onInteract(PlayerInteractEvent event) //Second Test
	{	
		TextComponent text = null;
		Player player = event.getPlayer();
		ItemStack it = event.getItem();
		Action action = event.getAction();
		
		if (it == null)
			return;
		if (it.getType() == Material.SNOWBALL) {
			if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
				event.setCancelled(true);
				text = new TextComponent("§6Click on me");
				text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,new ComponentBuilder("§cGoogle Ishtar").create()));
				text.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.google.com/search?client=firefox-b-d&q=Ishtar"));
				player.spigot().sendMessage(text);
			}
		}
	}
}
