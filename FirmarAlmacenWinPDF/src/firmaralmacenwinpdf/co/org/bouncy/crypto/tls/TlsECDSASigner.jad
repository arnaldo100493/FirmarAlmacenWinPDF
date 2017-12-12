// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsECDSASigner.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.DSA;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ECPublicKeyParameters;
import co.org.bouncy.crypto.signers.ECDSASigner;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsDSASigner

public class TlsECDSASigner extends TlsDSASigner
{

    public TlsECDSASigner()
    {
    }

    public boolean isValidPublicKey(AsymmetricKeyParameter publicKey)
    {
        return publicKey instanceof ECPublicKeyParameters;
    }

    protected DSA createDSAImpl()
    {
        return new ECDSASigner();
    }
}
