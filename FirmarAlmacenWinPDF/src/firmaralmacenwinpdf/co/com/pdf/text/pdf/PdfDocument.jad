// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfDocument.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.api.WriterOperation;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.pdf.collection.PdfCollection;
import co.com.pdf.text.pdf.draw.DrawInterface;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import co.com.pdf.text.pdf.internal.PdfAnnotationsImp;
import co.com.pdf.text.pdf.internal.PdfVersionImp;
import co.com.pdf.text.pdf.internal.PdfViewerPreferencesImp;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfChunk, PdfAction, PdfPTable, PdfPCell, 
//            PdfDestination, PdfOutline, PdfDiv, PdfStream, 
//            PdfArray, PdfXConformanceException, PdfRectangle, PdfDictionary, 
//            PdfPage, PdfNumber, PdfContents, PageResources, 
//            PdfContentByte, PdfLine, PdfFont, PdfAnnotation, 
//            PdfStructureElement, PdfTextArray, PdfString, ColumnText, 
//            FloatLayout, PdfPageEvent, PdfEncryption, PdfWriter, 
//            PdfObject, PdfException, PdfName, PdfIndirectObject, 
//            BaseFont, PdfStructureTreeRoot, PdfFormField, PdfPageLabels, 
//            PdfFileSpecification, PdfEncodings, PdfTransition, PdfIndirectReference, 
//            PdfAcroForm, PdfNameTree, PdfDate

public class PdfDocument extends Document
{
    public class Destination
    {

        public PdfAction action;
        public PdfIndirectReference reference;
        public PdfDestination destination;
        final PdfDocument this$0;

        public Destination()
        {
            this$0 = PdfDocument.this;
            super();
        }
    }

    public static class Indentation
    {

        float indentLeft;
        float sectionIndentLeft;
        float listIndentLeft;
        float imageIndentLeft;
        float indentRight;
        float sectionIndentRight;
        float imageIndentRight;
        float indentTop;
        float indentBottom;

        public Indentation()
        {
            indentLeft = 0.0F;
            sectionIndentLeft = 0.0F;
            listIndentLeft = 0.0F;
            imageIndentLeft = 0.0F;
            indentRight = 0.0F;
            sectionIndentRight = 0.0F;
            imageIndentRight = 0.0F;
            indentTop = 0.0F;
            indentBottom = 0.0F;
        }
    }

    static class PdfCatalog extends PdfDictionary
    {

        void addNames(TreeMap localDestinations, HashMap documentLevelJS, HashMap documentFileAttachment, PdfWriter writer)
        {
            if(localDestinations.isEmpty() && documentLevelJS.isEmpty() && documentFileAttachment.isEmpty())
                return;
            try
            {
                PdfDictionary names = new PdfDictionary();
                if(!localDestinations.isEmpty())
                {
                    PdfArray ar = new PdfArray();
                    Iterator i$ = localDestinations.entrySet().iterator();
                    do
                    {
                        if(!i$.hasNext())
                            break;
                        java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                        String name = (String)entry.getKey();
                        Destination dest = (Destination)entry.getValue();
                        if(dest.destination != null)
                        {
                            PdfIndirectReference ref = dest.reference;
                            ar.add(new PdfString(name, null));
                            ar.add(ref);
                        }
                    } while(true);
                    if(ar.size() > 0)
                    {
                        PdfDictionary dests = new PdfDictionary();
                        dests.put(PdfName.NAMES, ar);
                        names.put(PdfName.DESTS, writer.addToBody(dests).getIndirectReference());
                    }
                }
                if(!documentLevelJS.isEmpty())
                {
                    PdfDictionary tree = PdfNameTree.writeTree(documentLevelJS, writer);
                    names.put(PdfName.JAVASCRIPT, writer.addToBody(tree).getIndirectReference());
                }
                if(!documentFileAttachment.isEmpty())
                    names.put(PdfName.EMBEDDEDFILES, writer.addToBody(PdfNameTree.writeTree(documentFileAttachment, writer)).getIndirectReference());
                if(names.size() > 0)
                    put(PdfName.NAMES, writer.addToBody(names).getIndirectReference());
            }
            catch(IOException e)
            {
                throw new ExceptionConverter(e);
            }
        }

        void setOpenAction(PdfAction action)
        {
            put(PdfName.OPENACTION, action);
        }

        void setAdditionalActions(PdfDictionary actions)
        {
            try
            {
                put(PdfName.AA, writer.addToBody(actions).getIndirectReference());
            }
            catch(Exception e)
            {
                throw new ExceptionConverter(e);
            }
        }

        PdfWriter writer;

        PdfCatalog(PdfIndirectReference pages, PdfWriter writer)
        {
            super(CATALOG);
            this.writer = writer;
            put(PdfName.PAGES, pages);
        }
    }

    public static class PdfInfo extends PdfDictionary
    {

        void addTitle(String title)
        {
            put(PdfName.TITLE, new PdfString(title, "UnicodeBig"));
        }

        void addSubject(String subject)
        {
            put(PdfName.SUBJECT, new PdfString(subject, "UnicodeBig"));
        }

        void addKeywords(String keywords)
        {
            put(PdfName.KEYWORDS, new PdfString(keywords, "UnicodeBig"));
        }

        void addAuthor(String author)
        {
            put(PdfName.AUTHOR, new PdfString(author, "UnicodeBig"));
        }

        void addCreator(String creator)
        {
            put(PdfName.CREATOR, new PdfString(creator, "UnicodeBig"));
        }

        void addProducer()
        {
            put(PdfName.PRODUCER, new PdfString(Version.getInstance().getVersion()));
        }

        void addCreationDate()
        {
            PdfString date = new PdfDate();
            put(PdfName.CREATIONDATE, date);
            put(PdfName.MODDATE, date);
        }

        void addkey(String key, String value)
        {
            if(key.equals("Producer") || key.equals("CreationDate"))
            {
                return;
            } else
            {
                put(new PdfName(key), new PdfString(value, "UnicodeBig"));
                return;
            }
        }

        PdfInfo()
        {
            addProducer();
            addCreationDate();
        }

        PdfInfo(String author, String title, String subject)
        {
            this();
            addTitle(title);
            addSubject(subject);
            addAuthor(author);
        }
    }


    public PdfDocument()
    {
        structElements = new HashMap();
        openMCDocument = false;
        structParentIndices = new HashMap();
        markPoints = new HashMap();
        leading = 0.0F;
        alignment = 0;
        currentHeight = 0.0F;
        isSectionTitle = false;
        leadingCount = 0;
        anchorAction = null;
        firstPageEvent = true;
        line = null;
        lines = new ArrayList();
        lastElementType = -1;
        indentation = new Indentation();
        info = new PdfInfo();
        viewerPreferences = new PdfViewerPreferencesImp();
        localDestinations = new TreeMap();
        documentLevelJS = new HashMap();
        documentFileAttachment = new HashMap();
        nextPageSize = null;
        thisBoxSize = new HashMap();
        boxSize = new HashMap();
        pageEmpty = true;
        pageAA = null;
        strictImageSequence = false;
        imageEnd = -1F;
        imageWait = null;
        floatingElements = new ArrayList();
        addProducer();
        addCreationDate();
    }

    public void addWriter(PdfWriter writer)
        throws DocumentException
    {
        if(this.writer == null)
        {
            this.writer = writer;
            annotationsImp = new PdfAnnotationsImp(writer);
            return;
        } else
        {
            throw new DocumentException(MessageLocalization.getComposedMessage("you.can.only.add.a.writer.to.a.pdfdocument.once", new Object[0]));
        }
    }

    public float getLeading()
    {
        return leading;
    }

    void setLeading(float leading)
    {
        this.leading = leading;
    }

    public TabSettings getTabSettings()
    {
        return tabSettings;
    }

    public void setTabSettings(TabSettings tabSettings)
    {
        this.tabSettings = tabSettings;
    }

