// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KDFParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.DerivationParameters;

public class KDFParameters
    implements DerivationParameters
{

    public KDFParameters(byte shared[], byte iv[])
    {
        this.shared = shared;
        this.iv = iv;
    }

    public byte[] getSharedSecret()
    {
        return shared;
    }

    public byte[] getIV()
    {
        return iv;
    }

    byte iv[];
    byte shared[];
}
