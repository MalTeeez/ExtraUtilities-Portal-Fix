package net.sxmaa.exuportalfix;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static Integer deep_dark_id = -100;
    public static Integer last_millenium_id = -112;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        deep_dark_id = configuration.getInt("deep_dark_id", Configuration.CATEGORY_GENERAL, deep_dark_id, -999, -2,"The current id of the Deep Dark dimension configured in extrautilities.cfg");
        last_millenium_id = configuration.getInt("last_millenium_id", Configuration.CATEGORY_GENERAL, last_millenium_id, -999, -2,"The current id of the Last Millenium dimension configured in extrautilities.cfg");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
