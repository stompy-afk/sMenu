package lol.stompy.menu.enums;

public enum MenuSize {

    SMALL_CHEST(26),
    BIG_CHEST(47);

    private final int size;

    MenuSize(int size) {
        this.size = size;
    }

    public final int getSize() {
        return size;
    }

}
