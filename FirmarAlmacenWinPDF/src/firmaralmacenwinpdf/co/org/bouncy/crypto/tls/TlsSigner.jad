// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsSigner.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Signer;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsContext

public interface TlsSigner
{

    public abstract void init(TlsContext tlscontext);

    public abstract byte[] generateRawSignature(AsymmetricKeyParameter asymmetrickeyparameter, byte abyte0[])
        throws CryptoException;

    public abstract boolean verifyRawSignature(byte abyte0[], AsymmetricKeyParameter asymmetrickeyparameter, byte abyte1[])
        throws CryptoException;

    public abstract Signer createSigner(AsymmetricKeyParameter asymmetrickeyparameter);

    public abstract Signer createVerifyer(AsymmetricKeyParameter asymmetrickeyparameter);

    public abstract boolean isValidPublicKey(AsymmetricKeyParameter asymmetrickeyparameter);
}
