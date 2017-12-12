// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PEMWriter.java

package co.org.bouncy.openssl;

import co.org.bouncy.openssl.jcajce.JcaMiscPEMGenerator;
import co.org.bouncy.openssl.jcajce.JcePEMEncryptorBuilder;
import co.org.bouncy.util.io.pem.*;
import java.io.IOException;
import java.io.Writer;
import java.security.SecureRandom;

// Referenced classes of package co.org.bouncy.openssl:
//            PEMEncryptor

public class PEMWriter extends PemWriter
{

    public PEMWriter(Writer out)
    {
        this(out, "BC");
    }

    /**
     * @deprecated Method PEMWriter is deprecated
     */

    public PEMWriter(Writer out, String provider)
    {
        super(out);
        this.provider = provider;
    }

    public void writeObject(Object obj)
        throws IOException
    {
        writeObject(obj, null);
    }

    public void writeObject(Object obj, PEMEncryptor encryptor)
        throws IOException
    {
        try
        {
            super.writeObject(new JcaMiscPEMGenerator(obj, encryptor));
        }
        catch(PemGenerationException e)
        {
            if(e.getCause() instanceof IOException)
                throw (IOException)e.getCause();
            else
                throw e;
        }
    }

    public void writeObject(PemObjectGenerator obj)
        throws IOException
    {
        super.writeObject(obj);
    }

    /**
     * @deprecated Method writeObject is deprecated
     */

    public void writeObject(Object obj, String algorithm, char password[], SecureRandom random)
        throws IOException
    {
        writeObject(obj, (new JcePEMEncryptorBuilder(algorithm)).setSecureRandom(random).setProvider(provider).build(password));
    }

    private String provider;
}
