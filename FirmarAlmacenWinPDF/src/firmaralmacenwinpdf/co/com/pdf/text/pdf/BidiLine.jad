// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BidiLine.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.pdf.draw.DrawInterface;
import co.com.pdf.text.pdf.draw.LineSeparator;
import co.com.pdf.text.pdf.languages.ArabicLigaturizer;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfChunk, BidiOrder, PdfLine, HyphenationEvent, 
//            IntHashtable, BaseFont, PdfFont

public class BidiLine
{

    public BidiLine()
    {
        pieceSize = 256;
        text = new char[pieceSize];
        detailChunks = new PdfChunk[pieceSize];
        totalTextLength = 0;
        orderLevels = new byte[pieceSize];
        indexChars = new int[pieceSize];
        chunks = new ArrayList();
        indexChunk = 0;
        indexChunkChar = 0;
        currentChar = 0;
        storedText = new char[0];
        storedDetailChunks = new PdfChunk[0];
        storedTotalTextLength = 0;
        storedOrderLevels = new byte[0];
        storedIndexChars = new int[0];
        storedIndexChunk = 0;
        storedIndexChunkChar = 0;
        storedCurrentChar = 0;
    }

    public BidiLine(BidiLine org)
    {
        pieceSize = 256;
        text = new char[pieceSize];
        detailChunks = new PdfChunk[pieceSize];
        totalTextLength = 0;
        orderLevels = new byte[pieceSize];
        indexChars = new int[pieceSize];
        chunks = new ArrayList();
        indexChunk = 0;
        indexChunkChar = 0;
        currentChar = 0;
        storedText = new char[0];
        storedDetailChunks = new PdfChunk[0];
        storedTotalTextLength = 0;
        storedOrderLevels = new byte[0];
        storedIndexChars = new int[0];
        storedIndexChunk = 0;
        storedIndexChunkChar = 0;
        storedCurrentChar = 0;
        runDirection = org.runDirection;
        pieceSize = org.pieceSize;
        text = (char[])org.text.clone();
        detailChunks = (PdfChunk[])org.detailChunks.clone();
        totalTextLength = org.totalTextLength;
        orderLevels = (byte[])org.orderLevels.clone();
        indexChars = (int[])org.indexChars.clone();
        chunks = new ArrayList(org.chunks);
        indexChunk = org.indexChunk;
        indexChunkChar = org.indexChunkChar;
        currentChar = org.currentChar;
        storedRunDirection = org.storedRunDirection;
        storedText = (char[])org.storedText.clone();
        storedDetailChunks = (PdfChunk[])org.storedDetailChunks.clone();
        storedTotalTextLength = org.storedTotalTextLength;
        storedOrderLevels = (byte[])org.storedOrderLevels.clone();
        storedIndexChars = (int[])org.storedIndexChars.clone();
        storedIndexChunk = org.storedIndexChunk;
        storedIndexChunkChar = org.storedIndexChunkChar;
        storedCurrentChar = org.storedCurrentChar;
        shortStore = org.shortStore;
        arabicOptions = org.arabicOptions;
    }

    public boolean isEmpty()
    {
        return currentChar >= totalTextLength && indexChunk >= chunks.size();
    }

    public void clearChunks()
    {
        chunks.clear();
        totalTextLength = 0;
        currentChar = 0;
    }

    public boolean getParagraph(int runDirection)
    {
        this.runDirection = runDirection;
        currentChar = 0;
        totalTextLength = 0;
        boolean hasText = false;
        do
        {
            if(indexChunk >= chunks.size())
                break;
            PdfChunk ck = (PdfChunk)chunks.get(indexChunk);
            BaseFont bf = ck.font().getFont();
            String s = ck.toString();
            int len = s.length();
            do
            {
                if(indexChunkChar >= len)
                    break;
                char c = s.charAt(indexChunkChar);
                char uniC = (char)bf.getUnicodeEquivalent(c);
                if(uniC == '\r' || uniC == '\n')
                {
                    if(uniC == '\r' && indexChunkChar + 1 < len && s.charAt(indexChunkChar + 1) == '\n')
                        indexChunkChar++;
                    indexChunkChar++;
                    if(indexChunkChar >= len)
                    {
                        indexChunkChar = 0;
                        indexChunk++;
                    }
                    hasText = true;
                    if(totalTextLength == 0)
                        detailChunks[0] = ck;
                    break;
                }
                addPiece(c, ck);
                indexChunkChar++;
            } while(true);
            if(hasText)
                break;
            indexChunkChar = 0;
            indexChunk++;
        } while(true);
        if(totalTextLength == 0)
            return hasText;
        totalTextLength = trimRight(0, totalTextLength - 1) + 1;
        if(totalTextLength == 0)
            return true;
        if(runDirection == 2 || runDirection == 3)
        {
            if(orderLevels.length < totalTextLength)
            {
                orderLevels = new byte[pieceSize];
                indexChars = new int[pieceSize];
            }
            ArabicLigaturizer.processNumbers(text, 0, totalTextLength, arabicOptions);
            BidiOrder order = new BidiOrder(text, 0, totalTextLength, (byte)(runDirection != 3 ? 0 : 1));
            byte od[] = order.getLevels();
            for(int k = 0; k < totalTextLength; k++)
            {
                orderLevels[k] = od[k];
                indexChars[k] = k;
            }

            doArabicShapping();
            mirrorGlyphs();
        }
        totalTextLength = trimRightEx(0, totalTextLength - 1) + 1;
        return true;
    }

