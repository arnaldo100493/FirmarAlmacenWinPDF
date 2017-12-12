// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMEException.java

package co.org.bouncy.mail.smime;


public class SMIMEException extends Exception
{

    public SMIMEException(String name)
    {
        super(name);
    }

    public SMIMEException(String name, Exception e)
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
