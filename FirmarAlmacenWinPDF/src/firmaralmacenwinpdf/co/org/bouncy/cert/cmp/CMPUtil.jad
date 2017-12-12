// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMPUtil.java

package co.org.bouncy.cert.cmp;

import co.org.bouncy.asn1.ASN1Encodable;
import co.org.bouncy.asn1.DEROutputStream;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cert.cmp:
//            CMPRuntimeException

class CMPUtil
{

    CMPUtil()
    {
    }

    static void derEncodeToStream(ASN1Encodable obj, OutputStream stream)
    {
        DEROutputStream dOut = new DEROutputStream(stream);
        try
        {
            dOut.writeObject(obj);
            dOut.close();
        }
        catch(IOException e)
        {
            throw new CMPRuntimeException((new StringBuilder()).append("unable to DER encode object: ").append(e.getMessage()).toString(), e);
        }
    }
}
