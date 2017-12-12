// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignedMailValidator.java

package co.org.bouncy.mail.smime.validator;

import co.org.bouncy.asn1.*;
import co.org.bouncy.asn1.cms.*;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.x509.*;
import co.org.bouncy.cms.SignerInformation;
import co.org.bouncy.cms.SignerInformationStore;
import co.org.bouncy.cms.jcajce.JcaX509CertSelectorConverter;
import co.org.bouncy.i18n.ErrorBundle;
import co.org.bouncy.i18n.filter.TrustedInput;
import co.org.bouncy.i18n.filter.UntrustedInput;
import co.org.bouncy.jce.PrincipalUtil;
import co.org.bouncy.jce.X509Principal;
import co.org.bouncy.mail.smime.SMIMESigned;
import co.org.bouncy.util.Integers;
import co.org.bouncy.x509_.CertPathReviewerException;
import co.org.bouncy.x509_.PKIXCertPathReviewer;
import java.io.IOException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PublicKey;
import java.security.cert.*;
import java.security.interfaces.*;
import java.util.*;
import javax.mail.MessagingException;
import javax.mail.internet.*;
import javax.security.auth.x500.X500Principal;

// Referenced classes of package co.org.bouncy.mail.smime.validator:
//            SignedMailValidatorException

public class SignedMailValidator
{
    public class ValidationResult
    {

        public List getErrors()
        {
            return errors;
        }

        public List getNotifications()
        {
            return notifications;
        }

        public PKIXCertPathReviewer getCertPathReview()
        {
            return review;
        }

        public CertPath getCertPath()
        {
            return review == null ? null : review.getCertPath();
        }

        public List getUserProvidedCerts()
        {
            return userProvidedCerts;
        }

        public boolean isVerifiedSignature()
        {
            return signVerified;
        }

        public boolean isValidSignature()
        {
            if(review != null)
                return signVerified && review.isValidCertPath() && errors.isEmpty();
            else
                return false;
        }

        private PKIXCertPathReviewer review;
        private List errors;
        private List notifications;
        private List userProvidedCerts;
        private boolean signVerified;
        final SignedMailValidator this$0;

        ValidationResult(PKIXCertPathReviewer review, boolean verified, List errors, List notifications, List userProvidedCerts)
        {
            this$0 = SignedMailValidator.this;
            super();
            this.review = review;
            this.errors = errors;
            this.notifications = notifications;
            signVerified = verified;
            this.userProvidedCerts = userProvidedCerts;
        }
    }


    public SignedMailValidator(MimeMessage message, PKIXParameters param)
        throws SignedMailValidatorException
    {
        this(message, param, DEFAULT_CERT_PATH_REVIEWER);
    }

