package ch.hsr.mge.gadgeothek;

import android.app.Application;
import android.content.SharedPreferences;

import ch.hsr.mge.gadgeothek.service.LibraryService;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import okhttp3.OkHttpClient;

import static ch.hsr.mge.gadgeothek.ui.AbstractAuthenticationActivity.PREFERENCES;

public class GadgeothekApplication extends Application {

    public static LibraryService libraryService;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        SharedPreferences preferences = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        String url = preferences.getString(
                getString(R.string.settings_server_address),
                getString(R.string.settings_default_server));

        libraryService = createLibraryService(url);
    }

    protected LibraryService createLibraryService(String url) {
        return new LibraryService(url, new OkHttpClient());
    }
}
