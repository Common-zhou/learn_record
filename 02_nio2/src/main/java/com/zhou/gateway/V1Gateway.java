package com.zhou.gateway;

import java.util.Arrays;

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
        String proxyServers = System.getProperty("proxyServer", "http://localhost:9909,http://localhost:8801");

        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");

        HttpServer server = new HttpServer(port, Arrays.asList(proxyServers.split(",")));
        try {
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
