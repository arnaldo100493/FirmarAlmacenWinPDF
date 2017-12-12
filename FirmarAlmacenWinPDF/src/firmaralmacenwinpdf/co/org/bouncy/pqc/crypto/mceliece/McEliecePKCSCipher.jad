// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McEliecePKCSCipher.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.pqc.crypto.MessageEncryptor;
import co.org.bouncy.pqc.math.linearalgebra.*;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McEliecePublicKeyParameters, McEliecePrivateKeyParameters, McElieceKeyParameters

public class McEliecePKCSCipher
    implements MessageEncryptor
{

    public McEliecePKCSCipher()
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
                key = (McEliecePublicKeyParameters)rParam.getParameters();
                initCipherEncrypt((McEliecePublicKeyParameters)key);
            } else
            {
                sr = new SecureRandom();
                key = (McEliecePublicKeyParameters)param;
                initCipherEncrypt((McEliecePublicKeyParameters)key);
            }
        } else
        {
            key = (McEliecePrivateKeyParameters)param;
            initCipherDecrypt((McEliecePrivateKeyParameters)key);
        }
    }

    public int getKeySize(McElieceKeyParameters key)
    {
        if(key instanceof McEliecePublicKeyParameters)
            return ((McEliecePublicKeyParameters)key).getN();
        if(key instanceof McEliecePrivateKeyParameters)
            return ((McEliecePrivateKeyParameters)key).getN();
        else
            throw new IllegalArgumentException("unsupported type");
    }

    public void initCipherEncrypt(McEliecePublicKeyParameters pubKey)
    {
        sr = sr == null ? new SecureRandom() : sr;
        n = pubKey.getN();
        k = pubKey.getK();
        t = pubKey.getT();
        cipherTextSize = n >> 3;
        maxPlainTextSize = k >> 3;
    }

    public void initCipherDecrypt(McEliecePrivateKeyParameters privKey)
    {
        n = privKey.getN();
        k = privKey.getK();
        maxPlainTextSize = k >> 3;
        cipherTextSize = n >> 3;
    }

    public byte[] messageEncrypt(byte input[])
    {
        GF2Vector m = computeMessageRepresentative(input);
        GF2Vector z = new GF2Vector(n, t, sr);
        GF2Matrix g = ((McEliecePublicKeyParameters)key).getG();
        Vector mG = g.leftMultiply(m);
        GF2Vector mGZ = (GF2Vector)mG.add(z);
        return mGZ.getEncoded();
    }

    private GF2Vector computeMessageRepresentative(byte input[])
    {
        byte data[] = new byte[maxPlainTextSize + ((k & 7) == 0 ? 0 : 1)];
        System.arraycopy(input, 0, data, 0, input.length);
        data[input.length] = 1;
        return GF2Vector.OS2VP(k, data);
    }

    public byte[] messageDecrypt(byte input[])
        throws Exception
    {
        GF2Vector vec = GF2Vector.OS2VP(n, input);
        McEliecePrivateKeyParameters privKey = (McEliecePrivateKeyParameters)key;
        co.org.bouncy.pqc.math.linearalgebra.GF2mField field = privKey.getField();
        co.org.bouncy.pqc.math.linearalgebra.PolynomialGF2mSmallM gp = privKey.getGoppaPoly();
        GF2Matrix sInv = privKey.getSInv();
        Permutation p1 = privKey.getP1();
        Permutation p2 = privKey.getP2();
        GF2Matrix h = privKey.getH();
        co.org.bouncy.pqc.math.linearalgebra.PolynomialGF2mSmallM qInv[] = privKey.getQInv();
        Permutation p = p1.rightMultiply(p2);
        Permutation pInv = p.computeInverse();
        GF2Vector cPInv = (GF2Vector)vec.multiply(pInv);
        GF2Vector syndrome = (GF2Vector)h.rightMultiply(cPInv);
        GF2Vector z = GoppaCode.syndromeDecode(syndrome, field, gp, qInv);
        GF2Vector mSG = (GF2Vector)cPInv.add(z);
        mSG = (GF2Vector)mSG.multiply(p1);
        z = (GF2Vector)z.multiply(p);
        GF2Vector mS = mSG.extractRightVector(k);
        GF2Vector mVec = (GF2Vector)sInv.leftMultiply(mS);
        return computeMessage(mVec);
    }

    private byte[] computeMessage(GF2Vector mr)
        throws Exception
    {
        byte mrBytes[] = mr.getEncoded();
        int index;
        for(index = mrBytes.length - 1; index >= 0 && mrBytes[index] == 0; index--);
        if(mrBytes[index] != 1)
        {
            throw new Exception("Bad Padding: invalid ciphertext");
        } else
        {
            byte mBytes[] = new byte[index];
            System.arraycopy(mrBytes, 0, mBytes, 0, index);
            return mBytes;
        }
    }

    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
    private SecureRandom sr;
    private int n;
    private int k;
    private int t;
    public int maxPlainTextSize;
    public int cipherTextSize;
    McElieceKeyParameters key;
}
