// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LtvVerification.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.Utilities;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.*;
import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.ocsp.OCSPObjectIdentifiers;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            OcspClient, CrlClient, PdfPKCS7

public class LtvVerification
{
    private static class ValidationData
    {

        public List crls;
        public List ocsps;
        public List certs;

        private ValidationData()
        {
            crls = new ArrayList();
            ocsps = new ArrayList();
            certs = new ArrayList();
        }

    }

    public static final class CertificateInclusion extends Enum
    {

        public static CertificateInclusion[] values()
        {
            return (CertificateInclusion[])$VALUES.clone();
        }

        public static CertificateInclusion valueOf(String name)
        {
            return (CertificateInclusion)Enum.valueOf(co/com/pdf/text/pdf/security/LtvVerification$CertificateInclusion, name);
        }

        public static final CertificateInclusion YES;
        public static final CertificateInclusion NO;
        private static final CertificateInclusion $VALUES[];

        static 
        {
            YES = new CertificateInclusion("YES", 0);
            NO = new CertificateInclusion("NO", 1);
            $VALUES = (new CertificateInclusion[] {
                YES, NO
            });
        }

        private CertificateInclusion(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class CertificateOption extends Enum
    {

        public static CertificateOption[] values()
        {
            return (CertificateOption[])$VALUES.clone();
        }

        public static CertificateOption valueOf(String name)
        {
            return (CertificateOption)Enum.valueOf(co/com/pdf/text/pdf/security/LtvVerification$CertificateOption, name);
        }

        public static final CertificateOption SIGNING_CERTIFICATE;
        public static final CertificateOption WHOLE_CHAIN;
        private static final CertificateOption $VALUES[];

        static 
        {
            SIGNING_CERTIFICATE = new CertificateOption("SIGNING_CERTIFICATE", 0);
            WHOLE_CHAIN = new CertificateOption("WHOLE_CHAIN", 1);
            $VALUES = (new CertificateOption[] {
                SIGNING_CERTIFICATE, WHOLE_CHAIN
            });
        }

        private CertificateOption(String s, int i)
        {
            super(s, i);
        }
    }

    public static final class Level extends Enum
    {

        public static Level[] values()
        {
            return (Level[])$VALUES.clone();
        }

        public static Level valueOf(String name)
        {
            return (Level)Enum.valueOf(co/com/pdf/text/pdf/security/LtvVerification$Level, name);
        }

        public static final Level OCSP;
        public static final Level CRL;
        public static final Level OCSP_CRL;
        public static final Level OCSP_OPTIONAL_CRL;
        private static final Level $VALUES[];

        static 
        {
            OCSP = new Level("OCSP", 0);
            CRL = new Level("CRL", 1);
            OCSP_CRL = new Level("OCSP_CRL", 2);
            OCSP_OPTIONAL_CRL = new Level("OCSP_OPTIONAL_CRL", 3);
            $VALUES = (new Level[] {
                OCSP, CRL, OCSP_CRL, OCSP_OPTIONAL_CRL
            });
        }

        private Level(String s, int i)
        {
            super(s, i);
        }
    }


    public LtvVerification(PdfStamper stp)
    {
        LOGGER = LoggerFactory.getLogger(co/com/pdf/text/pdf/security/LtvVerification);
        validated = new HashMap();
        used = false;
        this.stp = stp;
        writer = stp.getWriter();
        reader = stp.getReader();
        acroFields = stp.getAcroFields();
    }

    public boolean addVerification(String signatureName, OcspClient ocsp, CrlClient crl, CertificateOption certOption, Level level, CertificateInclusion certInclude)
        throws IOException, GeneralSecurityException
    {
        if(used)
            throw new IllegalStateException(MessageLocalization.getComposedMessage("verification.already.output", new Object[0]));
        PdfPKCS7 pk = acroFields.verifySignature(signatureName);
        LOGGER.info((new StringBuilder()).append("Adding verification for ").append(signatureName).toString());
        Certificate xc[] = pk.getCertificates();
        X509Certificate signingCert = pk.getSigningCertificate();
        ValidationData vd = new ValidationData();
        for(int k = 0; k < xc.length; k++)
        {
            X509Certificate cert = (X509Certificate)xc[k];
            LOGGER.info((new StringBuilder()).append("Certificate: ").append(cert.getSubjectDN()).toString());
            if(certOption == CertificateOption.SIGNING_CERTIFICATE && !cert.equals(signingCert))
                continue;
            byte ocspEnc[] = null;
            if(ocsp != null && level != Level.CRL)
            {
                ocspEnc = ocsp.getEncoded(cert, getParent(cert, xc), null);
                if(ocspEnc != null)
                {
                    vd.ocsps.add(buildOCSPResponse(ocspEnc));
                    LOGGER.info("OCSP added");
                }
            }
            if(crl != null && (level == Level.CRL || level == Level.OCSP_CRL || level == Level.OCSP_OPTIONAL_CRL && ocspEnc == null))
            {
                Collection cims = crl.getEncoded(cert, null);
                if(cims != null)
                {
                    Iterator i$ = cims.iterator();
                    do
                    {
                        if(!i$.hasNext())
                            break;
                        byte cim[] = (byte[])i$.next();
                        boolean dup = false;
                        Iterator i$ = vd.crls.iterator();
                        do
                        {
                            if(!i$.hasNext())
                                break;
                            byte b[] = (byte[])i$.next();
                            if(!Arrays.equals(b, cim))
                                continue;
                            dup = true;
                            break;
                        } while(true);
                        if(!dup)
                        {
                            vd.crls.add(cim);
                            LOGGER.info("CRL added");
                        }
                    } while(true);
                }
            }
            if(certInclude == CertificateInclusion.YES)
                vd.certs.add(cert.getEncoded());
        }

        if(vd.crls.isEmpty() && vd.ocsps.isEmpty())
        {
            return false;
        } else
        {
            validated.put(getSignatureHashKey(signatureName), vd);
            return true;
        }
    }

    private X509Certificate getParent(X509Certificate cert, Certificate certs[])
    {
        for(int i = 0; i < certs.length;)
        {
            X509Certificate parent = (X509Certificate)certs[i];
            if(!cert.getIssuerDN().equals(parent.getSubjectDN()))
                continue;
            try
            {
                cert.verify(parent.getPublicKey());
                return parent;
            }
            catch(Exception e)
            {
                i++;
            }
        }

        return null;
    }

    public boolean addVerification(String signatureName, Collection ocsps, Collection crls, Collection certs)
        throws IOException, GeneralSecurityException
    {
        if(used)
            throw new IllegalStateException(MessageLocalization.getComposedMessage("verification.already.output", new Object[0]));
        ValidationData vd = new ValidationData();
        if(ocsps != null)
        {
            byte ocsp[];
            for(Iterator i$ = ocsps.iterator(); i$.hasNext(); vd.ocsps.add(buildOCSPResponse(ocsp)))
                ocsp = (byte[])i$.next();

        }
        if(crls != null)
        {
            byte crl[];
            for(Iterator i$ = crls.iterator(); i$.hasNext(); vd.crls.add(crl))
                crl = (byte[])i$.next();

        }
        if(certs != null)
        {
            byte cert[];
            for(Iterator i$ = certs.iterator(); i$.hasNext(); vd.certs.add(cert))
                cert = (byte[])i$.next();

        }
        validated.put(getSignatureHashKey(signatureName), vd);
        return true;
    }

    private static byte[] buildOCSPResponse(byte BasicOCSPResponse[])
        throws IOException
    {
        DEROctetString doctet = new DEROctetString(BasicOCSPResponse);
        ASN1EncodableVector v2 = new ASN1EncodableVector();
        v2.add(OCSPObjectIdentifiers.id_pkix_ocsp_basic);
        v2.add(doctet);
        ASN1Enumerated den = new ASN1Enumerated(0);
        ASN1EncodableVector v3 = new ASN1EncodableVector();
        v3.add(den);
        v3.add(new DERTaggedObject(true, 0, new DERSequence(v2)));
        DERSequence seq = new DERSequence(v3);
        return seq.getEncoded();
    }

    private PdfName getSignatureHashKey(String signatureName)
        throws NoSuchAlgorithmException, IOException
    {
        PdfDictionary dic = acroFields.getSignatureDictionary(signatureName);
        PdfString contents = dic.getAsString(PdfName.CONTENTS);
        byte bc[] = contents.getOriginalBytes();
        byte bt[] = null;
        if(PdfName.ETSI_RFC3161.equals(PdfReader.getPdfObject(dic.get(PdfName.SUBFILTER))))
        {
            ASN1InputStream din = new ASN1InputStream(new ByteArrayInputStream(bc));
            ASN1Primitive pkcs = din.readObject();
            bc = pkcs.getEncoded();
        }
        bt = hashBytesSha1(bc);
        return new PdfName(Utilities.convertToHex(bt));
    }

    private static byte[] hashBytesSha1(byte b[])
        throws NoSuchAlgorithmException
    {
        MessageDigest sh = MessageDigest.getInstance("SHA1");
        return sh.digest(b);
    }

    public void merge()
        throws IOException
    {
        if(used || validated.isEmpty())
            return;
        used = true;
        PdfDictionary catalog = reader.getCatalog();
        PdfObject dss = catalog.get(PdfName.DSS);
        if(dss == null)
            createDss();
        else
            updateDss();
    }

    private void updateDss()
        throws IOException
    {
        PdfDictionary catalog = reader.getCatalog();
        stp.markUsed(catalog);
        PdfDictionary dss = catalog.getAsDict(PdfName.DSS);
        PdfArray ocsps = dss.getAsArray(PdfName.OCSPS);
        PdfArray crls = dss.getAsArray(PdfName.CRLS);
        PdfArray certs = dss.getAsArray(PdfName.CERTS);
        dss.remove(PdfName.OCSPS);
        dss.remove(PdfName.CRLS);
        dss.remove(PdfName.CERTS);
        PdfDictionary vrim = dss.getAsDict(PdfName.VRI);
        if(vrim != null)
        {
            Iterator i$ = vrim.getKeys().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                PdfName n = (PdfName)i$.next();
                if(validated.containsKey(n))
                {
                    PdfDictionary vri = vrim.getAsDict(n);
                    if(vri != null)
                    {
                        deleteOldReferences(ocsps, vri.getAsArray(PdfName.OCSP));
                        deleteOldReferences(crls, vri.getAsArray(PdfName.CRL));
                        deleteOldReferences(certs, vri.getAsArray(PdfName.CERT));
                    }
                }
            } while(true);
        }
        if(ocsps == null)
            ocsps = new PdfArray();
        if(crls == null)
            crls = new PdfArray();
        if(certs == null)
            certs = new PdfArray();
        outputDss(dss, vrim, ocsps, crls, certs);
    }

