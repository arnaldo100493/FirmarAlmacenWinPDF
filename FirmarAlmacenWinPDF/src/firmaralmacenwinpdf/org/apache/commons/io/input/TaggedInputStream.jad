// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaggedInputStream.java

package org.apache.commons.io.input;

import java.io.*;
import java.util.UUID;
import org.apache.commons.io.TaggedIOException;

// Referenced classes of package org.apache.commons.io.input:
//            ProxyInputStream

public class TaggedInputStream extends ProxyInputStream
{

    public TaggedInputStream(InputStream proxy)
    {
        super(proxy);
    }

    public boolean isCauseOf(Throwable exception)
    {
        return TaggedIOException.isTaggedWith(exception, tag);
    }

    public void throwIfCauseOf(Throwable throwable)
        throws IOException
    {
        TaggedIOException.throwCauseIfTaggedWith(throwable, tag);
    }

    protected void handleIOException(IOException e)
        throws IOException
    {
        throw new TaggedIOException(e, tag);
    }

    private final Serializable tag = UUID.randomUUID();
}
