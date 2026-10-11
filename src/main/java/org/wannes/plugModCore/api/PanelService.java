package org.wannes.plugModCore.api;

/** Resolve from ModuleContext for each call; open must be called on the server thread. */
public interface PanelService {
    boolean open(org.bukkit.entity.Player player, String idOrCommand);
    java.util.List<Panel> panels();
    record Panel(String id, String name, String command, boolean enabled, String permission) {}
}
