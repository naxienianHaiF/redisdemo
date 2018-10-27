package com.wjh.protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @see LocalSetDemo
 * 测试redis协议的格式
 */
public class SocketDemo {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6378);
        Socket socket = server.accept();

        byte[] bytes = new byte[1024];
        int read = socket.getInputStream().read(bytes);
        System.out.println("total byte : "+ read);

        System.out.println(new String(bytes));
    }

}
