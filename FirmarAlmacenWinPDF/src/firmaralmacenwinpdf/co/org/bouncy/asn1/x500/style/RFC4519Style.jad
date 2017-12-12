// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RFC4519Style.java

package co.org.bouncy.asn1.x500.style;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.*;
import java.io.IOException;
import java.util.Hashtable;

// Referenced classes of package co.org.bouncy.asn1.x500.style:
//            IETFUtils

public class RFC4519Style
    implements X500NameStyle
{

    protected RFC4519Style()
    {
    }

    public ASN1Encodable stringToValue(ASN1ObjectIdentifier oid, String value)
    {
        if(value.length() != 0 && value.charAt(0) == '#')
            try
            {
                return IETFUtils.valueFromHexString(value, 1);
            }
            catch(IOException e)
            {
                throw new RuntimeException((new StringBuilder()).append("can't recode value for oid ").append(oid.getId()).toString());
            }
        if(value.length() != 0 && value.charAt(0) == '\\')
            value = value.substring(1);
        if(oid.equals(dc))
            return new DERIA5String(value);
        if(oid.equals(c) || oid.equals(serialNumber) || oid.equals(dnQualifier) || oid.equals(telephoneNumber))
            return new DERPrintableString(value);
        else
            return new DERUTF8String(value);
    }

    public String oidToDisplayName(ASN1ObjectIdentifier oid)
    {
        return (String)DefaultSymbols.get(oid);
    }

    public String[] oidToAttrNames(ASN1ObjectIdentifier oid)
    {
        return IETFUtils.findAttrNamesForOID(oid, DefaultLookUp);
    }

    public ASN1ObjectIdentifier attrNameToOID(String attrName)
    {
        return IETFUtils.decodeAttrName(attrName, DefaultLookUp);
    }

    public boolean areEqual(X500Name name1, X500Name name2)
    {
        RDN rdns1[] = name1.getRDNs();
        RDN rdns2[] = name2.getRDNs();
        if(rdns1.length != rdns2.length)
            return false;
        boolean reverse = false;
        if(rdns1[0].getFirst() != null && rdns2[0].getFirst() != null)
            reverse = !rdns1[0].getFirst().getType().equals(rdns2[0].getFirst().getType());
        for(int i = 0; i != rdns1.length; i++)
            if(!foundMatch(reverse, rdns1[i], rdns2))
                return false;

        return true;
    }

    private boolean foundMatch(boolean reverse, RDN rdn, RDN possRDNs[])
    {
        if(reverse)
        {
            for(int i = possRDNs.length - 1; i >= 0; i--)
                if(possRDNs[i] != null && rdnAreEqual(rdn, possRDNs[i]))
                {
                    possRDNs[i] = null;
                    return true;
                }

        } else
        {
            for(int i = 0; i != possRDNs.length; i++)
                if(possRDNs[i] != null && rdnAreEqual(rdn, possRDNs[i]))
                {
                    possRDNs[i] = null;
                    return true;
                }

        }
        return false;
    }

    protected boolean rdnAreEqual(RDN rdn1, RDN rdn2)
    {
        return IETFUtils.rDNAreEqual(rdn1, rdn2);
    }

    public RDN[] fromString(String dirName)
    {
        RDN tmp[] = IETFUtils.rDNsFromString(dirName, this);
        RDN res[] = new RDN[tmp.length];
        for(int i = 0; i != tmp.length; i++)
            res[res.length - i - 1] = tmp[i];

        return res;
    }

    public int calculateHashCode(X500Name name)
    {
        int hashCodeValue = 0;
        RDN rdns[] = name.getRDNs();
        for(int i = 0; i != rdns.length; i++)
            if(rdns[i].isMultiValued())
            {
                AttributeTypeAndValue atv[] = rdns[i].getTypesAndValues();
                for(int j = 0; j != atv.length; j++)
                {
                    hashCodeValue ^= atv[j].getType().hashCode();
                    hashCodeValue ^= calcHashCode(atv[j].getValue());
                }

            } else
            {
                hashCodeValue ^= rdns[i].getFirst().getType().hashCode();
                hashCodeValue ^= calcHashCode(rdns[i].getFirst().getValue());
            }

        return hashCodeValue;
    }

    private int calcHashCode(ASN1Encodable enc)
    {
        String value = IETFUtils.valueToString(enc);
        value = IETFUtils.canonicalize(value);
        return value.hashCode();
    }

    public String toString(X500Name name)
    {
        StringBuffer buf = new StringBuffer();
        boolean first = true;
        RDN rdns[] = name.getRDNs();
        for(int i = rdns.length - 1; i >= 0; i--)
        {
            if(first)
                first = false;
            else
                buf.append(',');
            IETFUtils.appendRDN(buf, rdns[i], DefaultSymbols);
        }

        return buf.toString();
    }

    public static final X500NameStyle INSTANCE = new RFC4519Style();
    public static final ASN1ObjectIdentifier businessCategory;
    public static final ASN1ObjectIdentifier c;
    public static final ASN1ObjectIdentifier cn;
    public static final ASN1ObjectIdentifier dc;
    public static final ASN1ObjectIdentifier description;
    public static final ASN1ObjectIdentifier destinationIndicator;
    public static final ASN1ObjectIdentifier distinguishedName;
    public static final ASN1ObjectIdentifier dnQualifier;
    public static final ASN1ObjectIdentifier enhancedSearchGuide;
    public static final ASN1ObjectIdentifier facsimileTelephoneNumber;
    public static final ASN1ObjectIdentifier generationQualifier;
    public static final ASN1ObjectIdentifier givenName;
    public static final ASN1ObjectIdentifier houseIdentifier;
    public static final ASN1ObjectIdentifier initials;
    public static final ASN1ObjectIdentifier internationalISDNNumber;
    public static final ASN1ObjectIdentifier l;
    public static final ASN1ObjectIdentifier member;
    public static final ASN1ObjectIdentifier name;
    public static final ASN1ObjectIdentifier o;
    public static final ASN1ObjectIdentifier ou;
    public static final ASN1ObjectIdentifier owner;
    public static final ASN1ObjectIdentifier physicalDeliveryOfficeName;
    public static final ASN1ObjectIdentifier postalAddress;
    public static final ASN1ObjectIdentifier postalCode;
    public static final ASN1ObjectIdentifier postOfficeBox;
    public static final ASN1ObjectIdentifier preferredDeliveryMethod;
    public static final ASN1ObjectIdentifier registeredAddress;
    public static final ASN1ObjectIdentifier roleOccupant;
    public static final ASN1ObjectIdentifier searchGuide;
    public static final ASN1ObjectIdentifier seeAlso;
    public static final ASN1ObjectIdentifier serialNumber;
    public static final ASN1ObjectIdentifier sn;
    public static final ASN1ObjectIdentifier st;
    public static final ASN1ObjectIdentifier street;
    public static final ASN1ObjectIdentifier telephoneNumber;
    public static final ASN1ObjectIdentifier teletexTerminalIdentifier;
    public static final ASN1ObjectIdentifier telexNumber;
    public static final ASN1ObjectIdentifier title;
    public static final ASN1ObjectIdentifier uid;
    public static final ASN1ObjectIdentifier uniqueMember;
    public static final ASN1ObjectIdentifier userPassword;
    public static final ASN1ObjectIdentifier x121Address;
    public static final ASN1ObjectIdentifier x500UniqueIdentifier;
    private static final Hashtable DefaultSymbols;
    private static final Hashtable DefaultLookUp;

    static 
    {
        businessCategory = new ASN1ObjectIdentifier("2.5.4.15");
        c = new ASN1ObjectIdentifier("2.5.4.6");
        cn = new ASN1ObjectIdentifier("2.5.4.3");
        dc = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.25");
        description = new ASN1ObjectIdentifier("2.5.4.13");
        destinationIndicator = new ASN1ObjectIdentifier("2.5.4.27");
        distinguishedName = new ASN1ObjectIdentifier("2.5.4.49");
        dnQualifier = new ASN1ObjectIdentifier("2.5.4.46");
        enhancedSearchGuide = new ASN1ObjectIdentifier("2.5.4.47");
        facsimileTelephoneNumber = new ASN1ObjectIdentifier("2.5.4.23");
        generationQualifier = new ASN1ObjectIdentifier("2.5.4.44");
        givenName = new ASN1ObjectIdentifier("2.5.4.42");
        houseIdentifier = new ASN1ObjectIdentifier("2.5.4.51");
        initials = new ASN1ObjectIdentifier("2.5.4.43");
        internationalISDNNumber = new ASN1ObjectIdentifier("2.5.4.25");
        l = new ASN1ObjectIdentifier("2.5.4.7");
        member = new ASN1ObjectIdentifier("2.5.4.31");
        name = new ASN1ObjectIdentifier("2.5.4.41");
        o = new ASN1ObjectIdentifier("2.5.4.10");
        ou = new ASN1ObjectIdentifier("2.5.4.11");
        owner = new ASN1ObjectIdentifier("2.5.4.32");
        physicalDeliveryOfficeName = new ASN1ObjectIdentifier("2.5.4.19");
        postalAddress = new ASN1ObjectIdentifier("2.5.4.16");
        postalCode = new ASN1ObjectIdentifier("2.5.4.17");
        postOfficeBox = new ASN1ObjectIdentifier("2.5.4.18");
        preferredDeliveryMethod = new ASN1ObjectIdentifier("2.5.4.28");
        registeredAddress = new ASN1ObjectIdentifier("2.5.4.26");
        roleOccupant = new ASN1ObjectIdentifier("2.5.4.33");
        searchGuide = new ASN1ObjectIdentifier("2.5.4.14");
        seeAlso = new ASN1ObjectIdentifier("2.5.4.34");
        serialNumber = new ASN1ObjectIdentifier("2.5.4.5");
        sn = new ASN1ObjectIdentifier("2.5.4.4");
        st = new ASN1ObjectIdentifier("2.5.4.8");
        street = new ASN1ObjectIdentifier("2.5.4.9");
        telephoneNumber = new ASN1ObjectIdentifier("2.5.4.20");
        teletexTerminalIdentifier = new ASN1ObjectIdentifier("2.5.4.22");
        telexNumber = new ASN1ObjectIdentifier("2.5.4.21");
        title = new ASN1ObjectIdentifier("2.5.4.12");
        uid = new ASN1ObjectIdentifier("0.9.2342.19200300.100.1.1");
        uniqueMember = new ASN1ObjectIdentifier("2.5.4.50");
        userPassword = new ASN1ObjectIdentifier("2.5.4.35");
        x121Address = new ASN1ObjectIdentifier("2.5.4.24");
        x500UniqueIdentifier = new ASN1ObjectIdentifier("2.5.4.45");
        DefaultSymbols = new Hashtable();
        DefaultLookUp = new Hashtable();
        DefaultSymbols.put(businessCategory, "businessCategory");
        DefaultSymbols.put(c, "c");
        DefaultSymbols.put(cn, "cn");
        DefaultSymbols.put(dc, "dc");
        DefaultSymbols.put(description, "description");
        DefaultSymbols.put(destinationIndicator, "destinationIndicator");
        DefaultSymbols.put(distinguishedName, "distinguishedName");
        DefaultSymbols.put(dnQualifier, "dnQualifier");
        DefaultSymbols.put(enhancedSearchGuide, "enhancedSearchGuide");
        DefaultSymbols.put(facsimileTelephoneNumber, "facsimileTelephoneNumber");
        DefaultSymbols.put(generationQualifier, "generationQualifier");
        DefaultSymbols.put(givenName, "givenName");
        DefaultSymbols.put(houseIdentifier, "houseIdentifier");
        DefaultSymbols.put(initials, "initials");
        DefaultSymbols.put(internationalISDNNumber, "internationalISDNNumber");
        DefaultSymbols.put(l, "l");
        DefaultSymbols.put(member, "member");
        DefaultSymbols.put(name, "name");
        DefaultSymbols.put(o, "o");
        DefaultSymbols.put(ou, "ou");
        DefaultSymbols.put(owner, "owner");
        DefaultSymbols.put(physicalDeliveryOfficeName, "physicalDeliveryOfficeName");
        DefaultSymbols.put(postalAddress, "postalAddress");
        DefaultSymbols.put(postalCode, "postalCode");
        DefaultSymbols.put(postOfficeBox, "postOfficeBox");
        DefaultSymbols.put(preferredDeliveryMethod, "preferredDeliveryMethod");
        DefaultSymbols.put(registeredAddress, "registeredAddress");
        DefaultSymbols.put(roleOccupant, "roleOccupant");
        DefaultSymbols.put(searchGuide, "searchGuide");
        DefaultSymbols.put(seeAlso, "seeAlso");
        DefaultSymbols.put(serialNumber, "serialNumber");
        DefaultSymbols.put(sn, "sn");
        DefaultSymbols.put(st, "st");
        DefaultSymbols.put(street, "street");
        DefaultSymbols.put(telephoneNumber, "telephoneNumber");
        DefaultSymbols.put(teletexTerminalIdentifier, "teletexTerminalIdentifier");
        DefaultSymbols.put(telexNumber, "telexNumber");
        DefaultSymbols.put(title, "title");
        DefaultSymbols.put(uid, "uid");
        DefaultSymbols.put(uniqueMember, "uniqueMember");
        DefaultSymbols.put(userPassword, "userPassword");
        DefaultSymbols.put(x121Address, "x121Address");
        DefaultSymbols.put(x500UniqueIdentifier, "x500UniqueIdentifier");
        DefaultLookUp.put("businesscategory", businessCategory);
        DefaultLookUp.put("c", c);
        DefaultLookUp.put("cn", cn);
        DefaultLookUp.put("dc", dc);
        DefaultLookUp.put("description", description);
        DefaultLookUp.put("destinationindicator", destinationIndicator);
        DefaultLookUp.put("distinguishedname", distinguishedName);
        DefaultLookUp.put("dnqualifier", dnQualifier);
        DefaultLookUp.put("enhancedsearchguide", enhancedSearchGuide);
        DefaultLookUp.put("facsimiletelephonenumber", facsimileTelephoneNumber);
        DefaultLookUp.put("generationqualifier", generationQualifier);
        DefaultLookUp.put("givenname", givenName);
        DefaultLookUp.put("houseidentifier", houseIdentifier);
        DefaultLookUp.put("initials", initials);
        DefaultLookUp.put("internationalisdnnumber", internationalISDNNumber);
        DefaultLookUp.put("l", l);
        DefaultLookUp.put("member", member);
        DefaultLookUp.put("name", name);
        DefaultLookUp.put("o", o);
        DefaultLookUp.put("ou", ou);
        DefaultLookUp.put("owner", owner);
        DefaultLookUp.put("physicaldeliveryofficename", physicalDeliveryOfficeName);
        DefaultLookUp.put("postaladdress", postalAddress);
        DefaultLookUp.put("postalcode", postalCode);
        DefaultLookUp.put("postofficebox", postOfficeBox);
        DefaultLookUp.put("preferreddeliverymethod", preferredDeliveryMethod);
        DefaultLookUp.put("registeredaddress", registeredAddress);
        DefaultLookUp.put("roleoccupant", roleOccupant);
        DefaultLookUp.put("searchguide", searchGuide);
        DefaultLookUp.put("seealso", seeAlso);
        DefaultLookUp.put("serialnumber", serialNumber);
        DefaultLookUp.put("sn", sn);
        DefaultLookUp.put("st", st);
        DefaultLookUp.put("street", street);
        DefaultLookUp.put("telephonenumber", telephoneNumber);
        DefaultLookUp.put("teletexterminalidentifier", teletexTerminalIdentifier);
        DefaultLookUp.put("telexnumber", telexNumber);
        DefaultLookUp.put("title", title);
        DefaultLookUp.put("uid", uid);
        DefaultLookUp.put("uniquemember", uniqueMember);
        DefaultLookUp.put("userpassword", userPassword);
        DefaultLookUp.put("x121address", x121Address);
        DefaultLookUp.put("x500uniqueidentifier", x500UniqueIdentifier);
    }
}
