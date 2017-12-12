// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TimeStampRequest.java

package co.org.bouncy.tsp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.tsp.MessageImprint;
import co.org.bouncy.asn1.tsp.TimeStampReq;
import co.org.bouncy.asn1.x509.*;
import java.io.*;
import java.math.BigInteger;
import java.security.NoSuchProviderException;
import java.util.*;

// Referenced classes of package co.org.bouncy.tsp:
//            TSPValidationException, TSPException, TSPUtil

public class TimeStampRequest
{

    public TimeStampRequest(TimeStampReq req)
    {
        this.req = req;
        extensions = req.getExtensions();
    }

    public TimeStampRequest(byte req[])
        throws IOException
    {
        this(((InputStream) (new ByteArrayInputStream(req))));
    }

    public TimeStampRequest(InputStream in)
        throws IOException
    {
        try
        {
            req = TimeStampReq.getInstance((new ASN1InputStream(in)).readObject());
        }
        catch(ClassCastException e)
        {
            throw new IOException((new StringBuilder()).append("malformed request: ").append(e).toString());
        }
        catch(IllegalArgumentException e)
        {
            throw new IOException((new StringBuilder()).append("malformed request: ").append(e).toString());
        }
    }

    public int getVersion()
    {
        return req.getVersion().getValue().intValue();
    }

    public ASN1ObjectIdentifier getMessageImprintAlgOID()
    {
        return req.getMessageImprint().getHashAlgorithm().getAlgorithm();
    }

    public byte[] getMessageImprintDigest()
    {
        return req.getMessageImprint().getHashedMessage();
    }

    public ASN1ObjectIdentifier getReqPolicy()
    {
        if(req.getReqPolicy() != null)
            return req.getReqPolicy();
        else
            return null;
    }

    public BigInteger getNonce()
    {
        if(req.getNonce() != null)
            return req.getNonce().getValue();
        else
            return null;
    }

    public boolean getCertReq()
    {
        if(req.getCertReq() != null)
            return req.getCertReq().isTrue();
        else
            return false;
    }

    /**
     * @deprecated Method validate is deprecated
     */

    public void validate(Set algorithms, Set policies, Set extensions, String provider)
        throws TSPException, NoSuchProviderException
    {
        validate(algorithms, policies, extensions);
    }

    public void validate(Set algorithms, Set policies, Set extensions)
        throws TSPException
    {
label0:
        {
            algorithms = convert(algorithms);
            policies = convert(policies);
            extensions = convert(extensions);
            if(!algorithms.contains(getMessageImprintAlgOID()))
                throw new TSPValidationException("request contains unknown algorithm.", 128);
            if(policies != null && getReqPolicy() != null && !policies.contains(getReqPolicy()))
                throw new TSPValidationException("request contains unknown policy.", 256);
            if(getExtensions() == null || extensions == null)
                break label0;
            Enumeration en = getExtensions().oids();
            String oid;
            do
            {
                if(!en.hasMoreElements())
                    break label0;
                oid = ((DERObjectIdentifier)en.nextElement()).getId();
            } while(extensions.contains(oid));
            throw new TSPValidationException("request contains unknown extension.", 0x800000);
        }
        int digestLength = TSPUtil.getDigestLength(getMessageImprintAlgOID().getId());
        if(digestLength != getMessageImprintDigest().length)
            throw new TSPValidationException("imprint digest the wrong length.", 4);
        else
            return;
    }

    public byte[] getEncoded()
        throws IOException
    {
        return req.getEncoded();
    }

    Extensions getExtensions()
    {
        return extensions;
    }

    public boolean hasExtensions()
    {
        return extensions != null;
    }

    public Extension getExtension(ASN1ObjectIdentifier oid)
    {
        if(extensions != null)
            return extensions.getExtension(oid);
        else
            return null;
    }

    public List getExtensionOIDs()
    {
        return TSPUtil.getExtensionOIDs(extensions);
    }

    public byte[] getExtensionValue(String oid)
    {
        Extensions exts = req.getExtensions();
        if(exts != null)
        {
            Extension ext = exts.getExtension(new ASN1ObjectIdentifier(oid));
            if(ext != null)
                try
                {
                    return ext.getExtnValue().getEncoded();
                }
                catch(Exception e)
                {
                    throw new RuntimeException((new StringBuilder()).append("error encoding ").append(e.toString()).toString());
                }
        }
        return null;
    }

    public Set getNonCriticalExtensionOIDs()
    {
        if(extensions == null)
            return EMPTY_SET;
        else
            return Collections.unmodifiableSet(new HashSet(Arrays.asList(extensions.getNonCriticalExtensionOIDs())));
    }

    public Set getCriticalExtensionOIDs()
    {
        if(extensions == null)
            return EMPTY_SET;
        else
            return Collections.unmodifiableSet(new HashSet(Arrays.asList(extensions.getCriticalExtensionOIDs())));
    }

    private Set convert(Set orig)
    {
        if(orig == null)
            return orig;
        Set con = new HashSet(orig.size());
        for(Iterator it = orig.iterator(); it.hasNext();)
        {
            Object o = it.next();
            if(o instanceof String)
                con.add(new ASN1ObjectIdentifier((String)o));
            else
                con.add(o);
        }

        return con;
    }

    private static Set EMPTY_SET = Collections.unmodifiableSet(new HashSet());
    private TimeStampReq req;
    private Extensions extensions;

}
