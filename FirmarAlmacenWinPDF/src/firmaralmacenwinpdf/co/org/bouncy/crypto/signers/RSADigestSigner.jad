// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSADigestSigner.java

package co.org.bouncy.crypto.signers;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.DERNull;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSABlindedEngine;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.util.Hashtable;

public class RSADigestSigner
    implements Signer
{

    public RSADigestSigner(Digest digest)
    {
        this.digest = digest;
        algId = new AlgorithmIdentifier((ASN1ObjectIdentifier)oidMap.get(digest.getAlgorithmName()), DERNull.INSTANCE);
    }

    /**
     * @deprecated Method getAlgorithmName is deprecated
     */

    public String getAlgorithmName()
    {
        return (new StringBuilder()).append(digest.getAlgorithmName()).append("withRSA").toString();
    }

    public void init(boolean forSigning, CipherParameters parameters)
    {
        this.forSigning = forSigning;
        AsymmetricKeyParameter k;
        if(parameters instanceof ParametersWithRandom)
            k = (AsymmetricKeyParameter)((ParametersWithRandom)parameters).getParameters();
        else
            k = (AsymmetricKeyParameter)parameters;
        if(forSigning && !k.isPrivate())
            throw new IllegalArgumentException("signing requires private key");
        if(!forSigning && k.isPrivate())
        {
            throw new IllegalArgumentException("verification requires public key");
        } else
        {
            reset();
            rsaEngine.init(forSigning, parameters);
            return;
        }
    }

    public void update(byte input)
    {
        digest.update(input);
    }

    public void update(byte input[], int inOff, int length)
    {
        digest.update(input, inOff, length);
    }

    public byte[] generateSignature()
        throws CryptoException, DataLengthException
    {
        if(!forSigning)
            throw new IllegalStateException("RSADigestSigner not initialised for signature generation.");
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        try
        {
            byte data[] = derEncode(hash);
            return rsaEngine.processBlock(data, 0, data.length);
        }
        catch(IOException e)
        {
            throw new CryptoException((new StringBuilder()).append("unable to encode signature: ").append(e.getMessage()).toString(), e);
        }
    }

    public boolean verifySignature(byte signature[])
    {
        if(forSigning)
            throw new IllegalStateException("RSADigestSigner not initialised for verification");
        byte hash[] = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        byte sig[];
        byte expected[];
        try
        {
            sig = rsaEngine.processBlock(signature, 0, signature.length);
            expected = derEncode(hash);
        }
        catch(Exception e)
        {
            return false;
        }
        if(sig.length == expected.length)
            return Arrays.constantTimeAreEqual(sig, expected);
        if(sig.length == expected.length - 2)
        {
            int sigOffset = sig.length - hash.length - 2;
            int expectedOffset = expected.length - hash.length - 2;
            expected[1] -= 2;
            expected[3] -= 2;
            int nonEqual = 0;
            for(int i = 0; i < hash.length; i++)
                nonEqual |= sig[sigOffset + i] ^ expected[expectedOffset + i];

            for(int i = 0; i < sigOffset; i++)
                nonEqual |= sig[i] ^ expected[i];

            return nonEqual == 0;
        } else
        {
            return false;
        }
    }

    public void reset()
    {
        digest.reset();
    }

    private byte[] derEncode(byte hash[])
        throws IOException
    {
        DigestInfo dInfo = new DigestInfo(algId, hash);
        return dInfo.getEncoded("DER");
    }

    private final AsymmetricBlockCipher rsaEngine = new PKCS1Encoding(new RSABlindedEngine());
    private final AlgorithmIdentifier algId;
    private final Digest digest;
    private boolean forSigning;
    private static final Hashtable oidMap;

    static 
    {
        oidMap = new Hashtable();
        oidMap.put("RIPEMD128", TeleTrusTObjectIdentifiers.ripemd128);
        oidMap.put("RIPEMD160", TeleTrusTObjectIdentifiers.ripemd160);
        oidMap.put("RIPEMD256", TeleTrusTObjectIdentifiers.ripemd256);
        oidMap.put("SHA-1", X509ObjectIdentifiers.id_SHA1);
        oidMap.put("SHA-224", NISTObjectIdentifiers.id_sha224);
        oidMap.put("SHA-256", NISTObjectIdentifiers.id_sha256);
        oidMap.put("SHA-384", NISTObjectIdentifiers.id_sha384);
        oidMap.put("SHA-512", NISTObjectIdentifiers.id_sha512);
        oidMap.put("MD2", PKCSObjectIdentifiers.md2);
        oidMap.put("MD4", PKCSObjectIdentifiers.md4);
        oidMap.put("MD5", PKCSObjectIdentifiers.md5);
    }
}
