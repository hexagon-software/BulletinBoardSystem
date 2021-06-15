package de.hexagonsoftware.abec.file;

import java.io.FileInputStream;
import java.io.IOException;

public class ConfigLoader {
    public static PGProperties loadConfig(String path) {
        PGProperties prop = new PGProperties();
        try {
            prop.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;
    }
}
