// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   MakeSignature.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.DocumentException;
import co.com.pdf.text.io.*;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.*;
import java.io.*;
import java.security.GeneralSecurityException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            PdfPKCS7, CrlClient, ExternalDigest, ExternalSignature, 
//            OcspClient, TSAClient, ExternalSignatureContainer, DigestAlgorithms

public class MakeSignature
{
    public static final class CryptoStandard extends Enum
    {

        public static CryptoStandard[] values()
        {
            return (CryptoStandard[])$VALUES.clone();
        }

        public static CryptoStandard valueOf(String name)
        {
            return (CryptoStandard)Enum.valueOf(co/com/pdf/text/pdf/security/MakeSignature$CryptoStandard, name);
        }

        public static final CryptoStandard CMS;
        public static final CryptoStandard CADES;
        private static final CryptoStandard $VALUES[];

        static 
        {
            CMS = new CryptoStandard("CMS", 0);
            CADES = new CryptoStandard("CADES", 1);
            $VALUES = (new CryptoStandard[] {
                CMS, CADES
            });
        }

        private CryptoStandard(String s, int i)
        {
            super(s, i);
        }
    }


    public MakeSignature()
    {
    }

    public static void signDetached(PdfSignatureAppearance sap, ExternalDigest externalDigest, ExternalSignature externalSignature, Certificate chain[], Collection crlList, OcspClient ocspClient, TSAClient tsaClient, int estimatedSize, 
            CryptoStandard sigtype)
        throws IOException, DocumentException, GeneralSecurityException
    {
        Collection crlBytes = null;
        for(int i = 0; crlBytes == null && i < chain.length; crlBytes = processCrl(chain[i++], crlList));
        if(estimatedSize == 0)
        {
            estimatedSize = 8192;
            if(crlBytes != null)
            {
                for(Iterator i$ = crlBytes.iterator(); i$.hasNext();)
                {
                    byte element[] = (byte[])i$.next();
                    estimatedSize += element.length + 10;
                }

            }
            if(ocspClient != null)
                estimatedSize += 4192;
            if(tsaClient != null)
                estimatedSize += 4192;
        }
        sap.setCertificate(chain[0]);
        if(sigtype == CryptoStandard.CADES)
            sap.addDeveloperExtension(PdfDeveloperExtension.ESIC_1_7_EXTENSIONLEVEL2);
        PdfSignature dic = new PdfSignature(PdfName.ADOBE_PPKLITE, sigtype != CryptoStandard.CADES ? PdfName.ADBE_PKCS7_DETACHED : PdfName.ETSI_CADES_DETACHED);
        dic.setReason(sap.getReason());
        dic.setLocation(sap.getLocation());
        dic.setContact(sap.getContact());
        dic.setDate(new PdfDate(sap.getSignDate()));
        sap.setCryptoDictionary(dic);
        HashMap exc = new HashMap();
        exc.put(PdfName.CONTENTS, new Integer(estimatedSize * 2 + 2));
        sap.preClose(exc);
        String hashAlgorithm = externalSignature.getHashAlgorithm();
        PdfPKCS7 sgn = new PdfPKCS7(null, chain, hashAlgorithm, null, externalDigest, false);
        InputStream data = sap.getRangeStream();
        byte hash[] = DigestAlgorithms.digest(data, externalDigest.getMessageDigest(hashAlgorithm));
        Calendar cal = Calendar.getInstance();
        byte ocsp[] = null;
        if(chain.length >= 2 && ocspClient != null)
            ocsp = ocspClient.getEncoded((X509Certificate)chain[0], (X509Certificate)chain[1], null);
        byte sh[] = sgn.getAuthenticatedAttributeBytes(hash, cal, ocsp, crlBytes, sigtype);
        byte extSignature[] = externalSignature.sign(sh);
        sgn.setExternalDigest(extSignature, null, externalSignature.getEncryptionAlgorithm());
        byte encodedSig[] = sgn.getEncodedPKCS7(hash, cal, tsaClient, ocsp, crlBytes, sigtype);
        if(estimatedSize < encodedSig.length)
        {
            throw new IOException("Not enough space");
        } else
        {
            byte paddedSig[] = new byte[estimatedSize];
            System.arraycopy(encodedSig, 0, paddedSig, 0, encodedSig.length);
            PdfDictionary dic2 = new PdfDictionary();
            dic2.put(PdfName.CONTENTS, (new PdfString(paddedSig)).setHexWriting(true));
            sap.close(dic2);
            return;
        }
    }

