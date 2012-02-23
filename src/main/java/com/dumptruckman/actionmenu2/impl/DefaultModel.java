package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.MenuModel;
import com.dumptruckman.actionmenu2.api.MenuItem;
import com.dumptruckman.actionmenu2.api.event.MenuEvent;
import com.dumptruckman.actionmenu2.api.event.MenuListener;
import com.dumptruckman.actionmenu2.api.util.ForwardingList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

class DefaultModel extends ForwardingList<MenuItem>
        implements MenuModel {

    private final Set<MenuListener> listeners;
    private int selectedIndex;

    private DefaultModel(final List<MenuItem> contents) {
        super(contents);
        this.listeners = new LinkedHashSet<MenuListener>();
        this.selectedIndex = this.size() - 1;
    }

    protected DefaultModel() {
        this(new ArrayList<MenuItem>());
    }

    @Override
    public final int getSelectedIndex() {
        return selectedIndex;
    }

    @Override
    public final void setSelectedIndex(final int index) {
        if (index < -1 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        }
        if (!this.get(index).isSelectable()) {
            return;
        }
        int oldIndex = this.selectedIndex;
        this.selectedIndex = index;
        for (MenuListener listener : this.listeners) {
            listener.onSelectionChange(new MenuEvent(this,
                    MenuEvent.SELECTION_CHANGED,
                    oldIndex, index));
        }
    }

    @Override
    public final boolean add(final MenuItem e) {
        int index = this.size();
        boolean added = super.add(e);
        if (added) {
            if (e.isSelectable() && this.getSelectedIndex() == -1) {
                this.setSelectedIndex(this.size() - 1);
            }
            for (MenuListener listener : this.listeners) {
                listener.onContentsAdd(new MenuEvent(this,
                        MenuEvent.CONTENTS_ADDED,
                        index, index));
            }
        }
        return added;
    }

    @Override
    public final void add(final int index, final MenuItem e) {
        super.add(index, e);
        for (MenuListener listener : this.listeners) {
            listener.onContentsAdd(new MenuEvent(this,
                    MenuEvent.CONTENTS_ADDED,
                    index, index));
        }
        if (this.getSelectedIndex() == -1 && e.isSelectable()) {
            this.setSelectedIndex(index);
        } else if (this.getSelectedIndex() >= index) {
            this.setSelectedIndex(this.getSelectedIndex() + 1);
        }
    }

    @Override
    public final boolean remove(final Object o) {
        int index = this.indexOf(o);
        boolean removed = super.remove(o);
        if (removed) {
            for (MenuListener listener : this.listeners) {
                listener.onContentsRemove(new MenuEvent(this,
                        MenuEvent.CONTENTS_REMOVED,
                        index, index));
            }
            if (this.getSelectedIndex() >= this.size()) {
                this.setSelectedIndex(this.size() - 1);
            }
        }
        return removed;
    }

    @Override
    public final boolean addAll(final Collection<? extends MenuItem> c) {
        int index0 = this.size();
        boolean added = super.addAll(c);
        int index1 = this.size();
        if (added) {
            for (MenuListener listener : this.listeners) {
                listener.onContentsAdd(new MenuEvent(this,
                        MenuEvent.CONTENTS_ADDED,
                        index0, index1));
            }
            if (this.getSelectedIndex() == -1) {
                this.setSelectedIndex(this.getFirstSelectableIndex());
            }
        }
        return added;
    }

    @Override
    public final boolean addAll(final int index,
                                final Collection<? extends MenuItem> c) {
        boolean added = super.addAll(index, c);
        int index1 = index + c.size();
        if (added) {
            for (MenuListener listener : this.listeners) {
                listener.onContentsAdd(new MenuEvent(this,
                        MenuEvent.CONTENTS_ADDED,
                        index, index1));
            }
            if (this.getSelectedIndex() == -1) {
                this.setSelectedIndex(this.getFirstSelectableIndex());
            } else if (this.getSelectedIndex() >= index) {
                this.setSelectedIndex(this.getSelectedIndex() + c.size());
            }
        }
        return added;
    }

    @Override
    public final boolean removeAll(final Collection<?> c) {
        MenuItem newSelectedItem = null;
        for (int i = this.getSelectedIndex(); i >= 0; i--) {
            if (this.get(i).isSelectable() && !c.contains(this.get(i))) {
                newSelectedItem = this.get(i);
                break;
            }
        }
        boolean removed = super.removeAll(c);
        if (removed) {
            for (MenuListener listener : this.listeners) {
                listener.onContentsChange(new MenuEvent(this,
                        MenuEvent.CONTENTS_CHANGED,
                        -1, -1));
            }
            if (newSelectedItem == null) {
                this.setSelectedIndex(this.getFirstSelectableIndex());
            } else {
                this.setSelectedIndex(this.indexOf(newSelectedItem));
            }
        }
        return removed;
    }

    @Override
    public final boolean retainAll(final Collection<?> c) {
        MenuItem newSelectedItem = null;
        for (int i = this.getSelectedIndex(); i >= 0; i--) {
            if (this.get(i).isSelectable() && c.contains(this.get(i))) {
                newSelectedItem = this.get(i);
                break;
            }
        }
        boolean removed = super.retainAll(c);
        if (removed) {
            for (MenuListener listener : this.listeners) {
                listener.onContentsChange(new MenuEvent(this,
                        MenuEvent.CONTENTS_CHANGED,
                        -1, -1));
            }
            if (newSelectedItem == null) {
                this.setSelectedIndex(this.getFirstSelectableIndex());
            } else {
                this.setSelectedIndex(this.indexOf(newSelectedItem));
            }
        }
        return removed;
    }

    @Override
    public final void clear() {
        int index1 = this.size();
        super.clear();
        for (MenuListener listener : this.listeners) {
            listener.onContentsChange(new MenuEvent(this,
                    MenuEvent.CONTENTS_REMOVED,
                    0, index1));
        }
        this.setSelectedIndex(-1);
    }

    @Override
    public final MenuItem set(final int index, final MenuItem element) {
        MenuItem e = super.set(index, element);
        if (this.getSelectedIndex() == index && !element.isSelectable()) {
            this.setSelectedIndex(this.getPreviousSelectableIndex(index));
        }
        for (MenuListener listener : this.listeners) {
            listener.onContentsChange(new MenuEvent(this,
                    MenuEvent.CONTENTS_CHANGED,
                    index, index));
        }
        return e;
    }

    @Override
    public final MenuItem remove(final int index) {
        MenuItem e = super.remove(index);
        for (MenuListener listener : this.listeners) {
            listener.onContentsChange(new MenuEvent(this,
                    MenuEvent.CONTENTS_REMOVED,
                    index, index));
        }
        if (this.getSelectedIndex() == index) {
            this.setSelectedIndex(this.getPreviousSelectableIndex(index));
        }
        return e;
    }

    private int getFirstSelectableIndex() {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).isSelectable()) {
                return i;
            }
        }
        return -1;
    }
    
    private int getPreviousSelectableIndex(int index) {
        for (int i = index - 1; i >= 0; i--) {
            if (this.get(i).isSelectable()) {
                return i;
            }
        }
        return getFirstSelectableIndex();
    }
    
    @Override public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            if (builder.toString().isEmpty()) {
                builder.append("[ ");
            } else {
                builder.append(", ");
            }
            if (i == getSelectedIndex()) {
                builder.append("*").append(i).append("*");
            } else {
                builder.append(i);
            }
            builder.append(" : ").append("\"").append(get(i).toString()).append("\"");
        }
        builder.append(" ]");
        return builder.toString();
    }

    @Override
    public Set<MenuListener> getMenuListeners() {
        return this.listeners;
    }
}
