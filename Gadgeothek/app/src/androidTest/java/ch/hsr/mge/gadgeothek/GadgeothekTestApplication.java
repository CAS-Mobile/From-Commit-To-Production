package ch.hsr.mge.gadgeothek;

import ch.hsr.mge.gadgeothek.http.HttpProxy;
import ch.hsr.mge.gadgeothek.service.LibraryService;

public class GadgeothekTestApplication extends GadgeothekApplication {

    public static HttpProxy httpProxy;

    @Override
    protected LibraryService createLibraryService(String url) {
        httpProxy = new HttpProxy();
        return new LibraryService(url, httpProxy);
    }
}
