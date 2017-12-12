// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMEncryptedKeyPair.java

package co.org.bouncy.openssl;

import co.org.bouncy.operator.OperatorCreationException;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMException, PEMDecryptorProvider, PEMDecryptor, PEMKeyPairParser, 
//            PEMKeyPair

public class PEMEncryptedKeyPair
{

    PEMEncryptedKeyPair(String dekAlgName, byte iv[], byte keyBytes[], PEMKeyPairParser parser)
    {
        this.dekAlgName = dekAlgName;
        this.iv = iv;
        this.keyBytes = keyBytes;
        this.parser = parser;
    }

    public PEMKeyPair decryptKeyPair(PEMDecryptorProvider keyDecryptorProvider)
        throws IOException
    {
        try
        {
            PEMDecryptor keyDecryptor = keyDecryptorProvider.get(dekAlgName);
            return parser.parse(keyDecryptor.decrypt(keyBytes, iv));
        }
        catch(IOException e)
        {
            throw e;
        }
        catch(OperatorCreationException e)
        {
            throw new PEMException((new StringBuilder()).append("cannot create extraction operator: ").append(e.getMessage()).toString(), e);
        }
        catch(Exception e)
        {
            throw new PEMException((new StringBuilder()).append("exception processing key pair: ").append(e.getMessage()).toString(), e);
        }
    }

    private final String dekAlgName;
    private final byte iv[];
    private final byte keyBytes[];
    private final PEMKeyPairParser parser;
}
