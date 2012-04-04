package com.dumptruckman.minecraft.actionmenu2.api.event;

import com.dumptruckman.minecraft.actionmenu2.api.MenuBlock;
import com.dumptruckman.minecraft.actionmenu2.api.MenuUser;

public interface MenuListener<U extends MenuUser, B extends MenuBlock> {

    public void onContentsAdd(MenuEvent<U, B> event);

    public void onContentsRemove(MenuEvent<U, B> event);

    public void onContentsChange(MenuEvent<U, B> event);

    public void onSelectionChange(MenuEvent<U, B> event);
    
    public void onModelChange(ModelChangeEvent<U, B> event);
}
