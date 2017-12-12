// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfSignatureAppearance.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.io.*;
import co.com.pdf.text.pdf.security.CertificateInfo;
import java.io.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfTemplate, PdfName, ColumnText, PdfNumber, 
//            PdfDictionary, PdfLiteral, ByteBuffer, PdfString, 
//            PdfArray, AcroFields, PdfIndirectReference, PdfObject, 
//            PdfFormField, PdfStamperImp, PdfReader, PdfStamper, 
//            PdfAnnotation, PdfDeveloperExtension

public class PdfSignatureAppearance
{
    public static final class RenderingMode extends Enum
    {

        public static RenderingMode[] values()
        {
            return (RenderingMode[])$VALUES.clone();
        }

        public static RenderingMode valueOf(String name)
        {
            return (RenderingMode)Enum.valueOf(co/com/pdf/text/pdf/PdfSignatureAppearance$RenderingMode, name);
        }

        public static final RenderingMode DESCRIPTION;
        public static final RenderingMode NAME_AND_DESCRIPTION;
        public static final RenderingMode GRAPHIC_AND_DESCRIPTION;
        public static final RenderingMode GRAPHIC;
        private static final RenderingMode $VALUES[];

        static 
        {
            DESCRIPTION = new RenderingMode("DESCRIPTION", 0);
            NAME_AND_DESCRIPTION = new RenderingMode("NAME_AND_DESCRIPTION", 1);
            GRAPHIC_AND_DESCRIPTION = new RenderingMode("GRAPHIC_AND_DESCRIPTION", 2);
            GRAPHIC = new RenderingMode("GRAPHIC", 3);
            $VALUES = (new RenderingMode[] {
                DESCRIPTION, NAME_AND_DESCRIPTION, GRAPHIC_AND_DESCRIPTION, GRAPHIC
            });
        }

        private RenderingMode(String s, int i)
        {
            super(s, i);
        }
    }

    public static interface SignatureEvent
    {

        public abstract void getSignatureDictionary(PdfDictionary pdfdictionary);
    }


    PdfSignatureAppearance(PdfStamperImp writer)
    {
        certificationLevel = 0;
        reasonCaption = "Reason: ";
        locationCaption = "Location: ";
        page = 1;
        renderingMode = RenderingMode.DESCRIPTION;
        signatureGraphic = null;
        acro6Layers = true;
        app = new PdfTemplate[5];
        reuseAppearance = false;
        runDirection = 1;
        preClosed = false;
        this.writer = writer;
        signDate = new GregorianCalendar();
        fieldName = getNewSigName();
    }

    public void setCertificationLevel(int certificationLevel)
    {
        this.certificationLevel = certificationLevel;
    }

