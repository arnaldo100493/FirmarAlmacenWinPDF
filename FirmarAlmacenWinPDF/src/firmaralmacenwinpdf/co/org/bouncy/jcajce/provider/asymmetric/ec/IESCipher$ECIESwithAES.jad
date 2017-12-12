// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IESCipher.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.crypto.agreement.ECDHBasicAgreement;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.engines.AESEngine;
import co.org.bouncy.crypto.engines.IESEngine;
import co.org.bouncy.crypto.generators.KDF2BytesGenerator;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.paddings.PaddedBufferedBlockCipher;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            IESCipher

public static class IESCipher$ECIESwithAES extends IESCipher
{

    public IESCipher$ECIESwithAES()
    {
        super(new IESEngine(new ECDHBasicAgreement(), new KDF2BytesGenerator(new SHA1Digest()), new HMac(new SHA1Digest()), new PaddedBufferedBlockCipher(new AESEngine())));
    }
}
