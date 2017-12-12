// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2Primitives.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.pqc.math.linearalgebra.*;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McElieceCCA2PublicKeyParameters, McElieceCCA2PrivateKeyParameters

public final class McElieceCCA2Primitives
{

    private McElieceCCA2Primitives()
    {
    }

    public static GF2Vector encryptionPrimitive(McElieceCCA2PublicKeyParameters pubKey, GF2Vector m, GF2Vector z)
    {
        GF2Matrix matrixG = pubKey.getMatrixG();
        Vector mG = matrixG.leftMultiplyLeftCompactForm(m);
        return (GF2Vector)mG.add(z);
    }

    public static GF2Vector[] decryptionPrimitive(McElieceCCA2PrivateKeyParameters privKey, GF2Vector c)
    {
        int k = privKey.getK();
        Permutation p = privKey.getP();
        co.org.bouncy.pqc.math.linearalgebra.GF2mField field = privKey.getField();
        co.org.bouncy.pqc.math.linearalgebra.PolynomialGF2mSmallM gp = privKey.getGoppaPoly();
        GF2Matrix h = privKey.getH();
        co.org.bouncy.pqc.math.linearalgebra.PolynomialGF2mSmallM q[] = privKey.getQInv();
        Permutation pInv = p.computeInverse();
        GF2Vector cPInv = (GF2Vector)c.multiply(pInv);
        GF2Vector syndVec = (GF2Vector)h.rightMultiply(cPInv);
        GF2Vector errors = GoppaCode.syndromeDecode(syndVec, field, gp, q);
        GF2Vector mG = (GF2Vector)cPInv.add(errors);
        mG = (GF2Vector)mG.multiply(p);
        errors = (GF2Vector)errors.multiply(p);
        GF2Vector m = mG.extractRightVector(k);
        return (new GF2Vector[] {
            m, errors
        });
    }
}
