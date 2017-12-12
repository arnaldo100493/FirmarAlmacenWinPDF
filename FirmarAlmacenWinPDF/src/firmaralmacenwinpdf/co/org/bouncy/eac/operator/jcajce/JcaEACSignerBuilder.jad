// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   JcaEACSignerBuilder.java

package co.org.bouncy.eac.operator.jcajce;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.eac.EACObjectIdentifiers;
import co.org.bouncy.eac.operator.EACSigner;
import co.org.bouncy.operator.*;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.*;
import java.util.Arrays;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.eac.operator.jcajce:
//            DefaultEACHelper, NamedEACHelper, ProviderEACHelper, EACHelper

public class JcaEACSignerBuilder
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
        final JcaEACSignerBuilder this$0;

        SignatureOutputStream(Signature sig)
        {
            this$0 = JcaEACSignerBuilder.this;
            super();
            this.sig = sig;
        }
    }


    public JcaEACSignerBuilder()
    {
        helper = new DefaultEACHelper();
    }

    public JcaEACSignerBuilder setProvider(String providerName)
    {
        helper = new NamedEACHelper(providerName);
        return this;
    }

    public JcaEACSignerBuilder setProvider(Provider provider)
    {
        helper = new ProviderEACHelper(provider);
        return this;
    }

    public EACSigner build(String algorithm, PrivateKey privKey)
        throws OperatorCreationException
    {
        return build((ASN1ObjectIdentifier)sigNames.get(algorithm), privKey);
    }

    public EACSigner build(final ASN1ObjectIdentifier usageOid, PrivateKey privKey)
        throws OperatorCreationException
    {
        Signature sig;
        try
        {
            sig = helper.getSignature(usageOid);
            sig.initSign(privKey);
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
        return new EACSigner() {

            public ASN1ObjectIdentifier getUsageIdentifier()
            {
                return usageOid;
            }

            public OutputStream getOutputStream()
            {
                return sigStream;
            }

            public byte[] getSignature()
            {
                byte signature[];
                try
                {
                    signature = sigStream.getSignature();
                    if(usageOid.on(EACObjectIdentifiers.id_TA_ECDSA))
                        return JcaEACSignerBuilder.reencode(signature);
                }
                catch(SignatureException e)
                {
                    throw new RuntimeOperatorException((new StringBuilder()).append("exception obtaining signature: ").append(e.getMessage()).toString(), e);
                }
                return signature;
            }

            final ASN1ObjectIdentifier val$usageOid;
            final SignatureOutputStream val$sigStream;
            final JcaEACSignerBuilder this$0;

            
            {
                this$0 = JcaEACSignerBuilder.this;
                usageOid = asn1objectidentifier;
                sigStream = signatureoutputstream;
                super();
            }
        }
;
    }

    public static int max(int el1, int el2)
    {
        return el1 <= el2 ? el2 : el1;
    }

    private static byte[] reencode(byte rawSign[])
    {
        ASN1Sequence sData = ASN1Sequence.getInstance(rawSign);
        BigInteger r = ASN1Integer.getInstance(sData.getObjectAt(0)).getValue();
        BigInteger s = ASN1Integer.getInstance(sData.getObjectAt(1)).getValue();
        byte rB[] = r.toByteArray();
        byte sB[] = s.toByteArray();
        int rLen = unsignedIntLength(rB);
        int sLen = unsignedIntLength(sB);
        int len = max(rLen, sLen);
        byte ret[] = new byte[len * 2];
        Arrays.fill(ret, (byte)0);
        copyUnsignedInt(rB, ret, len - rLen);
        copyUnsignedInt(sB, ret, 2 * len - sLen);
        return ret;
    }

    private static int unsignedIntLength(byte i[])
    {
        int len = i.length;
        if(i[0] == 0)
            len--;
        return len;
    }

    private static void copyUnsignedInt(byte src[], byte dst[], int offset)
    {
        int len = src.length;
        int readoffset = 0;
        if(src[0] == 0)
        {
            len--;
            readoffset = 1;
        }
        System.arraycopy(src, readoffset, dst, offset, len);
    }

    private static final Hashtable sigNames;
    private EACHelper helper;

    static 
    {
        sigNames = new Hashtable();
        sigNames.put("SHA1withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_1);
        sigNames.put("SHA256withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_256);
        sigNames.put("SHA1withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_1);
        sigNames.put("SHA256withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_256);
        sigNames.put("SHA512withRSA", EACObjectIdentifiers.id_TA_RSA_v1_5_SHA_512);
        sigNames.put("SHA512withRSAandMGF1", EACObjectIdentifiers.id_TA_RSA_PSS_SHA_512);
        sigNames.put("SHA1withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_1);
        sigNames.put("SHA224withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_224);
        sigNames.put("SHA256withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_256);
        sigNames.put("SHA384withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_384);
        sigNames.put("SHA512withECDSA", EACObjectIdentifiers.id_TA_ECDSA_SHA_512);
    }

}
