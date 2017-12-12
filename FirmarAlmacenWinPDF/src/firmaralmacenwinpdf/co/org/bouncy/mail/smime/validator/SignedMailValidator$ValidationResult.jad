// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SignedMailValidator.java

package co.org.bouncy.mail.smime.validator;

import co.org.bouncy.x509_.PKIXCertPathReviewer;
import java.security.cert.CertPath;
import java.util.List;

// Referenced classes of package co.org.bouncy.mail.smime.validator:
//            SignedMailValidator

public class SignedMailValidator$ValidationResult
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

    SignedMailValidator$ValidationResult(PKIXCertPathReviewer review, boolean verified, List errors, List notifications, List userProvidedCerts)
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
