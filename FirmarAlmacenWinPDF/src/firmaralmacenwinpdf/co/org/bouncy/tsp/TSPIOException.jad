// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TSPIOException.java

package co.org.bouncy.tsp;

import java.io.IOException;

public class TSPIOException extends IOException
{

    public TSPIOException(String message)
    {
        super(message);
    }

    public TSPIOException(String message, Throwable e)
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
