// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EphemeralKeyPairGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.*;

public class EphemeralKeyPairGenerator
{

    public EphemeralKeyPairGenerator(AsymmetricCipherKeyPairGenerator gen, KeyEncoder keyEncoder)
    {
        this.gen = gen;
        this.keyEncoder = keyEncoder;
    }

    public EphemeralKeyPair generate()
    {
        co.org.bouncy.crypto.AsymmetricCipherKeyPair eph = gen.generateKeyPair();
        return new EphemeralKeyPair(eph, keyEncoder);
    }

    private AsymmetricCipherKeyPairGenerator gen;
    private KeyEncoder keyEncoder;
}
