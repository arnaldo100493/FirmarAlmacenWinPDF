// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPDataEncryptorBuilder.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.BlockCipher;
import co.org.bouncy.crypto.BufferedBlockCipher;
import co.org.bouncy.crypto.io.CipherOutputStream;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.*;
import java.io.OutputStream;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            SHA1PGPDigestCalculator, BcImplProvider, BcUtil

public class BcPGPDataEncryptorBuilder
    implements PGPDataEncryptorBuilder
{
    private class MyPGPDataEncryptor
        implements PGPDataEncryptor
    {

        public OutputStream getOutputStream(OutputStream out)
        {
            return new CipherOutputStream(out, c);
        }

        public PGPDigestCalculator getIntegrityCalculator()
        {
            if(withIntegrityPacket)
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

        MyPGPDataEncryptor(byte keyBytes[])
            throws PGPException
        {
            this$0 = BcPGPDataEncryptorBuilder.this;
            super();
            BlockCipher engine = BcImplProvider.createBlockCipher(encAlgorithm);
            try
            {
                c = BcUtil.createStreamCipher(true, engine, withIntegrityPacket, keyBytes);
            }
            catch(IllegalArgumentException e)
            {
                throw new PGPException((new StringBuilder()).append("invalid parameters: ").append(e.getMessage()).toString(), e);
            }
        }
    }


    public BcPGPDataEncryptorBuilder(int encAlgorithm)
    {
        this.encAlgorithm = encAlgorithm;
        if(encAlgorithm == 0)
            throw new IllegalArgumentException("null cipher specified");
        else
            return;
    }

    public BcPGPDataEncryptorBuilder setWithIntegrityPacket(boolean withIntegrityPacket)
    {
        this.withIntegrityPacket = withIntegrityPacket;
        return this;
    }

    public BcPGPDataEncryptorBuilder setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public int getAlgorithm()
    {
        return encAlgorithm;
    }

    public SecureRandom getSecureRandom()
    {
        if(random == null)
            random = new SecureRandom();
        return random;
    }

    public PGPDataEncryptor build(byte keyBytes[])
        throws PGPException
    {
        return new MyPGPDataEncryptor(keyBytes);
    }

    private SecureRandom random;
    private boolean withIntegrityPacket;
    private int encAlgorithm;


}
