// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaPGPContentVerifierBuilderProvider.java

package co.org.bouncy.openpgp.operator.jcajce;

import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPublicKey;
import co.org.bouncy.openpgp.operator.PGPContentVerifier;
import co.org.bouncy.openpgp.operator.PGPContentVerifierBuilder;
import java.io.OutputStream;
import java.security.*;

// Referenced classes of package co.org.bouncy.openpgp.operator.jcajce:
//            JcaPGPContentVerifierBuilderProvider, OperatorHelper, JcaPGPKeyConverter, SignatureOutputStream

private class JcaPGPContentVerifierBuilderProvider$JcaPGPContentVerifierBuilder
    implements PGPContentVerifierBuilder
{

    public PGPContentVerifier build(final PGPPublicKey publicKey)
        throws PGPException
    {
        final Signature signature = JcaPGPContentVerifierBuilderProvider.access$000(JcaPGPContentVerifierBuilderProvider.this).createSignature(keyAlgorithm, hashAlgorithm);
        try
        {
            signature.initVerify(JcaPGPContentVerifierBuilderProvider.access$100(JcaPGPContentVerifierBuilderProvider.this).getPublicKey(publicKey));
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
            final JcaPGPContentVerifierBuilderProvider.JcaPGPContentVerifierBuilder this$1;

            
            {
                this$1 = JcaPGPContentVerifierBuilderProvider.JcaPGPContentVerifierBuilder.this;
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



    public JcaPGPContentVerifierBuilderProvider$JcaPGPContentVerifierBuilder(int keyAlgorithm, int hashAlgorithm)
    {
        this$0 = JcaPGPContentVerifierBuilderProvider.this;
        super();
        this.keyAlgorithm = keyAlgorithm;
        this.hashAlgorithm = hashAlgorithm;
    }
}
