// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUSigningKeyGenerationParameters.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.KeyGenerationParameters;
import co.org.bouncy.crypto.digests.SHA256Digest;
import co.org.bouncy.crypto.digests.SHA512Digest;
import java.io.*;
import java.security.SecureRandom;
import java.text.DecimalFormat;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUSigningParameters

public class NTRUSigningKeyGenerationParameters extends KeyGenerationParameters
    implements Cloneable
{

    public NTRUSigningKeyGenerationParameters(int N, int q, int d, int B, int basisType, double beta, 
            double normBound, double keyNormBound, boolean primeCheck, boolean sparse, int keyGenAlg, 
            Digest hashAlg)
    {
        super(new SecureRandom(), N);
        signFailTolerance = 100;
        bitsF = 6;
        this.N = N;
        this.q = q;
        this.d = d;
        this.B = B;
        this.basisType = basisType;
        this.beta = beta;
        this.normBound = normBound;
        this.keyNormBound = keyNormBound;
        this.primeCheck = primeCheck;
        this.sparse = sparse;
        this.keyGenAlg = keyGenAlg;
        this.hashAlg = hashAlg;
        polyType = 0;
        init();
    }

    public NTRUSigningKeyGenerationParameters(int N, int q, int d1, int d2, int d3, int B, int basisType, 
            double beta, double normBound, double keyNormBound, boolean primeCheck, 
            boolean sparse, int keyGenAlg, Digest hashAlg)
    {
        super(new SecureRandom(), N);
        signFailTolerance = 100;
        bitsF = 6;
        this.N = N;
        this.q = q;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.B = B;
        this.basisType = basisType;
        this.beta = beta;
        this.normBound = normBound;
        this.keyNormBound = keyNormBound;
        this.primeCheck = primeCheck;
        this.sparse = sparse;
        this.keyGenAlg = keyGenAlg;
        this.hashAlg = hashAlg;
        polyType = 1;
        init();
    }

    private void init()
    {
        betaSq = beta * beta;
        normBoundSq = normBound * normBound;
        keyNormBoundSq = keyNormBound * keyNormBound;
    }

    public NTRUSigningKeyGenerationParameters(InputStream is)
        throws IOException
    {
        super(new SecureRandom(), 0);
        signFailTolerance = 100;
        bitsF = 6;
        DataInputStream dis = new DataInputStream(is);
        N = dis.readInt();
        q = dis.readInt();
        d = dis.readInt();
        d1 = dis.readInt();
        d2 = dis.readInt();
        d3 = dis.readInt();
        B = dis.readInt();
        basisType = dis.readInt();
        beta = dis.readDouble();
        normBound = dis.readDouble();
        keyNormBound = dis.readDouble();
        signFailTolerance = dis.readInt();
        primeCheck = dis.readBoolean();
        sparse = dis.readBoolean();
        bitsF = dis.readInt();
        keyGenAlg = dis.read();
        String alg = dis.readUTF();
        if("SHA-512".equals(alg))
            hashAlg = new SHA512Digest();
        else
        if("SHA-256".equals(alg))
            hashAlg = new SHA256Digest();
        polyType = dis.read();
        init();
    }

    public void writeTo(OutputStream os)
        throws IOException
    {
        DataOutputStream dos = new DataOutputStream(os);
        dos.writeInt(N);
        dos.writeInt(q);
        dos.writeInt(d);
        dos.writeInt(d1);
        dos.writeInt(d2);
        dos.writeInt(d3);
        dos.writeInt(B);
        dos.writeInt(basisType);
        dos.writeDouble(beta);
        dos.writeDouble(normBound);
        dos.writeDouble(keyNormBound);
        dos.writeInt(signFailTolerance);
        dos.writeBoolean(primeCheck);
        dos.writeBoolean(sparse);
        dos.writeInt(bitsF);
        dos.write(keyGenAlg);
        dos.writeUTF(hashAlg.getAlgorithmName());
        dos.write(polyType);
    }

    public NTRUSigningParameters getSigningParameters()
    {
        return new NTRUSigningParameters(N, q, d, B, beta, normBound, hashAlg);
    }

    public NTRUSigningKeyGenerationParameters clone()
    {
        if(polyType == 0)
            return new NTRUSigningKeyGenerationParameters(N, q, d, B, basisType, beta, normBound, keyNormBound, primeCheck, sparse, keyGenAlg, hashAlg);
        else
            return new NTRUSigningKeyGenerationParameters(N, q, d1, d2, d3, B, basisType, beta, normBound, keyNormBound, primeCheck, sparse, keyGenAlg, hashAlg);
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + B;
        result = 31 * result + N;
        result = 31 * result + basisType;
        long temp = Double.doubleToLongBits(beta);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(betaSq);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        result = 31 * result + bitsF;
        result = 31 * result + d;
        result = 31 * result + d1;
        result = 31 * result + d2;
        result = 31 * result + d3;
        result = 31 * result + (hashAlg != null ? hashAlg.getAlgorithmName().hashCode() : 0);
        result = 31 * result + keyGenAlg;
        temp = Double.doubleToLongBits(keyNormBound);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(keyNormBoundSq);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(normBound);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(normBoundSq);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        result = 31 * result + polyType;
        result = 31 * result + (primeCheck ? 1231 : '\u04D5');
        result = 31 * result + q;
        result = 31 * result + signFailTolerance;
        result = 31 * result + (sparse ? 1231 : '\u04D5');
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof NTRUSigningKeyGenerationParameters))
            return false;
        NTRUSigningKeyGenerationParameters other = (NTRUSigningKeyGenerationParameters)obj;
        if(B != other.B)
            return false;
        if(N != other.N)
            return false;
        if(basisType != other.basisType)
            return false;
        if(Double.doubleToLongBits(beta) != Double.doubleToLongBits(other.beta))
            return false;
        if(Double.doubleToLongBits(betaSq) != Double.doubleToLongBits(other.betaSq))
            return false;
        if(bitsF != other.bitsF)
            return false;
        if(d != other.d)
            return false;
        if(d1 != other.d1)
            return false;
        if(d2 != other.d2)
            return false;
        if(d3 != other.d3)
            return false;
        if(hashAlg == null)
        {
            if(other.hashAlg != null)
                return false;
        } else
        if(!hashAlg.getAlgorithmName().equals(other.hashAlg.getAlgorithmName()))
            return false;
        if(keyGenAlg != other.keyGenAlg)
            return false;
        if(Double.doubleToLongBits(keyNormBound) != Double.doubleToLongBits(other.keyNormBound))
            return false;
        if(Double.doubleToLongBits(keyNormBoundSq) != Double.doubleToLongBits(other.keyNormBoundSq))
            return false;
        if(Double.doubleToLongBits(normBound) != Double.doubleToLongBits(other.normBound))
            return false;
        if(Double.doubleToLongBits(normBoundSq) != Double.doubleToLongBits(other.normBoundSq))
            return false;
        if(polyType != other.polyType)
            return false;
        if(primeCheck != other.primeCheck)
            return false;
        if(q != other.q)
            return false;
        if(signFailTolerance != other.signFailTolerance)
            return false;
        return sparse == other.sparse;
    }

    public String toString()
    {
        DecimalFormat format = new DecimalFormat("0.00");
        StringBuilder output = new StringBuilder((new StringBuilder()).append("SignatureParameters(N=").append(N).append(" q=").append(q).toString());
        if(polyType == 0)
            output.append((new StringBuilder()).append(" polyType=SIMPLE d=").append(d).toString());
        else
            output.append((new StringBuilder()).append(" polyType=PRODUCT d1=").append(d1).append(" d2=").append(d2).append(" d3=").append(d3).toString());
        output.append((new StringBuilder()).append(" B=").append(B).append(" basisType=").append(basisType).append(" beta=").append(format.format(beta)).append(" normBound=").append(format.format(normBound)).append(" keyNormBound=").append(format.format(keyNormBound)).append(" prime=").append(primeCheck).append(" sparse=").append(sparse).append(" keyGenAlg=").append(keyGenAlg).append(" hashAlg=").append(hashAlg).append(")").toString());
        return output.toString();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

    public static final int BASIS_TYPE_STANDARD = 0;
    public static final int BASIS_TYPE_TRANSPOSE = 1;
    public static final int KEY_GEN_ALG_RESULTANT = 0;
    public static final int KEY_GEN_ALG_FLOAT = 1;
    public static final NTRUSigningKeyGenerationParameters APR2011_439 = new NTRUSigningKeyGenerationParameters(439, 2048, 146, 1, 1, 0.16500000000000001D, 400D, 280D, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_439_PROD = new NTRUSigningKeyGenerationParameters(439, 2048, 9, 8, 5, 1, 1, 0.16500000000000001D, 400D, 280D, false, true, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_743 = new NTRUSigningKeyGenerationParameters(743, 2048, 248, 1, 1, 0.127D, 405D, 360D, true, false, 0, new SHA512Digest());
    public static final NTRUSigningKeyGenerationParameters APR2011_743_PROD = new NTRUSigningKeyGenerationParameters(743, 2048, 11, 11, 15, 1, 1, 0.127D, 405D, 360D, true, false, 0, new SHA512Digest());
    public static final NTRUSigningKeyGenerationParameters TEST157 = new NTRUSigningKeyGenerationParameters(157, 256, 29, 1, 1, 0.38D, 200D, 80D, false, false, 0, new SHA256Digest());
    public static final NTRUSigningKeyGenerationParameters TEST157_PROD = new NTRUSigningKeyGenerationParameters(157, 256, 5, 5, 8, 1, 1, 0.38D, 200D, 80D, false, false, 0, new SHA256Digest());
    public int N;
    public int q;
    public int d;
    public int d1;
    public int d2;
    public int d3;
    public int B;
    double beta;
    public double betaSq;
    double normBound;
    public double normBoundSq;
    public int signFailTolerance;
    double keyNormBound;
    public double keyNormBoundSq;
    public boolean primeCheck;
    public int basisType;
    int bitsF;
    public boolean sparse;
    public int keyGenAlg;
    public Digest hashAlg;
    public int polyType;

}
