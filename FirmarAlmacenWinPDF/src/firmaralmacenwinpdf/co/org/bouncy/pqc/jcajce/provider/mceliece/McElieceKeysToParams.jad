// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceKeysToParams.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.pqc.crypto.mceliece.McEliecePrivateKeyParameters;
import co.org.bouncy.pqc.crypto.mceliece.McEliecePublicKeyParameters;
import java.security.*;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            BCMcEliecePublicKey, BCMcEliecePrivateKey

public class McElieceKeysToParams
{

    public McElieceKeysToParams()
    {
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey key)
        throws InvalidKeyException
    {
        if(key instanceof BCMcEliecePublicKey)
        {
            BCMcEliecePublicKey k = (BCMcEliecePublicKey)key;
            return new McEliecePublicKeyParameters(k.getOIDString(), k.getN(), k.getT(), k.getG(), k.getMcElieceParameters());
        } else
        {
            throw new InvalidKeyException((new StringBuilder()).append("can't identify McEliece public key: ").append(key.getClass().getName()).toString());
        }
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey key)
        throws InvalidKeyException
    {
        if(key instanceof BCMcEliecePrivateKey)
        {
            BCMcEliecePrivateKey k = (BCMcEliecePrivateKey)key;
            return new McEliecePrivateKeyParameters(k.getOIDString(), k.getN(), k.getK(), k.getField(), k.getGoppaPoly(), k.getSInv(), k.getP1(), k.getP2(), k.getH(), k.getQInv(), k.getMcElieceParameters());
        } else
        {
            throw new InvalidKeyException("can't identify McEliece private key.");
        }
    }
}
