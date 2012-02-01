package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuHandle;
import com.dumptruckman.actionmenu2.api.MenuView;
import org.bukkit.block.Sign;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Menus {

    private Menus() {
        throw new AssertionError();
    }
    
    private static MenuHandle newMenuHandle(Plugin plugin, Sign sign, MenuView view) {
        return new DefaultHandle(plugin, new DefaultMenu(plugin), view);
    }
    
    public static MenuHandle newMenuHandle(Plugin plugin, Sign sign) {
        return newMenuHandle(plugin, sign, new DefaultSignView(plugin, sign));
    }
    
    public static MenuHandle newMenuHandle(Plugin plugin, Sign sign, Class<? extends MenuView> viewClass)
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
            return newMenuHandle(plugin, sign, view);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException(viewClass.getName() + " is not a valid MenuView");
        }
    }

    public static MenuHandle newMenuHandle(Plugin plugin, Menu menu) {
        return new DefaultHandle(plugin, menu);
    }

    public static MenuHandle newMenuHandle(Plugin plugin) {
        return Menus.newMenuHandle(plugin, new DefaultMenu(plugin));
    }
    
    public static Menu newMenu(Plugin plugin) {
        return new DefaultMenu(plugin);
    }
}
