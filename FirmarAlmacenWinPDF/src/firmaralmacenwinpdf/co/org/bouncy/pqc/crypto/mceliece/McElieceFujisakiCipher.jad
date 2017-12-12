// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceFujisakiCipher.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA1Digest;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.crypto.prng.DigestRandomGenerator;
import co.org.bouncy.pqc.crypto.MessageEncryptor;
import co.org.bouncy.pqc.math.linearalgebra.ByteUtils;
import co.org.bouncy.pqc.math.linearalgebra.GF2Vector;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McElieceCCA2PublicKeyParameters, McElieceCCA2PrivateKeyParameters, McElieceCCA2Parameters, Conversions, 
//            McElieceCCA2Primitives, McElieceCCA2KeyParameters

public class McElieceFujisakiCipher
    implements MessageEncryptor
{

    public McElieceFujisakiCipher()
    {
    }

    public void init(boolean forSigning, CipherParameters param)
    {
        if(forSigning)
        {
            if(param instanceof ParametersWithRandom)
            {
                ParametersWithRandom rParam = (ParametersWithRandom)param;
                sr = rParam.getRandom();
                key = (McElieceCCA2PublicKeyParameters)rParam.getParameters();
                initCipherEncrypt((McElieceCCA2PublicKeyParameters)key);
            } else
            {
                sr = new SecureRandom();
                key = (McElieceCCA2PublicKeyParameters)param;
                initCipherEncrypt((McElieceCCA2PublicKeyParameters)key);
            }
        } else
        {
            key = (McElieceCCA2PrivateKeyParameters)param;
            initCipherDecrypt((McElieceCCA2PrivateKeyParameters)key);
        }
    }

    public int getKeySize(McElieceCCA2KeyParameters key)
        throws IllegalArgumentException
    {
        if(key instanceof McElieceCCA2PublicKeyParameters)
            return ((McElieceCCA2PublicKeyParameters)key).getN();
        if(key instanceof McElieceCCA2PrivateKeyParameters)
            return ((McElieceCCA2PrivateKeyParameters)key).getN();
        else
            throw new IllegalArgumentException("unsupported type");
    }

    private void initCipherEncrypt(McElieceCCA2PublicKeyParameters pubKey)
    {
        sr = sr == null ? new SecureRandom() : sr;
        messDigest = pubKey.getParameters().getDigest();
        n = pubKey.getN();
        k = pubKey.getK();
        t = pubKey.getT();
    }

    public void initCipherDecrypt(McElieceCCA2PrivateKeyParameters privKey)
    {
        messDigest = privKey.getParameters().getDigest();
        n = privKey.getN();
        t = privKey.getT();
    }

    public byte[] messageEncrypt(byte input[])
        throws Exception
    {
        GF2Vector r = new GF2Vector(k, sr);
        byte rBytes[] = r.getEncoded();
        byte rm[] = ByteUtils.concatenate(rBytes, input);
        messDigest.update(rm, 0, rm.length);
        byte hrm[] = new byte[messDigest.getDigestSize()];
        messDigest.doFinal(hrm, 0);
        GF2Vector z = Conversions.encode(n, t, hrm);
        byte c1[] = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters)key, r, z).getEncoded();
        DigestRandomGenerator sr0 = new DigestRandomGenerator(new SHA1Digest());
        sr0.addSeedMaterial(rBytes);
        byte c2[] = new byte[input.length];
        sr0.nextBytes(c2);
        for(int i = 0; i < input.length; i++)
            c2[i] ^= input[i];

        return ByteUtils.concatenate(c1, c2);
    }

    public byte[] messageDecrypt(byte input[])
        throws Exception
    {
        int c1Len = n + 7 >> 3;
        int c2Len = input.length - c1Len;
        byte c1c2[][] = ByteUtils.split(input, c1Len);
        byte c1[] = c1c2[0];
        byte c2[] = c1c2[1];
        GF2Vector hrmVec = GF2Vector.OS2VP(n, c1);
        GF2Vector decC1[] = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters)key, hrmVec);
        byte rBytes[] = decC1[0].getEncoded();
        GF2Vector z = decC1[1];
        DigestRandomGenerator sr0 = new DigestRandomGenerator(new SHA1Digest());
        sr0.addSeedMaterial(rBytes);
        byte mBytes[] = new byte[c2Len];
        sr0.nextBytes(mBytes);
        for(int i = 0; i < c2Len; i++)
            mBytes[i] ^= c2[i];

        byte rmBytes[] = ByteUtils.concatenate(rBytes, mBytes);
        byte hrm[] = new byte[messDigest.getDigestSize()];
        messDigest.update(rmBytes, 0, rmBytes.length);
        messDigest.doFinal(hrm, 0);
        hrmVec = Conversions.encode(n, t, hrm);
        if(!hrmVec.equals(z))
            throw new Exception("Bad Padding: invalid ciphertext");
        else
            return mBytes;
    }

    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.1";
    private static final String DEFAULT_PRNG_NAME = "SHA1PRNG";
    private Digest messDigest;
    private SecureRandom sr;
    private int n;
    private int k;
    private int t;
    McElieceCCA2KeyParameters key;
}
