// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUEncryptionPrivateKeyParameters.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.pqc.math.ntru.polynomial.*;
import java.io.*;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUEncryptionKeyParameters, NTRUEncryptionParameters

public class NTRUEncryptionPrivateKeyParameters extends NTRUEncryptionKeyParameters
{

    public NTRUEncryptionPrivateKeyParameters(IntegerPolynomial h, Polynomial t, IntegerPolynomial fp, NTRUEncryptionParameters params)
    {
        super(true, params);
        this.h = h;
        this.t = t;
        this.fp = fp;
    }

    public NTRUEncryptionPrivateKeyParameters(byte b[], NTRUEncryptionParameters params)
        throws IOException
    {
        this(((InputStream) (new ByteArrayInputStream(b))), params);
    }

    public NTRUEncryptionPrivateKeyParameters(InputStream is, NTRUEncryptionParameters params)
        throws IOException
    {
        super(true, params);
        if(params.polyType == 1)
        {
            int N = params.N;
            int df1 = params.df1;
            int df2 = params.df2;
            int df3Ones = params.df3;
            int df3NegOnes = params.fastFp ? params.df3 : params.df3 - 1;
            h = IntegerPolynomial.fromBinary(is, params.N, params.q);
            t = ProductFormPolynomial.fromBinary(is, N, df1, df2, df3Ones, df3NegOnes);
        } else
        {
            h = IntegerPolynomial.fromBinary(is, params.N, params.q);
            IntegerPolynomial fInt = IntegerPolynomial.fromBinary3Tight(is, params.N);
            t = ((Polynomial) (params.sparse ? ((Polynomial) (new SparseTernaryPolynomial(fInt))) : ((Polynomial) (new DenseTernaryPolynomial(fInt)))));
        }
        init();
    }

    private void init()
    {
        if(params.fastFp)
        {
            fp = new IntegerPolynomial(params.N);
            fp.coeffs[0] = 1;
        } else
        {
            fp = t.toIntegerPolynomial().invertF3();
        }
    }

    public byte[] getEncoded()
    {
        byte hBytes[] = h.toBinary(params.q);
        byte tBytes[];
        if(t instanceof ProductFormPolynomial)
            tBytes = ((ProductFormPolynomial)t).toBinary();
        else
            tBytes = t.toIntegerPolynomial().toBinary3Tight();
        byte res[] = new byte[hBytes.length + tBytes.length];
        System.arraycopy(hBytes, 0, res, 0, hBytes.length);
        System.arraycopy(tBytes, 0, res, hBytes.length, tBytes.length);
        return res;
    }

    public void writeTo(OutputStream os)
        throws IOException
    {
        os.write(getEncoded());
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (params != null ? params.hashCode() : 0);
        result = 31 * result + (t != null ? t.hashCode() : 0);
        result = 31 * result + (h != null ? h.hashCode() : 0);
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof NTRUEncryptionPrivateKeyParameters))
            return false;
        NTRUEncryptionPrivateKeyParameters other = (NTRUEncryptionPrivateKeyParameters)obj;
        if(params == null)
        {
            if(other.params != null)
                return false;
        } else
        if(!params.equals(other.params))
            return false;
        if(t == null)
        {
            if(other.t != null)
                return false;
        } else
        if(!t.equals(other.t))
            return false;
        return h.equals(other.h);
    }

    public Polynomial t;
    public IntegerPolynomial fp;
    public IntegerPolynomial h;
}
