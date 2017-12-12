// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSSignedDataGenerator.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cms:
//            CMSTypedData, CMSException, CMSSignedDataGenerator, CMSProcessable

class CMSSignedDataGenerator$1
    implements CMSTypedData
{

    public ASN1ObjectIdentifier getContentType()
    {
        return val$contentTypeOID;
    }

    public void write(OutputStream out)
        throws IOException, CMSException
    {
        val$content.write(out);
    }

    public Object getContent()
    {
        return val$content.getContent();
    }

    final ASN1ObjectIdentifier val$contentTypeOID;
    final CMSProcessable val$content;
    final CMSSignedDataGenerator this$0;

    CMSSignedDataGenerator$1()
    {
        this$0 = final_cmssigneddatagenerator;
        val$contentTypeOID = asn1objectidentifier;
        val$content = CMSProcessable.this;
        super();
    }
}
