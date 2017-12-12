// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceKeyPairGenerator.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.crypto.*;
import co.org.bouncy.pqc.math.linearalgebra.*;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McElieceKeyGenerationParameters, McElieceParameters, McEliecePublicKeyParameters, McEliecePrivateKeyParameters

public class McElieceKeyPairGenerator
    implements AsymmetricCipherKeyPairGenerator
{

    public McElieceKeyPairGenerator()
    {
        initialized = false;
    }

    private void initializeDefault()
    {
        McElieceKeyGenerationParameters mcParams = new McElieceKeyGenerationParameters(new SecureRandom(), new McElieceParameters());
        initialize(mcParams);
    }

    private void initialize(KeyGenerationParameters param)
    {
        mcElieceParams = (McElieceKeyGenerationParameters)param;
        random = new SecureRandom();
        m = mcElieceParams.getParameters().getM();
        n = mcElieceParams.getParameters().getN();
        t = mcElieceParams.getParameters().getT();
        fieldPoly = mcElieceParams.getParameters().getFieldPoly();
        initialized = true;
    }

    private AsymmetricCipherKeyPair genKeyPair()
    {
        if(!initialized)
            initializeDefault();
        GF2mField field = new GF2mField(m, fieldPoly);
        PolynomialGF2mSmallM gp = new PolynomialGF2mSmallM(field, t, 'I', random);
        PolynomialRingGF2m ring = new PolynomialRingGF2m(field, gp);
        PolynomialGF2mSmallM sqRootMatrix[] = ring.getSquareRootMatrix();
        GF2Matrix h = GoppaCode.createCanonicalCheckMatrix(field, gp);
        co.org.bouncy.pqc.math.linearalgebra.GoppaCode.MaMaPe mmp = GoppaCode.computeSystematicForm(h, random);
        GF2Matrix shortH = mmp.getSecondMatrix();
        Permutation p1 = mmp.getPermutation();
        GF2Matrix shortG = (GF2Matrix)shortH.computeTranspose();
        GF2Matrix gPrime = shortG.extendLeftCompactForm();
        int k = shortG.getNumRows();
        GF2Matrix matrixSandInverse[] = GF2Matrix.createRandomRegularMatrixAndItsInverse(k, random);
        Permutation p2 = new Permutation(n, random);
        GF2Matrix g = (GF2Matrix)matrixSandInverse[0].rightMultiply(gPrime);
        g = (GF2Matrix)g.rightMultiply(p2);
        McEliecePublicKeyParameters pubKey = new McEliecePublicKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.1", n, t, g, mcElieceParams.getParameters());
        McEliecePrivateKeyParameters privKey = new McEliecePrivateKeyParameters("1.3.6.1.4.1.8301.3.1.3.4.1", n, k, field, gp, matrixSandInverse[1], p1, p2, h, sqRootMatrix, mcElieceParams.getParameters());
        return new AsymmetricCipherKeyPair(pubKey, privKey);
    }

    public void init(KeyGenerationParameters param)
    {
        initialize(param);
    }

    public AsymmetricCipherKeyPair generateKeyPair()
    {
        return genKeyPair();
    }

    private static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
    private McElieceKeyGenerationParameters mcElieceParams;
    private int m;
    private int n;
    private int t;
    private int fieldPoly;
    private SecureRandom random;
    private boolean initialized;
}
