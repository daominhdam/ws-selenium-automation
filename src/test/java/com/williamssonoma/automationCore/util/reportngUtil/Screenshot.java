package com.williamssonoma.automationCore.util.reportngUtil;

import java.io.File;

public class Screenshot
{
    /* Name of {@link ITestResult} attribute for Screenshot. */
    public static final String KEY = "screenshot";

    /** File in which is the screenshot stored. */
    File file;
    /** URL of a web application's page the screenshot captures. */
    String url;

    public Screenshot(File file, String url) {
        this.file = file;
        this.url = url;
    }

    public File getFile() {
        return file;
    }

    public String getUrl() {
        return url;
    }

}
