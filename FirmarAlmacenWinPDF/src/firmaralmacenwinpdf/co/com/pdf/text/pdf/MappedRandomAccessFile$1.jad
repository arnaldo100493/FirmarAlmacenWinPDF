// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MappedRandomAccessFile.java

package co.com.pdf.text.pdf;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.PrivilegedAction;

// Referenced classes of package co.com.pdf.text.pdf:
//            MappedRandomAccessFile

static class MappedRandomAccessFile$1
    implements PrivilegedAction
{

    public Boolean run()
    {
        Boolean success = Boolean.FALSE;
        try
        {
            Method getCleanerMethod = val$buffer.getClass().getMethod("cleaner", (Class[])null);
            getCleanerMethod.setAccessible(true);
            Object cleaner = getCleanerMethod.invoke(val$buffer, (Object[])null);
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

    MappedRandomAccessFile$1(ByteBuffer bytebuffer)
    {
        val$buffer = bytebuffer;
        super();
    }
}
