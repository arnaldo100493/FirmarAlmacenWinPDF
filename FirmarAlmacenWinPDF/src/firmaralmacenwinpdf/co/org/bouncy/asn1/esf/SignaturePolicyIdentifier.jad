// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignaturePolicyIdentifier.java

package co.org.bouncy.asn1.esf;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.esf:
//            SignaturePolicyId

public class SignaturePolicyIdentifier extends ASN1Object
{

    public static SignaturePolicyIdentifier getInstance(Object obj)
    {
        if(obj instanceof SignaturePolicyIdentifier)
            return (SignaturePolicyIdentifier)obj;
        if((obj instanceof ASN1Null) || hasEncodedTagValue(obj, 5))
            return new SignaturePolicyIdentifier();
        if(obj != null)
            return new SignaturePolicyIdentifier(SignaturePolicyId.getInstance(obj));
        else
            return null;
    }

    public SignaturePolicyIdentifier()
    {
        isSignaturePolicyImplied = true;
    }

    public SignaturePolicyIdentifier(SignaturePolicyId signaturePolicyId)
    {
        this.signaturePolicyId = signaturePolicyId;
        isSignaturePolicyImplied = false;
    }

    public SignaturePolicyId getSignaturePolicyId()
    {
        return signaturePolicyId;
    }

    public boolean isSignaturePolicyImplied()
    {
        return isSignaturePolicyImplied;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(isSignaturePolicyImplied)
            return DERNull.INSTANCE;
        else
            return signaturePolicyId.toASN1Primitive();
    }

    private SignaturePolicyId signaturePolicyId;
    private boolean isSignaturePolicyImplied;
}
