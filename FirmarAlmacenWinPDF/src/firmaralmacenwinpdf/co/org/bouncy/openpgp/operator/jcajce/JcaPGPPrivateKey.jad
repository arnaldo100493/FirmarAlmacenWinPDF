// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPPrivateKey.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPPrivateKey;
import co.org.bouncy.openpgp.PGPPublicKey;
import java.security.PrivateKey;

public class JcaPGPPrivateKey extends PGPPrivateKey
{

    public JcaPGPPrivateKey(long keyID, PrivateKey privateKey)
    {
        super(keyID, null, null);
        this.privateKey = privateKey;
    }

    public JcaPGPPrivateKey(PGPPublicKey pubKey, PrivateKey privateKey)
    {
        super(pubKey.getKeyID(), pubKey.getPublicKeyPacket(), null);
        this.privateKey = privateKey;
    }

    public PrivateKey getPrivateKey()
    {
        return privateKey;
    }

    private final PrivateKey privateKey;
}
