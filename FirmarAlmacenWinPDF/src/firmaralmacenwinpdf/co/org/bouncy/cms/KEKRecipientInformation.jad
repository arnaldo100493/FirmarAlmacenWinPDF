// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KEKRecipientInformation.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.cms.KEKIdentifier;
import co.org.bouncy.asn1.cms.KEKRecipientInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceKEKAuthenticatedRecipient;
import co.org.bouncy.cms.jcajce.JceKEKEnvelopedRecipient;
import co.org.bouncy.cms.jcajce.JceKEKRecipient;
import java.io.IOException;
import java.security.*;
import javax.crypto.SecretKey;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientInformation, KEKRecipientId, CMSException, KEKRecipient, 
//            CMSUtils, CMSEnvelopedHelper, CMSSecureReadable, AuthAttributesProvider, 
//            CMSTypedStream, Recipient, RecipientOperator

public class KEKRecipientInformation extends RecipientInformation
{

    KEKRecipientInformation(KEKRecipientInfo info, AlgorithmIdentifier messageAlgorithm, CMSSecureReadable secureReadable, AuthAttributesProvider additionalData)
    {
        super(info.getKeyEncryptionAlgorithm(), messageAlgorithm, secureReadable, additionalData);
        this.info = info;
        KEKIdentifier kekId = info.getKekid();
        rid = new KEKRecipientId(kekId.getKeyIdentifier().getOctets());
    }

    public CMSTypedStream getContentStream(Key key, String prov)
        throws CMSException, NoSuchProviderException
    {
        return getContentStream(key, CMSUtils.getProvider(prov));
    }

    /**
     * @deprecated Method getContentStream is deprecated
     */

    public CMSTypedStream getContentStream(Key key, Provider prov)
        throws CMSException
    {
        try
        {
            JceKEKRecipient recipient;
            if(secureReadable instanceof CMSEnvelopedHelper.CMSEnvelopedSecureReadable)
                recipient = new JceKEKEnvelopedRecipient((SecretKey)key);
            else
                recipient = new JceKEKAuthenticatedRecipient((SecretKey)key);
            if(prov != null)
                recipient.setProvider(prov);
            return getContentStream(((Recipient) (recipient)));
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("encoding error: ").append(e.getMessage()).toString(), e);
        }
    }

    protected RecipientOperator getRecipientOperator(Recipient recipient)
        throws CMSException, IOException
    {
        return ((KEKRecipient)recipient).getRecipientOperator(keyEncAlg, messageAlgorithm, info.getEncryptedKey().getOctets());
    }

    private KEKRecipientInfo info;
}
