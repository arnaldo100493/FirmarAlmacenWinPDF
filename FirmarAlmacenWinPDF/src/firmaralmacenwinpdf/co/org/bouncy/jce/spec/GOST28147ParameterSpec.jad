// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GOST28147ParameterSpec.java

package co.org.bouncy.jce.spec;

import co.org.bouncy.crypto.engines.GOST28147Engine;
import java.security.spec.AlgorithmParameterSpec;

public class GOST28147ParameterSpec
    implements AlgorithmParameterSpec
{

    public GOST28147ParameterSpec(byte sBox[])
    {
        iv = null;
        this.sBox = null;
        this.sBox = new byte[sBox.length];
        System.arraycopy(sBox, 0, this.sBox, 0, sBox.length);
    }

    public GOST28147ParameterSpec(byte sBox[], byte iv[])
    {
        this(sBox);
        this.iv = new byte[iv.length];
        System.arraycopy(iv, 0, this.iv, 0, iv.length);
    }

    public GOST28147ParameterSpec(String sBoxName)
    {
        iv = null;
        sBox = null;
        sBox = GOST28147Engine.getSBox(sBoxName);
    }

    public GOST28147ParameterSpec(String sBoxName, byte iv[])
    {
        this(sBoxName);
        this.iv = new byte[iv.length];
        System.arraycopy(iv, 0, this.iv, 0, iv.length);
    }

    public byte[] getSbox()
    {
        return sBox;
    }

    public byte[] getIV()
    {
        if(iv == null)
        {
            return null;
        } else
        {
            byte tmp[] = new byte[iv.length];
            System.arraycopy(iv, 0, tmp, 0, tmp.length);
            return tmp;
        }
    }

    private byte iv[];
    private byte sBox[];
}
