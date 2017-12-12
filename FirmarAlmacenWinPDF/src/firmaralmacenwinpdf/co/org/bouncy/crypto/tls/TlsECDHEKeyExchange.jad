// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TlsECDHEKeyExchange.java

package co.org.bouncy.crypto.tls;

import co.org.bouncy.crypto.*;
import co.org.bouncy.crypto.io.SignerInputStream;
import co.org.bouncy.crypto.params.*;
import java.io.*;
import java.util.Vector;

// Referenced classes of package co.org.bouncy.crypto.tls:
//            TlsECDHKeyExchange, TlsSignerCredentials, TlsFatalAlert, CombinedHash, 
//            SecurityParameters, TlsCredentials, TlsECCUtils, TlsProtocol, 
//            TlsContext, TlsUtils, CertificateRequest, TlsSigner

public class TlsECDHEKeyExchange extends TlsECDHKeyExchange
{

    public TlsECDHEKeyExchange(int keyExchange, Vector supportedSignatureAlgorithms, int namedCurves[], short clientECPointFormats[], short serverECPointFormats[])
    {
        super(keyExchange, supportedSignatureAlgorithms, namedCurves, clientECPointFormats, serverECPointFormats);
        serverCredentials = null;
    }

    public void processServerCredentials(TlsCredentials serverCredentials)
        throws IOException
    {
        if(!(serverCredentials instanceof TlsSignerCredentials))
        {
            throw new TlsFatalAlert((short)80);
        } else
        {
            processServerCertificate(serverCredentials.getCertificate());
            this.serverCredentials = (TlsSignerCredentials)serverCredentials;
            return;
        }
    }

    public byte[] generateServerKeyExchange()
        throws IOException
    {
        int namedCurve = -1;
        if(namedCurves == null)
        {
            namedCurve = 23;
        } else
        {
            int i = 0;
            do
            {
                if(i >= namedCurves.length)
                    break;
                int entry = namedCurves[i];
                if(TlsECCUtils.isSupportedNamedCurve(entry))
                {
                    namedCurve = entry;
                    break;
                }
                i++;
            } while(true);
        }
        ECDomainParameters curve_params = null;
        if(namedCurve >= 0)
            curve_params = TlsECCUtils.getParametersForNamedCurve(namedCurve);
        else
        if(TlsProtocol.arrayContains(namedCurves, 65281))
            curve_params = TlsECCUtils.getParametersForNamedCurve(23);
        else
        if(TlsProtocol.arrayContains(namedCurves, 65282))
            curve_params = TlsECCUtils.getParametersForNamedCurve(7);
        if(curve_params == null)
            throw new TlsFatalAlert((short)80);
        AsymmetricCipherKeyPair kp = TlsECCUtils.generateECKeyPair(context.getSecureRandom(), curve_params);
        ecAgreeServerPrivateKey = (ECPrivateKeyParameters)kp.getPrivate();
        byte publicBytes[] = TlsECCUtils.serializeECPublicKey(clientECPointFormats, (ECPublicKeyParameters)kp.getPublic());
        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        if(namedCurve < 0)
            TlsECCUtils.writeExplicitECParameters(clientECPointFormats, curve_params, buf);
        else
            TlsECCUtils.writeNamedECParameters(namedCurve, buf);
        TlsUtils.writeOpaque8(publicBytes, buf);
        byte digestInput[] = buf.toByteArray();
        Digest d = new CombinedHash();
        SecurityParameters securityParameters = context.getSecurityParameters();
        d.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        d.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        d.update(digestInput, 0, digestInput.length);
        byte hash[] = new byte[d.getDigestSize()];
        d.doFinal(hash, 0);
        byte sigBytes[] = serverCredentials.generateCertificateSignature(hash);
        TlsUtils.writeOpaque16(sigBytes, buf);
        return buf.toByteArray();
    }

    public void processServerKeyExchange(InputStream input)
        throws IOException
    {
        SecurityParameters securityParameters = context.getSecurityParameters();
        Signer signer = initVerifyer(tlsSigner, securityParameters);
        InputStream sigIn = new SignerInputStream(input, signer);
        ECDomainParameters curve_params = TlsECCUtils.readECParameters(namedCurves, clientECPointFormats, sigIn);
        byte point[] = TlsUtils.readOpaque8(sigIn);
        byte sigByte[] = TlsUtils.readOpaque16(input);
        if(!signer.verifySignature(sigByte))
        {
            throw new TlsFatalAlert((short)51);
        } else
        {
            ecAgreeServerPublicKey = TlsECCUtils.validateECPublicKey(TlsECCUtils.deserializeECPublicKey(clientECPointFormats, curve_params, point));
            return;
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
                i++;
                break;
            }
        } while(true);
    }

    public void processClientCredentials(TlsCredentials clientCredentials)
        throws IOException
    {
        if(!(clientCredentials instanceof TlsSignerCredentials))
            throw new TlsFatalAlert((short)80);
        else
            return;
    }

    protected Signer initVerifyer(TlsSigner tlsSigner, SecurityParameters securityParameters)
    {
        Signer signer = tlsSigner.createVerifyer(serverPublicKey);
        signer.update(securityParameters.clientRandom, 0, securityParameters.clientRandom.length);
        signer.update(securityParameters.serverRandom, 0, securityParameters.serverRandom.length);
        return signer;
    }

    protected TlsSignerCredentials serverCredentials;
}
