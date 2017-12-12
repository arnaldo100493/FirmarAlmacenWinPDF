// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMKeyPair.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;

public class PEMKeyPair
{

    public PEMKeyPair(SubjectPublicKeyInfo publicKeyInfo, PrivateKeyInfo privateKeyInfo)
    {
        this.publicKeyInfo = publicKeyInfo;
        this.privateKeyInfo = privateKeyInfo;
    }

    public PrivateKeyInfo getPrivateKeyInfo()
    {
        return privateKeyInfo;
    }

    public SubjectPublicKeyInfo getPublicKeyInfo()
    {
        return publicKeyInfo;
    }

    private final SubjectPublicKeyInfo publicKeyInfo;
    private final PrivateKeyInfo privateKeyInfo;
}
