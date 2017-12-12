// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPContentVerifierBuilderProvider.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.jcajce.*;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPublicKey;
import co.org.bouncy.openpgp.operator.*;
import java.io.OutputStream;
import java.security.*;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            OperatorHelper, JcaPGPKeyConverter, SignatureOutputStream

public class JcaPGPContentVerifierBuilderProvider
    implements PGPContentVerifierBuilderProvider
{
    private class JcaPGPContentVerifierBuilder
        implements PGPContentVerifierBuilder
    {

        public PGPContentVerifier build(final PGPPublicKey publicKey)
            throws PGPException
        {
            final Signature signature = helper.createSignature(keyAlgorithm, hashAlgorithm);
            try
            {
                signature.initVerify(keyConverter.getPublicKey(publicKey));
            }
            catch(InvalidKeyException e)
            {
                throw new PGPException("invalid key.", e);
            }
            return new PGPContentVerifier() {

                public int getHashAlgorithm()
                {
                    return hashAlgorithm;
                }

                public int getKeyAlgorithm()
                {
                    return keyAlgorithm;
                }

                public long getKeyID()
                {
                    return publicKey.getKeyID();
                }

                public boolean verify(byte expected[])
                {
                    try
                    {
                        return signature.verify(expected);
                    }
                    catch(SignatureException e)
                    {
                        throw new IllegalStateException("unable to verify signature");
                    }
                }

                public OutputStream getOutputStream()
                {
                    return new SignatureOutputStream(signature);
                }

                final PGPPublicKey val$publicKey;
                final Signature val$signature;
                final JcaPGPContentVerifierBuilder this$1;

                
                {
                    this$1 = JcaPGPContentVerifierBuilder.this;
                    publicKey = pgppublickey;
                    signature = signature1;
                    super();
                }
            }
;
        }

        private int hashAlgorithm;
        private int keyAlgorithm;
        final JcaPGPContentVerifierBuilderProvider this$0;



        public JcaPGPContentVerifierBuilder(int keyAlgorithm, int hashAlgorithm)
        {
            this$0 = JcaPGPContentVerifierBuilderProvider.this;
            super();
            this.keyAlgorithm = keyAlgorithm;
            this.hashAlgorithm = hashAlgorithm;
        }
    }


    public JcaPGPContentVerifierBuilderProvider()
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        keyConverter = new JcaPGPKeyConverter();
    }

    public JcaPGPContentVerifierBuilderProvider setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        keyConverter.setProvider(provider);
        return this;
    }

    public JcaPGPContentVerifierBuilderProvider setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        keyConverter.setProvider(providerName);
        return this;
    }

    public PGPContentVerifierBuilder get(int keyAlgorithm, int hashAlgorithm)
        throws PGPException
    {
        return new JcaPGPContentVerifierBuilder(keyAlgorithm, hashAlgorithm);
    }

    private OperatorHelper helper;
    private JcaPGPKeyConverter keyConverter;


}
