package com.dumptruckman.actionmenu2.api.event;

public interface MenuItemListener {

    void onAction(MenuItemEvent event);

    void onMenuItemChange(MenuItemEvent event);
}
