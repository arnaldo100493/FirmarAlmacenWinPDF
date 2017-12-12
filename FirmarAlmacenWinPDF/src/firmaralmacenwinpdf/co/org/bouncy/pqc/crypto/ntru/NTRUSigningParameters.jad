// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUSigningParameters.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.digests.SHA256Digest;
import co.org.bouncy.crypto.digests.SHA512Digest;
import java.io.*;
import java.text.DecimalFormat;

public class NTRUSigningParameters
    implements Cloneable
{

    public NTRUSigningParameters(int N, int q, int d, int B, double beta, double normBound, Digest hashAlg)
    {
        signFailTolerance = 100;
        bitsF = 6;
        this.N = N;
        this.q = q;
        this.d = d;
        this.B = B;
        this.beta = beta;
        this.normBound = normBound;
        this.hashAlg = hashAlg;
        init();
    }

    public NTRUSigningParameters(int N, int q, int d1, int d2, int d3, int B, double beta, double normBound, double keyNormBound, Digest hashAlg)
    {
        signFailTolerance = 100;
        bitsF = 6;
        this.N = N;
        this.q = q;
        this.d1 = d1;
        this.d2 = d2;
        this.d3 = d3;
        this.B = B;
        this.beta = beta;
        this.normBound = normBound;
        this.hashAlg = hashAlg;
        init();
    }

    private void init()
    {
        betaSq = beta * beta;
        normBoundSq = normBound * normBound;
    }

    public NTRUSigningParameters(InputStream is)
        throws IOException
    {
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
        beta = dis.readDouble();
        normBound = dis.readDouble();
        signFailTolerance = dis.readInt();
        bitsF = dis.readInt();
        String alg = dis.readUTF();
        if("SHA-512".equals(alg))
            hashAlg = new SHA512Digest();
        else
        if("SHA-256".equals(alg))
            hashAlg = new SHA256Digest();
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
        dos.writeDouble(beta);
        dos.writeDouble(normBound);
        dos.writeInt(signFailTolerance);
        dos.writeInt(bitsF);
        dos.writeUTF(hashAlg.getAlgorithmName());
    }

    public NTRUSigningParameters clone()
    {
        return new NTRUSigningParameters(N, q, d, B, beta, normBound, hashAlg);
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + B;
        result = 31 * result + N;
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
        temp = Double.doubleToLongBits(normBound);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        temp = Double.doubleToLongBits(normBoundSq);
        result = 31 * result + (int)(temp ^ temp >>> 32);
        result = 31 * result + q;
        result = 31 * result + signFailTolerance;
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof NTRUSigningParameters))
            return false;
        NTRUSigningParameters other = (NTRUSigningParameters)obj;
        if(B != other.B)
            return false;
        if(N != other.N)
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
        if(Double.doubleToLongBits(normBound) != Double.doubleToLongBits(other.normBound))
            return false;
        if(Double.doubleToLongBits(normBoundSq) != Double.doubleToLongBits(other.normBoundSq))
            return false;
        if(q != other.q)
            return false;
        return signFailTolerance == other.signFailTolerance;
    }

    public String toString()
    {
        DecimalFormat format = new DecimalFormat("0.00");
        StringBuilder output = new StringBuilder((new StringBuilder()).append("SignatureParameters(N=").append(N).append(" q=").append(q).toString());
        output.append((new StringBuilder()).append(" B=").append(B).append(" beta=").append(format.format(beta)).append(" normBound=").append(format.format(normBound)).append(" hashAlg=").append(hashAlg).append(")").toString());
        return output.toString();
    }

    public volatile Object clone()
        throws CloneNotSupportedException
    {
        return clone();
    }

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
    int bitsF;
    public Digest hashAlg;
}
