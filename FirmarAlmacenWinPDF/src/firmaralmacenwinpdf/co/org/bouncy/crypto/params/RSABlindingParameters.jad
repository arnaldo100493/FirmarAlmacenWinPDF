// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RSABlindingParameters.java

package co.org.bouncy.crypto.params;

import co.org.bouncy.crypto.CipherParameters;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.crypto.params:
//            RSAPrivateCrtKeyParameters, RSAKeyParameters

public class RSABlindingParameters
    implements CipherParameters
{

    public RSABlindingParameters(RSAKeyParameters publicKey, BigInteger blindingFactor)
    {
        if(publicKey instanceof RSAPrivateCrtKeyParameters)
        {
            throw new IllegalArgumentException("RSA parameters should be for a public key");
        } else
        {
            this.publicKey = publicKey;
            this.blindingFactor = blindingFactor;
            return;
        }
    }

    public RSAKeyParameters getPublicKey()
    {
        return publicKey;
    }

    public BigInteger getBlindingFactor()
    {
        return blindingFactor;
    }

    private RSAKeyParameters publicKey;
    private BigInteger blindingFactor;
}
