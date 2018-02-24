package nelsonalfo.tmdbunittestsapp.api;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Interceptor que se encarga de realizar una consulta a la cache en caso de que se este offline
 */
public class OfflineCacheInterceptor implements Interceptor {
    private static final int ONE_DAY_IN_MINUTES = 60 * 60 * 24;

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        try {
            return chain.proceed(chain.request());
        } catch (Exception e) {
            final Request offlineRequest = chain.request().newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + ONE_DAY_IN_MINUTES)
                    .build();

            return chain.proceed(offlineRequest);
        }
    }
}
