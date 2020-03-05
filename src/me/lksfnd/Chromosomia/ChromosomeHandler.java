package me.lksfnd.Chromosomia;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ChromosomeHandler {
	
	private main plugin;
	
	public ChromosomeHandler(main plugin) {
		this.plugin = plugin;
	}

	public int getChromosomes(Player p) {
		if(!plugin.getConfig().contains("player."+ p.getUniqueId())) {
			this.setChromosomes(p, 46);
			return 46;
		} else {
			return plugin.getConfig().getInt("player." + p.getUniqueId() + ".chromosomes");
		}
		
	}
	
	public void resetPlayer(Player p) {
		p.sendMessage("ßlßaHey du 51er!\nViel Spaﬂ mit deinen neuen 46 Chromosomen");
		plugin.getConfig().set("player." + p.getUniqueId(), null);
		this.setChromosomes(p, 46);
	}
	
	public void addChromosomes(Player p, int count) {
		this.setChromosomes(p, this.getChromosomes(p) + count);
	}
	public void removeChromosomes(Player p, int count) {
		this.addChromosomes(p, -count);
	}
	public void setChromosomes(Player p, int count) {
		
		if(count <= 0) {
			p.setHealth(0.0);
			Bukkit.broadcastMessage("ßa" + p.getName() + "ß4 hat keine Chromosome mehr und ist gestorben.");
		} else if(count == 1) {
			Bukkit.broadcastMessage("ßa" + p.getName() + "ß9 hat mit nur ßdeinemß9 Chromosom gerade eine Nahkoterfahrung.");
		} else if(count < 20) {
			p.damage(0.5);
			Bukkit.broadcastMessage("ßa" + p.getName() + "ß9 hat nur noch ßd" + count + "ß9 Chromosome.");
		} else if(count > 75) {
			p.setHealth(p.getHealth() + 0.5);
			Bukkit.broadcastMessage("ßa" + p.getName() + "ße hat "+ count + " Chromosome. Lol.");
		} else if(count > 100) {
			p.setHealth(p.getHealth() + 1);
			Bukkit.broadcastMessage("ßa" + p.getName() + "ß6 hat "+ count + " Chromosome. WTF?");
		} else {
			Bukkit.broadcastMessage("ßa" + p.getName() + "ß7: "+ count + " Chromosome.");
		}
		this.evokeChromosomeEvent(p, count);
				
		plugin.getConfig().set("player."+p.getUniqueId() + ".chromosomes", count);
		plugin.saveConfig();
	}
	
	public boolean getChromosomeBonus(Player p, String bonusName) {
		if(
				plugin.getConfig().contains("player." + p.getUniqueId() + ".claimed." + bonusName.toLowerCase()) ||
				plugin.getConfig().getBoolean("player." + p.getUniqueId() + ".claimed." + bonusName.toLowerCase()) == true
		) {
			return false;
		} else {
			plugin.getConfig().set("player." + p.getUniqueId() + ".claimed." + bonusName.toLowerCase(), true);
			plugin.saveConfig();
			return true;
		}
	}
	
	public void evokeChromosomeEvent(Player p, int count) {
		String message = null;
		
		switch(count) {
			case 47:
				
			break;
			default:
				break;
		}
		
		if(!(message == null)) {
			Bukkit.broadcastMessage(message);
		}
	}
	
}