// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMECapabilitiesAttribute.java

package co.org.bouncy.asn1.smime;

import co.org.bouncy.asn1.DERSequence;
import co.org.bouncy.asn1.DERSet;
import co.org.bouncy.asn1.cms.Attribute;

// Referenced classes of package co.org.bouncy.asn1.smime:
//            SMIMEAttributes, SMIMECapabilityVector

public class SMIMECapabilitiesAttribute extends Attribute
{

    public SMIMECapabilitiesAttribute(SMIMECapabilityVector capabilities)
    {
        super(SMIMEAttributes.smimeCapabilities, new DERSet(new DERSequence(capabilities.toASN1EncodableVector())));
    }
}
