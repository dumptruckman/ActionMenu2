package com.dumptruckman.actionmenu2.api.event;

public interface MenuListener {

    public void onContentsAdd(MenuEvent event);

    public void onContentsRemove(MenuEvent event);

    public void onContentsChange(MenuEvent event);

    public void onSelectionChange(MenuEvent event);
}