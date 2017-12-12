// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1TaggedObject.java

package co.org.bouncy.asn1;

import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1Choice, ASN1Set, DERTaggedObject, 
//            DLTaggedObject, ASN1TaggedObjectParser, ASN1Encodable, ASN1Sequence, 
//            ASN1OctetString, ASN1OutputStream

public abstract class ASN1TaggedObject extends ASN1Primitive
    implements ASN1TaggedObjectParser
{

    public static ASN1TaggedObject getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        if(explicit)
            return (ASN1TaggedObject)obj.getObject();
        else
            throw new IllegalArgumentException("implicitly tagged tagged object");
    }

    public static ASN1TaggedObject getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ASN1TaggedObject))
            return (ASN1TaggedObject)obj;
        if(obj instanceof byte[])
            try
            {
                return getInstance(fromByteArray((byte[])(byte[])obj));
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("failed to construct tagged object from byte[]: ").append(e.getMessage()).toString());
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public ASN1TaggedObject(boolean explicit, int tagNo, ASN1Encodable obj)
    {
        empty = false;
        this.explicit = true;
        this.obj = null;
        if(obj instanceof ASN1Choice)
            this.explicit = true;
        else
            this.explicit = explicit;
        this.tagNo = tagNo;
        if(this.explicit)
        {
            this.obj = obj;
        } else
        {
            ASN1Primitive prim = obj.toASN1Primitive();
            ASN1Set s;
            if(prim instanceof ASN1Set)
                s = null;
            this.obj = obj;
        }
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof ASN1TaggedObject))
            return false;
        ASN1TaggedObject other = (ASN1TaggedObject)o;
        if(tagNo != other.tagNo || empty != other.empty || explicit != other.explicit)
            return false;
        if(obj == null)
        {
            if(other.obj != null)
                return false;
        } else
        if(!obj.toASN1Primitive().equals(other.obj.toASN1Primitive()))
            return false;
        return true;
    }

    public int hashCode()
    {
        int code = tagNo;
        if(obj != null)
            code ^= obj.hashCode();
        return code;
    }

    public int getTagNo()
    {
        return tagNo;
    }

    public boolean isExplicit()
    {
        return explicit;
    }

    public boolean isEmpty()
    {
        return empty;
    }

    public ASN1Primitive getObject()
    {
        if(obj != null)
            return obj.toASN1Primitive();
        else
            return null;
    }

    public ASN1Encodable getObjectParser(int tag, boolean isExplicit)
    {
        switch(tag)
        {
        case 17: // '\021'
            return ASN1Set.getInstance(this, isExplicit).parser();

        case 16: // '\020'
            return ASN1Sequence.getInstance(this, isExplicit).parser();

        case 4: // '\004'
            return ASN1OctetString.getInstance(this, isExplicit).parser();
        }
        if(isExplicit)
            return getObject();
        else
            throw new RuntimeException((new StringBuilder()).append("implicit tagging not implemented for tag: ").append(tag).toString());
    }

    public ASN1Primitive getLoadedObject()
    {
        return toASN1Primitive();
    }

    ASN1Primitive toDERObject()
    {
        return new DERTaggedObject(explicit, tagNo, obj);
    }

    ASN1Primitive toDLObject()
    {
        return new DLTaggedObject(explicit, tagNo, obj);
    }

    abstract void encode(ASN1OutputStream asn1outputstream)
        throws IOException;

    public String toString()
    {
        return (new StringBuilder()).append("[").append(tagNo).append("]").append(obj).toString();
    }

    int tagNo;
    boolean empty;
    boolean explicit;
    ASN1Encodable obj;
}
