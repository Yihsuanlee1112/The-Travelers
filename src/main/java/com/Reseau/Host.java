package com.Reseau;

public class Host {
    public static void main(String arg[]) {
        String ip = "localhost";
        AbstractServer server = new Server(ip);
        server.connect();
    }

}