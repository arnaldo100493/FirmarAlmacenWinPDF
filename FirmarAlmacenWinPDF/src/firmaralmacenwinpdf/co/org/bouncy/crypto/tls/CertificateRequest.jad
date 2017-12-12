// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateRequest.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.x500.X500Name;
import java.io.*;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsUtils

public class CertificateRequest
{

    public CertificateRequest(short certificateTypes[], Vector certificateAuthorities)
    {
        this.certificateTypes = certificateTypes;
        this.certificateAuthorities = certificateAuthorities;
    }

    public short[] getCertificateTypes()
    {
        return certificateTypes;
    }

    public Vector getCertificateAuthorities()
    {
        return certificateAuthorities;
    }

    public void encode(OutputStream output)
        throws IOException
    {
        if(certificateTypes == null || certificateTypes.length == 0)
        {
            TlsUtils.writeUint8((short)0, output);
        } else
        {
            TlsUtils.writeUint8((short)certificateTypes.length, output);
            TlsUtils.writeUint8Array(certificateTypes, output);
        }
        if(certificateAuthorities == null || certificateAuthorities.isEmpty())
        {
            TlsUtils.writeUint16(0, output);
        } else
        {
            Vector encDNs = new Vector(certificateAuthorities.size());
            int totalLength = 0;
            for(int i = 0; i < certificateAuthorities.size(); i++)
            {
                X500Name authorityDN = (X500Name)certificateAuthorities.elementAt(i);
                byte encDN[] = authorityDN.getEncoded("DER");
                encDNs.addElement(encDN);
                totalLength += encDN.length;
            }

            TlsUtils.writeUint16(totalLength, output);
            for(int i = 0; i < encDNs.size(); i++)
            {
                byte encDN[] = (byte[])(byte[])encDNs.elementAt(i);
                output.write(encDN);
            }

        }
    }

    public static CertificateRequest parse(InputStream input)
        throws IOException
    {
        int numTypes = TlsUtils.readUint8(input);
        short certificateTypes[] = new short[numTypes];
        for(int i = 0; i < numTypes; i++)
            certificateTypes[i] = TlsUtils.readUint8(input);

        byte authorities[] = TlsUtils.readOpaque16(input);
        Vector authorityDNs = new Vector();
        byte dnBytes[];
        for(ByteArrayInputStream bis = new ByteArrayInputStream(authorities); bis.available() > 0; authorityDNs.addElement(X500Name.getInstance(ASN1Primitive.fromByteArray(dnBytes))))
            dnBytes = TlsUtils.readOpaque16(bis);

        return new CertificateRequest(certificateTypes, authorityDNs);
    }

    private short certificateTypes[];
    private Vector certificateAuthorities;
}
