// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AnnotatedException.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.jce.exception.ExtException;

public class AnnotatedException extends Exception
    implements ExtException
{

    AnnotatedException(String string, Throwable e)
    {
        super(string);
        _underlyingException = e;
    }

    AnnotatedException(String string)
    {
        this(string, null);
    }

    Throwable getUnderlyingException()
    {
        return _underlyingException;
    }

    public Throwable getCause()
    {
        return _underlyingException;
    }

    private Throwable _underlyingException;
}
