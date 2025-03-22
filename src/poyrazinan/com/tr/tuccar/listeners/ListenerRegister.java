package poyrazinan.com.tr.tuccar.listeners;

import org.bukkit.Bukkit;

import poyrazinan.com.tr.tuccar.Tuccar;
import poyrazinan.com.tr.tuccar.listeners.Guis.CategorySelectionListener;
import poyrazinan.com.tr.tuccar.listeners.Guis.ConfirmationGuiListener;
import poyrazinan.com.tr.tuccar.listeners.Guis.ItemGuiListener;
import poyrazinan.com.tr.tuccar.listeners.Guis.ItemSelectionListener;
import poyrazinan.com.tr.tuccar.listeners.Guis.PlayerProductsListener;

public class ListenerRegister {
	
	public Tuccar plugin;

	private Listeners listeners;
	private CategorySelectionListener categorySelectionListener;
	private ItemSelectionListener itemSelectionListener;
	private ItemGuiListener itemGuiListener;
	private ConfirmationGuiListener confirmationGuiListener;
	private PlayerProductsListener playerProductsListener;
	private FancyNpcsListener fancyNpcsListener;
	
	public ListenerRegister() {
		this.listeners = new Listeners(Tuccar.instance);
		Bukkit.getPluginManager().registerEvents(this.listeners, Tuccar.instance);
		this.categorySelectionListener = new CategorySelectionListener(Tuccar.instance);
		Bukkit.getPluginManager().registerEvents(this.categorySelectionListener, Tuccar.instance);
		this.itemSelectionListener = new ItemSelectionListener(Tuccar.instance);
		Bukkit.getPluginManager().registerEvents(this.itemSelectionListener, Tuccar.instance);
		this.itemGuiListener = new ItemGuiListener(Tuccar.instance);
		Bukkit.getPluginManager().registerEvents(this.itemGuiListener, Tuccar.instance);
		this.confirmationGuiListener = new ConfirmationGuiListener(Tuccar.instance);
		Bukkit.getPluginManager().registerEvents(this.confirmationGuiListener, Tuccar.instance);
		this.playerProductsListener = new PlayerProductsListener(Tuccar.instance);
		Bukkit.getPluginManager().registerEvents(this.playerProductsListener, Tuccar.instance);
		if(Bukkit.getPluginManager().getPlugin("FancyNpcs") != null) {
			try {
				this.fancyNpcsListener = new FancyNpcsListener(Tuccar.instance);
				Bukkit.getPluginManager().registerEvents(this.fancyNpcsListener, Tuccar.instance);
				Tuccar.instance.getLogger().info("FancyNpcs desteği etkinleştirildi!");
			} catch (Exception e) {
				Tuccar.instance.getLogger().warning("FancyNpcs desteği etkinleştirilemedi: " + e.getMessage());
			}
		}
	}

}
