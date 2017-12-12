// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TaggedOutputStream.java

package org.apache.commons.io.output;

import java.io.*;
import java.util.UUID;
import org.apache.commons.io.TaggedIOException;

// Referenced classes of package org.apache.commons.io.output:
//            ProxyOutputStream

public class TaggedOutputStream extends ProxyOutputStream
{

    public TaggedOutputStream(OutputStream proxy)
    {
        super(proxy);
    }

    public boolean isCauseOf(Exception exception)
    {
        return TaggedIOException.isTaggedWith(exception, tag);
    }

    public void throwIfCauseOf(Exception exception)
        throws IOException
    {
        TaggedIOException.throwCauseIfTaggedWith(exception, tag);
    }

    protected void handleIOException(IOException e)
        throws IOException
    {
        throw new TaggedIOException(e, tag);
    }

    private final Serializable tag = UUID.randomUUID();
}