    public boolean add(Element element)
        throws DocumentException
    {
        if(writer != null && writer.isPaused())
            return false;
        try
        {
            if(element.type() != 37)
                flushFloatingElements();
            switch(element.type())
            {
            case 0: // '\0'
            {
                info.addkey(((Meta)element).getName(), ((Meta)element).getContent());
                break;
            }

            case 1: // '\001'
            {
                info.addTitle(((Meta)element).getContent());
                break;
            }

            case 2: // '\002'
            {
                info.addSubject(((Meta)element).getContent());
                break;
            }

            case 3: // '\003'
            {
                info.addKeywords(((Meta)element).getContent());
                break;
            }

            case 4: // '\004'
            {
                info.addAuthor(((Meta)element).getContent());
                break;
            }

            case 7: // '\007'
            {
                info.addCreator(((Meta)element).getContent());
                break;
            }

            case 8: // '\b'
            {
                setLanguage(((Meta)element).getContent());
                break;
            }

            case 5: // '\005'
            {
                info.addProducer();
                break;
            }

            case 6: // '\006'
            {
                info.addCreationDate();
                break;
            }

            case 10: // '\n'
            {
                if(line == null)
                    carriageReturn();
                PdfChunk chunk = new PdfChunk((Chunk)element, anchorAction, tabSettings);
                do
                {
                    PdfChunk overflow;
                    if((overflow = line.add(chunk)) == null)
                        break;
                    carriageReturn();
                    boolean newlineSplit = chunk.isNewlineSplit();
                    chunk = overflow;
                    if(!newlineSplit)
                        chunk.trimFirstSpace();
                } while(true);
                pageEmpty = false;
                if(chunk.isAttribute("NEWPAGE"))
                    newPage();
                break;
            }

            case 17: // '\021'
            {
                leadingCount++;
                Anchor anchor = (Anchor)element;
                String url = anchor.getReference();
                leading = anchor.getLeading();
                if(url != null)
                    anchorAction = new PdfAction(url);
                element.process(this);
                anchorAction = null;
                leadingCount--;
                break;
            }

            case 29: // '\035'
            {
                if(line == null)
                    carriageReturn();
                Annotation annot = (Annotation)element;
                Rectangle rect = new Rectangle(0.0F, 0.0F);
                if(line != null)
                    rect = new Rectangle(annot.llx(indentRight() - line.widthLeft()), annot.ury(indentTop() - currentHeight - 20F), annot.urx((indentRight() - line.widthLeft()) + 20F), annot.lly(indentTop() - currentHeight));
                PdfAnnotation an = PdfAnnotationsImp.convertAnnotation(writer, annot, rect);
                annotationsImp.addPlainAnnotation(an);
                pageEmpty = false;
                break;
            }

            case 11: // '\013'
            {
                leadingCount++;
                TabSettings backupTabSettings = tabSettings;
                if(((Phrase)element).getTabSettings() != null)
                    tabSettings = ((Phrase)element).getTabSettings();
                leading = ((Phrase)element).getTotalLeading();
                element.process(this);
                tabSettings = backupTabSettings;
                leadingCount--;
                break;
            }

            case 12: // '\f'
            {
                leadingCount++;
                TabSettings backupTabSettings = tabSettings;
                if(((Phrase)element).getTabSettings() != null)
                    tabSettings = ((Phrase)element).getTabSettings();
                Paragraph paragraph = (Paragraph)element;
                if(isTagged(writer))
                {
                    flushLines();
                    text.openMCBlock(paragraph);
                }
                addSpacing(paragraph.getSpacingBefore(), leading, paragraph.getFont());
                alignment = paragraph.getAlignment();
                leading = paragraph.getTotalLeading();
                carriageReturn();
                if(currentHeight + line.height() + leading > indentTop() - indentBottom())
                    newPage();
                indentation.indentLeft += paragraph.getIndentationLeft();
                indentation.indentRight += paragraph.getIndentationRight();
                carriageReturn();
                PdfPageEvent pageEvent = writer.getPageEvent();
                if(pageEvent != null && !isSectionTitle)
                    pageEvent.onParagraph(writer, this, indentTop() - currentHeight);
                if(paragraph.getKeepTogether())
                {
                    carriageReturn();
                    PdfPTable table = new PdfPTable(1);
                    table.setKeepTogether(paragraph.getKeepTogether());
                    table.setWidthPercentage(100F);
                    PdfPCell cell = new PdfPCell();
                    cell.addElement(paragraph);
                    cell.setBorder(0);
                    cell.setPadding(0.0F);
                    table.addCell(cell);
                    indentation.indentLeft -= paragraph.getIndentationLeft();
                    indentation.indentRight -= paragraph.getIndentationRight();
                    add(((Element) (table)));
                    indentation.indentLeft += paragraph.getIndentationLeft();
                    indentation.indentRight += paragraph.getIndentationRight();
                } else
                {
                    line.setExtraIndent(paragraph.getFirstLineIndent());
                    element.process(this);
                    carriageReturn();
                    addSpacing(paragraph.getSpacingAfter(), paragraph.getTotalLeading(), paragraph.getFont());
                }
                if(pageEvent != null && !isSectionTitle)
                    pageEvent.onParagraphEnd(writer, this, indentTop() - currentHeight);
                alignment = 0;
                indentation.indentLeft -= paragraph.getIndentationLeft();
                indentation.indentRight -= paragraph.getIndentationRight();
                carriageReturn();
                tabSettings = backupTabSettings;
                leadingCount--;
                if(isTagged(writer))
                {
                    flushLines();
                    text.closeMCBlock(paragraph);
                }
                break;
            }

            case 13: // '\r'
            case 16: // '\020'
            {
                Section section = (Section)element;
                PdfPageEvent pageEvent = writer.getPageEvent();
                boolean hasTitle = section.isNotAddedYet() && section.getTitle() != null;
                if(section.isTriggerNewPage())
                    newPage();
                if(hasTitle)
                {
                    float fith = indentTop() - currentHeight;
                    int rotation = pageSize.getRotation();
                    if(rotation == 90 || rotation == 180)
                        fith = pageSize.getHeight() - fith;
                    PdfDestination destination = new PdfDestination(2, fith);
                    for(; currentOutline.level() >= section.getDepth(); currentOutline = currentOutline.parent());
                    PdfOutline outline = new PdfOutline(currentOutline, destination, section.getBookmarkTitle(), section.isBookmarkOpen());
                    currentOutline = outline;
                }
                carriageReturn();
                indentation.sectionIndentLeft += section.getIndentationLeft();
                indentation.sectionIndentRight += section.getIndentationRight();
                if(section.isNotAddedYet() && pageEvent != null)
                    if(element.type() == 16)
                        pageEvent.onChapter(writer, this, indentTop() - currentHeight, section.getTitle());
                    else
                        pageEvent.onSection(writer, this, indentTop() - currentHeight, section.getDepth(), section.getTitle());
                if(hasTitle)
                {
                    isSectionTitle = true;
                    add(((Element) (section.getTitle())));
                    isSectionTitle = false;
                }
                indentation.sectionIndentLeft += section.getIndentation();
                element.process(this);
                flushLines();
                indentation.sectionIndentLeft -= section.getIndentationLeft() + section.getIndentation();
                indentation.sectionIndentRight -= section.getIndentationRight();
                if(!section.isComplete() || pageEvent == null)
                    break;
                if(element.type() == 16)
                    pageEvent.onChapterEnd(writer, this, indentTop() - currentHeight);
                else
                    pageEvent.onSectionEnd(writer, this, indentTop() - currentHeight);
                break;
            }

            case 14: // '\016'
            {
                List list = (List)element;
                if(isTagged(writer))
                {
                    flushLines();
                    text.openMCBlock(list);
                }
                if(list.isAlignindent())
                    list.normalizeIndentation();
                indentation.listIndentLeft += list.getIndentationLeft();
                indentation.indentRight += list.getIndentationRight();
                element.process(this);
                indentation.listIndentLeft -= list.getIndentationLeft();
                indentation.indentRight -= list.getIndentationRight();
                carriageReturn();
                if(isTagged(writer))
                {
                    flushLines();
                    text.closeMCBlock(list);
                }
                break;
            }

            case 15: // '\017'
            {
                leadingCount++;
                ListItem listItem = (ListItem)element;
                if(isTagged(writer))
                {
                    flushLines();
                    text.openMCBlock(listItem);
                }
                addSpacing(listItem.getSpacingBefore(), leading, listItem.getFont());
                alignment = listItem.getAlignment();
                indentation.listIndentLeft += listItem.getIndentationLeft();
                indentation.indentRight += listItem.getIndentationRight();
                leading = listItem.getTotalLeading();
                carriageReturn();
                line.setListItem(listItem);
                element.process(this);
                addSpacing(listItem.getSpacingAfter(), listItem.getTotalLeading(), listItem.getFont());
                if(line.hasToBeJustified())
                    line.resetAlignment();
                carriageReturn();
                indentation.listIndentLeft -= listItem.getIndentationLeft();
                indentation.indentRight -= listItem.getIndentationRight();
                leadingCount--;
                if(isTagged(writer))
                {
                    flushLines();
                    text.closeMCBlock(listItem.getListBody());
                    text.closeMCBlock(listItem);
                }
                break;
            }

            case 30: // '\036'
            {
                Rectangle rectangle = (Rectangle)element;
                graphics.rectangle(rectangle);
                pageEmpty = false;
                break;
            }

            case 23: // '\027'
            {
                PdfPTable ptable = (PdfPTable)element;
                if(ptable.size() > ptable.getHeaderRows())
                {
                    ensureNewLine();
                    flushLines();
                    addPTable(ptable);
                    pageEmpty = false;
                    newLine();
                }
                break;
            }

            case 32: // ' '
            case 33: // '!'
            case 34: // '"'
            case 35: // '#'
            case 36: // '$'
            {
                if(isTagged(writer))
                {
                    flushLines();
                    text.openMCBlock((Image)element);
                }
                add((Image)element);
                if(isTagged(writer))
                {
                    flushLines();
                    text.closeMCBlock((Image)element);
                }
                break;
            }

            case 55: // '7'
            {
                DrawInterface zh = (DrawInterface)element;
                zh.draw(graphics, indentLeft(), indentBottom(), indentRight(), indentTop(), indentTop() - currentHeight - (leadingCount <= 0 ? 0.0F : leading));
                pageEmpty = false;
                break;
            }

            case 50: // '2'
            {
                MarkedObject mo;
                if(element instanceof MarkedSection)
                {
                    mo = ((MarkedSection)element).getTitle();
                    if(mo != null)
                        mo.process(this);
                }
                mo = (MarkedObject)element;
                mo.process(this);
                break;
            }

            case 666: 
            {
                if(null != writer)
                    ((WriterOperation)element).write(writer, this);
                break;
            }

            case 37: // '%'
            {
                ensureNewLine();
                flushLines();
                addDiv((PdfDiv)element);
                pageEmpty = false;
                break;
            }

            default:
            {
                return false;
            }
            }
        }
        catch(Exception e)
        {
            throw new DocumentException(e);
        }
        lastElementType = element.type();
        return true;
    }

