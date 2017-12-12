// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAuthenticatedDataStreamGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceCMSMacCalculatorBuilder;
import co.org.bouncy.operator.DigestCalculator;
import co.org.bouncy.operator.MacCalculator;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSAuthenticatedGenerator, RecipientInfoGenerator, CMSException, CMSUtils, 
//            DefaultAuthenticatedAttributeTableGenerator, CMSAttributeTableGenerator

public class CMSAuthenticatedDataStreamGenerator extends CMSAuthenticatedGenerator
{
    private class CmsAuthenticatedDataOutputStream extends OutputStream
    {

        public void write(int b)
            throws IOException
        {
            dataStream.write(b);
        }

        public void write(byte bytes[], int off, int len)
            throws IOException
        {
            dataStream.write(bytes, off, len);
        }

        public void write(byte bytes[])
            throws IOException
        {
            dataStream.write(bytes);
        }

        public void close()
            throws IOException
        {
            dataStream.close();
            eiGen.close();
            Map parameters;
            if(digestCalculator != null)
            {
                parameters = Collections.unmodifiableMap(getBaseParameters(contentType, digestCalculator.getAlgorithmIdentifier(), digestCalculator.getDigest()));
                if(authGen == null)
                    authGen = new DefaultAuthenticatedAttributeTableGenerator();
                ASN1Set authed = new DERSet(authGen.getAttributes(parameters).toASN1EncodableVector());
                OutputStream mOut = macCalculator.getOutputStream();
                mOut.write(authed.getEncoded("DER"));
                mOut.close();
                envGen.addObject(new DERTaggedObject(false, 2, authed));
            } else
            {
                parameters = Collections.unmodifiableMap(new HashMap());
            }
            envGen.addObject(new DEROctetString(macCalculator.getMac()));
            if(unauthGen != null)
                envGen.addObject(new DERTaggedObject(false, 3, new BERSet(unauthGen.getAttributes(parameters).toASN1EncodableVector())));
            envGen.close();
            cGen.close();
        }

        private OutputStream dataStream;
        private BERSequenceGenerator cGen;
        private BERSequenceGenerator envGen;
        private BERSequenceGenerator eiGen;
        private MacCalculator macCalculator;
        private DigestCalculator digestCalculator;
        private ASN1ObjectIdentifier contentType;
        final CMSAuthenticatedDataStreamGenerator this$0;

        public CmsAuthenticatedDataOutputStream(MacCalculator macCalculator, DigestCalculator digestCalculator, ASN1ObjectIdentifier contentType, OutputStream dataStream, BERSequenceGenerator cGen, BERSequenceGenerator envGen, 
                BERSequenceGenerator eiGen)
        {
            this$0 = CMSAuthenticatedDataStreamGenerator.this;
            super();
            this.macCalculator = macCalculator;
            this.digestCalculator = digestCalculator;
            this.contentType = contentType;
            this.dataStream = dataStream;
            this.cGen = cGen;
            this.envGen = envGen;
            this.eiGen = eiGen;
        }
    }


    public CMSAuthenticatedDataStreamGenerator()
    {
    }

    public void setBufferSize(int bufferSize)
    {
        this.bufferSize = bufferSize;
    }

    public void setBEREncodeRecipients(boolean useBerEncodingForRecipients)
    {
        berEncodeRecipientSet = useBerEncodingForRecipients;
    }

    public OutputStream open(OutputStream out, MacCalculator macCalculator)
        throws CMSException
    {
        return open(CMSObjectIdentifiers.data, out, macCalculator);
    }

    public OutputStream open(OutputStream out, MacCalculator macCalculator, DigestCalculator digestCalculator)
        throws CMSException
    {
        return open(CMSObjectIdentifiers.data, out, macCalculator, digestCalculator);
    }

    public OutputStream open(ASN1ObjectIdentifier dataType, OutputStream out, MacCalculator macCalculator)
        throws CMSException
    {
        return open(dataType, out, macCalculator, ((DigestCalculator) (null)));
    }

