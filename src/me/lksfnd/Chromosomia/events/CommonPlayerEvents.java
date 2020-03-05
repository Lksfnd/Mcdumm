package me.lksfnd.Chromosomia.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerFishEvent;

import me.lksfnd.Chromosomia.ChromosomeHandler;

public class CommonPlayerEvents implements Listener {
	private ChromosomeHandler ch;
	
	public CommonPlayerEvents(ChromosomeHandler ch) {
		this.ch = ch;
	}
	
	public void notify(Player p, String message) {
		Bukkit.broadcastMessage("§5§n[Event]§r §6" + p.getName() + "§3 " + message);
	}
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e) {
		if(Math.random() <= 0.15) {
			e.setCancelled(true);
			notify(e.getPlayer(), "hat ein Chromosom geangelt!");
			ch.addChromosomes(e.getPlayer(), 1);
		} else if(Math.random() <= 0.05) {
			e.setCancelled(true);
			notify(e.getPlayer(), "hat zwei Chromosome geangelt!");
			ch.addChromosomes(e.getPlayer(), 2);
		} else if(Math.random() <= 0.01) {
			e.setCancelled(true);
			notify(e.getPlayer(), "hat §ldrei§r§3 Chromosom geangelt!");
			ch.addChromosomes(e.getPlayer(), 3);
		} else if(Math.random() <= 0.10) {
			e.setCancelled(true);
			notify(e.getPlayer(), "hat ausversehen ein Chromosom an einen Fisch abgegeben!");
			ch.removeChromosomes(e.getPlayer(), 1);
		}
	}

	@EventHandler
	public void onEggThrowEvent(PlayerEggThrowEvent e) {
		if(Math.random() < 0.33) {
			e.setHatching(true);
			Player p = e.getPlayer();
			World w = p.getWorld();
			Location l = p.getLocation();
			notify(p, "hat ausversehen ein Chromosom statt einem Ei geworfen. Viel Spaß mit der Triesomiekuh!");
			
			w.spawnEntity(l, EntityType.MUSHROOM_COW);
		}
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		if(Math.random() < 0.02) {
			ch.addChromosomes(p, 1);
			notify(p,"hat ein Chromosom in einem Block gefunden.");
		} else if(Math.random() < 0.015) {
			notify(p,"hat ein Chromosom fallen gelassen.");
		}
		if(e.getBlock().getType() == Material.DIAMOND_ORE) {
			notify(p, "hat DIAMANTEN GEFUNDEN!!!!!!1");
			ch.addChromosomes(p, 1);
		}
	}
}
