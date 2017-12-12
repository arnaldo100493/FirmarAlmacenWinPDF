// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcePEMDecryptorProviderBuilder.java

package co.org.bouncy.openssl.jcajce;

import co.org.bouncy.openssl.*;

// Referenced classes of package co.org.bouncy.openssl.jcajce:
//            JcePEMDecryptorProviderBuilder, PEMUtilities

class JcePEMDecryptorProviderBuilder$1
    implements PEMDecryptorProvider
{

    public PEMDecryptor get(final String dekAlgName)
    {
        return new PEMDecryptor() {

            public byte[] decrypt(byte keyBytes[], byte iv[])
                throws PEMException
            {
                if(password == null)
                    throw new PasswordException("Password is null, but a password is required");
                else
                    return PEMUtilities.crypt(false, JcePEMDecryptorProviderBuilder.access$000(this$0), keyBytes, password, dekAlgName, iv);
            }

            final String val$dekAlgName;
            final JcePEMDecryptorProviderBuilder._cls1 this$1;

            
            {
                this$1 = JcePEMDecryptorProviderBuilder._cls1.this;
                dekAlgName = s;
                super();
            }
        }
;
    }

    final char val$password[];
    final JcePEMDecryptorProviderBuilder this$0;

    JcePEMDecryptorProviderBuilder$1()
    {
        this$0 = final_jcepemdecryptorproviderbuilder;
        val$password = _5B_C.this;
        super();
    }
}
