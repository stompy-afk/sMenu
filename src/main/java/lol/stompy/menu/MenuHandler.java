package lol.stompy.menu;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class MenuHandler {

    private final Map<UUID, Menu> menuMap;
    private final JavaPlugin javaPlugin;

    private static MenuHandler instance;

    /**
     * MenuHandler handles all menu related activites
     *
     * @param javaPlugin plugin
     */

    public MenuHandler(JavaPlugin javaPlugin) {
        this.menuMap = new HashMap<>();
        this.javaPlugin = javaPlugin;

        instance = this;
        new MenuListener(this);
    }

    /**
     * Sets the player to a menu
     *
     * @param player player to set menu to
     * @param menu   menu to set the player to
     */

    public final void set(Player player, Menu menu) {
        if (menuMap.containsKey(player.getUniqueId())) {
            final Menu playerMenu = menuMap.get(player.getUniqueId());

            if (playerMenu.getTitle().equalsIgnoreCase(menu.getTitle()))
                player.updateInventory();
            else
                player.closeInventory();
        }

        menuMap.put(player.getUniqueId(), menu);
    }

    /**
     * Removes a player
     *
     * @param player player to remove menu from
     */

    public final void remove(Player player) {
        if (menuMap.containsKey(player.getUniqueId())) {
            player.closeInventory();
        }

        menuMap.remove(player.getUniqueId());
    }

    /**
     * finds the menu of a player
     *
     * @param player player to find menu of
     * @return {@link Optional<Menu>}
     */

    public final Optional<Menu> findMenu(Player player) {
        return Optional.ofNullable(menuMap.get(player.getUniqueId()));
    }

    /**
     * Gets the instance of the menu handler
     *
     * @return {@link MenuHandler}
     */

    public static MenuHandler getInstance() {
        return instance;
    }

    /**
     * Gets the plugin
     *
     * @return {@link JavaPlugin}
     */

    public JavaPlugin getJavaPlugin() {
        return javaPlugin;
    }

}
