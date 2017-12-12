// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPairGeneratorSpi.java

package co.org.bouncy.jcajce.provider.asymmetric.gost;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.generators.GOST3410KeyPairGenerator;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.jce.spec.GOST3410ParameterSpec;
import co.org.bouncy.jce.spec.GOST3410PublicKeyParameterSetSpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.gost:
//            BCGOST3410PublicKey, BCGOST3410PrivateKey

public class KeyPairGeneratorSpi extends KeyPairGenerator
{

    public KeyPairGeneratorSpi()
    {
        super("GOST3410");
        engine = new GOST3410KeyPairGenerator();
        strength = 1024;
        random = null;
        initialised = false;
    }

    public void initialize(int strength, SecureRandom random)
    {
        this.strength = strength;
        this.random = random;
    }

    private void init(GOST3410ParameterSpec gParams, SecureRandom random)
    {
        GOST3410PublicKeyParameterSetSpec spec = gParams.getPublicKeyParameters();
        param = new GOST3410KeyGenerationParameters(random, new GOST3410Parameters(spec.getP(), spec.getQ(), spec.getA()));
        engine.init(param);
        initialised = true;
        gost3410Params = gParams;
    }

    public void initialize(AlgorithmParameterSpec params, SecureRandom random)
        throws InvalidAlgorithmParameterException
    {
        if(!(params instanceof GOST3410ParameterSpec))
        {
            throw new InvalidAlgorithmParameterException("parameter object not a GOST3410ParameterSpec");
        } else
        {
            init((GOST3410ParameterSpec)params, random);
            return;
        }
    }

    public KeyPair generateKeyPair()
    {
        if(!initialised)
            init(new GOST3410ParameterSpec(CryptoProObjectIdentifiers.gostR3410_94_CryptoPro_A.getId()), new SecureRandom());
        AsymmetricCipherKeyPair pair = engine.generateKeyPair();
        GOST3410PublicKeyParameters pub = (GOST3410PublicKeyParameters)pair.getPublic();
        GOST3410PrivateKeyParameters priv = (GOST3410PrivateKeyParameters)pair.getPrivate();
        return new KeyPair(new BCGOST3410PublicKey(pub, gost3410Params), new BCGOST3410PrivateKey(priv, gost3410Params));
    }

    GOST3410KeyGenerationParameters param;
    GOST3410KeyPairGenerator engine;
    GOST3410ParameterSpec gost3410Params;
    int strength;
    SecureRandom random;
    boolean initialised;
}
