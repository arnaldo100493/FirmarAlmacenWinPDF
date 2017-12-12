// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1StreamParser.java

package co.org.bouncy.asn1;

import java.io.*;

// Referenced classes of package co.org.bouncy.asn1:
//            DERExternalParser, BEROctetStringParser, BERSequenceParser, BERSetParser, 
//            ASN1Exception, IndefiniteLengthInputStream, DERSetParser, DERSequenceParser, 
//            DEROctetStringParser, DefiniteLengthInputStream, DERTaggedObject, DEROctetString, 
//            BERTaggedObject, BERApplicationSpecificParser, BERTaggedObjectParser, DERApplicationSpecific, 
//            ASN1EncodableVector, InMemoryRepresentable, ASN1Encodable, StreamUtil, 
//            BERFactory, DERFactory, ASN1InputStream, ASN1Primitive

public class ASN1StreamParser
{

    public ASN1StreamParser(InputStream in)
    {
        this(in, StreamUtil.findLimit(in));
    }

    public ASN1StreamParser(InputStream in, int limit)
    {
        _in = in;
        _limit = limit;
        tmpBuffers = new byte[11][];
    }

    public ASN1StreamParser(byte encoding[])
    {
        this(((InputStream) (new ByteArrayInputStream(encoding))), encoding.length);
    }

    ASN1Encodable readIndef(int tagValue)
        throws IOException
    {
        switch(tagValue)
        {
        case 8: // '\b'
            return new DERExternalParser(this);

        case 4: // '\004'
            return new BEROctetStringParser(this);

        case 16: // '\020'
            return new BERSequenceParser(this);

        case 17: // '\021'
            return new BERSetParser(this);
        }
        throw new ASN1Exception((new StringBuilder()).append("unknown BER object encountered: 0x").append(Integer.toHexString(tagValue)).toString());
    }

    ASN1Encodable readImplicit(boolean constructed, int tag)
        throws IOException
    {
        if(_in instanceof IndefiniteLengthInputStream)
            if(!constructed)
                throw new IOException("indefinite length primitive encoding encountered");
            else
                return readIndef(tag);
        if(constructed)
            switch(tag)
            {
            case 17: // '\021'
                return new DERSetParser(this);

            case 16: // '\020'
                return new DERSequenceParser(this);

            case 4: // '\004'
                return new BEROctetStringParser(this);
            }
        else
            switch(tag)
            {
            case 17: // '\021'
                throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");

            case 16: // '\020'
                throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");

            case 4: // '\004'
                return new DEROctetStringParser((DefiniteLengthInputStream)_in);
            }
        throw new RuntimeException("implicit tagging not implemented");
    }

    ASN1Primitive readTaggedObject(boolean constructed, int tag)
        throws IOException
    {
        if(!constructed)
        {
            DefiniteLengthInputStream defIn = (DefiniteLengthInputStream)_in;
            return new DERTaggedObject(false, tag, new DEROctetString(defIn.toByteArray()));
        }
        ASN1EncodableVector v = readVector();
        if(_in instanceof IndefiniteLengthInputStream)
            return v.size() != 1 ? new BERTaggedObject(false, tag, BERFactory.createSequence(v)) : new BERTaggedObject(true, tag, v.get(0));
        else
            return v.size() != 1 ? new DERTaggedObject(false, tag, DERFactory.createSequence(v)) : new DERTaggedObject(true, tag, v.get(0));
    }

    public ASN1Encodable readObject()
        throws IOException
    {
        int tag = _in.read();
        if(tag == -1)
            return null;
        set00Check(false);
        int tagNo = ASN1InputStream.readTagNumber(_in, tag);
        boolean isConstructed = (tag & 0x20) != 0;
        int length = ASN1InputStream.readLength(_in, _limit);
        if(length < 0)
        {
            if(!isConstructed)
                throw new IOException("indefinite length primitive encoding encountered");
            IndefiniteLengthInputStream indIn = new IndefiniteLengthInputStream(_in, _limit);
            ASN1StreamParser sp = new ASN1StreamParser(indIn, _limit);
            if((tag & 0x40) != 0)
                return new BERApplicationSpecificParser(tagNo, sp);
            if((tag & 0x80) != 0)
                return new BERTaggedObjectParser(true, tagNo, sp);
            else
                return sp.readIndef(tagNo);
        }
        DefiniteLengthInputStream defIn = new DefiniteLengthInputStream(_in, length);
        if((tag & 0x40) != 0)
            return new DERApplicationSpecific(isConstructed, tagNo, defIn.toByteArray());
        if((tag & 0x80) != 0)
            return new BERTaggedObjectParser(isConstructed, tagNo, new ASN1StreamParser(defIn));
        if(isConstructed)
        {
            switch(tagNo)
            {
            case 4: // '\004'
                return new BEROctetStringParser(new ASN1StreamParser(defIn));

            case 16: // '\020'
                return new DERSequenceParser(new ASN1StreamParser(defIn));

            case 17: // '\021'
                return new DERSetParser(new ASN1StreamParser(defIn));

            case 8: // '\b'
                return new DERExternalParser(new ASN1StreamParser(defIn));
            }
            throw new IOException((new StringBuilder()).append("unknown tag ").append(tagNo).append(" encountered").toString());
        }
        switch(tagNo)
        {
        case 4: // '\004'
            return new DEROctetStringParser(defIn);
        }
        try
        {
            return ASN1InputStream.createPrimitiveDERObject(tagNo, defIn, tmpBuffers);
        }
        catch(IllegalArgumentException e)
        {
            throw new ASN1Exception("corrupted stream detected", e);
        }
    }

    private void set00Check(boolean enabled)
    {
        if(_in instanceof IndefiniteLengthInputStream)
            ((IndefiniteLengthInputStream)_in).setEofOn00(enabled);
    }

    ASN1EncodableVector readVector()
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        ASN1Encodable obj;
        while((obj = readObject()) != null) 
            if(obj instanceof InMemoryRepresentable)
                v.add(((InMemoryRepresentable)obj).getLoadedObject());
            else
                v.add(obj.toASN1Primitive());
        return v;
    }

    private final InputStream _in;
    private final int _limit;
    private final byte tmpBuffers[][];
}
