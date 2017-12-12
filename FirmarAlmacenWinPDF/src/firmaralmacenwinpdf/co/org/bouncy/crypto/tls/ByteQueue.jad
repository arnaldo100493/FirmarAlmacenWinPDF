// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteQueue.java

package co.org.bouncy.crypto.tls;


// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsRuntimeException

public class ByteQueue
{

    public ByteQueue()
    {
        databuf = new byte[1024];
        skipped = 0;
        available = 0;
    }

    public static final int nextTwoPow(int i)
    {
        i |= i >> 1;
        i |= i >> 2;
        i |= i >> 4;
        i |= i >> 8;
        i |= i >> 16;
        return i + 1;
    }

    public void read(byte buf[], int offset, int len, int skip)
    {
        if(available - skip < len)
            throw new TlsRuntimeException("Not enough data to read");
        if(buf.length - offset < len)
        {
            throw new TlsRuntimeException((new StringBuilder()).append("Buffer size of ").append(buf.length).append(" is too small for a read of ").append(len).append(" bytes").toString());
        } else
        {
            System.arraycopy(databuf, skipped + skip, buf, offset, len);
            return;
        }
    }

    public void addData(byte data[], int offset, int len)
    {
        if(skipped + available + len > databuf.length)
        {
            byte tmp[] = new byte[nextTwoPow(data.length)];
            System.arraycopy(databuf, skipped, tmp, 0, available);
            skipped = 0;
            databuf = tmp;
        }
        System.arraycopy(data, offset, databuf, skipped + available, len);
        available += len;
    }

    public void removeData(int i)
    {
        if(i > available)
            throw new TlsRuntimeException((new StringBuilder()).append("Cannot remove ").append(i).append(" bytes, only got ").append(available).toString());
        available -= i;
        skipped += i;
        if(skipped > databuf.length / 2)
        {
            System.arraycopy(databuf, skipped, databuf, 0, available);
            skipped = 0;
        }
    }

    public int size()
    {
        return available;
    }

    private static final int INITBUFSIZE = 1024;
    private byte databuf[];
    private int skipped;
    private int available;
}
