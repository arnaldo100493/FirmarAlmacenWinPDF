// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUSigningKeyPairGenerator.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.*;
import co.org.bouncy.pqc.math.ntru.euclid.BigIntEuclidean;
import co.org.bouncy.pqc.math.ntru.polynomial.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUSigningKeyGenerationParameters, NTRUSigningPublicKeyParameters, NTRUSigningPrivateKeyParameters

public class NTRUSigningKeyPairGenerator
    implements AsymmetricCipherKeyPairGenerator
{
    public class FGBasis extends NTRUSigningPrivateKeyParameters.Basis
    {

        boolean isNormOk()
        {
            double keyNormBoundSq = params.keyNormBoundSq;
            int q = params.q;
            return (double)F.centeredNormSq(q) < keyNormBoundSq && (double)G.centeredNormSq(q) < keyNormBoundSq;
        }

        public IntegerPolynomial F;
        public IntegerPolynomial G;
        final NTRUSigningKeyPairGenerator this$0;

        FGBasis(Polynomial f, Polynomial fPrime, IntegerPolynomial h, IntegerPolynomial F, IntegerPolynomial G, NTRUSigningKeyGenerationParameters params)
        {
            this$0 = NTRUSigningKeyPairGenerator.this;
            super(f, fPrime, h, params);
            this.F = F;
            this.G = G;
        }
    }

    private class BasisGenerationTask
        implements Callable
    {

        public NTRUSigningPrivateKeyParameters.Basis call()
            throws Exception
        {
            return generateBoundedBasis();
        }

        public volatile Object call()
            throws Exception
        {
            return call();
        }

        final NTRUSigningKeyPairGenerator this$0;

        private BasisGenerationTask()
        {
            this$0 = NTRUSigningKeyPairGenerator.this;
            super();
        }

    }


    public NTRUSigningKeyPairGenerator()
    {
    }

    public void init(KeyGenerationParameters param)
    {
        params = (NTRUSigningKeyGenerationParameters)param;
    }

    public AsymmetricCipherKeyPair generateKeyPair()
    {
        NTRUSigningPublicKeyParameters pub = null;
        ExecutorService executor = Executors.newCachedThreadPool();
        List bases = new ArrayList();
        for(int k = params.B; k >= 0; k--)
            bases.add(executor.submit(new BasisGenerationTask()));

        executor.shutdown();
        List basises = new ArrayList();
        for(int k = params.B; k >= 0; k--)
        {
            Future basis = (Future)bases.get(k);
            try
            {
                basises.add(basis.get());
                if(k == params.B)
                    pub = new NTRUSigningPublicKeyParameters(((NTRUSigningPrivateKeyParameters.Basis)basis.get()).h, params.getSigningParameters());
            }
            catch(Exception e)
            {
                throw new IllegalStateException(e);
            }
        }

        NTRUSigningPrivateKeyParameters priv = new NTRUSigningPrivateKeyParameters(basises, pub);
        AsymmetricCipherKeyPair kp = new AsymmetricCipherKeyPair(pub, priv);
        return kp;
    }

    public AsymmetricCipherKeyPair generateKeyPairSingleThread()
    {
        List basises = new ArrayList();
        NTRUSigningPublicKeyParameters pub = null;
        for(int k = params.B; k >= 0; k--)
        {
            NTRUSigningPrivateKeyParameters.Basis basis = generateBoundedBasis();
            basises.add(basis);
            if(k == 0)
                pub = new NTRUSigningPublicKeyParameters(basis.h, params.getSigningParameters());
        }

        NTRUSigningPrivateKeyParameters priv = new NTRUSigningPrivateKeyParameters(basises, pub);
        return new AsymmetricCipherKeyPair(pub, priv);
    }

    private void minimizeFG(IntegerPolynomial f, IntegerPolynomial g, IntegerPolynomial F, IntegerPolynomial G, int N)
    {
        int E = 0;
        for(int j = 0; j < N; j++)
            E += 2 * N * (f.coeffs[j] * f.coeffs[j] + g.coeffs[j] * g.coeffs[j]);

        E -= 4;
        IntegerPolynomial u = (IntegerPolynomial)f.clone();
        IntegerPolynomial v = (IntegerPolynomial)g.clone();
        int j = 0;
        int k = 0;
        for(int maxAdjustment = N; k < maxAdjustment && j < N;)
        {
            int D = 0;
            int D1;
            for(int i = 0; i < N; i++)
            {
                D1 = F.coeffs[i] * f.coeffs[i];
                int D2 = G.coeffs[i] * g.coeffs[i];
                int D3 = 4 * N * (D1 + D2);
                D += D3;
            }

            D1 = 4 * (F.sumCoeffs() + G.sumCoeffs());
            D -= D1;
            if(D > E)
            {
                F.sub(u);
                G.sub(v);
                k++;
                j = 0;
            } else
            if(D < -E)
            {
                F.add(u);
                G.add(v);
                k++;
                j = 0;
            }
            j++;
            u.rotate1();
            v.rotate1();
        }

    }

    private FGBasis generateBasis()
    {
        int N = params.N;
        int q = params.q;
        int d = params.d;
        int d1 = params.d1;
        int d2 = params.d2;
        int d3 = params.d3;
        int basisType = params.basisType;
        int _2n1 = 2 * N + 1;
        boolean primeCheck = params.primeCheck;
        Polynomial f;
        IntegerPolynomial fInt;
        IntegerPolynomial fq;
        do
        {
            do
            {
                f = ((Polynomial) (params.polyType != 0 ? ((Polynomial) (ProductFormPolynomial.generateRandom(N, d1, d2, d3 + 1, d3, new SecureRandom()))) : ((Polynomial) (DenseTernaryPolynomial.generateRandom(N, d + 1, d, new SecureRandom())))));
                fInt = f.toIntegerPolynomial();
            } while(primeCheck && fInt.resultant(_2n1).res.equals(BigInteger.ZERO));
            fq = fInt.invertFq(q);
        } while(fq == null);
        Resultant rf = fInt.resultant();
        Polynomial g;
        IntegerPolynomial gInt;
        Resultant rg;
        BigIntEuclidean r;
        do
        {
            do
            {
                g = ((Polynomial) (params.polyType != 0 ? ((Polynomial) (ProductFormPolynomial.generateRandom(N, d1, d2, d3 + 1, d3, new SecureRandom()))) : ((Polynomial) (DenseTernaryPolynomial.generateRandom(N, d + 1, d, new SecureRandom())))));
                gInt = g.toIntegerPolynomial();
            } while(primeCheck && gInt.resultant(_2n1).res.equals(BigInteger.ZERO) || gInt.invertFq(q) == null);
            rg = gInt.resultant();
            r = BigIntEuclidean.calculate(rf.res, rg.res);
        } while(!r.gcd.equals(BigInteger.ONE));
        BigIntPolynomial A = (BigIntPolynomial)rf.rho.clone();
        A.mult(r.x.multiply(BigInteger.valueOf(q)));
        BigIntPolynomial B = (BigIntPolynomial)rg.rho.clone();
        B.mult(r.y.multiply(BigInteger.valueOf(-q)));
        BigIntPolynomial C;
        if(params.keyGenAlg == 0)
        {
            int fRevCoeffs[] = new int[N];
            int gRevCoeffs[] = new int[N];
            fRevCoeffs[0] = fInt.coeffs[0];
            gRevCoeffs[0] = gInt.coeffs[0];
            for(int i = 1; i < N; i++)
            {
                fRevCoeffs[i] = fInt.coeffs[N - i];
                gRevCoeffs[i] = gInt.coeffs[N - i];
            }

            IntegerPolynomial fRev = new IntegerPolynomial(fRevCoeffs);
            IntegerPolynomial gRev = new IntegerPolynomial(gRevCoeffs);
            IntegerPolynomial t = f.mult(fRev);
            t.add(g.mult(gRev));
            Resultant rt = t.resultant();
            C = fRev.mult(B);
            C.add(gRev.mult(A));
            C = C.mult(rt.rho);
            C.div(rt.res);
        } else
        {
            int log10N = 0;
            for(int i = 1; i < N; i *= 10)
                log10N++;

            BigDecimalPolynomial fInv = rf.rho.div(new BigDecimal(rf.res), B.getMaxCoeffLength() + 1 + log10N);
            BigDecimalPolynomial gInv = rg.rho.div(new BigDecimal(rg.res), A.getMaxCoeffLength() + 1 + log10N);
            BigDecimalPolynomial Cdec = fInv.mult(B);
            Cdec.add(gInv.mult(A));
            Cdec.halve();
            C = Cdec.round();
        }
        BigIntPolynomial F = (BigIntPolynomial)B.clone();
        F.sub(f.mult(C));
        BigIntPolynomial G = (BigIntPolynomial)A.clone();
        G.sub(g.mult(C));
        IntegerPolynomial FInt = new IntegerPolynomial(F);
        IntegerPolynomial GInt = new IntegerPolynomial(G);
        minimizeFG(fInt, gInt, FInt, GInt, N);
        Polynomial fPrime;
        IntegerPolynomial h;
        if(basisType == 0)
        {
            fPrime = FInt;
            h = g.mult(fq, q);
        } else
        {
            fPrime = g;
            h = FInt.mult(fq, q);
        }
        h.modPositive(q);
        return new FGBasis(f, fPrime, h, FInt, GInt, params);
    }

    public NTRUSigningPrivateKeyParameters.Basis generateBoundedBasis()
    {
        FGBasis basis;
        do
            basis = generateBasis();
        while(!basis.isNormOk());
        return basis;
    }

    private NTRUSigningKeyGenerationParameters params;
}
