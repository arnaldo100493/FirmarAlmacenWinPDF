// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DSTU4145Params.java

package co.org.bouncy.asn1.ua;

import co.org.bouncy.asn1.*;
import co.org.bouncy.util.Arrays;

// Referenced classes of package co.org.bouncy.asn1.ua:
//            DSTU4145ECBinary

public class DSTU4145Params extends ASN1Object
{

    public DSTU4145Params(ASN1ObjectIdentifier namedCurve)
    {
        dke = DEFAULT_DKE;
        this.namedCurve = namedCurve;
    }

    public DSTU4145Params(DSTU4145ECBinary ecbinary)
    {
        dke = DEFAULT_DKE;
        this.ecbinary = ecbinary;
    }

    public boolean isNamedCurve()
    {
        return namedCurve != null;
    }

    public DSTU4145ECBinary getECBinary()
    {
        return ecbinary;
    }

    public byte[] getDKE()
    {
        return dke;
    }

    public static byte[] getDefaultDKE()
    {
        return DEFAULT_DKE;
    }

    public ASN1ObjectIdentifier getNamedCurve()
    {
        return namedCurve;
    }

    public static DSTU4145Params getInstance(Object obj)
    {
        if(obj instanceof DSTU4145Params)
            return (DSTU4145Params)obj;
        if(obj != null)
        {
            ASN1Sequence seq = ASN1Sequence.getInstance(obj);
            DSTU4145Params params;
            if(seq.getObjectAt(0) instanceof ASN1ObjectIdentifier)
                params = new DSTU4145Params(ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0)));
            else
                params = new DSTU4145Params(DSTU4145ECBinary.getInstance(seq.getObjectAt(0)));
            if(seq.size() == 2)
            {
                params.dke = ASN1OctetString.getInstance(seq.getObjectAt(1)).getOctets();
                if(params.dke.length != DEFAULT_DKE.length)
                    throw new IllegalArgumentException("object parse error");
            }
            return params;
        } else
        {
            throw new IllegalArgumentException("object parse error");
        }
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(namedCurve != null)
            v.add(namedCurve);
        else
            v.add(ecbinary);
        if(!Arrays.areEqual(dke, DEFAULT_DKE))
            v.add(new DEROctetString(dke));
        return new DERSequence(v);
    }

    private static final byte DEFAULT_DKE[] = {
        -87, -42, -21, 69, -15, 60, 112, -126, -128, -60, 
        -106, 123, 35, 31, 94, -83, -10, 88, -21, -92, 
        -64, 55, 41, 29, 56, -39, 107, -16, 37, -54, 
        78, 23, -8, -23, 114, 13, -58, 21, -76, 58, 
        40, -105, 95, 11, -63, -34, -93, 100, 56, -75, 
        100, -22, 44, 23, -97, -48, 18, 62, 109, -72, 
        -6, -59, 121, 4
    };
    private ASN1ObjectIdentifier namedCurve;
    private DSTU4145ECBinary ecbinary;
    private byte dke[];

}
