// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OCSPResp.java

package co.org.bouncy.cert.ocsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.*;
import co.org.bouncy.cert.CertIOException;
import java.io.*;
import java.math.BigInteger;

// Referenced classes of package co.org.bouncy.cert.ocsp:
//            BasicOCSPResp, OCSPException

public class OCSPResp
{

    public OCSPResp(OCSPResponse resp)
    {
        this.resp = resp;
    }

    public OCSPResp(byte resp[])
        throws IOException
    {
        this(((InputStream) (new ByteArrayInputStream(resp))));
    }

    public OCSPResp(InputStream resp)
        throws IOException
    {
        this(new ASN1InputStream(resp));
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
            throw new CertIOException((new StringBuilder()).append("malformed response: ").append(e.getMessage()).toString(), e);
        }
        catch(ClassCastException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed response: ").append(e.getMessage()).toString(), e);
        }
        catch(ASN1Exception e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed response: ").append(e.getMessage()).toString(), e);
        }
        if(resp == null)
            throw new CertIOException("malformed response: no response data found");
        else
            return;
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

    public OCSPResponse toASN1Structure()
    {
        return resp;
    }

    public static final int SUCCESSFUL = 0;
    public static final int MALFORMED_REQUEST = 1;
    public static final int INTERNAL_ERROR = 2;
    public static final int TRY_LATER = 3;
    public static final int SIG_REQUIRED = 5;
    public static final int UNAUTHORIZED = 6;
    private OCSPResponse resp;
}
