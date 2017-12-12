// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePEMEncryptorBuilder.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openssl.PEMEncryptor;
import co.org.bouncy.openssl.PEMException;
import java.security.Provider;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.openssl.jcajce:
//            PEMUtilities

public class JcePEMEncryptorBuilder
{

    public JcePEMEncryptorBuilder(String algorithm)
    {
        helper = new DefaultJcaJceHelper();
        this.algorithm = algorithm;
    }

    public JcePEMEncryptorBuilder setProvider(Provider provider)
    {
        helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePEMEncryptorBuilder setProvider(String providerName)
    {
        helper = new NamedJcaJceHelper(providerName);
        return this;
    }

    public JcePEMEncryptorBuilder setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public PEMEncryptor build(final char password[])
    {
        if(random == null)
            random = new SecureRandom();
        int ivLength = algorithm.startsWith("AES-") ? 16 : 8;
        final byte iv[] = new byte[ivLength];
        random.nextBytes(iv);
        return new PEMEncryptor() {

            public String getAlgorithm()
            {
                return algorithm;
            }

            public byte[] getIV()
            {
                return iv;
            }

            public byte[] encrypt(byte encoding[])
                throws PEMException
            {
                return PEMUtilities.crypt(true, helper, encoding, password, algorithm, iv);
            }

            final byte val$iv[];
            final char val$password[];
            final JcePEMEncryptorBuilder this$0;

            
            {
                this$0 = JcePEMEncryptorBuilder.this;
                iv = abyte0;
                password = ac;
                super();
            }
        }
;
    }

    private final String algorithm;
    private JcaJceHelper helper;
    private SecureRandom random;


}
