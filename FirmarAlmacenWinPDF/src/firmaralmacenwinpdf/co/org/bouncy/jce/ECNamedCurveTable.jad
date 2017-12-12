// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECNamedCurveTable.java

package co.org.bouncy.jce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.nist.NISTNamedCurves;
import co.org.bouncy.asn1.sec.SECNamedCurves;
import co.org.bouncy.asn1.teletrust.TeleTrusTNamedCurves;
import co.org.bouncy.asn1.x9.X962NamedCurves;
import co.org.bouncy.asn1.x9.X9ECParameters;
import co.org.bouncy.jce.spec.ECNamedCurveParameterSpec;
import java.util.Enumeration;
import java.util.Vector;

public class ECNamedCurveTable
{

    public ECNamedCurveTable()
    {
    }

    public static ECNamedCurveParameterSpec getParameterSpec(String name)
    {
        X9ECParameters ecP = X962NamedCurves.getByName(name);
        if(ecP == null)
            try
            {
                ecP = X962NamedCurves.getByOID(new ASN1ObjectIdentifier(name));
            }
            catch(IllegalArgumentException e) { }
        if(ecP == null)
        {
            ecP = SECNamedCurves.getByName(name);
            if(ecP == null)
                try
                {
                    ecP = SECNamedCurves.getByOID(new ASN1ObjectIdentifier(name));
                }
                catch(IllegalArgumentException e) { }
        }
        if(ecP == null)
        {
            ecP = TeleTrusTNamedCurves.getByName(name);
            if(ecP == null)
                try
                {
                    ecP = TeleTrusTNamedCurves.getByOID(new ASN1ObjectIdentifier(name));
                }
                catch(IllegalArgumentException e) { }
        }
        if(ecP == null)
            ecP = NISTNamedCurves.getByName(name);
        if(ecP == null)
            return null;
        else
            return new ECNamedCurveParameterSpec(name, ecP.getCurve(), ecP.getG(), ecP.getN(), ecP.getH(), ecP.getSeed());
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
