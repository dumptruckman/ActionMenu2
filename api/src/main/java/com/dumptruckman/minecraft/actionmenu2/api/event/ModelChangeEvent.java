package com.dumptruckman.minecraft.actionmenu2.api.event;

import com.dumptruckman.minecraft.actionmenu2.api.MenuModel;

public class ModelChangeEvent {

    private MenuModel oldModel;
    private MenuModel newModel;

    public ModelChangeEvent(MenuModel oldModel, MenuModel newModel) {
        this.oldModel = oldModel;
        this.newModel = newModel;
    }

    public MenuModel getOldModel() {
        return this.oldModel;
    }

    public MenuModel getNewModel() {
        return this.newModel;
    }
}
