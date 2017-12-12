// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12PBEUtils.java

package co.org.bouncy.pkcs.bc;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.pkcs.PKCS12PBEParams;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.engines.DESedeEngine;
import co.org.bouncy.crypto.engines.RC2Engine;
import co.org.bouncy.crypto.generators.PKCS12ParametersGenerator;
import co.org.bouncy.crypto.io.MacOutputStream;
import co.org.bouncy.crypto.macs.HMac;
import co.org.bouncy.crypto.modes.CBCBlockCipher;
import co.org.bouncy.crypto.paddings.PKCS7Padding;
import co.org.bouncy.crypto.paddings.PaddedBufferedBlockCipher;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.MacCalculator;
import co.org.bouncy.util.Integers;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.*;

class PKCS12PBEUtils
{

    PKCS12PBEUtils()
    {
    }

    static int getKeySize(ASN1ObjectIdentifier algorithm)
    {
        return ((Integer)keySizes.get(algorithm)).intValue();
    }

    static boolean hasNoIv(ASN1ObjectIdentifier algorithm)
    {
        return noIvAlgs.contains(algorithm);
    }

    static boolean isDesAlg(ASN1ObjectIdentifier algorithm)
    {
        return desAlgs.contains(algorithm);
    }

    static PaddedBufferedBlockCipher getEngine(ASN1ObjectIdentifier algorithm)
    {
        BlockCipher engine;
        if(algorithm.equals(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC) || algorithm.equals(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC))
            engine = new DESedeEngine();
        else
        if(algorithm.equals(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC) || algorithm.equals(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC))
            engine = new RC2Engine();
        else
            throw new IllegalStateException("unknown algorithm");
        return new PaddedBufferedBlockCipher(new CBCBlockCipher(engine), new PKCS7Padding());
    }

    static MacCalculator createMacCalculator(ASN1ObjectIdentifier digestAlgorithm, ExtendedDigest digest, PKCS12PBEParams pbeParams, char password[])
    {
        PKCS12ParametersGenerator pGen = new PKCS12ParametersGenerator(digest);
        pGen.init(PKCS12ParametersGenerator.PKCS12PasswordToBytes(password), pbeParams.getIV(), pbeParams.getIterations().intValue());
        KeyParameter keyParam = (KeyParameter)pGen.generateDerivedMacParameters(digest.getDigestSize() * 8);
        HMac hMac = new HMac(digest);
        hMac.init(keyParam);
        return new MacCalculator(digestAlgorithm, pbeParams, hMac, password) {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return new AlgorithmIdentifier(digestAlgorithm, pbeParams);
            }

            public OutputStream getOutputStream()
            {
                return new MacOutputStream(hMac);
            }

            public byte[] getMac()
            {
                byte res[] = new byte[hMac.getMacSize()];
                hMac.doFinal(res, 0);
                return res;
            }

            public GenericKey getKey()
            {
                return new GenericKey(getAlgorithmIdentifier(), PKCS12ParametersGenerator.PKCS12PasswordToBytes(password));
            }

            final ASN1ObjectIdentifier val$digestAlgorithm;
            final PKCS12PBEParams val$pbeParams;
            final HMac val$hMac;
            final char val$password[];

            
            {
                digestAlgorithm = asn1objectidentifier;
                pbeParams = pkcs12pbeparams;
                hMac = hmac;
                password = ac;
                super();
            }
        }
;
    }

    static CipherParameters createCipherParameters(ASN1ObjectIdentifier algorithm, ExtendedDigest digest, int blockSize, PKCS12PBEParams pbeParams, char password[])
    {
        PKCS12ParametersGenerator pGen = new PKCS12ParametersGenerator(digest);
        pGen.init(PKCS12ParametersGenerator.PKCS12PasswordToBytes(password), pbeParams.getIV(), pbeParams.getIterations().intValue());
        CipherParameters params;
        if(hasNoIv(algorithm))
        {
            params = pGen.generateDerivedParameters(getKeySize(algorithm));
        } else
        {
            params = pGen.generateDerivedParameters(getKeySize(algorithm), blockSize * 8);
            if(isDesAlg(algorithm))
                DESedeParameters.setOddParity(((KeyParameter)((ParametersWithIV)params).getParameters()).getKey());
        }
        return params;
    }

    private static Map keySizes;
    private static Set noIvAlgs;
    private static Set desAlgs;

    static 
    {
        keySizes = new HashMap();
        noIvAlgs = new HashSet();
        desAlgs = new HashSet();
        keySizes.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4, Integers.valueOf(128));
        keySizes.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4, Integers.valueOf(40));
        keySizes.put(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC, Integers.valueOf(192));
        keySizes.put(PKCSObjectIdentifiers.pbeWithSHAAnd2_KeyTripleDES_CBC, Integers.valueOf(128));
        keySizes.put(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC2_CBC, Integers.valueOf(128));
        keySizes.put(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC2_CBC, Integers.valueOf(40));
        noIvAlgs.add(PKCSObjectIdentifiers.pbeWithSHAAnd128BitRC4);
        noIvAlgs.add(PKCSObjectIdentifiers.pbeWithSHAAnd40BitRC4);
        desAlgs.add(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC);
        desAlgs.add(PKCSObjectIdentifiers.pbeWithSHAAnd3_KeyTripleDES_CBC);
    }
}
