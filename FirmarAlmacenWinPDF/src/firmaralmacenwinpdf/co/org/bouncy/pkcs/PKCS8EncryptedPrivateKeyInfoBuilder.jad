// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKCS8EncryptedPrivateKeyInfoBuilder.java

package co.org.bouncy.pkcs;

import co.org.bouncy.asn1.pkcs.EncryptedPrivateKeyInfo;
import co.org.bouncy.asn1.pkcs.PrivateKeyInfo;
import co.org.bouncy.operator.OutputEncryptor;
import java.io.*;

// Referenced classes of package co.org.bouncy.pkcs:
//            PKCS8EncryptedPrivateKeyInfo

public class PKCS8EncryptedPrivateKeyInfoBuilder
{

    public PKCS8EncryptedPrivateKeyInfoBuilder(PrivateKeyInfo privateKeyInfo)
    {
        this.privateKeyInfo = privateKeyInfo;
    }

    public PKCS8EncryptedPrivateKeyInfo build(OutputEncryptor encryptor)
    {
        try
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            OutputStream cOut = encryptor.getOutputStream(bOut);
            cOut.write(privateKeyInfo.getEncoded());
            cOut.close();
            return new PKCS8EncryptedPrivateKeyInfo(new EncryptedPrivateKeyInfo(encryptor.getAlgorithmIdentifier(), bOut.toByteArray()));
        }
        catch(IOException e)
        {
            throw new IllegalStateException("cannot encode privateKeyInfo");
        }
    }

    private PrivateKeyInfo privateKeyInfo;
}
