// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Certificate.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.ASN1InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsUtils, TlsProtocol

public class Certificate
{

    public Certificate(co.org.bouncy.asn1.x509.Certificate certificateList[])
    {
        if(certificateList == null)
        {
            throw new IllegalArgumentException("'certificateList' cannot be null");
        } else
        {
            this.certificateList = certificateList;
            return;
        }
    }

    /**
     * @deprecated Method getCerts is deprecated
     */

    public co.org.bouncy.asn1.x509.Certificate[] getCerts()
    {
        return clone(certificateList);
    }

    public co.org.bouncy.asn1.x509.Certificate[] getCertificateList()
    {
        return clone(certificateList);
    }

    public co.org.bouncy.asn1.x509.Certificate getCertificateAt(int index)
    {
        return certificateList[index];
    }

    public int getLength()
    {
        return certificateList.length;
    }

    public boolean isEmpty()
    {
        return certificateList.length == 0;
    }

    public void encode(OutputStream output)
        throws IOException
    {
        Vector encCerts = new Vector(certificateList.length);
        int totalLength = 0;
        for(int i = 0; i < certificateList.length; i++)
        {
            byte encCert[] = certificateList[i].getEncoded("DER");
            encCerts.addElement(encCert);
            totalLength += encCert.length + 3;
        }

        TlsUtils.writeUint24(totalLength, output);
        for(int i = 0; i < encCerts.size(); i++)
        {
            byte encCert[] = (byte[])(byte[])encCerts.elementAt(i);
            TlsUtils.writeOpaque24(encCert, output);
        }

    }

    public static Certificate parse(InputStream input)
        throws IOException
    {
        int left = TlsUtils.readUint24(input);
        if(left == 0)
            return EMPTY_CHAIN;
        Vector tmp = new Vector();
        while(left > 0) 
        {
            int size = TlsUtils.readUint24(input);
            left -= 3 + size;
            byte buf[] = TlsUtils.readFully(size, input);
            ByteArrayInputStream bis = new ByteArrayInputStream(buf);
            co.org.bouncy.asn1.ASN1Primitive asn1 = (new ASN1InputStream(bis)).readObject();
            TlsProtocol.assertEmpty(bis);
            tmp.addElement(co.org.bouncy.asn1.x509.Certificate.getInstance(asn1));
        }
        co.org.bouncy.asn1.x509.Certificate certs[] = new co.org.bouncy.asn1.x509.Certificate[tmp.size()];
        for(int i = 0; i < tmp.size(); i++)
            certs[i] = (co.org.bouncy.asn1.x509.Certificate)tmp.elementAt(i);

        return new Certificate(certs);
    }

    private co.org.bouncy.asn1.x509.Certificate[] clone(co.org.bouncy.asn1.x509.Certificate list[])
    {
        co.org.bouncy.asn1.x509.Certificate rv[] = new co.org.bouncy.asn1.x509.Certificate[list.length];
        System.arraycopy(list, 0, rv, 0, rv.length);
        return rv;
    }

    public static final Certificate EMPTY_CHAIN = new Certificate(new co.org.bouncy.asn1.x509.Certificate[0]);
    protected co.org.bouncy.asn1.x509.Certificate certificateList[];

}
