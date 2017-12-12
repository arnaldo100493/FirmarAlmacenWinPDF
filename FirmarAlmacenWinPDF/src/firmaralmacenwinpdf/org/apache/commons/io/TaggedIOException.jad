// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaggedIOException.java

package org.apache.commons.io;

import java.io.IOException;
import java.io.Serializable;

// Referenced classes of package org.apache.commons.io:
//            IOExceptionWithCause

public class TaggedIOException extends IOExceptionWithCause
{

    public static boolean isTaggedWith(Throwable throwable, Object tag)
    {
        return tag != null && (throwable instanceof TaggedIOException) && tag.equals(((TaggedIOException)throwable).tag);
    }

    public static void throwCauseIfTaggedWith(Throwable throwable, Object tag)
        throws IOException
    {
        if(isTaggedWith(throwable, tag))
            throw ((TaggedIOException)throwable).getCause();
        else
            return;
    }

    public TaggedIOException(IOException original, Serializable tag)
    {
        super(original.getMessage(), original);
        this.tag = tag;
    }

    public Serializable getTag()
    {
        return tag;
    }

    public IOException getCause()
    {
        return (IOException)super.getCause();
    }

    public volatile Throwable getCause()
    {
        return getCause();
    }

    private static final long serialVersionUID = 0x9eefe1c017430d8dL;
    private final Serializable tag;
}
