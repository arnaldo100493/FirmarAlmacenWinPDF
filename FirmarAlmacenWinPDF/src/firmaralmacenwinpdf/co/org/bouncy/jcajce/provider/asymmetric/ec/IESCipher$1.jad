// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IESCipher.java

package co.org.bouncy.jcajce.provider.asymmetric.ec;

import co.org.bouncy.crypto.KeyEncoder;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ECPublicKeyParameters;
import co.org.bouncy.math.ec.ECPoint;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.ec:
//            IESCipher

class IESCipher$1
    implements KeyEncoder
{

    public byte[] getEncoded(AsymmetricKeyParameter keyParameter)
    {
        return ((ECPublicKeyParameters)keyParameter).getQ().getEncoded();
    }

    final IESCipher this$0;

    IESCipher$1()
    {
        this$0 = IESCipher.this;
        super();
    }
}
