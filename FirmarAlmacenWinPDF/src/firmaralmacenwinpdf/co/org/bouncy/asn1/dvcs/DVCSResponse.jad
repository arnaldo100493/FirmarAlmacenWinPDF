// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSResponse.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1.dvcs:
//            DVCSCertInfo, DVCSErrorNotice

public class DVCSResponse extends ASN1Object
    implements ASN1Choice
{

    public DVCSResponse(DVCSCertInfo dvCertInfo)
    {
        this.dvCertInfo = dvCertInfo;
    }

    public DVCSResponse(DVCSErrorNotice dvErrorNote)
    {
        this.dvErrorNote = dvErrorNote;
    }

    public static DVCSResponse getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DVCSResponse))
            return (DVCSResponse)obj;
        if(obj instanceof byte[])
            try
            {
                return getInstance(ASN1Primitive.fromByteArray((byte[])(byte[])obj));
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("failed to construct sequence from byte[]: ").append(e.getMessage()).toString());
            }
        if(obj instanceof ASN1Sequence)
        {
            DVCSCertInfo dvCertInfo = DVCSCertInfo.getInstance(obj);
            return new DVCSResponse(dvCertInfo);
        }
        if(obj instanceof ASN1TaggedObject)
        {
            ASN1TaggedObject t = ASN1TaggedObject.getInstance(obj);
            DVCSErrorNotice dvErrorNote = DVCSErrorNotice.getInstance(t, false);
            return new DVCSResponse(dvErrorNote);
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Couldn't convert from object to DVCSResponse: ").append(obj.getClass().getName()).toString());
        }
    }

    public static DVCSResponse getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public DVCSCertInfo getCertInfo()
    {
        return dvCertInfo;
    }

    public DVCSErrorNotice getErrorNotice()
    {
        return dvErrorNote;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(dvCertInfo != null)
            return dvCertInfo.toASN1Primitive();
        else
            return new DERTaggedObject(0, dvErrorNote);
    }

    public String toString()
    {
        if(dvCertInfo != null)
            return (new StringBuilder()).append("DVCSResponse {\ndvCertInfo: ").append(dvCertInfo.toString()).append("}\n").toString();
        if(dvErrorNote != null)
            return (new StringBuilder()).append("DVCSResponse {\ndvErrorNote: ").append(dvErrorNote.toString()).append("}\n").toString();
        else
            return null;
    }

    private DVCSCertInfo dvCertInfo;
    private DVCSErrorNotice dvErrorNote;
}
