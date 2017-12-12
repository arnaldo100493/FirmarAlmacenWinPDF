// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPKeyPair.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.openpgp.*;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcPGPKeyConverter

public class BcPGPKeyPair extends PGPKeyPair
{

    private static PGPPublicKey getPublicKey(int algorithm, AsymmetricKeyParameter pubKey, Date date)
        throws PGPException
    {
        return (new BcPGPKeyConverter()).getPGPPublicKey(algorithm, pubKey, date);
    }

    private static PGPPrivateKey getPrivateKey(PGPPublicKey pub, AsymmetricKeyParameter privKey)
        throws PGPException
    {
        return (new BcPGPKeyConverter()).getPGPPrivateKey(pub, privKey);
    }

    public BcPGPKeyPair(int algorithm, AsymmetricCipherKeyPair keyPair, Date date)
        throws PGPException
    {
        pub = getPublicKey(algorithm, keyPair.getPublic(), date);
        priv = getPrivateKey(pub, keyPair.getPrivate());
    }
}
