// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2PublicKeySpec.java

package co.org.bouncy.pqc.jcajce.spec;

import co.org.bouncy.pqc.math.linearalgebra.GF2Matrix;
import java.security.spec.KeySpec;

public class McElieceCCA2PublicKeySpec
    implements KeySpec
{

    public McElieceCCA2PublicKeySpec(String oid, int n, int t, GF2Matrix matrix)
    {
        this.oid = oid;
        this.n = n;
        this.t = t;
        matrixG = new GF2Matrix(matrix);
    }

    public McElieceCCA2PublicKeySpec(String oid, int n, int t, byte encMatrix[])
    {
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

    public String getOIDString()
    {
        return oid;
    }

    private String oid;
    private int n;
    private int t;
    private GF2Matrix matrixG;
}
