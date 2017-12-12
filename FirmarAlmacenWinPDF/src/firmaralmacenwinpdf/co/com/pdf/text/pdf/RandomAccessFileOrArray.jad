// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RandomAccessFileOrArray.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.Document;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.io.*;
import java.io.*;
import java.net.URL;

public class RandomAccessFileOrArray
    implements DataInput
{

    /**
     * @deprecated Method RandomAccessFileOrArray is deprecated
     */

    public RandomAccessFileOrArray(String filename)
        throws IOException
    {
        this((new RandomAccessSourceFactory()).setForceRead(false).setUsePlainRandomAccess(Document.plainRandomAccess).createBestSource(filename));
    }

    /**
     * @deprecated Method RandomAccessFileOrArray is deprecated
     */

    public RandomAccessFileOrArray(RandomAccessFileOrArray source)
    {
        this(((RandomAccessSource) (new IndependentRandomAccessSource(source.byteSource))));
    }

    public RandomAccessFileOrArray createView()
    {
        return new RandomAccessFileOrArray(new IndependentRandomAccessSource(byteSource));
    }

    public RandomAccessSource createSourceView()
    {
        return new IndependentRandomAccessSource(byteSource);
    }

    public RandomAccessFileOrArray(RandomAccessSource byteSource)
    {
        isBack = false;
        this.byteSource = byteSource;
    }

    /**
     * @deprecated Method RandomAccessFileOrArray is deprecated
     */

    public RandomAccessFileOrArray(String filename, boolean forceRead, boolean plainRandomAccess)
        throws IOException
    {
        this((new RandomAccessSourceFactory()).setForceRead(forceRead).setUsePlainRandomAccess(plainRandomAccess).createBestSource(filename));
    }

    /**
     * @deprecated Method RandomAccessFileOrArray is deprecated
     */

    public RandomAccessFileOrArray(URL url)
        throws IOException
    {
        this((new RandomAccessSourceFactory()).createSource(url));
    }

    /**
     * @deprecated Method RandomAccessFileOrArray is deprecated
     */

    public RandomAccessFileOrArray(InputStream is)
        throws IOException
    {
        this((new RandomAccessSourceFactory()).createSource(is));
    }

    /**
     * @deprecated Method RandomAccessFileOrArray is deprecated
     */

    public RandomAccessFileOrArray(byte arrayIn[])
    {
        this((new RandomAccessSourceFactory()).createSource(arrayIn));
    }

    /**
     * @deprecated Method getByteSource is deprecated
     */

    protected RandomAccessSource getByteSource()
    {
        return byteSource;
    }

    public void pushBack(byte b)
    {
        back = b;
        isBack = true;
    }

    public int read()
        throws IOException
    {
        if(isBack)
        {
            isBack = false;
            return back & 0xff;
        } else
        {
            return byteSource.get(byteSourcePosition++);
        }
    }

    public int read(byte b[], int off, int len)
        throws IOException
    {
        if(len == 0)
            return 0;
        int count = 0;
        if(isBack && len > 0)
        {
            isBack = false;
            b[off++] = back;
            len--;
            count++;
        }
        if(len > 0)
        {
            int byteSourceCount = byteSource.get(byteSourcePosition, b, off, len);
            if(byteSourceCount > 0)
            {
                count += byteSourceCount;
                byteSourcePosition += byteSourceCount;
            }
        }
        if(count == 0)
            return -1;
        else
            return count;
    }

    public int read(byte b[])
        throws IOException
    {
        return read(b, 0, b.length);
    }

    public void readFully(byte b[])
        throws IOException
    {
        readFully(b, 0, b.length);
    }

    public void readFully(byte b[], int off, int len)
        throws IOException
    {
        int n = 0;
        do
        {
            int count = read(b, off + n, len - n);
            if(count < 0)
                throw new EOFException();
            n += count;
        } while(n < len);
    }

    public long skip(long n)
        throws IOException
    {
        if(n <= 0L)
            return 0L;
        int adj = 0;
        if(isBack)
        {
            isBack = false;
            if(n == 1L)
                return 1L;
            n--;
            adj = 1;
        }
        long pos = getFilePointer();
        long len = length();
        long newpos = pos + n;
        if(newpos > len)
            newpos = len;
        seek(newpos);
        return (newpos - pos) + (long)adj;
    }

    public int skipBytes(int n)
        throws IOException
    {
        return (int)skip(n);
    }

    /**
     * @deprecated Method reOpen is deprecated
     */

    public void reOpen()
        throws IOException
    {
        seek(0L);
    }

    public void close()
        throws IOException
    {
        isBack = false;
        byteSource.close();
    }

    public long length()
        throws IOException
    {
        return byteSource.length();
    }

    public void seek(long pos)
        throws IOException
    {
        byteSourcePosition = pos;
        isBack = false;
    }

    public long getFilePointer()
        throws IOException
    {
        return byteSourcePosition - (long)(isBack ? 1 : 0);
    }

    public boolean readBoolean()
        throws IOException
    {
        int ch = read();
        if(ch < 0)
            throw new EOFException();
        else
            return ch != 0;
    }

    public byte readByte()
        throws IOException
    {
        int ch = read();
        if(ch < 0)
            throw new EOFException();
        else
            return (byte)ch;
    }

    public int readUnsignedByte()
        throws IOException
    {
        int ch = read();
        if(ch < 0)
            throw new EOFException();
        else
            return ch;
    }

    public short readShort()
        throws IOException
    {
        int ch1 = read();
        int ch2 = read();
        if((ch1 | ch2) < 0)
            throw new EOFException();
        else
            return (short)((ch1 << 8) + ch2);
    }

    public final short readShortLE()
        throws IOException
    {
        int ch1 = read();
        int ch2 = read();
        if((ch1 | ch2) < 0)
            throw new EOFException();
        else
            return (short)((ch2 << 8) + (ch1 << 0));
    }

    public int readUnsignedShort()
        throws IOException
    {
        int ch1 = read();
        int ch2 = read();
        if((ch1 | ch2) < 0)
            throw new EOFException();
        else
            return (ch1 << 8) + ch2;
    }

    public final int readUnsignedShortLE()
        throws IOException
    {
        int ch1 = read();
        int ch2 = read();
        if((ch1 | ch2) < 0)
            throw new EOFException();
        else
            return (ch2 << 8) + (ch1 << 0);
    }

    public char readChar()
        throws IOException
    {
        int ch1 = read();
        int ch2 = read();
        if((ch1 | ch2) < 0)
            throw new EOFException();
        else
            return (char)((ch1 << 8) + ch2);
    }

    public final char readCharLE()
        throws IOException
    {
        int ch1 = read();
        int ch2 = read();
        if((ch1 | ch2) < 0)
            throw new EOFException();
        else
            return (char)((ch2 << 8) + (ch1 << 0));
    }

    public int readInt()
        throws IOException
    {
        int ch1 = read();
        int ch2 = read();
        int ch3 = read();
        int ch4 = read();
        if((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        else
            return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + ch4;
    }

    public final int readIntLE()
        throws IOException
    {
        int ch1 = read();
        int ch2 = read();
        int ch3 = read();
        int ch4 = read();
        if((ch1 | ch2 | ch3 | ch4) < 0)
            throw new EOFException();
        else
            return (ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0);
    }

    public final long readUnsignedInt()
        throws IOException
    {
        long ch1 = read();
        long ch2 = read();
        long ch3 = read();
        long ch4 = read();
        if((ch1 | ch2 | ch3 | ch4) < 0L)
            throw new EOFException();
        else
            return (ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4 << 0);
    }

    public final long readUnsignedIntLE()
        throws IOException
    {
        long ch1 = read();
        long ch2 = read();
        long ch3 = read();
        long ch4 = read();
        if((ch1 | ch2 | ch3 | ch4) < 0L)
            throw new EOFException();
        else
            return (ch4 << 24) + (ch3 << 16) + (ch2 << 8) + (ch1 << 0);
    }

    public long readLong()
        throws IOException
    {
        return ((long)readInt() << 32) + ((long)readInt() & 0xffffffffL);
    }

    public final long readLongLE()
        throws IOException
    {
        int i1 = readIntLE();
        int i2 = readIntLE();
        return ((long)i2 << 32) + ((long)i1 & 0xffffffffL);
    }

    public float readFloat()
        throws IOException
    {
        return Float.intBitsToFloat(readInt());
    }

    public final float readFloatLE()
        throws IOException
    {
        return Float.intBitsToFloat(readIntLE());
    }

    public double readDouble()
        throws IOException
    {
        return Double.longBitsToDouble(readLong());
    }

    public final double readDoubleLE()
        throws IOException
    {
        return Double.longBitsToDouble(readLongLE());
    }

    public String readLine()
        throws IOException
    {
        StringBuilder input = new StringBuilder();
        int c = -1;
        boolean eol = false;
        do
        {
            if(eol)
                break;
            switch(c = read())
            {
            case -1: 
            case 10: // '\n'
                eol = true;
                break;

            case 13: // '\r'
                eol = true;
                long cur = getFilePointer();
                if(read() != 10)
                    seek(cur);
                break;

            default:
                input.append((char)c);
                break;
            }
        } while(true);
        if(c == -1 && input.length() == 0)
            return null;
        else
            return input.toString();
    }

    public String readUTF()
        throws IOException
    {
        return DataInputStream.readUTF(this);
    }

    public String readString(int length, String encoding)
        throws IOException
    {
        byte buf[] = new byte[length];
        readFully(buf);
        try
        {
            return new String(buf, encoding);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    private final RandomAccessSource byteSource;
    private long byteSourcePosition;
    private byte back;
    private boolean isBack;
}
