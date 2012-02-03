package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuModel;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.MenuView;
import com.dumptruckman.actionmenu2.api.MenuViews;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

class DefaultMenu implements Menu {

    private MenuModel model;
    private Plugin plugin = null;
    private Player player = null;
    private MenuViews views = new DefaultMenuViews();

    protected DefaultMenu(Plugin p, final MenuModel m, final MenuView v) {
        this.plugin = p;
        this.model = m;
        if (v != null) {
            this.views.add(v);
        }
    }

    protected DefaultMenu(Plugin plugin, MenuView v) {
        this(plugin, new DefaultModel(), v);
    }

    protected DefaultMenu(Plugin plugin) {
        this(plugin, null);
    }

    @Override
    public MenuModel getModel() {
        return this.model;
    }

    @Override
    public void setModel(MenuModel model) {
        this.model = model;
    }

    @Override
    public MenuItem getSelected() {
        if (this.getModel().getSelectedIndex() < 0) {
            return null;
        }
        return this.getModel().get(this.getModel().getSelectedIndex());
    }

    @Override
    public void cycleSelection() {
        this.cycleSelection(false);
    }

    @Override
    public final void cycleSelection(final boolean reverse) {
        MenuModel model = this.getModel();
        if (model.isEmpty()) {
            model.setSelectedIndex(-1);
            return;
        }
        int index = model.getSelectedIndex();
        for (int step = reverse ? -1 : 1,
                     newIndex = selectionStep(index, step, model.size());
             newIndex != index;
             newIndex = this.selectionStep(newIndex, step, model.size())) {
            if (newIndex < 0) {
                continue;
            }
            if (model.get(newIndex).isSelectable()) {
                index = newIndex;
                break;
            }
        }
        model.setSelectedIndex(index);
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
