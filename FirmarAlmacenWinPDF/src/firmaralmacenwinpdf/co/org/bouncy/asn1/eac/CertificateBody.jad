// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateBody.java

package co.org.bouncy.asn1.eac;

import co.org.bouncy.asn1.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1.eac:
//            CertificateHolderAuthorization, PackedDate, CertificateHolderReference, CertificationAuthorityReference, 
//            PublicKeyDataObject, EACTags

public class CertificateBody extends ASN1Object
{

    private void setIso7816CertificateBody(DERApplicationSpecific appSpe)
        throws IOException
    {
        DERApplicationSpecific aSpe;
        byte content[];
        if(appSpe.getApplicationTag() == 78)
            content = appSpe.getContents();
        else
            throw new IOException("Bad tag : not an iso7816 CERTIFICATE_CONTENT_TEMPLATE");
        ASN1InputStream aIS = new ASN1InputStream(content);
        do
        {
            ASN1Primitive obj;
            if((obj = aIS.readObject()) == null)
                break MISSING_BLOCK_LABEL_289;
            if(obj instanceof DERApplicationSpecific)
                aSpe = (DERApplicationSpecific)obj;
            else
                throw new IOException((new StringBuilder()).append("Not a valid iso7816 content : not a DERApplicationSpecific Object :").append(EACTags.encodeTag(appSpe)).append(obj.getClass()).toString());
            switch(aSpe.getApplicationTag())
            {
            case 41: // ')'
                setCertificateProfileIdentifier(aSpe);
                break;

            case 2: // '\002'
                setCertificationAuthorityReference(aSpe);
                break;

            case 73: // 'I'
                setPublicKey(PublicKeyDataObject.getInstance(aSpe.getObject(16)));
                break;

            case 32: // ' '
                setCertificateHolderReference(aSpe);
                break;

            case 76: // 'L'
                setCertificateHolderAuthorization(new CertificateHolderAuthorization(aSpe));
                break;

            case 37: // '%'
                setCertificateEffectiveDate(aSpe);
                break;

            case 36: // '$'
                setCertificateExpirationDate(aSpe);
                break;

            default:
label0:
                {
                    certificateType = 0;
                    break label0;
                }
                break;
            }
        } while(true);
        break MISSING_BLOCK_LABEL_289;
        throw new IOException((new StringBuilder()).append("Not a valid iso7816 DERApplicationSpecific tag ").append(aSpe.getApplicationTag()).toString());
    }

