package org.wannes.plugModCore.api;

public interface Module {
    /** Called once per fresh instance, before its first enable. */
    default void onLoad(ModuleContext context) {}
    /** Called before the instance and its classloader are released. */
    default void onUnload() {}

    void onEnable(ModuleContext context);
    void onDisable();
}
