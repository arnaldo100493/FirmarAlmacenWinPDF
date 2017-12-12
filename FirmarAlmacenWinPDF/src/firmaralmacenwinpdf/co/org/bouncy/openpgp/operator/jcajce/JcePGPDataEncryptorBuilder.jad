// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePGPDataEncryptorBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.operator.*;
import java.io.OutputStream;
import java.security.*;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper, SHA1PGPDigestCalculator, PGPUtil

public class JcePGPDataEncryptorBuilder
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

        private final Cipher c;
        final JcePGPDataEncryptorBuilder this$0;

        MyPGPDataEncryptor(byte keyBytes[])
            throws PGPException
        {
            this$0 = JcePGPDataEncryptorBuilder.this;
            super();
            c = helper.createStreamCipher(encAlgorithm, withIntegrityPacket);
            byte iv[] = new byte[c.getBlockSize()];
            try
            {
                c.init(1, PGPUtil.makeSymmetricKey(encAlgorithm, keyBytes), new IvParameterSpec(iv));
            }
            catch(InvalidKeyException e)
            {
                throw new PGPException((new StringBuilder()).append("invalid key: ").append(e.getMessage()).toString(), e);
            }
            catch(InvalidAlgorithmParameterException e)
            {
                throw new PGPException((new StringBuilder()).append("imvalid algorithm parameter: ").append(e.getMessage()).toString(), e);
            }
        }
    }


    public JcePGPDataEncryptorBuilder(int encAlgorithm)
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.encAlgorithm = encAlgorithm;
        if(encAlgorithm == 0)
            throw new IllegalArgumentException("null cipher specified");
        else
            return;
    }

    public JcePGPDataEncryptorBuilder setWithIntegrityPacket(boolean withIntegrityPacket)
    {
        this.withIntegrityPacket = withIntegrityPacket;
        return this;
    }

    public JcePGPDataEncryptorBuilder setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePGPDataEncryptorBuilder setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public JcePGPDataEncryptorBuilder setSecureRandom(SecureRandom random)
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

    private OperatorHelper helper;
    private SecureRandom random;
    private boolean withIntegrityPacket;
    private int encAlgorithm;



}
