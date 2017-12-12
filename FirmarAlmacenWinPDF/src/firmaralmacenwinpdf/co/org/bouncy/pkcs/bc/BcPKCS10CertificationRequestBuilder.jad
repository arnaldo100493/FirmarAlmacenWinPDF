// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPKCS10CertificationRequestBuilder.java

package co.org.bouncy.pkcs.bc;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.util.SubjectPublicKeyInfoFactory;
import co.org.bouncy.pkcs.PKCS10CertificationRequestBuilder;
import java.io.IOException;

public class BcPKCS10CertificationRequestBuilder extends PKCS10CertificationRequestBuilder
{

    public BcPKCS10CertificationRequestBuilder(X500Name subject, AsymmetricKeyParameter publicKey)
        throws IOException
    {
        super(subject, SubjectPublicKeyInfoFactory.createSubjectPublicKeyInfo(publicKey));
    }
}
