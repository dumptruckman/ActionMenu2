package com.dumptruckman.minecraft.actionmenu2.impl;

import com.dumptruckman.minecraft.actionmenu2.api.Menu;
import com.dumptruckman.minecraft.actionmenu2.api.MenuModel;
import com.dumptruckman.minecraft.actionmenu2.api.MenuView;
import org.bukkit.block.Sign;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Menus {

    private Menus() {
        throw new AssertionError();
    }

    public static Menu<BukkitPlugin, BukkitPlayer> newMenu(Plugin plugin, MenuModel model, MenuView view) throws IllegalArgumentException {
        return new DefaultMenu<BukkitPlugin, BukkitPlayer>(BukkitPlugin.get(plugin), model, view);
    }
    
    public static Menu<BukkitPlugin, BukkitPlayer> newMenu(Plugin plugin, MenuModel model) throws IllegalArgumentException {
        return new DefaultMenu<BukkitPlugin, BukkitPlayer>(BukkitPlugin.get(plugin), model);
    }
    
    private static Menu<BukkitPlugin, BukkitPlayer> newMenu(Plugin plugin, MenuView view) {
        return new DefaultMenu<BukkitPlugin, BukkitPlayer>(BukkitPlugin.get(plugin), view);
    }
    
    public static Menu<BukkitPlugin, BukkitPlayer> newMenu(Plugin plugin, Sign sign) {
        Menu<BukkitPlugin, BukkitPlayer> menu = newMenu(plugin);
        DefaultSignView view = new DefaultSignView(plugin, sign);
        menu.getModel().getMenuListeners().add(view);
        menu.getViews().add(view);
        return menu;
    }
    
    public static Menu newMenu(Plugin plugin, Sign sign, Class<? extends MenuView> viewClass)
            throws IllegalArgumentException {
        Constructor<? extends MenuView> constructor = null;
        try {
            constructor = viewClass.getConstructor(Plugin.class, Sign.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException(viewClass.getName() + " is not a valid MenuView");
        } catch (SecurityException e) {
            throw new IllegalArgumentException(viewClass.getName() + " is not a valid MenuView");
        }
        MenuView view = null;
        try {
            view = constructor.newInstance(plugin, sign);
        } catch (InstantiationException e) {
            throw new IllegalArgumentException(viewClass.getName() + " is not a valid MenuView");
        } catch (IllegalAccessException e) {
            throw new IllegalArgumentException(viewClass.getName() + " is not a valid MenuView");
        } catch (InvocationTargetException e) {
            throw new IllegalArgumentException(viewClass.getName() + " is not a valid MenuView");
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(viewClass.getName() + " is not a valid MenuView");
        }
        try {
            return newMenu(plugin, view);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(viewClass.getName() + " is not a valid MenuView");
        }
    }

    public static Menu<BukkitPlugin, BukkitPlayer> newMenu(Plugin plugin) {
        return new DefaultMenu<BukkitPlugin, BukkitPlayer>(BukkitPlugin.get(plugin));
    }
    
    public static MenuModel newMenuModel() {
        return new DefaultModel();
    }
}
