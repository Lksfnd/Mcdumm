package me.lksfnd.Chromosomia;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import me.lksfnd.Chromosomia.events.CommonPlayerEvents;


public class main extends JavaPlugin implements Listener {
	
	private ChromosomeHandler ch;
	
	@Override
    public void onEnable() {
		this.ch = new ChromosomeHandler(this);
		// Register Events
		System.out.println("[Chromosomia] Registering chromosomes...");
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(new CommonPlayerEvents(this.ch), this);
		
		System.out.println("[Chromosomia] Initialized successfully");
    }
	
    @Override
    public void onDisable() {
    	this.saveConfig();
    	System.out.println("[Chromosomia] Stopped successfully");
    }
    
    public boolean onCommand(CommandSender cs, Command cmd, String label, String args[]) {
    	Player p = (Player)cs;
    	if(cmd.getName().equalsIgnoreCase("chromosomes")) {
    		p.sendMessage("§aDu hast §6"+ ch.getChromosomes(p) +"§a Chromosome.");
    		return true;
    	}
    	if(cmd.getName().equalsIgnoreCase("clearchat")) {
    		for(int i = 0; i < 10; i++) {
    			Bukkit.broadcastMessage("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    		}
    		Bukkit.broadcastMessage("§aChat wurde von §6" + p.getName() + "§a gelöscht.");
    		return true;
    	}
    	return true;
    }
    
    @EventHandler
    public void onPlayerJoinEvent(PlayerJoinEvent event) {
    	event.setJoinMessage(
    			"§b" + event.getPlayer().getName() + "§a ist dem Server beigetreten§r | " +
    			"§d Chromosome: §5" + this.ch.getChromosomes(event.getPlayer())
    	);
    }
    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
    	event.setQuitMessage(
    			"§b" + event.getPlayer().getName() + "§c hat den Server verlassen§r | " +
    			"§d Chromosome: §5" + this.ch.getChromosomes(event.getPlayer())
    	);
    }
    
    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
    	if(event.getEntity().getKiller() instanceof Player) {
    		Player p = event.getEntity().getPlayer();
    		Player k = event.getEntity().getKiller();
    		int chromosomeTransfer = ch.getChromosomes(p)/2;
    		
    		Bukkit.broadcastMessage(
    			"§l§5" + k.getName() + " §l§3 hat §l§c" + k.getName() + " §l§3 ermordet und §d" + chromosomeTransfer  + "§l§3 Chromosome erhalten." 
    		);
    		ch.resetPlayer(p);
    		ch.addChromosomes(k, chromosomeTransfer);
    	} else {
    		Player p = event.getEntity().getPlayer();
    		ch.resetPlayer(p);
    		Bukkit.broadcastMessage("§a" + p.getName() + " §c ist durch §1" + event.getEntity().getKiller().getDisplayName() + " §c gestorben,"+
    				" und hat alle §6"+1+"§c Chromosome verloren.");
    	}
    }
    
    
}
