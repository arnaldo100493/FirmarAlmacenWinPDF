// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUEncryptionKeyPairGenerator.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.*;
import co.org.bouncy.pqc.math.ntru.polynomial.*;
import co.org.bouncy.pqc.math.ntru.util.Util;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUEncryptionKeyGenerationParameters, NTRUEncryptionPrivateKeyParameters, NTRUEncryptionPublicKeyParameters

public class NTRUEncryptionKeyPairGenerator
    implements AsymmetricCipherKeyPairGenerator
{

    public NTRUEncryptionKeyPairGenerator()
    {
    }

    public void init(KeyGenerationParameters param)
    {
        params = (NTRUEncryptionKeyGenerationParameters)param;
    }

    public AsymmetricCipherKeyPair generateKeyPair()
    {
        int N = params.N;
        int q = params.q;
        int df = params.df;
        int df1 = params.df1;
        int df2 = params.df2;
        int df3 = params.df3;
        int dg = params.dg;
        boolean fastFp = params.fastFp;
        boolean sparse = params.sparse;
        IntegerPolynomial fp = null;
        Polynomial t;
        IntegerPolynomial fq;
        do
        {
            IntegerPolynomial f;
            do
            {
                if(fastFp)
                {
                    t = ((Polynomial) (params.polyType != 0 ? ((Polynomial) (ProductFormPolynomial.generateRandom(N, df1, df2, df3, df3, params.getRandom()))) : ((Polynomial) (Util.generateRandomTernary(N, df, df, sparse, params.getRandom())))));
                    f = t.toIntegerPolynomial();
                    f.mult(3);
                    f.coeffs[0]++;
                    break;
                }
                t = ((Polynomial) (params.polyType != 0 ? ((Polynomial) (ProductFormPolynomial.generateRandom(N, df1, df2, df3, df3 - 1, params.getRandom()))) : ((Polynomial) (Util.generateRandomTernary(N, df, df - 1, sparse, params.getRandom())))));
                f = t.toIntegerPolynomial();
                fp = f.invertF3();
            } while(fp == null);
            fq = f.invertFq(q);
        } while(fq == null);
        if(fastFp)
        {
            fp = new IntegerPolynomial(N);
            fp.coeffs[0] = 1;
        }
        DenseTernaryPolynomial g;
        do
            g = DenseTernaryPolynomial.generateRandom(N, dg, dg - 1, params.getRandom());
        while(g.invertFq(q) == null);
        IntegerPolynomial h = g.mult(fq, q);
        h.mult3(q);
        h.ensurePositive(q);
        g.clear();
        fq.clear();
        NTRUEncryptionPrivateKeyParameters priv = new NTRUEncryptionPrivateKeyParameters(h, t, fp, params.getEncryptionParameters());
        NTRUEncryptionPublicKeyParameters pub = new NTRUEncryptionPublicKeyParameters(h, params.getEncryptionParameters());
        return new AsymmetricCipherKeyPair(pub, priv);
    }

    private NTRUEncryptionKeyGenerationParameters params;
}
