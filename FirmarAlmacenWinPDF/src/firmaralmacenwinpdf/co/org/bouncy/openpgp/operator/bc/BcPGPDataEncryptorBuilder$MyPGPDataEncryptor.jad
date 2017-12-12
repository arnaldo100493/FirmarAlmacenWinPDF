// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPDataEncryptorBuilder.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.io.CipherOutputStream;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.PGPDataEncryptor;
import co.org.bouncy.openpgp.operator.PGPDigestCalculator;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            SHA1PGPDigestCalculator, BcPGPDataEncryptorBuilder, BcImplProvider, BcUtil

private class BcPGPDataEncryptorBuilder$MyPGPDataEncryptor
    implements PGPDataEncryptor
{

    public OutputStream getOutputStream(OutputStream out)
    {
        return new CipherOutputStream(out, c);
    }

    public PGPDigestCalculator getIntegrityCalculator()
    {
        if(BcPGPDataEncryptorBuilder.access$100(BcPGPDataEncryptorBuilder.this))
            return new SHA1PGPDigestCalculator();
        else
            return null;
    }

    public int getBlockSize()
    {
        return c.getBlockSize();
    }

    private final BufferedBlockCipher c;
    final BcPGPDataEncryptorBuilder this$0;

    BcPGPDataEncryptorBuilder$MyPGPDataEncryptor(byte keyBytes[])
        throws PGPException
    {
        this$0 = BcPGPDataEncryptorBuilder.this;
        super();
        BlockCipher engine = BcImplProvider.createBlockCipher(BcPGPDataEncryptorBuilder.access$000(BcPGPDataEncryptorBuilder.this));
        try
        {
            c = BcUtil.createStreamCipher(true, engine, BcPGPDataEncryptorBuilder.access$100(BcPGPDataEncryptorBuilder.this), keyBytes);
        }
        catch(IllegalArgumentException e)
        {
            throw new PGPException((new StringBuilder()).append("invalid parameters: ").append(e.getMessage()).toString(), e);
        }
    }
}
