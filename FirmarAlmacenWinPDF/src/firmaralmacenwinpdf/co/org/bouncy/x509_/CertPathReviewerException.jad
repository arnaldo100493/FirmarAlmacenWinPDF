// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertPathReviewerException.java

package co.org.bouncy.x509_;

import co.org.bouncy.i18n.ErrorBundle;
import co.org.bouncy.i18n.LocalizedException;
import java.security.cert.CertPath;
import java.util.List;

public class CertPathReviewerException extends LocalizedException
{

    public CertPathReviewerException(ErrorBundle errorMessage, Throwable throwable)
    {
        super(errorMessage, throwable);
        index = -1;
        certPath = null;
    }

    public CertPathReviewerException(ErrorBundle errorMessage)
    {
        super(errorMessage);
        index = -1;
        certPath = null;
    }

    public CertPathReviewerException(ErrorBundle errorMessage, Throwable throwable, CertPath certPath, int index)
    {
        super(errorMessage, throwable);
        this.index = -1;
        this.certPath = null;
        if(certPath == null || index == -1)
            throw new IllegalArgumentException();
        if(index < -1 || certPath != null && index >= certPath.getCertificates().size())
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            this.certPath = certPath;
            this.index = index;
            return;
        }
    }

    public CertPathReviewerException(ErrorBundle errorMessage, CertPath certPath, int index)
    {
        super(errorMessage);
        this.index = -1;
        this.certPath = null;
        if(certPath == null || index == -1)
            throw new IllegalArgumentException();
        if(index < -1 || certPath != null && index >= certPath.getCertificates().size())
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            this.certPath = certPath;
            this.index = index;
            return;
        }
    }

    public CertPath getCertPath()
    {
        return certPath;
    }

    public int getIndex()
    {
        return index;
    }

    private int index;
    private CertPath certPath;
}
