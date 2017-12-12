// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ErrorMsgContent.java

package co.org.bouncy.asn1.cmp;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.cmp:
//            PKIStatusInfo, PKIFreeText

public class ErrorMsgContent extends ASN1Object
{

    private ErrorMsgContent(ASN1Sequence seq)
    {
        Enumeration en = seq.getObjects();
        pkiStatusInfo = PKIStatusInfo.getInstance(en.nextElement());
        while(en.hasMoreElements()) 
        {
            Object o = en.nextElement();
            if(o instanceof ASN1Integer)
                errorCode = ASN1Integer.getInstance(o);
            else
                errorDetails = PKIFreeText.getInstance(o);
        }
    }

    public static ErrorMsgContent getInstance(Object o)
    {
        if(o instanceof ErrorMsgContent)
            return (ErrorMsgContent)o;
        if(o != null)
            return new ErrorMsgContent(ASN1Sequence.getInstance(o));
        else
            return null;
    }

    public ErrorMsgContent(PKIStatusInfo pkiStatusInfo)
    {
        this(pkiStatusInfo, null, null);
    }

    public ErrorMsgContent(PKIStatusInfo pkiStatusInfo, ASN1Integer errorCode, PKIFreeText errorDetails)
    {
        if(pkiStatusInfo == null)
        {
            throw new IllegalArgumentException("'pkiStatusInfo' cannot be null");
        } else
        {
            this.pkiStatusInfo = pkiStatusInfo;
            this.errorCode = errorCode;
            this.errorDetails = errorDetails;
            return;
        }
    }

    public PKIStatusInfo getPKIStatusInfo()
    {
        return pkiStatusInfo;
    }

    public ASN1Integer getErrorCode()
    {
        return errorCode;
    }

    public PKIFreeText getErrorDetails()
    {
        return errorDetails;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(pkiStatusInfo);
        addOptional(v, errorCode);
        addOptional(v, errorDetails);
        return new DERSequence(v);
    }

    private void addOptional(ASN1EncodableVector v, ASN1Encodable obj)
    {
        if(obj != null)
            v.add(obj);
    }

    private PKIStatusInfo pkiStatusInfo;
    private ASN1Integer errorCode;
    private PKIFreeText errorDetails;
}
