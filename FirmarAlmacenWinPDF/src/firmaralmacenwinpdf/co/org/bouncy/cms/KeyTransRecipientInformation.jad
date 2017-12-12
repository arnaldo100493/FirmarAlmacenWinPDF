// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyTransRecipientInformation.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1Integer;
import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cms.jcajce.JceKeyTransAuthenticatedRecipient;
import co.org.bouncy.cms.jcajce.JceKeyTransEnvelopedRecipient;
import co.org.bouncy.cms.jcajce.JceKeyTransRecipient;
import java.io.IOException;
import java.security.*;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientInformation, KeyTransRecipientId, CMSException, KeyTransRecipient, 
//            CMSSecureReadable, AuthAttributesProvider, CMSUtils, CMSEnvelopedHelper, 
//            CMSTypedStream, Recipient, RecipientOperator

public class KeyTransRecipientInformation extends RecipientInformation
{

    KeyTransRecipientInformation(KeyTransRecipientInfo info, AlgorithmIdentifier messageAlgorithm, CMSSecureReadable secureReadable, AuthAttributesProvider additionalData)
    {
        super(info.getKeyEncryptionAlgorithm(), messageAlgorithm, secureReadable, additionalData);
        this.info = info;
        RecipientIdentifier r = info.getRecipientIdentifier();
        if(r.isTagged())
        {
            ASN1OctetString octs = ASN1OctetString.getInstance(r.getId());
            rid = new KeyTransRecipientId(octs.getOctets());
        } else
        {
            IssuerAndSerialNumber iAnds = IssuerAndSerialNumber.getInstance(r.getId());
            rid = new KeyTransRecipientId(iAnds.getName(), iAnds.getSerialNumber().getValue());
        }
    }

    /**
     * @deprecated Method getContentStream is deprecated
     */

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
            JceKeyTransRecipient recipient;
            if(secureReadable instanceof CMSEnvelopedHelper.CMSEnvelopedSecureReadable)
                recipient = new JceKeyTransEnvelopedRecipient((PrivateKey)key);
            else
                recipient = new JceKeyTransAuthenticatedRecipient((PrivateKey)key);
            if(prov != null)
            {
                recipient.setProvider(prov);
                if(prov.getName().equalsIgnoreCase("SunJCE"))
                    recipient.setContentProvider((String)null);
            }
            return getContentStream(((Recipient) (recipient)));
        }
        catch(IOException e)
        {
            throw new CMSException((new StringBuilder()).append("encoding error: ").append(e.getMessage()).toString(), e);
        }
    }

    protected RecipientOperator getRecipientOperator(Recipient recipient)
        throws CMSException
    {
        return ((KeyTransRecipient)recipient).getRecipientOperator(keyEncAlg, messageAlgorithm, info.getEncryptedKey().getOctets());
    }

    private KeyTransRecipientInfo info;
}
