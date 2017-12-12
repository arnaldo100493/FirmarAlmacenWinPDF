// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   McEliecePointchevalDigestCipher.java

package co.org.bouncy.pqc.crypto.mceliece;

import co.org.bouncy.crypto.CipherParameters;
import co.org.bouncy.crypto.Digest;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.pqc.crypto.MessageEncryptor;

public class McEliecePointchevalDigestCipher
{

    public McEliecePointchevalDigestCipher(MessageEncryptor mcElieceCCA2Cipher, Digest messDigest)
    {
        this.mcElieceCCA2Cipher = mcElieceCCA2Cipher;
        this.messDigest = messDigest;
    }

    public void init(boolean forEncrypting, CipherParameters param)
    {
        this.forEncrypting = forEncrypting;
        AsymmetricKeyParameter k;
        if(param instanceof ParametersWithRandom)
            k = (AsymmetricKeyParameter)((ParametersWithRandom)param).getParameters();
        else
            k = (AsymmetricKeyParameter)param;
        if(forEncrypting && k.isPrivate())
            throw new IllegalArgumentException("Encrypting Requires Public Key.");
        if(!forEncrypting && !k.isPrivate())
        {
            throw new IllegalArgumentException("Decrypting Requires Private Key.");
        } else
        {
            reset();
            mcElieceCCA2Cipher.init(forEncrypting, param);
            return;
        }
    }

    public byte[] messageEncrypt()
    {
        if(!forEncrypting)
            throw new IllegalStateException("McEliecePointchevalDigestCipher not initialised for encrypting.");
        byte hash[] = new byte[messDigest.getDigestSize()];
        messDigest.doFinal(hash, 0);
        byte enc[] = null;
        try
        {
            enc = mcElieceCCA2Cipher.messageEncrypt(hash);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return enc;
    }

    public byte[] messageDecrypt(byte ciphertext[])
    {
        byte output[] = null;
        if(forEncrypting)
            throw new IllegalStateException("McEliecePointchevalDigestCipher not initialised for decrypting.");
        try
        {
            output = mcElieceCCA2Cipher.messageDecrypt(ciphertext);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }

    public void update(byte b)
    {
        messDigest.update(b);
    }

    public void update(byte in[], int off, int len)
    {
        messDigest.update(in, off, len);
    }

    public void reset()
    {
        messDigest.reset();
    }

    private final Digest messDigest;
    private final MessageEncryptor mcElieceCCA2Cipher;
    private boolean forEncrypting;
}
