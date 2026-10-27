import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new HomeHandler());
        server.createContext("/konten", new KontenHandler());

        server.start();

        System.out.println("Server jalan di http://localhost:8080");
    }
}