// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AuthEnvelopedDataParser.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            EncryptedContentInfoParser, OriginatorInfo

public class AuthEnvelopedDataParser
{

    public AuthEnvelopedDataParser(ASN1SequenceParser seq)
        throws IOException
    {
        this.seq = seq;
        version = ASN1Integer.getInstance(seq.readObject());
    }

    public ASN1Integer getVersion()
    {
        return version;
    }

    public OriginatorInfo getOriginatorInfo()
        throws IOException
    {
        originatorInfoCalled = true;
        if(nextObject == null)
            nextObject = seq.readObject();
        if((nextObject instanceof ASN1TaggedObjectParser) && ((ASN1TaggedObjectParser)nextObject).getTagNo() == 0)
        {
            ASN1SequenceParser originatorInfo = (ASN1SequenceParser)((ASN1TaggedObjectParser)nextObject).getObjectParser(16, false);
            nextObject = null;
            return OriginatorInfo.getInstance(originatorInfo.toASN1Primitive());
        } else
        {
            return null;
        }
    }

    public ASN1SetParser getRecipientInfos()
        throws IOException
    {
        if(!originatorInfoCalled)
            getOriginatorInfo();
        if(nextObject == null)
            nextObject = seq.readObject();
        ASN1SetParser recipientInfos = (ASN1SetParser)nextObject;
        nextObject = null;
        return recipientInfos;
    }

    public EncryptedContentInfoParser getAuthEncryptedContentInfo()
        throws IOException
    {
        if(nextObject == null)
            nextObject = seq.readObject();
        if(nextObject != null)
        {
            ASN1SequenceParser o = (ASN1SequenceParser)nextObject;
            nextObject = null;
            return new EncryptedContentInfoParser(o);
        } else
        {
            return null;
        }
    }

    public ASN1SetParser getAuthAttrs()
        throws IOException
    {
        if(nextObject == null)
            nextObject = seq.readObject();
        if(nextObject instanceof ASN1TaggedObjectParser)
        {
            ASN1Encodable o = nextObject;
            nextObject = null;
            return (ASN1SetParser)((ASN1TaggedObjectParser)o).getObjectParser(17, false);
        } else
        {
            return null;
        }
    }

    public ASN1OctetString getMac()
        throws IOException
    {
        if(nextObject == null)
            nextObject = seq.readObject();
        ASN1Encodable o = nextObject;
        nextObject = null;
        return ASN1OctetString.getInstance(o.toASN1Primitive());
    }

    public ASN1SetParser getUnauthAttrs()
        throws IOException
    {
        if(nextObject == null)
            nextObject = seq.readObject();
        if(nextObject != null)
        {
            ASN1Encodable o = nextObject;
            nextObject = null;
            return (ASN1SetParser)((ASN1TaggedObjectParser)o).getObjectParser(17, false);
        } else
        {
            return null;
        }
    }

    private ASN1SequenceParser seq;
    private ASN1Integer version;
    private ASN1Encodable nextObject;
    private boolean originatorInfoCalled;
}
