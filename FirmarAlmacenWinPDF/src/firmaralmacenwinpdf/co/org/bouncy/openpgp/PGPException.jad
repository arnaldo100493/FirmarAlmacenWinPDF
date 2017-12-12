// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPException.java

package co.org.bouncy.openpgp;


public class PGPException extends Exception
{

    public PGPException(String message)
    {
        super(message);
    }

    public PGPException(String message, Exception underlying)
    {
        super(message);
        this.underlying = underlying;
    }

    public Exception getUnderlyingException()
    {
        return underlying;
    }

    public Throwable getCause()
    {
        return underlying;
    }

    Exception underlying;
}
