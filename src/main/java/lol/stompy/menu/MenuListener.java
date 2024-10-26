package lol.stompy.menu;

import lol.stompy.menu.button.Button;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import java.util.Optional;

public class MenuListener implements Listener {

    private final MenuHandler menuHandler;

    /**
     * Menu listener, handles all menu events
     *
     * @param menuHandler instance of menu handler
     */

    public MenuListener(MenuHandler menuHandler) {
        this.menuHandler = menuHandler;
        menuHandler.getJavaPlugin().getServer().getPluginManager().registerEvents(this, menuHandler.getJavaPlugin());
    }

    /**
     * on player click event
     *
     * @param event event to follow
     */

    @EventHandler
    public final void onPlayerClickInventoryEvent(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player))
            return;

        final Inventory inventory = event.getClickedInventory();

        if (inventory instanceof PlayerInventory || inventory == null)
            return;

        final Player player = (Player) event.getWhoClicked();
        final Optional<Menu> menuOptional = menuHandler.findMenu(player);

        if (menuOptional.isEmpty())
            return;

        final Menu menu = menuOptional.get();

        if (inventory.getSize() != menu.getSize())
            return;

        if (!player.getOpenInventory().getTitle().equalsIgnoreCase(menu.getTitle()))
            return;

        final Optional<Button> buttonOptional = menu.findButton(event.getSlot());

        if (buttonOptional.isEmpty()) {
            event.setCancelled(true);
            return;
        }

        final Button button = buttonOptional.get();
        button.getClickEvent().accept(event);
    }

    /**
     * Event to remove player from hashmap if he closes inventory
     *
     * @param event event to follow
     */

    @EventHandler
    public final void onPlayerCloseInventoryEvent(InventoryCloseEvent event) {

        if (!(event.getPlayer() instanceof Player))
            return;

        final Inventory inventory = event.getInventory();

        if (inventory instanceof PlayerInventory)
            return;

        final Player player = (Player) event.getPlayer();
        final Optional<Menu> menuOptional = menuHandler.findMenu(player);

        if (menuOptional.isEmpty())
            return;

        menuHandler.remove(player);
    }

}
