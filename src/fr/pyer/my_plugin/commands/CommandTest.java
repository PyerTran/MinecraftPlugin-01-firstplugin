package fr.pyer.my_plugin.commands;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import fr.pyer.my_plugin.main;

public class CommandTest implements CommandExecutor
{
	// check that every char in the string is a Numerical characters
	private static boolean my_str_isint(String str)
	{
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) > '9' || str.charAt(i) < '0') {
				return false;
			}
		}
		return true;
	}
	// creates an inv with one snowball inside and displays it to the player after nb ticks
	public boolean first_test(Player player, String nb)
	{
		double nb_ticks = Float.parseFloat(nb);
		Inventory inv = Bukkit.createInventory(null, 9, "§8Inventory containing one Item");
		ItemStack it = new ItemStack(Material.SNOWBALL, 1);
			
		inv.addItem(it);
		Bukkit.getScheduler().runTaskLater(main.getPlugin(main.class), new Runnable() {
			@Override
			public void run() {
				player.sendMessage("Salut :D");
				player.openInventory(inv);
			}
		}, (long)nb_ticks);
		return true;
	}
	//creates and Spawn a zombie à the player location that can't be killed
	public boolean spawn_zombie(Player player) 
	{
		Zombie my_zombie = (Zombie)player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE);
		EntityEquipment ee = my_zombie.getEquipment();
		ee.setItemInMainHand(new ItemStack(Material.WOODEN_AXE,1));
		
		player.sendMessage("§cONO");
		my_zombie.setInvulnerable(true);
		return true;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String msg, String[] args)
	{
		if (sender instanceof Player) {
			Player player = (Player)sender;

			// FIRST TEST
			if (cmd.getName().equalsIgnoreCase("test")) { //command line can now be written with caps letters
				if (args.length == 1 && my_str_isint(args[0])) {
					return first_test(player, args[0]);   //First Test Called Here
				} else {
					player.sendMessage("Command: Test takes one whole number as argument");
					return false;
				}
			}
			//Puts an unbreakable iron sword in the player's main hand
			if (cmd.getName().equalsIgnoreCase("excalibur")) {
			    ItemStack excalibur = new ItemStack(Material.IRON_SWORD, 1); //creates an Iron Sword
				ItemMeta excaM = excalibur.getItemMeta();

				excaM.setDisplayName("§cExcaliburn"); // Changes its name
				excaM.setUnbreakable(true);
				excaM.setLore(Arrays.asList("An Unknown Sword to this land", "it is said that through countless battles", "the sword never broke"));
				excalibur.setItemMeta(excaM);
				Bukkit.broadcastMessage(sender.getName() + " Got §cExcalibur");
				player.getInventory().setItemInMainHand(excalibur); //player is given Excalibur
				player.updateInventory();
				return true;
			}
			if (cmd.getName().equalsIgnoreCase("customZombie")) {
				return spawn_zombie(player);
			}
		}
		return false;
	}
}