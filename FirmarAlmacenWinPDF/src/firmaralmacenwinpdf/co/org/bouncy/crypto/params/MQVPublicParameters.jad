// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MQVPublicParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.CipherParameters;

// Referenced classes of package co.org.bouncy.crypto.params:
//            ECPublicKeyParameters

public class MQVPublicParameters
    implements CipherParameters
{

    public MQVPublicParameters(ECPublicKeyParameters staticPublicKey, ECPublicKeyParameters ephemeralPublicKey)
    {
        this.staticPublicKey = staticPublicKey;
        this.ephemeralPublicKey = ephemeralPublicKey;
    }

    public ECPublicKeyParameters getStaticPublicKey()
    {
        return staticPublicKey;
    }

    public ECPublicKeyParameters getEphemeralPublicKey()
    {
        return ephemeralPublicKey;
    }

    private ECPublicKeyParameters staticPublicKey;
    private ECPublicKeyParameters ephemeralPublicKey;
}
