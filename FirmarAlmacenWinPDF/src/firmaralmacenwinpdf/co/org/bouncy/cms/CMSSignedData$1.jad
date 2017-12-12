// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedData.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cms.ContentInfo;
import co.org.bouncy.asn1.cms.SignedData;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSTypedData, CMSException, CMSSignedData, CMSProcessable

class CMSSignedData$1
    implements CMSTypedData
{

    public ASN1ObjectIdentifier getContentType()
    {
        return signedData.getEncapContentInfo().getContentType();
    }

    public void write(OutputStream out)
        throws IOException, CMSException
    {
        val$signedContent.write(out);
    }

    public Object getContent()
    {
        return val$signedContent.getContent();
    }

    final CMSProcessable val$signedContent;
    final CMSSignedData this$0;

    CMSSignedData$1()
    {
        this$0 = final_cmssigneddata;
        val$signedContent = CMSProcessable.this;
        super();
    }
}
