// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LDSVersionInfo.java

package co.org.bouncy.asn1.icao;

import co.org.bouncy.asn1.*;

public class LDSVersionInfo extends ASN1Object
{

    public LDSVersionInfo(String ldsVersion, String unicodeVersion)
    {
        this.ldsVersion = new DERPrintableString(ldsVersion);
        this.unicodeVersion = new DERPrintableString(unicodeVersion);
    }

    private LDSVersionInfo(ASN1Sequence seq)
    {
        if(seq.size() != 2)
        {
            throw new IllegalArgumentException("sequence wrong size for LDSVersionInfo");
        } else
        {
            ldsVersion = DERPrintableString.getInstance(seq.getObjectAt(0));
            unicodeVersion = DERPrintableString.getInstance(seq.getObjectAt(1));
            return;
        }
    }

    public static LDSVersionInfo getInstance(Object obj)
    {
        if(obj instanceof LDSVersionInfo)
            return (LDSVersionInfo)obj;
        if(obj != null)
            return new LDSVersionInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public String getLdsVersion()
    {
        return ldsVersion.getString();
    }

    public String getUnicodeVersion()
    {
        return unicodeVersion.getString();
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(ldsVersion);
        v.add(unicodeVersion);
        return new DERSequence(v);
    }

    private DERPrintableString ldsVersion;
    private DERPrintableString unicodeVersion;
}
