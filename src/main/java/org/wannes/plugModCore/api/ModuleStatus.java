package org.wannes.plugModCore.api;

/** Immutable diagnostics; does not retain an implementation or classloader. */
public record ModuleStatus(String id, String name, String version, String state) {}
