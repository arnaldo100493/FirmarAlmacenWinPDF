// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DVCSRequestInformation.java

package co.org.bouncy.asn1.dvcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.*;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.asn1.dvcs:
//            ServiceType, DVCSTime

public class DVCSRequestInformation extends ASN1Object
{

    private DVCSRequestInformation(ASN1Sequence seq)
    {
        version = 1;
        int i = 0;
        if(seq.getObjectAt(0) instanceof ASN1Integer)
        {
            ASN1Integer encVersion = ASN1Integer.getInstance(seq.getObjectAt(i++));
            version = encVersion.getValue().intValue();
        } else
        {
            version = 1;
        }
        service = ServiceType.getInstance(seq.getObjectAt(i++));
        for(; i < seq.size(); i++)
        {
            ASN1Encodable x = seq.getObjectAt(i);
            if(x instanceof ASN1Integer)
            {
                nonce = ASN1Integer.getInstance(x).getValue();
                continue;
            }
            if(x instanceof ASN1GeneralizedTime)
            {
                requestTime = DVCSTime.getInstance(x);
                continue;
            }
            if(x instanceof ASN1TaggedObject)
            {
                ASN1TaggedObject t = ASN1TaggedObject.getInstance(x);
                int tagNo = t.getTagNo();
                switch(tagNo)
                {
                case 0: // '\0'
                    requester = GeneralNames.getInstance(t, false);
                    break;

                case 1: // '\001'
                    requestPolicy = PolicyInformation.getInstance(ASN1Sequence.getInstance(t, false));
                    break;

                case 2: // '\002'
                    dvcs = GeneralNames.getInstance(t, false);
                    break;

                case 3: // '\003'
                    dataLocations = GeneralNames.getInstance(t, false);
                    break;

                case 4: // '\004'
                    extensions = Extensions.getInstance(t, false);
                    break;
                }
            } else
            {
                requestTime = DVCSTime.getInstance(x);
            }
        }

    }

    public static DVCSRequestInformation getInstance(Object obj)
    {
        if(obj instanceof DVCSRequestInformation)
            return (DVCSRequestInformation)obj;
        if(obj != null)
            return new DVCSRequestInformation(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public static DVCSRequestInformation getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(version != 1)
            v.add(new ASN1Integer(version));
        v.add(service);
        if(nonce != null)
            v.add(new ASN1Integer(nonce));
        if(requestTime != null)
            v.add(requestTime);
        int tags[] = {
            0, 1, 2, 3, 4
        };
        ASN1Encodable taggedObjects[] = {
            requester, requestPolicy, dvcs, dataLocations, extensions
        };
        for(int i = 0; i < tags.length; i++)
        {
            int tag = tags[i];
            ASN1Encodable taggedObject = taggedObjects[i];
            if(taggedObject != null)
                v.add(new DERTaggedObject(false, tag, taggedObject));
        }

        return new DERSequence(v);
    }

    public String toString()
    {
        StringBuffer s = new StringBuffer();
        s.append("DVCSRequestInformation {\n");
        if(version != 1)
            s.append((new StringBuilder()).append("version: ").append(version).append("\n").toString());
        s.append((new StringBuilder()).append("service: ").append(service).append("\n").toString());
        if(nonce != null)
            s.append((new StringBuilder()).append("nonce: ").append(nonce).append("\n").toString());
        if(requestTime != null)
            s.append((new StringBuilder()).append("requestTime: ").append(requestTime).append("\n").toString());
        if(requester != null)
            s.append((new StringBuilder()).append("requester: ").append(requester).append("\n").toString());
        if(requestPolicy != null)
            s.append((new StringBuilder()).append("requestPolicy: ").append(requestPolicy).append("\n").toString());
        if(dvcs != null)
            s.append((new StringBuilder()).append("dvcs: ").append(dvcs).append("\n").toString());
        if(dataLocations != null)
            s.append((new StringBuilder()).append("dataLocations: ").append(dataLocations).append("\n").toString());
        if(extensions != null)
            s.append((new StringBuilder()).append("extensions: ").append(extensions).append("\n").toString());
        s.append("}\n");
        return s.toString();
    }

    public int getVersion()
    {
        return version;
    }

    public ServiceType getService()
    {
        return service;
    }

    public BigInteger getNonce()
    {
        return nonce;
    }

    public DVCSTime getRequestTime()
    {
        return requestTime;
    }

    public GeneralNames getRequester()
    {
        return requester;
    }

    public PolicyInformation getRequestPolicy()
    {
        return requestPolicy;
    }

    public GeneralNames getDVCS()
    {
        return dvcs;
    }

    public GeneralNames getDataLocations()
    {
        return dataLocations;
    }

    public Extensions getExtensions()
    {
        return extensions;
    }

    private int version;
    private ServiceType service;
    private BigInteger nonce;
    private DVCSTime requestTime;
    private GeneralNames requester;
    private PolicyInformation requestPolicy;
    private GeneralNames dvcs;
    private GeneralNames dataLocations;
    private Extensions extensions;
    private static final int DEFAULT_VERSION = 1;
    private static final int TAG_REQUESTER = 0;
    private static final int TAG_REQUEST_POLICY = 1;
    private static final int TAG_DVCS = 2;
    private static final int TAG_DATA_LOCATIONS = 3;
    private static final int TAG_EXTENSIONS = 4;
}
