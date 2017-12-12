// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptionException.java

package co.org.bouncy.openssl;


// Referenced classes of package co.org.bouncy.openssl:
//            PEMException

public class EncryptionException extends PEMException
{

    public EncryptionException(String msg)
    {
        super(msg);
    }

    public EncryptionException(String msg, Throwable ex)
    {
        super(msg);
        cause = ex;
    }

    public Throwable getCause()
    {
        return cause;
    }

    private Throwable cause;
}
