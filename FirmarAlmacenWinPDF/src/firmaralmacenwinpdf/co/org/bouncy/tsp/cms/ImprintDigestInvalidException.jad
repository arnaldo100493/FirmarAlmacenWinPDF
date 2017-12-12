// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ImprintDigestInvalidException.java

package co.org.bouncy.tsp.cms;

import co.org.bouncy.tsp.TimeStampToken;

public class ImprintDigestInvalidException extends Exception
{

    public ImprintDigestInvalidException(String message, TimeStampToken token)
    {
        super(message);
        this.token = token;
    }

    public TimeStampToken getTimeStampToken()
    {
        return token;
    }

    private TimeStampToken token;
}
