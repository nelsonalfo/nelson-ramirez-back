package nelsonalfo.tmdbunittestsapp.api;

/**
 * Diferentes status y codigos de error que pueden devolver como respuesta las consultas a las apis REST
 */
public interface ApiStatus {
    String NO_RESULT = "NO_RESULT";
    String SERVER_ERROR = "SERVER_ERROR";
    String CLIENT_ERROR = "CLIENT_ERROR";
    String NETWORK_ERROR = "NETWORK_ERROR";

    interface Code {
        int SERVER_ERROR = 500;
        int CLIENT_ERROR = 401;
        int UNSATISFIABLE_REQUEST_ERROR = 504;
    }
}
