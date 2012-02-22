package com.dumptruckman.actionmenu2.impl;

import com.dumptruckman.actionmenu2.api.AbstractSignView;
import com.dumptruckman.actionmenu2.api.Menu;
import com.dumptruckman.actionmenu2.api.MenuModel;
import com.dumptruckman.actionmenu2.api.event.MenuEvent;
import com.dumptruckman.actionmenu2.api.event.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class DefaultSignView extends AbstractSignView implements MenuListener {

    public static final long UPDATE_TICKS = 7;
    public static final int SIGN_LINES = 4;
    public static final int LINE_LENGTH = 13;
    public static final int MAX_TICKERS = 2;

    private int leadIndex = 0;
    private int selectedTextIndex = 0;
    private Plugin plugin;
    private TickerTask ticker;

    public DefaultSignView(Plugin plugin, Sign sign) {
        super(sign);
        this.plugin = plugin;
        ticker = new TickerTask();
    }

    public void updateView(Menu menu, Player player) {
        super.updateView(menu, player);
        this.ticker.update(menu, player);
    }

    @Override
    public void showMenu(Menu menu, Player player) {
        if (menu == null) {
            return;
        }
        this.ticker.start();
        Sign sign = getSign();
        MenuModel menuModel = menu.getModel();
        for (int i = 0, menuIndex = this.leadIndex; i < SIGN_LINES; i++, menuIndex++) {
            String text = "";
            if (menuIndex < menuModel.size()) {
                String menuText = menuModel.get(menuIndex).getText();
                if (menuModel.get(menuIndex).isSelectable()) {
                    if (menuModel.getSelectedIndex() == menuIndex) {
                        int textPosition = this.selectedTextIndex;
                        if (this.selectedTextIndex >= menuText.length() - LINE_LENGTH) {
                            textPosition = menuText.length() - LINE_LENGTH;
                            if (textPosition < 0) {
                                textPosition = 0;
                            }
                        }
                        if (this.selectedTextIndex >= menuText.length()) {
                            this.ticker.tickeredFully();
                            this.selectedTextIndex = 0;
                            textPosition = 0;
                        }
                        text = menuText.substring(textPosition);
                        text = ChatColor.DARK_RED + text;
                    } else {
                        text = " " + menuText;
                    }
                } else {
                    text = menuText;
                }
            }
            StringBuilder builder = new StringBuilder();
            builder.append(text);
            for (int j = text.length(); j <= 16; j++) {
                builder.append(" ");
            }
            text = builder.toString();
            sign.setLine(i, text);
        }
        sign.update(true);
    }

    @Override
    public void onSelectionChange(MenuEvent event) {
        this.selectedTextIndex = 0;
        this.ticker.stop();
        if (event.getIndex1() > event.getIndex0()) {
            int menuSize = event.getModel().size();
            if (event.getIndex1() >= menuSize - 1) {
                this.leadIndex = menuSize - SIGN_LINES;
            } else {
                this.leadIndex = event.getIndex1() - 2;
            }
        } else if (event.getIndex1() < event.getIndex0()) {
            if (event.getIndex1() <= 0) {
                this.leadIndex = 0;
            } else {
                this.leadIndex = event.getIndex1() - 1;
            }
        }
        if (this.leadIndex < 0) {
            this.leadIndex = 0;
        }
    }

    @Override
    public void onContentsAdd(MenuEvent menuEvent) { }

    @Override
    public void onContentsRemove(MenuEvent menuEvent) { }

    @Override
    public void onContentsChange(MenuEvent menuEvent) { }

    private class TickerTask implements Runnable {

        private Menu menu = null;
        private Player player = null;
        private int fullTickers = 0;
        private int taskId = -1;

        public void start() {
            if (this.taskId == -1) {
                schedule();
                this.fullTickers = 0;
            }
        }

        public void stop() {
            Bukkit.getScheduler().cancelTask(this.taskId);
            this.taskId = -1;
            this.fullTickers = 0;
        }

        public void update(Menu menu, Player player) {
            this.menu = menu;
            this.player = player;
        }

        public void tickeredFully() {
            this.fullTickers++;
        }

        private void schedule() {
            this.taskId = Bukkit.getScheduler()
                    .scheduleSyncDelayedTask(DefaultSignView.this.plugin, this, UPDATE_TICKS);
        }

        private void updateSelection() {
            DefaultSignView.this.selectedTextIndex++;
            DefaultSignView.this.updateView(this.menu, this.player);
        }

        @Override
        public void run() {
            if (this.fullTickers < MAX_TICKERS) {
                updateSelection();
                schedule();
            } else {
                stop();
            }
        }
    }
}
