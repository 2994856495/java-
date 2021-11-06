package exercise03.java04.client_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newCachedThreadPool;

public class Server {
    public static void main(String[] args) {
        server();
    }
    public static void server() {

        int port = 7000;
        int clientNo = 1;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建线程池
//        @SuppressWarnings("AlibabaThreadPoolCreation")
        ExecutorService service = newCachedThreadPool();

        try {

            while (true) {
                assert serverSocket != null;
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                service.execute(new SingleServer(socket, clientNo));
                clientNo++;
            }

        }  finally {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

