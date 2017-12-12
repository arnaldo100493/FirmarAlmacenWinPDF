// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIXAttrCertChecker.java

package co.org.bouncy.x509_;

import java.security.cert.CertPath;
import java.security.cert.CertPathValidatorException;
import java.util.Collection;
import java.util.Set;

// Referenced classes of package co.org.bouncy.x509_:
//            X509AttributeCertificate

public abstract class PKIXAttrCertChecker
    implements Cloneable
{

    public PKIXAttrCertChecker()
    {
    }

    public abstract Set getSupportedExtensions();

    public abstract void check(X509AttributeCertificate x509attributecertificate, CertPath certpath, CertPath certpath1, Collection collection)
        throws CertPathValidatorException;

    public abstract Object clone();
}
