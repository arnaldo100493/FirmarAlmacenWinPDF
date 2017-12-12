// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsECDHKeyExchange.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.asn1.x509.Certificate;
import co.org.bouncy.asn1.x509.SubjectPublicKeyInfo;
import co.org.bouncy.crypto.AsymmetricCipherKeyPair;
import co.org.bouncy.crypto.params.*;
import co.org.bouncy.crypto.util.PublicKeyFactory;
import java.io.*;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            AbstractTlsKeyExchange, TlsRSASigner, TlsECDSASigner, TlsFatalAlert, 
//            TlsAgreementCredentials, TlsSignerCredentials, Certificate, TlsSigner, 
//            TlsECCUtils, TlsUtils, CertificateRequest, TlsContext, 
//            TlsCredentials

public class TlsECDHKeyExchange extends AbstractTlsKeyExchange
{

    public TlsECDHKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, int namedCurves[], short clientECPointFormats[], short serverECPointFormats[])
    {
        super(keyExchange, supportedSignatureAlgorithms);
        switch(keyExchange)
        {
        case 19: // '\023'
            tlsSigner = new TlsRSASigner();
            break;

        case 17: // '\021'
            tlsSigner = new TlsECDSASigner();
            break;

        case 16: // '\020'
        case 18: // '\022'
            tlsSigner = null;
            break;

        default:
            throw new IllegalArgumentException("unsupported key exchange algorithm");
        }
        this.keyExchange = keyExchange;
        this.namedCurves = namedCurves;
        this.clientECPointFormats = clientECPointFormats;
        this.serverECPointFormats = serverECPointFormats;
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
                ecAgreeServerPublicKey = TlsECCUtils.validateECPublicKey((ECPublicKeyParameters)serverPublicKey);
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
        case 17: // '\021'
        case 19: // '\023'
        case 20: // '\024'
            return true;

        case 18: // '\022'
        default:
            return false;
        }
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
            case 64: // '@'
            case 65: // 'A'
            case 66: // 'B'
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
        if(agreementCredentials != null)
        {
            return;
        } else
        {
            AsymmetricCipherKeyPair ecAgreeClientKeyPair = TlsECCUtils.generateECKeyPair(context.getSecureRandom(), ecAgreeServerPublicKey.getParameters());
            ecAgreeClientPrivateKey = (ECPrivateKeyParameters)ecAgreeClientKeyPair.getPrivate();
            byte point[] = TlsECCUtils.serializeECPublicKey(serverECPointFormats, (ECPublicKeyParameters)ecAgreeClientKeyPair.getPublic());
            TlsUtils.writeOpaque8(point, output);
            return;
        }
    }

    public void processClientCertificate(co.org.bouncy.crypto.tls.Certificate certificate)
        throws IOException
    {
    }

    public void processClientKeyExchange(InputStream input)
        throws IOException
    {
        if(ecAgreeClientPublicKey != null)
        {
            return;
        } else
        {
            byte point[] = TlsUtils.readOpaque8(input);
            co.org.bouncy.crypto.params.ECDomainParameters curve_params = ecAgreeServerPrivateKey.getParameters();
            ecAgreeClientPublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(serverECPointFormats, curve_params, point));
            return;
        }
    }

    public byte[] generatePremasterSecret()
        throws IOException
    {
        if(agreementCredentials != null)
            return agreementCredentials.generateAgreement(ecAgreeServerPublicKey);
        if(ecAgreeServerPrivateKey != null)
            return TlsECCUtils.calculateECDHBasicAgreement(ecAgreeClientPublicKey, ecAgreeServerPrivateKey);
        if(ecAgreeClientPrivateKey != null)
            return TlsECCUtils.calculateECDHBasicAgreement(ecAgreeServerPublicKey, ecAgreeClientPrivateKey);
        else
            throw new TlsFatalAlert((short)80);
    }

    protected TlsSigner tlsSigner;
    protected int namedCurves[];
    protected short clientECPointFormats[];
    protected short serverECPointFormats[];
    protected AsymmetricKeyParameter serverPublicKey;
    protected ECPublicKeyParameters ecAgreeServerPublicKey;
    protected TlsAgreementCredentials agreementCredentials;
    protected ECPrivateKeyParameters ecAgreeClientPrivateKey;
    protected ECPrivateKeyParameters ecAgreeServerPrivateKey;
    protected ECPublicKeyParameters ecAgreeClientPublicKey;
}
