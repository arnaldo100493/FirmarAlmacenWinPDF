// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePEMDecryptorProviderBuilder.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openssl.*;
import java.security.Provider;

// Referenced classes of package co.org.bouncy.openssl.jcajce:
//            PEMUtilities

public class JcePEMDecryptorProviderBuilder
{

    public JcePEMDecryptorProviderBuilder()
    {
        helper = new DefaultJcaJceHelper();
    }

    public JcePEMDecryptorProviderBuilder setProvider(Provider provider)
    {
        helper = new ProviderJcaJceHelper(provider);
        return this;
    }

    public JcePEMDecryptorProviderBuilder setProvider(String providerName)
    {
        helper = new NamedJcaJceHelper(providerName);
        return this;
    }

    public PEMDecryptorProvider build(final char password[])
    {
        return new PEMDecryptorProvider() {

            public PEMDecryptor get(final String dekAlgName)
            {
                return new PEMDecryptor() {

                    public byte[] decrypt(byte keyBytes[], byte iv[])
                        throws PEMException
                    {
                        if(password == null)
                            throw new PasswordException("Password is null, but a password is required");
                        else
                            return PEMUtilities.crypt(false, helper, keyBytes, password, dekAlgName, iv);
                    }

                    final String val$dekAlgName;
                    final _cls1 this$1;

                    
                    {
                        this$1 = _cls1.this;
                        dekAlgName = s;
                        super();
                    }
                }
;
            }

            final char val$password[];
            final JcePEMDecryptorProviderBuilder this$0;

            
            {
                this$0 = JcePEMDecryptorProviderBuilder.this;
                password = ac;
                super();
            }
        }
;
    }

    private JcaJceHelper helper;

}
