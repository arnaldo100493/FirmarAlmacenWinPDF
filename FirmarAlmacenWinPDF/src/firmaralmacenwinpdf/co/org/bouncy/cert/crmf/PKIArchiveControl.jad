// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIArchiveControl.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.crmf.*;
import co.org.bouncy.cms.CMSEnvelopedData;
import co.org.bouncy.cms.CMSException;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFException, Control

public class PKIArchiveControl
    implements Control
{

    public PKIArchiveControl(PKIArchiveOptions pkiArchiveOptions)
    {
        this.pkiArchiveOptions = pkiArchiveOptions;
    }

    public ASN1ObjectIdentifier getType()
    {
        return type;
    }

    public ASN1Encodable getValue()
    {
        return pkiArchiveOptions;
    }

    public int getArchiveType()
    {
        return pkiArchiveOptions.getType();
    }

    public boolean isEnvelopedData()
    {
        EncryptedKey encKey = EncryptedKey.getInstance(pkiArchiveOptions.getValue());
        return !encKey.isEncryptedValue();
    }

    public CMSEnvelopedData getEnvelopedData()
        throws CRMFException
    {
        try
        {
            EncryptedKey encKey = EncryptedKey.getInstance(pkiArchiveOptions.getValue());
            EnvelopedData data = EnvelopedData.getInstance(encKey.getValue());
            return new CMSEnvelopedData(new ContentInfo(CMSObjectIdentifiers.envelopedData, data));
        }
        catch(CMSException e)
        {
            throw new CRMFException((new StringBuilder()).append("CMS parsing error: ").append(e.getMessage()).toString(), e.getCause());
        }
        catch(Exception e)
        {
            throw new CRMFException((new StringBuilder()).append("CRMF parsing error: ").append(e.getMessage()).toString(), e);
        }
    }

    public static final int encryptedPrivKey = 0;
    public static final int keyGenParameters = 1;
    public static final int archiveRemGenPrivKey = 2;
    private static final ASN1ObjectIdentifier type;
    private final PKIArchiveOptions pkiArchiveOptions;

    static 
    {
        type = CRMFObjectIdentifiers.id_regCtrl_pkiArchiveOptions;
    }
}
