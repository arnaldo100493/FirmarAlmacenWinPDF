// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Extensions.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Extension

public class Extensions extends ASN1Object
{

    public static Extensions getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static Extensions getInstance(Object obj)
    {
        if(obj instanceof Extensions)
            return (Extensions)obj;
        if(obj != null)
            return new Extensions(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    private Extensions(ASN1Sequence seq)
    {
        extensions = new Hashtable();
        ordering = new Vector();
        Extension ext;
        for(Enumeration e = seq.getObjects(); e.hasMoreElements(); ordering.addElement(ext.getExtnId()))
        {
            ext = Extension.getInstance(e.nextElement());
            extensions.put(ext.getExtnId(), ext);
        }

    }

    public Extensions(Extension extension)
    {
        extensions = new Hashtable();
        ordering = new Vector();
        ordering.addElement(extension.getExtnId());
        extensions.put(extension.getExtnId(), extension);
    }

    public Extensions(Extension extensions[])
    {
        this.extensions = new Hashtable();
        ordering = new Vector();
        for(int i = 0; i != extensions.length; i++)
        {
            Extension ext = extensions[i];
            ordering.addElement(ext.getExtnId());
            this.extensions.put(ext.getExtnId(), ext);
        }

    }

    public Enumeration oids()
    {
        return ordering.elements();
    }

    public Extension getExtension(ASN1ObjectIdentifier oid)
    {
        return (Extension)extensions.get(oid);
    }

    public ASN1Encodable getExtensionParsedValue(ASN1ObjectIdentifier oid)
    {
        Extension ext = getExtension(oid);
        if(ext != null)
            return ext.getParsedValue();
        else
            return null;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        Extension ext;
        for(Enumeration e = ordering.elements(); e.hasMoreElements(); vec.add(ext))
        {
            ASN1ObjectIdentifier oid = (ASN1ObjectIdentifier)e.nextElement();
            ext = (Extension)extensions.get(oid);
        }

        return new DERSequence(vec);
    }

    public boolean equivalent(Extensions other)
    {
        if(extensions.size() != other.extensions.size())
            return false;
        for(Enumeration e1 = extensions.keys(); e1.hasMoreElements();)
        {
            Object key = e1.nextElement();
            if(!extensions.get(key).equals(other.extensions.get(key)))
                return false;
        }

        return true;
    }

    public ASN1ObjectIdentifier[] getExtensionOIDs()
    {
        return toOidArray(ordering);
    }

    public ASN1ObjectIdentifier[] getNonCriticalExtensionOIDs()
    {
        return getExtensionOIDs(false);
    }

    public ASN1ObjectIdentifier[] getCriticalExtensionOIDs()
    {
        return getExtensionOIDs(true);
    }

    private ASN1ObjectIdentifier[] getExtensionOIDs(boolean isCritical)
    {
        Vector oidVec = new Vector();
        for(int i = 0; i != ordering.size(); i++)
        {
            Object oid = ordering.elementAt(i);
            if(((Extension)extensions.get(oid)).isCritical() == isCritical)
                oidVec.addElement(oid);
        }

        return toOidArray(oidVec);
    }

    private ASN1ObjectIdentifier[] toOidArray(Vector oidVec)
    {
        ASN1ObjectIdentifier oids[] = new ASN1ObjectIdentifier[oidVec.size()];
        for(int i = 0; i != oids.length; i++)
            oids[i] = (ASN1ObjectIdentifier)oidVec.elementAt(i);

        return oids;
    }

    private Hashtable extensions;
    private Vector ordering;
}
