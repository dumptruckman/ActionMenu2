package com.dumptruckman.minecraft.actionmenu2.api.event;

import com.dumptruckman.minecraft.actionmenu2.api.MenuBlock;
import com.dumptruckman.minecraft.actionmenu2.api.MenuModel;
import com.dumptruckman.minecraft.actionmenu2.api.MenuUser;

public class ModelChangeEvent<U extends MenuUser, B extends MenuBlock> {

    private MenuModel<U, B> oldModel;
    private MenuModel<U, B> newModel;

    public ModelChangeEvent(MenuModel<U, B> oldModel, MenuModel<U, B> newModel) {
        this.oldModel = oldModel;
        this.newModel = newModel;
    }

    public MenuModel<U, B> getOldModel() {
        return this.oldModel;
    }

    public MenuModel<U, B> getNewModel() {
        return this.newModel;
    }
}
