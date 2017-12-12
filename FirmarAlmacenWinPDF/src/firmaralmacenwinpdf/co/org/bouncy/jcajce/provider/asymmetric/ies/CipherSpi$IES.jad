// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CipherSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ies;

import co.org.bouncy.crypto.agreement.DHBasicAgreement;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.engines.IESEngine;
import co.org.bouncy.crypto.generators.KDF2BytesGenerator;
import co.org.bouncy.crypto.macs.HMac;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ies:
//            CipherSpi

public static class CipherSpi$IES extends CipherSpi
{

    public CipherSpi$IES()
    {
        super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest())));
    }
}
