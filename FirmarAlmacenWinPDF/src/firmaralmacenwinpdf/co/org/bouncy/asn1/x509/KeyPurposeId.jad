// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyPurposeId.java

package co.org.bouncy.asn1.x509;

import co.org.bouncy.asn1.*;

// Referenced classes of package co.org.bouncy.asn1.x509:
//            Extension

public class KeyPurposeId extends ASN1Object
{

    private KeyPurposeId(ASN1ObjectIdentifier id)
    {
        this.id = id;
    }

    /**
     * @deprecated Method KeyPurposeId is deprecated
     */

    public KeyPurposeId(String id)
    {
        this(new ASN1ObjectIdentifier(id));
    }

    public static KeyPurposeId getInstance(Object o)
    {
        if(o instanceof KeyPurposeId)
            return (KeyPurposeId)o;
        if(o != null)
            return new KeyPurposeId(ASN1ObjectIdentifier.getInstance(o));
        else
            return null;
    }

    public ASN1Primitive toASN1Primitive()
    {
        return id;
    }

    public String getId()
    {
        return id.getId();
    }

    private static final ASN1ObjectIdentifier id_kp;
    public static final KeyPurposeId anyExtendedKeyUsage;
    public static final KeyPurposeId id_kp_serverAuth;
    public static final KeyPurposeId id_kp_clientAuth;
    public static final KeyPurposeId id_kp_codeSigning;
    public static final KeyPurposeId id_kp_emailProtection;
    public static final KeyPurposeId id_kp_ipsecEndSystem;
    public static final KeyPurposeId id_kp_ipsecTunnel;
    public static final KeyPurposeId id_kp_ipsecUser;
    public static final KeyPurposeId id_kp_timeStamping;
    public static final KeyPurposeId id_kp_OCSPSigning;
    public static final KeyPurposeId id_kp_dvcs;
    public static final KeyPurposeId id_kp_sbgpCertAAServerAuth;
    public static final KeyPurposeId id_kp_scvp_responder;
    public static final KeyPurposeId id_kp_eapOverPPP;
    public static final KeyPurposeId id_kp_eapOverLAN;
    public static final KeyPurposeId id_kp_scvpServer;
    public static final KeyPurposeId id_kp_scvpClient;
    public static final KeyPurposeId id_kp_ipsecIKE;
    public static final KeyPurposeId id_kp_capwapAC;
    public static final KeyPurposeId id_kp_capwapWTP;
    public static final KeyPurposeId id_kp_smartcardlogon = new KeyPurposeId(new ASN1ObjectIdentifier("1.3.6.1.4.1.311.20.2.2"));
    private ASN1ObjectIdentifier id;

    static 
    {
        id_kp = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.3");
        anyExtendedKeyUsage = new KeyPurposeId(Extension.extendedKeyUsage.branch("0"));
        id_kp_serverAuth = new KeyPurposeId(id_kp.branch("1"));
        id_kp_clientAuth = new KeyPurposeId(id_kp.branch("2"));
        id_kp_codeSigning = new KeyPurposeId(id_kp.branch("3"));
        id_kp_emailProtection = new KeyPurposeId(id_kp.branch("4"));
        id_kp_ipsecEndSystem = new KeyPurposeId(id_kp.branch("5"));
        id_kp_ipsecTunnel = new KeyPurposeId(id_kp.branch("6"));
        id_kp_ipsecUser = new KeyPurposeId(id_kp.branch("7"));
        id_kp_timeStamping = new KeyPurposeId(id_kp.branch("8"));
        id_kp_OCSPSigning = new KeyPurposeId(id_kp.branch("9"));
        id_kp_dvcs = new KeyPurposeId(id_kp.branch("10"));
        id_kp_sbgpCertAAServerAuth = new KeyPurposeId(id_kp.branch("11"));
        id_kp_scvp_responder = new KeyPurposeId(id_kp.branch("12"));
        id_kp_eapOverPPP = new KeyPurposeId(id_kp.branch("13"));
        id_kp_eapOverLAN = new KeyPurposeId(id_kp.branch("14"));
        id_kp_scvpServer = new KeyPurposeId(id_kp.branch("15"));
        id_kp_scvpClient = new KeyPurposeId(id_kp.branch("16"));
        id_kp_ipsecIKE = new KeyPurposeId(id_kp.branch("17"));
        id_kp_capwapAC = new KeyPurposeId(id_kp.branch("18"));
        id_kp_capwapWTP = new KeyPurposeId(id_kp.branch("19"));
    }
}
