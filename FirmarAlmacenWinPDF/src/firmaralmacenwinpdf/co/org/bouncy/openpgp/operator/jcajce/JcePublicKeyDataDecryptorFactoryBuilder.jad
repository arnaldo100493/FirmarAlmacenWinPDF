// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePublicKeyDataDecryptorFactoryBuilder.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.jce.interfaces.ElGamalKey;
import co.org.bouncy.jce.spec.ElGamalParameterSpec;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPrivateKey;
import co.org.bouncy.openpgp.operator.PGPDataDecryptor;
import co.org.bouncy.openpgp.operator.PublicKeyDataDecryptorFactory;
import java.math.BigInteger;
import java.security.*;
import javax.crypto.Cipher;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper, JcaPGPKeyConverter

public class JcePublicKeyDataDecryptorFactoryBuilder
{

    public JcePublicKeyDataDecryptorFactoryBuilder()
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        contentHelper = new OperatorHelper(new DefaultJcaJceHelper());
        keyConverter = new JcaPGPKeyConverter();
    }

    public JcePublicKeyDataDecryptorFactoryBuilder setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        keyConverter.setProvider(provider);
        contentHelper = helper;
        return this;
    }

    public JcePublicKeyDataDecryptorFactoryBuilder setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        keyConverter.setProvider(providerName);
        contentHelper = helper;
        return this;
    }

    public JcePublicKeyDataDecryptorFactoryBuilder setContentProvider(Provider provider)
    {
        contentHelper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcePublicKeyDataDecryptorFactoryBuilder setContentProvider(String providerName)
    {
        contentHelper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public PublicKeyDataDecryptorFactory build(final PrivateKey privKey)
    {
        return new PublicKeyDataDecryptorFactory() {

            public byte[] recoverSessionData(int keyAlgorithm, BigInteger secKeyData[])
                throws PGPException
            {
                return decryptSessionData(keyAlgorithm, privKey, secKeyData);
            }

            public PGPDataDecryptor createDataDecryptor(boolean withIntegrityPacket, int encAlgorithm, byte key[])
                throws PGPException
            {
                return contentHelper.createDataDecryptor(withIntegrityPacket, encAlgorithm, key);
            }

            final PrivateKey val$privKey;
            final JcePublicKeyDataDecryptorFactoryBuilder this$0;

            
            {
                this$0 = JcePublicKeyDataDecryptorFactoryBuilder.this;
                privKey = privatekey;
                super();
            }
        }
;
    }

    public PublicKeyDataDecryptorFactory build(final PGPPrivateKey privKey)
    {
        return new PublicKeyDataDecryptorFactory() {

            public byte[] recoverSessionData(int keyAlgorithm, BigInteger secKeyData[])
                throws PGPException
            {
                return decryptSessionData(keyAlgorithm, keyConverter.getPrivateKey(privKey), secKeyData);
            }

            public PGPDataDecryptor createDataDecryptor(boolean withIntegrityPacket, int encAlgorithm, byte key[])
                throws PGPException
            {
                return contentHelper.createDataDecryptor(withIntegrityPacket, encAlgorithm, key);
            }

            final PGPPrivateKey val$privKey;
            final JcePublicKeyDataDecryptorFactoryBuilder this$0;

            
            {
                this$0 = JcePublicKeyDataDecryptorFactoryBuilder.this;
                privKey = pgpprivatekey;
                super();
            }
        }
;
    }

    private byte[] decryptSessionData(int keyAlgorithm, PrivateKey privKey, BigInteger secKeyData[])
        throws PGPException
    {
        Cipher c1 = helper.createPublicKeyCipher(keyAlgorithm);
        try
        {
            c1.init(2, privKey);
        }
        catch(InvalidKeyException e)
        {
            throw new PGPException("error setting asymmetric cipher", e);
        }
        if(keyAlgorithm == 2 || keyAlgorithm == 1)
        {
            byte bi[] = secKeyData[0].toByteArray();
            if(bi[0] == 0)
                c1.update(bi, 1, bi.length - 1);
            else
                c1.update(bi);
        } else
        {
            ElGamalKey k = (ElGamalKey)privKey;
            int size = (k.getParameters().getP().bitLength() + 7) / 8;
            byte tmp[] = new byte[size];
            byte bi[] = secKeyData[0].toByteArray();
            if(bi.length > size)
            {
                c1.update(bi, 1, bi.length - 1);
            } else
            {
                System.arraycopy(bi, 0, tmp, tmp.length - bi.length, bi.length);
                c1.update(tmp);
            }
            bi = secKeyData[1].toByteArray();
            for(int i = 0; i != tmp.length; i++)
                tmp[i] = 0;

            if(bi.length > size)
            {
                c1.update(bi, 1, bi.length - 1);
            } else
            {
                System.arraycopy(bi, 0, tmp, tmp.length - bi.length, bi.length);
                c1.update(tmp);
            }
        }
        byte plain[];
        try
        {
            plain = c1.doFinal();
        }
        catch(Exception e)
        {
            throw new PGPException("exception decrypting session data", e);
        }
        return plain;
    }

    private OperatorHelper helper;
    private OperatorHelper contentHelper;
    private JcaPGPKeyConverter keyConverter;



}
