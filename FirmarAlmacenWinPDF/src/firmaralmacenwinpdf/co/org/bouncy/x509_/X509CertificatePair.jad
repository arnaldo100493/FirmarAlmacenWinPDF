// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509CertificatePair.java

package co.org.bouncy.x509_;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.CertificatePair;
import co.org.bouncy.jce.provider.X509CertificateObject;
import java.io.IOException;
import java.security.cert.*;

// Referenced classes of package co.org.bouncy.x509_:
//            ExtCertificateEncodingException

public class X509CertificatePair
{

    public X509CertificatePair(X509Certificate forward, X509Certificate reverse)
    {
        this.forward = forward;
        this.reverse = reverse;
    }

    public X509CertificatePair(CertificatePair pair)
        throws CertificateParsingException
    {
        if(pair.getForward() != null)
            forward = new X509CertificateObject(pair.getForward());
        if(pair.getReverse() != null)
            reverse = new X509CertificateObject(pair.getReverse());
    }

    public byte[] getEncoded()
        throws CertificateEncodingException
    {
        Certificate f = null;
        Certificate r = null;
        try
        {
            if(forward != null)
            {
                f = Certificate.getInstance((new ASN1InputStream(forward.getEncoded())).readObject());
                if(f == null)
                    throw new CertificateEncodingException("unable to get encoding for forward");
            }
            if(reverse != null)
            {
                r = Certificate.getInstance((new ASN1InputStream(reverse.getEncoded())).readObject());
                if(r == null)
                    throw new CertificateEncodingException("unable to get encoding for reverse");
            }
            return (new CertificatePair(f, r)).getEncoded("DER");
        }
        catch(IllegalArgumentException e)
        {
            throw new ExtCertificateEncodingException(e.toString(), e);
        }
        catch(IOException e)
        {
            throw new ExtCertificateEncodingException(e.toString(), e);
        }
    }

    public X509Certificate getForward()
    {
        return forward;
    }

    public X509Certificate getReverse()
    {
        return reverse;
    }

    public boolean equals(Object o)
    {
        if(o == null)
            return false;
        if(!(o instanceof X509CertificatePair))
            return false;
        X509CertificatePair pair = (X509CertificatePair)o;
        boolean equalReverse = true;
        boolean equalForward = true;
        if(forward != null)
            equalForward = forward.equals(pair.forward);
        else
        if(pair.forward != null)
            equalForward = false;
        if(reverse != null)
            equalReverse = reverse.equals(pair.reverse);
        else
        if(pair.reverse != null)
            equalReverse = false;
        return equalForward && equalReverse;
    }

    public int hashCode()
    {
        int hash = -1;
        if(forward != null)
            hash ^= forward.hashCode();
        if(reverse != null)
        {
            hash *= 17;
            hash ^= reverse.hashCode();
        }
        return hash;
    }

    private X509Certificate forward;
    private X509Certificate reverse;
}
