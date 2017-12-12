// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPKeyConverter.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.bcpg.*;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.jce.interfaces.ElGamalPrivateKey;
import co.org.bouncy.jce.interfaces.ElGamalPublicKey;
import co.org.bouncy.jce.spec.*;
import co.org.bouncy.openpgp.*;
import co.org.bouncy.openpgp.operator.KeyFingerPrintCalculator;
import java.security.*;
import java.security.interfaces.*;
import java.security.spec.*;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper, JcaKeyFingerprintCalculator, JcaPGPPrivateKey

public class JcaPGPKeyConverter
{

    public JcaPGPKeyConverter()
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        fingerPrintCalculator = new JcaKeyFingerprintCalculator();
    }

    public JcaPGPKeyConverter setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaPGPKeyConverter setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public PublicKey getPublicKey(PGPPublicKey publicKey)
        throws PGPException
    {
        PublicKeyPacket publicPk = publicKey.getPublicKeyPacket();
        publicPk.getAlgorithm();
        JVM INSTR tableswitch 1 20: default 261
    //                   1 104
    //                   2 104
    //                   3 104
    //                   4 261
    //                   5 261
    //                   6 261
    //                   7 261
    //                   8 261
    //                   9 261
    //                   10 261
    //                   11 261
    //                   12 261
    //                   13 261
    //                   14 261
    //                   15 261
    //                   16 204
    //                   17 149
    //                   18 261
    //                   19 261
    //                   20 204;
           goto _L1 _L2 _L2 _L2 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L3 _L4 _L1 _L1 _L3
_L2:
        KeyFactory fact;
        RSAPublicBCPGKey rsaK = (RSAPublicBCPGKey)publicPk.getKey();
        RSAPublicKeySpec rsaSpec = new RSAPublicKeySpec(rsaK.getModulus(), rsaK.getPublicExponent());
        fact = helper.createKeyFactory("RSA");
        return fact.generatePublic(rsaSpec);
_L4:
        ElGamalPublicBCPGKey elK;
        ElGamalPublicKeySpec elSpec;
        try
        {
            DSAPublicBCPGKey dsaK = (DSAPublicBCPGKey)publicPk.getKey();
            DSAPublicKeySpec dsaSpec = new DSAPublicKeySpec(dsaK.getY(), dsaK.getP(), dsaK.getQ(), dsaK.getG());
            fact = helper.createKeyFactory("DSA");
            return fact.generatePublic(dsaSpec);
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
        elSpec = new ElGamalPublicKeySpec(elK.getY(), new ElGamalParameterSpec(elK.getP(), elK.getG()));
        fact = helper.createKeyFactory("ElGamal");
        return fact.generatePublic(elSpec);
_L1:
        throw new PGPException("unknown public key algorithm encountered");
    }

    public PGPPublicKey getPGPPublicKey(int algorithm, PublicKey pubKey, Date time)
        throws PGPException
    {
        BCPGKey bcpgKey;
        if(pubKey instanceof RSAPublicKey)
        {
            RSAPublicKey rK = (RSAPublicKey)pubKey;
            bcpgKey = new RSAPublicBCPGKey(rK.getModulus(), rK.getPublicExponent());
        } else
        if(pubKey instanceof DSAPublicKey)
        {
            DSAPublicKey dK = (DSAPublicKey)pubKey;
            DSAParams dP = dK.getParams();
            bcpgKey = new DSAPublicBCPGKey(dP.getP(), dP.getQ(), dP.getG(), dK.getY());
        } else
        if(pubKey instanceof ElGamalPublicKey)
        {
            ElGamalPublicKey eK = (ElGamalPublicKey)pubKey;
            ElGamalParameterSpec eS = eK.getParameters();
            bcpgKey = new ElGamalPublicBCPGKey(eS.getP(), eS.getG(), eK.getY());
        } else
        {
            throw new PGPException("unknown key class");
        }
        return new PGPPublicKey(new PublicKeyPacket(algorithm, time, bcpgKey), fingerPrintCalculator);
    }

    public PrivateKey getPrivateKey(PGPPrivateKey privKey)
        throws PGPException
    {
        PublicKeyPacket pubPk;
        BCPGKey privPk;
        if(privKey instanceof JcaPGPPrivateKey)
            return ((JcaPGPPrivateKey)privKey).getPrivateKey();
        pubPk = privKey.getPublicKeyPacket();
        privPk = privKey.getPrivateKeyDataPacket();
        pubPk.getAlgorithm();
        JVM INSTR tableswitch 1 20: default 335
    //                   1 124
    //                   2 124
    //                   3 124
    //                   4 335
    //                   5 335
    //                   6 335
    //                   7 335
    //                   8 335
    //                   9 335
    //                   10 335
    //                   11 335
    //                   12 335
    //                   13 335
    //                   14 335
    //                   15 335
    //                   16 270
    //                   17 207
    //                   18 335
    //                   19 335
    //                   20 270;
           goto _L1 _L2 _L2 _L2 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L1 _L3 _L4 _L1 _L1 _L3
_L2:
        KeyFactory fact;
        RSAPublicBCPGKey rsaPub = (RSAPublicBCPGKey)pubPk.getKey();
        RSASecretBCPGKey rsaPriv = (RSASecretBCPGKey)privPk;
        RSAPrivateCrtKeySpec rsaPrivSpec = new RSAPrivateCrtKeySpec(rsaPriv.getModulus(), rsaPub.getPublicExponent(), rsaPriv.getPrivateExponent(), rsaPriv.getPrimeP(), rsaPriv.getPrimeQ(), rsaPriv.getPrimeExponentP(), rsaPriv.getPrimeExponentQ(), rsaPriv.getCrtCoefficient());
        fact = helper.createKeyFactory("RSA");
        return fact.generatePrivate(rsaPrivSpec);
_L4:
        ElGamalPublicBCPGKey elPub;
        ElGamalSecretBCPGKey elPriv;
        ElGamalPrivateKeySpec elSpec;
        try
        {
            DSAPublicBCPGKey dsaPub = (DSAPublicBCPGKey)pubPk.getKey();
            DSASecretBCPGKey dsaPriv = (DSASecretBCPGKey)privPk;
            DSAPrivateKeySpec dsaPrivSpec = new DSAPrivateKeySpec(dsaPriv.getX(), dsaPub.getP(), dsaPub.getQ(), dsaPub.getG());
            fact = helper.createKeyFactory("DSA");
            return fact.generatePrivate(dsaPrivSpec);
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
        elSpec = new ElGamalPrivateKeySpec(elPriv.getX(), new ElGamalParameterSpec(elPub.getP(), elPub.getG()));
        fact = helper.createKeyFactory("ElGamal");
        return fact.generatePrivate(elSpec);
_L1:
        throw new PGPException("unknown public key algorithm encountered");
    }

    public PGPPrivateKey getPGPPrivateKey(PGPPublicKey pub, PrivateKey privKey)
        throws PGPException
    {
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
        return new PGPPrivateKey(pub.getKeyID(), pub.getPublicKeyPacket(), privPk);
    }

    private OperatorHelper helper;
    private KeyFingerPrintCalculator fingerPrintCalculator;
}
