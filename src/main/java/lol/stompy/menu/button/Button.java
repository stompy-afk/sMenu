package lol.stompy.menu.button;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Button {

    private final ItemStack itemStack;

    private Consumer<InventoryClickEvent> clickEvent = inventoryClickEvent -> inventoryClickEvent.setCancelled(true);
    private int amount;

    /**
     * Creates a button based off an itemstack
     *
     * @param itemStack itemstack to create the button of
     */

    public Button(ItemStack itemStack) {
        this.itemStack = itemStack;
        this.amount = itemStack.getAmount();
    }

    /**
     * Creates a button based off a material
     *
     * @param material material
     */

    public Button(Material material) {
        this.itemStack = new ItemStack(material);
        this.amount = 1;
    }

    /**
     * Creates a button w specified material & amount
     *
     * @param material material of the buton
     * @param amount amount of the button
     */

    public Button(Material material, int amount) {
        this.itemStack = new ItemStack(material, amount);
        this.amount = amount;
    }

    /**
     * Sets the name of the button
     *
     * @param name name to set to
     * @return {@link Button}
     */

    public Button setName(String name) {
        final ItemMeta itemMeta = this.getItemMeta();

        itemMeta.setDisplayName(name);
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    /**
     * Sets the lore of the button
     *
     * @param lore lore to set to
     * @return {@link Button}
     */

    public Button setLore(String... lore) {
        final ItemMeta itemMeta = this.getItemMeta();

        itemMeta.setLore(Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    /**
     * Sets the lore of the button
     *
     * @param lore lore to set to
     * @return {@link Button}
     */

    public Button setLore(List<String> lore) {
        final ItemMeta itemMeta = this.getItemMeta();

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);

        return this;
    }

    /**
     * Adds an unbreaking enchant to the item
     * for it to "glow"
     *
     * @param glow to add it or remove it
     * @return {@link Button}
     */

    public Button setGlow(boolean glow) {
        final ItemMeta itemMeta = this.getItemMeta();

        if (glow) {
            itemMeta.addEnchant(Enchantment.UNBREAKING, 1, true);
        } else {
            if (itemMeta.hasEnchant(Enchantment.UNBREAKING))
                itemMeta.removeEnchant(Enchantment.UNBREAKING);
        }

        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Sets the amount in the button
     *
     * @param amount amount to set it
     * @return {@link Button}
     */

    public Button setAmount(int amount) {
        this.amount = amount;
        this.itemStack.setAmount(amount);
        return this;
    }

    /**
     * Gets the item meta of the itemstack
     * due to new spigot code
     *
     * @return {@link ItemMeta}
     */

    private ItemMeta getItemMeta() {
        ItemMeta meta = itemStack.getItemMeta();

        if (meta == null)
            meta = Bukkit.getItemFactory().getItemMeta(itemStack.getType());

        return meta;
    }

    /**
     * Sets the click event of the button
     *
     * @param clickEvent new action
     * @return {@link Button}
     */

    public final Button setClickEvent(Consumer<InventoryClickEvent> clickEvent) {
        this.clickEvent = clickEvent;
        return this;
    }

    /**
     * Gets the ItemStack of the button
     *
     * @return {@link ItemStack}
     */

    public ItemStack getItemStack() {
        return itemStack;
    }

    /**
     * Gets the amount in the button
     *
     * @return {@link Integer}
     */

    public int getAmount() {
        return amount;
    }

    /**
     * Gets the click event of the button
     *
     * @return {@link Consumer<InventoryClickEvent>}
     */

    public Consumer<InventoryClickEvent> getClickEvent() {
        return clickEvent;
    }


}
