// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DESedeParameters.java

package co.org.bouncy.crypto.params;


// Referenced classes of package co.org.bouncy.crypto.params:
//            DESParameters

public class DESedeParameters extends DESParameters
{

    public DESedeParameters(byte key[])
    {
        super(key);
        if(isWeakKey(key, 0, key.length))
            throw new IllegalArgumentException("attempt to create weak DESede key");
        else
            return;
    }

    public static boolean isWeakKey(byte key[], int offset, int length)
    {
        for(int i = offset; i < length; i += 8)
            if(DESParameters.isWeakKey(key, i))
                return true;

        return false;
    }

    public static boolean isWeakKey(byte key[], int offset)
    {
        return isWeakKey(key, offset, key.length - offset);
    }

    public static final int DES_EDE_KEY_LENGTH = 24;
}
