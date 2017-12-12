// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BERTaggedObject.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1TaggedObject, BERSequence, ASN1OctetString, BEROctetString, 
//            ASN1Sequence, ASN1Set, ASN1Encodable, ASN1Primitive, 
//            StreamUtil, ASN1OutputStream

public class BERTaggedObject extends ASN1TaggedObject
{

    public BERTaggedObject(int tagNo, ASN1Encodable obj)
    {
        super(true, tagNo, obj);
    }

    public BERTaggedObject(boolean explicit, int tagNo, ASN1Encodable obj)
    {
        super(explicit, tagNo, obj);
    }

    public BERTaggedObject(int tagNo)
    {
        super(false, tagNo, new BERSequence());
    }

    boolean isConstructed()
    {
        if(!empty)
        {
            if(explicit)
            {
                return true;
            } else
            {
                ASN1Primitive primitive = obj.toASN1Primitive().toDERObject();
                return primitive.isConstructed();
            }
        } else
        {
            return true;
        }
    }

    int encodedLength()
        throws IOException
    {
        if(!empty)
        {
            ASN1Primitive primitive = obj.toASN1Primitive();
            int length = primitive.encodedLength();
            if(explicit)
            {
                return StreamUtil.calculateTagLength(tagNo) + StreamUtil.calculateBodyLength(length) + length;
            } else
            {
                length--;
                return StreamUtil.calculateTagLength(tagNo) + length;
            }
        } else
        {
            return StreamUtil.calculateTagLength(tagNo) + 1;
        }
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        out.writeTag(160, tagNo);
        out.write(128);
        if(!empty)
            if(!explicit)
            {
                Enumeration e;
                if(obj instanceof ASN1OctetString)
                {
                    if(obj instanceof BEROctetString)
                    {
                        e = ((BEROctetString)obj).getObjects();
                    } else
                    {
                        ASN1OctetString octs = (ASN1OctetString)obj;
                        BEROctetString berO = new BEROctetString(octs.getOctets());
                        e = berO.getObjects();
                    }
                } else
                if(obj instanceof ASN1Sequence)
                    e = ((ASN1Sequence)obj).getObjects();
                else
                if(obj instanceof ASN1Set)
                    e = ((ASN1Set)obj).getObjects();
                else
                    throw new RuntimeException((new StringBuilder()).append("not implemented: ").append(obj.getClass().getName()).toString());
                for(; e.hasMoreElements(); out.writeObject((ASN1Encodable)e.nextElement()));
            } else
            {
                out.writeObject(obj);
            }
        out.write(0);
        out.write(0);
    }
}
