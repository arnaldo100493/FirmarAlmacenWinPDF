// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESede.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.engines.DESedeEngine;
import co.org.bouncy.crypto.macs.CBCBlockCipherMac;
import co.org.bouncy.crypto.paddings.ISO7816d4Padding;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            DESede

public static class DESede$DESede64with7816d4 extends BaseMac
{

    public DESede$DESede64with7816d4()
    {
        super(new CBCBlockCipherMac(new DESedeEngine(), 64, new ISO7816d4Padding()));
    }
}
