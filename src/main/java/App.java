import java.io.IOException;
import java.util.List;
import retrofit2.Call;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class App
{
    public static final String API_URL = "http://localhost:8080/myapp/";

    public static class Jugadores {
        public final String nombre;
        public final int vida;

        public Jugadores(String nombre, int vida) {
            this.nombre = nombre;
            this.vida = vida;
        }
    }

    public interface Juego {
        @GET("/json/users")
        Call<List<Jugadores>> jugadoress();
    }

    public static void main( String[] args ) throws IOException
    {

        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        Juego prueva = retrofit.create(Juego.class);

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Jugadores>> call = prueva.jugadoress();

        // Fetch and print a list of the contributors to the library.
        List<Jugadores> jugador = call.execute().body();
        for (Jugadores jugadores : jugador) {
            System.out.println(jugadores.nombre + " (" + jugadores.vida + ") ");
        }
    }
}