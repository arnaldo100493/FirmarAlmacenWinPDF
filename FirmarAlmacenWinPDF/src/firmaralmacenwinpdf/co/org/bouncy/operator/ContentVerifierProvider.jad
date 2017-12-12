// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ContentVerifierProvider.java

package co.org.bouncy.operator;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;

// Referenced classes of package co.org.bouncy.operator:
//            OperatorCreationException, ContentVerifier

public interface ContentVerifierProvider
{

    public abstract boolean hasAssociatedCertificate();

    public abstract X509CertificateHolder getAssociatedCertificate();

    public abstract ContentVerifier get(AlgorithmIdentifier algorithmidentifier)
        throws OperatorCreationException;
}
