// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   EACCertificateBuilder.java

package co.org.bouncy.eac;

import co.org.bouncy.asn1.DERApplicationSpecific;
import co.org.bouncy.asn1.eac.*;
import co.org.bouncy.eac.operator.EACSigner;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.eac:
//            EACCertificateHolder, EACException

public class EACCertificateBuilder
{

    public EACCertificateBuilder(CertificationAuthorityReference certificationAuthorityReference, PublicKeyDataObject publicKey, CertificateHolderReference certificateHolderReference, CertificateHolderAuthorization certificateHolderAuthorization, PackedDate certificateEffectiveDate, PackedDate certificateExpirationDate)
    {
        this.certificationAuthorityReference = certificationAuthorityReference;
        this.publicKey = publicKey;
        this.certificateHolderReference = certificateHolderReference;
        this.certificateHolderAuthorization = certificateHolderAuthorization;
        this.certificateEffectiveDate = certificateEffectiveDate;
        this.certificateExpirationDate = certificateExpirationDate;
    }

    private CertificateBody buildBody()
    {
        DERApplicationSpecific certificateProfileIdentifier = new DERApplicationSpecific(41, ZeroArray);
        CertificateBody body = new CertificateBody(certificateProfileIdentifier, certificationAuthorityReference, publicKey, certificateHolderReference, certificateHolderAuthorization, certificateEffectiveDate, certificateExpirationDate);
        return body;
    }

    public EACCertificateHolder build(EACSigner signer)
        throws EACException
    {
        try
        {
            CertificateBody body = buildBody();
            OutputStream vOut = signer.getOutputStream();
            vOut.write(body.getEncoded("DER"));
            vOut.close();
            return new EACCertificateHolder(new CVCertificate(body, signer.getSignature()));
        }
        catch(Exception e)
        {
            throw new EACException((new StringBuilder()).append("unable to process signature: ").append(e.getMessage()).toString(), e);
        }
    }

    private static final byte ZeroArray[] = {
        0
    };
    private PublicKeyDataObject publicKey;
    private CertificateHolderAuthorization certificateHolderAuthorization;
    private PackedDate certificateEffectiveDate;
    private PackedDate certificateExpirationDate;
    private CertificateHolderReference certificateHolderReference;
    private CertificationAuthorityReference certificationAuthorityReference;

}
