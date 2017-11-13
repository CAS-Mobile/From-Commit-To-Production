package ch.hsr.mge.gadgeothek.service;

import java.util.HashMap;
import java.util.Map;

public class Configuration {
    static Configuration fromMap(Map<String, Boolean> values) {
        Configuration configuration = new Configuration();
        for(String featureName : values.keySet()) {
            configuration.addFeature(featureName, values.get(featureName));
        }

        return configuration;
    }

    private final Map<String, Boolean> config = new HashMap<String, Boolean>();

    public boolean isEnabled(String featureName) {
        return config.containsKey(featureName) && config.get(featureName);
    }

    public void addFeature(String featureName, boolean enabled) {
        config.put(featureName, enabled);
    }
}