    public SignedMailValidator(MimeMessage message, PKIXParameters param, Class certPathReviewerClass)
        throws SignedMailValidatorException
    {
        this.certPathReviewerClass = certPathReviewerClass;
        boolean isSubclass = DEFAULT_CERT_PATH_REVIEWER.isAssignableFrom(certPathReviewerClass);
        if(!isSubclass)
            throw new IllegalArgumentException((new StringBuilder()).append("certPathReviewerClass is not a subclass of ").append(DEFAULT_CERT_PATH_REVIEWER.getName()).toString());
        try
        {
            SMIMESigned s;
            if(message.isMimeType("multipart/signed"))
            {
                MimeMultipart mimemp = (MimeMultipart)message.getContent();
                s = new SMIMESigned(mimemp);
            } else
            if(message.isMimeType("application/pkcs7-mime") || message.isMimeType("application/x-pkcs7-mime"))
            {
                s = new SMIMESigned(message);
            } else
            {
                ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.noSignedMessage");
                throw new SignedMailValidatorException(msg);
            }
            certs = s.getCertificatesAndCRLs("Collection", "BC");
            signers = s.getSignerInfos();
            javax.mail.Address froms[] = message.getFrom();
            InternetAddress sender = null;
            try
            {
                if(message.getHeader("Sender") != null)
                    sender = new InternetAddress(message.getHeader("Sender")[0]);
            }
            catch(MessagingException ex) { }
            fromAddresses = new String[froms.length + (sender == null ? 0 : 1)];
            for(int i = 0; i < froms.length; i++)
            {
                InternetAddress inetAddr = (InternetAddress)froms[i];
                fromAddresses[i] = inetAddr.getAddress();
            }

            if(sender != null)
                fromAddresses[froms.length] = sender.getAddress();
            results = new HashMap();
        }
        catch(Exception e)
        {
            if(e instanceof SignedMailValidatorException)
            {
                throw (SignedMailValidatorException)e;
            } else
            {
                ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.exceptionReadingMessage", new Object[] {
                    e.getMessage(), e, e.getClass().getName()
                });
                throw new SignedMailValidatorException(msg, e);
            }
        }
        validateSignatures(param);
    }

    protected void validateSignatures(PKIXParameters pkixParam)
    {
        PKIXParameters usedParameters = (PKIXParameters)pkixParam.clone();
        usedParameters.addCertStore(certs);
        Collection c = signers.getSigners();
        for(Iterator it = c.iterator(); it.hasNext();)
        {
            List errors = new ArrayList();
            List notifications = new ArrayList();
            SignerInformation signer = (SignerInformation)it.next();
            X509Certificate cert = null;
            try
            {
                Collection certCollection = findCerts(usedParameters.getCertStores(), selectorConverter.getCertSelector(signer.getSID()));
                Iterator certIt = certCollection.iterator();
                if(certIt.hasNext())
                    cert = (X509Certificate)certIt.next();
            }
            catch(CertStoreException cse)
            {
                ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.exceptionRetrievingSignerCert", new Object[] {
                    cse.getMessage(), cse, cse.getClass().getName()
                });
                errors.add(msg);
            }
            if(cert != null)
            {
                boolean validSignature = false;
                try
                {
                    validSignature = signer.verify(cert.getPublicKey(), "BC");
                    if(!validSignature)
                    {
                        ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.signatureNotVerified");
                        errors.add(msg);
                    }
                }
                catch(Exception e)
                {
                    ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.exceptionVerifyingSignature", new Object[] {
                        e.getMessage(), e, e.getClass().getName()
                    });
                    errors.add(msg);
                }
                checkSignerCert(cert, errors, notifications);
                AttributeTable atab = signer.getSignedAttributes();
                if(atab != null)
                {
                    Attribute attr = atab.get(PKCSObjectIdentifiers.id_aa_receiptRequest);
                    if(attr != null)
                    {
                        ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.signedReceiptRequest");
                        notifications.add(msg);
                    }
                }
                Date signTime = getSignatureTime(signer);
                if(signTime == null)
                {
                    ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.noSigningTime");
                    errors.add(msg);
                    signTime = new Date();
                } else
                {
                    try
                    {
                        cert.checkValidity(signTime);
                    }
                    catch(CertificateExpiredException e)
                    {
                        ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.certExpired", new Object[] {
                            new TrustedInput(signTime), new TrustedInput(cert.getNotAfter())
                        });
                        errors.add(msg);
                    }
                    catch(CertificateNotYetValidException e)
                    {
                        ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.certNotYetValid", new Object[] {
                            new TrustedInput(signTime), new TrustedInput(cert.getNotBefore())
                        });
                        errors.add(msg);
                    }
                }
                usedParameters.setDate(signTime);
                try
                {
                    List userCertStores = new ArrayList();
                    userCertStores.add(certs);
                    Object cpres[] = createCertPath(cert, usedParameters.getTrustAnchors(), pkixParam.getCertStores(), userCertStores);
                    CertPath certPath = (CertPath)cpres[0];
                    List userProvidedList = (List)cpres[1];
                    PKIXCertPathReviewer review;
                    try
                    {
                        review = (PKIXCertPathReviewer)certPathReviewerClass.newInstance();
                    }
                    catch(IllegalAccessException e)
                    {
                        throw new IllegalArgumentException((new StringBuilder()).append("Cannot instantiate object of type ").append(certPathReviewerClass.getName()).append(": ").append(e.getMessage()).toString());
                    }
                    catch(InstantiationException e)
                    {
                        throw new IllegalArgumentException((new StringBuilder()).append("Cannot instantiate object of type ").append(certPathReviewerClass.getName()).append(": ").append(e.getMessage()).toString());
                    }
                    review.init(certPath, usedParameters);
                    if(!review.isValidCertPath())
                    {
                        ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.certPathInvalid");
                        errors.add(msg);
                    }
                    results.put(signer, new ValidationResult(review, validSignature, errors, notifications, userProvidedList));
                }
                catch(GeneralSecurityException gse)
                {
                    ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.exceptionCreateCertPath", new Object[] {
                        gse.getMessage(), gse, gse.getClass().getName()
                    });
                    errors.add(msg);
                    results.put(signer, new ValidationResult(null, validSignature, errors, notifications, null));
                }
                catch(CertPathReviewerException cpre)
                {
                    errors.add(cpre.getErrorMessage());
                    results.put(signer, new ValidationResult(null, validSignature, errors, notifications, null));
                }
            } else
            {
                ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.noSignerCert");
                errors.add(msg);
                results.put(signer, new ValidationResult(null, false, errors, notifications, null));
            }
        }

    }

    public static Set getEmailAddresses(X509Certificate cert)
        throws IOException, CertificateEncodingException
    {
        Set addresses = new HashSet();
        X509Principal name = PrincipalUtil.getSubjectX509Principal(cert);
        Vector oids = name.getOIDs();
        Vector names = name.getValues();
        int i = 0;
        do
        {
            if(i >= oids.size())
                break;
            if(oids.get(i).equals(X509Principal.EmailAddress))
            {
                String email = ((String)names.get(i)).toLowerCase();
                addresses.add(email);
                break;
            }
            i++;
        } while(true);
        byte ext[] = cert.getExtensionValue(SUBJECT_ALTERNATIVE_NAME);
        if(ext != null)
        {
            ASN1Sequence altNames = ASN1Sequence.getInstance(getObject(ext));
            for(int j = 0; j < altNames.size(); j++)
            {
                ASN1TaggedObject o = (ASN1TaggedObject)altNames.getObjectAt(j);
                if(o.getTagNo() == 1)
                {
                    String email = DERIA5String.getInstance(o, false).getString().toLowerCase();
                    addresses.add(email);
                }
            }

        }
        return addresses;
    }

    private static ASN1Primitive getObject(byte ext[])
        throws IOException
    {
        ASN1InputStream aIn = new ASN1InputStream(ext);
        ASN1OctetString octs = (ASN1OctetString)aIn.readObject();
        aIn = new ASN1InputStream(octs.getOctets());
        return aIn.readObject();
    }

    protected void checkSignerCert(X509Certificate cert, List errors, List notifications)
    {
        PublicKey key = cert.getPublicKey();
        int keyLenght = -1;
        if(key instanceof RSAPublicKey)
            keyLenght = ((RSAPublicKey)key).getModulus().bitLength();
        else
        if(key instanceof DSAPublicKey)
            keyLenght = ((DSAPublicKey)key).getParams().getP().bitLength();
        if(keyLenght != -1 && keyLenght <= 512)
        {
            ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.shortSigningKey", new Object[] {
                Integers.valueOf(keyLenght)
            });
            notifications.add(msg);
        }
        long validityPeriod = cert.getNotAfter().getTime() - cert.getNotBefore().getTime();
        if(validityPeriod > 0xdc6d62da00L)
        {
            ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.longValidity", new Object[] {
                new TrustedInput(cert.getNotBefore()), new TrustedInput(cert.getNotAfter())
            });
            notifications.add(msg);
        }
        boolean keyUsage[] = cert.getKeyUsage();
        if(keyUsage != null && !keyUsage[0] && !keyUsage[1])
        {
            ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.signingNotPermitted");
            errors.add(msg);
        }
        try
        {
            byte ext[] = cert.getExtensionValue(EXT_KEY_USAGE);
            if(ext != null)
            {
                ExtendedKeyUsage extKeyUsage = ExtendedKeyUsage.getInstance(getObject(ext));
                if(!extKeyUsage.hasKeyPurposeId(KeyPurposeId.anyExtendedKeyUsage) && !extKeyUsage.hasKeyPurposeId(KeyPurposeId.id_kp_emailProtection))
                {
                    ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.extKeyUsageNotPermitted");
                    errors.add(msg);
                }
            }
        }
        catch(Exception e)
        {
            ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.extKeyUsageError", new Object[] {
                e.getMessage(), e, e.getClass().getName()
            });
            errors.add(msg);
        }
        try
        {
            Set certEmails = getEmailAddresses(cert);
            if(certEmails.isEmpty())
            {
                ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.noEmailInCert");
                errors.add(msg);
            } else
            {
                boolean equalsFrom = false;
                int i = 0;
                do
                {
                    if(i >= fromAddresses.length)
                        break;
                    if(certEmails.contains(fromAddresses[i].toLowerCase()))
                    {
                        equalsFrom = true;
                        break;
                    }
                    i++;
                } while(true);
                if(!equalsFrom)
                {
                    ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.emailFromCertMismatch", new Object[] {
                        new UntrustedInput(addressesToString(fromAddresses)), new UntrustedInput(certEmails)
                    });
                    errors.add(msg);
                }
            }
        }
        catch(Exception e)
        {
            ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.certGetEmailError", new Object[] {
                e.getMessage(), e, e.getClass().getName()
            });
            errors.add(msg);
        }
    }

    static String addressesToString(Object a[])
    {
        if(a == null)
            return "null";
        StringBuffer b = new StringBuffer();
        b.append('[');
        for(int i = 0; i != a.length; i++)
        {
            if(i > 0)
                b.append(", ");
            b.append(String.valueOf(a[i]));
        }

        return b.append(']').toString();
    }

    public static Date getSignatureTime(SignerInformation signer)
    {
        AttributeTable atab = signer.getSignedAttributes();
        Date result = null;
        if(atab != null)
        {
            Attribute attr = atab.get(CMSAttributes.signingTime);
            if(attr != null)
            {
                Time t = Time.getInstance(attr.getAttrValues().getObjectAt(0).toASN1Primitive());
                result = t.getDate();
            }
        }
        return result;
    }

    private static List findCerts(List certStores, X509CertSelector selector)
        throws CertStoreException
    {
        List result = new ArrayList();
        Collection coll;
        for(Iterator it = certStores.iterator(); it.hasNext(); result.addAll(coll))
        {
            CertStore store = (CertStore)it.next();
            coll = store.getCertificates(selector);
        }

        return result;
    }

    private static X509Certificate findNextCert(List certStores, X509CertSelector selector, Set certSet)
        throws CertStoreException
    {
        Iterator certIt = findCerts(certStores, selector).iterator();
        boolean certFound = false;
        X509Certificate nextCert = null;
        do
        {
            if(!certIt.hasNext())
                break;
            nextCert = (X509Certificate)certIt.next();
            if(certSet.contains(nextCert))
                continue;
            certFound = true;
            break;
        } while(true);
        return certFound ? nextCert : null;
    }

    public static CertPath createCertPath(X509Certificate signerCert, Set trustanchors, List certStores)
        throws GeneralSecurityException
    {
        Object results[] = createCertPath(signerCert, trustanchors, certStores, null);
        return (CertPath)results[0];
    }

    public static Object[] createCertPath(X509Certificate signerCert, Set trustanchors, List systemCertStores, List userCertStores)
        throws GeneralSecurityException
    {
        Set certSet = new LinkedHashSet();
        List userProvidedList = new ArrayList();
        X509Certificate cert = signerCert;
        certSet.add(cert);
        userProvidedList.add(new Boolean(true));
        boolean trustAnchorFound = false;
        X509Certificate taCert = null;
        do
        {
            if(cert == null || trustAnchorFound)
                break;
            Iterator trustIt = trustanchors.iterator();
            do
            {
                if(!trustIt.hasNext())
                    break;
                TrustAnchor anchor = (TrustAnchor)trustIt.next();
                X509Certificate anchorCert = anchor.getTrustedCert();
                if(anchorCert != null)
                {
                    if(!anchorCert.getSubjectX500Principal().equals(cert.getIssuerX500Principal()))
                        continue;
                    try
                    {
                        cert.verify(anchorCert.getPublicKey(), "BC");
                        trustAnchorFound = true;
                        taCert = anchorCert;
                        break;
                    }
                    catch(Exception e) { }
                    continue;
                }
                if(!anchor.getCAName().equals(cert.getIssuerX500Principal().getName()))
                    continue;
                try
                {
                    cert.verify(anchor.getCAPublicKey(), "BC");
                    trustAnchorFound = true;
                    break;
                }
                catch(Exception e) { }
            } while(true);
            if(!trustAnchorFound)
            {
                X509CertSelector select = new X509CertSelector();
                try
                {
                    select.setSubject(cert.getIssuerX500Principal().getEncoded());
                }
                catch(IOException e)
                {
                    throw new IllegalStateException(e.toString());
                }
                byte authKeyIdentBytes[] = cert.getExtensionValue(X509Extensions.AuthorityKeyIdentifier.getId());
                if(authKeyIdentBytes != null)
                    try
                    {
                        AuthorityKeyIdentifier kid = AuthorityKeyIdentifier.getInstance(getObject(authKeyIdentBytes));
                        if(kid.getKeyIdentifier() != null)
                            select.setSubjectKeyIdentifier((new DEROctetString(kid.getKeyIdentifier())).getEncoded("DER"));
                    }
                    catch(IOException ioe) { }
                boolean userProvided = false;
                cert = findNextCert(systemCertStores, select, certSet);
                if(cert == null && userCertStores != null)
                {
                    userProvided = true;
                    cert = findNextCert(userCertStores, select, certSet);
                }
                if(cert != null)
                {
                    certSet.add(cert);
                    userProvidedList.add(new Boolean(userProvided));
                }
            }
        } while(true);
        if(trustAnchorFound)
            if(taCert != null && taCert.getSubjectX500Principal().equals(taCert.getIssuerX500Principal()))
            {
                certSet.add(taCert);
                userProvidedList.add(new Boolean(false));
            } else
            {
                X509CertSelector select = new X509CertSelector();
                try
                {
                    select.setSubject(cert.getIssuerX500Principal().getEncoded());
                    select.setIssuer(cert.getIssuerX500Principal().getEncoded());
                }
                catch(IOException e)
                {
                    throw new IllegalStateException(e.toString());
                }
                boolean userProvided = false;
                taCert = findNextCert(systemCertStores, select, certSet);
                if(taCert == null && userCertStores != null)
                {
                    userProvided = true;
                    taCert = findNextCert(userCertStores, select, certSet);
                }
                if(taCert != null)
                    try
                    {
                        cert.verify(taCert.getPublicKey(), "BC");
                        certSet.add(taCert);
                        userProvidedList.add(new Boolean(userProvided));
                    }
                    catch(GeneralSecurityException gse) { }
            }
        CertPath certPath = CertificateFactory.getInstance("X.509", "BC").generateCertPath(new ArrayList(certSet));
        return (new Object[] {
            certPath, userProvidedList
        });
    }

    public CertStore getCertsAndCRLs()
    {
        return certs;
    }

    public SignerInformationStore getSignerInformationStore()
    {
        return signers;
    }

    public ValidationResult getValidationResult(SignerInformation signer)
        throws SignedMailValidatorException
    {
        if(signers.getSigners(signer.getSID()).isEmpty())
        {
            ErrorBundle msg = new ErrorBundle("co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages", "SignedMailValidator.wrongSigner");
            throw new SignedMailValidatorException(msg);
        } else
        {
            return (ValidationResult)results.get(signer);
        }
    }

    private static final String RESOURCE_NAME = "co.org.bouncy.mail.smime.validator.SignedMailValidatorMessages";
    private static final Class DEFAULT_CERT_PATH_REVIEWER = co/org/bouncy/x509_/PKIXCertPathReviewer;
    private static final String EXT_KEY_USAGE;
    private static final String SUBJECT_ALTERNATIVE_NAME;
    private static final int shortKeyLength = 512;
    private static final long THIRTY_YEARS_IN_MILLI_SEC = 0xdc6d62da00L;
    private static final JcaX509CertSelectorConverter selectorConverter = new JcaX509CertSelectorConverter();
    private CertStore certs;
    private SignerInformationStore signers;
    private Map results;
    private String fromAddresses[];
    private Class certPathReviewerClass;

    static 
    {
        EXT_KEY_USAGE = X509Extensions.ExtendedKeyUsage.getId();
        SUBJECT_ALTERNATIVE_NAME = X509Extensions.SubjectAlternativeName.getId();
    }
}
