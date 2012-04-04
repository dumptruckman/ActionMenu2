package com.dumptruckman.minecraft.actionmenu2.api.event;

import com.dumptruckman.minecraft.actionmenu2.api.MenuBlock;
import com.dumptruckman.minecraft.actionmenu2.api.MenuUser;

public interface MenuItemListener<U extends MenuUser, B extends MenuBlock> {

    public void onAction(MenuItemEvent<U, B> event);

    public void onMenuItemChange(MenuItemEvent<U, B> event);
}
