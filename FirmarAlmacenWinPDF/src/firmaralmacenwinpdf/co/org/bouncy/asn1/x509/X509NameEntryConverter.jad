// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509NameEntryConverter.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.util.Strings;
import java.io.IOException;

public abstract class X509NameEntryConverter
{

    public X509NameEntryConverter()
    {
    }

    protected ASN1Primitive convertHexEncoded(String str, int off)
        throws IOException
    {
        str = Strings.toLowerCase(str);
        byte data[] = new byte[(str.length() - off) / 2];
        for(int index = 0; index != data.length; index++)
        {
            char left = str.charAt(index * 2 + off);
            char right = str.charAt(index * 2 + off + 1);
            if(left < 'a')
                data[index] = (byte)(left - 48 << 4);
            else
                data[index] = (byte)((left - 97) + 10 << 4);
            if(right < 'a')
                data[index] |= (byte)(right - 48);
            else
                data[index] |= (byte)((right - 97) + 10);
        }

        ASN1InputStream aIn = new ASN1InputStream(data);
        return aIn.readObject();
    }

    protected boolean canBePrintable(String str)
    {
        return DERPrintableString.isPrintableString(str);
    }

    public abstract ASN1Primitive getConvertedValue(ASN1ObjectIdentifier asn1objectidentifier, String s);
}
