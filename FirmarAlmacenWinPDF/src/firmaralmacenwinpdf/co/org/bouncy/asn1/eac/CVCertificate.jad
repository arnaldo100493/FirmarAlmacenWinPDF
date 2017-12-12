// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CVCertificate.java

package co.org.bouncy.asn1.eac;

import co.org.bouncy.asn1.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.asn1.eac:
//            Flags, CertificateBody, CertificateHolderAuthorization, PackedDate, 
//            CertificationAuthorityReference, CertificateHolderReference

public class CVCertificate extends ASN1Object
{

    private void setPrivateData(DERApplicationSpecific appSpe)
        throws IOException
    {
        valid = 0;
        if(appSpe.getApplicationTag() != 33) goto _L2; else goto _L1
_L1:
        ASN1InputStream content = new ASN1InputStream(appSpe.getContents());
_L6:
        ASN1Primitive tmpObj;
        if((tmpObj = content.readObject()) == null)
            break; /* Loop/switch isn't completed */
        if(!(tmpObj instanceof DERApplicationSpecific)) goto _L4; else goto _L3
_L3:
        DERApplicationSpecific aSpe = (DERApplicationSpecific)tmpObj;
        switch(aSpe.getApplicationTag())
        {
        case 78: // 'N'
            certificateBody = CertificateBody.getInstance(aSpe);
            valid |= bodyValid;
            break;

        case 55: // '7'
            signature = aSpe.getContents();
            valid |= signValid;
            break;

        default:
            throw new IOException((new StringBuilder()).append("Invalid tag, not an Iso7816CertificateStructure :").append(aSpe.getApplicationTag()).toString());
        }
        if(true) goto _L6; else goto _L5
_L4:
        throw new IOException("Invalid Object, not an Iso7816CertificateStructure");
_L2:
        throw new IOException((new StringBuilder()).append("not a CARDHOLDER_CERTIFICATE :").append(appSpe.getApplicationTag()).toString());
_L5:
    }

    public CVCertificate(ASN1InputStream aIS)
        throws IOException
    {
        initFrom(aIS);
    }

    private void initFrom(ASN1InputStream aIS)
        throws IOException
    {
        ASN1Primitive obj;
        while((obj = aIS.readObject()) != null) 
            if(obj instanceof DERApplicationSpecific)
                setPrivateData((DERApplicationSpecific)obj);
            else
                throw new IOException("Invalid Input Stream for creating an Iso7816CertificateStructure");
    }

    private CVCertificate(DERApplicationSpecific appSpe)
        throws IOException
    {
        setPrivateData(appSpe);
    }

    public CVCertificate(CertificateBody body, byte signature[])
        throws IOException
    {
        certificateBody = body;
        this.signature = signature;
        valid |= bodyValid;
        valid |= signValid;
    }

    public static CVCertificate getInstance(Object obj)
    {
        if(obj instanceof CVCertificate)
            return (CVCertificate)obj;
        if(obj != null)
            try
            {
                return new CVCertificate(DERApplicationSpecific.getInstance(obj));
            }
            catch(IOException e)
            {
                throw new ASN1ParsingException((new StringBuilder()).append("unable to parse data: ").append(e.getMessage()).toString(), e);
            }
        else
            return null;
    }

    public byte[] getSignature()
    {
        return signature;
    }

    public CertificateBody getBody()
    {
        return certificateBody;
    }

    public ASN1Primitive toASN1Primitive()
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        if(valid != (signValid | bodyValid))
            return null;
        v.add(certificateBody);
        try
        {
            v.add(new DERApplicationSpecific(false, 55, new DEROctetString(signature)));
        }
        catch(IOException e)
        {
            throw new IllegalStateException("unable to convert signature!");
        }
        return new DERApplicationSpecific(33, v);
    }

    public ASN1ObjectIdentifier getHolderAuthorization()
        throws IOException
    {
        CertificateHolderAuthorization cha = certificateBody.getCertificateHolderAuthorization();
        return cha.getOid();
    }

    public PackedDate getEffectiveDate()
        throws IOException
    {
        return certificateBody.getCertificateEffectiveDate();
    }

    public int getCertificateType()
    {
        return certificateBody.getCertificateType();
    }

    public PackedDate getExpirationDate()
        throws IOException
    {
        return certificateBody.getCertificateExpirationDate();
    }

    public int getRole()
        throws IOException
    {
        CertificateHolderAuthorization cha = certificateBody.getCertificateHolderAuthorization();
        return cha.getAccessRights();
    }

    public CertificationAuthorityReference getAuthorityReference()
        throws IOException
    {
        return certificateBody.getCertificationAuthorityReference();
    }

    public CertificateHolderReference getHolderReference()
        throws IOException
    {
        return certificateBody.getCertificateHolderReference();
    }

    public int getHolderAuthorizationRole()
        throws IOException
    {
        int rights = certificateBody.getCertificateHolderAuthorization().getAccessRights();
        return rights & 0xc0;
    }

    public Flags getHolderAuthorizationRights()
        throws IOException
    {
        return new Flags(certificateBody.getCertificateHolderAuthorization().getAccessRights() & 0x1f);
    }

    private CertificateBody certificateBody;
    private byte signature[];
    private int valid;
    private static int bodyValid = 1;
    private static int signValid = 2;
    public static final byte version_1 = 0;
    public static String ReferenceEncoding = "ISO-8859-1";

}
