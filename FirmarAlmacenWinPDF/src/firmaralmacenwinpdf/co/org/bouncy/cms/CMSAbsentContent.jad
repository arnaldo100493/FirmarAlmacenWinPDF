// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSAbsentContent.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cms.CMSObjectIdentifiers;
import java.io.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSTypedData, CMSReadable, CMSException

public class CMSAbsentContent
    implements CMSTypedData, CMSReadable
{

    public CMSAbsentContent()
    {
        this(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()));
    }

    public CMSAbsentContent(ASN1ObjectIdentifier type)
    {
        this.type = type;
    }

    public InputStream getInputStream()
    {
        return null;
    }

    public void write(OutputStream outputstream)
        throws IOException, CMSException
    {
    }

    public Object getContent()
    {
        return null;
    }

    public ASN1ObjectIdentifier getContentType()
    {
        return type;
    }

    private final ASN1ObjectIdentifier type;
}
