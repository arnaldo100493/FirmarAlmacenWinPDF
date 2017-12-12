// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AlgorithmIdentifier.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

public class AlgorithmIdentifier extends ASN1Object
{

    public static AlgorithmIdentifier getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static AlgorithmIdentifier getInstance(Object obj)
    {
        if(obj == null || (obj instanceof AlgorithmIdentifier))
            return (AlgorithmIdentifier)obj;
        if(obj instanceof ASN1ObjectIdentifier)
            return new AlgorithmIdentifier((ASN1ObjectIdentifier)obj);
        if(obj instanceof String)
            return new AlgorithmIdentifier((String)obj);
        else
            return new AlgorithmIdentifier(ASN1Sequence.getInstance(obj));
    }

    public AlgorithmIdentifier(ASN1ObjectIdentifier objectId)
    {
        parametersDefined = false;
        this.objectId = objectId;
    }

    /**
     * @deprecated Method AlgorithmIdentifier is deprecated
     */

    public AlgorithmIdentifier(String objectId)
    {
        parametersDefined = false;
        this.objectId = new ASN1ObjectIdentifier(objectId);
    }

    /**
     * @deprecated Method AlgorithmIdentifier is deprecated
     */

    public AlgorithmIdentifier(DERObjectIdentifier objectId)
    {
        parametersDefined = false;
        this.objectId = new ASN1ObjectIdentifier(objectId.getId());
    }

    /**
     * @deprecated Method AlgorithmIdentifier is deprecated
     */

    public AlgorithmIdentifier(DERObjectIdentifier objectId, ASN1Encodable parameters)
    {
        parametersDefined = false;
        parametersDefined = true;
        this.objectId = new ASN1ObjectIdentifier(objectId.getId());
        this.parameters = parameters;
    }

    public AlgorithmIdentifier(ASN1ObjectIdentifier objectId, ASN1Encodable parameters)
    {
        parametersDefined = false;
        parametersDefined = true;
        this.objectId = objectId;
        this.parameters = parameters;
    }

    /**
     * @deprecated Method AlgorithmIdentifier is deprecated
     */

    public AlgorithmIdentifier(ASN1Sequence seq)
    {
        parametersDefined = false;
        if(seq.size() < 1 || seq.size() > 2)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        objectId = ASN1ObjectIdentifier.getInstance(seq.getObjectAt(0));
        if(seq.size() == 2)
        {
            parametersDefined = true;
            parameters = seq.getObjectAt(1);
        } else
        {
            parameters = null;
        }
    }

    public ASN1ObjectIdentifier getAlgorithm()
    {
        return new ASN1ObjectIdentifier(objectId.getId());
    }

    /**
     * @deprecated Method getObjectId is deprecated
     */

    public ASN1ObjectIdentifier getObjectId()
    {
        return objectId;
    }

    public ASN1Encodable getParameters()
    {
        return parameters;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(objectId);
        if(parametersDefined)
            if(parameters != null)
                v.add(parameters);
            else
                v.add(DERNull.INSTANCE);
        return new DERSequence(v);
    }

    private ASN1ObjectIdentifier objectId;
    private ASN1Encodable parameters;
    private boolean parametersDefined;
}
