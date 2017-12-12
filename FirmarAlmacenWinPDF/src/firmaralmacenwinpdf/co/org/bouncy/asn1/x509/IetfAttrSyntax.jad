// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IetfAttrSyntax.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            GeneralNames

public class IetfAttrSyntax extends ASN1Object
{

    public static IetfAttrSyntax getInstance(Object obj)
    {
        if(obj instanceof IetfAttrSyntax)
            return (IetfAttrSyntax)obj;
        if(obj != null)
            return new IetfAttrSyntax(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private IetfAttrSyntax(ASN1Sequence seq)
    {
        policyAuthority = null;
        values = new Vector();
        valueChoice = -1;
        int i = 0;
        if(seq.getObjectAt(0) instanceof ASN1TaggedObject)
        {
            policyAuthority = GeneralNames.getInstance((ASN1TaggedObject)seq.getObjectAt(0), false);
            i++;
        } else
        if(seq.size() == 2)
        {
            policyAuthority = GeneralNames.getInstance(seq.getObjectAt(0));
            i++;
        }
        if(!(seq.getObjectAt(i) instanceof ASN1Sequence))
            throw new IllegalArgumentException("Non-IetfAttrSyntax encoding");
        seq = (ASN1Sequence)seq.getObjectAt(i);
        ASN1Primitive obj;
        for(Enumeration e = seq.getObjects(); e.hasMoreElements(); values.addElement(obj))
        {
            obj = (ASN1Primitive)e.nextElement();
            int type;
            if(obj instanceof ASN1ObjectIdentifier)
                type = 2;
            else
            if(obj instanceof DERUTF8String)
                type = 3;
            else
            if(obj instanceof DEROctetString)
                type = 1;
            else
                throw new IllegalArgumentException("Bad value type encoding IetfAttrSyntax");
            if(valueChoice < 0)
                valueChoice = type;
            if(type != valueChoice)
                throw new IllegalArgumentException("Mix of value types in IetfAttrSyntax");
        }

    }

    public GeneralNames getPolicyAuthority()
    {
        return policyAuthority;
    }

    public int getValueType()
    {
        return valueChoice;
    }

    public Object[] getValues()
    {
        ASN1ObjectIdentifier tmp[];
        if(getValueType() == 1)
        {
            tmp = new ASN1OctetString[values.size()];
            for(int i = 0; i != tmp.length; i++)
                tmp[i] = (ASN1OctetString)values.elementAt(i);

            return tmp;
        }
        if(getValueType() == 2)
        {
            tmp = new ASN1ObjectIdentifier[values.size()];
            for(int i = 0; i != tmp.length; i++)
                tmp[i] = (ASN1ObjectIdentifier)values.elementAt(i);

            return tmp;
        }
        tmp = new DERUTF8String[values.size()];
        for(int i = 0; i != tmp.length; i++)
            tmp[i] = (DERUTF8String)values.elementAt(i);

        return tmp;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(policyAuthority != null)
            v.add(new DERTaggedObject(0, policyAuthority));
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        for(Enumeration i = values.elements(); i.hasMoreElements(); v2.add((ASN1Encodable)i.nextElement()));
        v.add(new DERSequence(v2));
        return new DERSequence(v);
    }

    public static final int VALUE_OCTETS = 1;
    public static final int VALUE_OID = 2;
    public static final int VALUE_UTF8 = 3;
    GeneralNames policyAuthority;
    Vector values;
    int valueChoice;
}
