// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DERBitString.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.Arrays;
import co.org.bouncy.util.io.Streams;
import java.io.*;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1OctetString, ASN1OutputStream, ASN1String, 
//            ASN1TaggedObject, ASN1Encodable, StreamUtil

public class DERBitString extends ASN1Primitive
    implements ASN1String
{

    protected static int getPadBits(int bitString)
    {
        int val = 0;
        for(int i = 3; i >= 0; i--)
        {
            if(i != 0)
            {
                if(bitString >> i * 8 == 0)
                    continue;
                val = bitString >> i * 8 & 0xff;
                break;
            }
            if(bitString == 0)
                continue;
            val = bitString & 0xff;
            break;
        }

        if(val == 0)
            return 7;
        int bits;
        for(bits = 1; ((val <<= 1) & 0xff) != 0; bits++);
        return 8 - bits;
    }

    protected static byte[] getBytes(int bitString)
    {
        int bytes = 4;
        for(int i = 3; i >= 1 && (bitString & 255 << i * 8) == 0; i--)
            bytes--;

        byte result[] = new byte[bytes];
        for(int i = 0; i < bytes; i++)
            result[i] = (byte)(bitString >> i * 8 & 0xff);

        return result;
    }

    public static DERBitString getInstance(Object obj)
    {
        if(obj == null || (obj instanceof DERBitString))
            return (DERBitString)obj;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static DERBitString getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        ASN1Primitive o = obj.getObject();
        if(explicit || (o instanceof DERBitString))
            return getInstance(o);
        else
            return fromOctetString(((ASN1OctetString)o).getOctets());
    }

    protected DERBitString(byte data, int padBits)
    {
        this.data = new byte[1];
        this.data[0] = data;
        this.padBits = padBits;
    }

    public DERBitString(byte data[], int padBits)
    {
        this.data = data;
        this.padBits = padBits;
    }

    public DERBitString(byte data[])
    {
        this(data, 0);
    }

    public DERBitString(int value)
    {
        data = getBytes(value);
        padBits = getPadBits(value);
    }

    public DERBitString(ASN1Encodable obj)
        throws IOException
    {
        data = obj.toASN1Primitive().getEncoded("DER");
        padBits = 0;
    }

    public byte[] getBytes()
    {
        return data;
    }

    public int getPadBits()
    {
        return padBits;
    }

    public int intValue()
    {
        int value = 0;
        for(int i = 0; i != data.length && i != 4; i++)
            value |= (data[i] & 0xff) << 8 * i;

        return value;
    }

    boolean isConstructed()
    {
        return false;
    }

    int encodedLength()
    {
        return 1 + StreamUtil.calculateBodyLength(data.length + 1) + data.length + 1;
    }

    void encode(ASN1OutputStream out)
        throws IOException
    {
        byte bytes[] = new byte[getBytes().length + 1];
        bytes[0] = (byte)getPadBits();
        System.arraycopy(getBytes(), 0, bytes, 1, bytes.length - 1);
        out.writeEncoded(3, bytes);
    }

    public int hashCode()
    {
        return padBits ^ Arrays.hashCode(data);
    }

    protected boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof DERBitString))
        {
            return false;
        } else
        {
            DERBitString other = (DERBitString)o;
            return padBits == other.padBits && Arrays.areEqual(data, other.data);
        }
    }

    public String getString()
    {
        StringBuffer buf = new StringBuffer("#");
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ASN1OutputStream aOut = new ASN1OutputStream(bOut);
        try
        {
            aOut.writeObject(this);
        }
        catch(IOException e)
        {
            throw new RuntimeException("internal error encoding BitString");
        }
        byte string[] = bOut.toByteArray();
        for(int i = 0; i != string.length; i++)
        {
            buf.append(table[string[i] >>> 4 & 0xf]);
            buf.append(table[string[i] & 0xf]);
        }

        return buf.toString();
    }

    public String toString()
    {
        return getString();
    }

    static DERBitString fromOctetString(byte bytes[])
    {
        if(bytes.length < 1)
            throw new IllegalArgumentException("truncated BIT STRING detected");
        int padBits = bytes[0];
        byte data[] = new byte[bytes.length - 1];
        if(data.length != 0)
            System.arraycopy(bytes, 1, data, 0, bytes.length - 1);
        return new DERBitString(data, padBits);
    }

    static DERBitString fromInputStream(int length, InputStream stream)
        throws IOException
    {
        if(length < 1)
            throw new IllegalArgumentException("truncated BIT STRING detected");
        int padBits = stream.read();
        byte data[] = new byte[length - 1];
        if(data.length != 0 && Streams.readFully(stream, data) != data.length)
            throw new EOFException("EOF encountered in middle of BIT STRING");
        else
            return new DERBitString(data, padBits);
    }

    private static final char table[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'A', 'B', 'C', 'D', 'E', 'F'
    };
    protected byte data[];
    protected int padBits;

}
