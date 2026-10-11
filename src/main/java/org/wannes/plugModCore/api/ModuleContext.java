package org.wannes.plugModCore.api;

import java.io.File;
import java.util.logging.Logger;

public interface ModuleContext {

    String getModuleId();
    String getModuleName();
    String getVersion();

    Logger getLogger();
    File getDataFolder();
    org.bukkit.plugin.Plugin getHostPlugin();
    /** Fresh main-thread snapshot; never retain this across provider/module reloads. */
    default java.util.Map<String, org.bukkit.command.Command> getRegisteredCommands() {
        return CommandRegistry.snapshot(getHostPlugin().getServer());
    }
    void registerListener(org.bukkit.event.Listener listener);
    org.bukkit.scheduler.BukkitTask schedule(Runnable action, long delayTicks, long periodTicks, boolean async);
    <T extends AutoCloseable> T manage(T resource);
    <T> void registerService(Class<T> type, T service);
    <T> T getService(Class<T> type);
    /** Optional shared API integration. Resolve on each use; provider absence/reload is normal. */
    default <T> T getOptionalService(Class<T> type) { return getService(type); }
    default <T> T requireService(Class<T> type, String provider) {
        T service = getService(type);
        if (service == null) throw new IllegalStateException("Module " + getModuleId() + " requires service " + type.getName() + " from " + provider + "; enable the provider and check its service registration");
        return service;
    }
    default java.util.Optional<ModuleStatus> findModule(String idOrName) { return java.util.Optional.empty(); }
    /** Common text-rendering entry point. Declare the placeholderapi dependency when using it. */
    default String renderText(org.bukkit.OfflinePlayer player, String configuredText) {
        var placeholders = getService(PlaceholderService.class);
        return placeholders == null ? configuredText : placeholders.parse(player, configuredText);
    }
    void registerIncomingChannel(String channel, org.bukkit.plugin.messaging.PluginMessageListener listener);
    void registerOutgoingChannel(String channel);
    /**
     * Register a command for this module. The implementation should register
     * a server command that delegates execution to the provided executor.
     */
    void registerCommand(String name, org.bukkit.command.CommandExecutor executor, String description, String usage, String... aliases);
    /** Dynamic registrations can be released before module shutdown, on the server thread. */
    default AutoCloseable registerManagedCommand(String name, org.bukkit.command.CommandExecutor executor, String description, String usage, String... aliases) {
        throw new UnsupportedOperationException("This core does not support dynamic managed commands; upgrade PlugModCore.");
    }
}
