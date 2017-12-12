// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPKCS7.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.PdfName;
import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.ess.*;
import co.org.bouncy.asn1.ocsp.BasicOCSPResponse;
import co.org.bouncy.asn1.ocsp.OCSPObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.tsp.MessageImprint;
import co.org.bouncy.asn1.tsp.TSTInfo;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.cert.jcajce.JcaX509CertificateHolder;
import co.org.bouncy.cert.ocsp.*;
import co.org.bouncy.jce.X509Principal;
import co.org.bouncy.jce.provider.X509CertParser;
import co.org.bouncy.operator.DigestCalculatorProvider;
import co.org.bouncy.operator.jcajce.JcaDigestCalculatorProviderBuilder;
import co.org.bouncy.tsp.TimeStampToken;
import co.org.bouncy.tsp.TimeStampTokenInfo;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.security:
//            BouncyCastleDigest, ExternalDigest, TSAClient, DigestAlgorithms, 
//            CertificateInfo, EncryptionAlgorithms, MakeSignature

public class PdfPKCS7
{

    public PdfPKCS7(PrivateKey privKey, Certificate certChain[], String hashAlgorithm, String provider, ExternalDigest interfaceDigest, boolean hasRSAdata)
        throws InvalidKeyException, NoSuchProviderException, NoSuchAlgorithmException
    {
        version = 1;
        signerversion = 1;
        this.provider = provider;
        this.interfaceDigest = interfaceDigest;
        digestAlgorithmOid = DigestAlgorithms.getAllowedDigests(hashAlgorithm);
        if(digestAlgorithmOid == null)
            throw new NoSuchAlgorithmException(MessageLocalization.getComposedMessage("unknown.hash.algorithm.1", new Object[] {
                hashAlgorithm
            }));
        signCert = (X509Certificate)certChain[0];
        certs = new ArrayList();
        Certificate arr$[] = certChain;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            Certificate element = arr$[i$];
            certs.add(element);
        }

