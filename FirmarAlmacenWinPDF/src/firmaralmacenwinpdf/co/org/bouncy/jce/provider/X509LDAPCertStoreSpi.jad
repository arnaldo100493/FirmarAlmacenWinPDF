// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   X509LDAPCertStoreSpi.java

package co.org.bouncy.jce.provider;

import co.org.bouncy.asn1.ASN1InputStream;
import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.CertificatePair;
import co.org.bouncy.jce.X509LDAPCertStoreParameters;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.cert.*;
import java.util.*;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;
import javax.security.auth.x500.X500Principal;

public class X509LDAPCertStoreSpi extends CertStoreSpi
{

    public X509LDAPCertStoreSpi(CertStoreParameters params)
        throws InvalidAlgorithmParameterException
    {
        super(params);
        if(!(params instanceof X509LDAPCertStoreParameters))
        {
            throw new InvalidAlgorithmParameterException((new StringBuilder()).append(co/org/bouncy/jce/provider/X509LDAPCertStoreSpi.getName()).append(": parameter must be a ").append(co/org/bouncy/jce/X509LDAPCertStoreParameters.getName()).append(" object\n").append(params.toString()).toString());
        } else
        {
            this.params = (X509LDAPCertStoreParameters)params;
            return;
        }
    }

    private DirContext connectLDAP()
        throws NamingException
    {
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial", LDAP_PROVIDER);
        props.setProperty("java.naming.batchsize", "0");
        props.setProperty("java.naming.provider.url", params.getLdapURL());
        props.setProperty("java.naming.factory.url.pkgs", "com.sun.jndi.url");
        props.setProperty("java.naming.referral", REFERRALS_IGNORE);
        props.setProperty("java.naming.security.authentication", "none");
        DirContext ctx = new InitialDirContext(props);
        return ctx;
    }

    private String parseDN(String subject, String subjectAttributeName)
    {
        String temp = subject;
        int begin = temp.toLowerCase().indexOf(subjectAttributeName.toLowerCase());
        temp = temp.substring(begin + subjectAttributeName.length());
        int end = temp.indexOf(',');
        if(end == -1)
            end = temp.length();
        do
        {
            if(temp.charAt(end - 1) != '\\')
                break;
            end = temp.indexOf(',', end + 1);
            if(end == -1)
                end = temp.length();
        } while(true);
        temp = temp.substring(0, end);
        begin = temp.indexOf('=');
        temp = temp.substring(begin + 1);
        if(temp.charAt(0) == ' ')
            temp = temp.substring(1);
        if(temp.startsWith("\""))
            temp = temp.substring(1);
        if(temp.endsWith("\""))
            temp = temp.substring(0, temp.length() - 1);
        return temp;
    }

    public Collection engineGetCertificates(CertSelector selector)
        throws CertStoreException
    {
        if(!(selector instanceof X509CertSelector))
            throw new CertStoreException("selector is not a X509CertSelector");
        X509CertSelector xselector = (X509CertSelector)selector;
        Set certSet = new HashSet();
        Set set = getEndCertificates(xselector);
        set.addAll(getCACertificates(xselector));
        set.addAll(getCrossCertificates(xselector));
        Iterator it = set.iterator();
        try
        {
            CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");
            while(it.hasNext()) 
            {
                byte bytes[] = (byte[])(byte[])it.next();
                if(bytes != null && bytes.length != 0)
                {
                    List bytesList = new ArrayList();
                    bytesList.add(bytes);
                    try
                    {
                        CertificatePair pair = CertificatePair.getInstance((new ASN1InputStream(bytes)).readObject());
                        bytesList.clear();
                        if(pair.getForward() != null)
                            bytesList.add(pair.getForward().getEncoded());
                        if(pair.getReverse() != null)
                            bytesList.add(pair.getReverse().getEncoded());
                    }
                    catch(IOException e) { }
                    catch(IllegalArgumentException e) { }
                    Iterator it2 = bytesList.iterator();
                    while(it2.hasNext()) 
                    {
                        ByteArrayInputStream bIn = new ByteArrayInputStream((byte[])(byte[])it2.next());
                        try
                        {
                            java.security.cert.Certificate cert = cf.generateCertificate(bIn);
                            if(xselector.match(cert))
                                certSet.add(cert);
                        }
                        catch(Exception e) { }
                    }
                }
            }
        }
        catch(Exception e)
        {
            throw new CertStoreException((new StringBuilder()).append("certificate cannot be constructed from LDAP result: ").append(e).toString());
        }
        return certSet;
    }

    private Set certSubjectSerialSearch(X509CertSelector xselector, String attrs[], String attrName, String subjectAttributeName)
        throws CertStoreException
    {
        Set set = new HashSet();
        try
        {
            if(xselector.getSubjectAsBytes() != null || xselector.getSubjectAsString() != null || xselector.getCertificate() != null)
            {
                String subject = null;
                String serial = null;
                if(xselector.getCertificate() != null)
                {
                    subject = xselector.getCertificate().getSubjectX500Principal().getName("RFC1779");
                    serial = xselector.getCertificate().getSerialNumber().toString();
                } else
                if(xselector.getSubjectAsBytes() != null)
                    subject = (new X500Principal(xselector.getSubjectAsBytes())).getName("RFC1779");
                else
                    subject = xselector.getSubjectAsString();
                String attrValue = parseDN(subject, subjectAttributeName);
                set.addAll(search(attrName, (new StringBuilder()).append("*").append(attrValue).append("*").toString(), attrs));
                if(serial != null && params.getSearchForSerialNumberIn() != null)
                {
                    attrValue = serial;
                    attrName = params.getSearchForSerialNumberIn();
                    set.addAll(search(attrName, (new StringBuilder()).append("*").append(attrValue).append("*").toString(), attrs));
                }
            } else
            {
                set.addAll(search(attrName, "*", attrs));
            }
        }
        catch(IOException e)
        {
            throw new CertStoreException((new StringBuilder()).append("exception processing selector: ").append(e).toString());
        }
        return set;
    }

