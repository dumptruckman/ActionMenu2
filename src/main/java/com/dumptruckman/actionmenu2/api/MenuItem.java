package com.dumptruckman.actionmenu2.api;

import com.dumptruckman.actionmenu2.api.event.MenuItemListener;

import java.awt.Image;
import java.util.List;

public interface MenuItem extends MenuInterface {

    List<MenuItemListener> getMenuItemListeners();

    void setText(String...text);

    void setLine(int index, String line);

    void setImage(Image image);

    String getText();

    String getFullText();

    List<String> getLines();

    Image getImage();
}
