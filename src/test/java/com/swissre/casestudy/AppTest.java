package com.swissre.casestudy;

import com.swissre.casestudy.exceptions.CaseStudyException;
import com.swissre.casestudy.models.Employee;
import com.swissre.casestudy.utils.FileUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    App app = new App();
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws IOException, CaseStudyException {
        Map<Integer, Employee> employees = new FileUtils().getEmployees();
        assertEquals(employees.size(), 9);
    }
}
