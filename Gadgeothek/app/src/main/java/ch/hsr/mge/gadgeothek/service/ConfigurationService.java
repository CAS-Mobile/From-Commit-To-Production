package ch.hsr.mge.gadgeothek.service;


import java.util.HashMap;
import java.util.Map;

public class ConfigurationService {

    private final String configurationUrl;

    public ConfigurationService(String configurationUrl) {
        this.configurationUrl = configurationUrl;
    }

    public void getConfiguration(final Callback<Configuration> callback) {
        Request<Map<String, Boolean>> request = new Request<>(HttpVerb.GET, configurationUrl, Map.class, new HashMap<String, String>(), new HashMap<String, String>(), new Callback<Map<String, Boolean>>() {
            @Override
            public void onCompletion(Map<String, Boolean> input) {
                callback.onCompletion(Configuration.fromMap(input));
            }

            @Override
            public void onError(String message) {
                callback.onError(message);
            }
        });
        request.execute();
    }
}
