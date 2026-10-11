package org.wannes.plugModCore.api;

import org.bukkit.OfflinePlayer;
import java.util.Set;

/** Shared service; consumers resolve it again after a provider module reload. */
public interface PlaceholderService {
    default PlaceholderResult parseDetailed(org.bukkit.OfflinePlayer player, String text) {
        return new PlaceholderResult(parse(player, text), java.util.List.of());
    }
    String parse(OfflinePlayer player, String text);
    String parseBrackets(OfflinePlayer player, String text);
    Set<String> identifiers();
    default AutoCloseable register(ModuleContext owner, PlaceholderProvider provider) {
        throw new UnsupportedOperationException("Provider registration is unsupported by this service version");
    }
}