    public OutputStream open(ASN1ObjectIdentifier dataType, OutputStream out, MacCalculator macCalculator, DigestCalculator digestCalculator)
        throws CMSException
    {
        this.macCalculator = macCalculator;
        try
        {
            ASN1EncodableVector recipientInfos = new ASN1EncodableVector();
            RecipientInfoGenerator recipient;
            for(Iterator it = recipientInfoGenerators.iterator(); it.hasNext(); recipientInfos.add(recipient.generate(macCalculator.getKey())))
                recipient = (RecipientInfoGenerator)it.next();

            BERSequenceGenerator cGen = new BERSequenceGenerator(out);
            cGen.addObject(CMSObjectIdentifiers.authenticatedData);
            BERSequenceGenerator authGen = new BERSequenceGenerator(cGen.getRawOutputStream(), 0, true);
            authGen.addObject(new DERInteger(AuthenticatedData.calculateVersion(originatorInfo)));
            if(originatorInfo != null)
                authGen.addObject(new DERTaggedObject(false, 0, originatorInfo));
            if(berEncodeRecipientSet)
                authGen.getRawOutputStream().write((new BERSet(recipientInfos)).getEncoded());
            else
                authGen.getRawOutputStream().write((new DERSet(recipientInfos)).getEncoded());
            AlgorithmIdentifier macAlgId = macCalculator.getAlgorithmIdentifier();
            authGen.getRawOutputStream().write(macAlgId.getEncoded());
            if(digestCalculator != null)
                authGen.addObject(new DERTaggedObject(false, 1, digestCalculator.getAlgorithmIdentifier()));
            BERSequenceGenerator eiGen = new BERSequenceGenerator(authGen.getRawOutputStream());
            eiGen.addObject(dataType);
            OutputStream octetStream = CMSUtils.createBEROctetOutputStream(eiGen.getRawOutputStream(), 0, false, bufferSize);
            OutputStream mOut;
            if(digestCalculator != null)
                mOut = new TeeOutputStream(octetStream, digestCalculator.getOutputStream());
            else
                mOut = new TeeOutputStream(octetStream, macCalculator.getOutputStream());
            return new CmsAuthenticatedDataOutputStream(macCalculator, digestCalculator, dataType, mOut, cGen, authGen, eiGen);
        }
        catch(IOException e)
        {
            throw new CMSException("exception decoding algorithm parameters.", e);
        }
    }

    /**
     * @deprecated Method CMSAuthenticatedDataStreamGenerator is deprecated
     */

    public CMSAuthenticatedDataStreamGenerator(SecureRandom rand)
    {
        super(rand);
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String encryptionOID, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException, IOException
    {
        convertOldRecipients(rand, CMSUtils.getProvider(provider));
        return open(out, (new JceCMSMacCalculatorBuilder(new ASN1ObjectIdentifier(encryptionOID))).setSecureRandom(rand).setProvider(provider).build());
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String encryptionOID, Provider provider)
        throws NoSuchAlgorithmException, CMSException, IOException
    {
        convertOldRecipients(rand, provider);
        return open(out, (new JceCMSMacCalculatorBuilder(new ASN1ObjectIdentifier(encryptionOID))).setSecureRandom(rand).setProvider(provider).build());
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String encryptionOID, int keySize, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException, IOException
    {
        convertOldRecipients(rand, CMSUtils.getProvider(provider));
        return open(out, (new JceCMSMacCalculatorBuilder(new ASN1ObjectIdentifier(encryptionOID), keySize)).setSecureRandom(rand).setProvider(provider).build());
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String encryptionOID, int keySize, Provider provider)
        throws NoSuchAlgorithmException, CMSException, IOException
    {
        convertOldRecipients(rand, provider);
        return open(out, (new JceCMSMacCalculatorBuilder(new ASN1ObjectIdentifier(encryptionOID), keySize)).setSecureRandom(rand).setProvider(provider).build());
    }

    private int bufferSize;
    private boolean berEncodeRecipientSet;
    private MacCalculator macCalculator;
}
