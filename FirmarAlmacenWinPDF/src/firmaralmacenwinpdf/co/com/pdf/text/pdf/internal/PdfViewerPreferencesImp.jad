// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfViewerPreferencesImp.java

package co.com.pdf.text.pdf.internal;

import co.com.pdf.text.pdf.*;
import co.com.pdf.text.pdf.interfaces.PdfViewerPreferences;

public class PdfViewerPreferencesImp
    implements PdfViewerPreferences
{

    public PdfViewerPreferencesImp()
    {
        pageLayoutAndMode = 0;
        viewerPreferences = new PdfDictionary();
    }

    public int getPageLayoutAndMode()
    {
        return pageLayoutAndMode;
    }

    public PdfDictionary getViewerPreferences()
    {
        return viewerPreferences;
    }

    public void setViewerPreferences(int preferences)
    {
        pageLayoutAndMode |= preferences;
        if((preferences & 0xfff000) != 0)
        {
            pageLayoutAndMode = 0xff000fff & pageLayoutAndMode;
            if((preferences & 0x1000) != 0)
                viewerPreferences.put(PdfName.HIDETOOLBAR, PdfBoolean.PDFTRUE);
            if((preferences & 0x2000) != 0)
                viewerPreferences.put(PdfName.HIDEMENUBAR, PdfBoolean.PDFTRUE);
            if((preferences & 0x4000) != 0)
                viewerPreferences.put(PdfName.HIDEWINDOWUI, PdfBoolean.PDFTRUE);
            if((preferences & 0x8000) != 0)
                viewerPreferences.put(PdfName.FITWINDOW, PdfBoolean.PDFTRUE);
            if((preferences & 0x10000) != 0)
                viewerPreferences.put(PdfName.CENTERWINDOW, PdfBoolean.PDFTRUE);
            if((preferences & 0x20000) != 0)
                viewerPreferences.put(PdfName.DISPLAYDOCTITLE, PdfBoolean.PDFTRUE);
            if((preferences & 0x40000) != 0)
                viewerPreferences.put(PdfName.NONFULLSCREENPAGEMODE, PdfName.USENONE);
            else
            if((preferences & 0x80000) != 0)
                viewerPreferences.put(PdfName.NONFULLSCREENPAGEMODE, PdfName.USEOUTLINES);
            else
            if((preferences & 0x100000) != 0)
                viewerPreferences.put(PdfName.NONFULLSCREENPAGEMODE, PdfName.USETHUMBS);
            else
            if((preferences & 0x200000) != 0)
                viewerPreferences.put(PdfName.NONFULLSCREENPAGEMODE, PdfName.USEOC);
            if((preferences & 0x400000) != 0)
                viewerPreferences.put(PdfName.DIRECTION, PdfName.L2R);
            else
            if((preferences & 0x800000) != 0)
                viewerPreferences.put(PdfName.DIRECTION, PdfName.R2L);
            if((preferences & 0x1000000) != 0)
                viewerPreferences.put(PdfName.PRINTSCALING, PdfName.NONE);
        }
    }

    private int getIndex(PdfName key)
    {
        for(int i = 0; i < VIEWER_PREFERENCES.length; i++)
            if(VIEWER_PREFERENCES[i].equals(key))
                return i;

        return -1;
    }

    private boolean isPossibleValue(PdfName value, PdfName accepted[])
    {
        for(int i = 0; i < accepted.length; i++)
            if(accepted[i].equals(value))
                return true;

        return false;
    }

    public void addViewerPreference(PdfName key, PdfObject value)
    {
        switch(getIndex(key))
        {
        default:
            break;

        case 0: // '\0'
        case 1: // '\001'
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 14: // '\016'
            if(value instanceof PdfBoolean)
                viewerPreferences.put(key, value);
            break;

        case 6: // '\006'
            if((value instanceof PdfName) && isPossibleValue((PdfName)value, NONFULLSCREENPAGEMODE_PREFERENCES))
                viewerPreferences.put(key, value);
            break;

        case 7: // '\007'
            if((value instanceof PdfName) && isPossibleValue((PdfName)value, DIRECTION_PREFERENCES))
                viewerPreferences.put(key, value);
            break;

        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
            if((value instanceof PdfName) && isPossibleValue((PdfName)value, PAGE_BOUNDARIES))
                viewerPreferences.put(key, value);
            break;

        case 12: // '\f'
            if((value instanceof PdfName) && isPossibleValue((PdfName)value, PRINTSCALING_PREFERENCES))
                viewerPreferences.put(key, value);
            break;

        case 13: // '\r'
            if((value instanceof PdfName) && isPossibleValue((PdfName)value, DUPLEX_PREFERENCES))
                viewerPreferences.put(key, value);
            break;

        case 15: // '\017'
            if(value instanceof PdfArray)
                viewerPreferences.put(key, value);
            break;

        case 16: // '\020'
            if(value instanceof PdfNumber)
                viewerPreferences.put(key, value);
            break;
        }
    }

    public void addToCatalog(PdfDictionary catalog)
    {
        catalog.remove(PdfName.PAGELAYOUT);
        if((pageLayoutAndMode & 1) != 0)
            catalog.put(PdfName.PAGELAYOUT, PdfName.SINGLEPAGE);
        else
        if((pageLayoutAndMode & 2) != 0)
            catalog.put(PdfName.PAGELAYOUT, PdfName.ONECOLUMN);
        else
        if((pageLayoutAndMode & 4) != 0)
            catalog.put(PdfName.PAGELAYOUT, PdfName.TWOCOLUMNLEFT);
        else
        if((pageLayoutAndMode & 8) != 0)
            catalog.put(PdfName.PAGELAYOUT, PdfName.TWOCOLUMNRIGHT);
        else
        if((pageLayoutAndMode & 0x10) != 0)
            catalog.put(PdfName.PAGELAYOUT, PdfName.TWOPAGELEFT);
        else
        if((pageLayoutAndMode & 0x20) != 0)
            catalog.put(PdfName.PAGELAYOUT, PdfName.TWOPAGERIGHT);
        catalog.remove(PdfName.PAGEMODE);
        if((pageLayoutAndMode & 0x40) != 0)
            catalog.put(PdfName.PAGEMODE, PdfName.USENONE);
        else
        if((pageLayoutAndMode & 0x80) != 0)
            catalog.put(PdfName.PAGEMODE, PdfName.USEOUTLINES);
        else
        if((pageLayoutAndMode & 0x100) != 0)
            catalog.put(PdfName.PAGEMODE, PdfName.USETHUMBS);
        else
        if((pageLayoutAndMode & 0x200) != 0)
            catalog.put(PdfName.PAGEMODE, PdfName.FULLSCREEN);
        else
        if((pageLayoutAndMode & 0x400) != 0)
            catalog.put(PdfName.PAGEMODE, PdfName.USEOC);
        else
        if((pageLayoutAndMode & 0x800) != 0)
            catalog.put(PdfName.PAGEMODE, PdfName.USEATTACHMENTS);
        catalog.remove(PdfName.VIEWERPREFERENCES);
        if(viewerPreferences.size() > 0)
            catalog.put(PdfName.VIEWERPREFERENCES, viewerPreferences);
    }

    public static PdfViewerPreferencesImp getViewerPreferences(PdfDictionary catalog)
    {
        PdfViewerPreferencesImp preferences = new PdfViewerPreferencesImp();
        int prefs = 0;
        PdfName name = null;
        PdfObject obj = PdfReader.getPdfObjectRelease(catalog.get(PdfName.PAGELAYOUT));
        if(obj != null && obj.isName())
        {
            name = (PdfName)obj;
            if(name.equals(PdfName.SINGLEPAGE))
                prefs |= 1;
            else
            if(name.equals(PdfName.ONECOLUMN))
                prefs |= 2;
            else
            if(name.equals(PdfName.TWOCOLUMNLEFT))
                prefs |= 4;
            else
            if(name.equals(PdfName.TWOCOLUMNRIGHT))
                prefs |= 8;
            else
            if(name.equals(PdfName.TWOPAGELEFT))
                prefs |= 0x10;
            else
            if(name.equals(PdfName.TWOPAGERIGHT))
                prefs |= 0x20;
        }
        obj = PdfReader.getPdfObjectRelease(catalog.get(PdfName.PAGEMODE));
        if(obj != null && obj.isName())
        {
            name = (PdfName)obj;
            if(name.equals(PdfName.USENONE))
                prefs |= 0x40;
            else
            if(name.equals(PdfName.USEOUTLINES))
                prefs |= 0x80;
            else
            if(name.equals(PdfName.USETHUMBS))
                prefs |= 0x100;
            else
            if(name.equals(PdfName.FULLSCREEN))
                prefs |= 0x200;
            else
            if(name.equals(PdfName.USEOC))
                prefs |= 0x400;
            else
            if(name.equals(PdfName.USEATTACHMENTS))
                prefs |= 0x800;
        }
        preferences.setViewerPreferences(prefs);
        obj = PdfReader.getPdfObjectRelease(catalog.get(PdfName.VIEWERPREFERENCES));
        if(obj != null && obj.isDictionary())
        {
            PdfDictionary vp = (PdfDictionary)obj;
            for(int i = 0; i < VIEWER_PREFERENCES.length; i++)
            {
                obj = PdfReader.getPdfObjectRelease(vp.get(VIEWER_PREFERENCES[i]));
                preferences.addViewerPreference(VIEWER_PREFERENCES[i], obj);
            }

        }
        return preferences;
    }

    public static final PdfName VIEWER_PREFERENCES[];
    public static final PdfName NONFULLSCREENPAGEMODE_PREFERENCES[];
    public static final PdfName DIRECTION_PREFERENCES[];
    public static final PdfName PAGE_BOUNDARIES[];
    public static final PdfName PRINTSCALING_PREFERENCES[];
    public static final PdfName DUPLEX_PREFERENCES[];
    private int pageLayoutAndMode;
    private PdfDictionary viewerPreferences;
    private static final int viewerPreferencesMask = 0xfff000;

    static 
    {
        VIEWER_PREFERENCES = (new PdfName[] {
            PdfName.HIDETOOLBAR, PdfName.HIDEMENUBAR, PdfName.HIDEWINDOWUI, PdfName.FITWINDOW, PdfName.CENTERWINDOW, PdfName.DISPLAYDOCTITLE, PdfName.NONFULLSCREENPAGEMODE, PdfName.DIRECTION, PdfName.VIEWAREA, PdfName.VIEWCLIP, 
            PdfName.PRINTAREA, PdfName.PRINTCLIP, PdfName.PRINTSCALING, PdfName.DUPLEX, PdfName.PICKTRAYBYPDFSIZE, PdfName.PRINTPAGERANGE, PdfName.NUMCOPIES
        });
        NONFULLSCREENPAGEMODE_PREFERENCES = (new PdfName[] {
            PdfName.USENONE, PdfName.USEOUTLINES, PdfName.USETHUMBS, PdfName.USEOC
        });
        DIRECTION_PREFERENCES = (new PdfName[] {
            PdfName.L2R, PdfName.R2L
        });
        PAGE_BOUNDARIES = (new PdfName[] {
            PdfName.MEDIABOX, PdfName.CROPBOX, PdfName.BLEEDBOX, PdfName.TRIMBOX, PdfName.ARTBOX
        });
        PRINTSCALING_PREFERENCES = (new PdfName[] {
            PdfName.APPDEFAULT, PdfName.NONE
        });
        DUPLEX_PREFERENCES = (new PdfName[] {
            PdfName.SIMPLEX, PdfName.DUPLEXFLIPSHORTEDGE, PdfName.DUPLEXFLIPLONGEDGE
        });
    }
}
