// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPContentVerifierBuilderProvider.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.Signer;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPublicKey;
import co.org.bouncy.openpgp.operator.PGPContentVerifier;
import co.org.bouncy.openpgp.operator.PGPContentVerifierBuilder;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcImplProvider, BcPGPContentVerifierBuilderProvider, BcPGPKeyConverter, SignerOutputStream

private class BcPGPContentVerifierBuilderProvider$BcPGPContentVerifierBuilder
    implements PGPContentVerifierBuilder
{

    public PGPContentVerifier build(final PGPPublicKey publicKey)
        throws PGPException
    {
        final Signer signer = BcImplProvider.createSigner(keyAlgorithm, hashAlgorithm);
        signer.init(false, BcPGPContentVerifierBuilderProvider.access$000(BcPGPContentVerifierBuilderProvider.this).getPublicKey(publicKey));
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
                return signer.verifySignature(expected);
            }

            public OutputStream getOutputStream()
            {
                return new SignerOutputStream(signer);
            }

            final PGPPublicKey val$publicKey;
            final Signer val$signer;
            final BcPGPContentVerifierBuilderProvider.BcPGPContentVerifierBuilder this$1;

            
            {
                this$1 = BcPGPContentVerifierBuilderProvider.BcPGPContentVerifierBuilder.this;
                publicKey = pgppublickey;
                signer = signer1;
                super();
            }
        }
;
    }

    private int hashAlgorithm;
    private int keyAlgorithm;
    final BcPGPContentVerifierBuilderProvider this$0;



    public BcPGPContentVerifierBuilderProvider$BcPGPContentVerifierBuilder(int keyAlgorithm, int hashAlgorithm)
    {
        this$0 = BcPGPContentVerifierBuilderProvider.this;
        super();
        this.keyAlgorithm = keyAlgorithm;
        this.hashAlgorithm = hashAlgorithm;
    }
}
