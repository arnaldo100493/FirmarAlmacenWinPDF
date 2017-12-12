// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AsymmetricKeyInfoConverter.java

package co.org.bouncy.jcajce.provider.util;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;

public interface AsymmetricKeyInfoConverter
{

    public abstract PrivateKey generatePrivate(PrivateKeyInfo privatekeyinfo)
        throws IOException;

    public abstract PublicKey generatePublic(SubjectPublicKeyInfo subjectpublickeyinfo)
        throws IOException;
}
