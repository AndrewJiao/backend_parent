package com.ypjiao.mettingfilm.backend_show_hystrix.IO;

import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class NIO {
    @Test
    public void testIO() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(80));
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true){
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.stream().forEach(s-> {
                if (s.isAcceptable()){
                    ServerSocketChannel serverSocket = (ServerSocketChannel) s.channel();
                    try {
                        SocketChannel accept = serverSocket.accept();
                        accept.register(selector,SelectionKey.OP_CONNECT);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else if (s.isConnectable()){
                    SocketChannel socketChannel = (SocketChannel) s.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
                    byte[] data = new byte[1024];
                    try {
                        socketChannel.read(byteBuffer);
                        byteBuffer.get(data,0,byteBuffer.limit());
                        System.out.println("返回消息："+new String(data,0,byteBuffer.limit(),"utf-8"));

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Test
    public void testsocket(){
        int[] result = new int[2];
        int[] nums = {2,7,11,15};
        int target = 9;
        for (int i = 0; i < nums.length; i++) {
            int value = target - nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (value==nums[j]){
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
    }
}
