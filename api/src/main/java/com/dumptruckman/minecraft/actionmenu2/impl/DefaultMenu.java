package com.dumptruckman.minecraft.actionmenu2.impl;

import com.dumptruckman.minecraft.actionmenu2.api.Menu;
import com.dumptruckman.minecraft.actionmenu2.api.MenuItem;
import com.dumptruckman.minecraft.actionmenu2.api.MenuModel;
import com.dumptruckman.minecraft.actionmenu2.api.MenuView;
import com.dumptruckman.minecraft.actionmenu2.api.MenuViews;
import com.dumptruckman.minecraft.actionmenu2.api.event.MenuEvent;
import com.dumptruckman.minecraft.actionmenu2.api.event.MenuListener;
import com.dumptruckman.minecraft.actionmenu2.api.event.ModelChangeEvent;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.Set;

class DefaultMenu implements Menu {

    private MenuModel model;
    private Plugin plugin = null;
    private Player player = null;
    private MenuViews views;

    protected DefaultMenu(Plugin p, MenuModel m) {
        if (m == null) {
            throw new IllegalArgumentException("Model may not be null!");
        }
        if (p == null) {
            throw new IllegalArgumentException("Plugin may not be null!");
        }
        this.plugin = p;
        this.model = m;
        this.views = new DefaultMenuViews(p, this);
        
        this.model.getMenuListeners().add(this);
    }

    protected DefaultMenu(Plugin p, final MenuModel m, final MenuView v) {
        this(p, m);
        if (v != null) {
            this.views.add(v);
        }
    }

    protected DefaultMenu(Plugin p, MenuView v) {
        this(p, new DefaultModel(), v);
    }

    protected DefaultMenu(Plugin p) {
        this(p, (MenuView)null);
    }

    @Override
    public MenuModel getModel() {
        return this.model;
    }

    @Override
    public void setModel(MenuModel model) {
        if (model == null) {
            throw new IllegalArgumentException("model may not be null!");
        }
        this.getMenuListeners().remove(this);
        for (MenuView view : this.views) {
            this.getMenuListeners().remove(view);
        }
        ModelChangeEvent event = new ModelChangeEvent(this.model, model);
        this.model = model;
        this.getMenuListeners().add(this);
        for (MenuView view : this.views) {
            this.getMenuListeners().add(view);
        }
        for (MenuListener listener : getMenuListeners()) {
            listener.onModelChange(event);
        }
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
    
    @Override
    public void updateViews() {
        this.views.update(this.getUser());
    }

    @Override
    public Set<MenuListener> getMenuListeners() {
        return this.getModel().getMenuListeners();
    }

    @Override
    public void onContentsAdd(MenuEvent event) {

    }

    @Override
    public void onContentsRemove(MenuEvent event) {

    }

    @Override
    public void onContentsChange(MenuEvent event) {

    }

    @Override
    public void onSelectionChange(MenuEvent event) {

    }

    @Override
    public void onModelChange(ModelChangeEvent event) {

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getCanonicalName());
        builder.append(": { Model : ").append(getModel().toString());
        builder.append(", Views : ").append(getViews().toString());
        builder.append(" }");
        return builder.toString();
    }
}