        digestalgos = new HashSet();
        digestalgos.add(digestAlgorithmOid);
        if(privKey != null)
        {
            digestEncryptionAlgorithmOid = privKey.getAlgorithm();
            if(digestEncryptionAlgorithmOid.equals("RSA"))
                digestEncryptionAlgorithmOid = "1.2.840.113549.1.1.1";
            else
            if(digestEncryptionAlgorithmOid.equals("DSA"))
                digestEncryptionAlgorithmOid = "1.2.840.10040.4.1";
            else
                throw new NoSuchAlgorithmException(MessageLocalization.getComposedMessage("unknown.key.algorithm.1", new Object[] {
                    digestEncryptionAlgorithmOid
                }));
        }
        if(hasRSAdata)
        {
            RSAdata = new byte[0];
            messageDigest = DigestAlgorithms.getMessageDigest(getHashAlgorithm(), provider);
        }
        if(privKey != null)
            sig = initSignature(privKey);
    }

    public PdfPKCS7(byte contentsKey[], byte certsKey[], String provider)
    {
        version = 1;
        signerversion = 1;
        try
        {
            this.provider = provider;
            X509CertParser cr = new X509CertParser();
            cr.engineInit(new ByteArrayInputStream(certsKey));
            certs = cr.engineReadAll();
            signCerts = certs;
            signCert = (X509Certificate)certs.iterator().next();
            crls = new ArrayList();
            ASN1InputStream in = new ASN1InputStream(new ByteArrayInputStream(contentsKey));
            digest = ((ASN1OctetString)in.readObject()).getOctets();
            if(provider == null)
                sig = Signature.getInstance("SHA1withRSA");
            else
                sig = Signature.getInstance("SHA1withRSA", provider);
            sig.initVerify(signCert.getPublicKey());
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public PdfPKCS7(byte contentsKey[], PdfName filterSubtype, String provider)
    {
        version = 1;
        signerversion = 1;
        this.filterSubtype = filterSubtype;
        isTsp = PdfName.ETSI_RFC3161.equals(filterSubtype);
        isCades = PdfName.ETSI_CADES_DETACHED.equals(filterSubtype);
        try
        {
            this.provider = provider;
            ASN1InputStream din = new ASN1InputStream(new ByteArrayInputStream(contentsKey));
            ASN1Primitive pkcs;
            try
            {
                pkcs = din.readObject();
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("can.t.decode.pkcs7signeddata.object", new Object[0]));
            }
            if(!(pkcs instanceof ASN1Sequence))
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("not.a.valid.pkcs.7.object.not.a.sequence", new Object[0]));
            ASN1Sequence signedData = (ASN1Sequence)pkcs;
            ASN1ObjectIdentifier objId = (ASN1ObjectIdentifier)signedData.getObjectAt(0);
            if(!objId.getId().equals("1.2.840.113549.1.7.2"))
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("not.a.valid.pkcs.7.object.not.signed.data", new Object[0]));
            ASN1Sequence content = (ASN1Sequence)((ASN1TaggedObject)signedData.getObjectAt(1)).getObject();
            version = ((ASN1Integer)content.getObjectAt(0)).getValue().intValue();
            digestalgos = new HashSet();
            ASN1ObjectIdentifier o;
            for(Enumeration e = ((ASN1Set)content.getObjectAt(1)).getObjects(); e.hasMoreElements(); digestalgos.add(o.getId()))
            {
                ASN1Sequence s = (ASN1Sequence)e.nextElement();
                o = (ASN1ObjectIdentifier)s.getObjectAt(0);
            }

            ASN1Sequence rsaData = (ASN1Sequence)content.getObjectAt(2);
            if(rsaData.size() > 1)
            {
                ASN1OctetString rsaDataContent = (ASN1OctetString)((ASN1TaggedObject)rsaData.getObjectAt(1)).getObject();
                RSAdata = rsaDataContent.getOctets();
            }
            int next;
            for(next = 3; content.getObjectAt(next) instanceof ASN1TaggedObject; next++);
            X509CertParser cr = new X509CertParser();
            cr.engineInit(new ByteArrayInputStream(contentsKey));
            certs = cr.engineReadAll();
            ASN1Set signerInfos = (ASN1Set)content.getObjectAt(next);
            if(signerInfos.size() != 1)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("this.pkcs.7.object.has.multiple.signerinfos.only.one.is.supported.at.this.time", new Object[0]));
            ASN1Sequence signerInfo = (ASN1Sequence)signerInfos.getObjectAt(0);
            signerversion = ((ASN1Integer)signerInfo.getObjectAt(0)).getValue().intValue();
            ASN1Sequence issuerAndSerialNumber = (ASN1Sequence)signerInfo.getObjectAt(1);
            X509Principal issuer = new X509Principal(issuerAndSerialNumber.getObjectAt(0).toASN1Primitive().getEncoded());
            BigInteger serialNumber = ((ASN1Integer)issuerAndSerialNumber.getObjectAt(1)).getValue();
            Iterator i$ = certs.iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                Object element = i$.next();
                X509Certificate cert = (X509Certificate)element;
                if(!cert.getIssuerDN().equals(issuer) || !serialNumber.equals(cert.getSerialNumber()))
                    continue;
                signCert = cert;
                break;
            } while(true);
            if(signCert == null)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("can.t.find.signing.certificate.with.serial.1", new Object[] {
                    (new StringBuilder()).append(issuer.getName()).append(" / ").append(serialNumber.toString(16)).toString()
                }));
            signCertificateChain();
            digestAlgorithmOid = ((ASN1ObjectIdentifier)((ASN1Sequence)signerInfo.getObjectAt(2)).getObjectAt(0)).getId();
            next = 3;
            boolean foundCades = false;
            if(signerInfo.getObjectAt(next) instanceof ASN1TaggedObject)
            {
                ASN1TaggedObject tagsig = (ASN1TaggedObject)signerInfo.getObjectAt(next);
                ASN1Set sseq = ASN1Set.getInstance(tagsig, false);
                sigAttr = sseq.getEncoded();
                sigAttrDer = sseq.getEncoded("DER");
label0:
                for(int k = 0; k < sseq.size(); k++)
                {
                    ASN1Sequence seq2 = (ASN1Sequence)sseq.getObjectAt(k);
                    String idSeq2 = ((ASN1ObjectIdentifier)seq2.getObjectAt(0)).getId();
                    if(idSeq2.equals("1.2.840.113549.1.9.4"))
                    {
                        ASN1Set set = (ASN1Set)seq2.getObjectAt(1);
                        digestAttr = ((ASN1OctetString)set.getObjectAt(0)).getOctets();
                        continue;
                    }
                    ASN1Set setout;
                    ASN1Sequence seqout;
                    if(idSeq2.equals("1.2.840.113583.1.1.8"))
                    {
                        setout = (ASN1Set)seq2.getObjectAt(1);
                        seqout = (ASN1Sequence)setout.getObjectAt(0);
                        int j = 0;
                        do
                        {
                            if(j >= seqout.size())
                                continue label0;
                            ASN1TaggedObject tg = (ASN1TaggedObject)seqout.getObjectAt(j);
                            if(tg.getTagNo() == 0)
                            {
                                ASN1Sequence seqin = (ASN1Sequence)tg.getObject();
                                findCRL(seqin);
                            }
                            if(tg.getTagNo() == 1)
                            {
                                ASN1Sequence seqin = (ASN1Sequence)tg.getObject();
                                findOcsp(seqin);
                            }
                            j++;
                        } while(true);
                    }
                    SigningCertificateV2 sv2;
                    ESSCertIDv2 cerv2m[];
                    ESSCertIDv2 cerv2;
                    if(isCades && idSeq2.equals("1.2.840.113549.1.9.16.2.12"))
                    {
                        setout = (ASN1Set)seq2.getObjectAt(1);
                        seqout = (ASN1Sequence)setout.getObjectAt(0);
                        sv2 = SigningCertificate.getInstance(seqout);
                        cerv2m = sv2.getCerts();
                        cerv2 = cerv2m[0];
                        byte enc2[] = signCert.getEncoded();
                        MessageDigest m2 = (new BouncyCastleDigest()).getMessageDigest("SHA-1");
                        byte signCertHash[] = m2.digest(enc2);
                        byte hs2[] = cerv2.getCertHash();
                        if(!Arrays.equals(signCertHash, hs2))
                            throw new IllegalArgumentException("Signing certificate doesn't match the ESS information.");
                        foundCades = true;
                        continue;
                    }
                    if(!isCades || !idSeq2.equals("1.2.840.113549.1.9.16.2.47"))
                        continue;
                    setout = (ASN1Set)seq2.getObjectAt(1);
                    seqout = (ASN1Sequence)setout.getObjectAt(0);
                    sv2 = SigningCertificateV2.getInstance(seqout);
                    cerv2m = sv2.getCerts();
                    cerv2 = cerv2m[0];
                    AlgorithmIdentifier ai2 = cerv2.getHashAlgorithm();
                    byte enc2[] = signCert.getEncoded();
                    MessageDigest m2 = (new BouncyCastleDigest()).getMessageDigest(DigestAlgorithms.getDigest(ai2.getAlgorithm().getId()));
                    byte signCertHash[] = m2.digest(enc2);
                    byte hs2[] = cerv2.getCertHash();
                    if(!Arrays.equals(signCertHash, hs2))
                        throw new IllegalArgumentException("Signing certificate doesn't match the ESS information.");
                    foundCades = true;
                }

                if(digestAttr == null)
                    throw new IllegalArgumentException(MessageLocalization.getComposedMessage("authenticated.attribute.is.missing.the.digest", new Object[0]));
                next++;
            }
            if(isCades && !foundCades)
                throw new IllegalArgumentException("CAdES ESS information missing.");
            digestEncryptionAlgorithmOid = ((ASN1ObjectIdentifier)((ASN1Sequence)signerInfo.getObjectAt(next++)).getObjectAt(0)).getId();
            digest = ((ASN1OctetString)signerInfo.getObjectAt(next++)).getOctets();
            if(next < signerInfo.size() && (signerInfo.getObjectAt(next) instanceof ASN1TaggedObject))
            {
                ASN1TaggedObject taggedObject = (ASN1TaggedObject)signerInfo.getObjectAt(next);
                ASN1Set unat = ASN1Set.getInstance(taggedObject, false);
                AttributeTable attble = new AttributeTable(unat);
                Attribute ts = attble.get(PKCSObjectIdentifiers.id_aa_signatureTimeStampToken);
                if(ts != null && ts.getAttrValues().size() > 0)
                {
                    ASN1Set attributeValues = ts.getAttrValues();
                    ASN1Sequence tokenSequence = ASN1Sequence.getInstance(attributeValues.getObjectAt(0));
                    ContentInfo contentInfo = new ContentInfo(tokenSequence);
                    timeStampToken = new TimeStampToken(contentInfo);
                }
            }
            if(isTsp)
            {
                ContentInfo contentInfoTsp = new ContentInfo(signedData);
                timeStampToken = new TimeStampToken(contentInfoTsp);
                TimeStampTokenInfo info = timeStampToken.getTimeStampInfo();
                String algOID = info.getMessageImprintAlgOID().getId();
                messageDigest = DigestAlgorithms.getMessageDigestFromOid(algOID, null);
            } else
            {
                if(RSAdata != null || digestAttr != null)
                {
                    if(PdfName.ADBE_PKCS7_SHA1.equals(getFilterSubtype()))
                        messageDigest = DigestAlgorithms.getMessageDigest("SHA1", provider);
                    else
                        messageDigest = DigestAlgorithms.getMessageDigest(getHashAlgorithm(), provider);
                    encContDigest = DigestAlgorithms.getMessageDigest(getHashAlgorithm(), provider);
                }
                sig = initSignature(signCert.getPublicKey());
            }
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public String getSignName()
    {
        return signName;
    }

    public void setSignName(String signName)
    {
        this.signName = signName;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Calendar getSignDate()
    {
        Calendar dt = getTimeStampDate();
        if(dt == null)
            return signDate;
        else
            return dt;
    }

    public void setSignDate(Calendar signDate)
    {
        this.signDate = signDate;
    }

    public int getVersion()
    {
        return version;
    }

    public int getSigningInfoVersion()
    {
        return signerversion;
    }

    public String getDigestAlgorithmOid()
    {
        return digestAlgorithmOid;
    }

    public String getHashAlgorithm()
    {
        return DigestAlgorithms.getDigest(digestAlgorithmOid);
    }

    public String getDigestEncryptionAlgorithmOid()
    {
        return digestEncryptionAlgorithmOid;
    }

    public String getDigestAlgorithm()
    {
        return (new StringBuilder()).append(getHashAlgorithm()).append("with").append(getEncryptionAlgorithm()).toString();
    }

    public void setExternalDigest(byte digest[], byte RSAdata[], String digestEncryptionAlgorithm)
    {
        externalDigest = digest;
        externalRSAdata = RSAdata;
        if(digestEncryptionAlgorithm != null)
            if(digestEncryptionAlgorithm.equals("RSA"))
                digestEncryptionAlgorithmOid = "1.2.840.113549.1.1.1";
            else
            if(digestEncryptionAlgorithm.equals("DSA"))
                digestEncryptionAlgorithmOid = "1.2.840.10040.4.1";
            else
            if(digestEncryptionAlgorithm.equals("ECDSA"))
                digestEncryptionAlgorithmOid = "1.2.840.10045.2.1";
            else
                throw new ExceptionConverter(new NoSuchAlgorithmException(MessageLocalization.getComposedMessage("unknown.key.algorithm.1", new Object[] {
                    digestEncryptionAlgorithm
                })));
    }

    private Signature initSignature(PrivateKey key)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        Signature signature;
        if(provider == null)
            signature = Signature.getInstance(getDigestAlgorithm());
        else
            signature = Signature.getInstance(getDigestAlgorithm(), provider);
        signature.initSign(key);
        return signature;
    }

    private Signature initSignature(PublicKey key)
        throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException
    {
        String digestAlgorithm = getDigestAlgorithm();
        if(PdfName.ADBE_X509_RSA_SHA1.equals(getFilterSubtype()))
            digestAlgorithm = "SHA1withRSA";
        Signature signature;
        if(provider == null)
            signature = Signature.getInstance(digestAlgorithm);
        else
            signature = Signature.getInstance(digestAlgorithm, provider);
        signature.initVerify(key);
        return signature;
    }

    public void update(byte buf[], int off, int len)
        throws SignatureException
    {
        if(RSAdata != null || digestAttr != null || isTsp)
            messageDigest.update(buf, off, len);
        else
            sig.update(buf, off, len);
    }

    public byte[] getEncodedPKCS1()
    {
        try
        {
            if(externalDigest != null)
                digest = externalDigest;
            else
                digest = sig.sign();
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ASN1OutputStream dout = new ASN1OutputStream(bOut);
            dout.writeObject(new DEROctetString(digest));
            dout.close();
            return bOut.toByteArray();
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public byte[] getEncodedPKCS7()
    {
        return getEncodedPKCS7(null, null, null, null, null, MakeSignature.CryptoStandard.CMS);
    }

    public byte[] getEncodedPKCS7(byte secondDigest[], Calendar signingTime)
    {
        return getEncodedPKCS7(secondDigest, signingTime, null, null, null, MakeSignature.CryptoStandard.CMS);
    }

    public byte[] getEncodedPKCS7(byte secondDigest[], Calendar signingTime, TSAClient tsaClient, byte ocsp[], Collection crlBytes, MakeSignature.CryptoStandard sigtype)
    {
        try
        {
            if(externalDigest != null)
            {
                digest = externalDigest;
                if(RSAdata != null)
                    RSAdata = externalRSAdata;
            } else
            if(externalRSAdata != null && RSAdata != null)
            {
                RSAdata = externalRSAdata;
                sig.update(RSAdata);
                digest = sig.sign();
            } else
            {
                if(RSAdata != null)
                {
                    RSAdata = messageDigest.digest();
                    sig.update(RSAdata);
                }
                digest = sig.sign();
            }
            ASN1EncodableVector digestAlgorithms = new ASN1EncodableVector();
            ASN1EncodableVector algos;
            for(Iterator i$ = digestalgos.iterator(); i$.hasNext(); digestAlgorithms.add(new DERSequence(algos)))
            {
                Object element = i$.next();
                algos = new ASN1EncodableVector();
                algos.add(new ASN1ObjectIdentifier((String)element));
                algos.add(DERNull.INSTANCE);
            }

            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(new ASN1ObjectIdentifier("1.2.840.113549.1.7.1"));
            if(RSAdata != null)
                v.add(new DERTaggedObject(0, new DEROctetString(RSAdata)));
            DERSequence contentinfo = new DERSequence(v);
            v = new ASN1EncodableVector();
            ASN1InputStream tempstream;
            for(Iterator i$ = certs.iterator(); i$.hasNext(); v.add(tempstream.readObject()))
            {
                Object element = i$.next();
                tempstream = new ASN1InputStream(new ByteArrayInputStream(((X509Certificate)element).getEncoded()));
            }

            DERSet dercertificates = new DERSet(v);
            ASN1EncodableVector signerinfo = new ASN1EncodableVector();
            signerinfo.add(new ASN1Integer(signerversion));
            v = new ASN1EncodableVector();
            v.add(CertificateInfo.getIssuer(signCert.getTBSCertificate()));
            v.add(new ASN1Integer(signCert.getSerialNumber()));
            signerinfo.add(new DERSequence(v));
            v = new ASN1EncodableVector();
            v.add(new ASN1ObjectIdentifier(digestAlgorithmOid));
            v.add(new DERNull());
            signerinfo.add(new DERSequence(v));
            if(secondDigest != null && signingTime != null)
                signerinfo.add(new DERTaggedObject(false, 0, getAuthenticatedAttributeSet(secondDigest, signingTime, ocsp, crlBytes, sigtype)));
            v = new ASN1EncodableVector();
            v.add(new ASN1ObjectIdentifier(digestEncryptionAlgorithmOid));
            v.add(new DERNull());
            signerinfo.add(new DERSequence(v));
            signerinfo.add(new DEROctetString(digest));
            if(tsaClient != null)
            {
                byte tsImprint[] = tsaClient.getMessageDigest().digest(digest);
                byte tsToken[] = tsaClient.getTimeStampToken(tsImprint);
                if(tsToken != null)
                {
                    ASN1EncodableVector unauthAttributes = buildUnauthenticatedAttributes(tsToken);
                    if(unauthAttributes != null)
                        signerinfo.add(new DERTaggedObject(false, 1, new DERSet(unauthAttributes)));
                }
            }
            ASN1EncodableVector body = new ASN1EncodableVector();
            body.add(new ASN1Integer(version));
            body.add(new DERSet(digestAlgorithms));
            body.add(contentinfo);
            body.add(new DERTaggedObject(false, 0, dercertificates));
            body.add(new DERSet(new DERSequence(signerinfo)));
            ASN1EncodableVector whole = new ASN1EncodableVector();
            whole.add(new ASN1ObjectIdentifier("1.2.840.113549.1.7.2"));
            whole.add(new DERTaggedObject(0, new DERSequence(body)));
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ASN1OutputStream dout = new ASN1OutputStream(bOut);
            dout.writeObject(new DERSequence(whole));
            dout.close();
            return bOut.toByteArray();
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    private ASN1EncodableVector buildUnauthenticatedAttributes(byte timeStampToken[])
        throws IOException
    {
        if(timeStampToken == null)
        {
            return null;
        } else
        {
            String ID_TIME_STAMP_TOKEN = "1.2.840.113549.1.9.16.2.14";
            ASN1InputStream tempstream = new ASN1InputStream(new ByteArrayInputStream(timeStampToken));
            ASN1EncodableVector unauthAttributes = new ASN1EncodableVector();
            ASN1EncodableVector v = new ASN1EncodableVector();
            v.add(new ASN1ObjectIdentifier(ID_TIME_STAMP_TOKEN));
            ASN1Sequence seq = (ASN1Sequence)tempstream.readObject();
            v.add(new DERSet(seq));
            unauthAttributes.add(new DERSequence(v));
            return unauthAttributes;
        }
    }

    public byte[] getAuthenticatedAttributeBytes(byte secondDigest[], Calendar signingTime, byte ocsp[], Collection crlBytes, MakeSignature.CryptoStandard sigtype)
    {
        try
        {
            return getAuthenticatedAttributeSet(secondDigest, signingTime, ocsp, crlBytes, sigtype).getEncoded("DER");
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    private DERSet getAuthenticatedAttributeSet(byte secondDigest[], Calendar signingTime, byte ocsp[], Collection crlBytes, MakeSignature.CryptoStandard sigtype)
    {
        try
        {
            ASN1EncodableVector attribute;
            boolean haveCrl;
label0:
            {
                attribute = new ASN1EncodableVector();
                ASN1EncodableVector v = new ASN1EncodableVector();
                v.add(new ASN1ObjectIdentifier("1.2.840.113549.1.9.3"));
                v.add(new DERSet(new ASN1ObjectIdentifier("1.2.840.113549.1.7.1")));
                attribute.add(new DERSequence(v));
                v = new ASN1EncodableVector();
                v.add(new ASN1ObjectIdentifier("1.2.840.113549.1.9.5"));
                v.add(new DERSet(new DERUTCTime(signingTime.getTime())));
                attribute.add(new DERSequence(v));
                v = new ASN1EncodableVector();
                v.add(new ASN1ObjectIdentifier("1.2.840.113549.1.9.4"));
                v.add(new DERSet(new DEROctetString(secondDigest)));
                attribute.add(new DERSequence(v));
                haveCrl = false;
                if(crlBytes == null)
                    break label0;
                Iterator i$ = crlBytes.iterator();
                byte bCrl[];
                do
                {
                    if(!i$.hasNext())
                        break label0;
                    bCrl = (byte[])i$.next();
                } while(bCrl == null);
                haveCrl = true;
            }
            if(ocsp != null || haveCrl)
            {
                ASN1EncodableVector v = new ASN1EncodableVector();
                v.add(new ASN1ObjectIdentifier("1.2.840.113583.1.1.8"));
                ASN1EncodableVector revocationV = new ASN1EncodableVector();
                if(haveCrl)
                {
                    ASN1EncodableVector v2 = new ASN1EncodableVector();
                    Iterator i$ = crlBytes.iterator();
                    do
                    {
                        if(!i$.hasNext())
                            break;
                        byte bCrl[] = (byte[])i$.next();
                        if(bCrl != null)
                        {
                            ASN1InputStream t = new ASN1InputStream(new ByteArrayInputStream(bCrl));
                            v2.add(t.readObject());
                        }
                    } while(true);
                    revocationV.add(new DERTaggedObject(true, 0, new DERSequence(v2)));
                }
                if(ocsp != null)
                {
                    DEROctetString doctet = new DEROctetString(ocsp);
                    ASN1EncodableVector vo1 = new ASN1EncodableVector();
                    ASN1EncodableVector v2 = new ASN1EncodableVector();
                    v2.add(OCSPObjectIdentifiers.id_pkix_ocsp_basic);
                    v2.add(doctet);
                    ASN1Enumerated den = new ASN1Enumerated(0);
                    ASN1EncodableVector v3 = new ASN1EncodableVector();
                    v3.add(den);
                    v3.add(new DERTaggedObject(true, 0, new DERSequence(v2)));
                    vo1.add(new DERSequence(v3));
                    revocationV.add(new DERTaggedObject(true, 1, new DERSequence(vo1)));
                }
                v.add(new DERSet(new DERSequence(revocationV)));
                attribute.add(new DERSequence(v));
            }
            if(sigtype == MakeSignature.CryptoStandard.CADES)
            {
                ASN1EncodableVector v = new ASN1EncodableVector();
                v.add(new ASN1ObjectIdentifier("1.2.840.113549.1.9.16.2.47"));
                ASN1EncodableVector aaV2 = new ASN1EncodableVector();
                AlgorithmIdentifier algoId = new AlgorithmIdentifier(new ASN1ObjectIdentifier(digestAlgorithmOid), null);
                aaV2.add(algoId);
                MessageDigest md = interfaceDigest.getMessageDigest(getHashAlgorithm());
                byte dig[] = md.digest(signCert.getEncoded());
                aaV2.add(new DEROctetString(dig));
                v.add(new DERSet(new DERSequence(new DERSequence(new DERSequence(aaV2)))));
                attribute.add(new DERSequence(v));
            }
            return new DERSet(attribute);
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public boolean verify()
        throws GeneralSecurityException
    {
        if(verified)
            return verifyResult;
        if(isTsp)
        {
            TimeStampTokenInfo info = timeStampToken.getTimeStampInfo();
            MessageImprint imprint = info.toASN1Structure().getMessageImprint();
            byte md[] = messageDigest.digest();
            byte imphashed[] = imprint.getHashedMessage();
            verifyResult = Arrays.equals(md, imphashed);
        } else
        if(sigAttr != null || sigAttrDer != null)
        {
            byte msgDigestBytes[] = messageDigest.digest();
            boolean verifyRSAdata = true;
            boolean encContDigestCompare = false;
            if(RSAdata != null)
            {
                verifyRSAdata = Arrays.equals(msgDigestBytes, RSAdata);
                encContDigest.update(RSAdata);
                encContDigestCompare = Arrays.equals(encContDigest.digest(), digestAttr);
            }
            boolean absentEncContDigestCompare = Arrays.equals(msgDigestBytes, digestAttr);
            boolean concludingDigestCompare = absentEncContDigestCompare || encContDigestCompare;
            boolean sigVerify = verifySigAttributes(sigAttr) || verifySigAttributes(sigAttrDer);
            verifyResult = concludingDigestCompare && sigVerify && verifyRSAdata;
        } else
        {
            if(RSAdata != null)
                sig.update(messageDigest.digest());
            verifyResult = sig.verify(digest);
        }
        verified = true;
        return verifyResult;
    }

    private boolean verifySigAttributes(byte attr[])
        throws GeneralSecurityException
    {
        Signature signature = initSignature(signCert.getPublicKey());
        signature.update(attr);
        return signature.verify(digest);
    }

    public boolean verifyTimestampImprint()
        throws GeneralSecurityException
    {
        if(timeStampToken == null)
        {
            return false;
        } else
        {
            TimeStampTokenInfo info = timeStampToken.getTimeStampInfo();
            MessageImprint imprint = info.toASN1Structure().getMessageImprint();
            String algOID = info.getMessageImprintAlgOID().getId();
            byte md[] = (new BouncyCastleDigest()).getMessageDigest(DigestAlgorithms.getDigest(algOID)).digest(digest);
            byte imphashed[] = imprint.getHashedMessage();
            boolean res = Arrays.equals(md, imphashed);
            return res;
        }
    }

    public Certificate[] getCertificates()
    {
        return (Certificate[])certs.toArray(new X509Certificate[certs.size()]);
    }

    public Certificate[] getSignCertificateChain()
    {
        return (Certificate[])signCerts.toArray(new X509Certificate[signCerts.size()]);
    }

    public X509Certificate getSigningCertificate()
    {
        return signCert;
    }

    private void signCertificateChain()
    {
        ArrayList cc = new ArrayList();
        cc.add(signCert);
        ArrayList oc = new ArrayList(certs);
        for(int k = 0; k < oc.size(); k++)
            if(signCert.equals(oc.get(k)))
            {
                oc.remove(k);
                k--;
            }

        boolean found = true;
label0:
        do
        {
            if(found)
            {
                X509Certificate v = (X509Certificate)cc.get(cc.size() - 1);
                found = false;
                int k = 0;
                do
                {
                    if(k >= oc.size())
                        continue label0;
                    X509Certificate issuer = (X509Certificate)oc.get(k);
                    try
                    {
                        if(provider == null)
                            v.verify(issuer.getPublicKey());
                        else
                            v.verify(issuer.getPublicKey(), provider);
                        found = true;
                        cc.add(oc.get(k));
                        oc.remove(k);
                        continue label0;
                    }
                    catch(Exception e)
                    {
                        k++;
                    }
                } while(true);
            }
            signCerts = cc;
            return;
        } while(true);
    }

    public Collection getCRLs()
    {
        return crls;
    }

    private void findCRL(ASN1Sequence seq)
    {
        try
        {
            crls = new ArrayList();
            for(int k = 0; k < seq.size(); k++)
            {
                ByteArrayInputStream ar = new ByteArrayInputStream(seq.getObjectAt(k).toASN1Primitive().getEncoded("DER"));
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                X509CRL crl = (X509CRL)cf.generateCRL(ar);
                crls.add(crl);
            }

        }
        catch(Exception ex) { }
    }

    public BasicOCSPResp getOcsp()
    {
        return basicResp;
    }

    public boolean isRevocationValid()
    {
        if(basicResp == null)
            return false;
        if(signCerts.size() < 2)
            return false;
        try
        {
            X509Certificate cs[] = (X509Certificate[])(X509Certificate[])getSignCertificateChain();
            SingleResp sr = basicResp.getResponses()[0];
            CertificateID cid = sr.getCertID();
            co.org.bouncy.operator.DigestCalculator digestalg = (new JcaDigestCalculatorProviderBuilder()).build().get(new AlgorithmIdentifier(cid.getHashAlgOID(), DERNull.INSTANCE));
            X509Certificate sigcer = getSigningCertificate();
            X509Certificate isscer = cs[1];
            CertificateID tis = new CertificateID(digestalg, new JcaX509CertificateHolder(isscer), sigcer.getSerialNumber());
            return tis.equals(cid);
        }
        catch(Exception ex)
        {
            return false;
        }
    }

    private void findOcsp(ASN1Sequence seq)
        throws IOException
    {
        basicResp = null;
        boolean ret = false;
        while(!(seq.getObjectAt(0) instanceof ASN1ObjectIdentifier) || !((ASN1ObjectIdentifier)seq.getObjectAt(0)).getId().equals(OCSPObjectIdentifiers.id_pkix_ocsp_basic.getId())) 
        {
            ret = true;
            int k = 0;
            do
            {
                if(k >= seq.size())
                    break;
                if(seq.getObjectAt(k) instanceof ASN1Sequence)
                {
                    seq = (ASN1Sequence)seq.getObjectAt(0);
                    ret = false;
                    break;
                }
                if(seq.getObjectAt(k) instanceof ASN1TaggedObject)
                {
                    ASN1TaggedObject tag = (ASN1TaggedObject)seq.getObjectAt(k);
                    if(tag.getObject() instanceof ASN1Sequence)
                    {
                        seq = (ASN1Sequence)tag.getObject();
                        ret = false;
                    } else
                    {
                        return;
                    }
                    break;
                }
                k++;
            } while(true);
            if(ret)
                return;
        }
        ASN1OctetString os = (ASN1OctetString)seq.getObjectAt(1);
        ASN1InputStream inp = new ASN1InputStream(os.getOctets());
        BasicOCSPResponse resp = BasicOCSPResponse.getInstance(inp.readObject());
        basicResp = new BasicOCSPResp(resp);
    }

    public boolean isTsp()
    {
        return isTsp;
    }

    public TimeStampToken getTimeStampToken()
    {
        return timeStampToken;
    }

    public Calendar getTimeStampDate()
    {
        if(timeStampToken == null)
        {
            return null;
        } else
        {
            Calendar cal = new GregorianCalendar();
            java.util.Date date = timeStampToken.getTimeStampInfo().getGenTime();
            cal.setTime(date);
            return cal;
        }
    }

    public PdfName getFilterSubtype()
    {
        return filterSubtype;
    }

    public String getEncryptionAlgorithm()
    {
        String encryptAlgo = EncryptionAlgorithms.getAlgorithm(digestEncryptionAlgorithmOid);
        if(encryptAlgo == null)
            encryptAlgo = digestEncryptionAlgorithmOid;
        return encryptAlgo;
    }

    private String provider;
    private String signName;
    private String reason;
    private String location;
    private Calendar signDate;
    private int version;
    private int signerversion;
    private String digestAlgorithmOid;
    private MessageDigest messageDigest;
    private Set digestalgos;
    private byte digestAttr[];
    private PdfName filterSubtype;
    private String digestEncryptionAlgorithmOid;
    private ExternalDigest interfaceDigest;
    private byte externalDigest[];
    private byte externalRSAdata[];
    private Signature sig;
    private byte digest[];
    private byte RSAdata[];
    private byte sigAttr[];
    private byte sigAttrDer[];
    private MessageDigest encContDigest;
    private boolean verified;
    private boolean verifyResult;
    private Collection certs;
    private Collection signCerts;
    private X509Certificate signCert;
    private Collection crls;
    private BasicOCSPResp basicResp;
    private boolean isTsp;
    private boolean isCades;
    private TimeStampToken timeStampToken;
}