    public CertificateBody(DERApplicationSpecific certificateProfileIdentifier, CertificationAuthorityReference certificationAuthorityReference, PublicKeyDataObject publicKey, CertificateHolderReference certificateHolderReference, CertificateHolderAuthorization certificateHolderAuthorization, PackedDate certificateEffectiveDate, PackedDate certificateExpirationDate)
    {
        certificateType = 0;
        setCertificateProfileIdentifier(certificateProfileIdentifier);
        setCertificationAuthorityReference(new DERApplicationSpecific(2, certificationAuthorityReference.getEncoded()));
        setPublicKey(publicKey);
        setCertificateHolderReference(new DERApplicationSpecific(32, certificateHolderReference.getEncoded()));
        setCertificateHolderAuthorization(certificateHolderAuthorization);
        try
        {
            setCertificateEffectiveDate(new DERApplicationSpecific(false, 37, new DEROctetString(certificateEffectiveDate.getEncoding())));
            setCertificateExpirationDate(new DERApplicationSpecific(false, 36, new DEROctetString(certificateExpirationDate.getEncoding())));
        }
        catch(IOException e)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("unable to encode dates: ").append(e.getMessage()).toString());
        }
    }

    private CertificateBody(DERApplicationSpecific obj)
        throws IOException
    {
        certificateType = 0;
        setIso7816CertificateBody(obj);
    }

    private ASN1Primitive profileToASN1Object()
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certificateProfileIdentifier);
        v.add(certificationAuthorityReference);
        v.add(new DERApplicationSpecific(false, 73, publicKey));
        v.add(certificateHolderReference);
        v.add(certificateHolderAuthorization);
        v.add(certificateEffectiveDate);
        v.add(certificateExpirationDate);
        return new DERApplicationSpecific(78, v);
    }

    private void setCertificateProfileIdentifier(DERApplicationSpecific certificateProfileIdentifier)
        throws IllegalArgumentException
    {
        if(certificateProfileIdentifier.getApplicationTag() == 41)
        {
            this.certificateProfileIdentifier = certificateProfileIdentifier;
            certificateType |= 1;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Not an Iso7816Tags.INTERCHANGE_PROFILE tag :").append(EACTags.encodeTag(certificateProfileIdentifier)).toString());
        }
    }

    private void setCertificateHolderReference(DERApplicationSpecific certificateHolderReference)
        throws IllegalArgumentException
    {
        if(certificateHolderReference.getApplicationTag() == 32)
        {
            this.certificateHolderReference = certificateHolderReference;
            certificateType |= 8;
        } else
        {
            throw new IllegalArgumentException("Not an Iso7816Tags.CARDHOLDER_NAME tag");
        }
    }

    private void setCertificationAuthorityReference(DERApplicationSpecific certificationAuthorityReference)
        throws IllegalArgumentException
    {
        if(certificationAuthorityReference.getApplicationTag() == 2)
        {
            this.certificationAuthorityReference = certificationAuthorityReference;
            certificateType |= 2;
        } else
        {
            throw new IllegalArgumentException("Not an Iso7816Tags.ISSUER_IDENTIFICATION_NUMBER tag");
        }
    }

    private void setPublicKey(PublicKeyDataObject publicKey)
    {
        this.publicKey = PublicKeyDataObject.getInstance(publicKey);
        certificateType |= 4;
    }

    private ASN1Primitive requestToASN1Object()
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(certificateProfileIdentifier);
        v.add(new DERApplicationSpecific(false, 73, publicKey));
        v.add(certificateHolderReference);
        return new DERApplicationSpecific(78, v);
    }

    public ASN1Primitive toASN1Primitive()
    {
        try
        {
            if(certificateType == 127)
                return profileToASN1Object();
        }
        catch(IOException e)
        {
            return null;
        }
        if(certificateType == 13)
            return requestToASN1Object();
        return null;
    }

    public int getCertificateType()
    {
        return certificateType;
    }

    public static CertificateBody getInstance(Object obj)
        throws IOException
    {
        if(obj instanceof CertificateBody)
            return (CertificateBody)obj;
        if(obj != null)
            return new CertificateBody(DERApplicationSpecific.getInstance(obj));
        else
            return null;
    }

    public PackedDate getCertificateEffectiveDate()
    {
        if((certificateType & 0x20) == 32)
            return new PackedDate(certificateEffectiveDate.getContents());
        else
            return null;
    }

    private void setCertificateEffectiveDate(DERApplicationSpecific ced)
        throws IllegalArgumentException
    {
        if(ced.getApplicationTag() == 37)
        {
            certificateEffectiveDate = ced;
            certificateType |= 0x20;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Not an Iso7816Tags.APPLICATION_EFFECTIVE_DATE tag :").append(EACTags.encodeTag(ced)).toString());
        }
    }

    public PackedDate getCertificateExpirationDate()
        throws IOException
    {
        if((certificateType & 0x40) == 64)
            return new PackedDate(certificateExpirationDate.getContents());
        else
            throw new IOException("certificate Expiration Date not set");
    }

    private void setCertificateExpirationDate(DERApplicationSpecific ced)
        throws IllegalArgumentException
    {
        if(ced.getApplicationTag() == 36)
        {
            certificateExpirationDate = ced;
            certificateType |= 0x40;
        } else
        {
            throw new IllegalArgumentException("Not an Iso7816Tags.APPLICATION_EXPIRATION_DATE tag");
        }
    }

    public CertificateHolderAuthorization getCertificateHolderAuthorization()
        throws IOException
    {
        if((certificateType & 0x10) == 16)
            return certificateHolderAuthorization;
        else
            throw new IOException("Certificate Holder Authorisation not set");
    }

    private void setCertificateHolderAuthorization(CertificateHolderAuthorization cha)
    {
        certificateHolderAuthorization = cha;
        certificateType |= 0x10;
    }

    public CertificateHolderReference getCertificateHolderReference()
    {
        return new CertificateHolderReference(certificateHolderReference.getContents());
    }

    public DERApplicationSpecific getCertificateProfileIdentifier()
    {
        return certificateProfileIdentifier;
    }

    public CertificationAuthorityReference getCertificationAuthorityReference()
        throws IOException
    {
        if((certificateType & 2) == 2)
            return new CertificationAuthorityReference(certificationAuthorityReference.getContents());
        else
            throw new IOException("Certification authority reference not set");
    }

    public PublicKeyDataObject getPublicKey()
    {
        return publicKey;
    }

    ASN1InputStream seq;
    private DERApplicationSpecific certificateProfileIdentifier;
    private DERApplicationSpecific certificationAuthorityReference;
    private PublicKeyDataObject publicKey;
    private DERApplicationSpecific certificateHolderReference;
    private CertificateHolderAuthorization certificateHolderAuthorization;
    private DERApplicationSpecific certificateEffectiveDate;
    private DERApplicationSpecific certificateExpirationDate;
    private int certificateType;
    private static final int CPI = 1;
    private static final int CAR = 2;
    private static final int PK = 4;
    private static final int CHR = 8;
    private static final int CHA = 16;
    private static final int CEfD = 32;
    private static final int CExD = 64;
    public static final int profileType = 127;
    public static final int requestType = 13;
}
