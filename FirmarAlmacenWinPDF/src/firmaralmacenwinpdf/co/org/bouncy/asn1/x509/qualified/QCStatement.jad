// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   QCStatement.java

package co.org.bouncy.asn1.x509.qualified;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.x509.qualified:
//            ETSIQCObjectIdentifiers, RFC3739QCObjectIdentifiers

public class QCStatement extends ASN1Object
    implements ETSIQCObjectIdentifiers, RFC3739QCObjectIdentifiers
{

    public static QCStatement getInstance(Object obj)
    {
        if(obj instanceof QCStatement)
            return (QCStatement)obj;
        if(obj != null)
            return new QCStatement(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private QCStatement(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        qcStatementId = ASN1ObjectIdentifier.getInstance(e.nextElement());
        if(e.hasMoreElements())
            qcStatementInfo = (ASN1Encodable)e.nextElement();
    }

    public QCStatement(ASN1ObjectIdentifier qcStatementId)
    {
        this.qcStatementId = qcStatementId;
        qcStatementInfo = null;
    }

    public QCStatement(ASN1ObjectIdentifier qcStatementId, ASN1Encodable qcStatementInfo)
    {
        this.qcStatementId = qcStatementId;
        this.qcStatementInfo = qcStatementInfo;
    }

    public ASN1ObjectIdentifier getStatementId()
    {
        return qcStatementId;
    }

    public ASN1Encodable getStatementInfo()
    {
        return qcStatementInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector seq = new ASN1EncodableVector();
        seq.add(qcStatementId);
        if(qcStatementInfo != null)
            seq.add(qcStatementInfo);
        return new DERSequence(seq);
    }

    ASN1ObjectIdentifier qcStatementId;
    ASN1Encodable qcStatementInfo;
}
