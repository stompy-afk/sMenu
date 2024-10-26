package lol.stompy.test;

import lol.stompy.menu.button.Button;
import lol.stompy.menu.enums.MenuSize;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Menu extends lol.stompy.menu.Menu {


    public Menu(Player player) {
        super("Super cool menu", MenuSize.BIG_CHEST.getSize(), player);
    }

    @Override
    public Map<Integer, Button> getButtonMap() {
        final Map<Integer, Button> buttonMap = new HashMap<>();

        buttonMap.put(1, new Button(Material.ACACIA_PLANKS)
                .setName("Cool name").setLore("wow", "wow!")
                .setClickEvent(event -> {
                    player.sendMessage("WOAH DONT DO THAT!");
                    event.setCancelled(true);
                }));

        return buttonMap;
    }

}