    public static Collection processCrl(Certificate cert, Collection crlList)
    {
        if(crlList == null)
            return null;
        ArrayList crlBytes = new ArrayList();
        Iterator i$ = crlList.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            CrlClient cc = (CrlClient)i$.next();
            if(cc != null)
            {
                LOGGER.info((new StringBuilder()).append("Processing ").append(cc.getClass().getName()).toString());
                Collection b = cc.getEncoded((X509Certificate)cert, null);
                if(b != null)
                    crlBytes.addAll(b);
            }
        } while(true);
        if(crlBytes.isEmpty())
            return null;
        else
            return crlBytes;
    }

    public static void signExternalContainer(PdfSignatureAppearance sap, ExternalSignatureContainer externalSignatureContainer, int estimatedSize)
        throws GeneralSecurityException, IOException, DocumentException
    {
        PdfSignature dic = new PdfSignature(null, null);
        dic.setReason(sap.getReason());
        dic.setLocation(sap.getLocation());
        dic.setContact(sap.getContact());
        dic.setDate(new PdfDate(sap.getSignDate()));
        externalSignatureContainer.modifySigningDictionary(dic);
        sap.setCryptoDictionary(dic);
        HashMap exc = new HashMap();
        exc.put(PdfName.CONTENTS, new Integer(estimatedSize * 2 + 2));
        sap.preClose(exc);
        InputStream data = sap.getRangeStream();
        byte encodedSig[] = externalSignatureContainer.sign(data);
        if(estimatedSize < encodedSig.length)
        {
            throw new IOException("Not enough space");
        } else
        {
            byte paddedSig[] = new byte[estimatedSize];
            System.arraycopy(encodedSig, 0, paddedSig, 0, encodedSig.length);
            PdfDictionary dic2 = new PdfDictionary();
            dic2.put(PdfName.CONTENTS, (new PdfString(paddedSig)).setHexWriting(true));
            sap.close(dic2);
            return;
        }
    }

    public static void signDeferred(PdfReader reader, String fieldName, OutputStream outs, ExternalSignatureContainer externalSignatureContainer)
        throws DocumentException, IOException, GeneralSecurityException
    {
        AcroFields af = reader.getAcroFields();
        PdfDictionary v = af.getSignatureDictionary(fieldName);
        if(v == null)
            throw new DocumentException("No field");
        if(!af.signatureCoversWholeDocument(fieldName))
            throw new DocumentException("Not the last signature");
        PdfArray b = v.getAsArray(PdfName.BYTERANGE);
        long gaps[] = b.asLongArray();
        if(b.size() != 4 || gaps[0] != 0L)
            throw new DocumentException("Single exclusion space supported");
        RandomAccessSource readerSource = reader.getSafeFile().createSourceView();
        InputStream rg = new RASInputStream((new RandomAccessSourceFactory()).createRanged(readerSource, gaps));
        byte signedContent[] = externalSignatureContainer.sign(rg);
        int spaceAvailable = (int)(gaps[2] - gaps[1]) - 2;
        if((spaceAvailable & 1) != 0)
            throw new DocumentException("Gap is not a multiple of 2");
        spaceAvailable /= 2;
        if(spaceAvailable < signedContent.length)
            throw new DocumentException("Not enough space");
        StreamUtil.CopyBytes(readerSource, 0L, gaps[1] + 1L, outs);
        ByteBuffer bb = new ByteBuffer(spaceAvailable * 2);
        byte arr$[] = signedContent;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            byte bi = arr$[i$];
            bb.appendHex(bi);
        }

        int remain = (spaceAvailable - signedContent.length) * 2;
        for(int k = 0; k < remain; k++)
            bb.append((byte)48);

        bb.writeTo(outs);
        StreamUtil.CopyBytes(readerSource, gaps[2] - 1L, gaps[3] + 1L, outs);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/security/MakeSignature);

}
