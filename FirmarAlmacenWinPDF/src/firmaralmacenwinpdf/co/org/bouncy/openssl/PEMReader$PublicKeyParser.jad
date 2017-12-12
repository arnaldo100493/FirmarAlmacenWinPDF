// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemObjectParser;
import java.io.IOException;
import java.security.*;
import java.security.spec.*;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMReader

private class PEMReader$PublicKeyParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        KeySpec keySpec = new X509EncodedKeySpec(obj.getContent());
        String algorithms[] = {
            "DSA", "RSA"
        };
        for(int i = 0; i < algorithms.length; i++)
            try
            {
                KeyFactory keyFact = KeyFactory.getInstance(algorithms[i], provider);
                java.security.PublicKey pubKey = keyFact.generatePublic(keySpec);
                return pubKey;
            }
            catch(NoSuchAlgorithmException e) { }
            catch(InvalidKeySpecException e) { }
            catch(NoSuchProviderException e)
            {
                throw new RuntimeException((new StringBuilder()).append("can't find provider ").append(provider).toString());
            }

        return null;
    }

    private String provider;
    final PEMReader this$0;

    public PEMReader$PublicKeyParser(String provider)
    {
        this$0 = PEMReader.this;
        super();
        this.provider = provider;
    }
}
