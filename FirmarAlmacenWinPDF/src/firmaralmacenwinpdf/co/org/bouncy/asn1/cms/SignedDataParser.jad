// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignedDataParser.java

package co.org.bouncy.asn1.cms;

import co.org.bouncy.asn1.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1.cms:
//            ContentInfoParser

public class SignedDataParser
{

    public static SignedDataParser getInstance(Object o)
        throws IOException
    {
        if(o instanceof ASN1Sequence)
            return new SignedDataParser(((ASN1Sequence)o).parser());
        if(o instanceof ASN1SequenceParser)
            return new SignedDataParser((ASN1SequenceParser)o);
        else
            throw new IOException((new StringBuilder()).append("unknown object encountered: ").append(o.getClass().getName()).toString());
    }

    private SignedDataParser(ASN1SequenceParser seq)
        throws IOException
    {
        _seq = seq;
        _version = (ASN1Integer)seq.readObject();
    }

    public ASN1Integer getVersion()
    {
        return _version;
    }

    public ASN1SetParser getDigestAlgorithms()
        throws IOException
    {
        Object o = _seq.readObject();
        if(o instanceof ASN1Set)
            return ((ASN1Set)o).parser();
        else
            return (ASN1SetParser)o;
    }

    public ContentInfoParser getEncapContentInfo()
        throws IOException
    {
        return new ContentInfoParser((ASN1SequenceParser)_seq.readObject());
    }

    public ASN1SetParser getCertificates()
        throws IOException
    {
        _certsCalled = true;
        _nextObject = _seq.readObject();
        if((_nextObject instanceof ASN1TaggedObjectParser) && ((ASN1TaggedObjectParser)_nextObject).getTagNo() == 0)
        {
            ASN1SetParser certs = (ASN1SetParser)((ASN1TaggedObjectParser)_nextObject).getObjectParser(17, false);
            _nextObject = null;
            return certs;
        } else
        {
            return null;
        }
    }

    public ASN1SetParser getCrls()
        throws IOException
    {
        if(!_certsCalled)
            throw new IOException("getCerts() has not been called.");
        _crlsCalled = true;
        if(_nextObject == null)
            _nextObject = _seq.readObject();
        if((_nextObject instanceof ASN1TaggedObjectParser) && ((ASN1TaggedObjectParser)_nextObject).getTagNo() == 1)
        {
            ASN1SetParser crls = (ASN1SetParser)((ASN1TaggedObjectParser)_nextObject).getObjectParser(17, false);
            _nextObject = null;
            return crls;
        } else
        {
            return null;
        }
    }

    public ASN1SetParser getSignerInfos()
        throws IOException
    {
        if(!_certsCalled || !_crlsCalled)
            throw new IOException("getCerts() and/or getCrls() has not been called.");
        if(_nextObject == null)
            _nextObject = _seq.readObject();
        return (ASN1SetParser)_nextObject;
    }

    private ASN1SequenceParser _seq;
    private ASN1Integer _version;
    private Object _nextObject;
    private boolean _certsCalled;
    private boolean _crlsCalled;
}
