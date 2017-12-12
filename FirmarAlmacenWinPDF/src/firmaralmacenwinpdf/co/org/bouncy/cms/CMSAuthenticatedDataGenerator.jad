// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAuthenticatedDataGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceCMSMacCalculatorBuilder;
import co.org.bouncy.operator.*;
import co.org.bouncy.util.io.TeeOutputStream;
import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.KeyGenerator;

// Referenced classes of package co.org.bouncy.cms:
//            CMSAuthenticatedGenerator, RecipientInfoGenerator, CMSException, DefaultAuthenticatedAttributeTableGenerator, 
//            CMSAuthenticatedData, CMSTypedData, CMSAttributeTableGenerator, CMSUtils, 
//            CMSEnvelopedHelper, CMSProcessable

public class CMSAuthenticatedDataGenerator extends CMSAuthenticatedGenerator
{

    public CMSAuthenticatedDataGenerator()
    {
    }

    public CMSAuthenticatedData generate(CMSTypedData typedData, MacCalculator macCalculator)
        throws CMSException
    {
        return generate(typedData, macCalculator, ((DigestCalculator) (null)));
    }

    public CMSAuthenticatedData generate(CMSTypedData typedData, MacCalculator macCalculator, final DigestCalculator digestCalculator)
        throws CMSException
    {
        ASN1EncodableVector recipientInfos = new ASN1EncodableVector();
        RecipientInfoGenerator recipient;
        for(Iterator it = recipientInfoGenerators.iterator(); it.hasNext(); recipientInfos.add(recipient.generate(macCalculator.getKey())))
            recipient = (RecipientInfoGenerator)it.next();

        AuthenticatedData authData;
        if(digestCalculator != null)
        {
            ASN1OctetString encContent;
            try
            {
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                OutputStream out = new TeeOutputStream(digestCalculator.getOutputStream(), bOut);
                typedData.write(out);
                out.close();
                encContent = new BEROctetString(bOut.toByteArray());
            }
            catch(IOException e)
            {
                throw new CMSException((new StringBuilder()).append("unable to perform digest calculation: ").append(e.getMessage()).toString(), e);
            }
            Map parameters = getBaseParameters(typedData.getContentType(), digestCalculator.getAlgorithmIdentifier(), digestCalculator.getDigest());
            if(authGen == null)
                authGen = new DefaultAuthenticatedAttributeTableGenerator();
            ASN1Set authed = new DERSet(authGen.getAttributes(Collections.unmodifiableMap(parameters)).toASN1EncodableVector());
            ASN1OctetString macResult;
            try
            {
                OutputStream mOut = macCalculator.getOutputStream();
                mOut.write(authed.getEncoded("DER"));
                mOut.close();
                macResult = new DEROctetString(macCalculator.getMac());
            }
            catch(IOException e)
            {
                throw new CMSException("exception decoding algorithm parameters.", e);
            }
            ASN1Set unauthed = unauthGen == null ? null : ((ASN1Set) (new BERSet(unauthGen.getAttributes(Collections.unmodifiableMap(parameters)).toASN1EncodableVector())));
            ContentInfo eci = new ContentInfo(CMSObjectIdentifiers.data, encContent);
            authData = new AuthenticatedData(originatorInfo, new DERSet(recipientInfos), macCalculator.getAlgorithmIdentifier(), digestCalculator.getAlgorithmIdentifier(), eci, authed, macResult, unauthed);
        } else
        {
            ASN1OctetString encContent;
            ASN1OctetString macResult;
            try
            {
                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                OutputStream mOut = new TeeOutputStream(bOut, macCalculator.getOutputStream());
                typedData.write(mOut);
                mOut.close();
                encContent = new BEROctetString(bOut.toByteArray());
                macResult = new DEROctetString(macCalculator.getMac());
            }
            catch(IOException e)
            {
                throw new CMSException("exception decoding algorithm parameters.", e);
            }
            ASN1Set unauthed = unauthGen == null ? null : ((ASN1Set) (new BERSet(unauthGen.getAttributes(new HashMap()).toASN1EncodableVector())));
            ContentInfo eci = new ContentInfo(CMSObjectIdentifiers.data, encContent);
            authData = new AuthenticatedData(originatorInfo, new DERSet(recipientInfos), macCalculator.getAlgorithmIdentifier(), null, eci, null, macResult, unauthed);
        }
        ContentInfo contentInfo = new ContentInfo(CMSObjectIdentifiers.authenticatedData, authData);
        return new CMSAuthenticatedData(contentInfo, new DigestCalculatorProvider() {

            public DigestCalculator get(AlgorithmIdentifier digestAlgorithmIdentifier)
                throws OperatorCreationException
            {
                return digestCalculator;
            }

            final DigestCalculator val$digestCalculator;
            final CMSAuthenticatedDataGenerator this$0;

            
            {
                this$0 = CMSAuthenticatedDataGenerator.this;
                digestCalculator = digestcalculator;
                super();
            }
        }
);
    }

    /**
     * @deprecated Method CMSAuthenticatedDataGenerator is deprecated
     */

    public CMSAuthenticatedDataGenerator(SecureRandom rand)
    {
        super(rand);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    private CMSAuthenticatedData generate(final CMSProcessable content, String macOID, KeyGenerator keyGen, Provider provider)
        throws NoSuchAlgorithmException, CMSException
    {
        Provider encProvider = keyGen.getProvider();
        convertOldRecipients(rand, provider);
        return generate(new CMSTypedData() {

            public ASN1ObjectIdentifier getContentType()
            {
                return CMSObjectIdentifiers.data;
            }

            public void write(OutputStream out)
                throws IOException, CMSException
            {
                content.write(out);
            }

            public Object getContent()
            {
                return content;
            }

            final CMSProcessable val$content;
            final CMSAuthenticatedDataGenerator this$0;

            
            {
                this$0 = CMSAuthenticatedDataGenerator.this;
                content = cmsprocessable;
                super();
            }
        }
, (new JceCMSMacCalculatorBuilder(new ASN1ObjectIdentifier(macOID))).setProvider(encProvider).setSecureRandom(rand).build());
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSAuthenticatedData generate(CMSProcessable content, String macOID, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return generate(content, macOID, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSAuthenticatedData generate(CMSProcessable content, String encryptionOID, Provider provider)
        throws NoSuchAlgorithmException, CMSException
    {
        KeyGenerator keyGen = CMSEnvelopedHelper.INSTANCE.createSymmetricKeyGenerator(encryptionOID, provider);
        return generate(content, encryptionOID, keyGen, provider);
    }
}
