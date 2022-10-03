package net.wntiv.inventorymanager.client.window;

public abstract class InventoryWindow {
    protected boolean open = false;
    protected final Identifier id;
//    Class<? extends InventoryWindowProvider> clazz;

    protected InventoryWindow(Identifier id) {
        this.id = id;
    }

    public void setOpen(boolean open) {
        this.open = open;
        if(open) onOpen();
        else onClose();
    }

    public void onOpen(){}
    public void onClose(){}

    public abstract void onSelect();

    protected static abstract class Identifier {
        public abstract boolean equals(Identifier id);
    }
}
