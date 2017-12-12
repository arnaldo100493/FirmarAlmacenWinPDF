// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECPair.java

package co.org.bouncy.crypto.ec;

import co.org.bouncy.math.ec.ECPoint;

public class ECPair
{

    public ECPair(ECPoint x, ECPoint y)
    {
        this.x = x;
        this.y = y;
    }

    public ECPoint getX()
    {
        return x;
    }

    public ECPoint getY()
    {
        return y;
    }

    public byte[] getEncoded()
    {
        byte xEnc[] = x.getEncoded();
        byte yEnc[] = y.getEncoded();
        byte full[] = new byte[xEnc.length + yEnc.length];
        System.arraycopy(xEnc, 0, full, 0, xEnc.length);
        System.arraycopy(yEnc, 0, full, xEnc.length, yEnc.length);
        return full;
    }

    private final ECPoint x;
    private final ECPoint y;
}
