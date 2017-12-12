// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ExtCertPathBuilderException.java

package co.org.bouncy.jce.exception;

import java.security.cert.CertPath;
import java.security.cert.CertPathBuilderException;

// Referenced classes of package co.org.bouncy.jce.exception:
//            ExtException

public class ExtCertPathBuilderException extends CertPathBuilderException
    implements ExtException
{

    public ExtCertPathBuilderException(String message, Throwable cause)
    {
        super(message);
        this.cause = cause;
    }

    public ExtCertPathBuilderException(String msg, Throwable cause, CertPath certPath, int index)
    {
        super(msg, cause);
        this.cause = cause;
    }

    public Throwable getCause()
    {
        return cause;
    }

    private Throwable cause;
}
