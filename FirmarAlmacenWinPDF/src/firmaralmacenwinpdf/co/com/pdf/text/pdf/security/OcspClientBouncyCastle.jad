// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OcspClientBouncyCastle.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.io.StreamUtil;
import co.com.pdf.text.log.*;
import co.com.pdf.text.pdf.PdfEncryption;
import co.org.bouncy.asn1.DEROctetString;
import co.org.bouncy.asn1.ocsp.OCSPObjectIdentifiers;
import co.org.bouncy.asn1.x509.Extension;
import co.org.bouncy.asn1.x509.Extensions;
import co.org.bouncy.cert.jcajce.JcaX509CertificateHolder;
import co.org.bouncy.cert.ocsp.*;
import co.org.bouncy.jce.provider.BouncyCastleProvider;
import co.org.bouncy.ocsp.RevokedStatus;
import co.org.bouncy.operator.DigestCalculatorProvider;
import co.org.bouncy.operator.OperatorException;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import java.io.*;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            OcspClient, CertificateUtil

public class OcspClientBouncyCastle
    implements OcspClient
{

    public OcspClientBouncyCastle()
    {
    }

    private static OCSPReq generateOCSPRequest(X509Certificate issuerCert, BigInteger serialNumber)
        throws OCSPException, IOException, OperatorException, CertificateEncodingException
    {
        Security.addProvider(new BouncyCastleProvider());
        CertificateID id = new CertificateID((new JcaDigestCalculatorProviderBuilder()).build().get(CertificateID.HASH_SHA1), new JcaX509CertificateHolder(issuerCert), serialNumber);
        OCSPReqBuilder gen = new OCSPReqBuilder();
        gen.addRequest(id);
        Extension ext = new Extension(OCSPObjectIdentifiers.id_pkix_ocsp_nonce, false, new DEROctetString((new DEROctetString(PdfEncryption.createDocumentId())).getEncoded()));
        gen.setRequestExtensions(new Extensions(new Extension[] {
            ext
        }));
        return gen.build();
    }

    private OCSPResp getOcspResponse(X509Certificate checkCert, X509Certificate rootCert, String url)
        throws GeneralSecurityException, OCSPException, IOException, OperatorException
    {
        if(checkCert == null || rootCert == null)
            return null;
        if(url == null)
            url = CertificateUtil.getOCSPURL(checkCert);
        if(url == null)
            return null;
        LOGGER.info((new StringBuilder()).append("Getting OCSP from ").append(url).toString());
        OCSPReq request = generateOCSPRequest(rootCert, checkCert.getSerialNumber());
        byte array[] = request.getEncoded();
        URL urlt = new URL(url);
        HttpURLConnection con = (HttpURLConnection)urlt.openConnection();
        con.setRequestProperty("Content-Type", "application/ocsp-request");
        con.setRequestProperty("Accept", "application/ocsp-response");
        con.setDoOutput(true);
        OutputStream out = con.getOutputStream();
        DataOutputStream dataOut = new DataOutputStream(new BufferedOutputStream(out));
        dataOut.write(array);
        dataOut.flush();
        dataOut.close();
        if(con.getResponseCode() / 100 != 2)
        {
            throw new IOException(MessageLocalization.getComposedMessage("invalid.http.response.1", con.getResponseCode()));
        } else
        {
            InputStream in = (InputStream)con.getContent();
            return new OCSPResp(StreamUtil.inputStreamToArray(in));
        }
    }

    public BasicOCSPResp getBasicOCSPResp(X509Certificate checkCert, X509Certificate rootCert, String url)
    {
        OCSPResp ocspResponse;
        ocspResponse = getOcspResponse(checkCert, rootCert, url);
        if(ocspResponse == null)
            return null;
        if(ocspResponse.getStatus() != 0)
            return null;
        try
        {
            return (BasicOCSPResp)ocspResponse.getResponseObject();
        }
        catch(Exception ex)
        {
            if(LOGGER.isLogging(Level.ERROR))
                LOGGER.error(ex.getMessage());
        }
        return null;
    }

    public byte[] getEncoded(X509Certificate checkCert, X509Certificate rootCert, String url)
    {
        Object status;
        BasicOCSPResp basicResponse = getBasicOCSPResp(checkCert, rootCert, url);
        if(basicResponse == null)
            break MISSING_BLOCK_LABEL_129;
        SingleResp responses[] = basicResponse.getResponses();
        if(responses.length != 1)
            break MISSING_BLOCK_LABEL_129;
        SingleResp resp = responses[0];
        status = resp.getCertStatus();
        if(status == CertificateStatus.GOOD)
            return basicResponse.getEncoded();
        try
        {
            if(status instanceof RevokedStatus)
                throw new IOException(MessageLocalization.getComposedMessage("ocsp.status.is.revoked", new Object[0]));
            else
                throw new IOException(MessageLocalization.getComposedMessage("ocsp.status.is.unknown", new Object[0]));
        }
        catch(Exception ex)
        {
            if(LOGGER.isLogging(Level.ERROR))
                LOGGER.error(ex.getMessage());
        }
        return null;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/security/OcspClientBouncyCastle);

}
