// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPPrivateKey.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.jce.interfaces.ElGamalPrivateKey;
import co.org.bouncy.openpgp.operator.jcajce.JcaPGPKeyConverter;
import java.security.PrivateKey;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.RSAPrivateCrtKey;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPException, PGPUtil

public class PGPPrivateKey
{

    /**
     * @deprecated Method PGPPrivateKey is deprecated
     */

    public PGPPrivateKey(PrivateKey privateKey, long keyID)
    {
        this.privateKey = privateKey;
        this.keyID = keyID;
        if(privateKey instanceof RSAPrivateCrtKey)
        {
            RSAPrivateCrtKey rsK = (RSAPrivateCrtKey)privateKey;
            privateKeyDataPacket = new RSASecretBCPGKey(rsK.getPrivateExponent(), rsK.getPrimeP(), rsK.getPrimeQ());
        } else
        if(privateKey instanceof DSAPrivateKey)
        {
            DSAPrivateKey dsK = (DSAPrivateKey)privateKey;
            privateKeyDataPacket = new DSASecretBCPGKey(dsK.getX());
        } else
        if(privateKey instanceof ElGamalPrivateKey)
        {
            ElGamalPrivateKey esK = (ElGamalPrivateKey)privateKey;
            privateKeyDataPacket = new ElGamalSecretBCPGKey(esK.getX());
        } else
        {
            throw new IllegalArgumentException("unknown key class");
        }
    }

    public PGPPrivateKey(long keyID, PublicKeyPacket publicKeyPacket, BCPGKey privateKeyDataPacket)
    {
        this.keyID = keyID;
        this.publicKeyPacket = publicKeyPacket;
        this.privateKeyDataPacket = privateKeyDataPacket;
    }

    public long getKeyID()
    {
        return keyID;
    }

    /**
     * @deprecated Method getKey is deprecated
     */

    public PrivateKey getKey()
    {
        if(privateKey != null)
            return privateKey;
        try
        {
            return (new JcaPGPKeyConverter()).setProvider(PGPUtil.getDefaultProvider()).getPrivateKey(this);
        }
        catch(PGPException e)
        {
            throw new IllegalStateException((new StringBuilder()).append("unable to convert key: ").append(e.toString()).toString());
        }
    }

    public PublicKeyPacket getPublicKeyPacket()
    {
        return publicKeyPacket;
    }

    public BCPGKey getPrivateKeyDataPacket()
    {
        return privateKeyDataPacket;
    }

    private long keyID;
    private PrivateKey privateKey;
    private PublicKeyPacket publicKeyPacket;
    private BCPGKey privateKeyDataPacket;
}
