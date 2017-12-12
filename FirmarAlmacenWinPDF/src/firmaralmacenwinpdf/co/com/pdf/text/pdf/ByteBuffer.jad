// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteBuffer.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocWriter;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfEncodings

public class ByteBuffer extends OutputStream
{

    public ByteBuffer()
    {
        this(128);
    }

    public ByteBuffer(int size)
    {
        if(size < 1)
            size = 128;
        buf = new byte[size];
    }

    public static void setCacheSize(int size)
    {
        if(size > 0x31ff9c)
            size = 0x31ff9c;
        if(size <= byteCacheSize)
        {
            return;
        } else
        {
            byte tmpCache[][] = new byte[size][];
            System.arraycopy(byteCache, 0, tmpCache, 0, byteCacheSize);
            byteCache = tmpCache;
            byteCacheSize = size;
            return;
        }
    }

    public static void fillCache(int decimals)
    {
        int step = 1;
        switch(decimals)
        {
        case 0: // '\0'
            step = 100;
            break;

        case 1: // '\001'
            step = 10;
            break;
        }
        for(int i = 1; i < byteCacheSize; i += step)
            if(byteCache[i] == null)
                byteCache[i] = convertToBytes(i);

    }

    private static byte[] convertToBytes(int i)
    {
        int size = (int)Math.floor(Math.log(i) / Math.log(10D));
        if(i % 100 != 0)
            size += 2;
        if(i % 10 != 0)
            size++;
        if(i < 100)
        {
            size++;
            if(i < 10)
                size++;
        }
        byte cache[] = new byte[--size];
        size--;
        if(i < 100)
            cache[0] = 48;
        if(i % 10 != 0)
            cache[size--] = bytes[i % 10];
        if(i % 100 != 0)
        {
            cache[size--] = bytes[(i / 10) % 10];
            cache[size--] = 46;
        }
        size = (int)Math.floor(Math.log(i) / Math.log(10D)) - 1;
        for(int add = 0; add < size; add++)
            cache[add] = bytes[(i / (int)Math.pow(10D, (size - add) + 1)) % 10];

        return cache;
    }

    public ByteBuffer append_i(int b)
    {
        int newcount = count + 1;
        if(newcount > buf.length)
        {
            byte newbuf[] = new byte[Math.max(buf.length << 1, newcount)];
            System.arraycopy(buf, 0, newbuf, 0, count);
            buf = newbuf;
        }
        buf[count] = (byte)b;
        count = newcount;
        return this;
    }

    public ByteBuffer append(byte b[], int off, int len)
    {
        if(off < 0 || off > b.length || len < 0 || off + len > b.length || off + len < 0 || len == 0)
            return this;
        int newcount = count + len;
        if(newcount > buf.length)
        {
            byte newbuf[] = new byte[Math.max(buf.length << 1, newcount)];
            System.arraycopy(buf, 0, newbuf, 0, count);
            buf = newbuf;
        }
        System.arraycopy(b, off, buf, count, len);
        count = newcount;
        return this;
    }

    public ByteBuffer append(byte b[])
    {
        return append(b, 0, b.length);
    }

    public ByteBuffer append(String str)
    {
        if(str != null)
            return append(DocWriter.getISOBytes(str));
        else
            return this;
    }

    public ByteBuffer append(char c)
    {
        return append_i(c);
    }

    public ByteBuffer append(ByteBuffer buf)
    {
        return append(buf.buf, 0, buf.count);
    }

    public ByteBuffer append(int i)
    {
        return append(i);
    }

    public ByteBuffer append(long i)
    {
        return append(Long.toString(i));
    }

    public ByteBuffer append(byte b)
    {
        return append_i(b);
    }

    public ByteBuffer appendHex(byte b)
    {
        append(bytes[b >> 4 & 0xf]);
        return append(bytes[b & 0xf]);
    }

    public ByteBuffer append(float i)
    {
        return append(i);
    }

    public ByteBuffer append(double d)
    {
        append(formatDouble(d, this));
        return this;
    }

    public static String formatDouble(double d)
    {
        return formatDouble(d, null);
    }

