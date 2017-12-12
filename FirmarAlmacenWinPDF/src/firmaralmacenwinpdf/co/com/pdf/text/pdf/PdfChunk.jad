// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfChunk.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.*;
import co.com.pdf.text.pdf.interfaces.IAccessibleElement;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfFont, HyphenationEvent, PdfAction, BaseFont, 
//            DefaultSplitCharacter

public class PdfChunk
{

    PdfChunk(String string, PdfChunk other)
    {
        value = "";
        encoding = "Cp1252";
        attributes = new HashMap();
        noStroke = new HashMap();
        imageScalePercentage = 1.0F;
        changeLeading = false;
        leading = 0.0F;
        accessibleElement = null;
        thisChunk[0] = this;
        value = string;
        font = other.font;
        attributes = other.attributes;
        noStroke = other.noStroke;
        baseFont = other.baseFont;
        changeLeading = other.changeLeading;
        leading = other.leading;
        Object obj[] = (Object[])(Object[])attributes.get("IMAGE");
        if(obj == null)
        {
            image = null;
        } else
        {
            image = (Image)obj[0];
            offsetX = ((Float)obj[1]).floatValue();
            offsetY = ((Float)obj[2]).floatValue();
            changeLeading = ((Boolean)obj[3]).booleanValue();
        }
        encoding = font.getFont().getEncoding();
        splitCharacter = (SplitCharacter)noStroke.get("SPLITCHARACTER");
        if(splitCharacter == null)
            splitCharacter = DefaultSplitCharacter.DEFAULT;
        accessibleElement = other.accessibleElement;
    }

