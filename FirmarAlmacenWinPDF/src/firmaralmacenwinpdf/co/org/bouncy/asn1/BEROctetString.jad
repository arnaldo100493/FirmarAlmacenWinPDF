// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BEROctetString.java

package co.org.bouncy.asn1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.asn1:
//            ASN1OctetString, DEROctetString, ASN1Encodable, ASN1Primitive, 
//            ASN1OutputStream, ASN1Sequence

public class BEROctetString extends ASN1OctetString
{

    private static byte[] toBytes(ASN1OctetString octs[])
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        for(int i = 0; i != octs.length; i++)
            try
            {
                DEROctetString o = (DEROctetString)octs[i];
                bOut.write(o.getOctets());
            }
            catch(ClassCastException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append(octs[i].getClass().getName()).append(" found in input should only contain DEROctetString").toString());
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("exception converting octets ").append(e.toString()).toString());
            }

        return bOut.toByteArray();
    }

    public BEROctetString(byte string[])
    {
        super(string);
    }

    public BEROctetString(ASN1OctetString octs[])
    {
        super(toBytes(octs));
        this.octs = octs;
    }

    public byte[] getOctets()
    {
        return string;
    }

    public Enumeration getObjects()
    {
        if(octs == null)
            return generateOcts().elements();
        else
            return new Enumeration() {

                public boolean hasMoreElements()
                {
                    return counter < octs.length;
                }

                public Object nextElement()
                {
                    return octs[counter++];
                }

                int counter;
                final BEROctetString this$0;

            
            {
                this$0 = BEROctetString.this;
                super();
                counter = 0;
            }
            }
;
    }

    private Vector generateOcts()
    {
        Vector vec = new Vector();
        for(int i = 0; i < string.length; i += 1000)
        {
            int end;
            if(i + 1000 > string.length)
                end = string.length;
            else
                end = i + 1000;
            byte nStr[] = new byte[end - i];
            System.arraycopy(string, i, nStr, 0, nStr.length);
            vec.addElement(new DEROctetString(nStr));
        }

        return vec;
    }

    boolean isConstructed()
    {
        return true;
    }

    int encodedLength()
        throws IOException
    {
        int length = 0;
        for(Enumeration e = getObjects(); e.hasMoreElements();)
            length += ((ASN1Encodable)e.nextElement()).toASN1Primitive().encodedLength();

        return 2 + length + 2;
    }

    public void encode(ASN1OutputStream out)
        throws IOException
    {
        out.write(36);
        out.write(128);
        for(Enumeration e = getObjects(); e.hasMoreElements(); out.writeObject((ASN1Encodable)e.nextElement()));
        out.write(0);
        out.write(0);
    }

    static BEROctetString fromSequence(ASN1Sequence seq)
    {
        ASN1OctetString v[] = new ASN1OctetString[seq.size()];
        Enumeration e = seq.getObjects();
        int index = 0;
        while(e.hasMoreElements()) 
            v[index++] = (ASN1OctetString)e.nextElement();
        return new BEROctetString(v);
    }

    private static final int MAX_LENGTH = 1000;
    private ASN1OctetString octs[];

}
