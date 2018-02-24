package nelsonalfo.tmdbunittestsapp.command;

/**
 * Interface que deben implementar todas las clases que deseen ser comandos, la cual cuenta con metodos
 * para ejecutar el comando y para recibir una respuesta
 */
public interface Command {
    /**
     * Metodo para ejecutar el comando
     */
    void execute();

    /**
     * Listener para procesar la respuesta que pueda recibir el comandom por ejemplo despues de consultar una api
     */
    interface Listener {
        void notifyError(String errorStatus);
    }
}
