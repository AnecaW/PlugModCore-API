package org.wannes.plugModCore.api;

import java.io.File;
import java.util.logging.Logger;

public interface ModuleContext {

    String getModuleId();
    String getModuleName();
    String getVersion();

    Logger getLogger();
    File getDataFolder();
}
