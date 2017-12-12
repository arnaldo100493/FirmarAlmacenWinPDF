// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS8EncryptedPrivateKeyInfo.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.pkcs.EncryptedPrivateKeyInfo;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.cert.CertIOException;
import co.org.bouncy.operator.InputDecryptor;
import co.org.bouncy.operator.InputDecryptorProvider;
import co.org.bouncy.util.io.Streams;
import java.io.ByteArrayInputStream;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.pkcs:
//            PKCSException

public class PKCS8EncryptedPrivateKeyInfo
{

    private static EncryptedPrivateKeyInfo parseBytes(byte pkcs8Encoding[])
        throws IOException
    {
        try
        {
            return EncryptedPrivateKeyInfo.getInstance(ASN1Primitive.fromByteArray(pkcs8Encoding));
        }
        catch(ClassCastException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
    }

    public PKCS8EncryptedPrivateKeyInfo(EncryptedPrivateKeyInfo encryptedPrivateKeyInfo)
    {
        this.encryptedPrivateKeyInfo = encryptedPrivateKeyInfo;
    }

    public PKCS8EncryptedPrivateKeyInfo(byte encryptedPrivateKeyInfo[])
        throws IOException
    {
        this(parseBytes(encryptedPrivateKeyInfo));
    }

    public EncryptedPrivateKeyInfo toASN1Structure()
    {
        return encryptedPrivateKeyInfo;
    }

    public byte[] getEncoded()
        throws IOException
    {
        return encryptedPrivateKeyInfo.getEncoded();
    }

    public PrivateKeyInfo decryptPrivateKeyInfo(InputDecryptorProvider inputDecryptorProvider)
        throws PKCSException
    {
        try
        {
            InputDecryptor decrytor = inputDecryptorProvider.get(encryptedPrivateKeyInfo.getEncryptionAlgorithm());
            ByteArrayInputStream encIn = new ByteArrayInputStream(encryptedPrivateKeyInfo.getEncryptedData());
            return PrivateKeyInfo.getInstance(Streams.readAll(decrytor.getInputStream(encIn)));
        }
        catch(Exception e)
        {
            throw new PKCSException((new StringBuilder()).append("unable to read encrypted data: ").append(e.getMessage()).toString(), e);
        }
    }

    private EncryptedPrivateKeyInfo encryptedPrivateKeyInfo;
}
