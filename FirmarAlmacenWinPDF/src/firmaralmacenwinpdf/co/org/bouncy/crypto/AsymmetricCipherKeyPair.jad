// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AsymmetricCipherKeyPair.java

package co.org.bouncy.crypto;

import co.org.bouncy.crypto.params.AsymmetricKeyParameter;

// Referenced classes of package co.org.bouncy.crypto:
//            CipherParameters

public class AsymmetricCipherKeyPair
{

    public AsymmetricCipherKeyPair(AsymmetricKeyParameter publicParam, AsymmetricKeyParameter privateParam)
    {
        this.publicParam = publicParam;
        this.privateParam = privateParam;
    }

    /**
     * @deprecated Method AsymmetricCipherKeyPair is deprecated
     */

    public AsymmetricCipherKeyPair(CipherParameters publicParam, CipherParameters privateParam)
    {
        this.publicParam = (AsymmetricKeyParameter)publicParam;
        this.privateParam = (AsymmetricKeyParameter)privateParam;
    }

    public AsymmetricKeyParameter getPublic()
    {
        return publicParam;
    }

    public AsymmetricKeyParameter getPrivate()
    {
        return privateParam;
    }

    private AsymmetricKeyParameter publicParam;
    private AsymmetricKeyParameter privateParam;
}
