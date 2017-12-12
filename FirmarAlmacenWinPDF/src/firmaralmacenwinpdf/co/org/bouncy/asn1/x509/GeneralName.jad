// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GeneralName.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.X500Name;
import co.org.bouncy.util.IPAddress;
import java.io.IOException;
import java.util.StringTokenizer;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            X509Name

public class GeneralName extends ASN1Object
    implements ASN1Choice
{

    /**
     * @deprecated Method GeneralName is deprecated
     */

    public GeneralName(X509Name dirName)
    {
        obj = X500Name.getInstance(dirName);
        tag = 4;
    }

    public GeneralName(X500Name dirName)
    {
        obj = dirName;
        tag = 4;
    }

    public GeneralName(int tag, ASN1Encodable name)
    {
        obj = name;
        this.tag = tag;
    }

    public GeneralName(int tag, String name)
    {
        this.tag = tag;
        if(tag == 1 || tag == 2 || tag == 6)
            obj = new DERIA5String(name);
        else
        if(tag == 8)
            obj = new ASN1ObjectIdentifier(name);
        else
        if(tag == 4)
            obj = new X500Name(name);
        else
        if(tag == 7)
        {
            byte enc[] = toGeneralNameEncoding(name);
            if(enc != null)
                obj = new DEROctetString(enc);
            else
                throw new IllegalArgumentException("IP Address is invalid");
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("can't process String for tag: ").append(tag).toString());
        }
    }

    public static GeneralName getInstance(Object obj)
    {
        if(obj == null || (obj instanceof GeneralName))
            return (GeneralName)obj;
        if(obj instanceof ASN1TaggedObject)
        {
            ASN1TaggedObject tagObj = (ASN1TaggedObject)obj;
            int tag = tagObj.getTagNo();
            switch(tag)
            {
            case 0: // '\0'
                return new GeneralName(tag, ASN1Sequence.getInstance(tagObj, false));

            case 1: // '\001'
                return new GeneralName(tag, DERIA5String.getInstance(tagObj, false));

            case 2: // '\002'
                return new GeneralName(tag, DERIA5String.getInstance(tagObj, false));

            case 3: // '\003'
                throw new IllegalArgumentException((new StringBuilder()).append("unknown tag: ").append(tag).toString());

            case 4: // '\004'
                return new GeneralName(tag, X500Name.getInstance(tagObj, true));

            case 5: // '\005'
                return new GeneralName(tag, ASN1Sequence.getInstance(tagObj, false));

            case 6: // '\006'
                return new GeneralName(tag, DERIA5String.getInstance(tagObj, false));

            case 7: // '\007'
                return new GeneralName(tag, ASN1OctetString.getInstance(tagObj, false));

            case 8: // '\b'
                return new GeneralName(tag, ASN1ObjectIdentifier.getInstance(tagObj, false));
            }
        }
        if(obj instanceof byte[])
            try
            {
                return getInstance(ASN1Primitive.fromByteArray((byte[])(byte[])obj));
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException("unable to parse encoded general name");
            }
        else
            throw new IllegalArgumentException((new StringBuilder()).append("unknown object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    public static GeneralName getInstance(ASN1TaggedObject tagObj, boolean explicit)
    {
        return getInstance(ASN1TaggedObject.getInstance(tagObj, true));
    }

    public int getTagNo()
    {
        return tag;
    }

    public ASN1Encodable getName()
    {
        return obj;
    }

    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append(tag);
        buf.append(": ");
        switch(tag)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 6: // '\006'
            buf.append(DERIA5String.getInstance(obj).getString());
            break;

        case 4: // '\004'
            buf.append(X500Name.getInstance(obj).toString());
            break;

        case 3: // '\003'
        case 5: // '\005'
        default:
            buf.append(obj.toString());
            break;
        }
        return buf.toString();
    }

    private byte[] toGeneralNameEncoding(String ip)
    {
        if(IPAddress.isValidIPv6WithNetmask(ip) || IPAddress.isValidIPv6(ip))
        {
            int slashIndex = ip.indexOf('/');
            byte addr[];
            int parsedIp[];
            if(slashIndex < 0)
            {
                addr = new byte[16];
                parsedIp = parseIPv6(ip);
                copyInts(parsedIp, addr, 0);
                return addr;
            }
            addr = new byte[32];
            parsedIp = parseIPv6(ip.substring(0, slashIndex));
            copyInts(parsedIp, addr, 0);
            String mask = ip.substring(slashIndex + 1);
            if(mask.indexOf(':') > 0)
                parsedIp = parseIPv6(mask);
            else
                parsedIp = parseMask(mask);
            copyInts(parsedIp, addr, 16);
            return addr;
        }
        if(IPAddress.isValidIPv4WithNetmask(ip) || IPAddress.isValidIPv4(ip))
        {
            int slashIndex = ip.indexOf('/');
            byte addr[];
            if(slashIndex < 0)
            {
                addr = new byte[4];
                parseIPv4(ip, addr, 0);
                return addr;
            }
            addr = new byte[8];
            parseIPv4(ip.substring(0, slashIndex), addr, 0);
            String mask = ip.substring(slashIndex + 1);
            if(mask.indexOf('.') > 0)
                parseIPv4(mask, addr, 4);
            else
                parseIPv4Mask(mask, addr, 4);
            return addr;
        } else
        {
            return null;
        }
    }

    private void parseIPv4Mask(String mask, byte addr[], int offset)
    {
        int maskVal = Integer.parseInt(mask);
        for(int i = 0; i != maskVal; i++)
            addr[i / 8 + offset] |= 1 << 7 - i % 8;

    }

    private void parseIPv4(String ip, byte addr[], int offset)
    {
        StringTokenizer sTok = new StringTokenizer(ip, "./");
        int index = 0;
        while(sTok.hasMoreTokens()) 
            addr[offset + index++] = (byte)Integer.parseInt(sTok.nextToken());
    }

    private int[] parseMask(String mask)
    {
        int res[] = new int[8];
        int maskVal = Integer.parseInt(mask);
        for(int i = 0; i != maskVal; i++)
            res[i / 16] |= 1 << 15 - i % 16;

        return res;
    }

    private void copyInts(int parsedIp[], byte addr[], int offSet)
    {
        for(int i = 0; i != parsedIp.length; i++)
        {
            addr[i * 2 + offSet] = (byte)(parsedIp[i] >> 8);
            addr[i * 2 + 1 + offSet] = (byte)parsedIp[i];
        }

    }

    private int[] parseIPv6(String ip)
    {
        StringTokenizer sTok = new StringTokenizer(ip, ":", true);
        int index = 0;
        int val[] = new int[8];
        if(ip.charAt(0) == ':' && ip.charAt(1) == ':')
            sTok.nextToken();
        int doubleColon = -1;
        do
        {
            if(!sTok.hasMoreTokens())
                break;
            String e = sTok.nextToken();
            if(e.equals(":"))
            {
                doubleColon = index;
                val[index++] = 0;
            } else
            if(e.indexOf('.') < 0)
            {
                val[index++] = Integer.parseInt(e, 16);
                if(sTok.hasMoreTokens())
                    sTok.nextToken();
            } else
            {
                StringTokenizer eTok = new StringTokenizer(e, ".");
                val[index++] = Integer.parseInt(eTok.nextToken()) << 8 | Integer.parseInt(eTok.nextToken());
                val[index++] = Integer.parseInt(eTok.nextToken()) << 8 | Integer.parseInt(eTok.nextToken());
            }
        } while(true);
        if(index != val.length)
        {
            System.arraycopy(val, doubleColon, val, val.length - (index - doubleColon), index - doubleColon);
            for(int i = doubleColon; i != val.length - (index - doubleColon); i++)
                val[i] = 0;

        }
        return val;
    }

    public ASN1Primitive toASN1Primitive()
    {
        if(tag == 4)
            return new DERTaggedObject(true, tag, obj);
        else
            return new DERTaggedObject(false, tag, obj);
    }

    public static final int otherName = 0;
    public static final int rfc822Name = 1;
    public static final int dNSName = 2;
    public static final int x400Address = 3;
    public static final int directoryName = 4;
    public static final int ediPartyName = 5;
    public static final int uniformResourceIdentifier = 6;
    public static final int iPAddress = 7;
    public static final int registeredID = 8;
    private ASN1Encodable obj;
    private int tag;
}
