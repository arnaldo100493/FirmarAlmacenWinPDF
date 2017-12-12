// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ECGOST3410NamedCurveTable.java

package co.org.bouncy.jce;

import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cryptopro.ECGOST3410NamedCurves;
import co.org.bouncy.crypto.params.ECDomainParameters;
import co.org.bouncy.jce.spec.ECNamedCurveParameterSpec;
import java.util.Enumeration;

public class ECGOST3410NamedCurveTable
{

    public ECGOST3410NamedCurveTable()
    {
    }

    public static ECNamedCurveParameterSpec getParameterSpec(String name)
    {
        ECDomainParameters ecP = ECGOST3410NamedCurves.getByName(name);
        if(ecP == null)
            try
            {
                ecP = ECGOST3410NamedCurves.getByOID(new ASN1ObjectIdentifier(name));
            }
            catch(IllegalArgumentException e)
            {
                return null;
            }
        if(ecP == null)
            return null;
        else
            return new ECNamedCurveParameterSpec(name, ecP.getCurve(), ecP.getG(), ecP.getN(), ecP.getH(), ecP.getSeed());
    }

    public static Enumeration getNames()
    {
        return ECGOST3410NamedCurves.getNames();
    }
}