    public void addChunk(PdfChunk chunk)
    {
        chunks.add(chunk);
    }

    public void addChunks(ArrayList chunks)
    {
        this.chunks.addAll(chunks);
    }

    public void addPiece(char c, PdfChunk chunk)
    {
        if(totalTextLength >= pieceSize)
        {
            char tempText[] = text;
            PdfChunk tempDetailChunks[] = detailChunks;
            pieceSize *= 2;
            text = new char[pieceSize];
            detailChunks = new PdfChunk[pieceSize];
            System.arraycopy(tempText, 0, text, 0, totalTextLength);
            System.arraycopy(tempDetailChunks, 0, detailChunks, 0, totalTextLength);
        }
        text[totalTextLength] = c;
        detailChunks[totalTextLength++] = chunk;
    }

    public void save()
    {
        if(indexChunk > 0)
        {
            if(indexChunk >= chunks.size())
                chunks.clear();
            else
                for(indexChunk--; indexChunk >= 0; indexChunk--)
                    chunks.remove(indexChunk);

            indexChunk = 0;
        }
        storedRunDirection = runDirection;
        storedTotalTextLength = totalTextLength;
        storedIndexChunk = indexChunk;
        storedIndexChunkChar = indexChunkChar;
        storedCurrentChar = currentChar;
        shortStore = currentChar < totalTextLength;
        if(!shortStore)
        {
            if(storedText.length < totalTextLength)
            {
                storedText = new char[totalTextLength];
                storedDetailChunks = new PdfChunk[totalTextLength];
            }
            System.arraycopy(text, 0, storedText, 0, totalTextLength);
            System.arraycopy(detailChunks, 0, storedDetailChunks, 0, totalTextLength);
        }
        if(runDirection == 2 || runDirection == 3)
        {
            if(storedOrderLevels.length < totalTextLength)
            {
                storedOrderLevels = new byte[totalTextLength];
                storedIndexChars = new int[totalTextLength];
            }
            System.arraycopy(orderLevels, currentChar, storedOrderLevels, currentChar, totalTextLength - currentChar);
            System.arraycopy(indexChars, currentChar, storedIndexChars, currentChar, totalTextLength - currentChar);
        }
    }

    public void restore()
    {
        runDirection = storedRunDirection;
        totalTextLength = storedTotalTextLength;
        indexChunk = storedIndexChunk;
        indexChunkChar = storedIndexChunkChar;
        currentChar = storedCurrentChar;
        if(!shortStore)
        {
            System.arraycopy(storedText, 0, text, 0, totalTextLength);
            System.arraycopy(storedDetailChunks, 0, detailChunks, 0, totalTextLength);
        }
        if(runDirection == 2 || runDirection == 3)
        {
            System.arraycopy(storedOrderLevels, currentChar, orderLevels, currentChar, totalTextLength - currentChar);
            System.arraycopy(storedIndexChars, currentChar, indexChars, currentChar, totalTextLength - currentChar);
        }
    }

    public void mirrorGlyphs()
    {
        for(int k = 0; k < totalTextLength; k++)
        {
            if((orderLevels[k] & 1) != 1)
                continue;
            int mirror = mirrorChars.get(text[k]);
            if(mirror != 0)
                text[k] = (char)mirror;
        }

    }

    public void doArabicShapping()
    {
        int src = 0;
        int dest = 0;
        do
        {
            if(src < totalTextLength)
            {
                char c = text[src];
                if(c < '\u0600' || c > '\u06FF')
                {
                    if(src != dest)
                    {
                        text[dest] = text[src];
                        detailChunks[dest] = detailChunks[src];
                        orderLevels[dest] = orderLevels[src];
                    }
                    src++;
                    dest++;
                    continue;
                }
            }
            if(src >= totalTextLength)
            {
                totalTextLength = dest;
                return;
            }
            int startArabicIdx = src;
            src++;
            do
            {
                if(src >= totalTextLength)
                    break;
                char c = text[src];
                if(c < '\u0600' || c > '\u06FF')
                    break;
                src++;
            } while(true);
            int arabicWordSize = src - startArabicIdx;
            int size = ArabicLigaturizer.arabic_shape(text, startArabicIdx, arabicWordSize, text, dest, arabicWordSize, arabicOptions);
            if(startArabicIdx != dest)
            {
                int k = 0;
                while(k < size) 
                {
                    detailChunks[dest] = detailChunks[startArabicIdx];
                    orderLevels[dest++] = orderLevels[startArabicIdx++];
                    k++;
                }
            } else
            {
                dest += size;
            }
        } while(true);
    }

