// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ASN1OutputStream.java

package co.org.bouncy.asn1;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.asn1:
//            DEROutputStream, DLOutputStream, ASN1Encodable, ASN1Primitive

public class ASN1OutputStream
{
    private class ImplicitOutputStream extends ASN1OutputStream
    {

        public void write(int b)
            throws IOException
        {
            if(first)
                first = false;
            else
                write(b);
        }

        private boolean first;
        final ASN1OutputStream this$0;

        public ImplicitOutputStream(OutputStream os)
        {
            this$0 = ASN1OutputStream.this;
            super(os);
            first = true;
        }
    }


    public ASN1OutputStream(OutputStream os)
    {
        this.os = os;
    }

    void writeLength(int length)
        throws IOException
    {
        if(length > 127)
        {
            int size = 1;
            for(int val = length; (val >>>= 8) != 0;)
                size++;

            write((byte)(size | 0x80));
            for(int i = (size - 1) * 8; i >= 0; i -= 8)
                write((byte)(length >> i));

        } else
        {
            write((byte)length);
        }
    }

    void write(int b)
        throws IOException
    {
        os.write(b);
    }

    void write(byte bytes[])
        throws IOException
    {
        os.write(bytes);
    }

    void write(byte bytes[], int off, int len)
        throws IOException
    {
        os.write(bytes, off, len);
    }

    void writeEncoded(int tag, byte bytes[])
        throws IOException
    {
        write(tag);
        writeLength(bytes.length);
        write(bytes);
    }

    void writeTag(int flags, int tagNo)
        throws IOException
    {
        if(tagNo < 31)
        {
            write(flags | tagNo);
        } else
        {
            write(flags | 0x1f);
            if(tagNo < 128)
            {
                write(tagNo);
            } else
            {
                byte stack[] = new byte[5];
                int pos = stack.length;
                stack[--pos] = (byte)(tagNo & 0x7f);
                do
                {
                    tagNo >>= 7;
                    stack[--pos] = (byte)(tagNo & 0x7f | 0x80);
                } while(tagNo > 127);
                write(stack, pos, stack.length - pos);
            }
        }
    }

    void writeEncoded(int flags, int tagNo, byte bytes[])
        throws IOException
    {
        writeTag(flags, tagNo);
        writeLength(bytes.length);
        write(bytes);
    }

    protected void writeNull()
        throws IOException
    {
        os.write(5);
        os.write(0);
    }

    public void writeObject(ASN1Encodable obj)
        throws IOException
    {
        if(obj != null)
            obj.toASN1Primitive().encode(this);
        else
            throw new IOException("null object detected");
    }

    void writeImplicitObject(ASN1Primitive obj)
        throws IOException
    {
        if(obj != null)
            obj.encode(new ImplicitOutputStream(os));
        else
            throw new IOException("null object detected");
    }

    public void close()
        throws IOException
    {
        os.close();
    }

    public void flush()
        throws IOException
    {
        os.flush();
    }

    ASN1OutputStream getDERSubStream()
    {
        return new DEROutputStream(os);
    }

    ASN1OutputStream getDLSubStream()
    {
        return new DLOutputStream(os);
    }

    private OutputStream os;
}
