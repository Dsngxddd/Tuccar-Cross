package poyrazinan.com.tr.tuccar.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.oliver.fancynpcs.api.events.NpcInteractEvent;
import de.oliver.fancynpcs.api.Npc;

import poyrazinan.com.tr.tuccar.Tuccar;
import poyrazinan.com.tr.tuccar.Utils.getLang;
import poyrazinan.com.tr.tuccar.gui.CategorySelectionGUI;

public class FancyNpcsListener implements Listener {

    private final Tuccar plugin;

    public FancyNpcsListener(Tuccar plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onFancyNPCClick(NpcInteractEvent event) {
        // FancyNpcs, ActionTrigger.RIGHT_CLICK ve LEFT_CLICK arasında ayrım yaptığı için
        // NpcInteractEvent daha genel bir eventi handle ediyoruz

        Npc npc = event.getNpc();
        String npcId = npc.getData().getId();
        Player player = event.getPlayer();

        if (Tuccar.instance.getConfig().isSet("fancynpc.id") &&
                Tuccar.instance.getConfig().getString("fancynpc.id").equals(npcId)) {

            // İzin verilen dünya kontrolü
            if (Tuccar.instance.getConfig().getBoolean("Settings.world.worldWhitelist")) {
                java.util.List<String> allowedWorlds = Tuccar.instance.getConfig().getStringList("Settings.world.allowedWorlds");
                if (allowedWorlds.contains(player.getWorld().getName()) || player.isOp()) {
                    openTuccarMenu(player);
                } else {
                    player.sendMessage(getLang.getText("Messages.notInAllowedWorld"));
                }
            } else {
                openTuccarMenu(player);
            }
        }
    }

    private void openTuccarMenu(Player player) {
        Bukkit.getScheduler().runTask(Tuccar.instance, () -> {
            CategorySelectionGUI.createGui(player);
        });
    }
}