    private static void deleteOldReferences(PdfArray all, PdfArray toDelete)
    {
        if(all == null || toDelete == null)
            return;
        Iterator i$ = toDelete.iterator();
        do
        {
            if(!i$.hasNext())
                break;
            PdfObject pi = (PdfObject)i$.next();
            if(pi.isIndirect())
            {
                PRIndirectReference pir = (PRIndirectReference)pi;
                int k = 0;
                while(k < all.size()) 
                {
                    PdfObject po = all.getPdfObject(k);
                    if(po.isIndirect())
                    {
                        PRIndirectReference pod = (PRIndirectReference)po;
                        if(pir.getNumber() == pod.getNumber())
                        {
                            all.remove(k);
                            k--;
                        }
                    }
                    k++;
                }
            }
        } while(true);
    }

    private void createDss()
        throws IOException
    {
        outputDss(new PdfDictionary(), new PdfDictionary(), new PdfArray(), new PdfArray(), new PdfArray());
    }

    private void outputDss(PdfDictionary dss, PdfDictionary vrim, PdfArray ocsps, PdfArray crls, PdfArray certs)
        throws IOException
    {
        writer.addDeveloperExtension(PdfDeveloperExtension.ESIC_1_7_EXTENSIONLEVEL5);
        PdfDictionary catalog = reader.getCatalog();
        stp.markUsed(catalog);
        PdfName vkey;
        PdfDictionary vri;
        for(Iterator i$ = validated.keySet().iterator(); i$.hasNext(); vrim.put(vkey, writer.addToBody(vri, false).getIndirectReference()))
        {
            vkey = (PdfName)i$.next();
            PdfArray ocsp = new PdfArray();
            PdfArray crl = new PdfArray();
            PdfArray cert = new PdfArray();
            vri = new PdfDictionary();
            co.com.pdf.text.pdf.PdfIndirectReference iref;
            for(Iterator i$ = ((ValidationData)validated.get(vkey)).crls.iterator(); i$.hasNext(); crls.add(iref))
            {
                byte b[] = (byte[])i$.next();
                PdfStream ps = new PdfStream(b);
                ps.flateCompress();
                iref = writer.addToBody(ps, false).getIndirectReference();
                crl.add(iref);
            }

            co.com.pdf.text.pdf.PdfIndirectReference iref;
            for(Iterator i$ = ((ValidationData)validated.get(vkey)).ocsps.iterator(); i$.hasNext(); ocsps.add(iref))
            {
                byte b[] = (byte[])i$.next();
                PdfStream ps = new PdfStream(b);
                ps.flateCompress();
                iref = writer.addToBody(ps, false).getIndirectReference();
                ocsp.add(iref);
            }

            co.com.pdf.text.pdf.PdfIndirectReference iref;
            for(Iterator i$ = ((ValidationData)validated.get(vkey)).certs.iterator(); i$.hasNext(); certs.add(iref))
            {
                byte b[] = (byte[])i$.next();
                PdfStream ps = new PdfStream(b);
                ps.flateCompress();
                iref = writer.addToBody(ps, false).getIndirectReference();
                cert.add(iref);
            }

            if(ocsp.size() > 0)
                vri.put(PdfName.OCSP, writer.addToBody(ocsp, false).getIndirectReference());
            if(crl.size() > 0)
                vri.put(PdfName.CRL, writer.addToBody(crl, false).getIndirectReference());
            if(cert.size() > 0)
                vri.put(PdfName.CERT, writer.addToBody(cert, false).getIndirectReference());
        }

        dss.put(PdfName.VRI, writer.addToBody(vrim, false).getIndirectReference());
        if(ocsps.size() > 0)
            dss.put(PdfName.OCSPS, writer.addToBody(ocsps, false).getIndirectReference());
        if(crls.size() > 0)
            dss.put(PdfName.CRLS, writer.addToBody(crls, false).getIndirectReference());
        if(certs.size() > 0)
            dss.put(PdfName.CERTS, writer.addToBody(certs, false).getIndirectReference());
        catalog.put(PdfName.DSS, writer.addToBody(dss, false).getIndirectReference());
    }

    private Logger LOGGER;
    private PdfStamper stp;
    private PdfWriter writer;
    private PdfReader reader;
    private AcroFields acroFields;
    private Map validated;
    private boolean used;
}
