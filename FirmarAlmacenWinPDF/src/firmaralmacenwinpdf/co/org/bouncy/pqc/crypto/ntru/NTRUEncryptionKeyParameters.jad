// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   NTRUEncryptionKeyParameters.java

package co.org.bouncy.pqc.crypto.ntru;

import co.org.bouncy.crypto.params.AsymmetricKeyParameter;

// Referenced classes of package co.org.bouncy.pqc.crypto.ntru:
//            NTRUEncryptionParameters

public class NTRUEncryptionKeyParameters extends AsymmetricKeyParameter
{

    public NTRUEncryptionKeyParameters(boolean privateKey, NTRUEncryptionParameters params)
    {
        super(privateKey);
        this.params = params;
    }

    public NTRUEncryptionParameters getParameters()
    {
        return params;
    }

    protected final NTRUEncryptionParameters params;
}
