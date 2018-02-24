package nelsonalfo.tmdbunittestsapp.api;

import android.content.Context;
import android.support.annotation.NonNull;

import java.io.File;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static nelsonalfo.tmdbunittestsapp.models.Constants.API_BASE_URL;


/**
 * Esta clase se encarga de configurar y generar una instancia de retrofit para poder consultar las apis Rest de TMDb.
 * Tambien se maneja el tema de la cache para usar la app de forma offline haciendo uso de la clase {@link  OfflineCacheInterceptor}
 */
public class ApiServiceGenerator {
    private static final int MB_10 = 10 * 1024 * 1024;

    private final Retrofit retrofit;

    public ApiServiceGenerator(Context context) {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        final OfflineCacheInterceptor cacheInterceptor = new OfflineCacheInterceptor();
        final Cache cache = generateCacheObject(context);

        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(cacheInterceptor);

        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.client(httpClient.build()).build();
    }

    @NonNull
    private Cache generateCacheObject(Context context) {
        final File httpCacheDirectory = new File(context.getCacheDir(), "httpCache");
        return new Cache(httpCacheDirectory, MB_10);
    }

    public TheMovieDbRestApi createClient() {
        return retrofit.create(TheMovieDbRestApi.class);
    }
}
