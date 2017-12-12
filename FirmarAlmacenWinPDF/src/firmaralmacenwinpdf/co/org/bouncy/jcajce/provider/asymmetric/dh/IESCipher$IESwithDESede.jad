// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IESCipher.java

package co.org.bouncy.jcajce.provider.asymmetric.dh;

import co.org.bouncy.crypto.agreement.DHBasicAgreement;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.engines.DESedeEngine;
import co.org.bouncy.crypto.engines.IESEngine;
import co.org.bouncy.crypto.generators.KDF2BytesGenerator;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.paddings.PaddedBufferedBlockCipher;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.dh:
//            IESCipher

public static class IESCipher$IESwithDESede extends IESCipher
{

    public IESCipher$IESwithDESede()
    {
        super(new IESEngine(new DHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(new DESedeEngine())));
    }
}