    public PdfLine processLine(float leftX, float width, int alignment, int runDirection, int arabicOptions, float minY, float yLine, 
            float descender)
    {
        this.arabicOptions = arabicOptions;
        save();
        boolean isRTL = runDirection == 3;
        if(currentChar >= totalTextLength)
        {
            boolean hasText = getParagraph(runDirection);
            if(!hasText)
                return null;
            if(totalTextLength == 0)
            {
                ArrayList ar = new ArrayList();
                PdfChunk ck = new PdfChunk("", detailChunks[0]);
                ar.add(ck);
                return new PdfLine(0.0F, 0.0F, width, alignment, true, ar, isRTL);
            }
        }
        float originalWidth = width;
        int lastSplit = -1;
        if(currentChar != 0)
            currentChar = trimLeftEx(currentChar, totalTextLength - 1);
        int oldCurrentChar = currentChar;
        int uniC = 0;
        PdfChunk ck = null;
        float charWidth = 0.0F;
        PdfChunk lastValidChunk = null;
        TabStop tabStop = null;
        float tabStopAnchorPosition = (0.0F / 0.0F);
        float tabPosition = (0.0F / 0.0F);
        boolean surrogate = false;
        for(; currentChar < totalTextLength; currentChar++)
        {
            ck = detailChunks[currentChar];
            if(ck.isImage() && minY < yLine)
            {
                Image img = ck.getImage();
                if(img.isScaleToFitHeight() && (yLine + 2.0F * descender) - img.getScaledHeight() - ck.getImageOffsetY() - img.getSpacingBefore() < minY)
                {
                    float scalePercent = ((yLine + 2.0F * descender) - ck.getImageOffsetY() - img.getSpacingBefore() - minY) / img.getHeight();
                    ck.setImageScalePercentage(scalePercent);
                }
            }
            surrogate = Utilities.isSurrogatePair(text, currentChar);
            if(surrogate)
                uniC = ck.getUnicodeEquivalent(Utilities.convertToUtf32(text, currentChar));
            else
                uniC = ck.getUnicodeEquivalent(text[currentChar]);
            if(PdfChunk.noPrint(uniC))
                continue;
            if(surrogate)
                charWidth = ck.getCharWidth(uniC);
            else
            if(ck.isImage())
                charWidth = ck.getImageWidth();
            else
                charWidth = ck.getCharWidth(text[currentChar]);
            if(width - charWidth < 0.0F && lastValidChunk == null && ck.isImage())
            {
                Image img = ck.getImage();
                if(img.isScaleToFitLineWhenOverflow())
                {
                    float scalePercent = width / img.getWidth();
                    ck.setImageScalePercentage(scalePercent);
                    charWidth = width;
                }
            }
            if(ck.isTab())
            {
                if(ck.isAttribute("TABSETTINGS"))
                {
                    lastSplit = currentChar;
                    if(tabStop != null)
                    {
                        float tabStopPosition = tabStop.getPosition(tabPosition, originalWidth - width, tabStopAnchorPosition);
                        width = originalWidth - (tabStopPosition + (originalWidth - width - tabPosition));
                        if(width < 0.0F)
                        {
                            tabStopPosition += width;
                            width = 0.0F;
                        }
                        tabStop.setPosition(tabStopPosition);
                    }
                    tabStop = PdfChunk.getTabStop(ck, originalWidth - width);
                    if(tabStop.getPosition() > originalWidth)
                    {
                        tabStop = null;
                        break;
                    }
                    ck.setTabStop(tabStop);
                    if(tabStop.getAlignment() == co.com.pdf.text.TabStop.Alignment.LEFT)
                    {
                        width = originalWidth - tabStop.getPosition();
                        tabStop = null;
                        tabPosition = (0.0F / 0.0F);
                        tabStopAnchorPosition = (0.0F / 0.0F);
                    } else
                    {
                        tabPosition = originalWidth - width;
                        tabStopAnchorPosition = (0.0F / 0.0F);
                    }
                } else
                {
                    Object tab[] = (Object[])(Object[])ck.getAttribute("TAB");
                    float tabStopPosition = ((Float)tab[1]).floatValue();
                    boolean newLine = ((Boolean)tab[2]).booleanValue();
                    if(newLine && tabStopPosition < originalWidth - width)
                        return new PdfLine(0.0F, originalWidth, width, alignment, true, createArrayOfPdfChunks(oldCurrentChar, currentChar - 1), isRTL);
                    detailChunks[currentChar].adjustLeft(leftX);
                    width = originalWidth - tabStopPosition;
                }
            } else
            if(ck.isSeparator())
            {
                Object sep[] = (Object[])(Object[])ck.getAttribute("SEPARATOR");
                DrawInterface di = (DrawInterface)sep[0];
                Boolean vertical = (Boolean)sep[1];
                if(vertical.booleanValue() && (di instanceof LineSeparator))
                {
                    float separatorWidth = (originalWidth * ((LineSeparator)di).getPercentage()) / 100F;
                    width -= separatorWidth;
                    if(width < 0.0F)
                        width = 0.0F;
                }
            } else
            {
                boolean splitChar = ck.isExtSplitCharacter(oldCurrentChar, currentChar, totalTextLength, text, detailChunks);
                if(splitChar && Character.isWhitespace((char)uniC))
                    lastSplit = currentChar;
                if(width - charWidth < 0.0F)
                    break;
                if(tabStop != null && tabStop.getAlignment() == co.com.pdf.text.TabStop.Alignment.ANCHOR && Float.isNaN(tabStopAnchorPosition) && tabStop.getAnchorChar() == (char)uniC)
                    tabStopAnchorPosition = originalWidth - width;
                width -= charWidth;
                if(splitChar)
                    lastSplit = currentChar;
            }
            lastValidChunk = ck;
            if(surrogate)
                currentChar++;
        }

        if(lastValidChunk == null)
        {
            currentChar++;
            if(surrogate)
                currentChar++;
            return new PdfLine(0.0F, originalWidth, 0.0F, alignment, false, createArrayOfPdfChunks(currentChar - 1, currentChar - 1), isRTL);
        }
        if(tabStop != null)
        {
            float tabStopPosition = tabStop.getPosition(tabPosition, originalWidth - width, tabStopAnchorPosition);
            width = originalWidth - (tabStopPosition + (originalWidth - width - tabPosition));
            if(width < 0.0F)
            {
                tabStopPosition += width;
                width = 0.0F;
            }
            tabStop.setPosition(tabStopPosition);
        }
        if(currentChar >= totalTextLength)
            return new PdfLine(0.0F, originalWidth, width, alignment, true, createArrayOfPdfChunks(oldCurrentChar, totalTextLength - 1), isRTL);
        int newCurrentChar = trimRightEx(oldCurrentChar, currentChar - 1);
        if(newCurrentChar < oldCurrentChar)
            return new PdfLine(0.0F, originalWidth, width, alignment, false, createArrayOfPdfChunks(oldCurrentChar, currentChar - 1), isRTL);
        if(newCurrentChar == currentChar - 1)
        {
            HyphenationEvent he = (HyphenationEvent)lastValidChunk.getAttribute("HYPHENATION");
            if(he != null)
            {
                int word[] = getWord(oldCurrentChar, newCurrentChar);
                if(word != null)
                {
                    float testWidth = width + getWidth(word[0], currentChar - 1);
                    String pre = he.getHyphenatedWordPre(new String(text, word[0], word[1] - word[0]), lastValidChunk.font().getFont(), lastValidChunk.font().size(), testWidth);
                    String post = he.getHyphenatedWordPost();
                    if(pre.length() > 0)
                    {
                        PdfChunk extra = new PdfChunk(pre, lastValidChunk);
                        currentChar = word[1] - post.length();
                        return new PdfLine(0.0F, originalWidth, testWidth - lastValidChunk.width(pre), alignment, false, createArrayOfPdfChunks(oldCurrentChar, word[0] - 1, extra), isRTL);
                    }
                }
            }
        }
        if(lastSplit == -1 || lastSplit >= newCurrentChar)
            return new PdfLine(0.0F, originalWidth, width + getWidth(newCurrentChar + 1, currentChar - 1), alignment, false, createArrayOfPdfChunks(oldCurrentChar, newCurrentChar), isRTL);
        currentChar = lastSplit + 1;
        newCurrentChar = trimRightEx(oldCurrentChar, lastSplit);
        if(newCurrentChar < oldCurrentChar)
            newCurrentChar = currentChar - 1;
        return new PdfLine(0.0F, originalWidth, originalWidth - getWidth(oldCurrentChar, newCurrentChar), alignment, false, createArrayOfPdfChunks(oldCurrentChar, newCurrentChar), isRTL);
    }

