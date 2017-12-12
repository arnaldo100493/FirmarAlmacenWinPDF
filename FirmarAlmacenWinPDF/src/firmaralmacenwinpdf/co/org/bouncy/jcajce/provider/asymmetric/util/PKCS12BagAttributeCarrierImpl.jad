// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS12BagAttributeCarrierImpl.java

package co.org.bouncy.jcajce.provider.asymmetric.util;

import co.org.bouncy.asn1.*;
import co.org.bouncy.jce.interfaces.PKCS12BagAttributeCarrier;
import java.io.*;
import java.util.*;

public class PKCS12BagAttributeCarrierImpl
    implements PKCS12BagAttributeCarrier
{

    PKCS12BagAttributeCarrierImpl(Hashtable attributes, Vector ordering)
    {
        pkcs12Attributes = attributes;
        pkcs12Ordering = ordering;
    }

    public PKCS12BagAttributeCarrierImpl()
    {
        this(new Hashtable(), new Vector());
    }

    public void setBagAttribute(ASN1ObjectIdentifier oid, ASN1Encodable attribute)
    {
        if(pkcs12Attributes.containsKey(oid))
        {
            pkcs12Attributes.put(oid, attribute);
        } else
        {
            pkcs12Attributes.put(oid, attribute);
            pkcs12Ordering.addElement(oid);
        }
    }

    public ASN1Encodable getBagAttribute(ASN1ObjectIdentifier oid)
    {
        return (ASN1Encodable)pkcs12Attributes.get(oid);
    }

    public Enumeration getBagAttributeKeys()
    {
        return pkcs12Ordering.elements();
    }

    int size()
    {
        return pkcs12Ordering.size();
    }

    Hashtable getAttributes()
    {
        return pkcs12Attributes;
    }

    Vector getOrdering()
    {
        return pkcs12Ordering;
    }

    public void writeObject(ObjectOutputStream out)
        throws IOException
    {
        if(pkcs12Ordering.size() == 0)
        {
            out.writeObject(new Hashtable());
            out.writeObject(new Vector());
        } else
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ASN1OutputStream aOut = new ASN1OutputStream(bOut);
            DERObjectIdentifier oid;
            for(Enumeration e = getBagAttributeKeys(); e.hasMoreElements(); aOut.writeObject((ASN1Encodable)pkcs12Attributes.get(oid)))
            {
                oid = (DERObjectIdentifier)e.nextElement();
                aOut.writeObject(oid);
            }

            out.writeObject(bOut.toByteArray());
        }
    }

    public void readObject(ObjectInputStream in)
        throws IOException, ClassNotFoundException
    {
        Object obj = in.readObject();
        if(obj instanceof Hashtable)
        {
            pkcs12Attributes = (Hashtable)obj;
            pkcs12Ordering = (Vector)in.readObject();
        } else
        {
            ASN1InputStream aIn = new ASN1InputStream((byte[])(byte[])obj);
            ASN1ObjectIdentifier oid;
            while((oid = (ASN1ObjectIdentifier)aIn.readObject()) != null) 
                setBagAttribute(oid, aIn.readObject());
        }
    }

    private Hashtable pkcs12Attributes;
    private Vector pkcs12Ordering;
}
