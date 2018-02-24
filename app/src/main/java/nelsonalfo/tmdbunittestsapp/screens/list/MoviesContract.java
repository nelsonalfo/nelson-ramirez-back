package nelsonalfo.tmdbunittestsapp.screens.list;

import java.util.List;

import nelsonalfo.tmdbunittestsapp.command.list.GetConfigurationCommand;
import nelsonalfo.tmdbunittestsapp.command.list.GetMoviesCommand;
import nelsonalfo.tmdbunittestsapp.models.MovieResume;
import nelsonalfo.tmdbunittestsapp.models.TmdbConfiguration;


/**
 * Contrato en entra la vista y el presentador de la pantalla de Listas de Peliculas
 */
public interface MoviesContract {
    /**
     * Interface que debe implementar la vista para interactuar con el presentador
     */
    interface View {
        void setPresenter(Presenter presenter);

        void showMovies(List<MovieResume> movies);

        void showThereIsNoMovies();

        void showConnectionProblemsMessage();

        void showUnknownErrorMessage();

        void setConfiguration(TmdbConfiguration configuration);
    }

    /**
     * Interface que debe implementar el presentador para interactuar con la vista
     */
    interface Presenter extends GetMoviesCommand.Listener, GetConfigurationCommand.Listener {
        void callApi();
    }
}
