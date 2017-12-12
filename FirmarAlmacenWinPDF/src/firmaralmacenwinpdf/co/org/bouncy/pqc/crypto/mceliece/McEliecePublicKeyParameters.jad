// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McEliecePublicKeyParameters.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.pqc.math.linearalgebra.GF2Matrix;

// Referenced classes of package co.org.bouncy.pqc.crypto.mceliece:
//            McElieceKeyParameters, McElieceParameters

public class McEliecePublicKeyParameters extends McElieceKeyParameters
{

    public McEliecePublicKeyParameters(String oid, int n, int t, GF2Matrix g, McElieceParameters params)
    {
        super(false, params);
        this.oid = oid;
        this.n = n;
        this.t = t;
        this.g = new GF2Matrix(g);
    }

    public McEliecePublicKeyParameters(String oid, int t, int n, byte encG[], McElieceParameters params)
    {
        super(false, params);
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

    public int getK()
    {
        return g.getNumRows();
    }

    private String oid;
    private int n;
    private int t;
    private GF2Matrix g;
}
