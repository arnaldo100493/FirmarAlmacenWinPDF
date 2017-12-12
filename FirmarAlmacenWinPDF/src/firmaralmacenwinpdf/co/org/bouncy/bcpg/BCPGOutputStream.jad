// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCPGOutputStream.java

package co.org.bouncy.bcpg;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.bcpg:
//            PacketTags, CompressionAlgorithmTags, ContainedPacket, BCPGObject

public class BCPGOutputStream extends OutputStream
    implements PacketTags, CompressionAlgorithmTags
{

    public BCPGOutputStream(OutputStream out)
    {
        this.out = out;
    }

    public BCPGOutputStream(OutputStream out, int tag)
        throws IOException
    {
        this.out = out;
        writeHeader(tag, true, true, 0L);
    }

    public BCPGOutputStream(OutputStream out, int tag, long length, boolean oldFormat)
        throws IOException
    {
        this.out = out;
        if(length > 0xffffffffL)
        {
            writeHeader(tag, false, true, 0L);
            partialBufferLength = 0x10000;
            partialBuffer = new byte[partialBufferLength];
            partialPower = 16;
            partialOffset = 0;
        } else
        {
            writeHeader(tag, oldFormat, false, length);
        }
    }

    public BCPGOutputStream(OutputStream out, int tag, long length)
        throws IOException
    {
        this.out = out;
        writeHeader(tag, false, false, length);
    }

    public BCPGOutputStream(OutputStream out, int tag, byte buffer[])
        throws IOException
    {
        this.out = out;
        writeHeader(tag, false, true, 0L);
        partialBuffer = buffer;
        int length = partialBuffer.length;
        for(partialPower = 0; length != 1; partialPower++)
            length >>>= 1;

        if(partialPower > 30)
        {
            throw new IOException("Buffer cannot be greater than 2^30 in length.");
        } else
        {
            partialBufferLength = 1 << partialPower;
            partialOffset = 0;
            return;
        }
    }

    private void writeNewPacketLength(long bodyLen)
        throws IOException
    {
        if(bodyLen < 192L)
            out.write((byte)(int)bodyLen);
        else
        if(bodyLen <= 8383L)
        {
            bodyLen -= 192L;
            out.write((byte)(int)((bodyLen >> 8 & 255L) + 192L));
            out.write((byte)(int)bodyLen);
        } else
        {
            out.write(255);
            out.write((byte)(int)(bodyLen >> 24));
            out.write((byte)(int)(bodyLen >> 16));
            out.write((byte)(int)(bodyLen >> 8));
            out.write((byte)(int)bodyLen);
        }
    }

    private void writeHeader(int tag, boolean oldPackets, boolean partial, long bodyLen)
        throws IOException
    {
        int hdr = 128;
        if(partialBuffer != null)
        {
            partialFlush(true);
            partialBuffer = null;
        }
        if(oldPackets)
        {
            hdr |= tag << 2;
            if(partial)
                write(hdr | 3);
            else
            if(bodyLen <= 255L)
            {
                write(hdr);
                write((byte)(int)bodyLen);
            } else
            if(bodyLen <= 65535L)
            {
                write(hdr | 1);
                write((byte)(int)(bodyLen >> 8));
                write((byte)(int)bodyLen);
            } else
            {
                write(hdr | 2);
                write((byte)(int)(bodyLen >> 24));
                write((byte)(int)(bodyLen >> 16));
                write((byte)(int)(bodyLen >> 8));
                write((byte)(int)bodyLen);
            }
        } else
        {
            hdr |= 0x40 | tag;
            write(hdr);
            if(partial)
                partialOffset = 0;
            else
                writeNewPacketLength(bodyLen);
        }
    }

    private void partialFlush(boolean isLast)
        throws IOException
    {
        if(isLast)
        {
            writeNewPacketLength(partialOffset);
            out.write(partialBuffer, 0, partialOffset);
        } else
        {
            out.write(0xe0 | partialPower);
            out.write(partialBuffer, 0, partialBufferLength);
        }
        partialOffset = 0;
    }

    private void writePartial(byte b)
        throws IOException
    {
        if(partialOffset == partialBufferLength)
            partialFlush(false);
        partialBuffer[partialOffset++] = b;
    }

    private void writePartial(byte buf[], int off, int len)
        throws IOException
    {
        if(partialOffset == partialBufferLength)
            partialFlush(false);
        if(len <= partialBufferLength - partialOffset)
        {
            System.arraycopy(buf, off, partialBuffer, partialOffset, len);
            partialOffset += len;
        } else
        {
            System.arraycopy(buf, off, partialBuffer, partialOffset, partialBufferLength - partialOffset);
            off += partialBufferLength - partialOffset;
            len -= partialBufferLength - partialOffset;
            partialFlush(false);
            while(len > partialBufferLength) 
            {
                System.arraycopy(buf, off, partialBuffer, 0, partialBufferLength);
                off += partialBufferLength;
                len -= partialBufferLength;
                partialFlush(false);
            }
            System.arraycopy(buf, off, partialBuffer, 0, len);
            partialOffset += len;
        }
    }

    public void write(int b)
        throws IOException
    {
        if(partialBuffer != null)
            writePartial((byte)b);
        else
            out.write(b);
    }

    public void write(byte bytes[], int off, int len)
        throws IOException
    {
        if(partialBuffer != null)
            writePartial(bytes, off, len);
        else
            out.write(bytes, off, len);
    }

    public void writePacket(ContainedPacket p)
        throws IOException
    {
        p.encode(this);
    }

    void writePacket(int tag, byte body[], boolean oldFormat)
        throws IOException
    {
        writeHeader(tag, oldFormat, false, body.length);
        write(body);
    }

    public void writeObject(BCPGObject o)
        throws IOException
    {
        o.encode(this);
    }

    public void flush()
        throws IOException
    {
        out.flush();
    }

    public void finish()
        throws IOException
    {
        if(partialBuffer != null)
        {
            partialFlush(true);
            partialBuffer = null;
        }
    }

    public void close()
        throws IOException
    {
        finish();
        out.flush();
        out.close();
    }

    OutputStream out;
    private byte partialBuffer[];
    private int partialBufferLength;
    private int partialPower;
    private int partialOffset;
    private static final int BUF_SIZE_POWER = 16;
}
