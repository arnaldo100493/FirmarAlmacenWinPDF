// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEnvelopedDataStreamGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceCMSContentEncryptorBuilder;
import co.org.bouncy.operator.GenericKey;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.IOException;
import java.io.OutputStream;
import java.security.*;
import java.util.*;
import javax.crypto.KeyGenerator;

// Referenced classes of package co.org.bouncy.cms:
//            CMSEnvelopedGenerator, RecipientInfoGenerator, CMSException, CMSUtils, 
//            CMSEnvelopedHelper, CMSAttributeTableGenerator

public class CMSEnvelopedDataStreamGenerator extends CMSEnvelopedGenerator
{
    private class CmsEnvelopedDataOutputStream extends OutputStream
    {

        public void write(int b)
            throws IOException
        {
            _out.write(b);
        }

        public void write(byte bytes[], int off, int len)
            throws IOException
        {
            _out.write(bytes, off, len);
        }

        public void write(byte bytes[])
            throws IOException
        {
            _out.write(bytes);
        }

        public void close()
            throws IOException
        {
            _out.close();
            _eiGen.close();
            if(unprotectedAttributeGenerator != null)
            {
                AttributeTable attrTable = unprotectedAttributeGenerator.getAttributes(new HashMap());
                ASN1Set unprotectedAttrs = new BERSet(attrTable.toASN1EncodableVector());
                _envGen.addObject(new DERTaggedObject(false, 1, unprotectedAttrs));
            }
            _envGen.close();
            _cGen.close();
        }

        private OutputStream _out;
        private BERSequenceGenerator _cGen;
        private BERSequenceGenerator _envGen;
        private BERSequenceGenerator _eiGen;
        final CMSEnvelopedDataStreamGenerator this$0;

        public CmsEnvelopedDataOutputStream(OutputStream out, BERSequenceGenerator cGen, BERSequenceGenerator envGen, BERSequenceGenerator eiGen)
        {
            this$0 = CMSEnvelopedDataStreamGenerator.this;
            super();
            _out = out;
            _cGen = cGen;
            _envGen = envGen;
            _eiGen = eiGen;
        }
    }


    public CMSEnvelopedDataStreamGenerator()
    {
        _unprotectedAttributes = null;
    }

    /**
     * @deprecated Method CMSEnvelopedDataStreamGenerator is deprecated
     */

    public CMSEnvelopedDataStreamGenerator(SecureRandom rand)
    {
        super(rand);
        _unprotectedAttributes = null;
    }

    public void setBufferSize(int bufferSize)
    {
        _bufferSize = bufferSize;
    }

    public void setBEREncodeRecipients(boolean berEncodeRecipientSet)
    {
        _berEncodeRecipientSet = berEncodeRecipientSet;
    }

    private ASN1Integer getVersion()
    {
        if(originatorInfo != null || _unprotectedAttributes != null)
            return new ASN1Integer(2L);
        else
            return new ASN1Integer(0L);
    }

    /**
     * @deprecated Method open is deprecated
     */

    private OutputStream open(OutputStream out, String encryptionOID, int keySize, Provider encProvider, Provider provider)
        throws NoSuchAlgorithmException, CMSException, IOException
    {
        convertOldRecipients(rand, provider);
        JceCMSContentEncryptorBuilder builder;
        if(keySize != -1)
            builder = new JceCMSContentEncryptorBuilder(new ASN1ObjectIdentifier(encryptionOID), keySize);
        else
            builder = new JceCMSContentEncryptorBuilder(new ASN1ObjectIdentifier(encryptionOID));
        builder.setProvider(encProvider);
        builder.setSecureRandom(rand);
        return doOpen(CMSObjectIdentifiers.data, out, builder.build());
    }

    private OutputStream doOpen(ASN1ObjectIdentifier dataType, OutputStream out, OutputEncryptor encryptor)
        throws IOException, CMSException
    {
        ASN1EncodableVector recipientInfos = new ASN1EncodableVector();
        GenericKey encKey = encryptor.getKey();
        RecipientInfoGenerator recipient;
        for(Iterator it = recipientInfoGenerators.iterator(); it.hasNext(); recipientInfos.add(recipient.generate(encKey)))
            recipient = (RecipientInfoGenerator)it.next();

        return open(dataType, out, recipientInfos, encryptor);
    }

