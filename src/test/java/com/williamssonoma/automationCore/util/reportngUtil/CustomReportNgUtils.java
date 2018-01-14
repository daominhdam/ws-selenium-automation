package com.williamssonoma.automationCore.util.reportngUtil;

import org.testng.ITestResult;
import org.uncommons.reportng.ReportNGUtils;

import java.util.List;

public class CustomReportNgUtils extends ReportNGUtils
{
    public List<String> getTestOutput(ITestResult testResult)
    {
        List<String> output = super.getTestOutput(testResult);
        System.setProperty("org.uncommons.reportng.escape-output", "false");

        if ( testResult.getAttribute(Screenshot.KEY) != null )
        {
            Screenshot screenshot = (Screenshot) testResult.getAttribute(Screenshot.KEY);
            String screenshotFileName = screenshot.getFile().getName();

            if (screenshot != null)
            {
                String url = screenshot.getUrl();
                output.add(String.format("screenshot for %s  %s <br/><img src='../screenshots/failure_screenshots_reportng/%s'>",
                        testResult.getName(), url, screenshotFileName)
                );
            }
        }

        return output;
    }
}
