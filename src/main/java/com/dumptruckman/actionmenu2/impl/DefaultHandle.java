package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.AbstractHandle;
import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuContents;
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
    public final void cycleMenu(final boolean reverse) {
        MenuContents contents = this.getMenu().getContents();
        if (contents.isEmpty()) {
            contents.setSelectedIndex(-1);
            return;
        }
        int index = contents.getSelectedIndex();
        for (int step = reverse ? -1 : 1, newIndex = index + step; newIndex != index;
                newIndex = this.stepModulo(newIndex, step, contents.size())) {
            if (newIndex < 0) {
                continue;
            }
            if (contents.get(newIndex).isSelectable()) {
                index = newIndex;
                break;
            }
        }
        contents.setSelectedIndex(index);
    }

    private int stepModulo(int index, int step, int size) {
        int newIndex = index + step;
        if (step >= size) {
            newIndex = -1;
        } else if (newIndex < -1) {
            newIndex = size - 1;
        }
        return newIndex;
        //return (index + step + size) % size;
    }
}
