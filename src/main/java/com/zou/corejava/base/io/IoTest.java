package com.zou.corejava.base.io;

import java.io.*;

public class IoTest {

    public static void main(String[] args) throws Exception{
        long t1 = System.currentTimeMillis();
        copyFileByByteStream("C:\\迅雷下载\\ry1.mp4","C:\\迅雷下载\\ry2.mp4");
        System.out.println("byte stream cost: "+ (System.currentTimeMillis()-t1)+"ms");

        long t2 = System.currentTimeMillis();
        copyFileByCharStream("C:\\迅雷下载\\ry1.mp4","C:\\迅雷下载\\ry3.mp4");
        System.out.println("char stream cost: "+ (System.currentTimeMillis()-t2)+"ms");
    }

    /**
     * IO流：以运行的程序为主体,往程序输入为输入流,从程序输出为输出流
     * <p>
     * Serializable
     * 序列化：将对象以字节序列的形式持久化到一个文件中。
     * 反序列化：解析字节序列的文件并生成对象。(将对象进行冰冻和解冻,常用于流数据的传输等)
     * 使用要求：该类实现Serializable 接口,transient修饰的属性不会序列化
     */
    // 数据流拷贝
    public static void copyFileByByteStream(String src, String dist) throws Exception {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dist));
        //使用数组读取，每次读取多个字节，减少了系统间的IO操作次数，从而提高了读写的效率
        byte[] buffer = new byte[20 * 1024];
        int len;
        while ((len = in.read(buffer, 0, buffer.length - 1)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }


    /**
     * 缓冲字符流
     * 原理：创建流对象时会创建一个默认缓冲区,通过缓冲区读写,减少系统IO次数,从而提高读写效率
     * 特点：装饰设计模式,增强类,不能单独使用要依赖被增强的对象
     */
    public static void copyFileByCharStream(String src, String dist) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader(src));
        BufferedWriter writer = new BufferedWriter(new FileWriter(dist));
        String line = null;
        while ((line = reader.readLine()) != null) {
            writer.write(line);
            writer.newLine();//换行
        }
        reader.close();
        /**
         * void flush() :刷新缓冲区，流对象可以继续使用
         * void close():先刷新缓冲区，然后通知系统释放资源。流对象不可以再被使用了
         *  注意:关闭资源时,与FileOutputStream不同,如果不关闭,数据只是保存到缓冲区，并未保存到文件
         */
        writer.close();
    }

}