    PdfChunk(Chunk chunk, PdfAction action)
    {
        value = "";
        encoding = "Cp1252";
        attributes = new HashMap();
        noStroke = new HashMap();
        imageScalePercentage = 1.0F;
        changeLeading = false;
        leading = 0.0F;
        accessibleElement = null;
        thisChunk[0] = this;
        value = chunk.getContent();
        Font f = chunk.getFont();
        float size = f.getSize();
        if(size == -1F)
            size = 12F;
        baseFont = f.getBaseFont();
        int style = f.getStyle();
        if(style == -1)
            style = 0;
        if(baseFont == null)
        {
            baseFont = f.getCalculatedBaseFont(false);
        } else
        {
            if((style & 1) != 0)
                attributes.put("TEXTRENDERMODE", ((Object) (new Object[] {
                    Integer.valueOf(2), new Float(size / 30F), null
                })));
            if((style & 2) != 0)
                attributes.put("SKEW", new float[] {
                    0.0F, 0.21256F
                });
        }
        font = new PdfFont(baseFont, size);
        HashMap attr = chunk.getAttributes();
        if(attr != null)
        {
            Iterator i$ = attr.entrySet().iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                java.util.Map.Entry entry = (java.util.Map.Entry)i$.next();
                String name = (String)entry.getKey();
                if(keysAttributes.contains(name))
                    attributes.put(name, entry.getValue());
                else
                if(keysNoStroke.contains(name))
                    noStroke.put(name, entry.getValue());
            } while(true);
            if("".equals(attr.get("GENERICTAG")))
                attributes.put("GENERICTAG", chunk.getContent());
        }
        if(f.isUnderlined())
        {
            Object obj[] = {
                null, new float[] {
                    0.0F, 0.06666667F, 0.0F, -0.3333333F, 0.0F
                }
            };
            Object unders[][] = Utilities.addToArray((Object[][])(Object[][])attributes.get("UNDERLINE"), obj);
            attributes.put("UNDERLINE", ((Object) (unders)));
        }
        if(f.isStrikethru())
        {
            Object obj[] = {
                null, new float[] {
                    0.0F, 0.06666667F, 0.0F, 0.3333333F, 0.0F
                }
            };
            Object unders[][] = Utilities.addToArray((Object[][])(Object[][])attributes.get("UNDERLINE"), obj);
            attributes.put("UNDERLINE", ((Object) (unders)));
        }
        if(action != null)
            attributes.put("ACTION", action);
        noStroke.put("COLOR", f.getColor());
        noStroke.put("ENCODING", font.getFont().getEncoding());
        Float lh = (Float)attributes.get("LINEHEIGHT");
        if(lh != null)
        {
            changeLeading = true;
            leading = lh.floatValue();
        }
        Object obj[] = (Object[])(Object[])attributes.get("IMAGE");
        if(obj == null)
        {
            image = null;
        } else
        {
            attributes.remove("HSCALE");
            image = (Image)obj[0];
            offsetX = ((Float)obj[1]).floatValue();
            offsetY = ((Float)obj[2]).floatValue();
            changeLeading = ((Boolean)obj[3]).booleanValue();
        }
        Float hs = (Float)attributes.get("HSCALE");
        if(hs != null)
            font.setHorizontalScaling(hs.floatValue());
        encoding = font.getFont().getEncoding();
        splitCharacter = (SplitCharacter)noStroke.get("SPLITCHARACTER");
        if(splitCharacter == null)
            splitCharacter = DefaultSplitCharacter.DEFAULT;
        accessibleElement = chunk;
    }

    PdfChunk(Chunk chunk, PdfAction action, TabSettings tabSettings)
    {
        this(chunk, action);
        if(tabSettings != null && attributes.get("TABSETTINGS") == null)
            attributes.put("TABSETTINGS", tabSettings);
    }

    public int getUnicodeEquivalent(int c)
    {
        return baseFont.getUnicodeEquivalent(c);
    }

    protected int getWord(String text, int start)
    {
        for(int len = text.length(); start < len && Character.isLetter(text.charAt(start)); start++);
        return start;
    }

    PdfChunk split(float width)
    {
        newlineSplit = false;
        if(image != null)
            if(image.getScaledWidth() > width)
            {
                PdfChunk pc = new PdfChunk("\uFFFC", this);
                value = "";
                attributes = new HashMap();
                image = null;
                font = PdfFont.getDefaultFont();
                return pc;
            } else
            {
                return null;
            }
        HyphenationEvent hyphenationEvent = (HyphenationEvent)noStroke.get("HYPHENATION");
        int currentPosition = 0;
        int splitPosition = -1;
        float currentWidth = 0.0F;
        int lastSpace = -1;
        float lastSpaceWidth = 0.0F;
        int length = value.length();
        char valueArray[] = value.toCharArray();
        char character = '\0';
        BaseFont ft = font.getFont();
        boolean surrogate = false;
        if(ft.getFontType() == 2 && ft.getUnicodeEquivalent(32) != 32)
            do
            {
                if(currentPosition >= length)
                    break;
                char cidChar = valueArray[currentPosition];
                character = (char)ft.getUnicodeEquivalent(cidChar);
                if(character == '\n')
                {
                    newlineSplit = true;
                    String returnValue = value.substring(currentPosition + 1);
                    value = value.substring(0, currentPosition);
                    if(value.length() < 1)
                        value = "\001";
                    PdfChunk pc = new PdfChunk(returnValue, this);
                    return pc;
                }
                currentWidth += getCharWidth(cidChar);
                if(character == ' ')
                {
                    lastSpace = currentPosition + 1;
                    lastSpaceWidth = currentWidth;
                }
                if(currentWidth > width)
                    break;
                if(splitCharacter.isSplitCharacter(0, currentPosition, length, valueArray, thisChunk))
                    splitPosition = currentPosition + 1;
                currentPosition++;
            } while(true);
        else
            for(; currentPosition < length; currentPosition++)
            {
                character = valueArray[currentPosition];
                if(character == '\r' || character == '\n')
                {
                    newlineSplit = true;
                    int inc = 1;
                    if(character == '\r' && currentPosition + 1 < length && valueArray[currentPosition + 1] == '\n')
                        inc = 2;
                    String returnValue = value.substring(currentPosition + inc);
                    value = value.substring(0, currentPosition);
                    if(value.length() < 1)
                        value = " ";
                    PdfChunk pc = new PdfChunk(returnValue, this);
                    return pc;
                }
                surrogate = Utilities.isSurrogatePair(valueArray, currentPosition);
                if(surrogate)
                    currentWidth += getCharWidth(Utilities.convertToUtf32(valueArray[currentPosition], valueArray[currentPosition + 1]));
                else
                    currentWidth += getCharWidth(character);
                if(character == ' ')
                {
                    lastSpace = currentPosition + 1;
                    lastSpaceWidth = currentWidth;
                }
                if(surrogate)
                    currentPosition++;
                if(currentWidth > width)
                    break;
                if(splitCharacter.isSplitCharacter(0, currentPosition, length, valueArray, null))
                    splitPosition = currentPosition + 1;
            }

        if(currentPosition == length)
            return null;
        String returnValue;
        PdfChunk pc;
        if(splitPosition < 0)
        {
            returnValue = value;
            value = "";
            pc = new PdfChunk(returnValue, this);
            return pc;
        }
        if(lastSpace > splitPosition && splitCharacter.isSplitCharacter(0, 0, 1, singleSpace, null))
            splitPosition = lastSpace;
        if(hyphenationEvent != null && lastSpace >= 0 && lastSpace < currentPosition)
        {
            int wordIdx = getWord(value, lastSpace);
            if(wordIdx > lastSpace)
            {
                String pre = hyphenationEvent.getHyphenatedWordPre(value.substring(lastSpace, wordIdx), font.getFont(), font.size(), width - lastSpaceWidth);
                String post = hyphenationEvent.getHyphenatedWordPost();
                if(pre.length() > 0)
                {
                    String returnValue = (new StringBuilder()).append(post).append(value.substring(wordIdx)).toString();
                    value = trim((new StringBuilder()).append(value.substring(0, lastSpace)).append(pre).toString());
                    PdfChunk pc = new PdfChunk(returnValue, this);
                    return pc;
                }
            }
        }
        wordIdx = value.substring(splitPosition);
        value = trim(value.substring(0, splitPosition));
        pre = new PdfChunk(wordIdx, this);
        return pre;
    }

    PdfChunk truncate(float width)
    {
        if(image != null)
            if(image.getScaledWidth() > width)
            {
                if(image.isScaleToFitLineWhenOverflow())
                {
                    setImageScalePercentage(width / image.getWidth());
                    return null;
                } else
                {
                    PdfChunk pc = new PdfChunk("", this);
                    value = "";
                    attributes.remove("IMAGE");
                    image = null;
                    font = PdfFont.getDefaultFont();
                    return pc;
                }
            } else
            {
                return null;
            }
        int currentPosition = 0;
        float currentWidth = 0.0F;
        if(width < font.width())
        {
            String returnValue = value.substring(1);
            value = value.substring(0, 1);
            PdfChunk pc = new PdfChunk(returnValue, this);
            return pc;
        }
        int length = value.length();
        boolean surrogate = false;
        for(; currentPosition < length; currentPosition++)
        {
            surrogate = Utilities.isSurrogatePair(value, currentPosition);
            if(surrogate)
                currentWidth += getCharWidth(Utilities.convertToUtf32(value, currentPosition));
            else
                currentWidth += getCharWidth(value.charAt(currentPosition));
            if(currentWidth > width)
                break;
            if(surrogate)
                currentPosition++;
        }

        if(currentPosition == length)
            return null;
        if(currentPosition == 0)
        {
            currentPosition = 1;
            if(surrogate)
                currentPosition++;
        }
        String returnValue = value.substring(currentPosition);
        value = value.substring(0, currentPosition);
        PdfChunk pc = new PdfChunk(returnValue, this);
        return pc;
    }

    PdfFont font()
    {
        return font;
    }

    BaseColor color()
    {
        return (BaseColor)noStroke.get("COLOR");
    }

    float width()
    {
        return width(value);
    }

    float width(String str)
    {
        if(isAttribute("SEPARATOR"))
            return 0.0F;
        if(isImage())
            return getImageWidth();
        float width = font.width(str);
        if(isAttribute("CHAR_SPACING"))
        {
            Float cs = (Float)getAttribute("CHAR_SPACING");
            width += (float)str.length() * cs.floatValue();
        }
        if(isAttribute("WORD_SPACING"))
        {
            int numberOfSpaces = 0;
            for(int idx = -1; (idx = str.indexOf(' ', idx + 1)) >= 0;)
                numberOfSpaces++;

            Float ws = (Float)getAttribute("WORD_SPACING");
            width += (float)numberOfSpaces * ws.floatValue();
        }
        return width;
    }

    float height()
    {
        if(isImage())
            return getImageHeight();
        else
            return font.size();
    }

    public boolean isNewlineSplit()
    {
        return newlineSplit;
    }

    public float getWidthCorrected(float charSpacing, float wordSpacing)
    {
        if(image != null)
            return image.getScaledWidth() + charSpacing;
        int numberOfSpaces = 0;
        for(int idx = -1; (idx = value.indexOf(' ', idx + 1)) >= 0;)
            numberOfSpaces++;

        return font.width(value) + (float)value.length() * charSpacing + (float)numberOfSpaces * wordSpacing;
    }

    public float getTextRise()
    {
        Float f = (Float)getAttribute("SUBSUPSCRIPT");
        if(f != null)
            return f.floatValue();
        else
            return 0.0F;
    }

    public float trimLastSpace()
    {
        BaseFont ft = font.getFont();
        if(ft.getFontType() == 2 && ft.getUnicodeEquivalent(32) != 32)
        {
            if(value.length() > 1 && value.endsWith("\001"))
            {
                value = value.substring(0, value.length() - 1);
                return font.width(1);
            }
        } else
        if(value.length() > 1 && value.endsWith(" "))
        {
            value = value.substring(0, value.length() - 1);
            return font.width(32);
        }
        return 0.0F;
    }

    public float trimFirstSpace()
    {
        BaseFont ft = font.getFont();
        if(ft.getFontType() == 2 && ft.getUnicodeEquivalent(32) != 32)
        {
            if(value.length() > 1 && value.startsWith("\001"))
            {
                value = value.substring(1);
                return font.width(1);
            }
        } else
        if(value.length() > 1 && value.startsWith(" "))
        {
            value = value.substring(1);
            return font.width(32);
        }
        return 0.0F;
    }

    Object getAttribute(String name)
    {
        if(attributes.containsKey(name))
            return attributes.get(name);
        else
            return noStroke.get(name);
    }

    boolean isAttribute(String name)
    {
        if(attributes.containsKey(name))
            return true;
        else
            return noStroke.containsKey(name);
    }

    boolean isStroked()
    {
        return !attributes.isEmpty();
    }

    boolean isSeparator()
    {
        return isAttribute("SEPARATOR");
    }

    boolean isHorizontalSeparator()
    {
        if(isAttribute("SEPARATOR"))
        {
            Object o[] = (Object[])(Object[])getAttribute("SEPARATOR");
            return !((Boolean)o[1]).booleanValue();
        } else
        {
            return false;
        }
    }

    boolean isTab()
    {
        return isAttribute("TAB");
    }

    /**
     * @deprecated Method adjustLeft is deprecated
     */

    void adjustLeft(float newValue)
    {
        Object o[] = (Object[])(Object[])attributes.get("TAB");
        if(o != null)
            attributes.put("TAB", ((Object) (new Object[] {
                o[0], o[1], o[2], new Float(newValue)
            })));
    }

    static TabStop getTabStop(PdfChunk tab, float tabPosition)
    {
        TabStop tabStop = null;
        Object o[] = (Object[])(Object[])tab.attributes.get("TAB");
        if(o != null)
        {
            Float tabInterval = (Float)o[0];
            if(Float.isNaN(tabInterval.floatValue()))
                tabStop = TabSettings.getTabStopNewInstance(tabPosition, (TabSettings)tab.attributes.get("TABSETTINGS"));
            else
                tabStop = TabStop.newInstance(tabPosition, tabInterval.floatValue());
        }
        return tabStop;
    }

    TabStop getTabStop()
    {
        return (TabStop)attributes.get("TABSTOP");
    }

    void setTabStop(TabStop tabStop)
    {
        attributes.put("TABSTOP", tabStop);
    }

    boolean isImage()
    {
        return image != null;
    }

    Image getImage()
    {
        return image;
    }

    float getImageHeight()
    {
        return image.getScaledHeight() * imageScalePercentage;
    }

    float getImageWidth()
    {
        return image.getScaledWidth() * imageScalePercentage;
    }

    public float getImageScalePercentage()
    {
        return imageScalePercentage;
    }

    public void setImageScalePercentage(float imageScalePercentage)
    {
        this.imageScalePercentage = imageScalePercentage;
    }

    void setImageOffsetX(float offsetX)
    {
        this.offsetX = offsetX;
    }

    float getImageOffsetX()
    {
        return offsetX;
    }

    void setImageOffsetY(float offsetY)
    {
        this.offsetY = offsetY;
    }

    float getImageOffsetY()
    {
        return offsetY;
    }

    void setValue(String value)
    {
        this.value = value;
    }

    public String toString()
    {
        return value;
    }

    boolean isSpecialEncoding()
    {
        return encoding.equals("UnicodeBigUnmarked") || encoding.equals("Identity-H");
    }

    String getEncoding()
    {
        return encoding;
    }

    int length()
    {
        return value.length();
    }

    int lengthUtf32()
    {
        if(!"Identity-H".equals(encoding))
            return value.length();
        int total = 0;
        int len = value.length();
        for(int k = 0; k < len; k++)
        {
            if(Utilities.isSurrogateHigh(value.charAt(k)))
                k++;
            total++;
        }

        return total;
    }

    boolean isExtSplitCharacter(int start, int current, int end, char cc[], PdfChunk ck[])
    {
        return splitCharacter.isSplitCharacter(start, current, end, cc, ck);
    }

    String trim(String string)
    {
        BaseFont ft = font.getFont();
        if(ft.getFontType() == 2 && ft.getUnicodeEquivalent(32) != 32)
            for(; string.endsWith("\001"); string = string.substring(0, string.length() - 1));
        else
            for(; string.endsWith(" ") || string.endsWith("\t"); string = string.substring(0, string.length() - 1));
        return string;
    }

    public boolean changeLeading()
    {
        return changeLeading;
    }

    public float getLeading()
    {
        return leading;
    }

    float getCharWidth(int c)
    {
        if(noPrint(c))
            return 0.0F;
        if(isAttribute("CHAR_SPACING"))
        {
            Float cs = (Float)getAttribute("CHAR_SPACING");
            return font.width(c) + cs.floatValue() * font.getHorizontalScaling();
        }
        if(isImage())
            return getImageWidth();
        else
            return font.width(c);
    }

    public static boolean noPrint(int c)
    {
        return c >= 8203 && c <= 8207 || c >= 8234 && c <= 8238;
    }

    private static final char singleSpace[] = {
        ' '
    };
    private static final PdfChunk thisChunk[] = new PdfChunk[1];
    private static final float ITALIC_ANGLE = 0.21256F;
    private static final HashSet keysAttributes;
    private static final HashSet keysNoStroke;
    private static final String TABSTOP = "TABSTOP";
    protected String value;
    protected String encoding;
    protected PdfFont font;
    protected BaseFont baseFont;
    protected SplitCharacter splitCharacter;
    protected HashMap attributes;
    protected HashMap noStroke;
    protected boolean newlineSplit;
    protected Image image;
    protected float imageScalePercentage;
    protected float offsetX;
    protected float offsetY;
    protected boolean changeLeading;
    protected float leading;
    protected IAccessibleElement accessibleElement;

    static 
    {
        keysAttributes = new HashSet();
        keysNoStroke = new HashSet();
        keysAttributes.add("ACTION");
        keysAttributes.add("UNDERLINE");
        keysAttributes.add("REMOTEGOTO");
        keysAttributes.add("LOCALGOTO");
        keysAttributes.add("LOCALDESTINATION");
        keysAttributes.add("GENERICTAG");
        keysAttributes.add("NEWPAGE");
        keysAttributes.add("IMAGE");
        keysAttributes.add("BACKGROUND");
        keysAttributes.add("PDFANNOTATION");
        keysAttributes.add("SKEW");
        keysAttributes.add("HSCALE");
        keysAttributes.add("SEPARATOR");
        keysAttributes.add("TAB");
        keysAttributes.add("TABSETTINGS");
        keysAttributes.add("CHAR_SPACING");
        keysAttributes.add("WORD_SPACING");
        keysAttributes.add("LINEHEIGHT");
        keysNoStroke.add("SUBSUPSCRIPT");
        keysNoStroke.add("SPLITCHARACTER");
        keysNoStroke.add("HYPHENATION");
        keysNoStroke.add("TEXTRENDERMODE");
    }
}
