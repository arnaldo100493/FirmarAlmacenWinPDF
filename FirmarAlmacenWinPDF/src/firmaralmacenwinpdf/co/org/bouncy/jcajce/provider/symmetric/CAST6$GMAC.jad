// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CAST6.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.engines.CAST6Engine;
import co.org.bouncy.crypto.macs.GMac;
import co.org.bouncy.crypto.modes.GCMBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseMac;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            CAST6

public static class CAST6$GMAC extends BaseMac
{

    public CAST6$GMAC()
    {
        super(new GMac(new GCMBlockCipher(new CAST6Engine())));
    }
}
