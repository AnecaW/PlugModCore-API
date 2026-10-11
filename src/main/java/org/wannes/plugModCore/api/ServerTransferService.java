package org.wannes.plugModCore.api;

/** Optional real proxy bridge. Implementations must enforce their network's trust configuration. */
public interface ServerTransferService {
    java.util.Set<String> servers();
    /** True means a request was accepted by the bridge; it does not confirm player arrival. */
    boolean requestTransfer(org.bukkit.entity.Player player, String server);
}
