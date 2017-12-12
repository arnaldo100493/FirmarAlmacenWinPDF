// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUSigningKeyPairGenerator.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.pqc.math.ntru.polynomial.IntegerPolynomial;
import co.org.bouncy.pqc.math.ntru.polynomial.Polynomial;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUSigningKeyGenerationParameters, NTRUSigningKeyPairGenerator, NTRUSigningPrivateKeyParameters

public class NTRUSigningKeyPairGenerator$FGBasis extends s
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

    NTRUSigningKeyPairGenerator$FGBasis(Polynomial f, Polynomial fPrime, IntegerPolynomial h, IntegerPolynomial F, IntegerPolynomial G, NTRUSigningKeyGenerationParameters params)
    {
        this$0 = NTRUSigningKeyPairGenerator.this;
        super(f, fPrime, h, params);
        this.F = F;
        this.G = G;
    }
}
