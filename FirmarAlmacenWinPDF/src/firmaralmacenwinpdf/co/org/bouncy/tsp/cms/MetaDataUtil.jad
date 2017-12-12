// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MetaDataUtil.java

package co.org.bouncy.tsp.cms;

import co.org.bouncy.asn1.ASN1String;
import co.org.bouncy.asn1.cms.Attributes;
import co.org.bouncy.asn1.cms.MetaData;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.operator.DigestCalculator;
import java.io.IOException;
import java.io.OutputStream;

class MetaDataUtil
{

    MetaDataUtil(MetaData metaData)
    {
        this.metaData = metaData;
    }

    void initialiseMessageImprintDigestCalculator(DigestCalculator calculator)
        throws CMSException
    {
        if(metaData != null && metaData.isHashProtected())
            try
            {
                calculator.getOutputStream().write(metaData.getEncoded("DER"));
            }
            catch(IOException e)
            {
                throw new CMSException((new StringBuilder()).append("unable to initialise calculator from metaData: ").append(e.getMessage()).toString(), e);
            }
    }

    String getFileName()
    {
        if(metaData != null)
            return convertString(metaData.getFileName());
        else
            return null;
    }

    String getMediaType()
    {
        if(metaData != null)
            return convertString(metaData.getMediaType());
        else
            return null;
    }

    Attributes getOtherMetaData()
    {
        if(metaData != null)
            return metaData.getOtherMetaData();
        else
            return null;
    }

    private String convertString(ASN1String s)
    {
        if(s != null)
            return s.toString();
        else
            return null;
    }

    private final MetaData metaData;
}
