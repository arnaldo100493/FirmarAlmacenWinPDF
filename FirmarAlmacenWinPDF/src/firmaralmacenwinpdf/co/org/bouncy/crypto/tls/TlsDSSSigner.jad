// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsDSSSigner.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.DSA;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.DSAPublicKeyParameters;
import co.org.bouncy.crypto.signers.DSASigner;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsDSASigner

public class TlsDSSSigner extends TlsDSASigner
{

    public TlsDSSSigner()
    {
    }

    public boolean isValidPublicKey(AsymmetricKeyParameter publicKey)
    {
        return publicKey instanceof DSAPublicKeyParameters;
    }

    protected DSA createDSAImpl()
    {
        return new DSASigner();
    }
}
