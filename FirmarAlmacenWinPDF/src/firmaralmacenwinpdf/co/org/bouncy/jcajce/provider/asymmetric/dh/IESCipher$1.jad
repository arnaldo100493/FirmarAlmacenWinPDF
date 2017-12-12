// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IESCipher.java

package co.org.bouncy.jcajce.provider.asymmetric.dh;

import co.org.bouncy.crypto.KeyEncoder;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.util.BigIntegers;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.jcajce.provider.asymmetric.dh:
//            IESCipher

class IESCipher$1
    implements KeyEncoder
{

    public byte[] getEncoded(AsymmetricKeyParameter keyParameter)
    {
        byte Vloc[] = new byte[(((DHKeyParameters)keyParameter).getParameters().getP().bitLength() + 7) / 8];
        byte Vtmp[] = BigIntegers.asUnsignedByteArray(((DHPublicKeyParameters)keyParameter).getY());
        if(Vtmp.length > Vloc.length)
        {
            throw new IllegalArgumentException("Senders's public key longer than expected.");
        } else
        {
            System.arraycopy(Vtmp, 0, Vloc, Vloc.length - Vtmp.length, Vtmp.length);
            return Vloc;
        }
    }

    final IESCipher this$0;

    IESCipher$1()
    {
        this$0 = IESCipher.this;
        super();
    }
}
