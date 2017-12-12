// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SubjectDirectoryAttributes.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Attribute

public class SubjectDirectoryAttributes extends ASN1Object
{

    public static SubjectDirectoryAttributes getInstance(Object obj)
    {
        if(obj instanceof SubjectDirectoryAttributes)
            return (SubjectDirectoryAttributes)obj;
        if(obj != null)
            return new SubjectDirectoryAttributes(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private SubjectDirectoryAttributes(ASN1Sequence seq)
    {
        attributes = new Vector();
        ASN1Sequence s;
        for(Enumeration e = seq.getObjects(); e.hasMoreElements(); attributes.addElement(Attribute.getInstance(s)))
            s = ASN1Sequence.getInstance(e.nextElement());

    }

    public SubjectDirectoryAttributes(Vector attributes)
    {
        this.attributes = new Vector();
        for(Enumeration e = attributes.elements(); e.hasMoreElements(); this.attributes.addElement(e.nextElement()));
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        for(Enumeration e = attributes.elements(); e.hasMoreElements(); vec.add((Attribute)e.nextElement()));
        return new DERSequence(vec);
    }

    public Vector getAttributes()
    {
        return attributes;
    }

    private Vector attributes;
}
