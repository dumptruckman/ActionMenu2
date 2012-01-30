package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.event.MenuItemListener;

import java.awt.Image;
import java.util.List;
import java.util.Set;

public interface MenuItem extends MenuInterface {

    Set<MenuItemListener> getMenuItemListeners();

    void setText(String...text);

    void setLine(int index, String line);

    void setImage(Image image);

    String getText();

    String getFullText();

    List<String> getLines();

    Image getImage();

    void setSelectable(boolean selectable);

    boolean isSelectable();
}
