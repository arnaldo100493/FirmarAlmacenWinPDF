// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MQVPrivateParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.CipherParameters;

// Referenced classes of package co.org.bouncy.crypto.params:
//            ECPrivateKeyParameters, ECPublicKeyParameters

public class MQVPrivateParameters
    implements CipherParameters
{

    public MQVPrivateParameters(ECPrivateKeyParameters staticPrivateKey, ECPrivateKeyParameters ephemeralPrivateKey)
    {
        this(staticPrivateKey, ephemeralPrivateKey, null);
    }

    public MQVPrivateParameters(ECPrivateKeyParameters staticPrivateKey, ECPrivateKeyParameters ephemeralPrivateKey, ECPublicKeyParameters ephemeralPublicKey)
    {
        this.staticPrivateKey = staticPrivateKey;
        this.ephemeralPrivateKey = ephemeralPrivateKey;
        this.ephemeralPublicKey = ephemeralPublicKey;
    }

    public ECPrivateKeyParameters getStaticPrivateKey()
    {
        return staticPrivateKey;
    }

    public ECPrivateKeyParameters getEphemeralPrivateKey()
    {
        return ephemeralPrivateKey;
    }

    public ECPublicKeyParameters getEphemeralPublicKey()
    {
        return ephemeralPublicKey;
    }

    private ECPrivateKeyParameters staticPrivateKey;
    private ECPrivateKeyParameters ephemeralPrivateKey;
    private ECPublicKeyParameters ephemeralPublicKey;
}
