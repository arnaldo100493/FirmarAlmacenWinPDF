// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SMIMECapabilities.java

package co.org.bouncy.asn1.smime;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.Attribute;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1.smime:
//            SMIMECapability

public class SMIMECapabilities extends ASN1Object
{

    public static SMIMECapabilities getInstance(Object o)
    {
        if(o == null || (o instanceof SMIMECapabilities))
            return (SMIMECapabilities)o;
        if(o instanceof ASN1Sequence)
            return new SMIMECapabilities((ASN1Sequence)o);
        if(o instanceof Attribute)
            return new SMIMECapabilities((ASN1Sequence)(ASN1Sequence)((Attribute)o).getAttrValues().getObjectAt(0));
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in factory: ").append(o.getClass().getName()).toString());
    }

    public SMIMECapabilities(ASN1Sequence seq)
    {
        capabilities = seq;
    }

    public Vector getCapabilities(ASN1ObjectIdentifier capability)
    {
        Enumeration e = capabilities.getObjects();
        Vector list = new Vector();
        if(capability == null)
        {
            SMIMECapability cap;
            for(; e.hasMoreElements(); list.addElement(cap))
                cap = SMIMECapability.getInstance(e.nextElement());

        } else
        {
            do
            {
                if(!e.hasMoreElements())
                    break;
                SMIMECapability cap = SMIMECapability.getInstance(e.nextElement());
                if(capability.equals(cap.getCapabilityID()))
                    list.addElement(cap);
            } while(true);
        }
        return list;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return capabilities;
    }

    public static final ASN1ObjectIdentifier preferSignedData;
    public static final ASN1ObjectIdentifier canNotDecryptAny;
    public static final ASN1ObjectIdentifier sMIMECapabilitesVersions;
    public static final ASN1ObjectIdentifier dES_CBC = new ASN1ObjectIdentifier("1.3.14.3.2.7");
    public static final ASN1ObjectIdentifier dES_EDE3_CBC;
    public static final ASN1ObjectIdentifier rC2_CBC;
    private ASN1Sequence capabilities;

    static 
    {
        preferSignedData = PKCSObjectIdentifiers.preferSignedData;
        canNotDecryptAny = PKCSObjectIdentifiers.canNotDecryptAny;
        sMIMECapabilitesVersions = PKCSObjectIdentifiers.sMIMECapabilitiesVersions;
        dES_EDE3_CBC = PKCSObjectIdentifiers.des_EDE3_CBC;
        rC2_CBC = PKCSObjectIdentifiers.RC2_CBC;
    }
}
