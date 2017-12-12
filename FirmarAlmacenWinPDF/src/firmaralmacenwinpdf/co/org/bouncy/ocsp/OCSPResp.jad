// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPResp.java

package co.org.bouncy.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.*;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.ocsp:
//            BasicOCSPResp, OCSPException

/**
 * @deprecated Class OCSPResp is deprecated
 */

public class OCSPResp
{

    /**
     * @deprecated Method OCSPResp is deprecated
     */

    public OCSPResp(OCSPResponse resp)
    {
        this.resp = resp;
    }

    /**
     * @deprecated Method OCSPResp is deprecated
     */

    public OCSPResp(byte resp[])
        throws IOException
    {
        this(new ASN1InputStream(resp));
    }

    /**
     * @deprecated Method OCSPResp is deprecated
     */

    public OCSPResp(InputStream in)
        throws IOException
    {
        this(new ASN1InputStream(in));
    }

    private OCSPResp(ASN1InputStream aIn)
        throws IOException
    {
        try
        {
            resp = OCSPResponse.getInstance(aIn.readObject());
        }
        catch(IllegalArgumentException e)
        {
            throw new IOException((new StringBuilder()).append("malformed response: ").append(e.getMessage()).toString());
        }
        catch(ClassCastException e)
        {
            throw new IOException((new StringBuilder()).append("malformed response: ").append(e.getMessage()).toString());
        }
    }

    public int getStatus()
    {
        return resp.getResponseStatus().getValue().intValue();
    }

    public Object getResponseObject()
        throws OCSPException
    {
        ResponseBytes rb = resp.getResponseBytes();
        if(rb == null)
            return null;
        if(rb.getResponseType().equals(OCSPObjectIdentifiers.id_pkix_ocsp_basic))
            try
            {
                ASN1Primitive obj = ASN1Primitive.fromByteArray(rb.getResponse().getOctets());
                return new BasicOCSPResp(BasicOCSPResponse.getInstance(obj));
            }
            catch(Exception e)
            {
                throw new OCSPException((new StringBuilder()).append("problem decoding object: ").append(e).toString(), e);
            }
        else
            return rb.getResponse();
    }

    public byte[] getEncoded()
        throws IOException
    {
        return resp.getEncoded();
    }

    public boolean equals(Object o)
    {
        if(o == this)
            return true;
        if(!(o instanceof OCSPResp))
        {
            return false;
        } else
        {
            OCSPResp r = (OCSPResp)o;
            return resp.equals(r.resp);
        }
    }

    public int hashCode()
    {
        return resp.hashCode();
    }

    private OCSPResponse resp;
}
