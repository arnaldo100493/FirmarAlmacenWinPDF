// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUSigningPrivateKeyParameters.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.pqc.math.ntru.polynomial.*;
import java.io.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUSigningPublicKeyParameters, NTRUSigningKeyGenerationParameters

public class NTRUSigningPrivateKeyParameters extends AsymmetricKeyParameter
{
    public static class Basis
    {

        void encode(OutputStream os, boolean include_h)
            throws IOException
        {
            int q = params.q;
            os.write(getEncoded(f));
            if(params.basisType == 0)
            {
                IntegerPolynomial fPrimeInt = fPrime.toIntegerPolynomial();
                for(int i = 0; i < fPrimeInt.coeffs.length; i++)
                    fPrimeInt.coeffs[i] += q / 2;

                os.write(fPrimeInt.toBinary(q));
            } else
            {
                os.write(getEncoded(fPrime));
            }
            if(include_h)
                os.write(h.toBinary(q));
        }

        private byte[] getEncoded(Polynomial p)
        {
            if(p instanceof ProductFormPolynomial)
                return ((ProductFormPolynomial)p).toBinary();
            else
                return p.toIntegerPolynomial().toBinary3Tight();
        }

        public int hashCode()
        {
            int prime = 31;
            int result = 1;
            result = 31 * result + (f != null ? f.hashCode() : 0);
            result = 31 * result + (fPrime != null ? fPrime.hashCode() : 0);
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
            if(!(obj instanceof Basis))
                return false;
            Basis other = (Basis)obj;
            if(f == null)
            {
                if(other.f != null)
                    return false;
            } else
            if(!f.equals(other.f))
                return false;
            if(fPrime == null)
            {
                if(other.fPrime != null)
                    return false;
            } else
            if(!fPrime.equals(other.fPrime))
                return false;
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

        public Polynomial f;
        public Polynomial fPrime;
        public IntegerPolynomial h;
        NTRUSigningKeyGenerationParameters params;

        protected Basis(Polynomial f, Polynomial fPrime, IntegerPolynomial h, NTRUSigningKeyGenerationParameters params)
        {
            this.f = f;
            this.fPrime = fPrime;
            this.h = h;
            this.params = params;
        }

        Basis(InputStream is, NTRUSigningKeyGenerationParameters params, boolean include_h)
            throws IOException
        {
            int N = params.N;
            int q = params.q;
            int d1 = params.d1;
            int d2 = params.d2;
            int d3 = params.d3;
            boolean sparse = params.sparse;
            this.params = params;
            if(params.polyType == 1)
            {
                f = ProductFormPolynomial.fromBinary(is, N, d1, d2, d3 + 1, d3);
            } else
            {
                IntegerPolynomial fInt = IntegerPolynomial.fromBinary3Tight(is, N);
                f = ((Polynomial) (sparse ? ((Polynomial) (new SparseTernaryPolynomial(fInt))) : ((Polynomial) (new DenseTernaryPolynomial(fInt)))));
            }
            if(params.basisType == 0)
            {
                IntegerPolynomial fPrimeInt = IntegerPolynomial.fromBinary(is, N, q);
                for(int i = 0; i < fPrimeInt.coeffs.length; i++)
                    fPrimeInt.coeffs[i] -= q / 2;

                fPrime = fPrimeInt;
            } else
            if(params.polyType == 1)
                fPrime = ProductFormPolynomial.fromBinary(is, N, d1, d2, d3 + 1, d3);
            else
                fPrime = IntegerPolynomial.fromBinary3Tight(is, N);
            if(include_h)
                h = IntegerPolynomial.fromBinary(is, N, q);
        }
    }


    public NTRUSigningPrivateKeyParameters(byte b[], NTRUSigningKeyGenerationParameters params)
        throws IOException
    {
        this(((InputStream) (new ByteArrayInputStream(b))), params);
    }

    public NTRUSigningPrivateKeyParameters(InputStream is, NTRUSigningKeyGenerationParameters params)
        throws IOException
    {
        super(true);
        bases = new ArrayList();
        for(int i = 0; i <= params.B; i++)
            add(new Basis(is, params, i != 0));

        publicKey = new NTRUSigningPublicKeyParameters(is, params.getSigningParameters());
    }

    public NTRUSigningPrivateKeyParameters(List bases, NTRUSigningPublicKeyParameters publicKey)
    {
        super(true);
        this.bases = new ArrayList(bases);
        this.publicKey = publicKey;
    }

    private void add(Basis b)
    {
        bases.add(b);
    }

    public Basis getBasis(int i)
    {
        return (Basis)bases.get(i);
    }

    public NTRUSigningPublicKeyParameters getPublicKey()
    {
        return publicKey;
    }

    public byte[] getEncoded()
        throws IOException
    {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        for(int i = 0; i < bases.size(); i++)
            ((Basis)bases.get(i)).encode(os, i != 0);

        os.write(publicKey.getEncoded());
        return os.toByteArray();
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
        result = 31 * result + (bases != null ? bases.hashCode() : 0);
        for(Iterator i$ = bases.iterator(); i$.hasNext();)
        {
            Basis basis = (Basis)i$.next();
            result += basis.hashCode();
        }

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
        NTRUSigningPrivateKeyParameters other = (NTRUSigningPrivateKeyParameters)obj;
        if(bases == null && other.bases != null)
            return false;
        if(bases.size() != other.bases.size())
            return false;
        for(int i = 0; i < bases.size(); i++)
        {
            Basis basis1 = (Basis)bases.get(i);
            Basis basis2 = (Basis)other.bases.get(i);
            if(!basis1.f.equals(basis2.f))
                return false;
            if(!basis1.fPrime.equals(basis2.fPrime))
                return false;
            if(i != 0 && !basis1.h.equals(basis2.h))
                return false;
            if(!basis1.params.equals(basis2.params))
                return false;
        }

        return true;
    }

    private List bases;
    private NTRUSigningPublicKeyParameters publicKey;
}
