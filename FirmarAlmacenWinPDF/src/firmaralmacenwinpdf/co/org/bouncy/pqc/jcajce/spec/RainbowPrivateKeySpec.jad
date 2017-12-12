// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RainbowPrivateKeySpec.java

package co.org.bouncy.pqc.jcajce.spec;

import co.org.bouncy.pqc.crypto.rainbow.Layer;
import java.security.spec.KeySpec;

public class RainbowPrivateKeySpec
    implements KeySpec
{

    public RainbowPrivateKeySpec(short A1inv[][], short b1[], short A2inv[][], short b2[], int vi[], Layer layers[])
    {
        this.A1inv = A1inv;
        this.b1 = b1;
        this.A2inv = A2inv;
        this.b2 = b2;
        this.vi = vi;
        this.layers = layers;
    }

    public short[] getB1()
    {
        return b1;
    }

    public short[][] getInvA1()
    {
        return A1inv;
    }

    public short[] getB2()
    {
        return b2;
    }

    public short[][] getInvA2()
    {
        return A2inv;
    }

    public Layer[] getLayers()
    {
        return layers;
    }

    public int[] getVi()
    {
        return vi;
    }

    private short A1inv[][];
    private short b1[];
    private short A2inv[][];
    private short b2[];
    private int vi[];
    private Layer layers[];
}
