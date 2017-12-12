// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ProtectedPKIMessage.java

package co.org.bouncy.cert.cmp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cmp.*;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.X509CertificateHolder;
import co.org.bouncy.cert.crmf.PKMACBuilder;
import co.org.bouncy.operator.*;
import co.org.bouncy.util.Arrays;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.cert.cmp:
//            CMPException, GeneralPKIMessage

public class ProtectedPKIMessage
{

    public ProtectedPKIMessage(GeneralPKIMessage pkiMessage)
    {
        if(!pkiMessage.hasProtection())
        {
            throw new IllegalArgumentException("PKIMessage not protected");
        } else
        {
            this.pkiMessage = pkiMessage.toASN1Structure();
            return;
        }
    }

    ProtectedPKIMessage(PKIMessage pkiMessage)
    {
        if(pkiMessage.getHeader().getProtectionAlg() == null)
        {
            throw new IllegalArgumentException("PKIMessage not protected");
        } else
        {
            this.pkiMessage = pkiMessage;
            return;
        }
    }

    public PKIHeader getHeader()
    {
        return pkiMessage.getHeader();
    }

    public PKIBody getBody()
    {
        return pkiMessage.getBody();
    }

    public PKIMessage toASN1Structure()
    {
        return pkiMessage;
    }

    public boolean hasPasswordBasedMacProtection()
    {
        return pkiMessage.getHeader().getProtectionAlg().getAlgorithm().equals(CMPObjectIdentifiers.passwordBasedMac);
    }

    public X509CertificateHolder[] getCertificates()
    {
        CMPCertificate certs[] = pkiMessage.getExtraCerts();
        if(certs == null)
            return new X509CertificateHolder[0];
        X509CertificateHolder res[] = new X509CertificateHolder[certs.length];
        for(int i = 0; i != certs.length; i++)
            res[i] = new X509CertificateHolder(certs[i].getX509v3PKCert());

        return res;
    }

    public boolean verify(ContentVerifierProvider verifierProvider)
        throws CMPException
    {
        try
        {
            ContentVerifier verifier = verifierProvider.get(pkiMessage.getHeader().getProtectionAlg());
            return verifySignature(pkiMessage.getProtection().getBytes(), verifier);
        }
        catch(Exception e)
        {
            throw new CMPException((new StringBuilder()).append("unable to verify signature: ").append(e.getMessage()).toString(), e);
        }
    }

    public boolean verify(PKMACBuilder pkMacBuilder, char password[])
        throws CMPException
    {
        if(!CMPObjectIdentifiers.passwordBasedMac.equals(pkiMessage.getHeader().getProtectionAlg().getAlgorithm()))
            throw new CMPException("protection algorithm not mac based");
        try
        {
            pkMacBuilder.setParameters(PBMParameter.getInstance(pkiMessage.getHeader().getProtectionAlg().getParameters()));
            MacCalculator calculator = pkMacBuilder.build(password);
            OutputStream macOut = calculator.getOutputStream();
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(pkiMessage.getHeader());
            v.add(pkiMessage.getBody());
            macOut.write((new DERSequence(v)).getEncoded("DER"));
            macOut.close();
            return Arrays.areEqual(calculator.getMac(), pkiMessage.getProtection().getBytes());
        }
        catch(Exception e)
        {
            throw new CMPException((new StringBuilder()).append("unable to verify MAC: ").append(e.getMessage()).toString(), e);
        }
    }

    private boolean verifySignature(byte signature[], ContentVerifier verifier)
        throws IOException
    {
        ASN1EncodableVector v = new ASN1EncodableVector();
        v.add(pkiMessage.getHeader());
        v.add(pkiMessage.getBody());
        OutputStream sOut = verifier.getOutputStream();
        sOut.write((new DERSequence(v)).getEncoded("DER"));
        sOut.close();
        return verifier.verify(signature);
    }

    private PKIMessage pkiMessage;
}
