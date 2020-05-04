package channel;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author Tiger
 * @date 2020-05-04
 * @see channel
 **/
public class ChannelTest {

    //利用通道完成文件复制（非直接缓冲区）
    @Test
    public void test1() throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fileInputStream = new FileInputStream(new File("D:\\资源\\1.mp4"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("D:\\资源\\2.mp4"));
        FileChannel channel = fileInputStream.getChannel();
        FileChannel channel1 = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        while (channel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            channel1.write(byteBuffer);
            byteBuffer.clear();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        channel.close();
        channel1.close();
        fileInputStream.close();
        fileOutputStream.close();
    }

    @Test
    public void test2() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inputChannel = FileChannel.open(Paths.get("D:\\资源\\1.mp4"), StandardOpenOption.READ);
        FileChannel outputChannel = FileChannel.open(Paths.get("D:\\资源\\2.mp4"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
//        内存映射文件
        MappedByteBuffer map = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputChannel.size());
        MappedByteBuffer map1 = outputChannel.map(FileChannel.MapMode.READ_WRITE, 0, inputChannel.size());
        byte[] bytes = new byte[map.limit()];
        map.get(bytes);
        map1.put(bytes);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        inputChannel.close();
        outputChannel.close();
    }

    //通道间直接传输（直接缓冲区）
    @Test
    public void test3() throws IOException {
        long start = System.currentTimeMillis();
        FileChannel inputChannel = FileChannel.open(Paths.get("D:\\资源\\1.mp4"), StandardOpenOption.READ);
        FileChannel outputChannel = FileChannel.open(Paths.get("D:\\资源\\2.mp4"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        inputChannel.transferTo(0, inputChannel.size(), outputChannel);
//        outputChannel.transferFrom(inputChannel,0,inputChannel.size());
        inputChannel.close();
        outputChannel.close();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    //    分散读取，聚集写入
    @Test
    public void test4() throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\Admin\\Desktop\\新建文本文档.txt", "rw");
        FileChannel fileChannel = randomAccessFile.getChannel();
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] byteBuffers = {buffer1, buffer2};
        fileChannel.read(byteBuffers);
        for (ByteBuffer byteBuffer : byteBuffers) {
            byteBuffer.flip();
        }
        System.out.println(new String(byteBuffers[0].array(), 0, byteBuffers[0].limit()));
        System.out.println(new String(byteBuffers[1].array(), 0, byteBuffers[1].limit()));
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("2.txt", "rw");
        FileChannel fileChannel1 = randomAccessFile1.getChannel();
        fileChannel1.write(byteBuffers);
        fileChannel1.close();
        fileChannel.close();
    }
}
