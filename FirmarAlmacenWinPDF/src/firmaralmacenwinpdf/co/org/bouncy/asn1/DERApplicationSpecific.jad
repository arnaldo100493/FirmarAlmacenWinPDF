// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERApplicationSpecific.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1Set, ASN1Sequence, ASN1Object, 
//            ASN1ParsingException, ASN1Encodable, ASN1InputStream, ASN1EncodableVector, 
//            StreamUtil, ASN1OutputStream

public class DERApplicationSpecific extends ASN1Primitive
{

    DERApplicationSpecific(boolean isConstructed, int tag, byte octets[])
    {
        this.isConstructed = isConstructed;
        this.tag = tag;
        this.octets = octets;
    }

    public DERApplicationSpecific(int tag, byte octets[])
    {
        this(false, tag, octets);
    }

    public DERApplicationSpecific(int tag, ASN1Encodable object)
        throws IOException
    {
        this(true, tag, object);
    }

    public DERApplicationSpecific(boolean explicit, int tag, ASN1Encodable object)
        throws IOException
    {
        ASN1Primitive primitive = object.toASN1Primitive();
        byte data[] = primitive.getEncoded("DER");
        isConstructed = explicit || (primitive instanceof ASN1Set) || (primitive instanceof ASN1Sequence);
        this.tag = tag;
        if(explicit)
        {
            octets = data;
        } else
        {
            int lenBytes = getLengthOfHeader(data);
            byte tmp[] = new byte[data.length - lenBytes];
            System.arraycopy(data, lenBytes, tmp, 0, tmp.length);
            octets = tmp;
        }
    }

    public DERApplicationSpecific(int tagNo, ASN1EncodableVector vec)
    {
        tag = tagNo;
        isConstructed = true;
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        for(int i = 0; i != vec.size(); i++)
            try
            {
                bOut.write(((ASN1Object)vec.get(i)).getEncoded("DER"));
            }
            catch(IOException e)
            {
                throw new ASN1ParsingException((new StringBuilder()).append("malformed object: ").append(e).toString(), e);
            }

        octets = bOut.toByteArray();
    }

    public static DERApplicationSpecific getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERApplicationSpecific))
            return (DERApplicationSpecific)obj;
        if(obj instanceof byte[])
            try
            {
                return getInstance(ASN1Primitive.fromByteArray((byte[])(byte[])obj));
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("failed to construct object from byte[]: ").append(e.getMessage()).toString());
            }
        if(obj instanceof ASN1Encodable)
        {
            ASN1Primitive primitive = ((ASN1Encodable)obj).toASN1Primitive();
            if(primitive instanceof ASN1Sequence)
                return (DERApplicationSpecific)primitive;
        }
        throw new IllegalArgumentException((new StringBuilder()).append("unknown object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    private int getLengthOfHeader(byte data[])
    {
        int length = data[1] & 0xff;
        if(length == 128)
            return 2;
        if(length > 127)
        {
            int size = length & 0x7f;
            if(size > 4)
                throw new IllegalStateException((new StringBuilder()).append("DER length more than 4 bytes: ").append(size).toString());
            else
                return size + 2;
        } else
        {
            return 2;
        }
    }

    public boolean isConstructed()
    {
        return isConstructed;
    }

    public byte[] getContents()
    {
        return octets;
    }

    public int getApplicationTag()
    {
        return tag;
    }

    public ASN1Primitive getObject()
        throws IOException
    {
        return (new ASN1InputStream(getContents())).readObject();
    }

    public ASN1Primitive getObject(int derTagNo)
        throws IOException
    {
        if(derTagNo >= 31)
            throw new IOException("unsupported tag number");
        byte orig[] = getEncoded();
        byte tmp[] = replaceTagNumber(derTagNo, orig);
        if((orig[0] & 0x20) != 0)
            tmp[0] |= 0x20;
        return (new ASN1InputStream(tmp)).readObject();
    }

    int encodedLength()
        throws IOException
    {
        return StreamUtil.calculateTagLength(tag) + StreamUtil.calculateBodyLength(octets.length) + octets.length;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        int classBits = 64;
        if(isConstructed)
            classBits |= 0x20;
        out.writeEncoded(classBits, tag, octets);
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERApplicationSpecific))
        {
            return false;
        } else
        {
            DERApplicationSpecific other = (DERApplicationSpecific)o;
            return isConstructed == other.isConstructed && tag == other.tag && Arrays.areEqual(octets, other.octets);
        }
    }

    public int hashCode()
    {
        return (isConstructed ? 1 : 0) ^ tag ^ Arrays.hashCode(octets);
    }

    private byte[] replaceTagNumber(int newTag, byte input[])
        throws IOException
    {
        int tagNo = input[0] & 0x1f;
        int index = 1;
        if(tagNo == 31)
        {
            tagNo = 0;
            int b = input[index++] & 0xff;
            if((b & 0x7f) == 0)
                throw new ASN1ParsingException("corrupted stream - invalid high tag number found");
            for(; b >= 0 && (b & 0x80) != 0; b = input[index++] & 0xff)
            {
                tagNo |= b & 0x7f;
                tagNo <<= 7;
            }

            tagNo |= b & 0x7f;
        }
        byte tmp[] = new byte[(input.length - index) + 1];
        System.arraycopy(input, index, tmp, 1, tmp.length - 1);
        tmp[0] = (byte)newTag;
        return tmp;
    }

    private final boolean isConstructed;
    private final int tag;
    private final byte octets[];
}
