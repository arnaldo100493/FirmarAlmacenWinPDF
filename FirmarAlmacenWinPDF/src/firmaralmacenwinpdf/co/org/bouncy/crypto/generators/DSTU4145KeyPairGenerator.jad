// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSTU4145KeyPairGenerator.java

package co.org.bouncy.crypto.generators;

import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.params.ECPrivateKeyParameters;
import co.org.bouncy.crypto.params.ECPublicKeyParameters;
import co.org.bouncy.math.ec.ECPoint;

// Referenced classes of package co.org.bouncy.crypto.generators:
//            ECKeyPairGenerator

public class DSTU4145KeyPairGenerator extends ECKeyPairGenerator
{

    public DSTU4145KeyPairGenerator()
    {
    }

    public AsymmetricCipherKeyPair generateKeyPair()
    {
        AsymmetricCipherKeyPair pair = super.generateKeyPair();
        ECPublicKeyParameters pub = (ECPublicKeyParameters)pair.getPublic();
        ECPrivateKeyParameters priv = (ECPrivateKeyParameters)pair.getPrivate();
        pub = new ECPublicKeyParameters(pub.getQ().negate(), pub.getParameters());
        return new AsymmetricCipherKeyPair(pub, priv);
    }
}
