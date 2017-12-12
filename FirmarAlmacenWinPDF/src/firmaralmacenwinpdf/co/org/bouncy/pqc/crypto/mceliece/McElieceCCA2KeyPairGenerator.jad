// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2KeyPairGenerator.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.crypto.*;
import co.org.bouncy.pqc.math.linearalgebra.*;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McElieceCCA2KeyGenerationParameters, McElieceCCA2Parameters, McElieceCCA2PublicKeyParameters, McElieceCCA2PrivateKeyParameters

public class McElieceCCA2KeyPairGenerator
    implements AsymmetricCipherKeyPairGenerator
{

    public McElieceCCA2KeyPairGenerator()
    {
        initialized = false;
    }

    private void initializeDefault()
    {
        McElieceCCA2KeyGenerationParameters mcCCA2Params = new McElieceCCA2KeyGenerationParameters(new SecureRandom(), new McElieceCCA2Parameters());
        init(mcCCA2Params);
    }

    public void init(KeyGenerationParameters param)
    {
        mcElieceCCA2Params = (McElieceCCA2KeyGenerationParameters)param;
        random = new SecureRandom();
        m = mcElieceCCA2Params.getParameters().getM();
        n = mcElieceCCA2Params.getParameters().getN();
        t = mcElieceCCA2Params.getParameters().getT();
        fieldPoly = mcElieceCCA2Params.getParameters().getFieldPoly();
        initialized = true;
    }

    public AsymmetricCipherKeyPair generateKeyPair()
    {
        if(!initialized)
            initializeDefault();
        GF2mField field = new GF2mField(m, fieldPoly);
        PolynomialGF2mSmallM gp = new PolynomialGF2mSmallM(field, t, 'I', random);
        PolynomialRingGF2m ring = new PolynomialRingGF2m(field, gp);
        PolynomialGF2mSmallM qInv[] = ring.getSquareRootMatrix();
        GF2Matrix h = GoppaCode.createCanonicalCheckMatrix(field, gp);
        co.org.bouncy.pqc.math.linearalgebra.GoppaCode.MaMaPe mmp = GoppaCode.computeSystematicForm(h, random);
        GF2Matrix shortH = mmp.getSecondMatrix();
        co.org.bouncy.pqc.math.linearalgebra.Permutation p = mmp.getPermutation();
        GF2Matrix shortG = (GF2Matrix)shortH.computeTranspose();
        int k = shortG.getNumRows();
        McElieceCCA2PublicKeyParameters pubKey = new McElieceCCA2PublicKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.2", n, t, shortG, mcElieceCCA2Params.getParameters());
        McElieceCCA2PrivateKeyParameters privKey = new McElieceCCA2PrivateKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.2", n, k, field, gp, p, h, qInv, mcElieceCCA2Params.getParameters());
        return new AsymmetricCipherKeyPair(pubKey, privKey);
    }

    public static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.2";
    private McElieceCCA2KeyGenerationParameters mcElieceCCA2Params;
    private int m;
    private int n;
    private int t;
    private int fieldPoly;
    private SecureRandom random;
    private boolean initialized;
}
