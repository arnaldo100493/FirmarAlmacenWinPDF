// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSABlindedEngine;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            DigestSignatureSpi

public static class DigestSignatureSpi$SHA1 extends DigestSignatureSpi
{

    public DigestSignatureSpi$SHA1()
    {
        super(OIWObjectIdentifiers.idSHA1, new SHA1Digest(), new PKCS1Encoding(new RSABlindedEngine()));
    }
}
