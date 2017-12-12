// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECNamedCurveTable.java

package co.org.bouncy.asn1.x9;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.nist.NISTNamedCurves;
import co.org.bouncy.asn1.sec.SECNamedCurves;
import co.org.bouncy.asn1.teletrust.TeleTrusTNamedCurves;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1.x9:
//            X9ECParameters, X962NamedCurves

public class ECNamedCurveTable
{

    public ECNamedCurveTable()
    {
    }

    public static X9ECParameters getByName(String name)
    {
        X9ECParameters ecP = X962NamedCurves.getByName(name);
        if(ecP == null)
            ecP = SECNamedCurves.getByName(name);
        if(ecP == null)
            ecP = TeleTrusTNamedCurves.getByName(name);
        if(ecP == null)
            ecP = NISTNamedCurves.getByName(name);
        return ecP;
    }

    public static X9ECParameters getByOID(ASN1ObjectIdentifier oid)
    {
        X9ECParameters ecP = X962NamedCurves.getByOID(oid);
        if(ecP == null)
            ecP = SECNamedCurves.getByOID(oid);
        if(ecP == null)
            ecP = TeleTrusTNamedCurves.getByOID(oid);
        return ecP;
    }

    public static Enumeration getNames()
    {
        Vector v = new Vector();
        addEnumeration(v, X962NamedCurves.getNames());
        addEnumeration(v, SECNamedCurves.getNames());
        addEnumeration(v, NISTNamedCurves.getNames());
        addEnumeration(v, TeleTrusTNamedCurves.getNames());
        return v.elements();
    }

    private static void addEnumeration(Vector v, Enumeration e)
    {
        for(; e.hasMoreElements(); v.addElement(e.nextElement()));
    }
}
