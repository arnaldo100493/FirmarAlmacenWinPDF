// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMSProcessableByteArray.java

package co.org.bouncy.cms;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cms.CMSObjectIdentifiers;
import co.org.bouncy.util.Arrays;
import java.io.*;

// Referenced classes of package co.org.bouncy.cms:
//            CMSTypedData, CMSReadable, CMSException

public class CMSProcessableByteArray
    implements CMSTypedData, CMSReadable
{

    public CMSProcessableByteArray(byte bytes[])
    {
        this(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), bytes);
    }

    public CMSProcessableByteArray(ASN1ObjectIdentifier type, byte bytes[])
    {
        this.type = type;
        this.bytes = bytes;
    }

    public InputStream getInputStream()
    {
        return new ByteArrayInputStream(bytes);
    }

    public void write(OutputStream zOut)
        throws IOException, CMSException
    {
        zOut.write(bytes);
    }

    public Object getContent()
    {
        return Arrays.clone(bytes);
    }

    public ASN1ObjectIdentifier getContentType()
    {
        return type;
    }

    private final ASN1ObjectIdentifier type;
    private final byte bytes[];
}
