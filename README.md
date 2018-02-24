# nelson-ramirez-back

App que consulta la API de TMDb para Rappi

## Capas de la aplicación

La aplicación sigue la arquitectura MVP:
 
- La capa de la **Vista** esta compuesta por las clases `MoviesTabFragment` y `MovieDetailActivity`
- La capa del **Presentador** esta compuesta por las clases `MoviesPresenter` y `MovieDetailPresenter`
- La interaccion entre la clases `MoviesTabFragment` y `MoviesPresenter` esta sujeta a un contrato definido en por las interfaces `MoviesContract.View` y `MoviesContract.Presenter` respectivamente
- La interaccion entre la clases `MovieDetailActivity` y `MovieDetailPresenter` esta sujeta a un contrato definido en por las interfaces `MovieDetailContract.View` y `MovieDetailContract.Presenter` respectivamente
- La capa del **Modelo** esta coformado por las siguientes clases organizadas en los siguientes paquetes:
  - Las clases del paquete _model_ son clases POJO para representar la informacion que se obtiene de los servicios REST o de la cache
    - Algunas clases son `MovieDetail`, `MovieResume`, `MovieResponse` y `Genre` entre otras
  - Las clases del paquete _command_ son clases que se encargan de realizar las consultas a las apis y de procesar la respuesta obtenida para que sea mas facil de manejar por las clases Presentador, o alguno otro cliente.
    - Las clases comando son `GetConfigurationCommand`, `GetMoviesCommand` y `MovieDetailCommand`
    - Las clases comando implementan la interface `Command`
    - La clase `CommandFactory` es un clase que sigue el patrón de diseño _Factory_ para generar instancia de comandos ya preparados para solo ser consumidos
  - Las clases en el paquete _api_ se encargan de definir las apis a ser consultadas y generar una instancia de Retrofit (que es el cliente para hacer peticiones REST) ya configurada y preparada para ser usada
    - Dentro de este paquete tambien se maneja el tema de la cache para usar la app de forma _offline_ haciendo uso de la libreria _OkHttp_ por medio de la clase `OfflineCacheInterceptor`
    
## Responsabilidades de Clases

- `ApiServiceGenerator`: Esta clase se encarga de configurar y generar una instancia de retrofit para poder consultar las apis Rest de TMDb. Tambien se maneja el tema de la cache para usar la app de forma offline haciendo uso de la clase `OfflineCacheInterceptor`
- `OfflineCacheInterceptor`: Interceptor que se encarga de realizar una consulta a la cache en caso de que se este offline
- `ApiStatus`: Interface con diferentes status y codigos de error que pueden devolver como respuesta las consultas a las apis REST
- `TheMovieDbRestApi`: Interface con la definicion de las apis REST de TMDb
- `Command`: Interface que deben implementar todas las clases que deseen ser comandos, la cual cuenta con metodos para ejecutar el comando y para recibir una respuesta
- `MovieDetailCommand`: Comando que se encarga de consultar a la api que devuelve el detalle de una pelicula procesando la respuesta que obtiene de la api para hacerla mas manejable a sus clientes, por ejemplo un _Presenter_
- `GetConfigurationCommand`: Comando que se encarga de consultar a la api de Configuration la cual tiene informacion de como mostrar las imagenes que devuelven las consultas a las apis de peliculas procesando la respuesta que obtiene de la api para hacerla mas manejable a sus clientes, por ejemplo un Presenter
- `GetMoviesCommand`: Comando que se encarga de consultar a la api de _Top Rated Movies_, _Upcoming Movies_ y _Most Popular Movies_ procesando la respuesta que obtiene de la api para hacerla mas manejable a sus clientes, por ejemplo un _Presenter_
- `CommandFactory`: Esta clase implementa el patron de diseño Factory para generar un comando ya configurado para la consulta que se desee realizar. Los comando no se deben instanciar directamente, se debe hacer uso de esta Fabrica
- `TmdbConfigurationUtil`: Clase utilitaria para poder mostrar las imagenes devueltas por las consutas a las apis de peliculas haciendo uso de los datos devueltos por la api _Configuration_
- `MovieDetailContract`: Contrato en entra la vista y el presentador de la pantalla de Detalle de una Pelicula
- `MovieDetailActivity`: Actividad que representa la pantalla de detalle de una pelicula y se encarga de interactuar con `MovieDetailPresenter`. Implementa la interface `MovieDetailContract.View`
- `MovieDetailPresenter`: Clase que representa el presentador para la pantalla de detalle de una pelicula e interactua con `MovieDetailActivity`. Implementa la interface `MovieDetailContract.Presenter`
- `MoviesContract`: Contrato en entra la vista y el presentador de la pantalla de Listas de Peliculas
- `MoviesActivity`: Actividad que representa la pantalla donde se muestra 3 Tabs con las siguientes categorias de peliculas: _Top Rated_, _Upcoming_ y _Most Popular_
- `MoviesTabFragment`: Representa cada tab en la actividad `MoviesActivity` y se encarga de mostrar la lista de peliculas para una categoria dada interactuando con `MoviesPresenter`. Implementa la interface `MoviesContract.View`  
- `MoviesPresenter`: Clase que representa el presentador para la pantalla de categorias de peliculas e interactua con `MoviesTabFragment`. Implementa la interface `MoviesContract.Presenter`
- `MovieCategoriesViewPagerAdapter`: adapter para el `ViewPager` que va a mostrar los tabs de categorias de peliculas
- `MoviesRecyclerViewAdapter`: adapter para el `RecyclerView` que va a mostrar la lista de peliculas en cada tab. Tambien implemeta la funcionalidad para buscar una pelicula dentro de la lista
- `MoviesViewHolder`: `ViewHolder` para `MoviesRecyclerViewAdapter`
- `GetConfigurationCommandTest`: tests para la clase `GetConfigurationCommand`
- `GetMoviesCommandTest`: tests para la clase `GetMoviesCommand`
- `MovieDetailCommandTest`: tests para la clase `MovieDetailCommand`
- `CommandFactoryTest`: tests para la clase `CommandFactory`
- `MovieDetailTest`: tests para un metodo de la clase `MovieDetail`
- `MovieDetailPresenterTest`: tests para la clase `MovieDetailPresenter`
- `MoviesPresenterTest`: tests para la clase `MoviesPresenter`
- `TmdbConfigurationUtilTest`: tests para la clase `TmdbConfigurationUtil`

