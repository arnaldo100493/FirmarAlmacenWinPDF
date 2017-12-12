// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DTLSProtocol.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.util.Arrays;
import java.io.*;
import java.security.SecureRandom;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert, TlsUtils, TlsProtocol, Certificate

public abstract class DTLSProtocol
{

    protected DTLSProtocol(SecureRandom secureRandom)
    {
        if(secureRandom == null)
        {
            throw new IllegalArgumentException("'secureRandom' cannot be null");
        } else
        {
            this.secureRandom = secureRandom;
            return;
        }
    }

    protected void processFinished(byte body[], byte expected_verify_data[])
        throws IOException
    {
        ByteArrayInputStream buf = new ByteArrayInputStream(body);
        byte verify_data[] = TlsUtils.readFully(expected_verify_data.length, buf);
        TlsProtocol.assertEmpty(buf);
        if(!Arrays.constantTimeAreEqual(expected_verify_data, verify_data))
            throw new TlsFatalAlert((short)40);
        else
            return;
    }

    protected static byte[] generateCertificate(Certificate certificate)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        certificate.encode(buf);
        return buf.toByteArray();
    }

    protected static byte[] generateSupplementalData(Vector supplementalData)
        throws IOException
    {
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        TlsProtocol.writeSupplementalData(buf, supplementalData);
        return buf.toByteArray();
    }

    protected static void validateSelectedCipherSuite(int selectedCipherSuite, short alertDescription)
        throws IOException
    {
        switch(selectedCipherSuite)
        {
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 23: // '\027'
        case 24: // '\030'
        case 138: 
        case 142: 
        case 146: 
        case 49154: 
        case 49159: 
        case 49164: 
        case 49169: 
        case 49174: 
            throw new IllegalStateException("RC4 MUST NOT be used with DTLS");
        }
    }

    protected final SecureRandom secureRandom;
}
