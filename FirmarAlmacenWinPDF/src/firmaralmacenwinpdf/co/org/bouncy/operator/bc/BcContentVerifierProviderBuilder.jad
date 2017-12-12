// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcContentVerifierProviderBuilder.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.crypto.Signer;
import co.org.bouncy.crypto.params.AsymmetricKeyParameter;
import co.org.bouncy.operator.*;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcSignerOutputStream, BcDefaultDigestProvider, BcDigestProvider

public abstract class BcContentVerifierProviderBuilder
{
    private class SigVerifier
        implements ContentVerifier
    {

        public AlgorithmIdentifier getAlgorithmIdentifier()
        {
            return algorithm;
        }

        public OutputStream getOutputStream()
        {
            if(stream == null)
                throw new IllegalStateException("verifier not initialised");
            else
                return stream;
        }

        public boolean verify(byte expected[])
        {
            return stream.verify(expected);
        }

        private BcSignerOutputStream stream;
        private AlgorithmIdentifier algorithm;
        final BcContentVerifierProviderBuilder this$0;

        SigVerifier(AlgorithmIdentifier algorithm, BcSignerOutputStream stream)
        {
            this$0 = BcContentVerifierProviderBuilder.this;
            super();
            this.algorithm = algorithm;
            this.stream = stream;
        }
    }


    public BcContentVerifierProviderBuilder()
    {
        digestProvider = BcDefaultDigestProvider.INSTANCE;
    }

    public ContentVerifierProvider build(final X509CertificateHolder certHolder)
        throws OperatorCreationException
    {
        return new ContentVerifierProvider() {

            public boolean hasAssociatedCertificate()
            {
                return true;
            }

            public X509CertificateHolder getAssociatedCertificate()
            {
                return certHolder;
            }

            public ContentVerifier get(AlgorithmIdentifier algorithm)
                throws OperatorCreationException
            {
                try
                {
                    AsymmetricKeyParameter publicKey = extractKeyParameters(certHolder.getSubjectPublicKeyInfo());
                    BcSignerOutputStream stream = createSignatureStream(algorithm, publicKey);
                    return new SigVerifier(algorithm, stream);
                }
                catch(IOException e)
                {
                    throw new OperatorCreationException((new StringBuilder()).append("exception on setup: ").append(e).toString(), e);
                }
            }

            final X509CertificateHolder val$certHolder;
            final BcContentVerifierProviderBuilder this$0;

            
            {
                this$0 = BcContentVerifierProviderBuilder.this;
                certHolder = x509certificateholder;
                super();
            }
        }
;
    }

    public ContentVerifierProvider build(final AsymmetricKeyParameter publicKey)
        throws OperatorCreationException
    {
        return new ContentVerifierProvider() {

            public boolean hasAssociatedCertificate()
            {
                return false;
            }

            public X509CertificateHolder getAssociatedCertificate()
            {
                return null;
            }

            public ContentVerifier get(AlgorithmIdentifier algorithm)
                throws OperatorCreationException
            {
                BcSignerOutputStream stream = createSignatureStream(algorithm, publicKey);
                return new SigVerifier(algorithm, stream);
            }

            final AsymmetricKeyParameter val$publicKey;
            final BcContentVerifierProviderBuilder this$0;

            
            {
                this$0 = BcContentVerifierProviderBuilder.this;
                publicKey = asymmetrickeyparameter;
                super();
            }
        }
;
    }

    private BcSignerOutputStream createSignatureStream(AlgorithmIdentifier algorithm, AsymmetricKeyParameter publicKey)
        throws OperatorCreationException
    {
        Signer sig = createSigner(algorithm);
        sig.init(false, publicKey);
        return new BcSignerOutputStream(sig);
    }

    protected abstract AsymmetricKeyParameter extractKeyParameters(SubjectPublicKeyInfo subjectpublickeyinfo)
        throws IOException;

    protected abstract Signer createSigner(AlgorithmIdentifier algorithmidentifier)
        throws OperatorCreationException;

    protected BcDigestProvider digestProvider;

}
