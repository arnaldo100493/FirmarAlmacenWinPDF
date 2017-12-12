// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateInfo.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.org.bouncy.asn1.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            CertificateInfo

public static class CertificateInfo$X500Name
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

    public CertificateInfo$X500Name(ASN1Sequence seq)
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

    public CertificateInfo$X500Name(String dirName)
    {
        values = new HashMap();
        String value;
        ArrayList vs;
        for(okenizer nTok = new okenizer(dirName); nTok.hasMoreTokens(); vs.add(value))
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
