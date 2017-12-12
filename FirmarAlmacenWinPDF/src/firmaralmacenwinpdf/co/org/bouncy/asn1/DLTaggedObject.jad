// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DLTaggedObject.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1TaggedObject, ASN1Primitive, ASN1Encodable, StreamUtil, 
//            ASN1OutputStream

public class DLTaggedObject extends ASN1TaggedObject
{

    public DLTaggedObject(boolean explicit, int tagNo, ASN1Encodable obj)
    {
        super(explicit, tagNo, obj);
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
                ASN1Primitive primitive = obj.toASN1Primitive().toDLObject();
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
            int length = obj.toASN1Primitive().toDLObject().encodedLength();
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
        if(!empty)
        {
            ASN1Primitive primitive = obj.toASN1Primitive().toDLObject();
            if(explicit)
            {
                out.writeTag(160, tagNo);
                out.writeLength(primitive.encodedLength());
                out.writeObject(primitive);
            } else
            {
                int flags;
                if(primitive.isConstructed())
                    flags = 160;
                else
                    flags = 128;
                out.writeTag(flags, tagNo);
                out.writeImplicitObject(primitive);
            }
        } else
        {
            out.writeEncoded(160, tagNo, ZERO_BYTES);
        }
    }

    private static final byte ZERO_BYTES[] = new byte[0];

}
