// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSUtils.java

package co.org.bouncy.cms.bc;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.KeyParameter;
import co.org.bouncy.operator.GenericKey;

class CMSUtils
{

    CMSUtils()
    {
    }

    static CipherParameters getBcKey(GenericKey key)
    {
        if(key.getRepresentation() instanceof CipherParameters)
            return (CipherParameters)key.getRepresentation();
        if(key.getRepresentation() instanceof byte[])
            return new KeyParameter((byte[])(byte[])key.getRepresentation());
        else
            throw new IllegalArgumentException("unknown generic key type");
    }
}
