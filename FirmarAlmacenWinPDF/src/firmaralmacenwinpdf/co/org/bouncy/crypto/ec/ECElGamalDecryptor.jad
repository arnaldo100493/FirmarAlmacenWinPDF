// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECElGamalDecryptor.java

package co.org.bouncy.crypto.ec;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.params.ECPrivateKeyParameters;
import co.org.bouncy.math.ec.ECPoint;

// Referenced classes of package co.org.bouncy.crypto.ec:
//            ECDecryptor, ECPair

public class ECElGamalDecryptor
    implements ECDecryptor
{

    public ECElGamalDecryptor()
    {
    }

    public void init(CipherParameters param)
    {
        if(!(param instanceof ECPrivateKeyParameters))
        {
            throw new IllegalArgumentException("ECPrivateKeyParameters are required for decryption.");
        } else
        {
            key = (ECPrivateKeyParameters)param;
            return;
        }
    }

    public ECPoint decrypt(ECPair pair)
    {
        if(key == null)
        {
            throw new IllegalStateException("ECElGamalDecryptor not initialised");
        } else
        {
            ECPoint tmp = pair.getX().multiply(key.getD());
            return pair.getY().add(tmp.negate());
        }
    }

    private ECPrivateKeyParameters key;
}
