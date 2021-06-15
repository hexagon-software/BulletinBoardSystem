package de.hexagonsoftware.sbec;

import de.hexagonsoftware.abec.file.ConfigLoader;
import de.hexagonsoftware.abec.file.PGProperties;
import de.hexagonsoftware.abec.text.Logger;

public class Main {
    public static PGProperties CONFIG;

    public static void main(String[] args) {
        Logger.info("Starting...");
        CONFIG = ConfigLoader.loadConfig("server_files/config.properties");
    }
}
