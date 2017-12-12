// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EncryptedValueParser.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.DERBitString;
import co.org.bouncy.asn1.crmf.EncryptedValue;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.operator.InputDecryptor;
import co.org.bouncy.util.Strings;
import co.org.bouncy.util.io.Streams;
import java.io.*;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            CRMFException, ValueDecryptorGenerator, EncryptedValuePadder

public class EncryptedValueParser
{

    public EncryptedValueParser(EncryptedValue value)
    {
        this.value = value;
    }

    public EncryptedValueParser(EncryptedValue value, EncryptedValuePadder padder)
    {
        this.value = value;
        this.padder = padder;
    }

    private byte[] decryptValue(ValueDecryptorGenerator decGen)
        throws CRMFException
    {
        if(value.getIntendedAlg() != null)
            throw new UnsupportedOperationException();
        if(value.getValueHint() != null)
            throw new UnsupportedOperationException();
        InputDecryptor decryptor = decGen.getValueDecryptor(value.getKeyAlg(), value.getSymmAlg(), value.getEncSymmKey().getBytes());
        InputStream dataIn = decryptor.getInputStream(new ByteArrayInputStream(value.getEncValue().getBytes()));
        byte data[];
        try
        {
            data = Streams.readAll(dataIn);
            if(padder != null)
                return padder.getUnpaddedData(data);
        }
        catch(IOException e)
        {
            throw new CRMFException((new StringBuilder()).append("Cannot parse decrypted data: ").append(e.getMessage()).toString(), e);
        }
        return data;
    }

    public X509CertificateHolder readCertificateHolder(ValueDecryptorGenerator decGen)
        throws CRMFException
    {
        return new X509CertificateHolder(Certificate.getInstance(decryptValue(decGen)));
    }

    public char[] readPassphrase(ValueDecryptorGenerator decGen)
        throws CRMFException
    {
        return Strings.fromUTF8ByteArray(decryptValue(decGen)).toCharArray();
    }

    private EncryptedValue value;
    private EncryptedValuePadder padder;
}
