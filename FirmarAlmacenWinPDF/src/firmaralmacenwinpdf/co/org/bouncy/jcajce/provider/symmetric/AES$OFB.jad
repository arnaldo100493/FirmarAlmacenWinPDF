// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AES.java

package co.org.bouncy.jcajce.provider.symmetric;

import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.engines.AESFastEngine;
import co.org.bouncy.crypto.modes.OFBBlockCipher;
import co.org.bouncy.jcajce.provider.symmetric.util.BaseBlockCipher;

// Referenced classes of package co.org.bouncy.jcajce.provider.symmetric:
//            AES

public static class AES$OFB extends BaseBlockCipher
{

    public AES$OFB()
    {
        super(new BufferedBlockCipher(new OFBBlockCipher(new AESFastEngine(), 128)), 128);
    }
}
