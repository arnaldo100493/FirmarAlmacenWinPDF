// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPKCS10CertificationRequestBuilder.java

package co.org.bouncy.pkcs.jcajce;

import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.pkcs.PKCS10CertificationRequestBuilder;
import java.security.PublicKey;
import javax.security.auth.x500.X500Principal;

public class JcaPKCS10CertificationRequestBuilder extends PKCS10CertificationRequestBuilder
{

    public JcaPKCS10CertificationRequestBuilder(X500Name subject, PublicKey publicKey)
    {
        super(subject, SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
    }

    public JcaPKCS10CertificationRequestBuilder(X500Principal subject, PublicKey publicKey)
    {
        super(X500Name.getInstance(subject.getEncoded()), SubjectPublicKeyInfo.getInstance(publicKey.getEncoded()));
    }
}