    private Set getEndCertificates(X509CertSelector xselector)
        throws CertStoreException
    {
        String attrs[] = {
            params.getUserCertificateAttribute()
        };
        String attrName = params.getLdapUserCertificateAttributeName();
        String subjectAttributeName = params.getUserCertificateSubjectAttributeName();
        Set set = certSubjectSerialSearch(xselector, attrs, attrName, subjectAttributeName);
        return set;
    }

    private Set getCACertificates(X509CertSelector xselector)
        throws CertStoreException
    {
        String attrs[] = {
            params.getCACertificateAttribute()
        };
        String attrName = params.getLdapCACertificateAttributeName();
        String subjectAttributeName = params.getCACertificateSubjectAttributeName();
        Set set = certSubjectSerialSearch(xselector, attrs, attrName, subjectAttributeName);
        if(set.isEmpty())
            set.addAll(search(null, "*", attrs));
        return set;
    }

    private Set getCrossCertificates(X509CertSelector xselector)
        throws CertStoreException
    {
        String attrs[] = {
            params.getCrossCertificateAttribute()
        };
        String attrName = params.getLdapCrossCertificateAttributeName();
        String subjectAttributeName = params.getCrossCertificateSubjectAttributeName();
        Set set = certSubjectSerialSearch(xselector, attrs, attrName, subjectAttributeName);
        if(set.isEmpty())
            set.addAll(search(null, "*", attrs));
        return set;
    }

    public Collection engineGetCRLs(CRLSelector selector)
        throws CertStoreException
    {
        String attrs[] = {
            params.getCertificateRevocationListAttribute()
        };
        if(!(selector instanceof X509CRLSelector))
            throw new CertStoreException("selector is not a X509CRLSelector");
        X509CRLSelector xselector = (X509CRLSelector)selector;
        Set crlSet = new HashSet();
        String attrName = params.getLdapCertificateRevocationListAttributeName();
        Set set = new HashSet();
        Iterator it;
        if(xselector.getIssuerNames() != null)
        {
            String attrValue;
            for(it = xselector.getIssuerNames().iterator(); it.hasNext(); set.addAll(search(attrName, (new StringBuilder()).append("*").append(attrValue).append("*").toString(), attrs)))
            {
                Object o = it.next();
                attrValue = null;
                if(o instanceof String)
                {
                    String issuerAttributeName = params.getCertificateRevocationListIssuerAttributeName();
                    attrValue = parseDN((String)o, issuerAttributeName);
                } else
                {
                    String issuerAttributeName = params.getCertificateRevocationListIssuerAttributeName();
                    attrValue = parseDN((new X500Principal((byte[])(byte[])o)).getName("RFC1779"), issuerAttributeName);
                }
            }

        } else
        {
            set.addAll(search(attrName, "*", attrs));
        }
        set.addAll(search(null, "*", attrs));
        it = set.iterator();
        try
        {
            CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");
            do
            {
                if(!it.hasNext())
                    break;
                java.security.cert.CRL crl = cf.generateCRL(new ByteArrayInputStream((byte[])(byte[])it.next()));
                if(xselector.match(crl))
                    crlSet.add(crl);
            } while(true);
        }
        catch(Exception e)
        {
            throw new CertStoreException((new StringBuilder()).append("CRL cannot be constructed from LDAP result ").append(e).toString());
        }
        return crlSet;
    }

    private Set search(String attributeName, String attributeValue, String attrs[])
        throws CertStoreException
    {
        String filter;
        DirContext ctx;
        Set set;
        filter = (new StringBuilder()).append(attributeName).append("=").append(attributeValue).toString();
        if(attributeName == null)
            filter = null;
        ctx = null;
        set = new HashSet();
        try
        {
            ctx = connectLDAP();
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(2);
            constraints.setCountLimit(0L);
            for(int i = 0; i < attrs.length; i++)
            {
                String temp[] = new String[1];
                temp[0] = attrs[i];
                constraints.setReturningAttributes(temp);
                String filter2 = (new StringBuilder()).append("(&(").append(filter).append(")(").append(temp[0]).append("=*))").toString();
                if(filter == null)
                    filter2 = (new StringBuilder()).append("(").append(temp[0]).append("=*)").toString();
                for(NamingEnumeration results = ctx.search(params.getBaseDN(), filter2, constraints); results.hasMoreElements();)
                {
                    SearchResult sr = (SearchResult)results.next();
                    NamingEnumeration enumeration = ((Attribute)sr.getAttributes().getAll().next()).getAll();
                    while(enumeration.hasMore()) 
                    {
                        Object o = enumeration.next();
                        set.add(o);
                    }
                }

            }

        }
        catch(Exception e)
        {
            throw new CertStoreException((new StringBuilder()).append("Error getting results from LDAP directory ").append(e).toString());
        }
        try
        {
            if(null != ctx)
                ctx.close();
        }
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_357;
        Exception exception;
        exception;
        try
        {
            if(null != ctx)
                ctx.close();
        }
        catch(Exception e) { }
        throw exception;
        return set;
    }

    private X509LDAPCertStoreParameters params;
    private static String LDAP_PROVIDER = "com.sun.jndi.ldap.LdapCtxFactory";
    private static String REFERRALS_IGNORE = "ignore";
    private static final String SEARCH_SECURITY_LEVEL = "none";
    private static final String URL_CONTEXT_PREFIX = "com.sun.jndi.url";

}
