// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyAgreementSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.crypto.agreement.ECDHBasicAgreement;
import co.org.bouncy.crypto.agreement.kdf.ECDHKEKGenerator;
import co.org.bouncy.crypto.digests.SHA1Digest;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            KeyAgreementSpi

public static class KeyAgreementSpi$DHwithSHA1KDF extends KeyAgreementSpi
{

    public KeyAgreementSpi$DHwithSHA1KDF()
    {
        super("ECDHwithSHA1KDF", new ECDHBasicAgreement(), new ECDHKEKGenerator(new SHA1Digest()));
    }
}
