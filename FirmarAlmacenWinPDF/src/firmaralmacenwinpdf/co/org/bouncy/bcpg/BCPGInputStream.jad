// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BCPGInputStream.java

package co.org.bouncy.bcpg;

import co.org.bouncy.util.io.Streams;
import java.io.*;

// Referenced classes of package co.org.bouncy.bcpg:
//            InputStreamPacket, PublicKeyEncSessionPacket, SignaturePacket, SymmetricKeyEncSessionPacket, 
//            OnePassSignaturePacket, SecretKeyPacket, PublicKeyPacket, SecretSubkeyPacket, 
//            CompressedDataPacket, SymmetricEncDataPacket, MarkerPacket, LiteralDataPacket, 
//            TrustPacket, UserIDPacket, UserAttributePacket, PublicSubkeyPacket, 
//            SymmetricEncIntegrityPacket, ModDetectionCodePacket, ExperimentalPacket, PacketTags, 
//            Packet

public class BCPGInputStream extends InputStream
    implements PacketTags
{
    private static class PartialInputStream extends InputStream
    {

        public int available()
            throws IOException
        {
            int avail = in.available();
            if(avail <= dataLength || dataLength < 0)
                return avail;
            if(partial && dataLength == 0)
                return 1;
            else
                return dataLength;
        }

        private int loadDataLength()
            throws IOException
        {
            int l = in.read();
            if(l < 0)
                return -1;
            partial = false;
            if(l < 192)
                dataLength = l;
            else
            if(l <= 223)
                dataLength = (l - 192 << 8) + in.read() + 192;
            else
            if(l == 255)
            {
                dataLength = in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read();
            } else
            {
                partial = true;
                dataLength = 1 << (l & 0x1f);
            }
            return dataLength;
        }

        public int read(byte buf[], int offset, int len)
            throws IOException
        {
            do
                if(dataLength != 0)
                {
                    int readLen = dataLength <= len && dataLength >= 0 ? dataLength : len;
                    readLen = in.read(buf, offset, readLen);
                    if(readLen < 0)
                    {
                        throw new EOFException("premature end of stream in PartialInputStream");
                    } else
                    {
                        dataLength -= readLen;
                        return readLen;
                    }
                }
            while(partial && loadDataLength() >= 0);
            return -1;
        }

        public int read()
            throws IOException
        {
            do
                if(dataLength != 0)
                {
                    int ch = in.read();
                    if(ch < 0)
                    {
                        throw new EOFException("premature end of stream in PartialInputStream");
                    } else
                    {
                        dataLength--;
                        return ch;
                    }
                }
            while(partial && loadDataLength() >= 0);
            return -1;
        }

        private BCPGInputStream in;
        private boolean partial;
        private int dataLength;

        PartialInputStream(BCPGInputStream in, boolean partial, int dataLength)
        {
            this.in = in;
            this.partial = partial;
            this.dataLength = dataLength;
        }
    }


    public BCPGInputStream(InputStream in)
    {
        next = false;
        this.in = in;
    }

    public int available()
        throws IOException
    {
        return in.available();
    }

    public int read()
        throws IOException
    {
        if(next)
        {
            next = false;
            return nextB;
        } else
        {
            return in.read();
        }
    }

    public int read(byte buf[], int off, int len)
        throws IOException
    {
        if(len == 0)
            return 0;
        if(!next)
            return in.read(buf, off, len);
        if(nextB < 0)
        {
            return -1;
        } else
        {
            buf[off] = (byte)nextB;
            next = false;
            return 1;
        }
    }

    public void readFully(byte buf[], int off, int len)
        throws IOException
    {
        if(Streams.readFully(this, buf, off, len) < len)
            throw new EOFException();
        else
            return;
    }

    public byte[] readAll()
        throws IOException
    {
        return Streams.readAll(this);
    }

    public void readFully(byte buf[])
        throws IOException
    {
        readFully(buf, 0, buf.length);
    }

    public int nextPacketTag()
        throws IOException
    {
        if(!next)
            try
            {
                nextB = in.read();
            }
            catch(EOFException e)
            {
                nextB = -1;
            }
        next = true;
        if(nextB >= 0)
        {
            if((nextB & 0x40) != 0)
                return nextB & 0x3f;
            else
                return (nextB & 0x3f) >> 2;
        } else
        {
            return nextB;
        }
    }

    public Packet readPacket()
        throws IOException
    {
        int hdr = read();
        if(hdr < 0)
            return null;
        if((hdr & 0x80) == 0)
            throw new IOException("invalid header encountered");
        boolean newPacket = (hdr & 0x40) != 0;
        int tag = 0;
        int bodyLen = 0;
        boolean partial = false;
        if(newPacket)
        {
            tag = hdr & 0x3f;
            int l = read();
            if(l < 192)
                bodyLen = l;
            else
            if(l <= 223)
            {
                int b = in.read();
                bodyLen = (l - 192 << 8) + b + 192;
            } else
            if(l == 255)
            {
                bodyLen = in.read() << 24 | in.read() << 16 | in.read() << 8 | in.read();
            } else
            {
                partial = true;
                bodyLen = 1 << (l & 0x1f);
            }
        } else
        {
            int lengthType = hdr & 3;
            tag = (hdr & 0x3f) >> 2;
            switch(lengthType)
            {
            case 0: // '\0'
                bodyLen = read();
                break;

            case 1: // '\001'
                bodyLen = read() << 8 | read();
                break;

            case 2: // '\002'
                bodyLen = read() << 24 | read() << 16 | read() << 8 | read();
                break;

            case 3: // '\003'
                partial = true;
                break;

            default:
                throw new IOException("unknown length type encountered");
            }
        }
        BCPGInputStream objStream;
        if(bodyLen == 0 && partial)
            objStream = this;
        else
            objStream = new BCPGInputStream(new PartialInputStream(this, partial, bodyLen));
        switch(tag)
        {
        case 0: // '\0'
            return new InputStreamPacket(objStream);

        case 1: // '\001'
            return new PublicKeyEncSessionPacket(objStream);

        case 2: // '\002'
            return new SignaturePacket(objStream);

        case 3: // '\003'
            return new SymmetricKeyEncSessionPacket(objStream);

        case 4: // '\004'
            return new OnePassSignaturePacket(objStream);

        case 5: // '\005'
            return new SecretKeyPacket(objStream);

        case 6: // '\006'
            return new PublicKeyPacket(objStream);

        case 7: // '\007'
            return new SecretSubkeyPacket(objStream);

        case 8: // '\b'
            return new CompressedDataPacket(objStream);

        case 9: // '\t'
            return new SymmetricEncDataPacket(objStream);

        case 10: // '\n'
            return new MarkerPacket(objStream);

        case 11: // '\013'
            return new LiteralDataPacket(objStream);

        case 12: // '\f'
            return new TrustPacket(objStream);

        case 13: // '\r'
            return new UserIDPacket(objStream);

        case 17: // '\021'
            return new UserAttributePacket(objStream);

        case 14: // '\016'
            return new PublicSubkeyPacket(objStream);

        case 18: // '\022'
            return new SymmetricEncIntegrityPacket(objStream);

        case 19: // '\023'
            return new ModDetectionCodePacket(objStream);

        case 60: // '<'
        case 61: // '='
        case 62: // '>'
        case 63: // '?'
            return new ExperimentalPacket(tag, objStream);

        case 15: // '\017'
        case 16: // '\020'
        case 20: // '\024'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
        case 24: // '\030'
        case 25: // '\031'
        case 26: // '\032'
        case 27: // '\033'
        case 28: // '\034'
        case 29: // '\035'
        case 30: // '\036'
        case 31: // '\037'
        case 32: // ' '
        case 33: // '!'
        case 34: // '"'
        case 35: // '#'
        case 36: // '$'
        case 37: // '%'
        case 38: // '&'
        case 39: // '\''
        case 40: // '('
        case 41: // ')'
        case 42: // '*'
        case 43: // '+'
        case 44: // ','
        case 45: // '-'
        case 46: // '.'
        case 47: // '/'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
        case 58: // ':'
        case 59: // ';'
        default:
            throw new IOException((new StringBuilder()).append("unknown packet type encountered: ").append(tag).toString());
        }
    }

    public void close()
        throws IOException
    {
        in.close();
    }

    InputStream in;
    boolean next;
    int nextB;
}
