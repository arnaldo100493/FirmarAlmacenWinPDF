// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMReader.java

package co.org.bouncy.openssl;

import co.org.bouncy.asn1.ASN1Primitive;
import co.org.bouncy.asn1.ASN1Sequence;
import co.org.bouncy.util.encoders.Hex;
import co.org.bouncy.util.io.pem.*;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.org.bouncy.openssl:
//            PasswordException, PEMException, PEMReader, PasswordFinder

private abstract class PEMReader$KeyPairParser
    implements PemObjectParser
{

    protected ASN1Sequence readKeyPair(PemObject obj)
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
        if(isEncrypted)
        {
            if(PEMReader.access$400(PEMReader.this) == null)
                throw new PasswordException("No password finder specified, but a password is required");
            char password[] = PEMReader.access$400(PEMReader.this).getPassword();
            if(password == null)
                throw new PasswordException("Password is null, but a password is required");
            StringTokenizer tknz = new StringTokenizer(dekInfo, ",");
            String dekAlgName = tknz.nextToken();
            byte iv[] = Hex.decode(tknz.nextToken());
            keyBytes = PEMReader.crypt(false, symProvider, keyBytes, password, dekAlgName, iv);
        }
        try
        {
            return ASN1Sequence.getInstance(ASN1Primitive.fromByteArray(keyBytes));
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
    }

    protected String symProvider;
    final PEMReader this$0;

    public PEMReader$KeyPairParser(String symProvider)
    {
        this$0 = PEMReader.this;
        super();
        this.symProvider = symProvider;
    }
}
