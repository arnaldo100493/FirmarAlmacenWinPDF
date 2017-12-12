// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPKeyPair.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.*;
import java.security.*;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            JcaPGPKeyConverter

public class JcaPGPKeyPair extends PGPKeyPair
{

    private static PGPPublicKey getPublicKey(int algorithm, PublicKey pubKey, Date date)
        throws PGPException
    {
        return (new JcaPGPKeyConverter()).getPGPPublicKey(algorithm, pubKey, date);
    }

    private static PGPPrivateKey getPrivateKey(PGPPublicKey pub, PrivateKey privKey)
        throws PGPException
    {
        return (new JcaPGPKeyConverter()).getPGPPrivateKey(pub, privKey);
    }

    public JcaPGPKeyPair(int algorithm, KeyPair keyPair, Date date)
        throws PGPException
    {
        pub = getPublicKey(algorithm, keyPair.getPublic(), date);
        priv = getPrivateKey(pub, keyPair.getPrivate());
    }
}
