// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ElGamalEngine.java

package co.org.bouncy.crypto.engines;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;
import java.security.SecureRandom;

public class ElGamalEngine
    implements AsymmetricBlockCipher
{

    public ElGamalEngine()
    {
    }

    public void init(boolean forEncryption, CipherParameters param)
    {
        BigInteger p;
        if(param instanceof ParametersWithRandom)
        {
            p = (ParametersWithRandom)param;
            key = (ElGamalKeyParameters)p.getParameters();
            random = p.getRandom();
        } else
        {
            key = (ElGamalKeyParameters)param;
            random = new SecureRandom();
        }
        this.forEncryption = forEncryption;
        p = key.getParameters().getP();
        bitSize = p.bitLength();
        if(forEncryption)
        {
            if(!(key instanceof ElGamalPublicKeyParameters))
                throw new IllegalArgumentException("ElGamalPublicKeyParameters are required for encryption.");
        } else
        if(!(key instanceof ElGamalPrivateKeyParameters))
            throw new IllegalArgumentException("ElGamalPrivateKeyParameters are required for decryption.");
    }

    public int getInputBlockSize()
    {
        if(forEncryption)
            return (bitSize - 1) / 8;
        else
            return 2 * ((bitSize + 7) / 8);
    }

    public int getOutputBlockSize()
    {
        if(forEncryption)
            return 2 * ((bitSize + 7) / 8);
        else
            return (bitSize - 1) / 8;
    }

    public byte[] processBlock(byte in[], int inOff, int inLen)
    {
        if(key == null)
            throw new IllegalStateException("ElGamal engine not initialised");
        int maxLength = forEncryption ? ((bitSize - 1) + 7) / 8 : getInputBlockSize();
        if(inLen > maxLength)
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        BigInteger p = key.getParameters().getP();
        if(key instanceof ElGamalPrivateKeyParameters)
        {
            byte in1[] = new byte[inLen / 2];
            byte in2[] = new byte[inLen / 2];
            System.arraycopy(in, inOff, in1, 0, in1.length);
            System.arraycopy(in, inOff + in1.length, in2, 0, in2.length);
            BigInteger gamma = new BigInteger(1, in1);
            BigInteger phi = new BigInteger(1, in2);
            ElGamalPrivateKeyParameters priv = (ElGamalPrivateKeyParameters)key;
            BigInteger m = gamma.modPow(p.subtract(ONE).subtract(priv.getX()), p).multiply(phi).mod(p);
            return BigIntegers.asUnsignedByteArray(m);
        }
        byte block[];
        if(inOff != 0 || inLen != in.length)
        {
            block = new byte[inLen];
            System.arraycopy(in, inOff, block, 0, inLen);
        } else
        {
            block = in;
        }
        BigInteger input = new BigInteger(1, block);
        if(input.bitLength() >= p.bitLength())
            throw new DataLengthException("input too large for ElGamal cipher.\n");
        ElGamalPublicKeyParameters pub = (ElGamalPublicKeyParameters)key;
        int pBitLength = p.bitLength();
        BigInteger k;
        for(k = new BigInteger(pBitLength, random); k.equals(ZERO) || k.compareTo(p.subtract(TWO)) > 0; k = new BigInteger(pBitLength, random));
        BigInteger g = key.getParameters().getG();
        BigInteger gamma = g.modPow(k, p);
        BigInteger phi = input.multiply(pub.getY().modPow(k, p)).mod(p);
        byte out1[] = gamma.toByteArray();
        byte out2[] = phi.toByteArray();
        byte output[] = new byte[getOutputBlockSize()];
        if(out1.length > output.length / 2)
            System.arraycopy(out1, 1, output, output.length / 2 - (out1.length - 1), out1.length - 1);
        else
            System.arraycopy(out1, 0, output, output.length / 2 - out1.length, out1.length);
        if(out2.length > output.length / 2)
            System.arraycopy(out2, 1, output, output.length - (out2.length - 1), out2.length - 1);
        else
            System.arraycopy(out2, 0, output, output.length - out2.length, out2.length);
        return output;
    }

    private ElGamalKeyParameters key;
    private SecureRandom random;
    private boolean forEncryption;
    private int bitSize;
    private static final BigInteger ZERO = BigInteger.valueOf(0L);
    private static final BigInteger ONE = BigInteger.valueOf(1L);
    private static final BigInteger TWO = BigInteger.valueOf(2L);

}
