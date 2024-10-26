package lol.stompy.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import lol.stompy.menu.button.Button;

import java.util.Map;
import java.util.Optional;

public abstract class Menu {

    private final String title;
    protected final Player player;
    private final int size;

    /**
     * Menuu
     *
     * @param title  title of the menu
     * @param size   size of the menu
     * @param player player to set menu to
     */

    public Menu(String title, int size, Player player) {
        this.title = title;
        this.size = size;
        this.player = player;
    }

    /**
     * Updates the menu
     */

    public final void updateMenu() {
        final Inventory inventory = Bukkit.createInventory(null, size, title);

        getButtonMap().forEach((integer, button) -> {
            inventory.setItem(integer, button.getItemStack());
        });

        MenuHandler.getInstance().set(player, this);
        player.openInventory(inventory);
    }

    /**
     * Gets the title of the menu
     *
     * @return {@link String}
     */

    public String getTitle() {
        return title;
    }

    /**
     * Gets the size of the menu
     *
     * @return {@link Integer}
     */

    public int getSize() {
        return size;
    }

    /**
     * Finds a button in the menu
     *
     * @param slot slot to find the menu of
     * @return {@link Optional<Button>}
     */

    public final Optional<Button> findButton(int slot) {
        return Optional.ofNullable(getButtonMap().get(slot));
    }

    /**
     * Gets the button map
     *
     * @return {@link Map<Integer, Button>}
     */

    public abstract Map<Integer, Button> getButtonMap();

}