    public float getWidth(int startIdx, int lastIdx)
    {
        char c = '\0';
        PdfChunk ck = null;
        float width = 0.0F;
        TabStop tabStop = null;
        float tabStopAnchorPosition = (0.0F / 0.0F);
        float tabPosition = (0.0F / 0.0F);
        for(; startIdx <= lastIdx; startIdx++)
        {
            boolean surrogate = Utilities.isSurrogatePair(text, startIdx);
            if(detailChunks[startIdx].isTab() && detailChunks[startIdx].isAttribute("TABSETTINGS"))
            {
                if(tabStop != null)
                {
                    float tabStopPosition = tabStop.getPosition(tabPosition, width, tabStopAnchorPosition);
                    width = tabStopPosition + (width - tabPosition);
                    tabStop.setPosition(tabStopPosition);
                }
                tabStop = detailChunks[startIdx].getTabStop();
                if(tabStop == null)
                {
                    tabStop = PdfChunk.getTabStop(detailChunks[startIdx], width);
                    tabPosition = width;
                    tabStopAnchorPosition = (0.0F / 0.0F);
                } else
                {
                    width = tabStop.getPosition();
                    tabStop = null;
                    tabPosition = (0.0F / 0.0F);
                    tabStopAnchorPosition = (0.0F / 0.0F);
                }
                continue;
            }
            if(surrogate)
            {
                width += detailChunks[startIdx].getCharWidth(Utilities.convertToUtf32(text, startIdx));
                startIdx++;
                continue;
            }
            c = text[startIdx];
            ck = detailChunks[startIdx];
            if(PdfChunk.noPrint(ck.getUnicodeEquivalent(c)))
                continue;
            if(tabStop != null && tabStop.getAlignment() != co.com.pdf.text.TabStop.Alignment.ANCHOR && Float.isNaN(tabStopAnchorPosition) && tabStop.getAnchorChar() == (char)ck.getUnicodeEquivalent(c))
                tabStopAnchorPosition = width;
            width += detailChunks[startIdx].getCharWidth(c);
        }

        if(tabStop != null)
        {
            float tabStopPosition = tabStop.getPosition(tabPosition, width, tabStopAnchorPosition);
            width = tabStopPosition + (width - tabPosition);
            tabStop.setPosition(tabStopPosition);
        }
        return width;
    }

    public ArrayList createArrayOfPdfChunks(int startIdx, int endIdx)
    {
        return createArrayOfPdfChunks(startIdx, endIdx, null);
    }

