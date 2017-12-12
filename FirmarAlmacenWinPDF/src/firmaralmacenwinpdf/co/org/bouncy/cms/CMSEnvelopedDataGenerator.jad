// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedDataGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceCMSContentEncryptorBuilder;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.*;
import java.security.*;
import java.util.*;
import javax.crypto.KeyGenerator;

// Referenced classes of package co.org.bouncy.cms:
//            CMSEnvelopedGenerator, CMSException, RecipientInfoGenerator, CMSEnvelopedData, 
//            CMSTypedData, CMSAttributeTableGenerator, CMSUtils, CMSEnvelopedHelper, 
//            CMSProcessable

public class CMSEnvelopedDataGenerator extends CMSEnvelopedGenerator
{

    public CMSEnvelopedDataGenerator()
    {
    }

    /**
     * @deprecated Method CMSEnvelopedDataGenerator is deprecated
     */

    public CMSEnvelopedDataGenerator(SecureRandom rand)
    {
        super(rand);
    }

    private CMSEnvelopedData generate(final CMSProcessable content, String encryptionOID, int keySize, Provider encProvider, Provider provider)
        throws NoSuchAlgorithmException, CMSException
    {
        convertOldRecipients(rand, provider);
        JceCMSContentEncryptorBuilder builder;
        if(keySize != -1)
            builder = new JceCMSContentEncryptorBuilder(new ASN1ObjectIdentifier(encryptionOID), keySize);
        else
            builder = new JceCMSContentEncryptorBuilder(new ASN1ObjectIdentifier(encryptionOID));
        builder.setProvider(encProvider);
        builder.setSecureRandom(rand);
        return doGenerate(new CMSTypedData() {

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
            final CMSEnvelopedDataGenerator this$0;

            
            {
                this$0 = CMSEnvelopedDataGenerator.this;
                content = cmsprocessable;
                super();
            }
        }
, builder.build());
    }

    private CMSEnvelopedData doGenerate(CMSTypedData content, OutputEncryptor contentEncryptor)
        throws CMSException
    {
        if(!oldRecipientInfoGenerators.isEmpty())
            throw new IllegalStateException("can only use addRecipientGenerator() with this method");
        ASN1EncodableVector recipientInfos = new ASN1EncodableVector();
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        try
        {
            OutputStream cOut = contentEncryptor.getOutputStream(bOut);
            content.write(cOut);
            cOut.close();
        }
        catch(IOException e)
        {
            throw new CMSException("");
        }
        byte encryptedContent[] = bOut.toByteArray();
        AlgorithmIdentifier encAlgId = contentEncryptor.getAlgorithmIdentifier();
        ASN1OctetString encContent = new BEROctetString(encryptedContent);
        GenericKey encKey = contentEncryptor.getKey();
        RecipientInfoGenerator recipient;
        for(Iterator it = recipientInfoGenerators.iterator(); it.hasNext(); recipientInfos.add(recipient.generate(encKey)))
            recipient = (RecipientInfoGenerator)it.next();

        EncryptedContentInfo eci = new EncryptedContentInfo(content.getContentType(), encAlgId, encContent);
        ASN1Set unprotectedAttrSet = null;
        if(unprotectedAttributeGenerator != null)
        {
            AttributeTable attrTable = unprotectedAttributeGenerator.getAttributes(new HashMap());
            unprotectedAttrSet = new BERSet(attrTable.toASN1EncodableVector());
        }
        ContentInfo contentInfo = new ContentInfo(CMSObjectIdentifiers.envelopedData, new EnvelopedData(originatorInfo, new DERSet(recipientInfos), eci, unprotectedAttrSet));
        return new CMSEnvelopedData(contentInfo);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSEnvelopedData generate(CMSProcessable content, String encryptionOID, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return generate(content, encryptionOID, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSEnvelopedData generate(CMSProcessable content, String encryptionOID, Provider provider)
        throws NoSuchAlgorithmException, CMSException
    {
        KeyGenerator keyGen = CMSEnvelopedHelper.INSTANCE.createSymmetricKeyGenerator(encryptionOID, provider);
        return generate(content, encryptionOID, -1, keyGen.getProvider(), provider);
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSEnvelopedData generate(CMSProcessable content, String encryptionOID, int keySize, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        return generate(content, encryptionOID, keySize, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method generate is deprecated
     */

    public CMSEnvelopedData generate(CMSProcessable content, String encryptionOID, int keySize, Provider provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException
    {
        KeyGenerator keyGen = CMSEnvelopedHelper.INSTANCE.createSymmetricKeyGenerator(encryptionOID, provider);
        return generate(content, encryptionOID, keySize, keyGen.getProvider(), provider);
    }

    public CMSEnvelopedData generate(CMSTypedData content, OutputEncryptor contentEncryptor)
        throws CMSException
    {
        return doGenerate(content, contentEncryptor);
    }
}
