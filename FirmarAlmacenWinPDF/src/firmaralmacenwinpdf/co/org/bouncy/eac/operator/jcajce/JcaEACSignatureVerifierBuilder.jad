// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaEACSignatureVerifierBuilder.java

package co.org.bouncy.eac.operator.jcajce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.eac.EACObjectIdentifiers;
import co.org.bouncy.eac.operator.EACSignatureVerifier;
import co.org.bouncy.operator.*;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.*;

// Referenced classes of package co.org.bouncy.eac.operator.jcajce:
//            DefaultEACHelper, NamedEACHelper, ProviderEACHelper, EACHelper

public class JcaEACSignatureVerifierBuilder
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

        boolean verify(byte expected[])
            throws SignatureException
        {
            return sig.verify(expected);
        }

        private Signature sig;
        final JcaEACSignatureVerifierBuilder this$0;

        SignatureOutputStream(Signature sig)
        {
            this$0 = JcaEACSignatureVerifierBuilder.this;
            super();
            this.sig = sig;
        }
    }


    public JcaEACSignatureVerifierBuilder()
    {
        helper = new DefaultEACHelper();
    }

    public JcaEACSignatureVerifierBuilder setProvider(String providerName)
    {
        helper = new NamedEACHelper(providerName);
        return this;
    }

    public JcaEACSignatureVerifierBuilder setProvider(Provider provider)
    {
        helper = new ProviderEACHelper(provider);
        return this;
    }

    public EACSignatureVerifier build(final ASN1ObjectIdentifier usageOid, PublicKey pubKey)
        throws OperatorCreationException
    {
        Signature sig;
        try
        {
            sig = helper.getSignature(usageOid);
            sig.initVerify(pubKey);
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("unable to find algorithm: ").append(e.getMessage()).toString(), e);
        }
        catch(NoSuchProviderException e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("unable to find provider: ").append(e.getMessage()).toString(), e);
        }
        catch(InvalidKeyException e)
        {
            throw new OperatorCreationException((new StringBuilder()).append("invalid key: ").append(e.getMessage()).toString(), e);
        }
        final SignatureOutputStream sigStream = new SignatureOutputStream(sig);
        return new EACSignatureVerifier() {

            public ASN1ObjectIdentifier getUsageIdentifier()
            {
                return usageOid;
            }

            public OutputStream getOutputStream()
            {
                return sigStream;
            }

            public boolean verify(byte expected[])
            {
                if(usageOid.on(EACObjectIdentifiers.id_TA_ECDSA))
                    try
                    {
                        byte reencoded[] = JcaEACSignatureVerifierBuilder.derEncode(expected);
                        return sigStream.verify(reencoded);
                    }
                    catch(Exception e)
                    {
                        return false;
                    }
                try
                {
                    return sigStream.verify(expected);
                }
                catch(SignatureException e)
                {
                    throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining signature: ").append(e.getMessage()).toString(), e);
                }
            }

            final ASN1ObjectIdentifier val$usageOid;
            final SignatureOutputStream val$sigStream;
            final JcaEACSignatureVerifierBuilder this$0;

            
            {
                this$0 = JcaEACSignatureVerifierBuilder.this;
                usageOid = asn1objectidentifier;
                sigStream = signatureoutputstream;
                super();
            }
        }
;
    }

    private static byte[] derEncode(byte rawSign[])
        throws IOException
    {
        int len = rawSign.length / 2;
        byte r[] = new byte[len];
        byte s[] = new byte[len];
        System.arraycopy(rawSign, 0, r, 0, len);
        System.arraycopy(rawSign, len, s, 0, len);
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new DERInteger(new BigInteger(1, r)));
        v.add(new DERInteger(new BigInteger(1, s)));
        DERSequence seq = new DERSequence(v);
        return seq.getEncoded();
    }

    private EACHelper helper;

}
