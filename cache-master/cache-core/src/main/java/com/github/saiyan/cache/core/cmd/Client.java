package com.github.saiyan.cache.core.cmd;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8888);
            OutputStream outputStream = socket.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

            new Thread(new Read(socket)).start();       //读取服务端返回结果，单独启动一个线程，防止阻塞

            Scanner input = new Scanner(System.in);
            while(input.hasNext()){
                String command = input.nextLine();      //接受用户输入
                bufferedWriter.write(command+"\n");
                bufferedWriter.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}

/**
 * 服务读取服务器的结果，为了防止阻塞，所以新起了线程进行读
 */
class Read implements Runnable {

    private Socket socket;

    Read(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String info;
            while ((info = bufferedReader.readLine()) != null) {
                System.out.println(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


