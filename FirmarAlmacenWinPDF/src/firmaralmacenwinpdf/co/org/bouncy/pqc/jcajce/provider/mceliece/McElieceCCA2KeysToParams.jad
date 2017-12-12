// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McElieceCCA2KeysToParams.java

package co.org.bouncy.pqc.jcajce.provider.mceliece;

import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.pqc.crypto.mceliece.McElieceCCA2PrivateKeyParameters;
import co.org.bouncy.pqc.crypto.mceliece.McElieceCCA2PublicKeyParameters;
import java.security.*;

// Referenced classes of package co.org.bouncy.pqc.jcajce.provider.mceliece:
//            BCMcElieceCCA2PublicKey, BCMcElieceCCA2PrivateKey

public class McElieceCCA2KeysToParams
{

    public McElieceCCA2KeysToParams()
    {
    }

    public static AsymmetricKeyParameter generatePublicKeyParameter(PublicKey key)
        throws InvalidKeyException
    {
        if(key instanceof BCMcElieceCCA2PublicKey)
        {
            BCMcElieceCCA2PublicKey k = (BCMcElieceCCA2PublicKey)key;
            return new McElieceCCA2PublicKeyParameters(k.getOIDString(), k.getN(), k.getT(), k.getG(), k.getMcElieceCCA2Parameters());
        } else
        {
            throw new InvalidKeyException((new StringBuilder()).append("can't identify McElieceCCA2 public key: ").append(key.getClass().getName()).toString());
        }
    }

    public static AsymmetricKeyParameter generatePrivateKeyParameter(PrivateKey key)
        throws InvalidKeyException
    {
        if(key instanceof BCMcElieceCCA2PrivateKey)
        {
            BCMcElieceCCA2PrivateKey k = (BCMcElieceCCA2PrivateKey)key;
            return new McElieceCCA2PrivateKeyParameters(k.getOIDString(), k.getN(), k.getK(), k.getField(), k.getGoppaPoly(), k.getP(), k.getH(), k.getQInv(), k.getMcElieceCCA2Parameters());
        } else
        {
            throw new InvalidKeyException("can't identify McElieceCCA2 private key.");
        }
    }
}
