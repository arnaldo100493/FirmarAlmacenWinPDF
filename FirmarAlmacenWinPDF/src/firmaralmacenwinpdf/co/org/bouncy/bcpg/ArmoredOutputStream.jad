// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArmoredOutputStream.java

package co.org.bouncy.bcpg;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.bcpg:
//            CRC24

public class ArmoredOutputStream extends OutputStream
{

    private void encode(OutputStream out, int data[], int len)
        throws IOException
    {
        switch(len)
        {
        case 1: // '\001'
        {
            int d1 = data[0];
            out.write(encodingTable[d1 >>> 2 & 0x3f]);
            out.write(encodingTable[d1 << 4 & 0x3f]);
            out.write(61);
            out.write(61);
            break;
        }

        case 2: // '\002'
        {
            int d1 = data[0];
            int d2 = data[1];
            out.write(encodingTable[d1 >>> 2 & 0x3f]);
            out.write(encodingTable[(d1 << 4 | d2 >>> 4) & 0x3f]);
            out.write(encodingTable[d2 << 2 & 0x3f]);
            out.write(61);
            break;
        }

        case 3: // '\003'
        {
            int d1 = data[0];
            int d2 = data[1];
            int d3 = data[2];
            out.write(encodingTable[d1 >>> 2 & 0x3f]);
            out.write(encodingTable[(d1 << 4 | d2 >>> 4) & 0x3f]);
            out.write(encodingTable[(d2 << 2 | d3 >>> 6) & 0x3f]);
            out.write(encodingTable[d3 & 0x3f]);
            break;
        }

        default:
        {
            throw new IOException("unknown length in encode");
        }

        case 0: // '\0'
            break;
        }
    }

    public ArmoredOutputStream(OutputStream out)
    {
        buf = new int[3];
        bufPtr = 0;
        crc = new CRC24();
        chunkCount = 0;
        start = true;
        clearText = false;
        newLine = false;
        nl = System.getProperty("line.separator");
        headerStart = "-----BEGIN PGP ";
        headerTail = "-----";
        footerStart = "-----END PGP ";
        footerTail = "-----";
        version = "BCPG v1.49";
        headers = new Hashtable();
        this.out = out;
        if(nl == null)
            nl = "\r\n";
        resetHeaders();
    }

    public ArmoredOutputStream(OutputStream out, Hashtable headers)
    {
        this(out);
        Object key;
        for(Enumeration e = headers.keys(); e.hasMoreElements(); this.headers.put(key, headers.get(key)))
            key = e.nextElement();

    }

    public void setHeader(String name, String value)
    {
        headers.put(name, value);
    }

    public void resetHeaders()
    {
        headers.clear();
        headers.put("Version", version);
    }

    public void beginClearText(int hashAlgorithm)
        throws IOException
    {
        String hash;
        switch(hashAlgorithm)
        {
        case 2: // '\002'
            hash = "SHA1";
            break;

        case 8: // '\b'
            hash = "SHA256";
            break;

        case 9: // '\t'
            hash = "SHA384";
            break;

        case 10: // '\n'
            hash = "SHA512";
            break;

        case 5: // '\005'
            hash = "MD2";
            break;

        case 1: // '\001'
            hash = "MD5";
            break;

        case 3: // '\003'
            hash = "RIPEMD160";
            break;

        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        default:
            throw new IOException((new StringBuilder()).append("unknown hash algorithm tag in beginClearText: ").append(hashAlgorithm).toString());
        }
        String armorHdr = (new StringBuilder()).append("-----BEGIN PGP SIGNED MESSAGE-----").append(nl).toString();
        String hdrs = (new StringBuilder()).append("Hash: ").append(hash).append(nl).append(nl).toString();
        for(int i = 0; i != armorHdr.length(); i++)
            out.write(armorHdr.charAt(i));

        for(int i = 0; i != hdrs.length(); i++)
            out.write(hdrs.charAt(i));

        clearText = true;
        newLine = true;
        lastb = 0;
    }

