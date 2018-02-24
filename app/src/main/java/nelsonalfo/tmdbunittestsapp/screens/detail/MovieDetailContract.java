package nelsonalfo.tmdbunittestsapp.screens.detail;

import nelsonalfo.tmdbunittestsapp.command.detail.MovieDetailCommand;
import nelsonalfo.tmdbunittestsapp.models.MovieDetail;


/**
 * Contrato en entra la vista y el presentador de la pantalla de Detalle de una Pelicula
 */
public interface MovieDetailContract {
    /**
     * Interface que debe implementar la vista para interactuar con el presentador
     */
    interface View {
        void setPresenter(Presenter presenter);

        void showMovieDetail(MovieDetail movieDetail);

        void finishAndShowNoDetailsMessage();

        void finishAndShowConnectionProblemsMessage();

        void finishAndShowUnknownErrorMessage();
    }

    /**
     * Interface que debe implementar el presentador para interactuar con la vista
     */
    interface Presenter extends MovieDetailCommand.Listener {
        void callApi();
    }
}
