package com.othelle.cig.email

/**
 * Created with IntelliJ IDEA.
 * User: Администратор
 * Date: 01.08.12
 * Time: 9:35
 * To change this template use File | Settings | File Templates.
 */
class Utilities {
    static String[] emailParse(String s) {
        String[] tokens = s.split(", ")
        return tokens
    }

    static def getFileContents(File file) {
        String fileContents
        List<String> contents = file.getText("WINDOWS-1251").readLines()
        fileContents = contents*.encodeAsHTML().join("<br/>")
        return fileContents
    }
}