    public ArrayList createArrayOfPdfChunks(int startIdx, int endIdx, PdfChunk extraPdfChunk)
    {
        boolean bidi = runDirection == 2 || runDirection == 3;
        if(bidi)
            reorder(startIdx, endIdx);
        ArrayList ar = new ArrayList();
        PdfChunk refCk = detailChunks[startIdx];
        PdfChunk ck = null;
        StringBuffer buf = new StringBuffer();
        int idx = 0;
        for(; startIdx <= endIdx; startIdx++)
        {
            idx = bidi ? indexChars[startIdx] : startIdx;
            char c = text[idx];
            ck = detailChunks[idx];
            if(PdfChunk.noPrint(ck.getUnicodeEquivalent(c)))
                continue;
            if(ck.isImage() || ck.isSeparator() || ck.isTab())
            {
                if(buf.length() > 0)
                {
                    ar.add(new PdfChunk(buf.toString(), refCk));
                    buf = new StringBuffer();
                }
                ar.add(ck);
                continue;
            }
            if(ck == refCk)
            {
                buf.append(c);
                continue;
            }
            if(buf.length() > 0)
            {
                ar.add(new PdfChunk(buf.toString(), refCk));
                buf = new StringBuffer();
            }
            if(!ck.isImage() && !ck.isSeparator() && !ck.isTab())
                buf.append(c);
            refCk = ck;
        }

        if(buf.length() > 0)
            ar.add(new PdfChunk(buf.toString(), refCk));
        if(extraPdfChunk != null)
            ar.add(extraPdfChunk);
        return ar;
    }

    public int[] getWord(int startIdx, int idx)
    {
        int last = idx;
        int first = idx;
        for(; last < totalTextLength && Character.isLetter(text[last]); last++);
        if(last == idx)
            return null;
        for(; first >= startIdx && Character.isLetter(text[first]); first--);
        first++;
        return (new int[] {
            first, last
        });
    }

    public int trimRight(int startIdx, int endIdx)
    {
        int idx = endIdx;
        do
        {
            if(idx < startIdx)
                break;
            char c = (char)detailChunks[idx].getUnicodeEquivalent(text[idx]);
            if(!isWS(c))
                break;
            idx--;
        } while(true);
        return idx;
    }

    public int trimLeft(int startIdx, int endIdx)
    {
        int idx = startIdx;
        do
        {
            if(idx > endIdx)
                break;
            char c = (char)detailChunks[idx].getUnicodeEquivalent(text[idx]);
            if(!isWS(c))
                break;
            idx++;
        } while(true);
        return idx;
    }

    public int trimRightEx(int startIdx, int endIdx)
    {
        int idx = endIdx;
        char c = '\0';
        for(; idx >= startIdx; idx--)
        {
            c = (char)detailChunks[idx].getUnicodeEquivalent(text[idx]);
            if(isWS(c) || PdfChunk.noPrint(c))
                continue;
            if(!detailChunks[idx].isTab() || !detailChunks[idx].isAttribute("TABSETTINGS"))
                break;
            Object tab[] = (Object[])(Object[])detailChunks[idx].getAttribute("TAB");
            boolean isWhitespace = ((Boolean)tab[1]).booleanValue();
            if(!isWhitespace)
                break;
        }

        return idx;
    }

    public int trimLeftEx(int startIdx, int endIdx)
    {
        int idx = startIdx;
        char c = '\0';
        for(; idx <= endIdx; idx++)
        {
            c = (char)detailChunks[idx].getUnicodeEquivalent(text[idx]);
            if(isWS(c) || PdfChunk.noPrint(c))
                continue;
            if(!detailChunks[idx].isTab() || !detailChunks[idx].isAttribute("TABSETTINGS"))
                break;
            Object tab[] = (Object[])(Object[])detailChunks[idx].getAttribute("TAB");
            boolean isWhitespace = ((Boolean)tab[1]).booleanValue();
            if(!isWhitespace)
                break;
        }

        return idx;
    }

    public void reorder(int start, int end)
    {
        byte maxLevel = orderLevels[start];
        byte minLevel = maxLevel;
        byte onlyOddLevels = maxLevel;
        byte onlyEvenLevels = maxLevel;
        for(int k = start + 1; k <= end; k++)
        {
            byte b = orderLevels[k];
            if(b > maxLevel)
                maxLevel = b;
            else
            if(b < minLevel)
                minLevel = b;
            onlyOddLevels &= b;
            onlyEvenLevels |= b;
        }

        if((onlyEvenLevels & 1) == 0)
            return;
        if((onlyOddLevels & 1) == 1)
        {
            flip(start, end + 1);
            return;
        }
label0:
        for(minLevel |= 1; maxLevel >= minLevel; maxLevel--)
            do
            {
                int pstart;
                for(pstart = start; pstart <= end && orderLevels[pstart] < maxLevel; pstart++);
                if(pstart > end)
                    continue label0;
                int pend;
                for(pend = pstart + 1; pend <= end && orderLevels[pend] >= maxLevel; pend++);
                flip(pstart, pend);
                pstart = pend + 1;
            } while(true);

    }

    public void flip(int start, int end)
    {
        int mid = (start + end) / 2;
        for(end--; start < mid; end--)
        {
            int temp = indexChars[start];
            indexChars[start] = indexChars[end];
            indexChars[end] = temp;
            start++;
        }

    }

