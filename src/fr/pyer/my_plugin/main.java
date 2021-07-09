package fr.pyer.my_plugin;

import org.bukkit.plugin.java.JavaPlugin;

import fr.pyer.my_plugin.commands.CommandTest;


public class main extends JavaPlugin {
	@Override
	public void onEnable() {
		getCommand("test").setExecutor(new CommandTest());
		getCommand("excalibur").setExecutor(new CommandTest());
		getCommand("customZombie").setExecutor(new CommandTest());
		getServer().getPluginManager().registerEvents(new PluginListener(), this);
		System.out.println("Plugin is up");
	}
	@Override
	public void onDisable() {
		System.out.println("Plugin is down");
	}
}
