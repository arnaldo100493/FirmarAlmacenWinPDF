// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPKeyConverter.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.openpgp.*;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcKeyFingerprintCalculator

public class BcPGPKeyConverter
{

    public BcPGPKeyConverter()
    {
    }

    public PGPPublicKey getPGPPublicKey(int algorithm, AsymmetricKeyParameter pubKey, Date time)
        throws PGPException
    {
        BCPGKey bcpgKey;
        if(pubKey instanceof RSAKeyParameters)
        {
            RSAKeyParameters rK = (RSAKeyParameters)pubKey;
            bcpgKey = new RSAPublicBCPGKey(rK.getModulus(), rK.getExponent());
        } else
        if(pubKey instanceof DSAPublicKeyParameters)
        {
            DSAPublicKeyParameters dK = (DSAPublicKeyParameters)pubKey;
            DSAParameters dP = dK.getParameters();
            bcpgKey = new DSAPublicBCPGKey(dP.getP(), dP.getQ(), dP.getG(), dK.getY());
        } else
        if(pubKey instanceof ElGamalPublicKeyParameters)
        {
            ElGamalPublicKeyParameters eK = (ElGamalPublicKeyParameters)pubKey;
            ElGamalParameters eS = eK.getParameters();
            bcpgKey = new ElGamalPublicBCPGKey(eS.getP(), eS.getG(), eK.getY());
        } else
        {
            throw new PGPException("unknown key class");
        }
        return new PGPPublicKey(new PublicKeyPacket(algorithm, time, bcpgKey), new BcKeyFingerprintCalculator());
    }

    public PGPPrivateKey getPGPPrivateKey(PGPPublicKey pubKey, AsymmetricKeyParameter privKey)
        throws PGPException
    {
        BCPGKey privPk;
        switch(pubKey.getAlgorithm())
        {
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
            RSAPrivateCrtKeyParameters rsK = (RSAPrivateCrtKeyParameters)privKey;
            privPk = new RSASecretBCPGKey(rsK.getExponent(), rsK.getP(), rsK.getQ());
            break;

        case 17: // '\021'
            DSAPrivateKeyParameters dsK = (DSAPrivateKeyParameters)privKey;
            privPk = new DSASecretBCPGKey(dsK.getX());
            break;

        case 16: // '\020'
        case 20: // '\024'
            ElGamalPrivateKeyParameters esK = (ElGamalPrivateKeyParameters)privKey;
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
        return new PGPPrivateKey(pubKey.getKeyID(), pubKey.getPublicKeyPacket(), privPk);
    }

    public AsymmetricKeyParameter getPublicKey(PGPPublicKey publicKey)
        throws PGPException
    {
        PublicKeyPacket publicPk = publicKey.getPublicKeyPacket();
        publicPk.getAlgorithm();
        JVM INSTR tableswitch 1 20: default 212
    //                   1 104
    //                   2 104
    //                   3 104
    //                   4 212
    //                   5 212
    //                   6 212
    //                   7 212
    //                   8 212
    //                   9 212
    //                   10 212
    //                   11 212
    //                   12 212
    //                   13 212
    //                   14 212
    //                   15 212
    //                   16 173
    //                   17 129
    //                   18 212
    //                   19 212
    //                   20 173;
           goto _L1 _L2 _L2 _L2 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L3 _L4 _L1 _L1 _L3
_L2:
        RSAPublicBCPGKey rsaK = (RSAPublicBCPGKey)publicPk.getKey();
        return new RSAKeyParameters(false, rsaK.getModulus(), rsaK.getPublicExponent());
_L4:
        ElGamalPublicBCPGKey elK;
        try
        {
            DSAPublicBCPGKey dsaK = (DSAPublicBCPGKey)publicPk.getKey();
            return new DSAPublicKeyParameters(dsaK.getY(), new DSAParameters(dsaK.getP(), dsaK.getQ(), dsaK.getG()));
        }
        catch(PGPException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PGPException("exception constructing public key", e);
        }
_L3:
        elK = (ElGamalPublicBCPGKey)publicPk.getKey();
        return new ElGamalPublicKeyParameters(elK.getY(), new ElGamalParameters(elK.getP(), elK.getG()));
_L1:
        throw new PGPException("unknown public key algorithm encountered");
    }

    public AsymmetricKeyParameter getPrivateKey(PGPPrivateKey privKey)
        throws PGPException
    {
        PublicKeyPacket pubPk;
        BCPGKey privPk;
        pubPk = privKey.getPublicKeyPacket();
        privPk = privKey.getPrivateKeyDataPacket();
        pubPk.getAlgorithm();
        JVM INSTR tableswitch 1 20: default 266
    //                   1 108
    //                   2 108
    //                   3 108
    //                   4 266
    //                   5 266
    //                   6 266
    //                   7 266
    //                   8 266
    //                   9 266
    //                   10 266
    //                   11 266
    //                   12 266
    //                   13 266
    //                   14 266
    //                   15 266
    //                   16 221
    //                   17 171
    //                   18 266
    //                   19 266
    //                   20 221;
           goto _L1 _L2 _L2 _L2 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L3 _L4 _L1 _L1 _L3
_L2:
        RSAPublicBCPGKey rsaPub = (RSAPublicBCPGKey)pubPk.getKey();
        RSASecretBCPGKey rsaPriv = (RSASecretBCPGKey)privPk;
        return new RSAPrivateCrtKeyParameters(rsaPriv.getModulus(), rsaPub.getPublicExponent(), rsaPriv.getPrivateExponent(), rsaPriv.getPrimeP(), rsaPriv.getPrimeQ(), rsaPriv.getPrimeExponentP(), rsaPriv.getPrimeExponentQ(), rsaPriv.getCrtCoefficient());
_L4:
        ElGamalPublicBCPGKey elPub;
        ElGamalSecretBCPGKey elPriv;
        try
        {
            DSAPublicBCPGKey dsaPub = (DSAPublicBCPGKey)pubPk.getKey();
            DSASecretBCPGKey dsaPriv = (DSASecretBCPGKey)privPk;
            return new DSAPrivateKeyParameters(dsaPriv.getX(), new DSAParameters(dsaPub.getP(), dsaPub.getQ(), dsaPub.getG()));
        }
        catch(PGPException e)
        {
            throw e;
        }
        catch(Exception e)
        {
            throw new PGPException("Exception constructing key", e);
        }
_L3:
        elPub = (ElGamalPublicBCPGKey)pubPk.getKey();
        elPriv = (ElGamalSecretBCPGKey)privPk;
        return new ElGamalPrivateKeyParameters(elPriv.getX(), new ElGamalParameters(elPub.getP(), elPub.getG()));
_L1:
        throw new PGPException("unknown public key algorithm encountered");
    }
}
