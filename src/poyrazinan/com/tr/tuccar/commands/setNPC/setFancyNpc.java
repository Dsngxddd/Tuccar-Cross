package poyrazinan.com.tr.tuccar.commands.setNPC;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;

import poyrazinan.com.tr.tuccar.Tuccar;
import poyrazinan.com.tr.tuccar.Utils.getLang;

public class setFancyNpc {

    public static void cmd(String[] args, CommandSender sender) {
        if (args[0].equalsIgnoreCase("fancynpc")) {
            // Gönderenin oyuncu olup olmadığını kontrol et
            if (sender instanceof Player) {
                Player player = (Player) sender;

                // Oyuncunun iznini kontrol et
                if (player.hasPermission("tuccar.belirle")) {

                    Bukkit.getScheduler().runTask(Tuccar.instance, () -> {
                        // FancyNpcs eklentisinin yüklü olup olmadığını kontrol et
                        if (Bukkit.getPluginManager().getPlugin("FancyNpcs") == null) {
                            player.sendMessage(getLang.getText("Messages.fancyNpcPluginMissing"));
                            return;
                        }

                        try {
                            // FancyNpcs API'sini al
                            FancyNpcsPlugin fancyNpcsPlugin = FancyNpcsPlugin.get();

                            // Oyuncunun bir NPC seçip seçmediğini kontrol et
                            Npc selectedNpc = null;
                            double closestDistance = 5.0; // NPC tespit etmek için maksimum mesafe

                            for (Npc npc : fancyNpcsPlugin.getNpcManager().getAllNpcs()) {
                                if (npc.getData().getLocation().getWorld().equals(player.getWorld())) {
                                    double distance = npc.getData().getLocation().distance(player.getLocation());
                                    if (distance < closestDistance) {
                                        closestDistance = distance;
                                        selectedNpc = npc;
                                    }
                                }
                            }

                            if (selectedNpc == null) {
                                player.sendMessage(getLang.getText("Messages.fancyNpcNotFound"));
                                return;
                            }

                            // NPC ID'sini config dosyasına kaydet
                            Tuccar.instance.getConfig().set("fancynpc.id", selectedNpc.getData().getId());
                            Tuccar.instance.saveConfig();

                            player.sendMessage(getLang.getText("Messages.setFancyNpcSuccess"));
                        } catch (Exception e) {
                            player.sendMessage(Tuccar.color("&cFancyNpcs API'si ile iletişim kurarken bir hata oluştu!"));
                            e.printStackTrace();
                        }
                    });
                } else {
                    player.sendMessage(Tuccar.color(getLang.getText("Messages.dontHavePerm")));
                }
            } else {
                sender.sendMessage(Tuccar.color("&4Bunun için oyuncu olman gerek!"));
            }
        }
    }
}