// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcAESSymmetricKeyUnwrapper.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.crypto.engines.AESWrapEngine;
import co.org.bouncy.crypto.params.KeyParameter;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcSymmetricKeyUnwrapper, AESUtil

public class BcAESSymmetricKeyUnwrapper extends BcSymmetricKeyUnwrapper
{

    public BcAESSymmetricKeyUnwrapper(KeyParameter wrappingKey)
    {
        super(AESUtil.determineKeyEncAlg(wrappingKey), new AESWrapEngine(), wrappingKey);
    }
}
