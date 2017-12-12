// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ByteBufferRandomAccessSource.java

package co.com.pdf.text.io;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;

// Referenced classes of package co.com.pdf.text.io:
//            RandomAccessSource

class ByteBufferRandomAccessSource
    implements RandomAccessSource
{

    public ByteBufferRandomAccessSource(ByteBuffer byteBuffer)
    {
        this.byteBuffer = byteBuffer;
    }

    public int get(long position)
        throws IOException
    {
        if(position > 0x7fffffffL)
            throw new IllegalArgumentException("Position must be less than Integer.MAX_VALUE");
        byte b;
        int n;
        try
        {
            if(position >= (long)byteBuffer.limit())
                return -1;
        }
        catch(BufferUnderflowException e)
        {
            return -1;
        }
        b = byteBuffer.get((int)position);
        n = b & 0xff;
        return n;
    }

    public int get(long position, byte bytes[], int off, int len)
        throws IOException
    {
        if(position > 0x7fffffffL)
            throw new IllegalArgumentException("Position must be less than Integer.MAX_VALUE");
        if(position >= (long)byteBuffer.limit())
        {
            return -1;
        } else
        {
            byteBuffer.position((int)position);
            int bytesFromThisBuffer = Math.min(len, byteBuffer.remaining());
            byteBuffer.get(bytes, off, bytesFromThisBuffer);
            return bytesFromThisBuffer;
        }
    }

    public long length()
    {
        return (long)byteBuffer.limit();
    }

    public void close()
        throws IOException
    {
        clean(byteBuffer);
    }

    private static boolean clean(ByteBuffer buffer)
    {
        if(buffer == null || !buffer.isDirect())
        {
            return false;
        } else
        {
            Boolean b = (Boolean)AccessController.doPrivileged(new PrivilegedAction(buffer) {

                public Boolean run()
                {
                    Boolean success = Boolean.FALSE;
                    try
                    {
                        Method getCleanerMethod = buffer.getClass().getMethod("cleaner", (Class[])null);
                        getCleanerMethod.setAccessible(true);
                        Object cleaner = getCleanerMethod.invoke(buffer, (Object[])null);
                        Method clean = cleaner.getClass().getMethod("clean", (Class[])null);
                        clean.invoke(cleaner, (Object[])null);
                        success = Boolean.TRUE;
                    }
                    catch(Exception e) { }
                    return success;
                }

                public volatile Object run()
                {
                    return run();
                }

                final ByteBuffer val$buffer;

            
            {
                buffer = bytebuffer;
                super();
            }
            }
);
            return b.booleanValue();
        }
    }

    private final ByteBuffer byteBuffer;
}
