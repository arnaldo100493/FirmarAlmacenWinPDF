// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPKeyPair.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.jce.interfaces.ElGamalPrivateKey;
import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.RSAPrivateCrtKey;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPPublicKey, PGPException, PGPPrivateKey

public class PGPKeyPair
{

    /**
     * @deprecated Method PGPKeyPair is deprecated
     */

    public PGPKeyPair(int algorithm, KeyPair keyPair, Date time, String provider)
        throws PGPException, NoSuchProviderException
    {
        this(algorithm, keyPair.getPublic(), keyPair.getPrivate(), time, provider);
    }

    /**
     * @deprecated Method PGPKeyPair is deprecated
     */

    public PGPKeyPair(int algorithm, KeyPair keyPair, Date time)
        throws PGPException
    {
        this(algorithm, keyPair.getPublic(), keyPair.getPrivate(), time);
    }

    /**
     * @deprecated Method PGPKeyPair is deprecated
     */

    public PGPKeyPair(int algorithm, PublicKey pubKey, PrivateKey privKey, Date time, String provider)
        throws PGPException, NoSuchProviderException
    {
        this(algorithm, pubKey, privKey, time);
    }

    /**
     * @deprecated Method PGPKeyPair is deprecated
     */

    public PGPKeyPair(int algorithm, PublicKey pubKey, PrivateKey privKey, Date time)
        throws PGPException
    {
        pub = new PGPPublicKey(algorithm, pubKey, time);
        BCPGKey privPk;
        switch(pub.getAlgorithm())
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            RSAPrivateCrtKey rsK = (RSAPrivateCrtKey)privKey;
            privPk = new RSASecretBCPGKey(rsK.getPrivateExponent(), rsK.getPrimeP(), rsK.getPrimeQ());
            break;

        case 17: // '\021'
            DSAPrivateKey dsK = (DSAPrivateKey)privKey;
            privPk = new DSASecretBCPGKey(dsK.getX());
            break;

        case 16: // '\020'
        case 20: // '\024'
            ElGamalPrivateKey esK = (ElGamalPrivateKey)privKey;
            privPk = new ElGamalSecretBCPGKey(esK.getX());
            break;

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 18: // '\022'
        case 19: // '\023'
        default:
            throw new PGPException("unknown key class");
        }
        priv = new PGPPrivateKey(pub.getKeyID(), pub.getPublicKeyPacket(), privPk);
    }

    public PGPKeyPair(PGPPublicKey pub, PGPPrivateKey priv)
    {
        this.pub = pub;
        this.priv = priv;
    }

    protected PGPKeyPair()
    {
    }

    public long getKeyID()
    {
        return pub.getKeyID();
    }

    public PGPPublicKey getPublicKey()
    {
        return pub;
    }

    public PGPPrivateKey getPrivateKey()
    {
        return priv;
    }

    protected PGPPublicKey pub;
    protected PGPPrivateKey priv;
}
