package org.wannes.plugModCore.api;

import org.bukkit.Server;
import org.bukkit.command.Command;
import java.util.*;

/** Main-thread, request-scoped view of the public server command registry. */
public final class CommandRegistry {
    private CommandRegistry() {}
    public static Map<String, Command> snapshot(Server server) {
        Objects.requireNonNull(server, "Server is required for command discovery");
        if (!server.isPrimaryThread()) throw new IllegalStateException("Command discovery requires the server thread");
        var map = server.getCommandMap();
        if (map == null) throw new IllegalStateException("Public server command map is unavailable");
        Map<String, Command> result = new LinkedHashMap<>();
        // Paper 1.21.11's Brigadier forwarding map extends HashMap without overriding
        // Map.forEach. That method visits its empty backing table, not live commands.
        // Enumerate the public entry view explicitly; no private server fields are used.
        for (var entry : map.getKnownCommands().entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) result.put(entry.getKey(), entry.getValue());
        }
        return Collections.unmodifiableMap(result);
    }
}
