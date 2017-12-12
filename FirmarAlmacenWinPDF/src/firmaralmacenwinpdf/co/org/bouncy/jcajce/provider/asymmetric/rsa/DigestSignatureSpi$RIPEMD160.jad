// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DigestSignatureSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.rsa;

import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.crypto.digests.RIPEMD160Digest;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSABlindedEngine;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.rsa:
//            DigestSignatureSpi

public static class DigestSignatureSpi$RIPEMD160 extends DigestSignatureSpi
{

    public DigestSignatureSpi$RIPEMD160()
    {
        super(TeleTrusTObjectIdentifiers.ripemd160, new RIPEMD160Digest(), new PKCS1Encoding(new RSABlindedEngine()));
    }
}
