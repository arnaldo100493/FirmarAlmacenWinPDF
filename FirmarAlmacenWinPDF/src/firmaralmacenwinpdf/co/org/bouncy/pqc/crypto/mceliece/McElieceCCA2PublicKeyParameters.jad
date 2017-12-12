// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2PublicKeyParameters.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.pqc.math.linearalgebra.GF2Matrix;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McElieceCCA2KeyParameters, McElieceCCA2Parameters

public class McElieceCCA2PublicKeyParameters extends McElieceCCA2KeyParameters
{

    public McElieceCCA2PublicKeyParameters(String oid, int n, int t, GF2Matrix matrix, McElieceCCA2Parameters params)
    {
        super(false, params);
        this.oid = oid;
        this.n = n;
        this.t = t;
        matrixG = new GF2Matrix(matrix);
    }

    public McElieceCCA2PublicKeyParameters(String oid, int n, int t, byte encMatrix[], McElieceCCA2Parameters params)
    {
        super(false, params);
        this.oid = oid;
        this.n = n;
        this.t = t;
        matrixG = new GF2Matrix(encMatrix);
    }

    public int getN()
    {
        return n;
    }

    public int getT()
    {
        return t;
    }

    public GF2Matrix getMatrixG()
    {
        return matrixG;
    }

    public int getK()
    {
        return matrixG.getNumRows();
    }

    public String getOIDString()
    {
        return oid;
    }

    private String oid;
    private int n;
    private int t;
    private GF2Matrix matrixG;
}
