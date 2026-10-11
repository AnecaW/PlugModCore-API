package org.wannes.plugModCore.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/** Published on the server thread. Listeners receive identities, never live module objects. */
public final class ModuleLifecycleEvent extends Event {
    private static final HandlerList HANDLERS = new HandlerList();
    private final String moduleId, previousState, state;
    public ModuleLifecycleEvent(String moduleId, String previousState, String state) { this.moduleId = moduleId; this.previousState = previousState; this.state = state; }
    public String getModuleId() { return moduleId; }
    public String getPreviousState() { return previousState; }
    public String getState() { return state; }
    public HandlerList getHandlers() { return HANDLERS; }
    public static HandlerList getHandlerList() { return HANDLERS; }
}
