// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPRespBuilder.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.ASN1OctetString;
import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.ocsp.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            OCSPResp, BasicOCSPResp, OCSPException

public class OCSPRespBuilder
{

    public OCSPRespBuilder()
    {
    }

    public OCSPResp build(int status, Object response)
        throws OCSPException
    {
        if(response == null)
            return new OCSPResp(new OCSPResponse(new OCSPResponseStatus(status), null));
        if(response instanceof BasicOCSPResp)
        {
            BasicOCSPResp r = (BasicOCSPResp)response;
            ASN1OctetString octs;
            try
            {
                octs = new DEROctetString(r.getEncoded());
            }
            catch(IOException e)
            {
                throw new OCSPException("can't encode object.", e);
            }
            ResponseBytes rb = new ResponseBytes(OCSPObjectIdentifiers.id_pkix_ocsp_basic, octs);
            return new OCSPResp(new OCSPResponse(new OCSPResponseStatus(status), rb));
        } else
        {
            throw new OCSPException("unknown response object");
        }
    }

    public static final int SUCCESSFUL = 0;
    public static final int MALFORMED_REQUEST = 1;
    public static final int INTERNAL_ERROR = 2;
    public static final int TRY_LATER = 3;
    public static final int SIG_REQUIRED = 5;
    public static final int UNAUTHORIZED = 6;
}
