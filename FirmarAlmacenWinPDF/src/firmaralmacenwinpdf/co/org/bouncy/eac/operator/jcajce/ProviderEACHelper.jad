// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProviderEACHelper.java

package co.org.bouncy.eac.operator.jcajce;

import java.security.*;

// Referenced classes of package co.org.bouncy.eac.operator.jcajce:
//            EACHelper

class ProviderEACHelper extends EACHelper
{

    ProviderEACHelper(Provider provider)
    {
        this.provider = provider;
    }

    protected Signature createSignature(String type)
        throws NoSuchAlgorithmException
    {
        return Signature.getInstance(type, provider);
    }

    private final Provider provider;
}
