// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   StreamUtil.java

package co.com.pdf.text.io;

import java.io.*;

// Referenced classes of package co.com.pdf.text.io:
//            RandomAccessSource

public final class StreamUtil
{

    private StreamUtil()
    {
    }

    public static byte[] inputStreamToArray(InputStream is)
        throws IOException
    {
        byte b[] = new byte[8192];
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        do
        {
            int read = is.read(b);
            if(read >= 1)
            {
                out.write(b, 0, read);
            } else
            {
                out.close();
                return out.toByteArray();
            }
        } while(true);
    }

    public static void CopyBytes(RandomAccessSource source, long start, long length, OutputStream outs)
        throws IOException
    {
        if(length <= 0L)
            return;
        long idx = start;
        byte buf[] = new byte[8192];
        long n;
        for(; length > 0L; length -= n)
        {
            n = source.get(idx, buf, 0, (int)Math.min(buf.length, length));
            if(n <= 0L)
                throw new EOFException();
            outs.write(buf, 0, (int)n);
            idx += n;
        }

    }

    public static InputStream getResourceStream(String key)
    {
        return getResourceStream(key, null);
    }

    public static InputStream getResourceStream(String key, ClassLoader loader)
    {
        if(key.startsWith("/"))
            key = key.substring(1);
        InputStream is = null;
        if(loader != null)
        {
            is = loader.getResourceAsStream(key);
            if(is != null)
                return is;
        }
        try
        {
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            if(contextClassLoader != null)
                is = contextClassLoader.getResourceAsStream(key);
        }
        catch(Throwable e) { }
        if(is == null)
            is = co/com/pdf/text/io/StreamUtil.getResourceAsStream((new StringBuilder()).append("/").append(key).toString());
        if(is == null)
            is = ClassLoader.getSystemResourceAsStream(key);
        return is;
    }
}
