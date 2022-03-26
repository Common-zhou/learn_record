package com.zhou;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 多线程去处理
 * sb -u http://localhost:8802 -c 20 -N 60
 * @author zhoubing
 * @date 2022-03-26 16:31
 */
public class HttpServer02 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8802);
        while (true){
            try {
                Socket socket = serverSocket.accept();
                service(socket);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private static void service(Socket socket) {
        new Thread(()->{
            try {
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                printWriter.println("HTTP/1.1 200 OK");
                printWriter.println("Content-Type:text/html;charset=utf-8");
                String body = "hello,nio2";
                printWriter.println("Content-Length:" + body.getBytes().length);
                printWriter.println();
                printWriter.println(body);
                printWriter.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();
    }
}
