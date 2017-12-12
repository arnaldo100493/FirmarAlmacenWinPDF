// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERExternal.java

package co.org.bouncy.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1ObjectIdentifier, ASN1Integer, DERTaggedObject, 
//            ASN1EncodableVector, ASN1Encodable, ASN1OutputStream

public class DERExternal extends ASN1Primitive
{

    public DERExternal(ASN1EncodableVector vector)
    {
        int offset = 0;
        ASN1Primitive enc = getObjFromVector(vector, offset);
        if(enc instanceof ASN1ObjectIdentifier)
        {
            directReference = (ASN1ObjectIdentifier)enc;
            offset++;
            enc = getObjFromVector(vector, offset);
        }
        if(enc instanceof ASN1Integer)
        {
            indirectReference = (ASN1Integer)enc;
            offset++;
            enc = getObjFromVector(vector, offset);
        }
        if(!(enc instanceof DERTaggedObject))
        {
            dataValueDescriptor = enc;
            offset++;
            enc = getObjFromVector(vector, offset);
        }
        if(vector.size() != offset + 1)
            throw new IllegalArgumentException("input vector too large");
        if(!(enc instanceof DERTaggedObject))
        {
            throw new IllegalArgumentException("No tagged object found in vector. Structure doesn't seem to be of type External");
        } else
        {
            DERTaggedObject obj = (DERTaggedObject)enc;
            setEncoding(obj.getTagNo());
            externalContent = obj.getObject();
            return;
        }
    }

    private ASN1Primitive getObjFromVector(ASN1EncodableVector v, int index)
    {
        if(v.size() <= index)
            throw new IllegalArgumentException("too few objects in input vector");
        else
            return v.get(index).toASN1Primitive();
    }

    public DERExternal(ASN1ObjectIdentifier directReference, ASN1Integer indirectReference, ASN1Primitive dataValueDescriptor, DERTaggedObject externalData)
    {
        this(directReference, indirectReference, dataValueDescriptor, externalData.getTagNo(), externalData.toASN1Primitive());
    }

    public DERExternal(ASN1ObjectIdentifier directReference, ASN1Integer indirectReference, ASN1Primitive dataValueDescriptor, int encoding, ASN1Primitive externalData)
    {
        setDirectReference(directReference);
        setIndirectReference(indirectReference);
        setDataValueDescriptor(dataValueDescriptor);
        setEncoding(encoding);
        setExternalContent(externalData.toASN1Primitive());
    }

    public int hashCode()
    {
        int ret = 0;
        if(directReference != null)
            ret = directReference.hashCode();
        if(indirectReference != null)
            ret ^= indirectReference.hashCode();
        if(dataValueDescriptor != null)
            ret ^= dataValueDescriptor.hashCode();
        ret ^= externalContent.hashCode();
        return ret;
    }

    boolean isConstructed()
    {
        return true;
    }

    int encodedLength()
        throws IOException
    {
        return getEncoded().length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if(directReference != null)
            baos.write(directReference.getEncoded("DER"));
        if(indirectReference != null)
            baos.write(indirectReference.getEncoded("DER"));
        if(dataValueDescriptor != null)
            baos.write(dataValueDescriptor.getEncoded("DER"));
        DERTaggedObject obj = new DERTaggedObject(true, encoding, externalContent);
        baos.write(obj.getEncoded("DER"));
        out.writeEncoded(32, 8, baos.toByteArray());
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERExternal))
            return false;
        if(this == o)
            return true;
        DERExternal other = (DERExternal)o;
        if(directReference != null && (other.directReference == null || !other.directReference.equals(directReference)))
            return false;
        if(indirectReference != null && (other.indirectReference == null || !other.indirectReference.equals(indirectReference)))
            return false;
        if(dataValueDescriptor != null && (other.dataValueDescriptor == null || !other.dataValueDescriptor.equals(dataValueDescriptor)))
            return false;
        else
            return externalContent.equals(other.externalContent);
    }

    public ASN1Primitive getDataValueDescriptor()
    {
        return dataValueDescriptor;
    }

    public ASN1ObjectIdentifier getDirectReference()
    {
        return directReference;
    }

    public int getEncoding()
    {
        return encoding;
    }

    public ASN1Primitive getExternalContent()
    {
        return externalContent;
    }

    public ASN1Integer getIndirectReference()
    {
        return indirectReference;
    }

    private void setDataValueDescriptor(ASN1Primitive dataValueDescriptor)
    {
        this.dataValueDescriptor = dataValueDescriptor;
    }

    private void setDirectReference(ASN1ObjectIdentifier directReferemce)
    {
        directReference = directReferemce;
    }

    private void setEncoding(int encoding)
    {
        if(encoding < 0 || encoding > 2)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("invalid encoding value: ").append(encoding).toString());
        } else
        {
            this.encoding = encoding;
            return;
        }
    }

    private void setExternalContent(ASN1Primitive externalContent)
    {
        this.externalContent = externalContent;
    }

    private void setIndirectReference(ASN1Integer indirectReference)
    {
        this.indirectReference = indirectReference;
    }

    private ASN1ObjectIdentifier directReference;
    private ASN1Integer indirectReference;
    private ASN1Primitive dataValueDescriptor;
    private int encoding;
    private ASN1Primitive externalContent;
}
