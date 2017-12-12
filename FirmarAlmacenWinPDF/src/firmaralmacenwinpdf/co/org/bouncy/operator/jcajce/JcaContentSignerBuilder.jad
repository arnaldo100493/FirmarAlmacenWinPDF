// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaContentSignerBuilder.java

package co.org.bouncy.operator.jcajce;

import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.jcajce.*;
import co.org.bouncy.operator.*;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;

// Referenced classes of package co.org.bouncy.operator.jcajce:
//            OperatorHelper

public class JcaContentSignerBuilder
{
    private class SignatureOutputStream extends OutputStream
    {

        public void write(byte bytes[], int off, int len)
            throws IOException
        {
            try
            {
                sig.update(bytes, off, len);
            }
            catch(SignatureException e)
            {
                throw new OperatorStreamException((new StringBuilder()).append("exception in content signer: ").append(e.getMessage()).toString(), e);
            }
        }

        public void write(byte bytes[])
            throws IOException
        {
            try
            {
                sig.update(bytes);
            }
            catch(SignatureException e)
            {
                throw new OperatorStreamException((new StringBuilder()).append("exception in content signer: ").append(e.getMessage()).toString(), e);
            }
        }

        public void write(int b)
            throws IOException
        {
            try
            {
                sig.update((byte)b);
            }
            catch(SignatureException e)
            {
                throw new OperatorStreamException((new StringBuilder()).append("exception in content signer: ").append(e.getMessage()).toString(), e);
            }
        }

        byte[] getSignature()
            throws SignatureException
        {
            return sig.sign();
        }

        private Signature sig;
        final JcaContentSignerBuilder this$0;

        SignatureOutputStream(Signature sig)
        {
            this$0 = JcaContentSignerBuilder.this;
            super();
            this.sig = sig;
        }
    }


    public JcaContentSignerBuilder(String signatureAlgorithm)
    {
        helper = new OperatorHelper(new DefaultJcaJceHelper());
        this.signatureAlgorithm = signatureAlgorithm;
        sigAlgId = (new DefaultSignatureAlgorithmIdentifierFinder()).find(signatureAlgorithm);
    }

    public JcaContentSignerBuilder setProvider(Provider provider)
    {
        helper = new OperatorHelper(new ProviderJcaJceHelper(provider));
        return this;
    }

    public JcaContentSignerBuilder setProvider(String providerName)
    {
        helper = new OperatorHelper(new NamedJcaJceHelper(providerName));
        return this;
    }

    public JcaContentSignerBuilder setSecureRandom(SecureRandom random)
    {
        this.random = random;
        return this;
    }

    public ContentSigner build(PrivateKey privateKey)
        throws OperatorCreationException
    {
        try
        {
            final Signature sig = helper.createSignature(sigAlgId);
            if(random != null)
                sig.initSign(privateKey, random);
            else
                sig.initSign(privateKey);
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
                    catch(SignatureException e)
                    {
                        throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining signature: ").append(e.getMessage()).toString(), e);
                    }
                }

                private SignatureOutputStream stream;
                final Signature val$sig;
                final JcaContentSignerBuilder this$0;

            
            {
                this$0 = JcaContentSignerBuilder.this;
                sig = signature;
                super();
                stream = new SignatureOutputStream(sig);
            }
            }
;
        }
        catch(GeneralSecurityException e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("cannot create signer: ").append(e.getMessage()).toString(), e);
        }
    }

    private OperatorHelper helper;
    private SecureRandom random;
    private String signatureAlgorithm;
    private AlgorithmIdentifier sigAlgId;

}
