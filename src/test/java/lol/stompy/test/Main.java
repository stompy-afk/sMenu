package lol.stompy.test;

import lol.stompy.menu.MenuHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        new MenuHandler(this);
    }

    @EventHandler
    public final void onPlayerJoin(PlayerJoinEvent event) {
        new Menu(event.getPlayer()).updateMenu();
    }
}
