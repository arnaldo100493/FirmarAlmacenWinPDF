// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUEncryptionPublicKeyParameters.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.pqc.math.ntru.polynomial.IntegerPolynomial;
import java.io.*;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUEncryptionKeyParameters, NTRUEncryptionParameters

public class NTRUEncryptionPublicKeyParameters extends NTRUEncryptionKeyParameters
{

    public NTRUEncryptionPublicKeyParameters(IntegerPolynomial h, NTRUEncryptionParameters params)
    {
        super(false, params);
        this.h = h;
    }

    public NTRUEncryptionPublicKeyParameters(byte b[], NTRUEncryptionParameters params)
    {
        super(false, params);
        h = IntegerPolynomial.fromBinary(b, params.N, params.q);
    }

    public NTRUEncryptionPublicKeyParameters(InputStream is, NTRUEncryptionParameters params)
        throws IOException
    {
        super(false, params);
        h = IntegerPolynomial.fromBinary(is, params.N, params.q);
    }

    public byte[] getEncoded()
    {
        return h.toBinary(params.q);
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
        result = 31 * result + (h != null ? h.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof NTRUEncryptionPublicKeyParameters))
            return false;
        NTRUEncryptionPublicKeyParameters other = (NTRUEncryptionPublicKeyParameters)obj;
        if(h == null)
        {
            if(other.h != null)
                return false;
        } else
        if(!h.equals(other.h))
            return false;
        if(params == null)
        {
            if(other.params != null)
                return false;
        } else
        if(!params.equals(other.params))
            return false;
        return true;
    }

    public IntegerPolynomial h;
}
