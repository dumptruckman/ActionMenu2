package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuContents;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.MenuView;
import com.dumptruckman.actionmenu2.api.MenuViews;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

class DefaultMenu implements Menu {

    private MenuContents contents;
    private Plugin plugin = null;
    private Player player = null;
    private MenuViews views = new DefaultMenuViews();

    protected DefaultMenu(Plugin p, final MenuContents c, final MenuView v) {
        this.plugin = p;
        this.contents = c;
        if (v != null) {
            this.views.add(v);
        }
    }

    protected DefaultMenu(Plugin plugin, MenuView v) {
        this(plugin, new DefaultContents(), v);
    }

    protected DefaultMenu(Plugin plugin) {
        this(plugin, null);
    }

    @Override
    public MenuContents getContents() {
        return this.contents;
    }

    @Override
    public MenuItem getSelected() {
        if (this.getContents().getSelectedIndex() < 0) {
            return null;
        }
        return this.getContents().get(this.getContents().getSelectedIndex());
    }

    @Override
    public void cycleSelection() {
        this.cycleSelection(false);
    }

    @Override
    public final void cycleSelection(final boolean reverse) {
        MenuContents contents = this.getContents();
        if (contents.isEmpty()) {
            contents.setSelectedIndex(-1);
            return;
        }
        int index = contents.getSelectedIndex();
        for (int step = reverse ? -1 : 1,
                     newIndex = selectionStep(index, step, contents.size());
             newIndex != index;
             newIndex = this.selectionStep(newIndex, step, contents.size())) {
            if (newIndex < 0) {
                continue;
            }
            if (contents.get(newIndex).isSelectable()) {
                index = newIndex;
                break;
            }
        }
        contents.setSelectedIndex(index);
        this.getSelected().update(this.getUser());
    }

    private int selectionStep(int index, int step, int size) {
        int newIndex = index + step;
        if (newIndex >= size) {
            newIndex = -1;
        } else if (newIndex < -1) {
            newIndex = size - 1;
        }
        return newIndex;
    }

    @Override
    public void setUser(Player player) {
        this.player = player;
        this.views.update(this.getPlugin(), this, player);
    }

    protected Plugin getPlugin() {
        return this.plugin;
    }

    @Override
    public Player getUser() {
        return this.player;
    }

    @Override
    public MenuViews getViews() {
        return this.views;
    }
}
