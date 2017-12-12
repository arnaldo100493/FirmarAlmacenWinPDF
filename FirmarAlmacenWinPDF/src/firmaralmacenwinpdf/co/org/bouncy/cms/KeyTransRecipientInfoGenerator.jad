// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyTransRecipientInfoGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.operator.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSException, RecipientInfoGenerator

public abstract class KeyTransRecipientInfoGenerator
    implements RecipientInfoGenerator
{

    protected KeyTransRecipientInfoGenerator(IssuerAndSerialNumber issuerAndSerial, AsymmetricKeyWrapper wrapper)
    {
        this.issuerAndSerial = issuerAndSerial;
        this.wrapper = wrapper;
    }

    protected KeyTransRecipientInfoGenerator(byte subjectKeyIdentifier[], AsymmetricKeyWrapper wrapper)
    {
        this.subjectKeyIdentifier = subjectKeyIdentifier;
        this.wrapper = wrapper;
    }

    public final RecipientInfo generate(GenericKey contentEncryptionKey)
        throws CMSException
    {
        byte encryptedKeyBytes[];
        try
        {
            encryptedKeyBytes = wrapper.generateWrappedKey(contentEncryptionKey);
        }
        catch(OperatorException e)
        {
            throw new CMSException((new StringBuilder()).append("exception wrapping content key: ").append(e.getMessage()).toString(), e);
        }
        RecipientIdentifier recipId;
        if(issuerAndSerial != null)
            recipId = new RecipientIdentifier(issuerAndSerial);
        else
            recipId = new RecipientIdentifier(new DEROctetString(subjectKeyIdentifier));
        return new RecipientInfo(new KeyTransRecipientInfo(recipId, wrapper.getAlgorithmIdentifier(), new DEROctetString(encryptedKeyBytes)));
    }

    protected final AsymmetricKeyWrapper wrapper;
    private IssuerAndSerialNumber issuerAndSerial;
    private byte subjectKeyIdentifier[];
}
