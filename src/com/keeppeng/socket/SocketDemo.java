package com.keeppeng.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketDemo {
    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println(serverSocket.getLocalPort() + "Running");
        for (;;) {
            Socket sock = serverSocket.accept();
            System.out.println("connected from " + sock.getRemoteSocketAddress());
            Thread t = new Handler(sock);
            t.start();
        }
    }
}

class Handler extends Thread{

    Socket socket;

    public Handler(Socket socketIn) {
        this.socket = socketIn;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = this.socket.getInputStream();
            OutputStream outputStream = this.socket.getOutputStream();
            handleRun(inputStream, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                this.socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    private void handleRun(InputStream inputStream, OutputStream outputStream) throws IOException {
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        writer.write("hello\n");
        writer.flush();
        for (;;) {
            String s = reader.readLine();
            if (s.equals("bye")) {
                writer.write("bye\n");
                writer.flush();
                break;
            }
            writer.write("ok: " + s + "\n");
            writer.flush();
        }
    }
}
