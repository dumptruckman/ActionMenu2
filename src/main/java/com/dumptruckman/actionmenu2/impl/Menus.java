package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuView;
import org.bukkit.block.Sign;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Menus {

    private Menus() {
        throw new AssertionError();
    }
    
    private static Menu newMenu(Plugin plugin, MenuView view) {
        return new DefaultMenu(plugin, view);
    }
    
    public static Menu newMenu(Plugin plugin, Sign sign) {
        return newMenu(plugin, new DefaultSignView(sign));
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

    public static Menu newMenu(Plugin plugin) {
        return new DefaultMenu(plugin);
    }
}
