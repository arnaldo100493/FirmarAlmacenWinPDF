// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1InputStream.java

package co.org.bouncy.asn1;

import co.org.bouncy.util.io.Streams;
import java.io.*;

// Referenced classes of package co.org.bouncy.asn1:
//            DefiniteLengthInputStream, DERApplicationSpecific, ASN1StreamParser, ASN1OctetString, 
//            BEROctetString, LazyEncodedSequence, DERExternal, ASN1EncodableVector, 
//            IndefiniteLengthInputStream, BERApplicationSpecificParser, BERTaggedObjectParser, BEROctetStringParser, 
//            BERSequenceParser, BERSetParser, DERExternalParser, ASN1Exception, 
//            DERBMPString, ASN1GeneralizedTime, DERGeneralString, DERIA5String, 
//            ASN1Integer, DERNumericString, DEROctetString, DERPrintableString, 
//            DERT61String, DERUniversalString, ASN1UTCTime, DERUTF8String, 
//            DERVisibleString, BERTags, ASN1Primitive, StreamUtil, 
//            DERFactory, DERBitString, ASN1Boolean, ASN1Enumerated, 
//            DERNull, ASN1ObjectIdentifier

public class ASN1InputStream extends FilterInputStream
    implements BERTags
{

    public ASN1InputStream(InputStream is)
    {
        this(is, StreamUtil.findLimit(is));
    }

    public ASN1InputStream(byte input[])
    {
        this(((InputStream) (new ByteArrayInputStream(input))), input.length);
    }

    public ASN1InputStream(byte input[], boolean lazyEvaluate)
    {
        this(((InputStream) (new ByteArrayInputStream(input))), input.length, lazyEvaluate);
    }

    public ASN1InputStream(InputStream input, int limit)
    {
        this(input, limit, false);
    }

    public ASN1InputStream(InputStream input, boolean lazyEvaluate)
    {
        this(input, StreamUtil.findLimit(input), lazyEvaluate);
    }

    public ASN1InputStream(InputStream input, int limit, boolean lazyEvaluate)
    {
        super(input);
        this.limit = limit;
        this.lazyEvaluate = lazyEvaluate;
        tmpBuffers = new byte[11][];
    }

    int getLimit()
    {
        return limit;
    }

    protected int readLength()
        throws IOException
    {
        return readLength(((InputStream) (this)), limit);
    }

    protected void readFully(byte bytes[])
        throws IOException
    {
        if(Streams.readFully(this, bytes) != bytes.length)
            throw new EOFException("EOF encountered in middle of object");
        else
            return;
    }

    protected ASN1Primitive buildObject(int tag, int tagNo, int length)
        throws IOException
    {
        boolean isConstructed = (tag & 0x20) != 0;
        DefiniteLengthInputStream defIn = new DefiniteLengthInputStream(this, length);
        if((tag & 0x40) != 0)
            return new DERApplicationSpecific(isConstructed, tagNo, defIn.toByteArray());
        if((tag & 0x80) != 0)
            return (new ASN1StreamParser(defIn)).readTaggedObject(isConstructed, tagNo);
        if(isConstructed)
        {
            switch(tagNo)
            {
            case 4: // '\004'
                ASN1EncodableVector v = buildDEREncodableVector(defIn);
                ASN1OctetString strings[] = new ASN1OctetString[v.size()];
                for(int i = 0; i != strings.length; i++)
                    strings[i] = (ASN1OctetString)v.get(i);

                return new BEROctetString(strings);

            case 16: // '\020'
                if(lazyEvaluate)
                    return new LazyEncodedSequence(defIn.toByteArray());
                else
                    return DERFactory.createSequence(buildDEREncodableVector(defIn));

            case 17: // '\021'
                return DERFactory.createSet(buildDEREncodableVector(defIn));

            case 8: // '\b'
                return new DERExternal(buildDEREncodableVector(defIn));
            }
            throw new IOException((new StringBuilder()).append("unknown tag ").append(tagNo).append(" encountered").toString());
        } else
        {
            return createPrimitiveDERObject(tagNo, defIn, tmpBuffers);
        }
    }

    ASN1EncodableVector buildEncodableVector()
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        ASN1Primitive o;
        while((o = readObject()) != null) 
            v.add(o);
        return v;
    }

    ASN1EncodableVector buildDEREncodableVector(DefiniteLengthInputStream dIn)
        throws IOException
    {
        return (new ASN1InputStream(dIn)).buildEncodableVector();
    }

    public ASN1Primitive readObject()
        throws IOException
    {
        int tag = read();
        if(tag <= 0)
            if(tag == 0)
                throw new IOException("unexpected end-of-contents marker");
            else
                return null;
        int tagNo = readTagNumber(this, tag);
        boolean isConstructed = (tag & 0x20) != 0;
        int length = readLength();
        if(length < 0)
        {
            if(!isConstructed)
                throw new IOException("indefinite length primitive encoding encountered");
            IndefiniteLengthInputStream indIn = new IndefiniteLengthInputStream(this, limit);
            ASN1StreamParser sp = new ASN1StreamParser(indIn, limit);
            if((tag & 0x40) != 0)
                return (new BERApplicationSpecificParser(tagNo, sp)).getLoadedObject();
            if((tag & 0x80) != 0)
                return (new BERTaggedObjectParser(true, tagNo, sp)).getLoadedObject();
            switch(tagNo)
            {
            case 4: // '\004'
                return (new BEROctetStringParser(sp)).getLoadedObject();

            case 16: // '\020'
                return (new BERSequenceParser(sp)).getLoadedObject();

            case 17: // '\021'
                return (new BERSetParser(sp)).getLoadedObject();

            case 8: // '\b'
                return (new DERExternalParser(sp)).getLoadedObject();
            }
            throw new IOException("unknown BER object encountered");
        }
        try
        {
            return buildObject(tag, tagNo, length);
        }
        catch(IllegalArgumentException e)
        {
            throw new ASN1Exception("corrupted stream detected", e);
        }
    }

    static int readTagNumber(InputStream s, int tag)
        throws IOException
    {
        int tagNo = tag & 0x1f;
        if(tagNo == 31)
        {
            tagNo = 0;
            int b = s.read();
            if((b & 0x7f) == 0)
                throw new IOException("corrupted stream - invalid high tag number found");
            for(; b >= 0 && (b & 0x80) != 0; b = s.read())
            {
                tagNo |= b & 0x7f;
                tagNo <<= 7;
            }

            if(b < 0)
                throw new EOFException("EOF found inside tag value.");
            tagNo |= b & 0x7f;
        }
        return tagNo;
    }

    static int readLength(InputStream s, int limit)
        throws IOException
    {
        int length = s.read();
        if(length < 0)
            throw new EOFException("EOF found when length expected");
        if(length == 128)
            return -1;
        if(length > 127)
        {
            int size = length & 0x7f;
            if(size > 4)
                throw new IOException((new StringBuilder()).append("DER length more than 4 bytes: ").append(size).toString());
            length = 0;
            for(int i = 0; i < size; i++)
            {
                int next = s.read();
                if(next < 0)
                    throw new EOFException("EOF found reading length");
                length = (length << 8) + next;
            }

            if(length < 0)
                throw new IOException("corrupted stream - negative length found");
            if(length >= limit)
                throw new IOException("corrupted stream - out of bounds length found");
        }
        return length;
    }

    private static byte[] getBuffer(DefiniteLengthInputStream defIn, byte tmpBuffers[][])
        throws IOException
    {
        int len = defIn.getRemaining();
        if(defIn.getRemaining() < tmpBuffers.length)
        {
            byte buf[] = tmpBuffers[len];
            if(buf == null)
                buf = tmpBuffers[len] = new byte[len];
            Streams.readFully(defIn, buf);
            return buf;
        } else
        {
            return defIn.toByteArray();
        }
    }

    private static char[] getBMPCharBuffer(DefiniteLengthInputStream defIn)
        throws IOException
    {
        int len = defIn.getRemaining() / 2;
        char buf[] = new char[len];
        int totalRead = 0;
        do
        {
            if(totalRead >= len)
                break;
            int ch1 = defIn.read();
            if(ch1 < 0)
                break;
            int ch2 = defIn.read();
            if(ch2 < 0)
                break;
            buf[totalRead++] = (char)(ch1 << 8 | ch2 & 0xff);
        } while(true);
        return buf;
    }

    static ASN1Primitive createPrimitiveDERObject(int tagNo, DefiniteLengthInputStream defIn, byte tmpBuffers[][])
        throws IOException
    {
        switch(tagNo)
        {
        case 3: // '\003'
            return DERBitString.fromInputStream(defIn.getRemaining(), defIn);

        case 30: // '\036'
            return new DERBMPString(getBMPCharBuffer(defIn));

        case 1: // '\001'
            return ASN1Boolean.fromOctetString(getBuffer(defIn, tmpBuffers));

        case 10: // '\n'
            return ASN1Enumerated.fromOctetString(getBuffer(defIn, tmpBuffers));

        case 24: // '\030'
            return new ASN1GeneralizedTime(defIn.toByteArray());

        case 27: // '\033'
            return new DERGeneralString(defIn.toByteArray());

        case 22: // '\026'
            return new DERIA5String(defIn.toByteArray());

        case 2: // '\002'
            return new ASN1Integer(defIn.toByteArray());

        case 5: // '\005'
            return DERNull.INSTANCE;

        case 18: // '\022'
            return new DERNumericString(defIn.toByteArray());

        case 6: // '\006'
            return ASN1ObjectIdentifier.fromOctetString(getBuffer(defIn, tmpBuffers));

        case 4: // '\004'
            return new DEROctetString(defIn.toByteArray());

        case 19: // '\023'
            return new DERPrintableString(defIn.toByteArray());

        case 20: // '\024'
            return new DERT61String(defIn.toByteArray());

        case 28: // '\034'
            return new DERUniversalString(defIn.toByteArray());

        case 23: // '\027'
            return new ASN1UTCTime(defIn.toByteArray());

        case 12: // '\f'
            return new DERUTF8String(defIn.toByteArray());

        case 26: // '\032'
            return new DERVisibleString(defIn.toByteArray());

        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        case 11: // '\013'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        case 21: // '\025'
        case 25: // '\031'
        case 29: // '\035'
        default:
            throw new IOException((new StringBuilder()).append("unknown tag ").append(tagNo).append(" encountered").toString());
        }
    }

    private final int limit;
    private final boolean lazyEvaluate;
    private final byte tmpBuffers[][];
}
