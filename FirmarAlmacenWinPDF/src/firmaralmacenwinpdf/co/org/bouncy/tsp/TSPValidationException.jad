// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TSPValidationException.java

package co.org.bouncy.tsp;


// Referenced classes of package co.org.bouncy.tsp:
//            TSPException

public class TSPValidationException extends TSPException
{

    public TSPValidationException(String message)
    {
        super(message);
        failureCode = -1;
    }

    public TSPValidationException(String message, int failureCode)
    {
        super(message);
        this.failureCode = -1;
        this.failureCode = failureCode;
    }

    public int getFailureCode()
    {
        return failureCode;
    }

    private int failureCode;
}
