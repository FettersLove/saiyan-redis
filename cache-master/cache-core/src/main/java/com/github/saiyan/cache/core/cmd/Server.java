package com.github.saiyan.cache.core.cmd;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while(true) {
                Socket socket = serverSocket.accept();
                new Thread(new Handler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 负责对请求进行处理
 */
class Handler implements Runnable{

    private Socket socket;

    Handler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            String info;
            while((info= bufferedReader.readLine())!=null) {
                String[] s = info.split(" ");
                if (s[0].equals("set") || s[0].equals("SET")) {             //识别为set命令就set
                    KvStore.set(s[1], s[2]);
                } else if (s[0].equals("get") || s[0].equals("GET")) {      //识别为get命令就get
                    String s1 = KvStore.get(s[1]);
                    bufferedWriter.write(s1 + "\n");
                    bufferedWriter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

