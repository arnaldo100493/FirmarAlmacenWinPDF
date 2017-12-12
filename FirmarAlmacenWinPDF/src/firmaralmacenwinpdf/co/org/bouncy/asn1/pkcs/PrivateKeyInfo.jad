// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PrivateKeyInfo.java

package co.org.bouncy.asn1.pkcs;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Enumeration;

public class PrivateKeyInfo extends ASN1Object
{

    public static PrivateKeyInfo getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        return getInstance(ASN1Sequence.getInstance(obj, explicit));
    }

    public static PrivateKeyInfo getInstance(Object obj)
    {
        if(obj instanceof PrivateKeyInfo)
            return (PrivateKeyInfo)obj;
        if(obj != null)
            return new PrivateKeyInfo(ASN1Sequence.getInstance(obj));
        else
            return null;
    }

    public PrivateKeyInfo(AlgorithmIdentifier algId, ASN1Encodable privateKey)
        throws IOException
    {
        this(algId, privateKey, null);
    }

    public PrivateKeyInfo(AlgorithmIdentifier algId, ASN1Encodable privateKey, ASN1Set attributes)
        throws IOException
    {
        privKey = new DEROctetString(privateKey.toASN1Primitive().getEncoded("DER"));
        this.algId = algId;
        this.attributes = attributes;
    }

    public PrivateKeyInfo(ASN1Sequence seq)
    {
        Enumeration e = seq.getObjects();
        BigInteger version = ((ASN1Integer)e.nextElement()).getValue();
        if(version.intValue() != 0)
            throw new IllegalArgumentException("wrong version for private key info");
        algId = AlgorithmIdentifier.getInstance(e.nextElement());
        privKey = ASN1OctetString.getInstance(e.nextElement());
        if(e.hasMoreElements())
            attributes = ASN1Set.getInstance((ASN1TaggedObject)e.nextElement(), false);
    }

    public AlgorithmIdentifier getPrivateKeyAlgorithm()
    {
        return algId;
    }

    /**
     * @deprecated Method getAlgorithmId is deprecated
     */

    public AlgorithmIdentifier getAlgorithmId()
    {
        return algId;
    }

    public ASN1Encodable parsePrivateKey()
        throws IOException
    {
        return ASN1Primitive.fromByteArray(privKey.getOctets());
    }

    /**
     * @deprecated Method getPrivateKey is deprecated
     */

    public ASN1Primitive getPrivateKey()
    {
        try
        {
            return parsePrivateKey().toASN1Primitive();
        }
        catch(IOException e)
        {
            throw new IllegalStateException("unable to parse private key");
        }
    }

    public ASN1Set getAttributes()
    {
        return attributes;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(new ASN1Integer(0L));
        v.add(algId);
        v.add(privKey);
        if(attributes != null)
            v.add(new DERTaggedObject(false, 0, attributes));
        return new DERSequence(v);
    }

    private ASN1OctetString privKey;
    private AlgorithmIdentifier algId;
    private ASN1Set attributes;
}
