// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSTimeStampedGenerator.java

package co.org.bouncy.tsp.cms;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.Attributes;
import co.org.bouncy.asn1.cms.MetaData;
import co.org.bouncy.cms.CMSException;
import co.org.bouncy.operator.DigestCalculator;
import java.net.URI;

// Referenced classes of package co.org.bouncy.tsp.cms:
//            MetaDataUtil

public class CMSTimeStampedGenerator
{

    public CMSTimeStampedGenerator()
    {
    }

    public void setDataUri(URI dataUri)
    {
        this.dataUri = dataUri;
    }

    public void setMetaData(boolean hashProtected, String fileName, String mediaType)
    {
        setMetaData(hashProtected, fileName, mediaType, null);
    }

    public void setMetaData(boolean hashProtected, String fileName, String mediaType, Attributes attributes)
    {
        DERUTF8String asn1FileName = null;
        if(fileName != null)
            asn1FileName = new DERUTF8String(fileName);
        DERIA5String asn1MediaType = null;
        if(mediaType != null)
            asn1MediaType = new DERIA5String(mediaType);
        setMetaData(hashProtected, asn1FileName, asn1MediaType, attributes);
    }

    private void setMetaData(boolean hashProtected, DERUTF8String fileName, DERIA5String mediaType, Attributes attributes)
    {
        metaData = new MetaData(ASN1Boolean.getInstance(hashProtected), fileName, mediaType, attributes);
    }

    public void initialiseMessageImprintDigestCalculator(DigestCalculator calculator)
        throws CMSException
    {
        MetaDataUtil util = new MetaDataUtil(metaData);
        util.initialiseMessageImprintDigestCalculator(calculator);
    }

    protected MetaData metaData;
    protected URI dataUri;
}
