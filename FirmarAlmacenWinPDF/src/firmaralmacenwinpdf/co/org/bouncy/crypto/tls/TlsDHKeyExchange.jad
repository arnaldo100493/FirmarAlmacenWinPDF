// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsDHKeyExchange.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsKeyExchange, TlsRSASigner, TlsDSSSigner, TlsFatalAlert, 
//            TlsAgreementCredentials, TlsSignerCredentials, Certificate, TlsSigner, 
//            TlsUtils, CertificateRequest, TlsContext, TlsDHUtils, 
//            TlsCredentials

public class TlsDHKeyExchange extends AbstractTlsKeyExchange
{

    public TlsDHKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, DHParameters dhParameters)
    {
        super(keyExchange, supportedSignatureAlgorithms);
        switch(keyExchange)
        {
        case 7: // '\007'
        case 9: // '\t'
            tlsSigner = null;
            break;

        case 5: // '\005'
            tlsSigner = new TlsRSASigner();
            break;

        case 3: // '\003'
            tlsSigner = new TlsDSSSigner();
            break;

        case 4: // '\004'
        case 6: // '\006'
        case 8: // '\b'
        default:
            throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.dhParameters = dhParameters;
    }

    public void init(TlsContext context)
    {
        super.init(context);
        if(tlsSigner != null)
            tlsSigner.init(context);
    }

    public void skipServerCredentials()
        throws IOException
    {
        throw new TlsFatalAlert((short)10);
    }

    public void processServerCertificate(co.org.bouncy.crypto.tls.Certificate serverCertificate)
        throws IOException
    {
        if(serverCertificate.isEmpty())
            throw new TlsFatalAlert((short)42);
        Certificate x509Cert = serverCertificate.getCertificateAt(0);
        SubjectPublicKeyInfo keyInfo = x509Cert.getSubjectPublicKeyInfo();
        try
        {
            serverPublicKey = PublicKeyFactory.createKey(keyInfo);
        }
        catch(RuntimeException e)
        {
            throw new TlsFatalAlert((short)43);
        }
        if(tlsSigner == null)
        {
            try
            {
                dhAgreeServerPublicKey = validateDHPublicKey((DHPublicKeyParameters)serverPublicKey);
            }
            catch(ClassCastException e)
            {
                throw new TlsFatalAlert((short)46);
            }
            TlsUtils.validateKeyUsage(x509Cert, 8);
        } else
        {
            if(!tlsSigner.isValidPublicKey(serverPublicKey))
                throw new TlsFatalAlert((short)46);
            TlsUtils.validateKeyUsage(x509Cert, 128);
        }
        super.processServerCertificate(serverCertificate);
    }

    public boolean requiresServerKeyExchange()
    {
        switch(keyExchange)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 11: // '\013'
            return true;
        }
        return false;
    }

    public void validateCertificateRequest(CertificateRequest certificateRequest)
        throws IOException
    {
        short types[] = certificateRequest.getCertificateTypes();
        int i = 0;
        do
        {
            if(i >= types.length)
                break;
            switch(types[i])
            {
            default:
                throw new TlsFatalAlert((short)47);

            case 1: // '\001'
            case 2: // '\002'
            case 3: // '\003'
            case 4: // '\004'
            case 64: // '@'
                i++;
                break;
            }
        } while(true);
    }

    public void processClientCredentials(TlsCredentials clientCredentials)
        throws IOException
    {
        if(clientCredentials instanceof TlsAgreementCredentials)
            agreementCredentials = (TlsAgreementCredentials)clientCredentials;
        else
        if(!(clientCredentials instanceof TlsSignerCredentials))
            throw new TlsFatalAlert((short)80);
    }

    public void generateClientKeyExchange(OutputStream output)
        throws IOException
    {
        if(agreementCredentials == null)
            dhAgreeClientPrivateKey = TlsDHUtils.generateEphemeralClientKeyExchange(context.getSecureRandom(), dhAgreeServerPublicKey.getParameters(), output);
    }

    public byte[] generatePremasterSecret()
        throws IOException
    {
        if(agreementCredentials != null)
            return agreementCredentials.generateAgreement(dhAgreeServerPublicKey);
        else
            return calculateDHBasicAgreement(dhAgreeServerPublicKey, dhAgreeClientPrivateKey);
    }

    protected boolean areCompatibleParameters(DHParameters a, DHParameters b)
    {
        return a.getP().equals(b.getP()) && a.getG().equals(b.getG());
    }

    protected byte[] calculateDHBasicAgreement(DHPublicKeyParameters publicKey, DHPrivateKeyParameters privateKey)
    {
        return TlsDHUtils.calculateDHBasicAgreement(publicKey, privateKey);
    }

    protected AsymmetricCipherKeyPair generateDHKeyPair(DHParameters dhParams)
    {
        return TlsDHUtils.generateDHKeyPair(context.getSecureRandom(), dhParams);
    }

    protected DHPublicKeyParameters validateDHPublicKey(DHPublicKeyParameters key)
        throws IOException
    {
        return TlsDHUtils.validateDHPublicKey(key);
    }

    protected static final BigInteger ONE = BigInteger.valueOf(1L);
    protected static final BigInteger TWO = BigInteger.valueOf(2L);
    protected TlsSigner tlsSigner;
    protected DHParameters dhParameters;
    protected AsymmetricKeyParameter serverPublicKey;
    protected DHPublicKeyParameters dhAgreeServerPublicKey;
    protected TlsAgreementCredentials agreementCredentials;
    protected DHPrivateKeyParameters dhAgreeClientPrivateKey;
    protected DHPublicKeyParameters dhAgreeClientPublicKey;

}
