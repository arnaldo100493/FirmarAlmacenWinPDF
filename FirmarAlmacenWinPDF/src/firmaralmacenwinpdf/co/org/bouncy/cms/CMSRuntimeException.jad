// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSRuntimeException.java

package co.org.bouncy.cms;


public class CMSRuntimeException extends RuntimeException
{

    public CMSRuntimeException(String name)
    {
        super(name);
    }

    public CMSRuntimeException(String name, Exception e)
    {
        super(name);
        this.e = e;
    }

    public Exception getUnderlyingException()
    {
        return e;
    }

    public Throwable getCause()
    {
        return e;
    }

    Exception e;
}
