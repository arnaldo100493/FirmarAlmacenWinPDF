// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   TSAClientBouncyCastle.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.codec.Base64;
import co.org.bouncy.asn1.ASN1ObjectIdentifier;
import co.org.bouncy.asn1.cmp.PKIFailureInfo;
import co.org.bouncy.tsp.*;
import java.io.*;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            BouncyCastleDigest, TSAClient, DigestAlgorithms, TSAInfoBouncyCastle

public class TSAClientBouncyCastle
    implements TSAClient
{

    public TSAClientBouncyCastle(String url)
    {
        this(url, null, null, 4096, "SHA-256");
    }

    public TSAClientBouncyCastle(String url, String username, String password)
    {
        this(url, username, password, 4096, "SHA-256");
    }

    public TSAClientBouncyCastle(String url, String username, String password, int tokSzEstimate, String digestAlgorithm)
    {
        tsaURL = url;
        tsaUsername = username;
        tsaPassword = password;
        tokenSizeEstimate = tokSzEstimate;
        this.digestAlgorithm = digestAlgorithm;
    }

    public void setTSAInfo(TSAInfoBouncyCastle tsaInfo)
    {
        this.tsaInfo = tsaInfo;
    }

    public int getTokenSizeEstimate()
    {
        return tokenSizeEstimate;
    }

    public MessageDigest getMessageDigest()
        throws GeneralSecurityException
    {
        return (new BouncyCastleDigest()).getMessageDigest(digestAlgorithm);
    }

    public byte[] getTimeStampToken(byte imprint[])
        throws IOException, TSPException
    {
        byte respBytes[] = null;
        TimeStampRequestGenerator tsqGenerator = new TimeStampRequestGenerator();
        tsqGenerator.setCertReq(true);
        BigInteger nonce = BigInteger.valueOf(System.currentTimeMillis());
        TimeStampRequest request = tsqGenerator.generate(new ASN1ObjectIdentifier(DigestAlgorithms.getAllowedDigests(digestAlgorithm)), imprint, nonce);
        byte requestBytes[] = request.getEncoded();
        respBytes = getTSAResponse(requestBytes);
        TimeStampResponse response = new TimeStampResponse(respBytes);
        response.validate(request);
        PKIFailureInfo failure = response.getFailInfo();
        int value = failure != null ? failure.intValue() : 0;
        if(value != 0)
            throw new IOException(MessageLocalization.getComposedMessage("invalid.tsa.1.response.code.2", new Object[] {
                tsaURL, String.valueOf(value)
            }));
        TimeStampToken tsToken = response.getTimeStampToken();
        if(tsToken == null)
            throw new IOException(MessageLocalization.getComposedMessage("tsa.1.failed.to.return.time.stamp.token.2", new Object[] {
                tsaURL, response.getStatusString()
            }));
        TimeStampTokenInfo tsTokenInfo = tsToken.getTimeStampInfo();
        byte encoded[] = tsToken.getEncoded();
        LOGGER.info((new StringBuilder()).append("Timestamp generated: ").append(tsTokenInfo.getGenTime()).toString());
        if(tsaInfo != null)
            tsaInfo.inspectTimeStampTokenInfo(tsTokenInfo);
        tokenSizeEstimate = encoded.length + 32;
        return encoded;
    }

    protected byte[] getTSAResponse(byte requestBytes[])
        throws IOException
    {
        URL url = new URL(tsaURL);
        URLConnection tsaConnection;
        try
        {
            tsaConnection = url.openConnection();
        }
        catch(IOException ioe)
        {
            throw new IOException(MessageLocalization.getComposedMessage("failed.to.get.tsa.response.from.1", new Object[] {
                tsaURL
            }));
        }
        tsaConnection.setDoInput(true);
        tsaConnection.setDoOutput(true);
        tsaConnection.setUseCaches(false);
        tsaConnection.setRequestProperty("Content-Type", "application/timestamp-query");
        tsaConnection.setRequestProperty("Content-Transfer-Encoding", "binary");
        if(tsaUsername != null && !tsaUsername.equals(""))
        {
            String userPassword = (new StringBuilder()).append(tsaUsername).append(":").append(tsaPassword).toString();
            tsaConnection.setRequestProperty("Authorization", (new StringBuilder()).append("Basic ").append(Base64.encodeBytes(userPassword.getBytes(), 8)).toString());
        }
        OutputStream out = tsaConnection.getOutputStream();
        out.write(requestBytes);
        out.close();
        InputStream inp = tsaConnection.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte buffer[] = new byte[1024];
        for(int bytesRead = 0; (bytesRead = inp.read(buffer, 0, buffer.length)) >= 0;)
            baos.write(buffer, 0, bytesRead);

        byte respBytes[] = baos.toByteArray();
        String encoding = tsaConnection.getContentEncoding();
        if(encoding != null && encoding.equalsIgnoreCase("base64"))
            respBytes = Base64.decode(new String(respBytes));
        return respBytes;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/security/TSAClientBouncyCastle);
    protected String tsaURL;
    protected String tsaUsername;
    protected String tsaPassword;
    protected TSAInfoBouncyCastle tsaInfo;
    public static final int DEFAULTTOKENSIZE = 4096;
    protected int tokenSizeEstimate;
    public static final String DEFAULTHASHALGORITHM = "SHA-256";
    protected String digestAlgorithm;

}
