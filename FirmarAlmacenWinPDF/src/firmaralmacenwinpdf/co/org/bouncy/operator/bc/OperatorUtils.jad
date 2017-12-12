// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OperatorUtils.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.operator.GenericKey;
import java.security.Key;

class OperatorUtils
{

    OperatorUtils()
    {
    }

    static byte[] getKeyBytes(GenericKey key)
    {
        if(key.getRepresentation() instanceof Key)
            return ((Key)key.getRepresentation()).getEncoded();
        if(key.getRepresentation() instanceof byte[])
            return (byte[])(byte[])key.getRepresentation();
        else
            throw new IllegalArgumentException("unknown generic key type");
    }
}
