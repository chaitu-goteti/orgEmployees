package com.swissre.casestudy.utils;

import com.swissre.casestudy.exceptions.CaseStudyException;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;


public class FileUtilsTest extends TestCase {

    FileUtils fileUtils = new FileUtils();

    @Test
    public void testReadFile() throws IOException, CaseStudyException {
        assertEquals(fileUtils.getEmployees().size(), 9);
    }

}
