package com.zhou;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程的处理程序
 * sb -u http://localhost:8801 -c 20 -N 60
 * 使用测试程序 以20个线程压测。60s
 * <pre>
 * RPS: 48 (requests/second)
 * Max: 437ms
 * Min: 8ms
 * Avg: 408.7ms
 *
 *   50%   below 410ms
 *   60%   below 410ms
 *   70%   below 411ms
 *   80%   below 411ms
 *   90%   below 412ms
 *   95%   below 413ms
 *   98%   below 414ms
 *   99%   below 415ms
 *   99.9%   below 417ms
 * </pre>
 *
 * @author zhoubing
 * @date 2022-03-26 16:07
 */
public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8801);
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void service(Socket socket) {
        try {
            Thread.sleep(20);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            String body = "hello,nio1";
            printWriter.println("Content-Length:" + body.getBytes().length);
            printWriter.println();
            printWriter.println(body);
            printWriter.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
