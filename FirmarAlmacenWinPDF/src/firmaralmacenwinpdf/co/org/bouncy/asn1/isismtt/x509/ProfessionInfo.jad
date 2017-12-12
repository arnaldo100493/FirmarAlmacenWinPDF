// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProfessionInfo.java

package co.org.bouncy.asn1.isismtt.x509;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.x500.DirectoryString;
import java.util.Enumeration;

// Referenced classes of package co.org.bouncy.asn1.isismtt.x509:
//            NamingAuthority

public class ProfessionInfo extends ASN1Object
{

    public static ProfessionInfo getInstance(Object obj)
    {
        if(obj == null || (obj instanceof ProfessionInfo))
            return (ProfessionInfo)obj;
        if(obj instanceof ASN1Sequence)
            return new ProfessionInfo((ASN1Sequence)obj);
        else
            throw new IllegalArgumentException((new StringBuilder()).append("illegal object in getInstance: ").append(obj.getClass().getName()).toString());
    }

    private ProfessionInfo(ASN1Sequence seq)
    {
        if(seq.size() > 5)
            throw new IllegalArgumentException((new StringBuilder()).append("Bad sequence size: ").append(seq.size()).toString());
        Enumeration e = seq.getObjects();
        ASN1Encodable o = (ASN1Encodable)e.nextElement();
        if(o instanceof ASN1TaggedObject)
        {
            if(((ASN1TaggedObject)o).getTagNo() != 0)
                throw new IllegalArgumentException((new StringBuilder()).append("Bad tag number: ").append(((ASN1TaggedObject)o).getTagNo()).toString());
            namingAuthority = NamingAuthority.getInstance((ASN1TaggedObject)o, true);
            o = (ASN1Encodable)e.nextElement();
        }
        professionItems = ASN1Sequence.getInstance(o);
        if(e.hasMoreElements())
        {
            o = (ASN1Encodable)e.nextElement();
            if(o instanceof ASN1Sequence)
                professionOIDs = ASN1Sequence.getInstance(o);
            else
            if(o instanceof DERPrintableString)
                registrationNumber = DERPrintableString.getInstance(o).getString();
            else
            if(o instanceof ASN1OctetString)
                addProfessionInfo = ASN1OctetString.getInstance(o);
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Bad object encountered: ").append(o.getClass()).toString());
        }
        if(e.hasMoreElements())
        {
            o = (ASN1Encodable)e.nextElement();
            if(o instanceof DERPrintableString)
                registrationNumber = DERPrintableString.getInstance(o).getString();
            else
            if(o instanceof DEROctetString)
                addProfessionInfo = (DEROctetString)o;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Bad object encountered: ").append(o.getClass()).toString());
        }
        if(e.hasMoreElements())
        {
            o = (ASN1Encodable)e.nextElement();
            if(o instanceof DEROctetString)
                addProfessionInfo = (DEROctetString)o;
            else
                throw new IllegalArgumentException((new StringBuilder()).append("Bad object encountered: ").append(o.getClass()).toString());
        }
    }

    public ProfessionInfo(NamingAuthority namingAuthority, DirectoryString professionItems[], ASN1ObjectIdentifier professionOIDs[], String registrationNumber, ASN1OctetString addProfessionInfo)
    {
        this.namingAuthority = namingAuthority;
        ASN1EncodableVector v = new ASN1EncodableVector();
        for(int i = 0; i != professionItems.length; i++)
            v.add(professionItems[i]);

        this.professionItems = new DERSequence(v);
        if(professionOIDs != null)
        {
            v = new ASN1EncodableVector();
            for(int i = 0; i != professionOIDs.length; i++)
                v.add(professionOIDs[i]);

            this.professionOIDs = new DERSequence(v);
        }
        this.registrationNumber = registrationNumber;
        this.addProfessionInfo = addProfessionInfo;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector vec = new ASN1EncodableVector();
        if(namingAuthority != null)
            vec.add(new DERTaggedObject(true, 0, namingAuthority));
        vec.add(professionItems);
        if(professionOIDs != null)
            vec.add(professionOIDs);
        if(registrationNumber != null)
            vec.add(new DERPrintableString(registrationNumber, true));
        if(addProfessionInfo != null)
            vec.add(addProfessionInfo);
        return new DERSequence(vec);
    }