    public void endClearText()
    {
        clearText = false;
    }

    private void writeHeaderEntry(String name, String value)
        throws IOException
    {
        for(int i = 0; i != name.length(); i++)
            out.write(name.charAt(i));

        out.write(58);
        out.write(32);
        for(int i = 0; i != value.length(); i++)
            out.write(value.charAt(i));

        for(int i = 0; i != nl.length(); i++)
            out.write(nl.charAt(i));

    }

    public void write(int b)
        throws IOException
    {
        if(clearText)
        {
            out.write(b);
            if(newLine)
            {
                if(b != 10 || lastb != 13)
                    newLine = false;
                if(b == 45)
                {
                    out.write(32);
                    out.write(45);
                }
            }
            if(b == 13 || b == 10 && lastb != 13)
                newLine = true;
            lastb = b;
            return;
        }
        if(start)
        {
            boolean newPacket = (b & 0x40) != 0;
            int tag = 0;
            if(newPacket)
                tag = b & 0x3f;
            else
                tag = (b & 0x3f) >> 2;
            switch(tag)
            {
            case 6: // '\006'
                type = "PUBLIC KEY BLOCK";
                break;

            case 5: // '\005'
                type = "PRIVATE KEY BLOCK";
                break;

            case 2: // '\002'
                type = "SIGNATURE";
                break;

            case 3: // '\003'
            case 4: // '\004'
            default:
                type = "MESSAGE";
                break;
            }
            for(int i = 0; i != headerStart.length(); i++)
                out.write(headerStart.charAt(i));

            for(int i = 0; i != type.length(); i++)
                out.write(type.charAt(i));

            for(int i = 0; i != headerTail.length(); i++)
                out.write(headerTail.charAt(i));

            for(int i = 0; i != nl.length(); i++)
                out.write(nl.charAt(i));

            writeHeaderEntry("Version", (String)headers.get("Version"));
            Enumeration e = headers.keys();
            do
            {
                if(!e.hasMoreElements())
                    break;
                String key = (String)e.nextElement();
                if(!key.equals("Version"))
                    writeHeaderEntry(key, (String)headers.get(key));
            } while(true);
            for(int i = 0; i != nl.length(); i++)
                out.write(nl.charAt(i));

            start = false;
        }
        if(bufPtr == 3)
        {
            encode(out, buf, bufPtr);
            bufPtr = 0;
            if((++chunkCount & 0xf) == 0)
            {
                for(int i = 0; i != nl.length(); i++)
                    out.write(nl.charAt(i));

            }
        }
        crc.update(b);
        buf[bufPtr++] = b & 0xff;
    }

    public void flush()
        throws IOException
    {
    }

    public void close()
        throws IOException
    {
        if(type != null)
        {
            encode(out, buf, bufPtr);
            for(int i = 0; i != nl.length(); i++)
                out.write(nl.charAt(i));

            out.write(61);
            int crcV = crc.getValue();
            buf[0] = crcV >> 16 & 0xff;
            buf[1] = crcV >> 8 & 0xff;
            buf[2] = crcV & 0xff;
            encode(out, buf, 3);
            for(int i = 0; i != nl.length(); i++)
                out.write(nl.charAt(i));

            for(int i = 0; i != footerStart.length(); i++)
                out.write(footerStart.charAt(i));

            for(int i = 0; i != type.length(); i++)
                out.write(type.charAt(i));

            for(int i = 0; i != footerTail.length(); i++)
                out.write(footerTail.charAt(i));

            for(int i = 0; i != nl.length(); i++)
                out.write(nl.charAt(i));

            out.flush();
            type = null;
            start = true;
        }
    }

    private static final byte encodingTable[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 43, 47
    };
    OutputStream out;
    int buf[];
    int bufPtr;
    CRC24 crc;
    int chunkCount;
    int lastb;
    boolean start;
    boolean clearText;
    boolean newLine;
    String nl;
    String type;
    String headerStart;
    String headerTail;
    String footerStart;
    String footerTail;
    String version;
    Hashtable headers;

}
