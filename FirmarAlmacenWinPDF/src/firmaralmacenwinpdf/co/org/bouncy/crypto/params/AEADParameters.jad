// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AEADParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.CipherParameters;

// Referenced classes of package co.org.bouncy.crypto.params:
//            KeyParameter

public class AEADParameters
    implements CipherParameters
{

    public AEADParameters(KeyParameter key, int macSize, byte nonce[])
    {
        this(key, macSize, nonce, null);
    }

    public AEADParameters(KeyParameter key, int macSize, byte nonce[], byte associatedText[])
    {
        this.key = key;
        this.nonce = nonce;
        this.macSize = macSize;
        this.associatedText = associatedText;
    }

    public KeyParameter getKey()
    {
        return key;
    }

    public int getMacSize()
    {
        return macSize;
    }

    public byte[] getAssociatedText()
    {
        return associatedText;
    }

    public byte[] getNonce()
    {
        return nonce;
    }

    private byte associatedText[];
    private byte nonce[];
    private KeyParameter key;
    private int macSize;
}
