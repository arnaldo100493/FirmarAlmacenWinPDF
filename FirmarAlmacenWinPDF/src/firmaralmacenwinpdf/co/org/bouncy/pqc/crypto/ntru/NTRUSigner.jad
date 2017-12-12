// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUSigner.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.pqc.math.ntru.polynomial.IntegerPolynomial;
import co.org.bouncy.pqc.math.ntru.polynomial.Polynomial;
import java.nio.ByteBuffer;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUSigningPrivateKeyParameters, NTRUSigningPublicKeyParameters, NTRUSignerPrng, NTRUSigningParameters

public class NTRUSigner
{

    public NTRUSigner(NTRUSigningParameters params)
    {
        this.params = params;
    }

    public void init(boolean forSigning, CipherParameters params)
    {
        if(forSigning)
            signingKeyPair = (NTRUSigningPrivateKeyParameters)params;
        else
            verificationKey = (NTRUSigningPublicKeyParameters)params;
        hashAlg = this.params.hashAlg;
        hashAlg.reset();
    }

    public void update(byte b)
    {
        if(hashAlg == null)
        {
            throw new IllegalStateException("Call initSign or initVerify first!");
        } else
        {
            hashAlg.update(b);
            return;
        }
    }

    public void update(byte m[], int off, int length)
    {
        if(hashAlg == null)
        {
            throw new IllegalStateException("Call initSign or initVerify first!");
        } else
        {
            hashAlg.update(m, off, length);
            return;
        }
    }

    public byte[] generateSignature()
    {
        if(hashAlg == null || signingKeyPair == null)
        {
            throw new IllegalStateException("Call initSign first!");
        } else
        {
            byte msgHash[] = new byte[hashAlg.getDigestSize()];
            hashAlg.doFinal(msgHash, 0);
            return signHash(msgHash, signingKeyPair);
        }
    }

    private byte[] signHash(byte msgHash[], NTRUSigningPrivateKeyParameters kp)
    {
        int r = 0;
        NTRUSigningPublicKeyParameters kPub = kp.getPublicKey();
        IntegerPolynomial s;
        IntegerPolynomial i;
        do
        {
            if(++r > params.signFailTolerance)
                throw new IllegalStateException((new StringBuilder()).append("Signing failed: too many retries (max=").append(params.signFailTolerance).append(")").toString());
            i = createMsgRep(msgHash, r);
            s = sign(i, kp);
        } while(!verify(i, s, kPub.h));
        byte rawSig[] = s.toBinary(params.q);
        ByteBuffer sbuf = ByteBuffer.allocate(rawSig.length + 4);
        sbuf.put(rawSig);
        sbuf.putInt(r);
        return sbuf.array();
    }

    private IntegerPolynomial sign(IntegerPolynomial i, NTRUSigningPrivateKeyParameters kp)
    {
        int N = params.N;
        int q = params.q;
        int perturbationBases = params.B;
        NTRUSigningPrivateKeyParameters kPriv = kp;
        NTRUSigningPublicKeyParameters kPub = kp.getPublicKey();
        IntegerPolynomial s = new IntegerPolynomial(N);
        Polynomial f;
        Polynomial fPrime;
        IntegerPolynomial y;
        IntegerPolynomial x;
        for(int iLoop = perturbationBases; iLoop >= 1; iLoop--)
        {
            f = kPriv.getBasis(iLoop).f;
            fPrime = kPriv.getBasis(iLoop).fPrime;
            y = f.mult(i);
            y.div(q);
            y = fPrime.mult(y);
            x = fPrime.mult(i);
            x.div(q);
            x = f.mult(x);
            IntegerPolynomial si = y;
            si.sub(x);
            s.add(si);
            IntegerPolynomial hi = (IntegerPolynomial)kPriv.getBasis(iLoop).h.clone();
            if(iLoop > 1)
                hi.sub(kPriv.getBasis(iLoop - 1).h);
            else
                hi.sub(kPub.h);
            i = si.mult(hi, q);
        }

        f = kPriv.getBasis(0).f;
        fPrime = kPriv.getBasis(0).fPrime;
        y = f.mult(i);
        y.div(q);
        y = fPrime.mult(y);
        x = fPrime.mult(i);
        x.div(q);
        x = f.mult(x);
        y.sub(x);
        s.add(y);
        s.modPositive(q);
        return s;
    }

    public boolean verifySignature(byte sig[])
    {
        if(hashAlg == null || verificationKey == null)
        {
            throw new IllegalStateException("Call initVerify first!");
        } else
        {
            byte msgHash[] = new byte[hashAlg.getDigestSize()];
            hashAlg.doFinal(msgHash, 0);
            return verifyHash(msgHash, sig, verificationKey);
        }
    }

    private boolean verifyHash(byte msgHash[], byte sig[], NTRUSigningPublicKeyParameters pub)
    {
        ByteBuffer sbuf = ByteBuffer.wrap(sig);
        byte rawSig[] = new byte[sig.length - 4];
        sbuf.get(rawSig);
        IntegerPolynomial s = IntegerPolynomial.fromBinary(rawSig, params.N, params.q);
        int r = sbuf.getInt();
        return verify(createMsgRep(msgHash, r), s, pub.h);
    }

    private boolean verify(IntegerPolynomial i, IntegerPolynomial s, IntegerPolynomial h)
    {
        int q = params.q;
        double normBoundSq = params.normBoundSq;
        double betaSq = params.betaSq;
        IntegerPolynomial t = h.mult(s, q);
        t.sub(i);
        long centeredNormSq = (long)((double)s.centeredNormSq(q) + betaSq * (double)t.centeredNormSq(q));
        return (double)centeredNormSq <= normBoundSq;
    }

    protected IntegerPolynomial createMsgRep(byte msgHash[], int r)
    {
        int N = params.N;
        int q = params.q;
        int c = 31 - Integer.numberOfLeadingZeros(q);
        int B = (c + 7) / 8;
        IntegerPolynomial i = new IntegerPolynomial(N);
        ByteBuffer cbuf = ByteBuffer.allocate(msgHash.length + 4);
        cbuf.put(msgHash);
        cbuf.putInt(r);
        NTRUSignerPrng prng = new NTRUSignerPrng(cbuf.array(), params.hashAlg);
        for(int t = 0; t < N; t++)
        {
            byte o[] = prng.nextBytes(B);
            int hi = o[o.length - 1];
            hi >>= 8 * B - c;
            hi <<= 8 * B - c;
            o[o.length - 1] = (byte)hi;
            ByteBuffer obuf = ByteBuffer.allocate(4);
            obuf.put(o);
            obuf.rewind();
            i.coeffs[t] = Integer.reverseBytes(obuf.getInt());
        }

        return i;
    }

    private NTRUSigningParameters params;
    private Digest hashAlg;
    private NTRUSigningPrivateKeyParameters signingKeyPair;
    private NTRUSigningPublicKeyParameters verificationKey;
}
