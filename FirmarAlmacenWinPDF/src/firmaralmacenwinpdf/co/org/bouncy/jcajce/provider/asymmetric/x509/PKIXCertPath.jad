// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PKIXCertPath.java

package co.org.bouncy.jcajce.provider.asymmetric.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.pkcs.*;
import co.org.bouncy.util.io.pem.PemObject;
import co.org.bouncy.util.io.pem.PemWriter;
import java.io.*;
import java.security.NoSuchProviderException;
import java.security.cert.*;
import java.util.*;
import javax.security.auth.x500.X500Principal;

public class PKIXCertPath extends CertPath
{

    private List sortCerts(List certs)
    {
        if(certs.size() < 2)
            return certs;
        X500Principal issuer = ((X509Certificate)certs.get(0)).getIssuerX500Principal();
        boolean okay = true;
        int i = 1;
        do
        {
            if(i == certs.size())
                break;
            X509Certificate cert = (X509Certificate)certs.get(i);
            if(issuer.equals(cert.getSubjectX500Principal()))
            {
                issuer = ((X509Certificate)certs.get(i)).getIssuerX500Principal();
            } else
            {
                okay = false;
                break;
            }
            i++;
        } while(true);
        if(okay)
            return certs;
        List retList = new ArrayList(certs.size());
        List orig = new ArrayList(certs);
        for(int i = 0; i < certs.size(); i++)
        {
            X509Certificate cert = (X509Certificate)certs.get(i);
            boolean found = false;
            X500Principal subject = cert.getSubjectX500Principal();
            int j = 0;
            do
            {
                if(j == certs.size())
                    break;
                X509Certificate c = (X509Certificate)certs.get(j);
                if(c.getIssuerX500Principal().equals(subject))
                {
                    found = true;
                    break;
                }
                j++;
            } while(true);
            if(!found)
            {
                retList.add(cert);
                certs.remove(i);
            }
        }

        if(retList.size() > 1)
            return orig;
label0:
        for(int i = 0; i != retList.size(); i++)
        {
            issuer = ((X509Certificate)retList.get(i)).getIssuerX500Principal();
            int j = 0;
            do
            {
                if(j >= certs.size())
                    continue label0;
                X509Certificate c = (X509Certificate)certs.get(j);
                if(issuer.equals(c.getSubjectX500Principal()))
                {
                    retList.add(c);
                    certs.remove(j);
                    continue label0;
                }
                j++;
            } while(true);
        }

        if(certs.size() > 0)
            return orig;
        else
            return retList;
    }

    PKIXCertPath(List certificates)
    {
        super("X.509");
        this.certificates = sortCerts(new ArrayList(certificates));
    }

    PKIXCertPath(InputStream inStream, String encoding)
        throws CertificateException
    {
        super("X.509");
        try
        {
            if(encoding.equalsIgnoreCase("PkiPath"))
            {
                ASN1InputStream derInStream = new ASN1InputStream(inStream);
                ASN1Primitive derObject = derInStream.readObject();
                if(!(derObject instanceof ASN1Sequence))
                    throw new CertificateException("input stream does not contain a ASN1 SEQUENCE while reading PkiPath encoded data to load CertPath");
                Enumeration e = ((ASN1Sequence)derObject).getObjects();
                certificates = new ArrayList();
                CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");
                byte encoded[];
                for(; e.hasMoreElements(); certificates.add(0, certFactory.generateCertificate(new ByteArrayInputStream(encoded))))
                {
                    ASN1Encodable element = (ASN1Encodable)e.nextElement();
                    encoded = element.toASN1Primitive().getEncoded("DER");
                }

            } else
            if(encoding.equalsIgnoreCase("PKCS7") || encoding.equalsIgnoreCase("PEM"))
            {
                inStream = new BufferedInputStream(inStream);
                certificates = new ArrayList();
                CertificateFactory certFactory = CertificateFactory.getInstance("X.509", "BC");
                java.security.cert.Certificate cert;
                while((cert = certFactory.generateCertificate(inStream)) != null) 
                    certificates.add(cert);
            } else
            {
                throw new CertificateException((new StringBuilder()).append("unsupported encoding: ").append(encoding).toString());
            }
        }
        catch(IOException ex)
        {
            throw new CertificateException((new StringBuilder()).append("IOException throw while decoding CertPath:\n").append(ex.toString()).toString());
        }
        catch(NoSuchProviderException ex)
        {
            throw new CertificateException((new StringBuilder()).append("BouncyCastle provider not found while trying to get a CertificateFactory:\n").append(ex.toString()).toString());
        }
        certificates = sortCerts(certificates);
    }

