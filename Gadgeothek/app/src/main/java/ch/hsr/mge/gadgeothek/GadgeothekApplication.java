package ch.hsr.mge.gadgeothek;

import android.app.Application;
import android.content.SharedPreferences;

import ch.hsr.mge.gadgeothek.service.LibraryService;

import static ch.hsr.mge.gadgeothek.ui.AbstractAuthenticationActivity.PREFERENCES;

public class GadgeothekApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        String url = preferences.getString(
                getString(R.string.settings_server_address),
                getString(R.string.settings_default_server));

        LibraryService.setServerAddress(url);

    }
}
