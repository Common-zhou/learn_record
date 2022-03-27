package com.zhou.gateway;

/**
 *
 * @author zhoubing
 * @date 2022-03-27 20:54
 */
public class V1Gateway {
    private static final String GATEWAY_VERSION = "1.0.0";
    private static final String GATEWAY_NAME = "HttpGateWay";

    public static void main(String[] args) {

        String proxyPort = System.getProperty("proxyPort", "9999");
        String proxyServer = System.getProperty("proxyServer", "http://localhost:9909");

        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");

        HttpServer server = new HttpServer(port, proxyServer);
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
