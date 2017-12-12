// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyAgreeRecipientInformation.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cms.jcajce.JceKeyAgreeAuthenticatedRecipient;
import co.org.bouncy.cms.jcajce.JceKeyAgreeEnvelopedRecipient;
import co.org.bouncy.cms.jcajce.JceKeyAgreeRecipient;
import java.io.IOException;
import java.security.*;
import java.util.List;

// Referenced classes of package co.org.bouncy.cms:
//            RecipientInformation, KeyAgreeRecipientId, OriginatorId, CMSException, 
//            KeyAgreeRecipient, CMSSecureReadable, AuthAttributesProvider, RecipientId, 
//            CMSUtils, CMSEnvelopedHelper, CMSTypedStream, Recipient, 
//            RecipientOperator

public class KeyAgreeRecipientInformation extends RecipientInformation
{

    static void readRecipientInfo(List infos, KeyAgreeRecipientInfo info, AlgorithmIdentifier messageAlgorithm, CMSSecureReadable secureReadable, AuthAttributesProvider additionalData)
    {
        ASN1Sequence s = info.getRecipientEncryptedKeys();
        for(int i = 0; i < s.size(); i++)
        {
            RecipientEncryptedKey id = RecipientEncryptedKey.getInstance(s.getObjectAt(i));
            KeyAgreeRecipientIdentifier karid = id.getIdentifier();
            IssuerAndSerialNumber iAndSN = karid.getIssuerAndSerialNumber();
            RecipientId rid;
            if(iAndSN != null)
            {
                rid = new KeyAgreeRecipientId(iAndSN.getName(), iAndSN.getSerialNumber().getValue());
            } else
            {
                RecipientKeyIdentifier rKeyID = karid.getRKeyID();
                rid = new KeyAgreeRecipientId(rKeyID.getSubjectKeyIdentifier().getOctets());
            }
            infos.add(new KeyAgreeRecipientInformation(info, rid, id.getEncryptedKey(), messageAlgorithm, secureReadable, additionalData));
        }

    }

    KeyAgreeRecipientInformation(KeyAgreeRecipientInfo info, RecipientId rid, ASN1OctetString encryptedKey, AlgorithmIdentifier messageAlgorithm, CMSSecureReadable secureReadable, AuthAttributesProvider additionalData)
    {
        super(info.getKeyEncryptionAlgorithm(), messageAlgorithm, secureReadable, additionalData);
        this.info = info;
        this.rid = rid;
        this.encryptedKey = encryptedKey;
    }

    private SubjectPublicKeyInfo getSenderPublicKeyInfo(AlgorithmIdentifier recKeyAlgId, OriginatorIdentifierOrKey originator)
        throws CMSException, IOException
    {
        OriginatorPublicKey opk = originator.getOriginatorKey();
        if(opk != null)
            return getPublicKeyInfoFromOriginatorPublicKey(recKeyAlgId, opk);
        IssuerAndSerialNumber iAndSN = originator.getIssuerAndSerialNumber();
        OriginatorId origID;
        if(iAndSN != null)
        {
            origID = new OriginatorId(iAndSN.getName(), iAndSN.getSerialNumber().getValue());
        } else
        {
            SubjectKeyIdentifier ski = originator.getSubjectKeyIdentifier();
            origID = new OriginatorId(ski.getKeyIdentifier());
        }
        return getPublicKeyInfoFromOriginatorId(origID);
    }

    private SubjectPublicKeyInfo getPublicKeyInfoFromOriginatorPublicKey(AlgorithmIdentifier recKeyAlgId, OriginatorPublicKey originatorPublicKey)
    {
        SubjectPublicKeyInfo pubInfo = new SubjectPublicKeyInfo(recKeyAlgId, originatorPublicKey.getPublicKey().getBytes());
        return pubInfo;
    }

    private SubjectPublicKeyInfo getPublicKeyInfoFromOriginatorId(OriginatorId origID)
        throws CMSException
    {
        throw new CMSException("No support for 'originator' as IssuerAndSerialNumber or SubjectKeyIdentifier");
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
            JceKeyAgreeRecipient recipient;
            if(secureReadable instanceof CMSEnvelopedHelper.CMSEnvelopedSecureReadable)
                recipient = new JceKeyAgreeEnvelopedRecipient((PrivateKey)key);
            else
                recipient = new JceKeyAgreeAuthenticatedRecipient((PrivateKey)key);
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
        throws CMSException, IOException
    {
        KeyAgreeRecipient agreeRecipient = (KeyAgreeRecipient)recipient;
        AlgorithmIdentifier recKeyAlgId = agreeRecipient.getPrivateKeyAlgorithmIdentifier();
        return ((KeyAgreeRecipient)recipient).getRecipientOperator(keyEncAlg, messageAlgorithm, getSenderPublicKeyInfo(recKeyAlgId, info.getOriginator()), info.getUserKeyingMaterial(), encryptedKey.getOctets());
    }

    private KeyAgreeRecipientInfo info;
    private ASN1OctetString encryptedKey;
}
