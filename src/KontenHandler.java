import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class KontenHandler implements HttpHandler {
    public void handle(HttpExchange ex) throws java.io.IOException {
        System.out.println("1. Request masuk: " + ex.getRequestMethod() + " " + ex.getRequestURI());

        Konten model = new Konten();
        System.out.println("2. Data Model: " + model.nama + " v" + model.versi);

        Path file = Path.of("konten.html");
        System.out.println("Mencari file di: " + file.toAbsolutePath());

        if (!Files.exists(file)) {
            System.out.println("   FILE TIDAK DITEMUKAN");
            ex.sendResponseHeaders(404, -1);
            ex.close();
            return;
        }

        ex.getResponseHeaders().add("Content-Type", "text/html; charset=utf-8");
        ex.sendResponseHeaders(200, Files.size(file));

        OutputStream out = ex.getResponseBody();
        Files.copy(file, out);
        out.close();

        System.out.println("3. Response terkirim");
    }
}