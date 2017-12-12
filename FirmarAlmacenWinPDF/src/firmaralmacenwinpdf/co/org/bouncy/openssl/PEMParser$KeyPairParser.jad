// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMParser.java

package co.org.bouncy.openssl;

import co.org.bouncy.util.encoders.Hex;
import co.org.bouncy.util.io.pem.*;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMEncryptedKeyPair, PEMException, PEMKeyPairParser, PEMParser

private class PEMParser$KeyPairParser
    implements PemObjectParser
{

    public Object parseObject(PemObject obj)
        throws IOException
    {
        boolean isEncrypted = false;
        String dekInfo = null;
        List headers = obj.getHeaders();
        Iterator it = headers.iterator();
        do
        {
            if(!it.hasNext())
                break;
            PemHeader hdr = (PemHeader)it.next();
            if(hdr.getName().equals("Proc-Type") && hdr.getValue().equals("4,ENCRYPTED"))
                isEncrypted = true;
            else
            if(hdr.getName().equals("DEK-Info"))
                dekInfo = hdr.getValue();
        } while(true);
        byte keyBytes[] = obj.getContent();
        try
        {
            if(isEncrypted)
            {
                StringTokenizer tknz = new StringTokenizer(dekInfo, ",");
                String dekAlgName = tknz.nextToken();
                byte iv[] = Hex.decode(tknz.nextToken());
                return new PEMEncryptedKeyPair(dekAlgName, iv, keyBytes, pemKeyPairParser);
            }
        }
        catch(IOException e)
        {
            if(isEncrypted)
                throw new PEMException("exception decoding - please check password and data.", e);
            else
                throw new PEMException(e.getMessage(), e);
        }
        catch(IllegalArgumentException e)
        {
            if(isEncrypted)
                throw new PEMException("exception decoding - please check password and data.", e);
            else
                throw new PEMException(e.getMessage(), e);
        }
        return pemKeyPairParser.parse(keyBytes);
    }

    private final PEMKeyPairParser pemKeyPairParser;
    final PEMParser this$0;

    public PEMParser$KeyPairParser(PEMKeyPairParser pemKeyPairParser)
    {
        this$0 = PEMParser.this;
        super();
        this.pemKeyPairParser = pemKeyPairParser;
    }
}
