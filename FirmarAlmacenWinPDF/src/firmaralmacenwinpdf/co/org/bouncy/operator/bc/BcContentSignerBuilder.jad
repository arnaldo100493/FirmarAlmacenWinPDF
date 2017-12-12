// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcContentSignerBuilder.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.CryptoException;
import co.org.bouncy.crypto.Signer;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.operator.*;
import java.io.OutputStream;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcDefaultDigestProvider, BcDigestProvider, BcSignerOutputStream

public abstract class BcContentSignerBuilder
{

    public BcContentSignerBuilder(AlgorithmIdentifier sigAlgId, AlgorithmIdentifier digAlgId)
    {
        this.sigAlgId = sigAlgId;
        this.digAlgId = digAlgId;
        digestProvider = BcDefaultDigestProvider.INSTANCE;
    }

    public BcContentSignerBuilder setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public ContentSigner build(AsymmetricKeyParameter privateKey)
        throws OperatorCreationException
    {
        final Signer sig = createSigner(sigAlgId, digAlgId);
        if(random != null)
            sig.init(true, new ParametersWithRandom(privateKey, random));
        else
            sig.init(true, privateKey);
        return new ContentSigner() {

            public AlgorithmIdentifier getAlgorithmIdentifier()
            {
                return sigAlgId;
            }

            public OutputStream getOutputStream()
            {
                return stream;
            }

            public byte[] getSignature()
            {
                try
                {
                    return stream.getSignature();
                }
                catch(CryptoException e)
                {
                    throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining signature: ").append(e.getMessage()).toString(), e);
                }
            }

            private BcSignerOutputStream stream;
            final Signer val$sig;
            final BcContentSignerBuilder this$0;

            
            {
                this$0 = BcContentSignerBuilder.this;
                sig = signer;
                super();
                stream = new BcSignerOutputStream(sig);
            }
        }
;
    }

    protected abstract Signer createSigner(AlgorithmIdentifier algorithmidentifier, AlgorithmIdentifier algorithmidentifier1)
        throws OperatorCreationException;

    private SecureRandom random;
    private AlgorithmIdentifier sigAlgId;
    private AlgorithmIdentifier digAlgId;
    protected BcDigestProvider digestProvider;

}
