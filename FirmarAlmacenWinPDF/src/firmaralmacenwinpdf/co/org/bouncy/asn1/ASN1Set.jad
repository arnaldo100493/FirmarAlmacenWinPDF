// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1Set.java

package co.org.bouncy.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1Primitive, ASN1SetParser, ASN1Encodable, BERTaggedObject, 
//            BERSet, DLSet, ASN1Sequence, DERSet, 
//            ASN1OutputStream, ASN1EncodableVector, ASN1TaggedObject, DERNull

public abstract class ASN1Set extends ASN1Primitive
{

    public static ASN1Set getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ASN1Set))
            return (ASN1Set)obj;
        if(obj instanceof ASN1SetParser)
            return getInstance(((ASN1SetParser)obj).toASN1Primitive());
        if(obj instanceof byte[])
            try
            {
                return getInstance(ASN1Primitive.fromByteArray((byte[])(byte[])obj));
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("failed to construct set from byte[]: ").append(e.getMessage()).toString());
            }
        if(obj instanceof ASN1Encodable)
        {
            ASN1Primitive primitive = ((ASN1Encodable)obj).toASN1Primitive();
            if(primitive instanceof ASN1Set)
                return (ASN1Set)primitive;
        }
        throw new IllegalArgumentException((new StringBuilder()).append("unknown object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static ASN1Set getInstance(ASN1TaggedObject obj, boolean explicit)
    {
        if(explicit)
            if(!obj.isExplicit())
                throw new IllegalArgumentException("object implicit - explicit expected.");
            else
                return (ASN1Set)obj.getObject();
        if(obj.isExplicit())
            if(obj instanceof BERTaggedObject)
                return new BERSet(obj.getObject());
            else
                return new DLSet(obj.getObject());
        if(obj.getObject() instanceof ASN1Set)
            return (ASN1Set)obj.getObject();
        if(obj.getObject() instanceof ASN1Sequence)
        {
            ASN1Sequence s = (ASN1Sequence)obj.getObject();
            if(obj instanceof BERTaggedObject)
                return new BERSet(s.toArray());
            else
                return new DLSet(s.toArray());
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in getInstance: ").append(obj.getClass().getName()).toString());
        }
    }

    protected ASN1Set()
    {
        set = new Vector();
        isSorted = false;
    }

    protected ASN1Set(ASN1Encodable obj)
    {
        set = new Vector();
        isSorted = false;
        set.addElement(obj);
    }

    protected ASN1Set(ASN1EncodableVector v, boolean doSort)
    {
        set = new Vector();
        isSorted = false;
        for(int i = 0; i != v.size(); i++)
            set.addElement(v.get(i));

        if(doSort)
            sort();
    }

    protected ASN1Set(ASN1Encodable array[], boolean doSort)
    {
        set = new Vector();
        isSorted = false;
        for(int i = 0; i != array.length; i++)
            set.addElement(array[i]);

        if(doSort)
            sort();
    }

    public Enumeration getObjects()
    {
        return set.elements();
    }

    public ASN1Encodable getObjectAt(int index)
    {
        return (ASN1Encodable)set.elementAt(index);
    }

    public int size()
    {
        return set.size();
    }

    public ASN1Encodable[] toArray()
    {
        ASN1Encodable values[] = new ASN1Encodable[size()];
        for(int i = 0; i != size(); i++)
            values[i] = getObjectAt(i);

        return values;
    }

    public ASN1SetParser parser()
    {
        final ASN1Set outer = this;
        return new ASN1SetParser() {

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
            final ASN1Set val$outer;
            final ASN1Set this$0;

            
            {
                this$0 = ASN1Set.this;
                outer = asn1set1;
                super();
                max = size();
            }
        }
;
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

    ASN1Primitive toDERObject()
    {
        if(isSorted)
        {
            ASN1Set derSet = new DERSet();
            derSet.set = set;
            return derSet;
        }
        Vector v = new Vector();
        for(int i = 0; i != set.size(); i++)
            v.addElement(set.elementAt(i));

        ASN1Set derSet = new DERSet();
        derSet.set = v;
        derSet.sort();
        return derSet;
    }

    ASN1Primitive toDLObject()
    {
        ASN1Set derSet = new DLSet();
        derSet.set = set;
        return derSet;
    }

    boolean asn1Equals(ASN1Primitive o)
    {
        if(!(o instanceof ASN1Set))
            return false;
        ASN1Set other = (ASN1Set)o;
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
        if(encObj == null)
            return DERNull.INSTANCE;
        else
            return encObj;
    }

    private boolean lessThanOrEqual(byte a[], byte b[])
    {
        int len = Math.min(a.length, b.length);
        for(int i = 0; i != len; i++)
            if(a[i] != b[i])
                return (a[i] & 0xff) < (b[i] & 0xff);

        return len == a.length;
    }

    private byte[] getEncoded(ASN1Encodable obj)
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        ASN1OutputStream aOut = new ASN1OutputStream(bOut);
        try
        {
            aOut.writeObject(obj);
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException("cannot encode object added to SET");
        }
        return bOut.toByteArray();
    }

    protected void sort()
    {
        if(!isSorted)
        {
            isSorted = true;
            if(set.size() > 1)
            {
                boolean swapped = true;
                int swapIndex;
                for(int lastSwap = set.size() - 1; swapped; lastSwap = swapIndex)
                {
                    int index = 0;
                    swapIndex = 0;
                    byte a[] = getEncoded((ASN1Encodable)set.elementAt(0));
                    swapped = false;
                    for(; index != lastSwap; index++)
                    {
                        byte b[] = getEncoded((ASN1Encodable)set.elementAt(index + 1));
                        if(lessThanOrEqual(a, b))
                        {
                            a = b;
                        } else
                        {
                            Object o = set.elementAt(index);
                            set.setElementAt(set.elementAt(index + 1), index);
                            set.setElementAt(o, index + 1);
                            swapped = true;
                            swapIndex = index;
                        }
                    }

                }

            }
        }
    }

    boolean isConstructed()
    {
        return true;
    }

    abstract void encode(ASN1OutputStream asn1outputstream)
        throws IOException;

    public String toString()
    {
        return set.toString();
    }

    private Vector set;
    private boolean isSorted;
}
