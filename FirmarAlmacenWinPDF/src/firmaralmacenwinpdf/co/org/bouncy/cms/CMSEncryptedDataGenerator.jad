// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSEncryptedDataGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.*;
import java.util.HashMap;

// Referenced classes of package co.org.bouncy.cms:
//            CMSEncryptedGenerator, CMSException, CMSEncryptedData, CMSTypedData, 
//            CMSAttributeTableGenerator

public class CMSEncryptedDataGenerator extends CMSEncryptedGenerator
{

    public CMSEncryptedDataGenerator()
    {
    }

    private CMSEncryptedData doGenerate(CMSTypedData content, OutputEncryptor contentEncryptor)
        throws CMSException
    {
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
        EncryptedContentInfo eci = new EncryptedContentInfo(content.getContentType(), encAlgId, encContent);
        ASN1Set unprotectedAttrSet = null;
        if(unprotectedAttributeGenerator != null)
        {
            AttributeTable attrTable = unprotectedAttributeGenerator.getAttributes(new HashMap());
            unprotectedAttrSet = new BERSet(attrTable.toASN1EncodableVector());
        }
        ContentInfo contentInfo = new ContentInfo(CMSObjectIdentifiers.encryptedData, new EncryptedData(eci, unprotectedAttrSet));
        return new CMSEncryptedData(contentInfo);
    }

    public CMSEncryptedData generate(CMSTypedData content, OutputEncryptor contentEncryptor)
        throws CMSException
    {
        return doGenerate(content, contentEncryptor);
    }
}
