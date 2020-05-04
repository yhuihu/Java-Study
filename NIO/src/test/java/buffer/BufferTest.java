package buffer;

import java.nio.ByteBuffer;

public class BufferTest {
    public static void main(String[] args) {
        //        直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println("-----------allocateDirect----------");
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        System.out.println("-----------put----------");
        buffer.put("abcdefg".getBytes());
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        System.out.println("-----------flip----------");
        buffer.flip();
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        System.out.println("-----------第一次get----------");
        byte[] bt = new byte[buffer.limit()];
        //        offset表示第几个开始，length表示选多少个
        buffer.get(bt, 0, 2);
        System.out.println("取出的数据为：" + new String(bt));
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        System.out.println("-----------第二次get----------");
        buffer.mark();
        byte[] bt1 = new byte[buffer.limit()];
        //        offset表示第几个开始，length表示选多少个
        buffer.get(bt1, 2, 2);
        System.out.println("取出的数据为：" + new String(bt1));
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        System.out.println("-----------reset----------");
        buffer.reset();
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        System.out.println("-----------rewind----------");
        buffer.rewind();
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());

        System.out.println("-----------remind----------");
        if (buffer.hasRemaining()) {
//            显示可操作数量
            System.out.println(buffer.remaining());
        }

        System.out.println("-----------clear----------");
//        注意：数据还存在，只是位置回到了最开始的位置，数据处于被遗忘状态
        buffer.clear();
        System.out.println("position:" + buffer.position());
        System.out.println("limit:" + buffer.limit());
        System.out.println("capacity:" + buffer.capacity());
        System.out.println("data:" + (char) (buffer.get()));
    }
}