    public static boolean isWS(char c)
    {
        return c <= ' ';
    }

    public static String processLTR(String s, int runDirection, int arabicOptions)
    {
        BidiLine bidi = new BidiLine();
        bidi.addChunk(new PdfChunk(new Chunk(s), null));
        bidi.arabicOptions = arabicOptions;
        bidi.getParagraph(runDirection);
        ArrayList arr = bidi.createArrayOfPdfChunks(0, bidi.totalTextLength - 1);
        StringBuilder sb = new StringBuilder();
        PdfChunk ck;
        for(Iterator i$ = arr.iterator(); i$.hasNext(); sb.append(ck.toString()))
            ck = (PdfChunk)i$.next();

        return sb.toString();
    }

    protected int runDirection;
    protected int pieceSize;
    protected char text[];
    protected PdfChunk detailChunks[];
    protected int totalTextLength;
    protected byte orderLevels[];
    protected int indexChars[];
    protected ArrayList chunks;
    protected int indexChunk;
    protected int indexChunkChar;
    protected int currentChar;
    protected int storedRunDirection;
    protected char storedText[];
    protected PdfChunk storedDetailChunks[];
    protected int storedTotalTextLength;
    protected byte storedOrderLevels[];
    protected int storedIndexChars[];
    protected int storedIndexChunk;
    protected int storedIndexChunkChar;
    protected int storedCurrentChar;
    protected boolean shortStore;
    protected static final IntHashtable mirrorChars;
    protected int arabicOptions;

