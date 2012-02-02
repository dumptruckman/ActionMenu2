package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.event.MenuItemListener;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.awt.Image;
import java.util.List;
import java.util.Set;

public interface MenuItem {

    Set<MenuItemListener> getMenuItemListeners();

    void run();

    void setText(String...text);

    void setLine(int index, String line);

    void setImage(Image image);

    String getText();

    String getFullText();

    List<String> getLines();

    Image getImage();
    
    List<Block> getBlocks();

    void setSelectable(boolean selectable);

    boolean isSelectable();

    void update(Player player);
}
