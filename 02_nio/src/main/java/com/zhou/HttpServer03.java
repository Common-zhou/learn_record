package com.zhou;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 多线程去处理
 * sb -u http://localhost:8803 -c 20 -N 60
 *
 * <pre>
 *     Status 200:    53271
 * Status 303:    346
 *
 * RPS: 876.5 (requests/second)
 * Max: 82ms
 * Min: 0ms
 * Avg: 21.1ms
 *
 *   50%   below 20ms
 *   60%   below 20ms
 *   70%   below 20ms
 *   80%   below 21ms
 *   90%   below 21ms
 *   95%   below 22ms
 *   98%   below 41ms
 *   99%   below 41ms
 * 99.9%   below 44ms
 * </pre>
 *
 * @author zhoubing
 * @date 2022-03-26 16:31
 */
public class HttpServer03 {

    private static ExecutorService executorService = Executors.newFixedThreadPool(32);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8803);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void service(Socket socket) {
        executorService.submit(() -> {
            try {
                Thread.sleep(20);
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Content-Type:text/html;charset=utf-8");
                String body = "hello,nio3";
                printWriter.println("Content-Length:" + body.getBytes().length);
                printWriter.println();
                printWriter.println(body);
                printWriter.close();
                socket.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        });
    }
}
