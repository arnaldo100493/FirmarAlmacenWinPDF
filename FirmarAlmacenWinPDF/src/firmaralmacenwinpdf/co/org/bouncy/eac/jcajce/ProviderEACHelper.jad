// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProviderEACHelper.java

package co.org.bouncy.eac.jcajce;

import java.security.*;

// Referenced classes of package co.org.bouncy.eac.jcajce:
//            EACHelper

class ProviderEACHelper
    implements EACHelper
{

    ProviderEACHelper(Provider provider)
    {
        this.provider = provider;
    }

    public KeyFactory createKeyFactory(String type)
        throws NoSuchAlgorithmException
    {
        return KeyFactory.getInstance(type, provider);
    }

    private final Provider provider;
}
