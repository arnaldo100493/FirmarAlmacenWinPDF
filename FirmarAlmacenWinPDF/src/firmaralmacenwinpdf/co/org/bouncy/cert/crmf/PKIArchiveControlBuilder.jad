// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIArchiveControlBuilder.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.cms.EnvelopedData;
import co.org.bouncy.asn1.crmf.*;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.asn1.x509.GeneralName;
import co.org.bouncy.cms.*;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            PKIArchiveControl

public class PKIArchiveControlBuilder
{

    public PKIArchiveControlBuilder(PrivateKeyInfo privateKeyInfo, GeneralName generalName)
    {
        EncKeyWithID encKeyWithID = new EncKeyWithID(privateKeyInfo, generalName);
        try
        {
            keyContent = new CMSProcessableByteArray(CRMFObjectIdentifiers.id_ct_encKeyWithID, encKeyWithID.getEncoded());
        }
        catch(IOException e)
        {
            throw new IllegalStateException("unable to encode key and general name info");
        }
        envGen = new CMSEnvelopedDataGenerator();
    }

    public PKIArchiveControlBuilder addRecipientGenerator(RecipientInfoGenerator recipientGen)
    {
        envGen.addRecipientInfoGenerator(recipientGen);
        return this;
    }

    public PKIArchiveControl build(OutputEncryptor contentEncryptor)
        throws CMSException
    {
        CMSEnvelopedData envContent = envGen.generate(keyContent, contentEncryptor);
        EnvelopedData envD = EnvelopedData.getInstance(envContent.toASN1Structure().getContent());
        return new PKIArchiveControl(new PKIArchiveOptions(new EncryptedKey(envD)));
    }

    private CMSEnvelopedDataGenerator envGen;
    private CMSProcessableByteArray keyContent;
}
