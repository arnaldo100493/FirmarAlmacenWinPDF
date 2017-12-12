// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateInfo.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.org.bouncy.asn1.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.*;

public class CertificateInfo
{
    public static class X509NameTokenizer
    {

        public boolean hasMoreTokens()
        {
            return index != oid.length();
        }

        public String nextToken()
        {
            if(index == oid.length())
                return null;
            int end = index + 1;
            boolean quoted = false;
            boolean escaped = false;
            buf.setLength(0);
            for(; end != oid.length(); end++)
            {
                char c = oid.charAt(end);
                if(c == '"')
                {
                    if(!escaped)
                        quoted = !quoted;
                    else
                        buf.append(c);
                    escaped = false;
                    continue;
                }
                if(escaped || quoted)
                {
                    buf.append(c);
                    escaped = false;
                    continue;
                }
                if(c == '\\')
                {
                    escaped = true;
                    continue;
                }
                if(c == ',')
                    break;
                buf.append(c);
            }

            index = end;
            return buf.toString().trim();
        }

        private String oid;
        private int index;
        private StringBuffer buf;

        public X509NameTokenizer(String oid)
        {
            buf = new StringBuffer();
            this.oid = oid;
            index = -1;
        }
    }

    public static class X500Name
    {

        public String getField(String name)
        {
            List vs = (List)values.get(name);
            return vs != null ? (String)vs.get(0) : null;
        }

        public List getFieldArray(String name)
        {
            return (List)values.get(name);
        }

        public Map getFields()
        {
            return values;
        }

        public String toString()
        {
            return values.toString();
        }

        public static final ASN1ObjectIdentifier C;
        public static final ASN1ObjectIdentifier O;
        public static final ASN1ObjectIdentifier OU;
        public static final ASN1ObjectIdentifier T;
        public static final ASN1ObjectIdentifier CN;
        public static final ASN1ObjectIdentifier SN;
        public static final ASN1ObjectIdentifier L;
        public static final ASN1ObjectIdentifier ST;
        public static final ASN1ObjectIdentifier SURNAME;
        public static final ASN1ObjectIdentifier GIVENNAME;
        public static final ASN1ObjectIdentifier INITIALS;
        public static final ASN1ObjectIdentifier GENERATION;
        public static final ASN1ObjectIdentifier UNIQUE_IDENTIFIER = new ASN1ObjectIdentifier("2.5.4.45");
        public static final ASN1ObjectIdentifier EmailAddress;
        public static final ASN1ObjectIdentifier E;
        public static final ASN1ObjectIdentifier DC;
        public static final ASN1ObjectIdentifier UID;
        public static final Map DefaultSymbols;
        public Map values;

        static 
        {
            C = new ASN1ObjectIdentifier("2.5.4.6");
            O = new ASN1ObjectIdentifier("2.5.4.10");
            OU = new ASN1ObjectIdentifier("2.5.4.11");
            T = new ASN1ObjectIdentifier("2.5.4.12");
            CN = new ASN1ObjectIdentifier("2.5.4.3");
            SN = new ASN1ObjectIdentifier("2.5.4.5");
            L = new ASN1ObjectIdentifier("2.5.4.7");
            ST = new ASN1ObjectIdentifier("2.5.4.8");
            SURNAME = new ASN1ObjectIdentifier("2.5.4.4");
            GIVENNAME = new ASN1ObjectIdentifier("2.5.4.42");
            INITIALS = new ASN1ObjectIdentifier("2.5.4.43");
            GENERATION = new ASN1ObjectIdentifier("2.5.4.44");
            EmailAddress = new ASN1ObjectIdentifier("1.2.840.113549.1.9.1");
            E = EmailAddress;
            DC = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
            UID = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
            DefaultSymbols = new HashMap();
            DefaultSymbols.put(C, "C");
            DefaultSymbols.put(O, "O");
            DefaultSymbols.put(T, "T");
            DefaultSymbols.put(OU, "OU");
            DefaultSymbols.put(CN, "CN");
            DefaultSymbols.put(L, "L");
            DefaultSymbols.put(ST, "ST");
            DefaultSymbols.put(SN, "SN");
            DefaultSymbols.put(EmailAddress, "E");
            DefaultSymbols.put(DC, "DC");
            DefaultSymbols.put(UID, "UID");
            DefaultSymbols.put(SURNAME, "SURNAME");
            DefaultSymbols.put(GIVENNAME, "GIVENNAME");
            DefaultSymbols.put(INITIALS, "INITIALS");
            DefaultSymbols.put(GENERATION, "GENERATION");
        }

        public X500Name(ASN1Sequence seq)
        {
            values = new HashMap();
            for(Enumeration e = seq.getObjects(); e.hasMoreElements();)
            {
                ASN1Set set = (ASN1Set)e.nextElement();
                int i = 0;
                while(i < set.size()) 
                {
                    ASN1Sequence s = (ASN1Sequence)set.getObjectAt(i);
                    String id = (String)DefaultSymbols.get(s.getObjectAt(0));
                    if(id != null)
                    {
                        ArrayList vs = (ArrayList)values.get(id);
                        if(vs == null)
                        {
                            vs = new ArrayList();
                            values.put(id, vs);
                        }
                        vs.add(((ASN1String)s.getObjectAt(1)).getString());
                    }
                    i++;
                }
            }

        }

        public X500Name(String dirName)
        {
            values = new HashMap();
            String value;
            ArrayList vs;
            for(X509NameTokenizer nTok = new X509NameTokenizer(dirName); nTok.hasMoreTokens(); vs.add(value))
            {
                String token = nTok.nextToken();
                int index = token.indexOf('=');
                if(index == -1)
                    throw new IllegalArgumentException(MessageLocalization.getComposedMessage("badly.formated.directory.string", new Object[0]));
                String id = token.substring(0, index).toUpperCase();
                value = token.substring(index + 1);
                vs = (ArrayList)values.get(id);
                if(vs == null)
                {
                    vs = new ArrayList();
                    values.put(id, vs);
                }
            }

        }
    }


    public CertificateInfo()
    {
    }

    public static X500Name getIssuerFields(X509Certificate cert)
    {
        try
        {
            return new X500Name((ASN1Sequence)getIssuer(cert.getTBSCertificate()));
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static ASN1Primitive getIssuer(byte enc[])
    {
        try
        {
            ASN1InputStream in = new ASN1InputStream(new ByteArrayInputStream(enc));
            ASN1Sequence seq = (ASN1Sequence)in.readObject();
            return (ASN1Primitive)seq.getObjectAt((seq.getObjectAt(0) instanceof ASN1TaggedObject) ? 3 : 2);
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static X500Name getSubjectFields(X509Certificate cert)
    {
        try
        {
            if(cert != null)
                return new X500Name((ASN1Sequence)getSubject(cert.getTBSCertificate()));
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        return null;
    }

    public static ASN1Primitive getSubject(byte enc[])
    {
        try
        {
            ASN1InputStream in = new ASN1InputStream(new ByteArrayInputStream(enc));
            ASN1Sequence seq = (ASN1Sequence)in.readObject();
            return (ASN1Primitive)seq.getObjectAt((seq.getObjectAt(0) instanceof ASN1TaggedObject) ? 5 : 4);
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }
}