    public static String formatDouble(double d, ByteBuffer buf)
    {
        if(HIGH_PRECISION)
        {
            DecimalFormat dn = new DecimalFormat("0.######", dfs);
            String sform = dn.format(d);
            if(buf == null)
            {
                return sform;
            } else
            {
                buf.append(sform);
                return null;
            }
        }
        boolean negative = false;
        if(Math.abs(d) < 1.5E-005D)
            if(buf != null)
            {
                buf.append((byte)48);
                return null;
            } else
            {
                return "0";
            }
        if(d < 0.0D)
        {
            negative = true;
            d = -d;
        }
        int v;
        if(d < 1.0D)
        {
            d += 5.0000000000000004E-006D;
            if(d >= 1.0D)
            {
                if(negative)
                    if(buf != null)
                    {
                        buf.append((byte)45);
                        buf.append((byte)49);
                        return null;
                    } else
                    {
                        return "-1";
                    }
                if(buf != null)
                {
                    buf.append((byte)49);
                    return null;
                } else
                {
                    return "1";
                }
            }
            if(buf != null)
            {
                v = (int)(d * 100000D);
                if(negative)
                    buf.append((byte)45);
                buf.append((byte)48);
                buf.append((byte)46);
                buf.append((byte)(v / 10000 + 48));
                if(v % 10000 != 0)
                {
                    buf.append((byte)((v / 1000) % 10 + 48));
                    if(v % 1000 != 0)
                    {
                        buf.append((byte)((v / 100) % 10 + 48));
                        if(v % 100 != 0)
                        {
                            buf.append((byte)((v / 10) % 10 + 48));
                            if(v % 10 != 0)
                                buf.append((byte)(v % 10 + 48));
                        }
                    }
                }
                return null;
            }
            int x = 0x186a0;
            int v = (int)(d * (double)x);
            StringBuilder res = new StringBuilder();
            if(negative)
                res.append('-');
            res.append("0.");
            for(; v < x / 10; x /= 10)
                res.append('0');

            res.append(v);
            int cut;
            for(cut = res.length() - 1; res.charAt(cut) == '0'; cut--);
            res.setLength(cut + 1);
            return res.toString();
        }
        if(d <= 32767D)
        {
            d += 0.0050000000000000001D;
            x = (int)(d * 100D);
            if(x < byteCacheSize && byteCache[x] != null)
            {
                if(buf != null)
                {
                    if(negative)
                        buf.append((byte)45);
                    buf.append(byteCache[x]);
                    return null;
                }
                String tmp = PdfEncodings.convertToString(byteCache[x], null);
                if(negative)
                    tmp = (new StringBuilder()).append("-").append(tmp).toString();
                return tmp;
            }
            if(buf != null)
            {
                if(x < byteCacheSize)
                {
                    int size = 0;
                    if(x >= 0xf4240)
                        size += 5;
                    else
                    if(x >= 0x186a0)
                        size += 4;
                    else
                    if(x >= 10000)
                        size += 3;
                    else
                    if(x >= 1000)
                        size += 2;
                    else
                    if(x >= 100)
                        size++;
                    if(x % 100 != 0)
                        size += 2;
                    if(x % 10 != 0)
                        size++;
                    byte cache[] = new byte[size];
                    int add = 0;
                    if(x >= 0xf4240)
                        cache[add++] = bytes[x / 0xf4240];
                    if(x >= 0x186a0)
                        cache[add++] = bytes[(x / 0x186a0) % 10];
                    if(x >= 10000)
                        cache[add++] = bytes[(x / 10000) % 10];
                    if(x >= 1000)
                        cache[add++] = bytes[(x / 1000) % 10];
                    if(x >= 100)
                        cache[add++] = bytes[(x / 100) % 10];
                    if(x % 100 != 0)
                    {
                        cache[add++] = 46;
                        cache[add++] = bytes[(x / 10) % 10];
                        if(x % 10 != 0)
                            cache[add++] = bytes[x % 10];
                    }
                    byteCache[x] = cache;
                }
                if(negative)
                    buf.append((byte)45);
                if(x >= 0xf4240)
                    buf.append(bytes[x / 0xf4240]);
                if(x >= 0x186a0)
                    buf.append(bytes[(x / 0x186a0) % 10]);
                if(x >= 10000)
                    buf.append(bytes[(x / 10000) % 10]);
                if(x >= 1000)
                    buf.append(bytes[(x / 1000) % 10]);
                if(x >= 100)
                    buf.append(bytes[(x / 100) % 10]);
                if(x % 100 != 0)
                {
                    buf.append((byte)46);
                    buf.append(bytes[(x / 10) % 10]);
                    if(x % 10 != 0)
                        buf.append(bytes[x % 10]);
                }
                return null;
            }
            StringBuilder res = new StringBuilder();
            if(negative)
                res.append('-');
            if(x >= 0xf4240)
                res.append(chars[x / 0xf4240]);
            if(x >= 0x186a0)
                res.append(chars[(x / 0x186a0) % 10]);
            if(x >= 10000)
                res.append(chars[(x / 10000) % 10]);
            if(x >= 1000)
                res.append(chars[(x / 1000) % 10]);
            if(x >= 100)
                res.append(chars[(x / 100) % 10]);
            if(x % 100 != 0)
            {
                res.append('.');
                res.append(chars[(x / 10) % 10]);
                if(x % 10 != 0)
                    res.append(chars[x % 10]);
            }
            return res.toString();
        }
        d += 0.5D;
        x = (long)d;
        if(negative)
            return (new StringBuilder()).append("-").append(Long.toString(x)).toString();
        else
            return Long.toString(x);
    }

    public void reset()
    {
        count = 0;
    }

    public byte[] toByteArray()
    {
        byte newbuf[] = new byte[count];
        System.arraycopy(buf, 0, newbuf, 0, count);
        return newbuf;
    }

    public int size()
    {
        return count;
    }

    public void setSize(int size)
    {
        if(size > count || size < 0)
        {
            throw new IndexOutOfBoundsException(MessageLocalization.getComposedMessage("the.new.size.must.be.positive.and.lt.eq.of.the.current.size", new Object[0]));
        } else
        {
            count = size;
            return;
        }
    }

    public String toString()
    {
        return new String(buf, 0, count);
    }

    public String toString(String enc)
        throws UnsupportedEncodingException
    {
        return new String(buf, 0, count, enc);
    }

    public void writeTo(OutputStream out)
        throws IOException
    {
        out.write(buf, 0, count);
    }

    public void write(int b)
        throws IOException
    {
        append((byte)b);
    }

    public void write(byte b[], int off, int len)
    {
        append(b, off, len);
    }

    public byte[] getBuffer()
    {
        return buf;
    }

    protected int count;
    protected byte buf[];
    private static int byteCacheSize;
    private static byte byteCache[][];
    public static final byte ZERO = 48;
    private static final char chars[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    private static final byte bytes[] = {
        48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 
        97, 98, 99, 100, 101, 102
    };
    public static boolean HIGH_PRECISION = false;
    private static final DecimalFormatSymbols dfs;

    static 
    {
        byteCacheSize = 0;
        byteCache = new byte[byteCacheSize][];
        dfs = new DecimalFormatSymbols(Locale.US);
    }
}
