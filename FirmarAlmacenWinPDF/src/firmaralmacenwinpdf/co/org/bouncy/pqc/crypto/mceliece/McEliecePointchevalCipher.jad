// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McEliecePointchevalCipher.java

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

public class McEliecePointchevalCipher
    implements MessageEncryptor
{

    public McEliecePointchevalCipher()
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

    protected int decryptOutputSize(int inLen)
    {
        return 0;
    }

    protected int encryptOutputSize(int inLen)
    {
        return 0;
    }

    public void initCipherEncrypt(McElieceCCA2PublicKeyParameters pubKey)
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
        k = privKey.getK();
        t = privKey.getT();
    }

    public byte[] messageEncrypt(byte input[])
        throws Exception
    {
        int kDiv8 = k >> 3;
        byte r[] = new byte[kDiv8];
        sr.nextBytes(r);
        GF2Vector rPrime = new GF2Vector(k, sr);
        byte rPrimeBytes[] = rPrime.getEncoded();
        byte mr[] = ByteUtils.concatenate(input, r);
        messDigest.update(mr, 0, mr.length);
        byte hmr[] = new byte[messDigest.getDigestSize()];
        messDigest.doFinal(hmr, 0);
        GF2Vector z = Conversions.encode(n, t, hmr);
        byte c1[] = McElieceCCA2Primitives.encryptionPrimitive((McElieceCCA2PublicKeyParameters)key, rPrime, z).getEncoded();
        DigestRandomGenerator sr0 = new DigestRandomGenerator(new SHA1Digest());
        sr0.addSeedMaterial(rPrimeBytes);
        byte c2[] = new byte[input.length + kDiv8];
        sr0.nextBytes(c2);
        for(int i = 0; i < input.length; i++)
            c2[i] ^= input[i];

        for(int i = 0; i < kDiv8; i++)
            c2[input.length + i] ^= r[i];

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
        GF2Vector c1Vec = GF2Vector.OS2VP(n, c1);
        GF2Vector c1Dec[] = McElieceCCA2Primitives.decryptionPrimitive((McElieceCCA2PrivateKeyParameters)key, c1Vec);
        byte rPrimeBytes[] = c1Dec[0].getEncoded();
        GF2Vector z = c1Dec[1];
        DigestRandomGenerator sr0 = new DigestRandomGenerator(new SHA1Digest());
        sr0.addSeedMaterial(rPrimeBytes);
        byte mrBytes[] = new byte[c2Len];
        sr0.nextBytes(mrBytes);
        for(int i = 0; i < c2Len; i++)
            mrBytes[i] ^= c2[i];

        messDigest.update(mrBytes, 0, mrBytes.length);
        byte hmr[] = new byte[messDigest.getDigestSize()];
        messDigest.doFinal(hmr, 0);
        c1Vec = Conversions.encode(n, t, hmr);
        if(!c1Vec.equals(z))
        {
            throw new Exception("Bad Padding: Invalid ciphertext.");
        } else
        {
            int kDiv8 = k >> 3;
            byte mr[][] = ByteUtils.split(mrBytes, c2Len - kDiv8);
            return mr[0];
        }
    }

    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2.2";
    private Digest messDigest;
    private SecureRandom sr;
    private int n;
    private int k;
    private int t;
    McElieceCCA2KeyParameters key;
}
