package com.theah64.bugmailer.core


import com.theah64.bugmailer.models.Node


/**
 * Created by theapache64 on 9/9/17.
 */

internal class ReportGenerator(projectName: String, packageName: String, var primaryStackLine: String) {

    private val report: StringBuilder

    init {

        var f1 = HEADER.replace(KEY_PROJECT_NAME, projectName)
        f1 = f1.replace(KEY_PACKAGE_NAME, packageName)

        primaryStackLine = primaryStackLine.replace("\\{.+\\}".toRegex(), "")
        primaryStackLine = primaryStackLine.replace(packageName, "")
        val lines = primaryStackLine.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val queryBuilder = StringBuilder()
        val upperBound = if (lines.size > 3) 3 else lines.size
        for (i in 0 until upperBound) {
            queryBuilder.append(lines[i])
        }

        this.primaryStackLine = queryBuilder.toString()

        report = StringBuilder(f1)
    }

    fun build(): String {
        report.append(FOOTER)
        var f1 = report.toString()
        f1 = f1.replace(KEY_SEARCH_WITH_GOOGLE_QUERY, primaryStackLine)
        return f1.replace(KEY_THEMECOLOR, BugMailer.themeColor!!)
    }

    fun addNode(node: Node): ReportGenerator {
        report.append(node)
        return this
    }

    fun addCustomNode(customNode: BugMailerNode?): ReportGenerator {

        if (customNode != null) {
            for (i in 0 until customNode.nodes.size) {
                addNode(customNode.nodes[i])
            }
        }
        return this
    }

    companion object {

        private val HEADER = "<html><head></head><body><div class=\"msg\"> <div style=\"font-size:16px;border:1px solid #e0e0e0;max-width:800px;font-family:roboto,sans-serif;font-weight:400\"> <div style=\"padding:20px 0px 20px 0px;text-align:center;background-color:#ffffff\"><img src=\"https://i.stack.imgur.com/U2JVG.png\" style=\"width:145px;min-height:40px\"></div> <div style=\"padding:24px 24px 24px 24px;background-color:#THEMECOLOR;font-family:roboto,sans-serif;font-weight:400\"> <table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr> <td> <div style=\"font-size:14px;line-height:16px;color:#fff\">PROJECTNAME</div> <div style=\"font-size:20px;line-height:24px;color:#ffffff\">New issue found in PACKAGENAME</div> </td> <td> <div style=\"text-align:right\"><img src=\"https://ci6.googleusercontent.com/proxy/Cvj52vFVxSfNWI4XcQgZJvyDSATy77fAyZvSRAMKVOONvK0uQn8NBMx1LyeaDGUkZ7c8xyQ-UiwGXlRWUvEpiia0Euc3wL-kBn8aWPjlGZmGgxBZpTyJRZgJuD9RVkPun0e9m8odWw=s0-d-e1-ft#https://www.gstatic.com/images/icons/material/system/2x/bug_report_white_24dp.png\" style=\"min-height:24px;width:24px\"></div> </td> </tr> </tbody> </table> </div> <div style=\"font-family:roboto,sans-serif;font-weight:400\"> <hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\">"
        private val FOOTER = "<div style=\"background-color:#ffffff;margin:0px 0px 0px 0px;font-size:14px;font-family:roboto,sans-serif;font-weight:400\"> <div style=\"padding-left:20px;font-size:14px;line-height:16px;padding-top:12px;padding-bottom:34px;padding-top:34px;text-align:right;font-family:roboto,sans-serif;font-weight:500\"> <a href=\"http://google.com/search?q=SEARCHWITHGOOGLEQUERY\" style=\"text-decoration:none;margin-right:24px;padding:12px 12px 12px 12px;border:0;color:#THEMECOLOR;background-color:#ffffff;font-family:roboto,sans-serif;font-weight:500;border-radius:2px\" target=\"_blank\" rel=\"noreferrer\" data-saferedirecturl=\"https://www.google.com/url?hl=en&amp;q=https://console.firebase.google.com/project/anona-705d7/monitoring/app/android:com.cybaze.goodbuy/cluster/7846c195?utm_source%3Dalerts%26utm_medium%3Demail%26utm_campaign%3Dcrash%26utm_content%3Dcluster&amp;source=gmail&amp;ust=1504955366065000&amp;usg=AFQjCNHDa_-uwZO9NB_x2q7mwXfkTP2EYw\">SEARCH WITH GOOGLE</a></div> </div> <hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"> <hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"> <hr style=\"display:block;border:0;border-top:1px solid #e0e0e0;margin:0px 24px 0px 24px\"> </div> <div style=\"background-color:#343434;padding:23px 24px 23px 24px;min-height:42px\"> <table style=\"width:100%;font-family:roboto,sans-serif;font-weight:400\" cellpadding=\"0\" cellspacing=\"0\"> <tbody> <tr> <td style=\"text-align:right;padding:0\"> <div style=\"line-height:14px;font-size:10px;color:#fff\">BugMailer<br><a style=\"color:#fff\" href=\"mailto:theapache64@gmail.com\"> theapache64@gmail.com </a><br><a style=\"color:#fff\" href=\"https://github.com/theapache64/BugMailer\">A Github opensource project</a><br></div> </td> </tr> </tbody> </table> </div> </div> </div> </body></html>"
        private val KEY_PROJECT_NAME = "PROJECTNAME"
        private val KEY_PACKAGE_NAME = "PACKAGENAME"
        private val KEY_THEMECOLOR = "#THEMECOLOR"
        private val KEY_SEARCH_WITH_GOOGLE_QUERY = "SEARCHWITHGOOGLEQUERY"
    }
}