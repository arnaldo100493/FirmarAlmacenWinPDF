// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IESParameterSpec.java

package co.org.bouncy.jce.spec;

import java.security.spec.AlgorithmParameterSpec;

public class IESParameterSpec
    implements AlgorithmParameterSpec
{

    public IESParameterSpec(byte derivation[], byte encoding[], int macKeySize)
    {
        this(derivation, encoding, macKeySize, -1);
    }

    public IESParameterSpec(byte derivation[], byte encoding[], int macKeySize, int cipherKeySize)
    {
        if(derivation != null)
        {
            this.derivation = new byte[derivation.length];
            System.arraycopy(derivation, 0, this.derivation, 0, derivation.length);
        } else
        {
            this.derivation = null;
        }
        if(encoding != null)
        {
            this.encoding = new byte[encoding.length];
            System.arraycopy(encoding, 0, this.encoding, 0, encoding.length);
        } else
        {
            this.encoding = null;
        }
        this.macKeySize = macKeySize;
        this.cipherKeySize = cipherKeySize;
    }

    public byte[] getDerivationV()
    {
        return derivation;
    }

    public byte[] getEncodingV()
    {
        return encoding;
    }

    public int getMacKeySize()
    {
        return macKeySize;
    }

    public int getCipherKeySize()
    {
        return cipherKeySize;
    }

    private byte derivation[];
    private byte encoding[];
    private int macKeySize;
    private int cipherKeySize;
}
