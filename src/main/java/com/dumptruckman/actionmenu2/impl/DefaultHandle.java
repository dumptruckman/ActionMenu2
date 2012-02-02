package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.AbstractHandle;
import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuContents;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.MenuView;
import org.bukkit.plugin.Plugin;

class DefaultHandle extends AbstractHandle {

    protected DefaultHandle(Plugin p, final Menu m) {
        this(p, m, null);
    }

    protected DefaultHandle(Plugin p, final Menu m, final MenuView v) {
        super(p, m, v);
    }

    @Override
    public final void cycleMenu(final Boolean reverse) {
        MenuContents contents = this.getMenu().getContents();
        int index = contents.getSelectedIndex();
        if (reverse) {
            index--;
        } else {
            index++;
        }
        if (contents.isEmpty()) {
            index = -1;
        } else if (index >= contents.size()) {
            index = 0;
        } else if (index < 0) {
            index = contents.size() - 1;
        }
        contents.setSelectedIndex(index);
        if (!contents.get(index).isSelectable()) {

        }
    }
}
