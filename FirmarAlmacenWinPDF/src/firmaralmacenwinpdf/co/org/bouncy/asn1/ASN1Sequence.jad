// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1Sequence.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1SequenceParser, ASN1Encodable, BERTaggedObject, 
//            BERSequence, DLSequence, DERSequence, ASN1EncodableVector, 
//            ASN1TaggedObject, ASN1OutputStream, ASN1Set

public abstract class ASN1Sequence extends ASN1Primitive
{

    public static ASN1Sequence getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ASN1Sequence))
            return (ASN1Sequence)obj;
        if(obj instanceof ASN1SequenceParser)
            return getInstance(((ASN1SequenceParser)obj).toASN1Primitive());
        if(obj instanceof byte[])
            try
            {
                return getInstance(fromByteArray((byte[])(byte[])obj));
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("failed to construct sequence from byte[]: ").append(e.getMessage()).toString());
            }
        if(obj instanceof ASN1Encodable)
        {
            ASN1Primitive primitive = ((ASN1Encodable)obj).toASN1Primitive();
            if(primitive instanceof ASN1Sequence)
                return (ASN1Sequence)primitive;
        }
        throw new IllegalArgumentException((new StringBuilder()).append("unknown object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static ASN1Sequence getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        if(explicit)
            if(!obj.isExplicit())
                throw new IllegalArgumentException("object implicit - explicit expected.");
            else
                return getInstance(obj.getObject().toASN1Primitive());
        if(obj.isExplicit())
            if(obj instanceof BERTaggedObject)
                return new BERSequence(obj.getObject());
            else
                return new DLSequence(obj.getObject());
        if(obj.getObject() instanceof ASN1Sequence)
            return (ASN1Sequence)obj.getObject();
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    protected ASN1Sequence()
    {
        seq = new Vector();
    }

    protected ASN1Sequence(ASN1Encodable obj)
    {
        seq = new Vector();
        seq.addElement(obj);
    }

    protected ASN1Sequence(ASN1EncodableVector v)
    {
        seq = new Vector();
        for(int i = 0; i != v.size(); i++)
            seq.addElement(v.get(i));

    }

    protected ASN1Sequence(ASN1Encodable array[])
    {
        seq = new Vector();
        for(int i = 0; i != array.length; i++)
            seq.addElement(array[i]);

    }

    public ASN1Encodable[] toArray()
    {
        ASN1Encodable values[] = new ASN1Encodable[size()];
        for(int i = 0; i != size(); i++)
            values[i] = getObjectAt(i);

        return values;
    }

    public Enumeration getObjects()
    {
        return seq.elements();
    }

    public ASN1SequenceParser parser()
    {
        final ASN1Sequence outer = this;
        return new ASN1SequenceParser() {

            public ASN1Encodable readObject()
                throws IOException
            {
                if(index == max)
                    return null;
                ASN1Encodable obj = getObjectAt(index++);
                if(obj instanceof ASN1Sequence)
                    return ((ASN1Sequence)obj).parser();
                if(obj instanceof ASN1Set)
                    return ((ASN1Set)obj).parser();
                else
                    return obj;
            }

            public ASN1Primitive getLoadedObject()
            {
                return outer;
            }

            public ASN1Primitive toASN1Primitive()
            {
                return outer;
            }

            private final int max;
            private int index;
            final ASN1Sequence val$outer;
            final ASN1Sequence this$0;

            
            {
                this$0 = ASN1Sequence.this;
                outer = asn1sequence1;
                super();
                max = size();
            }
        }
;
    }

    public ASN1Encodable getObjectAt(int index)
    {
        return (ASN1Encodable)seq.elementAt(index);
    }

    public int size()
    {
        return seq.size();
    }

    public int hashCode()
    {
        Enumeration e = getObjects();
        int hashCode;
        Object o;
        for(hashCode = size(); e.hasMoreElements(); hashCode ^= o.hashCode())
        {
            o = getNext(e);
            hashCode *= 17;
        }

        return hashCode;
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof ASN1Sequence))
            return false;
        ASN1Sequence other = (ASN1Sequence)o;
        if(size() != other.size())
            return false;
        Enumeration s1 = getObjects();
        Enumeration s2 = other.getObjects();
        while(s1.hasMoreElements()) 
        {
            ASN1Encodable obj1 = getNext(s1);
            ASN1Encodable obj2 = getNext(s2);
            ASN1Primitive o1 = obj1.toASN1Primitive();
            ASN1Primitive o2 = obj2.toASN1Primitive();
            if(o1 != o2 && !o1.equals(o2))
                return false;
        }
        return true;
    }

    private ASN1Encodable getNext(Enumeration e)
    {
        ASN1Encodable encObj = (ASN1Encodable)e.nextElement();
        return encObj;
    }

    ASN1Primitive toDERObject()
    {
        ASN1Sequence derSeq = new DERSequence();
        derSeq.seq = seq;
        return derSeq;
    }

    ASN1Primitive toDLObject()
    {
        ASN1Sequence dlSeq = new DLSequence();
        dlSeq.seq = seq;
        return dlSeq;
    }

    boolean isConstructed()
    {
        return true;
    }

    abstract void encode(ASN1OutputStream asn1outputstream)
        throws IOException;

    public String toString()
    {
        return seq.toString();
    }

    protected Vector seq;
}