    public Iterator getEncodings()
    {
        return certPathEncodings.iterator();
    }

    public byte[] getEncoded()
        throws CertificateEncodingException
    {
        Iterator iter = getEncodings();
        if(iter.hasNext())
        {
            Object enc = iter.next();
            if(enc instanceof String)
                return getEncoded((String)enc);
        }
        return null;
    }

    public byte[] getEncoded(String encoding)
        throws CertificateEncodingException
    {
        if(encoding.equalsIgnoreCase("PkiPath"))
        {
            ASN1EncodableVector v = new ASN1EncodableVector();
            for(ListIterator iter = certificates.listIterator(certificates.size()); iter.hasPrevious(); v.add(toASN1Object((X509Certificate)iter.previous())));
            return toDEREncoded(new DERSequence(v));
        }
        if(encoding.equalsIgnoreCase("PKCS7"))
        {
            ContentInfo encInfo = new ContentInfo(PKCSObjectIdentifiers.data, null);
            ASN1EncodableVector v = new ASN1EncodableVector();
            for(int i = 0; i != certificates.size(); i++)
                v.add(toASN1Object((X509Certificate)certificates.get(i)));

            SignedData sd = new SignedData(new ASN1Integer(1L), new DERSet(), encInfo, new DERSet(v), null, new DERSet());
            return toDEREncoded(new ContentInfo(PKCSObjectIdentifiers.signedData, sd));
        }
        if(encoding.equalsIgnoreCase("PEM"))
        {
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            PemWriter pWrt = new PemWriter(new OutputStreamWriter(bOut));
            try
            {
                for(int i = 0; i != certificates.size(); i++)
                    pWrt.writeObject(new PemObject("CERTIFICATE", ((X509Certificate)certificates.get(i)).getEncoded()));

                pWrt.close();
            }
            catch(Exception e)
            {
                throw new CertificateEncodingException("can't encode certificate for PEM encoded path");
            }
            return bOut.toByteArray();
        } else
        {
            throw new CertificateEncodingException((new StringBuilder()).append("unsupported encoding: ").append(encoding).toString());
        }
    }

    public List getCertificates()
    {
        return Collections.unmodifiableList(new ArrayList(certificates));
    }

    private ASN1Primitive toASN1Object(X509Certificate cert)
        throws CertificateEncodingException
    {
        try
        {
            return (new ASN1InputStream(cert.getEncoded())).readObject();
        }
        catch(Exception e)
        {
            throw new CertificateEncodingException((new StringBuilder()).append("Exception while encoding certificate: ").append(e.toString()).toString());
        }
    }

    private byte[] toDEREncoded(ASN1Encodable obj)
        throws CertificateEncodingException
    {
        try
        {
            return obj.toASN1Primitive().getEncoded("DER");
        }
        catch(IOException e)
        {
            throw new CertificateEncodingException((new StringBuilder()).append("Exception thrown: ").append(e).toString());
        }
    }

    static final List certPathEncodings;
    private List certificates;

    static 
    {
        List encodings = new ArrayList();
        encodings.add("PkiPath");
        encodings.add("PEM");
        encodings.add("PKCS7");
        certPathEncodings = Collections.unmodifiableList(encodings);
    }
}
