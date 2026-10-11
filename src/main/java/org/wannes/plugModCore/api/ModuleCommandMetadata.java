package org.wannes.plugModCore.api;

/** Public ownership metadata for commands registered through a module context.
 * A module command remains a Bukkit Command, not a simulated Bukkit plugin.
 * Consumers must query current registration and activation rather than cache this object.
 */
public interface ModuleCommandMetadata {
    String moduleId();
    String moduleName();
    boolean moduleEnabled();
}
