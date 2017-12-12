// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArmoredInputStream.java

package co.org.bouncy.bcpg;

import java.io.*;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.bcpg:
//            CRC24

public class ArmoredInputStream extends InputStream
{

    private int decode(int in0, int in1, int in2, int in3, int out[])
        throws EOFException
    {
        if(in3 < 0)
            throw new EOFException("unexpected end of file in armored stream.");
        if(in2 == 61)
        {
            int b1 = decodingTable[in0] & 0xff;
            int b2 = decodingTable[in1] & 0xff;
            out[2] = (b1 << 2 | b2 >> 4) & 0xff;
            return 2;
        }
        if(in3 == 61)
        {
            int b1 = decodingTable[in0];
            int b2 = decodingTable[in1];
            int b3 = decodingTable[in2];
            out[1] = (b1 << 2 | b2 >> 4) & 0xff;
            out[2] = (b2 << 4 | b3 >> 2) & 0xff;
            return 1;
        } else
        {
            int b1 = decodingTable[in0];
            int b2 = decodingTable[in1];
            int b3 = decodingTable[in2];
            int b4 = decodingTable[in3];
            out[0] = (b1 << 2 | b2 >> 4) & 0xff;
            out[1] = (b2 << 4 | b3 >> 2) & 0xff;
            out[2] = (b3 << 6 | b4) & 0xff;
            return 0;
        }
    }

    public ArmoredInputStream(InputStream in)
        throws IOException
    {
        this(in, true);
    }

    public ArmoredInputStream(InputStream in, boolean hasHeaders)
        throws IOException
    {
        start = true;
        outBuf = new int[3];
        bufPtr = 3;
        crc = new CRC24();
        crcFound = false;
        this.hasHeaders = true;
        header = null;
        newLineFound = false;
        clearText = false;
        restart = false;
        headerList = new Vector();
        lastC = 0;
        this.in = in;
        this.hasHeaders = hasHeaders;
        if(hasHeaders)
            parseHeaders();
        start = false;
    }

    public int available()
        throws IOException
    {
        return in.available();
    }

    private boolean parseHeaders()
        throws IOException
    {
        header = null;
        int last = 0;
        boolean headerFound = false;
        headerList = new Vector();
        if(restart)
            headerFound = true;
        else
            do
            {
                int c;
                if((c = in.read()) < 0)
                    break;
                if(c == 45 && (last == 0 || last == 10 || last == 13))
                {
                    headerFound = true;
                    break;
                }
                last = c;
            } while(true);
        if(headerFound)
        {
            StringBuffer buf = new StringBuffer("-");
            boolean eolReached = false;
            boolean crLf = false;
            if(restart)
                buf.append('-');
            int c;
            for(; (c = in.read()) >= 0; last = c)
            {
                if(last == 13 && c == 10)
                    crLf = true;
                if(eolReached && last != 13 && c == 10 || eolReached && c == 13)
                    break;
                if(c == 13 || last != 13 && c == 10)
                {
                    String line = buf.toString();
                    if(line.trim().length() == 0)
                        break;
                    headerList.addElement(line);
                    buf.setLength(0);
                }
                if(c != 10 && c != 13)
                {
                    buf.append((char)c);
                    eolReached = false;
                    continue;
                }
                if(c == 13 || last != 13 && c == 10)
                    eolReached = true;
            }

            if(crLf)
                in.read();
        }
        if(headerList.size() > 0)
            header = (String)headerList.elementAt(0);
        clearText = "-----BEGIN PGP SIGNED MESSAGE-----".equals(header);
        newLineFound = true;
        return headerFound;
    }

    public boolean isClearText()
    {
        return clearText;
    }

    public boolean isEndOfStream()
    {
        return isEndOfStream;
    }

    public String getArmorHeaderLine()
    {
        return header;
    }

    public String[] getArmorHeaders()
    {
        if(headerList.size() <= 1)
            return null;
        String hdrs[] = new String[headerList.size() - 1];
        for(int i = 0; i != hdrs.length; i++)
            hdrs[i] = (String)headerList.elementAt(i + 1);

        return hdrs;
    }

    private int readIgnoreSpace()
        throws IOException
    {
        int c;
        for(c = in.read(); c == 32 || c == 9; c = in.read());
        return c;
    }

    public int read()
        throws IOException
    {
        if(start)
        {
            if(hasHeaders)
                parseHeaders();
            crc.reset();
            start = false;
        }
        int c;
        if(clearText)
        {
            c = in.read();
            if(c == 13 || c == 10 && lastC != 13)
                newLineFound = true;
            else
            if(newLineFound && c == 45)
            {
                c = in.read();
                if(c == 45)
                {
                    clearText = false;
                    start = true;
                    restart = true;
                } else
                {
                    c = in.read();
                }
                newLineFound = false;
            } else
            if(c != 10 && lastC != 13)
                newLineFound = false;
            lastC = c;
            if(c < 0)
                isEndOfStream = true;
            return c;
        }
        if(bufPtr > 2 || crcFound)
        {
            c = readIgnoreSpace();
            if(c == 13 || c == 10)
            {
                for(c = readIgnoreSpace(); c == 10 || c == 13; c = readIgnoreSpace());
                if(c < 0)
                {
                    isEndOfStream = true;
                    return -1;
                }
                if(c == 61)
                {
                    bufPtr = decode(readIgnoreSpace(), readIgnoreSpace(), readIgnoreSpace(), readIgnoreSpace(), outBuf);
                    if(bufPtr == 0)
                    {
                        int i = (outBuf[0] & 0xff) << 16 | (outBuf[1] & 0xff) << 8 | outBuf[2] & 0xff;
                        crcFound = true;
                        if(i != crc.getValue())
                            throw new IOException("crc check failed in armored message.");
                        else
                            return read();
                    } else
                    {
                        throw new IOException("no crc found in armored message.");
                    }
                }
                if(c == 45)
                {
                    while((c = in.read()) >= 0 && c != 10 && c != 13) ;
                    if(!crcFound)
                        throw new IOException("crc check not found.");
                    crcFound = false;
                    start = true;
                    bufPtr = 3;
                    if(c < 0)
                        isEndOfStream = true;
                    return -1;
                }
                bufPtr = decode(c, readIgnoreSpace(), readIgnoreSpace(), readIgnoreSpace(), outBuf);
            } else
            if(c >= 0)
            {
                bufPtr = decode(c, readIgnoreSpace(), readIgnoreSpace(), readIgnoreSpace(), outBuf);
            } else
            {
                isEndOfStream = true;
                return -1;
            }
        }
        c = outBuf[bufPtr++];
        crc.update(c);
        return c;
    }

    public void close()
        throws IOException
    {
        in.close();
    }

    private static final byte decodingTable[];
    InputStream in;
    boolean start;
    int outBuf[];
    int bufPtr;
    CRC24 crc;
    boolean crcFound;
    boolean hasHeaders;
    String header;
    boolean newLineFound;
    boolean clearText;
    boolean restart;
    Vector headerList;
    int lastC;
    boolean isEndOfStream;

    static 
    {
        decodingTable = new byte[128];
        for(int i = 65; i <= 90; i++)
            decodingTable[i] = (byte)(i - 65);

        for(int i = 97; i <= 122; i++)
            decodingTable[i] = (byte)((i - 97) + 26);

        for(int i = 48; i <= 57; i++)
            decodingTable[i] = (byte)((i - 48) + 52);

        decodingTable[43] = 62;
        decodingTable[47] = 63;
    }
}
