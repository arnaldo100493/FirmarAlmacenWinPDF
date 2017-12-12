// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CertificateRequestMessage.java

package co.org.bouncy.cert.crmf;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.crmf.*;
import co.org.bouncy.cert.CertIOException;
import co.org.bouncy.operator.*;
import java.io.IOException;

// Referenced classes of package co.org.bouncy.cert.crmf:
//            PKIArchiveControl, RegTokenControl, AuthenticatorControl, PKMACValueVerifier, 
//            CRMFException, CRMFUtil, Control, PKMACBuilder

public class CertificateRequestMessage
{

    private static CertReqMsg parseBytes(byte encoding[])
        throws IOException
    {
        try
        {
            return CertReqMsg.getInstance(ASN1Primitive.fromByteArray(encoding));
        }
        catch(ClassCastException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
        catch(IllegalArgumentException e)
        {
            throw new CertIOException((new StringBuilder()).append("malformed data: ").append(e.getMessage()).toString(), e);
        }
    }

    public CertificateRequestMessage(byte certReqMsg[])
        throws IOException
    {
        this(parseBytes(certReqMsg));
    }

    public CertificateRequestMessage(CertReqMsg certReqMsg)
    {
        this.certReqMsg = certReqMsg;
        controls = certReqMsg.getCertReq().getControls();
    }

    public CertReqMsg toASN1Structure()
    {
        return certReqMsg;
    }

    public CertTemplate getCertTemplate()
    {
        return certReqMsg.getCertReq().getCertTemplate();
    }

    public boolean hasControls()
    {
        return controls != null;
    }

    public boolean hasControl(ASN1ObjectIdentifier type)
    {
        return findControl(type) != null;
    }

    public Control getControl(ASN1ObjectIdentifier type)
    {
        AttributeTypeAndValue found = findControl(type);
        if(found != null)
        {
            if(found.getType().equals(CRMFObjectIdentifiers.id_regCtrl_pkiArchiveOptions))
                return new PKIArchiveControl(PKIArchiveOptions.getInstance(found.getValue()));
            if(found.getType().equals(CRMFObjectIdentifiers.id_regCtrl_regToken))
                return new RegTokenControl(DERUTF8String.getInstance(found.getValue()));
            if(found.getType().equals(CRMFObjectIdentifiers.id_regCtrl_authenticator))
                return new AuthenticatorControl(DERUTF8String.getInstance(found.getValue()));
        }
        return null;
    }

    private AttributeTypeAndValue findControl(ASN1ObjectIdentifier type)
    {
        if(controls == null)
            return null;
        AttributeTypeAndValue tAndVs[] = controls.toAttributeTypeAndValueArray();
        AttributeTypeAndValue found = null;
        int i = 0;
        do
        {
            if(i == tAndVs.length)
                break;
            if(tAndVs[i].getType().equals(type))
            {
                found = tAndVs[i];
                break;
            }
            i++;
        } while(true);
        return found;
    }

    public boolean hasProofOfPossession()
    {
        return certReqMsg.getPopo() != null;
    }

    public int getProofOfPossessionType()
    {
        return certReqMsg.getPopo().getType();
    }

    public boolean hasSigningKeyProofOfPossessionWithPKMAC()
    {
        ProofOfPossession pop = certReqMsg.getPopo();
        if(pop.getType() == 1)
        {
            POPOSigningKey popoSign = POPOSigningKey.getInstance(pop.getObject());
            return popoSign.getPoposkInput().getPublicKeyMAC() != null;
        } else
        {
            return false;
        }
    }

    public boolean isValidSigningKeyPOP(ContentVerifierProvider verifierProvider)
        throws CRMFException, IllegalStateException
    {
        ProofOfPossession pop = certReqMsg.getPopo();
        if(pop.getType() == 1)
        {
            POPOSigningKey popoSign = POPOSigningKey.getInstance(pop.getObject());
            if(popoSign.getPoposkInput() != null && popoSign.getPoposkInput().getPublicKeyMAC() != null)
                throw new IllegalStateException("verification requires password check");
            else
                return verifySignature(verifierProvider, popoSign);
        } else
        {
            throw new IllegalStateException("not Signing Key type of proof of possession");
        }
    }

    public boolean isValidSigningKeyPOP(ContentVerifierProvider verifierProvider, PKMACBuilder macBuilder, char password[])
        throws CRMFException, IllegalStateException
    {
        ProofOfPossession pop = certReqMsg.getPopo();
        if(pop.getType() == 1)
        {
            POPOSigningKey popoSign = POPOSigningKey.getInstance(pop.getObject());
            if(popoSign.getPoposkInput() == null || popoSign.getPoposkInput().getSender() != null)
                throw new IllegalStateException("no PKMAC present in proof of possession");
            PKMACValue pkMAC = popoSign.getPoposkInput().getPublicKeyMAC();
            PKMACValueVerifier macVerifier = new PKMACValueVerifier(macBuilder);
            if(macVerifier.isValid(pkMAC, password, getCertTemplate().getPublicKey()))
                return verifySignature(verifierProvider, popoSign);
            else
                return false;
        } else
        {
            throw new IllegalStateException("not Signing Key type of proof of possession");
        }
    }

    private boolean verifySignature(ContentVerifierProvider verifierProvider, POPOSigningKey popoSign)
        throws CRMFException
    {
        ContentVerifier verifier;
        try
        {
            verifier = verifierProvider.get(popoSign.getAlgorithmIdentifier());
        }
        catch(OperatorCreationException e)
        {
            throw new CRMFException((new StringBuilder()).append("unable to create verifier: ").append(e.getMessage()).toString(), e);
        }
        if(popoSign.getPoposkInput() != null)
            CRMFUtil.derEncodeToStream(popoSign.getPoposkInput(), verifier.getOutputStream());
        else
            CRMFUtil.derEncodeToStream(certReqMsg.getCertReq(), verifier.getOutputStream());
        return verifier.verify(popoSign.getSignature().getBytes());
    }

    public byte[] getEncoded()
        throws IOException
    {
        return certReqMsg.getEncoded();
    }

    public static final int popRaVerified = 0;
    public static final int popSigningKey = 1;
    public static final int popKeyEncipherment = 2;
    public static final int popKeyAgreement = 3;
    private final CertReqMsg certReqMsg;
    private final Controls controls;
}
