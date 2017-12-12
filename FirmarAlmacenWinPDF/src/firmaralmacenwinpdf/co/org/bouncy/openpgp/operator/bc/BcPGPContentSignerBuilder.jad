// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcPGPContentSignerBuilder.java

package co.org.bouncy.openpgp.operator.bc;

import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Signer;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.openpgp.PGPPrivateKey;
import co.org.bouncy.openpgp.operator.*;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.openpgp.operator.bc:
//            BcPGPDigestCalculatorProvider, BcPGPKeyConverter, BcImplProvider, SignerOutputStream

public class BcPGPContentSignerBuilder
    implements PGPContentSignerBuilder
{

    public BcPGPContentSignerBuilder(int keyAlgorithm, int hashAlgorithm)
    {
        digestCalculatorProvider = new BcPGPDigestCalculatorProvider();
        keyConverter = new BcPGPKeyConverter();
        this.keyAlgorithm = keyAlgorithm;
        this.hashAlgorithm = hashAlgorithm;
    }

    public BcPGPContentSignerBuilder setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public PGPContentSigner build(final int signatureType, final PGPPrivateKey privateKey)
        throws PGPException
    {
        final PGPDigestCalculator digestCalculator = digestCalculatorProvider.get(hashAlgorithm);
        final Signer signer = BcImplProvider.createSigner(keyAlgorithm, hashAlgorithm);
        if(random != null)
            signer.init(true, new ParametersWithRandom(keyConverter.getPrivateKey(privateKey), random));
        else
            signer.init(true, keyConverter.getPrivateKey(privateKey));
        return new PGPContentSigner() {

            public int getType()
            {
                return signatureType;
            }

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
                return privateKey.getKeyID();
            }

            public OutputStream getOutputStream()
            {
                return new TeeOutputStream(new SignerOutputStream(signer), digestCalculator.getOutputStream());
            }

            public byte[] getSignature()
            {
                try
                {
                    return signer.generateSignature();
                }
                catch(CryptoException e)
                {
                    throw new IllegalStateException("unable to create signature");
                }
            }

            public byte[] getDigest()
            {
                return digestCalculator.getDigest();
            }

            final int val$signatureType;
            final PGPPrivateKey val$privateKey;
            final Signer val$signer;
            final PGPDigestCalculator val$digestCalculator;
            final BcPGPContentSignerBuilder this$0;

            
            {
                this$0 = BcPGPContentSignerBuilder.this;
                signatureType = i;
                privateKey = pgpprivatekey;
                signer = signer1;
                digestCalculator = pgpdigestcalculator;
                super();
            }
        }
;
    }

    private BcPGPDigestCalculatorProvider digestCalculatorProvider;
    private BcPGPKeyConverter keyConverter;
    private int hashAlgorithm;
    private SecureRandom random;
    private int keyAlgorithm;


}
