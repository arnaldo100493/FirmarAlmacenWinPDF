// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPKCS8Generator.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.openssl.PKCS8Generator;
import co.org.bouncy.operator.OutputEncryptor;
import co.org.bouncy.util.io.pem.PemGenerationException;
import java.security.PrivateKey;

public class JcaPKCS8Generator extends PKCS8Generator
{

    public JcaPKCS8Generator(PrivateKey key, OutputEncryptor encryptor)
        throws PemGenerationException
    {
        super(PrivateKeyInfo.getInstance(key.getEncoded()), encryptor);
    }
}
