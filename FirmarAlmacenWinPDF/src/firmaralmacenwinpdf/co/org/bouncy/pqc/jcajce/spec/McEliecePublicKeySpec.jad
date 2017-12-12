// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McEliecePublicKeySpec.java

package co.org.bouncy.pqc.jcajce.spec;

import co.org.bouncy.pqc.math.linearalgebra.GF2Matrix;
import java.security.spec.KeySpec;

public class McEliecePublicKeySpec
    implements KeySpec
{

    public McEliecePublicKeySpec(String oid, int n, int t, GF2Matrix g)
    {
        this.oid = oid;
        this.n = n;
        this.t = t;
        this.g = new GF2Matrix(g);
    }

    public McEliecePublicKeySpec(String oid, int t, int n, byte encG[])
    {
        this.oid = oid;
        this.n = n;
        this.t = t;
        g = new GF2Matrix(encG);
    }

    public int getN()
    {
        return n;
    }

    public int getT()
    {
        return t;
    }

    public GF2Matrix getG()
    {
        return g;
    }

    public String getOIDString()
    {
        return oid;
    }

    private String oid;
    private int n;
    private int t;
    private GF2Matrix g;
}
