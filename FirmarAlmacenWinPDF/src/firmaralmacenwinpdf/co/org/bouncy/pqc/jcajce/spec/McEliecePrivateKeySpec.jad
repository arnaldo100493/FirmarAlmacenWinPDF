// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McEliecePrivateKeySpec.java

package co.org.bouncy.pqc.jcajce.spec;

import co.org.bouncy.pqc.math.linearalgebra.*;
import java.security.spec.KeySpec;

public class McEliecePrivateKeySpec
    implements KeySpec
{

    public McEliecePrivateKeySpec(String oid, int n, int k, GF2mField field, PolynomialGF2mSmallM goppaPoly, GF2Matrix sInv, Permutation p1, 
            Permutation p2, GF2Matrix h, PolynomialGF2mSmallM qInv[])
    {
        this.oid = oid;
        this.k = k;
        this.n = n;
        this.field = field;
        this.goppaPoly = goppaPoly;
        this.sInv = sInv;
        this.p1 = p1;
        this.p2 = p2;
        this.h = h;
        this.qInv = qInv;
    }

    public McEliecePrivateKeySpec(String oid, int n, int k, byte encField[], byte encGoppaPoly[], byte encSInv[], byte encP1[], 
            byte encP2[], byte encH[], byte encQInv[][])
    {
        this.oid = oid;
        this.n = n;
        this.k = k;
        field = new GF2mField(encField);
        goppaPoly = new PolynomialGF2mSmallM(field, encGoppaPoly);
        sInv = new GF2Matrix(encSInv);
        p1 = new Permutation(encP1);
        p2 = new Permutation(encP2);
        h = new GF2Matrix(encH);
        qInv = new PolynomialGF2mSmallM[encQInv.length];
        for(int i = 0; i < encQInv.length; i++)
            qInv[i] = new PolynomialGF2mSmallM(field, encQInv[i]);

    }

    public int getN()
    {
        return n;
    }

    public int getK()
    {
        return k;
    }

    public GF2mField getField()
    {
        return field;
    }

    public PolynomialGF2mSmallM getGoppaPoly()
    {
        return goppaPoly;
    }

    public GF2Matrix getSInv()
    {
        return sInv;
    }

    public Permutation getP1()
    {
        return p1;
    }

    public Permutation getP2()
    {
        return p2;
    }

    public GF2Matrix getH()
    {
        return h;
    }

    public PolynomialGF2mSmallM[] getQInv()
    {
        return qInv;
    }

    public String getOIDString()
    {
        return oid;
    }

    private String oid;
    private int n;
    private int k;
    private GF2mField field;
    private PolynomialGF2mSmallM goppaPoly;
    private GF2Matrix sInv;
    private Permutation p1;
    private Permutation p2;
    private GF2Matrix h;
    private PolynomialGF2mSmallM qInv[];
}
