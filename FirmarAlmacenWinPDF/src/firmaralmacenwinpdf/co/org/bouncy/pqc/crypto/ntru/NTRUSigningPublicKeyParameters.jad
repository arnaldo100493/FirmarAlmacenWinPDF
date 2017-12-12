// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUSigningPublicKeyParameters.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.pqc.math.ntru.polynomial.IntegerPolynomial;
import java.io.*;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUSigningParameters

public class NTRUSigningPublicKeyParameters extends AsymmetricKeyParameter
{

    public NTRUSigningPublicKeyParameters(IntegerPolynomial h, NTRUSigningParameters params)
    {
        super(false);
        this.h = h;
        this.params = params;
    }

    public NTRUSigningPublicKeyParameters(byte b[], NTRUSigningParameters params)
    {
        super(false);
        h = IntegerPolynomial.fromBinary(b, params.N, params.q);
        this.params = params;
    }

    public NTRUSigningPublicKeyParameters(InputStream is, NTRUSigningParameters params)
        throws IOException
    {
        super(false);
        h = IntegerPolynomial.fromBinary(is, params.N, params.q);
        this.params = params;
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
        if(getClass() != obj.getClass())
            return false;
        NTRUSigningPublicKeyParameters other = (NTRUSigningPublicKeyParameters)obj;
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

    private NTRUSigningParameters params;
    public IntegerPolynomial h;
}