    protected OutputStream open(ASN1ObjectIdentifier dataType, OutputStream out, ASN1EncodableVector recipientInfos, OutputEncryptor encryptor)
        throws IOException
    {
        BERSequenceGenerator cGen = new BERSequenceGenerator(out);
        cGen.addObject(CMSObjectIdentifiers.envelopedData);
        BERSequenceGenerator envGen = new BERSequenceGenerator(cGen.getRawOutputStream(), 0, true);
        envGen.addObject(getVersion());
        if(originatorInfo != null)
            envGen.addObject(new DERTaggedObject(false, 0, originatorInfo));
        if(_berEncodeRecipientSet)
            envGen.getRawOutputStream().write((new BERSet(recipientInfos)).getEncoded());
        else
            envGen.getRawOutputStream().write((new DERSet(recipientInfos)).getEncoded());
        BERSequenceGenerator eiGen = new BERSequenceGenerator(envGen.getRawOutputStream());
        eiGen.addObject(dataType);
        AlgorithmIdentifier encAlgId = encryptor.getAlgorithmIdentifier();
        eiGen.getRawOutputStream().write(encAlgId.getEncoded());
        OutputStream octetStream = CMSUtils.createBEROctetOutputStream(eiGen.getRawOutputStream(), 0, false, _bufferSize);
        OutputStream cOut = encryptor.getOutputStream(octetStream);
        return new CmsEnvelopedDataOutputStream(cOut, cGen, envGen, eiGen);
    }

    protected OutputStream open(OutputStream out, ASN1EncodableVector recipientInfos, OutputEncryptor encryptor)
        throws CMSException
    {
        try
        {
            BERSequenceGenerator cGen = new BERSequenceGenerator(out);
            cGen.addObject(CMSObjectIdentifiers.envelopedData);
            BERSequenceGenerator envGen = new BERSequenceGenerator(cGen.getRawOutputStream(), 0, true);
            ASN1Set recipients;
            if(_berEncodeRecipientSet)
                recipients = new BERSet(recipientInfos);
            else
                recipients = new DERSet(recipientInfos);
            envGen.addObject(new ASN1Integer(EnvelopedData.calculateVersion(originatorInfo, recipients, _unprotectedAttributes)));
            if(originatorInfo != null)
                envGen.addObject(new DERTaggedObject(false, 0, originatorInfo));
            envGen.getRawOutputStream().write(recipients.getEncoded());
            BERSequenceGenerator eiGen = new BERSequenceGenerator(envGen.getRawOutputStream());
            eiGen.addObject(CMSObjectIdentifiers.data);
            AlgorithmIdentifier encAlgId = encryptor.getAlgorithmIdentifier();
            eiGen.getRawOutputStream().write(encAlgId.getEncoded());
            OutputStream octetStream = CMSUtils.createBEROctetOutputStream(eiGen.getRawOutputStream(), 0, false, _bufferSize);
            return new CmsEnvelopedDataOutputStream(encryptor.getOutputStream(octetStream), cGen, envGen, eiGen);
        }
        catch(IOException e)
        {
            throw new CMSException("exception decoding algorithm parameters.", e);
        }
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String encryptionOID, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException, IOException
    {
        return open(out, encryptionOID, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String encryptionOID, Provider provider)
        throws NoSuchAlgorithmException, CMSException, IOException
    {
        KeyGenerator keyGen = CMSEnvelopedHelper.INSTANCE.createSymmetricKeyGenerator(encryptionOID, provider);
        keyGen.init(rand);
        return open(out, encryptionOID, -1, keyGen.getProvider(), provider);
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String encryptionOID, int keySize, String provider)
        throws NoSuchAlgorithmException, NoSuchProviderException, CMSException, IOException
    {
        return open(out, encryptionOID, keySize, CMSUtils.getProvider(provider));
    }

    /**
     * @deprecated Method open is deprecated
     */

    public OutputStream open(OutputStream out, String encryptionOID, int keySize, Provider provider)
        throws NoSuchAlgorithmException, CMSException, IOException
    {
        KeyGenerator keyGen = CMSEnvelopedHelper.INSTANCE.createSymmetricKeyGenerator(encryptionOID, provider);
        keyGen.init(keySize, rand);
        return open(out, encryptionOID, -1, keyGen.getProvider(), provider);
    }

    public OutputStream open(OutputStream out, OutputEncryptor encryptor)
        throws CMSException, IOException
    {
        return doOpen(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), out, encryptor);
    }

    public OutputStream open(ASN1ObjectIdentifier dataType, OutputStream out, OutputEncryptor encryptor)
        throws CMSException, IOException
    {
        return doOpen(dataType, out, encryptor);
    }

    private ASN1Set _unprotectedAttributes;
    private int _bufferSize;
    private boolean _berEncodeRecipientSet;
}