    public ASN1OctetString getAddProfessionInfo()
    {
        return addProfessionInfo;
    }

    public NamingAuthority getNamingAuthority()
    {
        return namingAuthority;
    }

    public DirectoryString[] getProfessionItems()
    {
        DirectoryString items[] = new DirectoryString[professionItems.size()];
        int count = 0;
        for(Enumeration e = professionItems.getObjects(); e.hasMoreElements();)
            items[count++] = DirectoryString.getInstance(e.nextElement());

        return items;
    }

    public ASN1ObjectIdentifier[] getProfessionOIDs()
    {
        if(professionOIDs == null)
            return new ASN1ObjectIdentifier[0];
        ASN1ObjectIdentifier oids[] = new ASN1ObjectIdentifier[professionOIDs.size()];
        int count = 0;
        for(Enumeration e = professionOIDs.getObjects(); e.hasMoreElements();)
            oids[count++] = ASN1ObjectIdentifier.getInstance(e.nextElement());

        return oids;
    }

    public String getRegistrationNumber()
    {
        return registrationNumber;
    }

    public static final ASN1ObjectIdentifier Rechtsanwltin;
    public static final ASN1ObjectIdentifier Rechtsanwalt;
    public static final ASN1ObjectIdentifier Rechtsbeistand;
    public static final ASN1ObjectIdentifier Steuerberaterin;
    public static final ASN1ObjectIdentifier Steuerberater;
    public static final ASN1ObjectIdentifier Steuerbevollmchtigte;
    public static final ASN1ObjectIdentifier Steuerbevollmchtigter;
    public static final ASN1ObjectIdentifier Notarin;
    public static final ASN1ObjectIdentifier Notar;
    public static final ASN1ObjectIdentifier Notarvertreterin;
    public static final ASN1ObjectIdentifier Notarvertreter;
    public static final ASN1ObjectIdentifier Notariatsverwalterin;
    public static final ASN1ObjectIdentifier Notariatsverwalter;
    public static final ASN1ObjectIdentifier Wirtschaftsprferin;
    public static final ASN1ObjectIdentifier Wirtschaftsprfer;
    public static final ASN1ObjectIdentifier VereidigteBuchprferin;
    public static final ASN1ObjectIdentifier VereidigterBuchprfer;
    public static final ASN1ObjectIdentifier Patentanwltin;
    public static final ASN1ObjectIdentifier Patentanwalt;
    private NamingAuthority namingAuthority;
    private ASN1Sequence professionItems;
    private ASN1Sequence professionOIDs;
    private String registrationNumber;
    private ASN1OctetString addProfessionInfo;

    static 
    {
        Rechtsanwltin = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".1").toString());
        Rechtsanwalt = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".2").toString());
        Rechtsbeistand = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".3").toString());
        Steuerberaterin = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".4").toString());
        Steuerberater = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".5").toString());
        Steuerbevollmchtigte = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".6").toString());
        Steuerbevollmchtigter = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".7").toString());
        Notarin = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".8").toString());
        Notar = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".9").toString());
        Notarvertreterin = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".10").toString());
        Notarvertreter = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".11").toString());
        Notariatsverwalterin = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".12").toString());
        Notariatsverwalter = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".13").toString());
        Wirtschaftsprferin = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".14").toString());
        Wirtschaftsprfer = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".15").toString());
        VereidigteBuchprferin = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".16").toString());
        VereidigterBuchprfer = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".17").toString());
        Patentanwltin = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".18").toString());
        Patentanwalt = new ASN1ObjectIdentifier((new StringBuilder()).append(NamingAuthority.id_isismtt_at_namingAuthorities_RechtWirtschaftSteuern).append(".19").toString());
    }
}
