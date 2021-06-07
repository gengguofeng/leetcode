package NIO;

import java.nio.CharBuffer;

/**
 * @author gengguofeng
 * @date 2021/5/15 6:01 下午
 * @description
 */
public class BufferTest {
    public static void main(String[] args) {

        testSlice();
    }

    /**
     * 2.4直接缓冲区
     * 最后一个需要掌握的概念是直接缓冲区，它是以创建时的开销换取了IO时的高效率。另外一点是，直接缓冲区使用的内存是直接调用了操作系统api分配的，绕过了JVM堆栈。
     * 直接缓冲区通过ByteBuffer.allocateDirect()方法创建，并可以调用isDirect()来查询一个缓冲区是否为直接缓冲区。
     * 一般来说，直接缓冲区是最好的IO选择。
     */

    /**
     * 缓冲区切片，将一个大缓冲区的一部分切出来，作为一个单独的缓冲区，但是它们公用同一个内部数组。
     * 切片从原缓冲区的position位置开始，至limit为止。原缓冲区和切片各自拥有自己的属性，测试代码如下：
     */
    static void testSlice() {
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put("abcdefghij");
        buffer.position(5);
        CharBuffer CharBufferslice = buffer.slice();
        showBuffer(buffer);
        showBuffer(CharBufferslice);
    }

    /**
     * 复制缓冲区，两个缓冲区对象实际上指向了同一个内部数组，但分别管理各自的属性。
     */
    static void testDuplicate() {
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put("abcde");
        CharBuffer buffer1 = buffer.duplicate();
        buffer1.clear();
        buffer1.put("alex");
        showBuffer(buffer);
        showBuffer(buffer1);
    }
    static void testCompact() {
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put("abcde");
        buffer.flip();
        //先读取两个字符
        buffer.get();
        buffer.get();
        showBuffer(buffer);
        //压缩
        buffer.compact();
        showBuffer(buffer);
        //继续写入
        buffer.put("fghi");
        buffer.flip();
        showBuffer(buffer);
        //从头读取后续的字符
        char[] chars = new char[buffer.remaining()];
        buffer.get(chars);
        System.out.println(chars);
    }

    static void testPutGetByFlip(){
        CharBuffer buffer = CharBuffer.allocate(10);
        buffer.put("abc");
        buffer.flip();
        char[] chars = new char[buffer.remaining()];
        buffer.get(chars);
        System.out.println(chars);

        //以下操作与flip等同
        buffer.clear();
        buffer.put("abc");
        buffer.limit(buffer.position());
        buffer.position(0);
        chars = new char[buffer.remaining()];
        buffer.get(chars);
        System.out.println(chars);

    }
    static void testPut() {
        CharBuffer buffer = CharBuffer.allocate(10);
        //第一种put方法
        buffer.put('a').put('b').put('c');
        showBuffer(buffer);
        buffer.clear();
        showBuffer(buffer);
        //第二种put方法
        char[] chars = {'A', 'B', 'C'};
        buffer.put(chars);
        showBuffer(buffer);
        buffer.clear();
        showBuffer(buffer);
        //CharBuffer还可以使用String
        buffer.put("abc");
    }
    static void BufferProperties(){
        CharBuffer buffer = CharBuffer.allocate(10) ;
        //存入三个字符后的状态
        buffer.put("abc");
        showBuffer(buffer);
        System.out.println("存入三个字符后的状态");

        //flip后的状态
        buffer.flip();
        showBuffer(buffer);
        System.out.println("flip后的状态");

        //读取两个字符后的状态
        char charc1= buffer.get();
        char charc2=buffer.get();
        showBuffer(buffer);
        System.out.println("读取两个字符后的状态");

        //clear后的状态
        buffer.clear();
        showBuffer(buffer);
        System.out.println("clear后的状态");
    }
    static void testMark() {
        CharBuffer buffer = CharBuffer.allocate(10);
        showBuffer(buffer);
        //设置mark位置为3
        buffer.position(3).mark().position(5);
        showBuffer(buffer);
        //reset后，position=mark
        buffer.reset();
        showBuffer(buffer);
    }
     static void showBuffer(CharBuffer buffer) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < buffer.limit(); i++) {
            char c = buffer.get(i);
            if (c == 0) {
                c = '.';
            }
            sb.append(c);
        }
        System.out.printf("position=%d, limit=%d, capacity=%d,content=%s\n",
                buffer.position(),buffer.limit(),buffer.capacity(),sb.toString());
    }
}
