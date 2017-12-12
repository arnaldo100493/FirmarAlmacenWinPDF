// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EphemeralKeyPair.java

package co.org.bouncy.crypto;


// Referenced classes of package co.org.bouncy.crypto:
//            AsymmetricCipherKeyPair, KeyEncoder

public class EphemeralKeyPair
{

    public EphemeralKeyPair(AsymmetricCipherKeyPair keyPair, KeyEncoder publicKeyEncoder)
    {
        this.keyPair = keyPair;
        this.publicKeyEncoder = publicKeyEncoder;
    }

    public AsymmetricCipherKeyPair getKeyPair()
    {
        return keyPair;
    }

    public byte[] getEncodedPublicKey()
    {
        return publicKeyEncoder.getEncoded(keyPair.getPublic());
    }

    private AsymmetricCipherKeyPair keyPair;
    private KeyEncoder publicKeyEncoder;
}
