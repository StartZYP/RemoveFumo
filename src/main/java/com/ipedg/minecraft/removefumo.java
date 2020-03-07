package com.ipedg.minecraft;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.Map;
import java.util.Set;

public class removefumo extends JavaPlugin {

    @Override
    public void onEnable() {
        File config = new File(getDataFolder() + File.separator + "config.yml");
        if (!config.exists()) {
            getConfig().options().copyDefaults(true);
        }
        saveDefaultConfig();

        new BukkitRunnable(){
            @Override
            public void run() {
                System.out.print("检测附魔中");
                for (Player player:Bukkit.getOnlinePlayers()){
                    if (!player.hasPermission("removefumo.liliang")){
                    for (int a=0;a<=35;a++){
                        ItemStack item = player.getInventory().getItem(a);
                        if (item!=null&&item.getType()!= Material.AIR&&item.containsEnchantment(Enchantment.ARROW_DAMAGE)){
                            item.removeEnchantment(Enchantment.ARROW_DAMAGE);
                            player.getInventory().setItem(a,item);
                            player.sendMessage(getConfig().getString("Msg"));
                        }
                    }
                        player.updateInventory();
                    }
                }
            }
        }.runTaskTimer(this,1L,20*getConfig().getInt("Time"));
        super.onEnable();
    }

}
