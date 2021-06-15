package de.hexagonsoftware.sbec;

import de.hexagonsoftware.abec.file.ConfigLoader;
import de.hexagonsoftware.abec.file.PGProperties;
import de.hexagonsoftware.abec.text.Logger;

import java.io.IOException;

public class Main {
    public static PGProperties CONFIG;

    public static void main(String[] args) throws IOException {
        Logger.info("Starting...");
        CONFIG = ConfigLoader.loadConfig("server_files/config.properties");
        ServerThread st = new ServerThread();
        st.start(CONFIG.getPropertyAsInt("server.port"));
    }
}