    static 
    {
        mirrorChars = new IntHashtable();
        mirrorChars.put(40, 41);
        mirrorChars.put(41, 40);
        mirrorChars.put(60, 62);
        mirrorChars.put(62, 60);
        mirrorChars.put(91, 93);
        mirrorChars.put(93, 91);
        mirrorChars.put(123, 125);
        mirrorChars.put(125, 123);
        mirrorChars.put(171, 187);
        mirrorChars.put(187, 171);
        mirrorChars.put(8249, 8250);
        mirrorChars.put(8250, 8249);
        mirrorChars.put(8261, 8262);
        mirrorChars.put(8262, 8261);
        mirrorChars.put(8317, 8318);
        mirrorChars.put(8318, 8317);
        mirrorChars.put(8333, 8334);
        mirrorChars.put(8334, 8333);
        mirrorChars.put(8712, 8715);
        mirrorChars.put(8713, 8716);
        mirrorChars.put(8714, 8717);
        mirrorChars.put(8715, 8712);
        mirrorChars.put(8716, 8713);
        mirrorChars.put(8717, 8714);
        mirrorChars.put(8725, 10741);
        mirrorChars.put(8764, 8765);
        mirrorChars.put(8765, 8764);
        mirrorChars.put(8771, 8909);
        mirrorChars.put(8786, 8787);
        mirrorChars.put(8787, 8786);
        mirrorChars.put(8788, 8789);
        mirrorChars.put(8789, 8788);
        mirrorChars.put(8804, 8805);
        mirrorChars.put(8805, 8804);
        mirrorChars.put(8806, 8807);
        mirrorChars.put(8807, 8806);
        mirrorChars.put(8808, 8809);
        mirrorChars.put(8809, 8808);
        mirrorChars.put(8810, 8811);
        mirrorChars.put(8811, 8810);
        mirrorChars.put(8814, 8815);
        mirrorChars.put(8815, 8814);
        mirrorChars.put(8816, 8817);
        mirrorChars.put(8817, 8816);
        mirrorChars.put(8818, 8819);
        mirrorChars.put(8819, 8818);
        mirrorChars.put(8820, 8821);
        mirrorChars.put(8821, 8820);
        mirrorChars.put(8822, 8823);
        mirrorChars.put(8823, 8822);
        mirrorChars.put(8824, 8825);
        mirrorChars.put(8825, 8824);
        mirrorChars.put(8826, 8827);
        mirrorChars.put(8827, 8826);
        mirrorChars.put(8828, 8829);
        mirrorChars.put(8829, 8828);
        mirrorChars.put(8830, 8831);
        mirrorChars.put(8831, 8830);
        mirrorChars.put(8832, 8833);
        mirrorChars.put(8833, 8832);
        mirrorChars.put(8834, 8835);
        mirrorChars.put(8835, 8834);
        mirrorChars.put(8836, 8837);
        mirrorChars.put(8837, 8836);
        mirrorChars.put(8838, 8839);
        mirrorChars.put(8839, 8838);
        mirrorChars.put(8840, 8841);
        mirrorChars.put(8841, 8840);
        mirrorChars.put(8842, 8843);
        mirrorChars.put(8843, 8842);
        mirrorChars.put(8847, 8848);
        mirrorChars.put(8848, 8847);
        mirrorChars.put(8849, 8850);
        mirrorChars.put(8850, 8849);
        mirrorChars.put(8856, 10680);
        mirrorChars.put(8866, 8867);
        mirrorChars.put(8867, 8866);
        mirrorChars.put(8870, 10974);
        mirrorChars.put(8872, 10980);
        mirrorChars.put(8873, 10979);
        mirrorChars.put(8875, 10981);
        mirrorChars.put(8880, 8881);
        mirrorChars.put(8881, 8880);
        mirrorChars.put(8882, 8883);
        mirrorChars.put(8883, 8882);
        mirrorChars.put(8884, 8885);
        mirrorChars.put(8885, 8884);
        mirrorChars.put(8886, 8887);
        mirrorChars.put(8887, 8886);
        mirrorChars.put(8905, 8906);
        mirrorChars.put(8906, 8905);
        mirrorChars.put(8907, 8908);
        mirrorChars.put(8908, 8907);
        mirrorChars.put(8909, 8771);
        mirrorChars.put(8912, 8913);
        mirrorChars.put(8913, 8912);
        mirrorChars.put(8918, 8919);
        mirrorChars.put(8919, 8918);
        mirrorChars.put(8920, 8921);
        mirrorChars.put(8921, 8920);
        mirrorChars.put(8922, 8923);
        mirrorChars.put(8923, 8922);
        mirrorChars.put(8924, 8925);
        mirrorChars.put(8925, 8924);
        mirrorChars.put(8926, 8927);
        mirrorChars.put(8927, 8926);
        mirrorChars.put(8928, 8929);
        mirrorChars.put(8929, 8928);
        mirrorChars.put(8930, 8931);
        mirrorChars.put(8931, 8930);
        mirrorChars.put(8932, 8933);
        mirrorChars.put(8933, 8932);
        mirrorChars.put(8934, 8935);
        mirrorChars.put(8935, 8934);
        mirrorChars.put(8936, 8937);
        mirrorChars.put(8937, 8936);
        mirrorChars.put(8938, 8939);
        mirrorChars.put(8939, 8938);
        mirrorChars.put(8940, 8941);
        mirrorChars.put(8941, 8940);
        mirrorChars.put(8944, 8945);
        mirrorChars.put(8945, 8944);
        mirrorChars.put(8946, 8954);
        mirrorChars.put(8947, 8955);
        mirrorChars.put(8948, 8956);
        mirrorChars.put(8950, 8957);
        mirrorChars.put(8951, 8958);
        mirrorChars.put(8954, 8946);
        mirrorChars.put(8955, 8947);
        mirrorChars.put(8956, 8948);
        mirrorChars.put(8957, 8950);
        mirrorChars.put(8958, 8951);
        mirrorChars.put(8968, 8969);
        mirrorChars.put(8969, 8968);
        mirrorChars.put(8970, 8971);
        mirrorChars.put(8971, 8970);
        mirrorChars.put(9001, 9002);
        mirrorChars.put(9002, 9001);
        mirrorChars.put(10088, 10089);
        mirrorChars.put(10089, 10088);
        mirrorChars.put(10090, 10091);
        mirrorChars.put(10091, 10090);
        mirrorChars.put(10092, 10093);
        mirrorChars.put(10093, 10092);
        mirrorChars.put(10094, 10095);
        mirrorChars.put(10095, 10094);
        mirrorChars.put(10096, 10097);
        mirrorChars.put(10097, 10096);
        mirrorChars.put(10098, 10099);
        mirrorChars.put(10099, 10098);
        mirrorChars.put(10100, 10101);
        mirrorChars.put(10101, 10100);
        mirrorChars.put(10197, 10198);
        mirrorChars.put(10198, 10197);
        mirrorChars.put(10205, 10206);
        mirrorChars.put(10206, 10205);
        mirrorChars.put(10210, 10211);
        mirrorChars.put(10211, 10210);
        mirrorChars.put(10212, 10213);
        mirrorChars.put(10213, 10212);
        mirrorChars.put(10214, 10215);
        mirrorChars.put(10215, 10214);
        mirrorChars.put(10216, 10217);
        mirrorChars.put(10217, 10216);
        mirrorChars.put(10218, 10219);
        mirrorChars.put(10219, 10218);
        mirrorChars.put(10627, 10628);
        mirrorChars.put(10628, 10627);
        mirrorChars.put(10629, 10630);
        mirrorChars.put(10630, 10629);
        mirrorChars.put(10631, 10632);
        mirrorChars.put(10632, 10631);
        mirrorChars.put(10633, 10634);
        mirrorChars.put(10634, 10633);
        mirrorChars.put(10635, 10636);
        mirrorChars.put(10636, 10635);
        mirrorChars.put(10637, 10640);
        mirrorChars.put(10638, 10639);
        mirrorChars.put(10639, 10638);
        mirrorChars.put(10640, 10637);
        mirrorChars.put(10641, 10642);
        mirrorChars.put(10642, 10641);
        mirrorChars.put(10643, 10644);
        mirrorChars.put(10644, 10643);
        mirrorChars.put(10645, 10646);
        mirrorChars.put(10646, 10645);
        mirrorChars.put(10647, 10648);
        mirrorChars.put(10648, 10647);
        mirrorChars.put(10680, 8856);
        mirrorChars.put(10688, 10689);
        mirrorChars.put(10689, 10688);
        mirrorChars.put(10692, 10693);
        mirrorChars.put(10693, 10692);
        mirrorChars.put(10703, 10704);
        mirrorChars.put(10704, 10703);
        mirrorChars.put(10705, 10706);
        mirrorChars.put(10706, 10705);
        mirrorChars.put(10708, 10709);
        mirrorChars.put(10709, 10708);
        mirrorChars.put(10712, 10713);
        mirrorChars.put(10713, 10712);
        mirrorChars.put(10714, 10715);
        mirrorChars.put(10715, 10714);
        mirrorChars.put(10741, 8725);
        mirrorChars.put(10744, 10745);
        mirrorChars.put(10745, 10744);
        mirrorChars.put(10748, 10749);
        mirrorChars.put(10749, 10748);
        mirrorChars.put(10795, 10796);
        mirrorChars.put(10796, 10795);
        mirrorChars.put(10797, 10796);
        mirrorChars.put(10798, 10797);
        mirrorChars.put(10804, 10805);
        mirrorChars.put(10805, 10804);
        mirrorChars.put(10812, 10813);
        mirrorChars.put(10813, 10812);
        mirrorChars.put(10852, 10853);
        mirrorChars.put(10853, 10852);
        mirrorChars.put(10873, 10874);
        mirrorChars.put(10874, 10873);
        mirrorChars.put(10877, 10878);
        mirrorChars.put(10878, 10877);
        mirrorChars.put(10879, 10880);
        mirrorChars.put(10880, 10879);
        mirrorChars.put(10881, 10882);
        mirrorChars.put(10882, 10881);
        mirrorChars.put(10883, 10884);
        mirrorChars.put(10884, 10883);
        mirrorChars.put(10891, 10892);
        mirrorChars.put(10892, 10891);
        mirrorChars.put(10897, 10898);
        mirrorChars.put(10898, 10897);
        mirrorChars.put(10899, 10900);
        mirrorChars.put(10900, 10899);
        mirrorChars.put(10901, 10902);
        mirrorChars.put(10902, 10901);
        mirrorChars.put(10903, 10904);
        mirrorChars.put(10904, 10903);
        mirrorChars.put(10905, 10906);
        mirrorChars.put(10906, 10905);
        mirrorChars.put(10907, 10908);
        mirrorChars.put(10908, 10907);
        mirrorChars.put(10913, 10914);
        mirrorChars.put(10914, 10913);
        mirrorChars.put(10918, 10919);
        mirrorChars.put(10919, 10918);
        mirrorChars.put(10920, 10921);
        mirrorChars.put(10921, 10920);
        mirrorChars.put(10922, 10923);
        mirrorChars.put(10923, 10922);
        mirrorChars.put(10924, 10925);
        mirrorChars.put(10925, 10924);
        mirrorChars.put(10927, 10928);
        mirrorChars.put(10928, 10927);
        mirrorChars.put(10931, 10932);
        mirrorChars.put(10932, 10931);
        mirrorChars.put(10939, 10940);
        mirrorChars.put(10940, 10939);
        mirrorChars.put(10941, 10942);
        mirrorChars.put(10942, 10941);
        mirrorChars.put(10943, 10944);
        mirrorChars.put(10944, 10943);
        mirrorChars.put(10945, 10946);
        mirrorChars.put(10946, 10945);
        mirrorChars.put(10947, 10948);
        mirrorChars.put(10948, 10947);
        mirrorChars.put(10949, 10950);
        mirrorChars.put(10950, 10949);
        mirrorChars.put(10957, 10958);
        mirrorChars.put(10958, 10957);
        mirrorChars.put(10959, 10960);
        mirrorChars.put(10960, 10959);
        mirrorChars.put(10961, 10962);
        mirrorChars.put(10962, 10961);
        mirrorChars.put(10963, 10964);
        mirrorChars.put(10964, 10963);
        mirrorChars.put(10965, 10966);
        mirrorChars.put(10966, 10965);
        mirrorChars.put(10974, 8870);
        mirrorChars.put(10979, 8873);
        mirrorChars.put(10980, 8872);
        mirrorChars.put(10981, 8875);
        mirrorChars.put(10988, 10989);
        mirrorChars.put(10989, 10988);
        mirrorChars.put(10999, 11000);
        mirrorChars.put(11000, 10999);
        mirrorChars.put(11001, 11002);
        mirrorChars.put(11002, 11001);
        mirrorChars.put(12296, 12297);
        mirrorChars.put(12297, 12296);
        mirrorChars.put(12298, 12299);
        mirrorChars.put(12299, 12298);
        mirrorChars.put(12300, 12301);
        mirrorChars.put(12301, 12300);
        mirrorChars.put(12302, 12303);
        mirrorChars.put(12303, 12302);
        mirrorChars.put(12304, 12305);
        mirrorChars.put(12305, 12304);
        mirrorChars.put(12308, 12309);
        mirrorChars.put(12309, 12308);
        mirrorChars.put(12310, 12311);
        mirrorChars.put(12311, 12310);
        mirrorChars.put(12312, 12313);
        mirrorChars.put(12313, 12312);
        mirrorChars.put(12314, 12315);
        mirrorChars.put(12315, 12314);
        mirrorChars.put(65288, 65289);
        mirrorChars.put(65289, 65288);
        mirrorChars.put(65308, 65310);
        mirrorChars.put(65310, 65308);
        mirrorChars.put(65339, 65341);
        mirrorChars.put(65341, 65339);
        mirrorChars.put(65371, 65373);
        mirrorChars.put(65373, 65371);
        mirrorChars.put(65375, 65376);
        mirrorChars.put(65376, 65375);
        mirrorChars.put(65378, 65379);
        mirrorChars.put(65379, 65378);
    }
}
