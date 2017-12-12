// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1EncodableVector.java

package co.org.bouncy.asn1;

import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Encodable

public class ASN1EncodableVector
{

    public ASN1EncodableVector()
    {
        v = new Vector();
    }

    public void add(ASN1Encodable obj)
    {
        v.addElement(obj);
    }

    public void addAll(ASN1EncodableVector other)
    {
        for(Enumeration en = other.v.elements(); en.hasMoreElements(); v.addElement(en.nextElement()));
    }

    public ASN1Encodable get(int i)
    {
        return (ASN1Encodable)v.elementAt(i);
    }

    public int size()
    {
        return v.size();
    }

    Vector v;
}
