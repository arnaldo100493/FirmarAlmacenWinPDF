// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NamedEACHelper.java

package co.org.bouncy.eac.operator.jcajce;

import java.security.*;

// Referenced classes of package co.org.bouncy.eac.operator.jcajce:
//            EACHelper

class NamedEACHelper extends EACHelper
{

    NamedEACHelper(String providerName)
    {
        this.providerName = providerName;
    }

    protected Signature createSignature(String type)
        throws NoSuchProviderException, NoSuchAlgorithmException
    {
        return Signature.getInstance(type, providerName);
    }

    private final String providerName;
}