    public int getCertificationLevel()
    {
        return certificationLevel;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public void setReasonCaption(String reasonCaption)
    {
        this.reasonCaption = reasonCaption;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public void setLocationCaption(String locationCaption)
    {
        this.locationCaption = locationCaption;
    }

    public String getContact()
    {
        return contact;
    }

    public void setContact(String contact)
    {
        this.contact = contact;
    }

    public Calendar getSignDate()
    {
        return signDate;
    }

    public void setSignDate(Calendar signDate)
    {
        this.signDate = signDate;
    }

    public InputStream getRangeStream()
        throws IOException
    {
        RandomAccessSourceFactory fac = new RandomAccessSourceFactory();
        return new RASInputStream(fac.createRanged(getUnderlyingSource(), range));
    }

    private RandomAccessSource getUnderlyingSource()
        throws IOException
    {
        RandomAccessSourceFactory fac = new RandomAccessSourceFactory();
        return raf != null ? fac.createSource(raf) : fac.createSource(bout);
    }

    public void addDeveloperExtension(PdfDeveloperExtension de)
    {
        writer.addDeveloperExtension(de);
    }

    public PdfDictionary getCryptoDictionary()
    {
        return cryptoDictionary;
    }

    public void setCryptoDictionary(PdfDictionary cryptoDictionary)
    {
        this.cryptoDictionary = cryptoDictionary;
    }

    public void setCertificate(Certificate signCertificate)
    {
        this.signCertificate = signCertificate;
    }

    public Certificate getCertificate()
    {
        return signCertificate;
    }

    public SignatureEvent getSignatureEvent()
    {
        return signatureEvent;
    }

    public void setSignatureEvent(SignatureEvent signatureEvent)
    {
        this.signatureEvent = signatureEvent;
    }

    public String getFieldName()
    {
        return fieldName;
    }

    public String getNewSigName()
    {
        AcroFields af = writer.getAcroFields();
        String name = "Signature";
        int step = 0;
        boolean found = false;
label0:
        do
        {
            if(found)
                break;
            step++;
            String n1 = (new StringBuilder()).append(name).append(step).toString();
            if(af.getFieldItem(n1) != null)
                continue;
            n1 = (new StringBuilder()).append(n1).append(".").toString();
            found = true;
            Iterator i$ = af.getFields().keySet().iterator();
            String fn;
            do
            {
                if(!i$.hasNext())
                    continue label0;
                Object element = i$.next();
                fn = (String)element;
            } while(!fn.startsWith(n1));
            found = false;
        } while(true);
        name = (new StringBuilder()).append(name).append(step).toString();
        return name;
    }

    public boolean isNewField()
    {
        return newField;
    }

    public int getPage()
    {
        return page;
    }

    public Rectangle getRect()
    {
        return rect;
    }

    public Rectangle getPageRect()
    {
        return pageRect;
    }

    public boolean isInvisible()
    {
        return rect == null || rect.getWidth() == 0.0F || rect.getHeight() == 0.0F;
    }

    public void setVisibleSignature(Rectangle pageRect, int page, String fieldName)
    {
        if(fieldName != null)
        {
            if(fieldName.indexOf('.') >= 0)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("field.names.cannot.contain.a.dot", new Object[0]));
            AcroFields af = writer.getAcroFields();
            AcroFields.Item item = af.getFieldItem(fieldName);
            if(item != null)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.field.1.already.exists", new Object[] {
                    fieldName
                }));
            this.fieldName = fieldName;
        }
        if(page < 1 || page > writer.reader.getNumberOfPages())
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("invalid.page.number.1", page));
        } else
        {
            this.pageRect = new Rectangle(pageRect);
            this.pageRect.normalize();
            rect = new Rectangle(this.pageRect.getWidth(), this.pageRect.getHeight());
            this.page = page;
            newField = true;
            return;
        }
    }

    public void setVisibleSignature(String fieldName)
    {
        AcroFields af = writer.getAcroFields();
        AcroFields.Item item = af.getFieldItem(fieldName);
        if(item == null)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.field.1.does.not.exist", new Object[] {
                fieldName
            }));
        PdfDictionary merged = item.getMerged(0);
        if(!PdfName.SIG.equals(PdfReader.getPdfObject(merged.get(PdfName.FT))))
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.field.1.is.not.a.signature.field", new Object[] {
                fieldName
            }));
        this.fieldName = fieldName;
        PdfArray r = merged.getAsArray(PdfName.RECT);
        float llx = r.getAsNumber(0).floatValue();
        float lly = r.getAsNumber(1).floatValue();
        float urx = r.getAsNumber(2).floatValue();
        float ury = r.getAsNumber(3).floatValue();
        pageRect = new Rectangle(llx, lly, urx, ury);
        pageRect.normalize();
        page = item.getPage(0).intValue();
        int rotation = writer.reader.getPageRotation(page);
        Rectangle pageSize = writer.reader.getPageSizeWithRotation(page);
        switch(rotation)
        {
        case 90: // 'Z'
            pageRect = new Rectangle(pageRect.getBottom(), pageSize.getTop() - pageRect.getLeft(), pageRect.getTop(), pageSize.getTop() - pageRect.getRight());
            break;

        case 180: 
            pageRect = new Rectangle(pageSize.getRight() - pageRect.getLeft(), pageSize.getTop() - pageRect.getBottom(), pageSize.getRight() - pageRect.getRight(), pageSize.getTop() - pageRect.getTop());
            break;

        case 270: 
            pageRect = new Rectangle(pageSize.getRight() - pageRect.getBottom(), pageRect.getLeft(), pageSize.getRight() - pageRect.getTop(), pageRect.getRight());
            break;
        }
        if(rotation != 0)
            pageRect.normalize();
        rect = new Rectangle(pageRect.getWidth(), pageRect.getHeight());
    }

    public RenderingMode getRenderingMode()
    {
        return renderingMode;
    }

    public void setRenderingMode(RenderingMode renderingMode)
    {
        this.renderingMode = renderingMode;
    }

    public Image getSignatureGraphic()
    {
        return signatureGraphic;
    }

    public void setSignatureGraphic(Image signatureGraphic)
    {
        this.signatureGraphic = signatureGraphic;
    }

    public boolean isAcro6Layers()
    {
        return acro6Layers;
    }

    /**
     * @deprecated Method setAcro6Layers is deprecated
     */

    public void setAcro6Layers(boolean acro6Layers)
    {
        this.acro6Layers = acro6Layers;
    }

    public PdfTemplate getLayer(int layer)
    {
        if(layer < 0 || layer >= app.length)
            return null;
        PdfTemplate t = app[layer];
        if(t == null)
        {
            t = app[layer] = new PdfTemplate(writer);
            t.setBoundingBox(rect);
            writer.addDirectTemplateSimple(t, new PdfName((new StringBuilder()).append("n").append(layer).toString()));
        }
        return t;
    }

    public void setReuseAppearance(boolean reuseAppearance)
    {
        this.reuseAppearance = reuseAppearance;
    }

    public Image getImage()
    {
        return image;
    }

    public void setImage(Image image)
    {
        this.image = image;
    }

    public float getImageScale()
    {
        return imageScale;
    }

    public void setImageScale(float imageScale)
    {
        this.imageScale = imageScale;
    }

    public void setLayer2Text(String text)
    {
        layer2Text = text;
    }

    public String getLayer2Text()
    {
        return layer2Text;
    }

    public Font getLayer2Font()
    {
        return layer2Font;
    }

    public void setLayer2Font(Font layer2Font)
    {
        this.layer2Font = layer2Font;
    }

    public void setRunDirection(int runDirection)
    {
        if(runDirection < 0 || runDirection > 3)
        {
            throw new RuntimeException(MessageLocalization.getComposedMessage("invalid.run.direction.1", runDirection));
        } else
        {
            this.runDirection = runDirection;
            return;
        }
    }

    public int getRunDirection()
    {
        return runDirection;
    }

    public void setLayer4Text(String text)
    {
        layer4Text = text;
    }

    public String getLayer4Text()
    {
        return layer4Text;
    }

    public PdfTemplate getTopLayer()
    {
        if(frm == null)
        {
            frm = new PdfTemplate(writer);
            frm.setBoundingBox(rect);
            writer.addDirectTemplateSimple(frm, new PdfName("FRM"));
        }
        return frm;
    }

    public PdfTemplate getAppearance()
        throws DocumentException
    {
        if(isInvisible())
        {
            PdfTemplate t = new PdfTemplate(writer);
            t.setBoundingBox(new Rectangle(0.0F, 0.0F));
            writer.addDirectTemplateSimple(t, null);
            return t;
        }
        if(app[0] == null && !reuseAppearance)
            createBlankN0();
        if(app[1] == null && !acro6Layers)
        {
            PdfTemplate t = app[1] = new PdfTemplate(writer);
            t.setBoundingBox(new Rectangle(100F, 100F));
            writer.addDirectTemplateSimple(t, new PdfName("n1"));
            t.setLiteral("% DSUnknown\nq\n1 G\n1 g\n0.1 0 0 0.1 9 0 cm\n0 J 0 j 4 M []0 d\n1 i \n0 g\n313 292 m\n313 404 325 453 432 529 c\n478 561 504 597 504 645 c\n504 736 440 760 391 760 c\n286 760 271 681 265 626 c\n265 625 l\n100 625 l\n100 828 253 898 381 898 c\n451 898 679 878 679 650 c\n679 555 628 499 538 435 c\n488 399 467 376 467 292 c\n313 292 l\nh\n308 214 170 -164 re\nf\n0.44 G\n1.2 w\n1 1 0.4 rg\n287 318 m\n287 430 299 479 406 555 c\n451 587 478 623 478 671 c\n478 762 414 786 365 786 c\n260 786 245 707 239 652 c\n239 651 l\n74 651 l\n74 854 227 924 355 924 c\n425 924 653 904 653 676 c\n653 581 602 525 512 461 c\n462 425 441 402 441 318 c\n287 318 l\nh\n282 240 170 -164 re\nB\nQ\n");
        }
        if(app[2] == null)
        {
            String text;
            if(layer2Text == null)
            {
                StringBuilder buf = new StringBuilder();
                buf.append("Digitally signed by ");
                String name = null;
                co.com.pdf.text.pdf.security.CertificateInfo.X500Name x500name = CertificateInfo.getSubjectFields((X509Certificate)signCertificate);
                if(x500name != null)
                {
                    name = x500name.getField("CN");
                    if(name == null)
                        name = x500name.getField("E");
                }
                if(name == null)
                    name = "";
                buf.append(name).append('\n');
                SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
                buf.append("Date: ").append(sd.format(signDate.getTime()));
                if(reason != null)
                    buf.append('\n').append(reasonCaption).append(reason);
                if(location != null)
                    buf.append('\n').append(locationCaption).append(location);
                text = buf.toString();
            } else
            {
                text = layer2Text;
            }
            PdfTemplate t = app[2] = new PdfTemplate(writer);
            t.setBoundingBox(rect);
            writer.addDirectTemplateSimple(t, new PdfName("n2"));
            if(image != null)
                if(imageScale == 0.0F)
                {
                    t.addImage(image, rect.getWidth(), 0.0F, 0.0F, rect.getHeight(), 0.0F, 0.0F);
                } else
                {
                    float usableScale = imageScale;
                    if(imageScale < 0.0F)
                        usableScale = Math.min(rect.getWidth() / image.getWidth(), rect.getHeight() / image.getHeight());
                    float w = image.getWidth() * usableScale;
                    float h = image.getHeight() * usableScale;
                    float x = (rect.getWidth() - w) / 2.0F;
                    float y = (rect.getHeight() - h) / 2.0F;
                    t.addImage(image, w, 0.0F, 0.0F, h, x, y);
                }
            Font font;
            if(layer2Font == null)
                font = new Font();
            else
                font = new Font(layer2Font);
            float size = font.getSize();
            Rectangle dataRect = null;
            Rectangle signatureRect = null;
            if(renderingMode == RenderingMode.NAME_AND_DESCRIPTION || renderingMode == RenderingMode.GRAPHIC_AND_DESCRIPTION && signatureGraphic != null)
            {
                signatureRect = new Rectangle(2.0F, 2.0F, rect.getWidth() / 2.0F - 2.0F, rect.getHeight() - 2.0F);
                dataRect = new Rectangle(rect.getWidth() / 2.0F + 1.0F, 2.0F, rect.getWidth() - 1.0F, rect.getHeight() - 2.0F);
                if(rect.getHeight() > rect.getWidth())
                {
                    signatureRect = new Rectangle(2.0F, rect.getHeight() / 2.0F, rect.getWidth() - 2.0F, rect.getHeight());
                    dataRect = new Rectangle(2.0F, 2.0F, rect.getWidth() - 2.0F, rect.getHeight() / 2.0F - 2.0F);
                }
            } else
            if(renderingMode == RenderingMode.GRAPHIC)
            {
                if(signatureGraphic == null)
                    throw new IllegalStateException(MessageLocalization.getComposedMessage("a.signature.image.should.be.present.when.rendering.mode.is.graphic.only", new Object[0]));
                signatureRect = new Rectangle(2.0F, 2.0F, rect.getWidth() - 2.0F, rect.getHeight() - 2.0F);
            } else
            {
                dataRect = new Rectangle(2.0F, 2.0F, rect.getWidth() - 2.0F, rect.getHeight() * 0.7F - 2.0F);
            }
            static class _cls1
            {

                static final int $SwitchMap$co$com$pdf$text$pdf$PdfSignatureAppearance$RenderingMode[];

                static 
                {
                    $SwitchMap$co$com$pdf$text$pdf$PdfSignatureAppearance$RenderingMode = new int[RenderingMode.values().length];
                    try
                    {
                        $SwitchMap$co$com$pdf$text$pdf$PdfSignatureAppearance$RenderingMode[RenderingMode.NAME_AND_DESCRIPTION.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$co$com$pdf$text$pdf$PdfSignatureAppearance$RenderingMode[RenderingMode.GRAPHIC_AND_DESCRIPTION.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError ex) { }
                    try
                    {
                        $SwitchMap$co$com$pdf$text$pdf$PdfSignatureAppearance$RenderingMode[RenderingMode.GRAPHIC.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError ex) { }
                }
            }

            switch(_cls1..SwitchMap.co.com.pdf.text.pdf.PdfSignatureAppearance.RenderingMode[renderingMode.ordinal()])
            {
            case 1: // '\001'
            {
                String signedBy = CertificateInfo.getSubjectFields((X509Certificate)signCertificate).getField("CN");
                if(signedBy == null)
                    signedBy = CertificateInfo.getSubjectFields((X509Certificate)signCertificate).getField("E");
                if(signedBy == null)
                    signedBy = "";
                Rectangle sr2 = new Rectangle(signatureRect.getWidth() - 2.0F, signatureRect.getHeight() - 2.0F);
                float signedSize = ColumnText.fitText(font, signedBy, sr2, -1F, runDirection);
                ColumnText ct2 = new ColumnText(t);
                ct2.setRunDirection(runDirection);
                ct2.setSimpleColumn(new Phrase(signedBy, font), signatureRect.getLeft(), signatureRect.getBottom(), signatureRect.getRight(), signatureRect.getTop(), signedSize, 0);
                ct2.go();
                break;
            }

            case 2: // '\002'
            {
                if(signatureGraphic == null)
                    throw new IllegalStateException(MessageLocalization.getComposedMessage("a.signature.image.should.be.present.when.rendering.mode.is.graphic.and.description", new Object[0]));
                ColumnText ct2 = new ColumnText(t);
                ct2.setRunDirection(runDirection);
                ct2.setSimpleColumn(signatureRect.getLeft(), signatureRect.getBottom(), signatureRect.getRight(), signatureRect.getTop(), 0.0F, 2);
                Image im = Image.getInstance(signatureGraphic);
                im.scaleToFit(signatureRect.getWidth(), signatureRect.getHeight());
                Paragraph p = new Paragraph();
                float x = 0.0F;
                float y = -im.getScaledHeight() + 15F;
                x += (signatureRect.getWidth() - im.getScaledWidth()) / 2.0F;
                y -= (signatureRect.getHeight() - im.getScaledHeight()) / 2.0F;
                p.add(new Chunk(im, x + (signatureRect.getWidth() - im.getScaledWidth()) / 2.0F, y, false));
                ct2.addElement(p);
                ct2.go();
                break;
            }

            case 3: // '\003'
            {
                ColumnText ct2 = new ColumnText(t);
                ct2.setRunDirection(runDirection);
                ct2.setSimpleColumn(signatureRect.getLeft(), signatureRect.getBottom(), signatureRect.getRight(), signatureRect.getTop(), 0.0F, 2);
                Image im = Image.getInstance(signatureGraphic);
                im.scaleToFit(signatureRect.getWidth(), signatureRect.getHeight());
                Paragraph p = new Paragraph(signatureRect.getHeight());
                float x = (signatureRect.getWidth() - im.getScaledWidth()) / 2.0F;
                float y = (signatureRect.getHeight() - im.getScaledHeight()) / 2.0F;
                p.add(new Chunk(im, x, y, false));
                ct2.addElement(p);
                ct2.go();
                break;
            }
            }
            if(renderingMode != RenderingMode.GRAPHIC)
            {
                if(size <= 0.0F)
                {
                    Rectangle sr = new Rectangle(dataRect.getWidth(), dataRect.getHeight());
                    size = ColumnText.fitText(font, text, sr, 12F, runDirection);
                }
                ColumnText ct = new ColumnText(t);
                ct.setRunDirection(runDirection);
                ct.setSimpleColumn(new Phrase(text, font), dataRect.getLeft(), dataRect.getBottom(), dataRect.getRight(), dataRect.getTop(), size, 0);
                ct.go();
            }
        }
        if(app[3] == null && !acro6Layers)
        {
            PdfTemplate t = app[3] = new PdfTemplate(writer);
            t.setBoundingBox(new Rectangle(100F, 100F));
            writer.addDirectTemplateSimple(t, new PdfName("n3"));
            t.setLiteral("% DSBlank\n");
        }
        if(app[4] == null && !acro6Layers)
        {
            PdfTemplate t = app[4] = new PdfTemplate(writer);
            t.setBoundingBox(new Rectangle(0.0F, rect.getHeight() * 0.7F, rect.getRight(), rect.getTop()));
            writer.addDirectTemplateSimple(t, new PdfName("n4"));
            Font font;
            if(layer2Font == null)
                font = new Font();
            else
                font = new Font(layer2Font);
            String text = "Signature Not Verified";
            if(layer4Text != null)
                text = layer4Text;
            Rectangle sr = new Rectangle(rect.getWidth() - 4F, rect.getHeight() * 0.3F - 4F);
            float size = ColumnText.fitText(font, text, sr, 15F, runDirection);
            ColumnText ct = new ColumnText(t);
            ct.setRunDirection(runDirection);
            ct.setSimpleColumn(new Phrase(text, font), 2.0F, 0.0F, rect.getWidth() - 2.0F, rect.getHeight() - 2.0F, size, 0);
            ct.go();
        }
        int rotation = writer.reader.getPageRotation(page);
        Rectangle rotated = new Rectangle(rect);
        for(int n = rotation; n > 0; n -= 90)
            rotated = rotated.rotate();

        if(frm == null)
        {
            frm = new PdfTemplate(writer);
            frm.setBoundingBox(rotated);
            writer.addDirectTemplateSimple(frm, new PdfName("FRM"));
            float scale = Math.min(rect.getWidth(), rect.getHeight()) * 0.9F;
            float x = (rect.getWidth() - scale) / 2.0F;
            float y = (rect.getHeight() - scale) / 2.0F;
            scale /= 100F;
            if(rotation == 90)
                frm.concatCTM(0.0F, 1.0F, -1F, 0.0F, rect.getHeight(), 0.0F);
            else
            if(rotation == 180)
                frm.concatCTM(-1F, 0.0F, 0.0F, -1F, rect.getWidth(), rect.getHeight());
            else
            if(rotation == 270)
                frm.concatCTM(0.0F, -1F, 1.0F, 0.0F, 0.0F, rect.getWidth());
            if(reuseAppearance)
            {
                AcroFields af = writer.getAcroFields();
                PdfIndirectReference ref = af.getNormalAppearance(getFieldName());
                if(ref != null)
                {
                    frm.addTemplateReference(ref, new PdfName("n0"), 1.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F);
                } else
                {
                    reuseAppearance = false;
                    if(app[0] == null)
                        createBlankN0();
                }
            }
            if(!reuseAppearance)
                frm.addTemplate(app[0], 0.0F, 0.0F);
            if(!acro6Layers)
                frm.addTemplate(app[1], scale, 0.0F, 0.0F, scale, x, y);
            frm.addTemplate(app[2], 0.0F, 0.0F);
            if(!acro6Layers)
            {
                frm.addTemplate(app[3], scale, 0.0F, 0.0F, scale, x, y);
                frm.addTemplate(app[4], 0.0F, 0.0F);
            }
        }
        PdfTemplate napp = new PdfTemplate(writer);
        napp.setBoundingBox(rotated);
        writer.addDirectTemplateSimple(napp, null);
        napp.addTemplate(frm, 0.0F, 0.0F);
        return napp;
    }

    private void createBlankN0()
    {
        PdfTemplate t = app[0] = new PdfTemplate(writer);
        t.setBoundingBox(new Rectangle(100F, 100F));
        writer.addDirectTemplateSimple(t, new PdfName("n0"));
        t.setLiteral("% DSBlank\n");
    }

    public PdfStamper getStamper()
    {
        return stamper;
    }

    void setStamper(PdfStamper stamper)
    {
        this.stamper = stamper;
    }

    ByteBuffer getSigout()
    {
        return sigout;
    }

    void setSigout(ByteBuffer sigout)
    {
        this.sigout = sigout;
    }

    OutputStream getOriginalout()
    {
        return originalout;
    }

    void setOriginalout(OutputStream originalout)
    {
        this.originalout = originalout;
    }

    public File getTempFile()
    {
        return tempFile;
    }

    void setTempFile(File tempFile)
    {
        this.tempFile = tempFile;
    }

    public boolean isPreClosed()
    {
        return preClosed;
    }

    public void preClose(HashMap exclusionSizes)
        throws IOException, DocumentException
    {
        if(preClosed)
            throw new DocumentException(MessageLocalization.getComposedMessage("document.already.pre.closed", new Object[0]));
        stamper.mergeVerification();
        preClosed = true;
        AcroFields af = writer.getAcroFields();
        String name = getFieldName();
        boolean fieldExists = !isInvisible() && !isNewField();
        PdfIndirectReference refSig = writer.getPdfIndirectReference();
        writer.setSigFlags(3);
        PdfDictionary fieldLock = null;
        if(fieldExists)
        {
            PdfDictionary widget = af.getFieldItem(name).getWidget(0);
            writer.markUsed(widget);
            fieldLock = widget.getAsDict(PdfName.LOCK);
            widget.put(PdfName.P, writer.getPageReference(getPage()));
            widget.put(PdfName.V, refSig);
            PdfObject obj = PdfReader.getPdfObjectRelease(widget.get(PdfName.F));
            int flags = 0;
            if(obj != null && obj.isNumber())
                flags = ((PdfNumber)obj).intValue();
            flags |= 0x80;
            widget.put(PdfName.F, new PdfNumber(flags));
            PdfDictionary ap = new PdfDictionary();
            ap.put(PdfName.N, getAppearance().getIndirectReference());
            widget.put(PdfName.AP, ap);
        } else
        {
            PdfFormField sigField = PdfFormField.createSignature(writer);
            sigField.setFieldName(name);
            sigField.put(PdfName.V, refSig);
            sigField.setFlags(132);
            int pagen = getPage();
            if(!isInvisible())
                sigField.setWidget(getPageRect(), null);
            else
                sigField.setWidget(new Rectangle(0.0F, 0.0F), null);
            sigField.setAppearance(PdfAnnotation.APPEARANCE_NORMAL, getAppearance());
            sigField.setPage(pagen);
            writer.addAnnotation(sigField, pagen);
        }
        exclusionLocations = new HashMap();
        if(cryptoDictionary == null)
            throw new DocumentException("No crypto dictionary defined.");
        PdfLiteral lit = new PdfLiteral(80);
        exclusionLocations.put(PdfName.BYTERANGE, lit);
        cryptoDictionary.put(PdfName.BYTERANGE, lit);
        PdfName key;
        for(Iterator i$ = exclusionSizes.entrySet().iterator(); i$.hasNext(); cryptoDictionary.put(key, lit))
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
            key = (PdfName)entry.getKey();
            Integer v = (Integer)entry.getValue();
            lit = new PdfLiteral(v.intValue());
            exclusionLocations.put(key, lit);
        }

        if(certificationLevel > 0)
            addDocMDP(cryptoDictionary);
        if(fieldLock != null)
            addFieldMDP(cryptoDictionary, fieldLock);
        if(signatureEvent != null)
            signatureEvent.getSignatureDictionary(cryptoDictionary);
        writer.addToBody(cryptoDictionary, refSig, false);
        if(certificationLevel > 0)
        {
            PdfDictionary docmdp = new PdfDictionary();
            docmdp.put(new PdfName("DocMDP"), refSig);
            writer.reader.getCatalog().put(new PdfName("Perms"), docmdp);
        }
        writer.close(stamper.getMoreInfo());
        range = new long[exclusionLocations.size() * 2];
        long byteRangePosition = ((PdfLiteral)exclusionLocations.get(PdfName.BYTERANGE)).getPosition();
        exclusionLocations.remove(PdfName.BYTERANGE);
        int idx = 1;
        for(Iterator i$ = exclusionLocations.values().iterator(); i$.hasNext();)
        {
            PdfLiteral lit = (PdfLiteral)i$.next();
            long n = lit.getPosition();
            range[idx++] = n;
            range[idx++] = (long)lit.getPosLength() + n;
        }

        Arrays.sort(range, 1, range.length - 1);
        for(int k = 3; k < range.length - 2; k += 2)
            range[k] -= range[k - 1];

        if(tempFile == null)
        {
            bout = sigout.getBuffer();
            boutLen = sigout.size();
            range[range.length - 1] = (long)boutLen - range[range.length - 2];
            ByteBuffer bf = new ByteBuffer();
            bf.append('[');
            for(int k = 0; k < range.length; k++)
                bf.append(range[k]).append(' ');

            bf.append(']');
            System.arraycopy(bf.getBuffer(), 0, bout, (int)byteRangePosition, bf.size());
        } else
        {
            try
            {
                raf = new RandomAccessFile(tempFile, "rw");
                long len = raf.length();
                range[range.length - 1] = len - range[range.length - 2];
                ByteBuffer bf = new ByteBuffer();
                bf.append('[');
                for(int k = 0; k < range.length; k++)
                    bf.append(range[k]).append(' ');

                bf.append(']');
                raf.seek(byteRangePosition);
                raf.write(bf.getBuffer(), 0, bf.size());
            }
            catch(IOException e)
            {
                try
                {
                    raf.close();
                }
                catch(Exception ee) { }
                try
                {
                    tempFile.delete();
                }
                catch(Exception ee) { }
                throw e;
            }
        }
    }

    private void addDocMDP(PdfDictionary crypto)
    {
        PdfDictionary reference = new PdfDictionary();
        PdfDictionary transformParams = new PdfDictionary();
        transformParams.put(PdfName.P, new PdfNumber(certificationLevel));
        transformParams.put(PdfName.V, new PdfName("1.2"));
        transformParams.put(PdfName.TYPE, PdfName.TRANSFORMPARAMS);
        reference.put(PdfName.TRANSFORMMETHOD, PdfName.DOCMDP);
        reference.put(PdfName.TYPE, PdfName.SIGREF);
        reference.put(PdfName.TRANSFORMPARAMS, transformParams);
        reference.put(new PdfName("DigestValue"), new PdfString("aa"));
        PdfArray loc = new PdfArray();
        loc.add(new PdfNumber(0));
        loc.add(new PdfNumber(0));
        reference.put(new PdfName("DigestLocation"), loc);
        reference.put(new PdfName("DigestMethod"), new PdfName("MD5"));
        reference.put(PdfName.DATA, writer.reader.getTrailer().get(PdfName.ROOT));
        PdfArray types = new PdfArray();
        types.add(reference);
        crypto.put(PdfName.REFERENCE, types);
    }

    private void addFieldMDP(PdfDictionary crypto, PdfDictionary fieldLock)
    {
        PdfDictionary reference = new PdfDictionary();
        PdfDictionary transformParams = new PdfDictionary();
        transformParams.putAll(fieldLock);
        transformParams.put(PdfName.TYPE, PdfName.TRANSFORMPARAMS);
        transformParams.put(PdfName.V, new PdfName("1.2"));
        reference.put(PdfName.TRANSFORMMETHOD, PdfName.FIELDMDP);
        reference.put(PdfName.TYPE, PdfName.SIGREF);
        reference.put(PdfName.TRANSFORMPARAMS, transformParams);
        reference.put(new PdfName("DigestValue"), new PdfString("aa"));
        PdfArray loc = new PdfArray();
        loc.add(new PdfNumber(0));
        loc.add(new PdfNumber(0));
        reference.put(new PdfName("DigestLocation"), loc);
        reference.put(new PdfName("DigestMethod"), new PdfName("MD5"));
        reference.put(PdfName.DATA, writer.reader.getTrailer().get(PdfName.ROOT));
        PdfArray types = crypto.getAsArray(PdfName.REFERENCE);
        if(types == null)
            types = new PdfArray();
        types.add(reference);
        crypto.put(PdfName.REFERENCE, types);
    }

    public void close(PdfDictionary update)
        throws IOException, DocumentException
    {
        if(!preClosed)
            throw new DocumentException(MessageLocalization.getComposedMessage("preclose.must.be.called.first", new Object[0]));
        ByteBuffer bf = new ByteBuffer();
        for(Iterator i$ = update.getKeys().iterator(); i$.hasNext();)
        {
            PdfName key = (PdfName)i$.next();
            PdfObject obj = update.get(key);
            PdfLiteral lit = (PdfLiteral)exclusionLocations.get(key);
            if(lit == null)
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.key.1.didn.t.reserve.space.in.preclose", new Object[] {
                    key.toString()
                }));
            bf.reset();
            obj.toPdf(null, bf);
            if(bf.size() > lit.getPosLength())
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.key.1.is.too.big.is.2.reserved.3", new Object[] {
                    key.toString(), String.valueOf(bf.size()), String.valueOf(lit.getPosLength())
                }));
            if(tempFile == null)
            {
                System.arraycopy(bf.getBuffer(), 0, bout, (int)lit.getPosition(), bf.size());
            } else
            {
                raf.seek(lit.getPosition());
                raf.write(bf.getBuffer(), 0, bf.size());
            }
        }

        if(update.size() != exclusionLocations.size())
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("the.update.dictionary.has.less.keys.than.required", new Object[0]));
        if(tempFile == null)
            originalout.write(bout, 0, boutLen);
        else
        if(originalout != null)
        {
            raf.seek(0L);
            long length = raf.length();
            byte buf[] = new byte[8192];
            int r;
            for(; length > 0L; length -= r)
            {
                r = raf.read(buf, 0, (int)Math.min(buf.length, length));
                if(r < 0)
                    throw new EOFException(MessageLocalization.getComposedMessage("unexpected.eof", new Object[0]));
                originalout.write(buf, 0, r);
            }

        }
        writer.reader.close();
        if(tempFile != null)
        {
            try
            {
                raf.close();
            }
            catch(Exception ee) { }
            if(originalout != null)
                try
                {
                    tempFile.delete();
                }
                catch(Exception ee) { }
        }
        if(originalout != null)
            try
            {
                originalout.close();
            }
            catch(Exception e) { }
        break MISSING_BLOCK_LABEL_549;
        Exception exception;
        exception;
        writer.reader.close();
        if(tempFile != null)
        {
            try
            {
                raf.close();
            }
            catch(Exception ee) { }
            if(originalout != null)
                try
                {
                    tempFile.delete();
                }
                catch(Exception ee) { }
        }
        if(originalout != null)
            try
            {
                originalout.close();
            }
            catch(Exception e) { }
        throw exception;
    }

    public static final int NOT_CERTIFIED = 0;
    public static final int CERTIFIED_NO_CHANGES_ALLOWED = 1;
    public static final int CERTIFIED_FORM_FILLING = 2;
    public static final int CERTIFIED_FORM_FILLING_AND_ANNOTATIONS = 3;
    private int certificationLevel;
    private String reasonCaption;
    private String locationCaption;
    private String reason;
    private String location;
    private Calendar signDate;
    private String contact;
    private RandomAccessFile raf;
    private byte bout[];
    private long range[];
    private Certificate signCertificate;
    private PdfDictionary cryptoDictionary;
    private SignatureEvent signatureEvent;
    private String fieldName;
    private boolean newField;
    private int page;
    private Rectangle rect;
    private Rectangle pageRect;
    private RenderingMode renderingMode;
    private Image signatureGraphic;
    private boolean acro6Layers;
    private PdfTemplate app[];
    private boolean reuseAppearance;
    public static final String questionMark = "% DSUnknown\nq\n1 G\n1 g\n0.1 0 0 0.1 9 0 cm\n0 J 0 j 4 M []0 d\n1 i \n0 g\n313 292 m\n313 404 325 453 432 529 c\n478 561 504 597 504 645 c\n504 736 440 760 391 760 c\n286 760 271 681 265 626 c\n265 625 l\n100 625 l\n100 828 253 898 381 898 c\n451 898 679 878 679 650 c\n679 555 628 499 538 435 c\n488 399 467 376 467 292 c\n313 292 l\nh\n308 214 170 -164 re\nf\n0.44 G\n1.2 w\n1 1 0.4 rg\n287 318 m\n287 430 299 479 406 555 c\n451 587 478 623 478 671 c\n478 762 414 786 365 786 c\n260 786 245 707 239 652 c\n239 651 l\n74 651 l\n74 854 227 924 355 924 c\n425 924 653 904 653 676 c\n653 581 602 525 512 461 c\n462 425 441 402 441 318 c\n287 318 l\nh\n282 240 170 -164 re\nB\nQ\n";
    private Image image;
    private float imageScale;
    private String layer2Text;
    private Font layer2Font;
    private int runDirection;
    private String layer4Text;
    private PdfTemplate frm;
    private static final float TOP_SECTION = 0.3F;
    private static final float MARGIN = 2F;
    private PdfStamper stamper;
    private PdfStamperImp writer;
    private ByteBuffer sigout;
    private OutputStream originalout;
    private File tempFile;
    private HashMap exclusionLocations;
    private int boutLen;
    private boolean preClosed;
}
