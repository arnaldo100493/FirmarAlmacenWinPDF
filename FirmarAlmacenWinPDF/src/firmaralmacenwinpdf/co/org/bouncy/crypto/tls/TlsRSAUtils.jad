// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsRSAUtils.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.InvalidCipherTextException;
import co.org.bouncy.crypto.encodings.PKCS1Encoding;
import co.org.bouncy.crypto.engines.RSABlindedEngine;
import co.org.bouncy.crypto.params.ParametersWithRandom;
import co.org.bouncy.crypto.params.RSAKeyParameters;
import java.io.IOException;
import java.io.OutputStream;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsFatalAlert, TlsContext, TlsUtils, ProtocolVersion

public class TlsRSAUtils
{

    public TlsRSAUtils()
    {
    }

    public static byte[] generateEncryptedPreMasterSecret(TlsContext context, RSAKeyParameters rsaServerPublicKey, OutputStream output)
        throws IOException
    {
        byte premasterSecret[] = new byte[48];
        context.getSecureRandom().nextBytes(premasterSecret);
        TlsUtils.writeVersion(context.getClientVersion(), premasterSecret, 0);
        PKCS1Encoding encoding = new PKCS1Encoding(new RSABlindedEngine());
        encoding.init(true, new ParametersWithRandom(rsaServerPublicKey, context.getSecureRandom()));
        try
        {
            byte encryptedPreMasterSecret[] = encoding.processBlock(premasterSecret, 0, premasterSecret.length);
            if(context.getServerVersion().isSSL())
                output.write(encryptedPreMasterSecret);
            else
                TlsUtils.writeOpaque16(encryptedPreMasterSecret, output);
        }
        catch(InvalidCipherTextException e)
        {
            throw new TlsFatalAlert((short)80);
        }
        return premasterSecret;
    }
}
