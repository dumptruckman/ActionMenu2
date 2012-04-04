package com.dumptruckman.minecraft.actionmenu2.api;

import com.dumptruckman.minecraft.actionmenu2.api.event.MenuItemListener;

import java.awt.*;
import java.util.List;
import java.util.Set;

public interface MenuItem<U extends MenuUser, B extends MenuBlock> {

    Set<MenuItemListener<U, B>> getMenuItemListeners();

    void run();

    void setText(String... text);

    void setLine(int index, String line);

    void setImage(Image image);

    String getText();

    String getFullText();

    List<String> getLines();

    Image getImage();
    
    List<B> getBlocks();

    void setSelectable(boolean selectable);

    boolean isSelectable();

    void update(U user);
}
