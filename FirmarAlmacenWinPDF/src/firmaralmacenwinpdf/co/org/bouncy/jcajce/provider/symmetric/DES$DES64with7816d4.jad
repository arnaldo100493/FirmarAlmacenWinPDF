// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.engines.DESEngine;
import co.org.bouncy.crypto.macs.CBCBlockCipherMac;
import co.org.bouncy.crypto.paddings.ISO7816d4Padding;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DES

public static class DES$DES64with7816d4 extends BaseMac
{

    public DES$DES64with7816d4()
    {
        super(new CBCBlockCipherMac(new DESEngine(), 64, new ISO7816d4Padding()));
    }
}
