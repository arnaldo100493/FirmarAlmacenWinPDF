// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TSPException.java

package co.org.bouncy.tsp;


public class TSPException extends Exception
{

    public TSPException(String message)
    {
        super(message);
    }

    public TSPException(String message, Throwable e)
    {
        super(message);
        underlyingException = e;
    }

    public Exception getUnderlyingException()
    {
        return (Exception)underlyingException;
    }

    public Throwable getCause()
    {
        return underlyingException;
    }

    Throwable underlyingException;
}
