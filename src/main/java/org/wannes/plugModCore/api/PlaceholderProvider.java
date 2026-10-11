package org.wannes.plugModCore.api;

import org.bukkit.OfflinePlayer;

/** Modules provide conventional %identifier_parameters% placeholders through a shared interface. */
public interface PlaceholderProvider {
    String identifier();
    String resolve(OfflinePlayer player, String parameters);
    default String version() { return "1.0"; }
}