    public void open()
    {
        if(!open)
        {
            super.open();
            writer.open();
            rootOutline = new PdfOutline(writer);
            currentOutline = rootOutline;
        }
        try
        {
            initPage();
            if(isTagged(writer))
                openMCDocument = true;
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
    }

    public void close()
    {
        if(close)
            return;
        try
        {
            if(isTagged(writer))
            {
                flushFloatingElements();
                flushLines();
                writer.getDirectContent().closeMCBlock(this);
                writer.flushAcroFields();
                writer.flushTaggedObjects();
                if(isPageEmpty())
                {
                    int pageReferenceCount = writer.pageReferences.size();
                    if(pageReferenceCount > 0 && writer.currentPageNumber == pageReferenceCount)
                        writer.pageReferences.remove(pageReferenceCount - 1);
                }
            } else
            {
                writer.flushAcroFields();
            }
            boolean wasImage = imageWait != null;
            newPage();
            if(imageWait != null || wasImage)
                newPage();
            if(annotationsImp.hasUnusedAnnotations())
                throw new RuntimeException(MessageLocalization.getComposedMessage("not.all.annotations.could.be.added.to.the.document.the.document.doesn.t.have.enough.pages", new Object[0]));
            PdfPageEvent pageEvent = writer.getPageEvent();
            if(pageEvent != null)
                pageEvent.onCloseDocument(writer, this);
            super.close();
            writer.addLocalDestinations(localDestinations);
            calculateOutlineCount();
            writeOutlines();
        }
        catch(Exception e)
        {
            throw ExceptionConverter.convertException(e);
        }
        writer.close();
    }

    public void setXmpMetadata(byte xmpMetadata[])
        throws IOException
    {
        PdfStream xmp = new PdfStream(xmpMetadata);
        xmp.put(PdfName.TYPE, PdfName.METADATA);
        xmp.put(PdfName.SUBTYPE, PdfName.XML);
        PdfEncryption crypto = writer.getEncryption();
        if(crypto != null && !crypto.isMetadataEncrypted())
        {
            PdfArray ar = new PdfArray();
            ar.add(PdfName.CRYPT);
            xmp.put(PdfName.FILTER, ar);
        }
        writer.addPageDictEntry(PdfName.METADATA, writer.addToBody(xmp).getIndirectReference());
    }

    public boolean newPage()
    {
        try
        {
            flushFloatingElements();
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
        lastElementType = -1;
        if(isPageEmpty())
        {
            setNewPageSizeAndMargins();
            return false;
        }
        if(!open || close)
            throw new RuntimeException(MessageLocalization.getComposedMessage("the.document.is.not.open", new Object[0]));
        PdfPageEvent pageEvent = writer.getPageEvent();
        if(pageEvent != null)
            pageEvent.onEndPage(writer, this);
        super.newPage();
        indentation.imageIndentLeft = 0.0F;
        indentation.imageIndentRight = 0.0F;
        try
        {
            flushLines();
            int rotation = pageSize.getRotation();
            if(writer.isPdfIso())
            {
                if(thisBoxSize.containsKey("art") && thisBoxSize.containsKey("trim"))
                    throw new PdfXConformanceException(MessageLocalization.getComposedMessage("only.one.of.artbox.or.trimbox.can.exist.in.the.page", new Object[0]));
                if(!thisBoxSize.containsKey("art") && !thisBoxSize.containsKey("trim"))
                    if(thisBoxSize.containsKey("crop"))
                        thisBoxSize.put("trim", thisBoxSize.get("crop"));
                    else
                        thisBoxSize.put("trim", new PdfRectangle(pageSize, pageSize.getRotation()));
            }
            pageResources.addDefaultColorDiff(writer.getDefaultColorspace());
            if(writer.isRgbTransparencyBlending())
            {
                PdfDictionary dcs = new PdfDictionary();
                dcs.put(PdfName.CS, PdfName.DEVICERGB);
                pageResources.addDefaultColorDiff(dcs);
            }
            PdfDictionary resources = pageResources.getResources();
            PdfPage page = new PdfPage(new PdfRectangle(pageSize, rotation), thisBoxSize, resources, rotation);
            if(isTagged(writer))
                page.put(PdfName.TABS, PdfName.S);
            else
                page.put(PdfName.TABS, writer.getTabs());
            page.putAll(writer.getPageDictEntries());
            writer.resetPageDictEntries();
            if(pageAA != null)
            {
                page.put(PdfName.AA, writer.addToBody(pageAA).getIndirectReference());
                pageAA = null;
            }
            if(annotationsImp.hasUnusedAnnotations())
            {
                PdfArray array = annotationsImp.rotateAnnotations(writer, pageSize);
                if(array.size() != 0)
                    page.put(PdfName.ANNOTS, array);
            }
            if(isTagged(writer))
                page.put(PdfName.STRUCTPARENTS, new PdfNumber(getStructParentIndex(writer.getCurrentPage())));
            if(text.size() > textEmptySize || isTagged(writer))
                text.endText();
            else
                text = null;
            ArrayList mcBlocks = null;
            if(isTagged(writer))
                mcBlocks = writer.getDirectContent().saveMCBlocks();
            writer.add(page, new PdfContents(writer.getDirectContentUnder(), graphics, isTagged(writer) ? null : text, writer.getDirectContent(), pageSize));
            initPage();
            if(isTagged(writer))
                writer.getDirectContentUnder().restoreMCBlocks(mcBlocks);
        }
        catch(DocumentException de)
        {
            throw new ExceptionConverter(de);
        }
        catch(IOException ioe)
        {
            throw new ExceptionConverter(ioe);
        }
        return true;
    }

    public boolean setPageSize(Rectangle pageSize)
    {
        if(writer != null && writer.isPaused())
        {
            return false;
        } else
        {
            nextPageSize = new Rectangle(pageSize);
            return true;
        }
    }

    public boolean setMargins(float marginLeft, float marginRight, float marginTop, float marginBottom)
    {
        if(writer != null && writer.isPaused())
        {
            return false;
        } else
        {
            nextMarginLeft = marginLeft;
            nextMarginRight = marginRight;
            nextMarginTop = marginTop;
            nextMarginBottom = marginBottom;
            return true;
        }
    }

    public boolean setMarginMirroring(boolean MarginMirroring)
    {
        if(writer != null && writer.isPaused())
            return false;
        else
            return super.setMarginMirroring(MarginMirroring);
    }

    public boolean setMarginMirroringTopBottom(boolean MarginMirroringTopBottom)
    {
        if(writer != null && writer.isPaused())
            return false;
        else
            return super.setMarginMirroringTopBottom(MarginMirroringTopBottom);
    }

    public void setPageCount(int pageN)
    {
        if(writer != null && writer.isPaused())
        {
            return;
        } else
        {
            super.setPageCount(pageN);
            return;
        }
    }

    public void resetPageCount()
    {
        if(writer != null && writer.isPaused())
        {
            return;
        } else
        {
            super.resetPageCount();
            return;
        }
    }

    protected void initPage()
        throws DocumentException
    {
        pageN++;
        annotationsImp.resetAnnotations();
        pageResources = new PageResources();
        writer.resetContent();
        if(isTagged(writer))
        {
            graphics = writer.getDirectContentUnder().getDuplicate();
            writer.getDirectContent().duplicatedFrom = graphics;
        } else
        {
            graphics = new PdfContentByte(writer);
        }
        setNewPageSizeAndMargins();
        imageEnd = -1F;
        indentation.imageIndentRight = 0.0F;
        indentation.imageIndentLeft = 0.0F;
        indentation.indentBottom = 0.0F;
        indentation.indentTop = 0.0F;
        currentHeight = 0.0F;
        thisBoxSize = new HashMap(boxSize);
        if(pageSize.getBackgroundColor() != null || pageSize.hasBorders() || pageSize.getBorderColor() != null)
            add(pageSize);
        float oldleading = leading;
        int oldAlignment = alignment;
        pageEmpty = true;
        try
        {
            if(imageWait != null)
            {
                add(imageWait);
                imageWait = null;
            }
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        leading = oldleading;
        alignment = oldAlignment;
        carriageReturn();
        PdfPageEvent pageEvent = writer.getPageEvent();
        if(pageEvent != null)
        {
            if(firstPageEvent)
                pageEvent.onOpenDocument(writer, this);
            pageEvent.onStartPage(writer, this);
        }
        firstPageEvent = false;
    }

    protected void newLine()
        throws DocumentException
    {
        lastElementType = -1;
        carriageReturn();
        if(lines != null && !lines.isEmpty())
        {
            lines.add(line);
            currentHeight += line.height();
        }
        line = new PdfLine(indentLeft(), indentRight(), alignment, leading);
    }

    protected void carriageReturn()
    {
        if(lines == null)
            lines = new ArrayList();
        if(line != null && line.size() > 0)
        {
            if(currentHeight + line.height() + leading > indentTop() - indentBottom())
            {
                PdfLine overflowLine = line;
                line = null;
                newPage();
                line = overflowLine;
                overflowLine.left = indentLeft();
            }
            currentHeight += line.height();
            lines.add(line);
            pageEmpty = false;
        }
        if(imageEnd > -1F && currentHeight > imageEnd)
        {
            imageEnd = -1F;
            indentation.imageIndentRight = 0.0F;
            indentation.imageIndentLeft = 0.0F;
        }
        line = new PdfLine(indentLeft(), indentRight(), alignment, leading);
    }

    public float getVerticalPosition(boolean ensureNewLine)
    {
        if(ensureNewLine)
            ensureNewLine();
        return top() - currentHeight - indentation.indentTop;
    }

    protected void ensureNewLine()
    {
        try
        {
            if(lastElementType == 11 || lastElementType == 10)
            {
                newLine();
                flushLines();
            }
        }
        catch(DocumentException ex)
        {
            throw new ExceptionConverter(ex);
        }
    }

    protected float flushLines()
        throws DocumentException
    {
        if(lines == null)
            return 0.0F;
        if(line != null && line.size() > 0)
        {
            lines.add(line);
            line = new PdfLine(indentLeft(), indentRight(), alignment, leading);
        }
        if(lines.isEmpty())
            return 0.0F;
        Object currentValues[] = new Object[2];
        PdfFont currentFont = null;
        float displacement = 0.0F;
        Float lastBaseFactor = new Float(0.0F);
        currentValues[1] = lastBaseFactor;
        float moveTextX;
        for(Iterator i$ = lines.iterator(); i$.hasNext(); text.moveText(-moveTextX, 0.0F))
        {
            PdfLine l = (PdfLine)i$.next();
            moveTextX = (l.indentLeft() - indentLeft()) + indentation.indentLeft + indentation.listIndentLeft + indentation.sectionIndentLeft;
            text.moveText(moveTextX, -l.height());
            l.flush();
            if(l.listSymbol() != null)
            {
                ListLabel lbl = null;
                Chunk symbol = l.listSymbol();
                if(isTagged(writer))
                {
                    lbl = l.listItem().getListLabel();
                    graphics.openMCBlock(lbl);
                    symbol = new Chunk(symbol);
                    if(!lbl.getTagLabelContent())
                        symbol.setRole(null);
                }
                ColumnText.showTextAligned(graphics, 0, new Phrase(symbol), text.getXTLM() - l.listIndent(), text.getYTLM(), 0.0F);
                if(lbl != null)
                    graphics.closeMCBlock(lbl);
            }
            currentValues[0] = currentFont;
            if(isTagged(writer) && l.listItem() != null)
                text.openMCBlock(l.listItem().getListBody());
            writeLineToContent(l, text, graphics, currentValues, writer.getSpaceCharRatio());
            currentFont = (PdfFont)currentValues[0];
            displacement += l.height();
        }

        lines = new ArrayList();
        return displacement;
    }

    float writeLineToContent(PdfLine line, PdfContentByte text, PdfContentByte graphics, Object currentValues[], float ratio)
        throws DocumentException
    {
        PdfFont currentFont = (PdfFont)currentValues[0];
        float lastBaseFactor = ((Float)currentValues[1]).floatValue();
        float hangingCorrection = 0.0F;
        float hScale = 1.0F;
        float lastHScale = (0.0F / 0.0F);
        float baseWordSpacing = 0.0F;
        float baseCharacterSpacing = 0.0F;
        float glueWidth = 0.0F;
        float lastX = text.getXTLM() + line.getOriginalWidth();
        int numberOfSpaces = line.numberOfSpaces();
        int lineLen = line.getLineLengthUtf32();
        boolean isJustified = line.hasToBeJustified() && (numberOfSpaces != 0 || lineLen > 1);
        int separatorCount = line.getSeparatorCount();
        if(separatorCount > 0)
            glueWidth = line.widthLeft() / (float)separatorCount;
        else
        if(isJustified && separatorCount == 0)
        {
            if(line.isNewlineSplit() && line.widthLeft() >= lastBaseFactor * ((ratio * (float)numberOfSpaces + (float)lineLen) - 1.0F))
            {
                if(line.isRTL())
                    text.moveText(line.widthLeft() - lastBaseFactor * ((ratio * (float)numberOfSpaces + (float)lineLen) - 1.0F), 0.0F);
                baseWordSpacing = ratio * lastBaseFactor;
                baseCharacterSpacing = lastBaseFactor;
            } else
            {
                float width = line.widthLeft();
                PdfChunk last = line.getChunk(line.size() - 1);
                if(last != null)
                {
                    String s = last.toString();
                    char c;
                    if(s.length() > 0 && ".,;:'".indexOf(c = s.charAt(s.length() - 1)) >= 0)
                    {
                        float oldWidth = width;
                        width += last.font().width(c) * 0.4F;
                        hangingCorrection = width - oldWidth;
                    }
                }
                float baseFactor = width / ((ratio * (float)numberOfSpaces + (float)lineLen) - 1.0F);
                baseWordSpacing = ratio * baseFactor;
                baseCharacterSpacing = baseFactor;
                lastBaseFactor = baseFactor;
            }
        } else
        if(line.alignment == 0 || line.alignment == -1)
            lastX -= line.widthLeft();
        int lastChunkStroke = line.getLastStrokeChunk();
        int chunkStrokeIdx = 0;
        float xMarker = text.getXTLM();
        float baseXMarker = xMarker;
        float yMarker = text.getYTLM();
        boolean adjustMatrix = false;
        float tabPosition = 0.0F;
        Iterator j = line.iterator();
        do
        {
            if(!j.hasNext())
                break;
            PdfChunk chunk = (PdfChunk)j.next();
            if(isTagged(writer) && chunk.accessibleElement != null)
                text.openMCBlock(chunk.accessibleElement);
            BaseColor color = chunk.color();
            float fontSize = chunk.font().size();
            float ascender;
            float descender;
            if(chunk.isImage())
            {
                ascender = chunk.height();
                descender = 0.0F;
            } else
            {
                ascender = chunk.font().getFont().getFontDescriptor(1, fontSize);
                descender = chunk.font().getFont().getFontDescriptor(3, fontSize);
            }
            hScale = 1.0F;
            if(chunkStrokeIdx <= lastChunkStroke)
            {
                float width;
                if(isJustified)
                    width = chunk.getWidthCorrected(baseCharacterSpacing, baseWordSpacing);
                else
                    width = chunk.width();
                if(chunk.isStroked())
                {
                    PdfChunk nextChunk = line.getChunk(chunkStrokeIdx + 1);
                    if(chunk.isSeparator())
                    {
                        width = glueWidth;
                        Object sep[] = (Object[])(Object[])chunk.getAttribute("SEPARATOR");
                        DrawInterface di = (DrawInterface)sep[0];
                        Boolean vertical = (Boolean)sep[1];
                        if(vertical.booleanValue())
                            di.draw(graphics, baseXMarker, yMarker + descender, baseXMarker + line.getOriginalWidth(), ascender - descender, yMarker);
                        else
                            di.draw(graphics, xMarker, yMarker + descender, xMarker + width, ascender - descender, yMarker);
                    }
                    if(chunk.isTab())
                    {
                        if(chunk.isAttribute("TABSETTINGS"))
                        {
                            TabStop tabStop = chunk.getTabStop();
                            if(tabStop != null)
                            {
                                tabPosition = tabStop.getPosition() + baseXMarker;
                                if(tabStop.getLeader() != null)
                                    tabStop.getLeader().draw(graphics, xMarker, yMarker + descender, tabPosition, ascender - descender, yMarker);
                            } else
                            {
                                tabPosition = xMarker;
                            }
                        } else
                        {
                            Object tab[] = (Object[])(Object[])chunk.getAttribute("TAB");
                            DrawInterface di = (DrawInterface)tab[0];
                            tabPosition = ((Float)tab[1]).floatValue() + ((Float)tab[3]).floatValue();
                            if(tabPosition > xMarker)
                                di.draw(graphics, xMarker, yMarker + descender, tabPosition, ascender - descender, yMarker);
                        }
                        float tmp = xMarker;
                        xMarker = tabPosition;
                        tabPosition = tmp;
                    }
                    if(chunk.isAttribute("BACKGROUND"))
                    {
                        boolean inText = graphics.getInText();
                        if(inText && isTagged(writer))
                            graphics.endText();
                        float subtract = lastBaseFactor;
                        if(nextChunk != null && nextChunk.isAttribute("BACKGROUND"))
                            subtract = 0.0F;
                        if(nextChunk == null)
                            subtract += hangingCorrection;
                        Object bgr[] = (Object[])(Object[])chunk.getAttribute("BACKGROUND");
                        graphics.setColorFill((BaseColor)bgr[0]);
                        float extra[] = (float[])(float[])bgr[1];
                        graphics.rectangle(xMarker - extra[0], ((yMarker + descender) - extra[1]) + chunk.getTextRise(), (width - subtract) + extra[0] + extra[2], (ascender - descender) + extra[1] + extra[3]);
                        graphics.fill();
                        graphics.setGrayFill(0.0F);
                        if(inText && isTagged(writer))
                            graphics.beginText(true);
                    }
                    if(chunk.isAttribute("UNDERLINE"))
                    {
                        boolean inText = graphics.getInText();
                        if(inText && isTagged(writer))
                            graphics.endText();
                        float subtract = lastBaseFactor;
                        if(nextChunk != null && nextChunk.isAttribute("UNDERLINE"))
                            subtract = 0.0F;
                        if(nextChunk == null)
                            subtract += hangingCorrection;
                        Object unders[][] = (Object[][])(Object[][])chunk.getAttribute("UNDERLINE");
                        BaseColor scolor = null;
                        for(int k = 0; k < unders.length; k++)
                        {
                            Object obj[] = unders[k];
                            scolor = (BaseColor)obj[0];
                            float ps[] = (float[])(float[])obj[1];
                            if(scolor == null)
                                scolor = color;
                            if(scolor != null)
                                graphics.setColorStroke(scolor);
                            graphics.setLineWidth(ps[0] + fontSize * ps[1]);
                            float shift = ps[2] + fontSize * ps[3];
                            int cap2 = (int)ps[4];
                            if(cap2 != 0)
                                graphics.setLineCap(cap2);
                            graphics.moveTo(xMarker, yMarker + shift);
                            graphics.lineTo((xMarker + width) - subtract, yMarker + shift);
                            graphics.stroke();
                            if(scolor != null)
                                graphics.resetGrayStroke();
                            if(cap2 != 0)
                                graphics.setLineCap(0);
                        }

                        graphics.setLineWidth(1.0F);
                        if(inText && isTagged(writer))
                            graphics.beginText(true);
                    }
                    if(chunk.isAttribute("ACTION"))
                    {
                        float subtract = lastBaseFactor;
                        if(nextChunk != null && nextChunk.isAttribute("ACTION"))
                            subtract = 0.0F;
                        if(nextChunk == null)
                            subtract += hangingCorrection;
                        PdfAnnotation annot = null;
                        if(chunk.isImage())
                            annot = new PdfAnnotation(writer, xMarker, yMarker + chunk.getImageOffsetY(), (xMarker + width) - subtract, yMarker + chunk.getImageHeight() + chunk.getImageOffsetY(), (PdfAction)chunk.getAttribute("ACTION"));
                        else
                            annot = new PdfAnnotation(writer, xMarker, yMarker + descender + chunk.getTextRise(), (xMarker + width) - subtract, yMarker + ascender + chunk.getTextRise(), (PdfAction)chunk.getAttribute("ACTION"));
                        text.addAnnotation(annot, true);
                        if(isTagged(writer) && chunk.accessibleElement != null)
                        {
                            int structParent = getStructParentIndex(annot);
                            annot.put(PdfName.STRUCTPARENT, new PdfNumber(structParent));
                            PdfStructureElement strucElem = (PdfStructureElement)structElements.get(chunk.accessibleElement.getId());
                            if(strucElem != null)
                            {
                                PdfArray kArray = strucElem.getAsArray(PdfName.K);
                                if(kArray == null)
                                {
                                    kArray = new PdfArray();
                                    PdfObject k = strucElem.get(PdfName.K);
                                    if(k != null)
                                        kArray.add(k);
                                    strucElem.put(PdfName.K, kArray);
                                }
                                PdfDictionary dict = new PdfDictionary();
                                dict.put(PdfName.TYPE, PdfName.OBJR);
                                dict.put(PdfName.OBJ, annot.getIndirectReference());
                                kArray.add(dict);
                                writer.getStructureTreeRoot().setAnnotationMark(structParent, strucElem.getReference());
                            }
                        }
                    }
                    if(chunk.isAttribute("REMOTEGOTO"))
                    {
                        float subtract = lastBaseFactor;
                        if(nextChunk != null && nextChunk.isAttribute("REMOTEGOTO"))
                            subtract = 0.0F;
                        if(nextChunk == null)
                            subtract += hangingCorrection;
                        Object obj[] = (Object[])(Object[])chunk.getAttribute("REMOTEGOTO");
                        String filename = (String)obj[0];
                        if(obj[1] instanceof String)
                            remoteGoto(filename, (String)obj[1], xMarker, yMarker + descender + chunk.getTextRise(), (xMarker + width) - subtract, yMarker + ascender + chunk.getTextRise());
                        else
                            remoteGoto(filename, ((Integer)obj[1]).intValue(), xMarker, yMarker + descender + chunk.getTextRise(), (xMarker + width) - subtract, yMarker + ascender + chunk.getTextRise());
                    }
                    if(chunk.isAttribute("LOCALGOTO"))
                    {
                        float subtract = lastBaseFactor;
                        if(nextChunk != null && nextChunk.isAttribute("LOCALGOTO"))
                            subtract = 0.0F;
                        if(nextChunk == null)
                            subtract += hangingCorrection;
                        localGoto((String)chunk.getAttribute("LOCALGOTO"), xMarker, yMarker, (xMarker + width) - subtract, yMarker + fontSize);
                    }
                    if(chunk.isAttribute("LOCALDESTINATION"))
                        localDestination((String)chunk.getAttribute("LOCALDESTINATION"), new PdfDestination(0, xMarker, yMarker + fontSize, 0.0F));
                    if(chunk.isAttribute("GENERICTAG"))
                    {
                        float subtract = lastBaseFactor;
                        if(nextChunk != null && nextChunk.isAttribute("GENERICTAG"))
                            subtract = 0.0F;
                        if(nextChunk == null)
                            subtract += hangingCorrection;
                        Rectangle rect = new Rectangle(xMarker, yMarker, (xMarker + width) - subtract, yMarker + fontSize);
                        PdfPageEvent pev = writer.getPageEvent();
                        if(pev != null)
                            pev.onGenericTag(writer, this, rect, (String)chunk.getAttribute("GENERICTAG"));
                    }
                    if(chunk.isAttribute("PDFANNOTATION"))
                    {
                        float subtract = lastBaseFactor;
                        if(nextChunk != null && nextChunk.isAttribute("PDFANNOTATION"))
                            subtract = 0.0F;
                        if(nextChunk == null)
                            subtract += hangingCorrection;
                        PdfAnnotation annot = PdfFormField.shallowDuplicate((PdfAnnotation)chunk.getAttribute("PDFANNOTATION"));
                        annot.put(PdfName.RECT, new PdfRectangle(xMarker, yMarker + descender, (xMarker + width) - subtract, yMarker + ascender));
                        text.addAnnotation(annot, true);
                    }
                    float params[] = (float[])(float[])chunk.getAttribute("SKEW");
                    Float hs = (Float)chunk.getAttribute("HSCALE");
                    if(params != null || hs != null)
                    {
                        float b = 0.0F;
                        float c = 0.0F;
                        if(params != null)
                        {
                            b = params[0];
                            c = params[1];
                        }
                        if(hs != null)
                            hScale = hs.floatValue();
                        text.setTextMatrix(hScale, b, c, 1.0F, xMarker, yMarker);
                    }
                    if(!isJustified && chunk.isAttribute("WORD_SPACING"))
                    {
                        Float ws = (Float)chunk.getAttribute("WORD_SPACING");
                        text.setWordSpacing(ws.floatValue());
                    }
                    if(chunk.isAttribute("CHAR_SPACING"))
                    {
                        Float cs = (Float)chunk.getAttribute("CHAR_SPACING");
                        text.setCharacterSpacing(cs.floatValue());
                    }
                    if(chunk.isImage())
                    {
                        Image image = chunk.getImage();
                        width = chunk.getImageWidth();
                        float matrix[] = image.matrix(chunk.getImageScalePercentage());
                        matrix[4] = (xMarker + chunk.getImageOffsetX()) - matrix[4];
                        matrix[5] = (yMarker + chunk.getImageOffsetY()) - matrix[5];
                        graphics.addImage(image, matrix[0], matrix[1], matrix[2], matrix[3], matrix[4], matrix[5]);
                        text.moveText((xMarker + lastBaseFactor + chunk.getImageWidth()) - text.getXTLM(), 0.0F);
                    }
                }
                xMarker += width;
                chunkStrokeIdx++;
            }
            if(!chunk.isImage() && chunk.font().compareTo(currentFont) != 0)
            {
                currentFont = chunk.font();
                text.setFontAndSize(currentFont.getFont(), currentFont.size());
            }
            float rise = 0.0F;
            Object textRender[] = (Object[])(Object[])chunk.getAttribute("TEXTRENDERMODE");
            int tr = 0;
            float strokeWidth = 1.0F;
            BaseColor strokeColor = null;
            Float fr = (Float)chunk.getAttribute("SUBSUPSCRIPT");
            if(textRender != null)
            {
                tr = ((Integer)textRender[0]).intValue() & 3;
                if(tr != 0)
                    text.setTextRenderingMode(tr);
                if(tr == 1 || tr == 2)
                {
                    strokeWidth = ((Float)textRender[1]).floatValue();
                    if(strokeWidth != 1.0F)
                        text.setLineWidth(strokeWidth);
                    strokeColor = (BaseColor)textRender[2];
                    if(strokeColor == null)
                        strokeColor = color;
                    if(strokeColor != null)
                        text.setColorStroke(strokeColor);
                }
            }
            if(fr != null)
                rise = fr.floatValue();
            if(color != null)
                text.setColorFill(color);
            if(rise != 0.0F)
                text.setTextRise(rise);
            if(chunk.isImage())
                adjustMatrix = true;
            else
            if(chunk.isHorizontalSeparator())
            {
                PdfTextArray array = new PdfTextArray();
                array.add((-glueWidth * 1000F) / chunk.font.size() / hScale);
                text.showText(array);
            } else
            if(chunk.isTab() && tabPosition != xMarker)
            {
                PdfTextArray array = new PdfTextArray();
                array.add(((tabPosition - xMarker) * 1000F) / chunk.font.size() / hScale);
                text.showText(array);
            } else
            if(isJustified && numberOfSpaces > 0 && chunk.isSpecialEncoding())
            {
                if(hScale != lastHScale)
                {
                    lastHScale = hScale;
                    text.setWordSpacing(baseWordSpacing / hScale);
                    text.setCharacterSpacing(baseCharacterSpacing / hScale + text.getCharacterSpacing());
                }
                String s = chunk.toString();
                int idx = s.indexOf(' ');
                if(idx < 0)
                {
                    text.showText(s);
                } else
                {
                    float spaceCorrection = (-baseWordSpacing * 1000F) / chunk.font.size() / hScale;
                    PdfTextArray textArray = new PdfTextArray(s.substring(0, idx));
                    int lastIdx;
                    for(lastIdx = idx; (idx = s.indexOf(' ', lastIdx + 1)) >= 0; lastIdx = idx)
                    {
                        textArray.add(spaceCorrection);
                        textArray.add(s.substring(lastIdx, idx));
                    }

                    textArray.add(spaceCorrection);
                    textArray.add(s.substring(lastIdx));
                    text.showText(textArray);
                }
            } else
            {
                if(isJustified && hScale != lastHScale)
                {
                    lastHScale = hScale;
                    text.setWordSpacing(baseWordSpacing / hScale);
                    text.setCharacterSpacing(baseCharacterSpacing / hScale + text.getCharacterSpacing());
                }
                text.showText(chunk.toString());
            }
            if(rise != 0.0F)
                text.setTextRise(0.0F);
            if(color != null)
                text.resetRGBColorFill();
            if(tr != 0)
                text.setTextRenderingMode(0);
            if(strokeColor != null)
                text.resetRGBColorStroke();
            if(strokeWidth != 1.0F)
                text.setLineWidth(1.0F);
            if(chunk.isAttribute("SKEW") || chunk.isAttribute("HSCALE"))
            {
                adjustMatrix = true;
                text.setTextMatrix(xMarker, yMarker);
            }
            if(chunk.isAttribute("CHAR_SPACING"))
                text.setCharacterSpacing(baseCharacterSpacing);
            if(chunk.isAttribute("WORD_SPACING"))
                text.setWordSpacing(baseWordSpacing);
            if(isTagged(writer) && chunk.accessibleElement != null)
                text.closeMCBlock(chunk.accessibleElement);
        } while(true);
        if(isJustified)
        {
            text.setWordSpacing(0.0F);
            text.setCharacterSpacing(0.0F);
            if(line.isNewlineSplit())
                lastBaseFactor = 0.0F;
        }
        if(adjustMatrix)
            text.moveText(baseXMarker - text.getXTLM(), 0.0F);
        currentValues[0] = currentFont;
        currentValues[1] = new Float(lastBaseFactor);
        return lastX;
    }

    protected float indentLeft()
    {
        return left(indentation.indentLeft + indentation.listIndentLeft + indentation.imageIndentLeft + indentation.sectionIndentLeft);
    }

    protected float indentRight()
    {
        return right(indentation.indentRight + indentation.sectionIndentRight + indentation.imageIndentRight);
    }

    protected float indentTop()
    {
        return top(indentation.indentTop);
    }

    float indentBottom()
    {
        return bottom(indentation.indentBottom);
    }

    protected void addSpacing(float extraspace, float oldleading, Font f)
    {
        if(extraspace == 0.0F)
            return;
        if(pageEmpty)
            return;
        if(currentHeight + line.height() + leading > indentTop() - indentBottom())
            return;
        leading = extraspace;
        carriageReturn();
        if(f.isUnderlined() || f.isStrikethru())
        {
            f = new Font(f);
            int style = f.getStyle();
            style &= -5;
            style &= -9;
            f.setStyle(style);
        }
        Chunk space = new Chunk(" ", f);
        space.process(this);
        carriageReturn();
        leading = oldleading;
    }

    PdfInfo getInfo()
    {
        return info;
    }

    PdfCatalog getCatalog(PdfIndirectReference pages)
    {
        PdfCatalog catalog = new PdfCatalog(pages, writer);
        if(rootOutline.getKids().size() > 0)
        {
            catalog.put(PdfName.PAGEMODE, PdfName.USEOUTLINES);
            catalog.put(PdfName.OUTLINES, rootOutline.indirectReference());
        }
        writer.getPdfVersion().addToCatalog(catalog);
        viewerPreferences.addToCatalog(catalog);
        if(pageLabels != null)
            catalog.put(PdfName.PAGELABELS, pageLabels.getDictionary(writer));
        catalog.addNames(localDestinations, getDocumentLevelJS(), documentFileAttachment, writer);
        if(openActionName != null)
        {
            PdfAction action = getLocalGotoAction(openActionName);
            catalog.setOpenAction(action);
        } else
        if(openActionAction != null)
            catalog.setOpenAction(openActionAction);
        if(additionalActions != null)
            catalog.setAdditionalActions(additionalActions);
        if(collection != null)
            catalog.put(PdfName.COLLECTION, collection);
        if(annotationsImp.hasValidAcroForm())
            try
            {
                catalog.put(PdfName.ACROFORM, writer.addToBody(annotationsImp.getAcroForm()).getIndirectReference());
            }
            catch(IOException e)
            {
                throw new ExceptionConverter(e);
            }
        if(language != null)
            catalog.put(PdfName.LANG, language);
        return catalog;
    }

    void addOutline(PdfOutline outline, String name)
    {
        localDestination(name, outline.getPdfDestination());
    }

    public PdfOutline getRootOutline()
    {
        return rootOutline;
    }

    void calculateOutlineCount()
    {
        if(rootOutline.getKids().size() == 0)
        {
            return;
        } else
        {
            traverseOutlineCount(rootOutline);
            return;
        }
    }

    void traverseOutlineCount(PdfOutline outline)
    {
        ArrayList kids = outline.getKids();
        PdfOutline parent = outline.parent();
        if(kids.isEmpty())
        {
            if(parent != null)
                parent.setCount(parent.getCount() + 1);
        } else
        {
            for(int k = 0; k < kids.size(); k++)
                traverseOutlineCount((PdfOutline)kids.get(k));

            if(parent != null)
                if(outline.isOpen())
                {
                    parent.setCount(outline.getCount() + parent.getCount() + 1);
                } else
                {
                    parent.setCount(parent.getCount() + 1);
                    outline.setCount(-outline.getCount());
                }
        }
    }

    void writeOutlines()
        throws IOException
    {
        if(rootOutline.getKids().size() == 0)
        {
            return;
        } else
        {
            outlineTree(rootOutline);
            writer.addToBody(rootOutline, rootOutline.indirectReference());
            return;
        }
    }

    void outlineTree(PdfOutline outline)
        throws IOException
    {
        outline.setIndirectReference(writer.getPdfIndirectReference());
        if(outline.parent() != null)
            outline.put(PdfName.PARENT, outline.parent().indirectReference());
        ArrayList kids = outline.getKids();
        int size = kids.size();
        for(int k = 0; k < size; k++)
            outlineTree((PdfOutline)kids.get(k));

        for(int k = 0; k < size; k++)
        {
            if(k > 0)
                ((PdfOutline)kids.get(k)).put(PdfName.PREV, ((PdfOutline)kids.get(k - 1)).indirectReference());
            if(k < size - 1)
                ((PdfOutline)kids.get(k)).put(PdfName.NEXT, ((PdfOutline)kids.get(k + 1)).indirectReference());
        }

        if(size > 0)
        {
            outline.put(PdfName.FIRST, ((PdfOutline)kids.get(0)).indirectReference());
            outline.put(PdfName.LAST, ((PdfOutline)kids.get(size - 1)).indirectReference());
        }
        for(int k = 0; k < size; k++)
        {
            PdfOutline kid = (PdfOutline)kids.get(k);
            writer.addToBody(kid, kid.indirectReference());
        }

    }

    void setViewerPreferences(int preferences)
    {
        viewerPreferences.setViewerPreferences(preferences);
    }

    void addViewerPreference(PdfName key, PdfObject value)
    {
        viewerPreferences.addViewerPreference(key, value);
    }

    void setPageLabels(PdfPageLabels pageLabels)
    {
        this.pageLabels = pageLabels;
    }

    public PdfPageLabels getPageLabels()
    {
        return pageLabels;
    }

    void localGoto(String name, float llx, float lly, float urx, float ury)
    {
        PdfAction action = getLocalGotoAction(name);
        annotationsImp.addPlainAnnotation(new PdfAnnotation(writer, llx, lly, urx, ury, action));
    }

    void remoteGoto(String filename, String name, float llx, float lly, float urx, float ury)
    {
        annotationsImp.addPlainAnnotation(new PdfAnnotation(writer, llx, lly, urx, ury, new PdfAction(filename, name)));
    }

    void remoteGoto(String filename, int page, float llx, float lly, float urx, float ury)
    {
        addAnnotation(new PdfAnnotation(writer, llx, lly, urx, ury, new PdfAction(filename, page)));
    }

    void setAction(PdfAction action, float llx, float lly, float urx, float ury)
    {
        addAnnotation(new PdfAnnotation(writer, llx, lly, urx, ury, action));
    }

    PdfAction getLocalGotoAction(String name)
    {
        Destination dest = (Destination)localDestinations.get(name);
        if(dest == null)
            dest = new Destination();
        PdfAction action;
        if(dest.action == null)
        {
            if(dest.reference == null)
                dest.reference = writer.getPdfIndirectReference();
            action = new PdfAction(dest.reference);
            dest.action = action;
            localDestinations.put(name, dest);
        } else
        {
            action = dest.action;
        }
        return action;
    }

    boolean localDestination(String name, PdfDestination destination)
    {
        Destination dest = (Destination)localDestinations.get(name);
        if(dest == null)
            dest = new Destination();
        if(dest.destination != null)
            return false;
        dest.destination = destination;
        localDestinations.put(name, dest);
        if(!destination.hasPage())
            destination.addPage(writer.getCurrentPage());
        return true;
    }

    void addJavaScript(PdfAction js)
    {
        if(js.get(PdfName.JS) == null)
            throw new RuntimeException(MessageLocalization.getComposedMessage("only.javascript.actions.are.allowed", new Object[0]));
        try
        {
            documentLevelJS.put(SIXTEEN_DIGITS.format(jsCounter++), writer.addToBody(js).getIndirectReference());
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    void addJavaScript(String name, PdfAction js)
    {
        if(js.get(PdfName.JS) == null)
            throw new RuntimeException(MessageLocalization.getComposedMessage("only.javascript.actions.are.allowed", new Object[0]));
        try
        {
            documentLevelJS.put(name, writer.addToBody(js).getIndirectReference());
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    HashMap getDocumentLevelJS()
    {
        return documentLevelJS;
    }

    void addFileAttachment(String description, PdfFileSpecification fs)
        throws IOException
    {
        if(description == null)
        {
            PdfString desc = (PdfString)fs.get(PdfName.DESC);
            if(desc == null)
                description = "";
            else
                description = PdfEncodings.convertToString(desc.getBytes(), null);
        }
        fs.addDescription(description, true);
        if(description.length() == 0)
            description = "Unnamed";
        String fn = PdfEncodings.convertToString((new PdfString(description, "UnicodeBig")).getBytes(), null);
        int k = 0;
        for(; documentFileAttachment.containsKey(fn); fn = PdfEncodings.convertToString((new PdfString((new StringBuilder()).append(description).append(" ").append(k).toString(), "UnicodeBig")).getBytes(), null))
            k++;

        documentFileAttachment.put(fn, fs.getReference());
    }

    HashMap getDocumentFileAttachment()
    {
        return documentFileAttachment;
    }

    void setOpenAction(String name)
    {
        openActionName = name;
        openActionAction = null;
    }

    void setOpenAction(PdfAction action)
    {
        openActionAction = action;
        openActionName = null;
    }

    void addAdditionalAction(PdfName actionType, PdfAction action)
    {
        if(additionalActions == null)
            additionalActions = new PdfDictionary();
        if(action == null)
            additionalActions.remove(actionType);
        else
            additionalActions.put(actionType, action);
        if(additionalActions.size() == 0)
            additionalActions = null;
    }

    public void setCollection(PdfCollection collection)
    {
        this.collection = collection;
    }

    PdfAcroForm getAcroForm()
    {
        return annotationsImp.getAcroForm();
    }

    void setSigFlags(int f)
    {
        annotationsImp.setSigFlags(f);
    }

    void addCalculationOrder(PdfFormField formField)
    {
        annotationsImp.addCalculationOrder(formField);
    }

    void addAnnotation(PdfAnnotation annot)
    {
        pageEmpty = false;
        annotationsImp.addAnnotation(annot);
    }

    void setLanguage(String language)
    {
        this.language = new PdfString(language);
    }

    void setCropBoxSize(Rectangle crop)
    {
        setBoxSize("crop", crop);
    }

    void setBoxSize(String boxName, Rectangle size)
    {
        if(size == null)
            boxSize.remove(boxName);
        else
            boxSize.put(boxName, new PdfRectangle(size));
    }

    protected void setNewPageSizeAndMargins()
    {
        pageSize = nextPageSize;
        if(marginMirroring && (getPageNumber() & 1) == 0)
        {
            marginRight = nextMarginLeft;
            marginLeft = nextMarginRight;
        } else
        {
            marginLeft = nextMarginLeft;
            marginRight = nextMarginRight;
        }
        if(marginMirroringTopBottom && (getPageNumber() & 1) == 0)
        {
            marginTop = nextMarginBottom;
            marginBottom = nextMarginTop;
        } else
        {
            marginTop = nextMarginTop;
            marginBottom = nextMarginBottom;
        }
        if(!isTagged(writer))
        {
            text = new PdfContentByte(writer);
            text.reset();
        } else
        {
            text = graphics;
        }
        text.beginText();
        text.moveText(left(), top());
        if(isTagged(writer))
            textEmptySize = text.size();
    }

    Rectangle getBoxSize(String boxName)
    {
        PdfRectangle r = (PdfRectangle)thisBoxSize.get(boxName);
        if(r != null)
            return r.getRectangle();
        else
            return null;
    }

    void setPageEmpty(boolean pageEmpty)
    {
        this.pageEmpty = pageEmpty;
    }

    boolean isPageEmpty()
    {
        if(isTagged(writer))
            return writer == null || writer.getDirectContent().size(false) == 0 && writer.getDirectContentUnder().size(false) == 0 && text.size(false) - textEmptySize == 0 && (pageEmpty || writer.isPaused());
        else
            return writer == null || writer.getDirectContent().size() == 0 && writer.getDirectContentUnder().size() == 0 && (pageEmpty || writer.isPaused());
    }

    void setDuration(int seconds)
    {
        if(seconds > 0)
            writer.addPageDictEntry(PdfName.DUR, new PdfNumber(seconds));
    }

    void setTransition(PdfTransition transition)
    {
        writer.addPageDictEntry(PdfName.TRANS, transition.getTransitionDictionary());
    }

    void setPageAction(PdfName actionType, PdfAction action)
    {
        if(pageAA == null)
            pageAA = new PdfDictionary();
        pageAA.put(actionType, action);
    }

    void setThumbnail(Image image)
        throws PdfException, DocumentException
    {
        writer.addPageDictEntry(PdfName.THUMB, writer.getImageReference(writer.addDirectImageSimple(image)));
    }

    PageResources getPageResources()
    {
        return pageResources;
    }

    boolean isStrictImageSequence()
    {
        return strictImageSequence;
    }

    void setStrictImageSequence(boolean strictImageSequence)
    {
        this.strictImageSequence = strictImageSequence;
    }

    public void clearTextWrap()
    {
        float tmpHeight = imageEnd - currentHeight;
        if(line != null)
            tmpHeight += line.height();
        if(imageEnd > -1F && tmpHeight > 0.0F)
        {
            carriageReturn();
            currentHeight += tmpHeight;
        }
    }

    public int getStructParentIndex(Object obj)
    {
        int i[] = (int[])structParentIndices.get(obj);
        if(i == null)
        {
            i = (new int[] {
                structParentIndices.size(), 0
            });
            structParentIndices.put(obj, i);
        }
        return i[0];
    }

    public int getNextMarkPoint(Object obj)
    {
        int i[] = (int[])structParentIndices.get(obj);
        if(i == null)
        {
            i = (new int[] {
                structParentIndices.size(), 0
            });
            structParentIndices.put(obj, i);
        }
        int markPoint = i[1];
        i[1]++;
        return markPoint;
    }

    public int[] getStructParentIndexAndNextMarkPoint(Object obj)
    {
        int i[] = (int[])structParentIndices.get(obj);
        if(i == null)
        {
            i = (new int[] {
                structParentIndices.size(), 0
            });
            structParentIndices.put(obj, i);
        }
        int markPoint = i[1];
        i[1]++;
        return (new int[] {
            i[0], markPoint
        });
    }

    protected void add(Image image)
        throws PdfException, DocumentException
    {
        if(image.hasAbsoluteY())
        {
            graphics.addImage(image);
            pageEmpty = false;
            return;
        }
        if(currentHeight != 0.0F && indentTop() - currentHeight - image.getScaledHeight() < indentBottom())
        {
            if(!strictImageSequence && imageWait == null)
            {
                imageWait = image;
                return;
            }
            newPage();
            if(currentHeight != 0.0F && indentTop() - currentHeight - image.getScaledHeight() < indentBottom())
            {
                imageWait = image;
                return;
            }
        }
        pageEmpty = false;
        if(image == imageWait)
            imageWait = null;
        boolean textwrap = (image.getAlignment() & 4) == 4 && (image.getAlignment() & 1) != 1;
        boolean underlying = (image.getAlignment() & 8) == 8;
        float diff = leading / 2.0F;
        if(textwrap)
            diff += leading;
        float lowerleft = indentTop() - currentHeight - image.getScaledHeight() - diff;
        float mt[] = image.matrix();
        float startPosition = indentLeft() - mt[4];
        if((image.getAlignment() & 2) == 2)
            startPosition = indentRight() - image.getScaledWidth() - mt[4];
        if((image.getAlignment() & 1) == 1)
            startPosition = (indentLeft() + (indentRight() - indentLeft() - image.getScaledWidth()) / 2.0F) - mt[4];
        if(image.hasAbsoluteX())
            startPosition = image.getAbsoluteX();
        if(textwrap)
        {
            if(imageEnd < 0.0F || imageEnd < currentHeight + image.getScaledHeight() + diff)
                imageEnd = currentHeight + image.getScaledHeight() + diff;
            if((image.getAlignment() & 2) == 2)
                indentation.imageIndentRight += image.getScaledWidth() + image.getIndentationLeft();
            else
                indentation.imageIndentLeft += image.getScaledWidth() + image.getIndentationRight();
        } else
        if((image.getAlignment() & 2) == 2)
            startPosition -= image.getIndentationRight();
        else
        if((image.getAlignment() & 1) == 1)
            startPosition += image.getIndentationLeft() - image.getIndentationRight();
        else
            startPosition += image.getIndentationLeft();
        graphics.addImage(image, mt[0], mt[1], mt[2], mt[3], startPosition, lowerleft - mt[5]);
        if(!textwrap && !underlying)
        {
            currentHeight += image.getScaledHeight() + diff;
            flushLines();
            text.moveText(0.0F, -(image.getScaledHeight() + diff));
            newLine();
        }
    }

    void addPTable(PdfPTable ptable)
        throws DocumentException
    {
        ColumnText ct = new ColumnText(isTagged(writer) ? text : writer.getDirectContent());
        if(ptable.getKeepTogether() && !fitsPage(ptable, 0.0F) && currentHeight > 0.0F)
            newPage();
        if(currentHeight == 0.0F)
            ct.setAdjustFirstLine(false);
        ct.addElement(ptable);
        boolean he = ptable.isHeadersInEvent();
        ptable.setHeadersInEvent(true);
        int loop = 0;
        do
        {
            ct.setSimpleColumn(indentLeft(), indentBottom(), indentRight(), indentTop() - currentHeight);
            int status = ct.go();
            if((status & 1) != 0)
            {
                if(isTagged(writer))
                    text.setTextMatrix(indentLeft(), ct.getYLine());
                else
                    text.moveText(0.0F, (ct.getYLine() - indentTop()) + currentHeight);
                currentHeight = indentTop() - ct.getYLine();
                break;
            }
            if(indentTop() - currentHeight == ct.getYLine())
                loop++;
            else
                loop = 0;
            if(loop == 3)
                throw new DocumentException(MessageLocalization.getComposedMessage("infinite.table.loop", new Object[0]));
            newPage();
            if(isTagged(writer))
                ct.setCanvas(text);
        } while(true);
        ptable.setHeadersInEvent(he);
    }

    private void addDiv(PdfDiv div)
        throws DocumentException
    {
        if(floatingElements == null)
            floatingElements = new ArrayList();
        floatingElements.add(div);
    }

    private void flushFloatingElements()
        throws DocumentException
    {
        if(floatingElements != null && !floatingElements.isEmpty())
        {
            ArrayList cachedFloatingElements = floatingElements;
            floatingElements = null;
            FloatLayout fl = new FloatLayout(cachedFloatingElements, false);
            int loop = 0;
            do
            {
                fl.setSimpleColumn(indentLeft(), indentBottom(), indentRight(), indentTop() - currentHeight);
                try
                {
                    int status = fl.layout(isTagged(writer) ? text : writer.getDirectContent(), false);
                    if((status & 1) != 0)
                    {
                        if(isTagged(writer))
                            text.setTextMatrix(indentLeft(), fl.getYLine());
                        else
                            text.moveText(0.0F, (fl.getYLine() - indentTop()) + currentHeight);
                        currentHeight = indentTop() - fl.getYLine();
                        break;
                    }
                }
                catch(Exception exc)
                {
                    return;
                }
                if(indentTop() - currentHeight == fl.getYLine() || isPageEmpty())
                    loop++;
                else
                    loop = 0;
                if(loop == 2)
                    return;
                newPage();
            } while(true);
        }
    }

    boolean fitsPage(PdfPTable table, float margin)
    {
        if(!table.isLockedWidth())
        {
            float totalWidth = ((indentRight() - indentLeft()) * table.getWidthPercentage()) / 100F;
            table.setTotalWidth(totalWidth);
        }
        ensureNewLine();
        Float spaceNeeded = Float.valueOf(table.isSkipFirstHeader() ? table.getTotalHeight() - table.getHeaderHeight() : table.getTotalHeight());
        return spaceNeeded.floatValue() + (currentHeight <= 0.0F ? 0.0F : table.spacingBefore()) <= indentTop() - currentHeight - indentBottom() - margin;
    }

    private static boolean isTagged(PdfWriter writer)
    {
        return writer != null && writer.isTagged();
    }

    private PdfLine getLastLine()
    {
        if(lines.size() > 0)
            return (PdfLine)lines.get(lines.size() - 1);
        else
            return null;
    }

    protected PdfWriter writer;
    protected HashMap structElements;
    protected boolean openMCDocument;
    protected HashMap structParentIndices;
    protected HashMap markPoints;
    protected PdfContentByte text;
    protected PdfContentByte graphics;
    protected float leading;
    protected int alignment;
    protected float currentHeight;
    protected boolean isSectionTitle;
    protected int leadingCount;
    protected PdfAction anchorAction;
    protected TabSettings tabSettings;
    protected int textEmptySize;
    protected float nextMarginLeft;
    protected float nextMarginRight;
    protected float nextMarginTop;
    protected float nextMarginBottom;
    protected boolean firstPageEvent;
    protected PdfLine line;
    protected ArrayList lines;
    protected int lastElementType;
    static final String hangingPunctuation = ".,;:'";
    protected Indentation indentation;
    protected PdfInfo info;
    protected PdfOutline rootOutline;
    protected PdfOutline currentOutline;
    protected PdfViewerPreferencesImp viewerPreferences;
    protected PdfPageLabels pageLabels;
    protected TreeMap localDestinations;
    int jsCounter;
    protected HashMap documentLevelJS;
    protected static final DecimalFormat SIXTEEN_DIGITS = new DecimalFormat("0000000000000000");
    protected HashMap documentFileAttachment;
    protected String openActionName;
    protected PdfAction openActionAction;
    protected PdfDictionary additionalActions;
    protected PdfCollection collection;
    PdfAnnotationsImp annotationsImp;
    protected PdfString language;
    protected Rectangle nextPageSize;
    protected HashMap thisBoxSize;
    protected HashMap boxSize;
    private boolean pageEmpty;
    protected PdfDictionary pageAA;
    protected PageResources pageResources;
    protected boolean strictImageSequence;
    protected float imageEnd;
    protected Image imageWait;
    private ArrayList floatingElements;

}
