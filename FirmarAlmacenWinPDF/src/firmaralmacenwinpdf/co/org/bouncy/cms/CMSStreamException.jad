// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSStreamException.java

package co.org.bouncy.cms;

import java.io.IOException;

public class CMSStreamException extends IOException
{

    CMSStreamException(String msg)
    {
        super(msg);
        underlying = null;
    }

    CMSStreamException(String msg, Throwable underlying)
    {
        super(msg);
        this.underlying = underlying;
    }

    public Throwable getCause()
    {
        return underlying;
    }

    private final Throwable underlying;
}
