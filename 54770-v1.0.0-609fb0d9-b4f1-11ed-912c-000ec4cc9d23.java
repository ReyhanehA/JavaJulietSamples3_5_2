/* TEMPLATE GENERATED TESTCASE FILE
Filename: CWE470_Unsafe_Reflection__connect_tcp_45.java
Label Definition File: CWE470_Unsafe_Reflection.label.xml
Template File: sources-sinks-45.tmpl.java
*/
/*
 * @description
 * CWE: 470 Unsafe Reflection
 * BadSource: connect_tcp Read data using an outbound tcp connection
 * GoodSource: Hardcoded class to load
 * Sinks:
 *    GoodSink: instantiate only certain fixed classes
 *    BadSink : instantiate arbitrary class
 * Flow Variant: 45 Data flow: data passed as a private class member variable from one function to another in the same class
 *
 * */

package testcases.CWE470_Unsafe_Reflection;

import testcasesupport.*;

import java.sql.*;
import javax.servlet.http.*;
import java.util.Properties;
import java.io.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.Socket;

import java.util.logging.Logger;

public class CWE470_Unsafe_Reflection__connect_tcp_45 extends AbstractTestCase
{

    private String bad_data;
    private String goodG2B_data;
    private String goodB2G_data;

    private void bad_sink() throws Throwable
    {
        String data = bad_data;

        Class<?> c = Class.forName(data); /* FLAW: loading arbitrary class */
        Object instance = c.newInstance();

        IO.writeLine(instance.toString());

    }

    public void bad() throws Throwable
    {
        String data;

        Logger log_bad = Logger.getLogger("local-logger");

        data = ""; /* init data */

        Socket sock = null;
        BufferedReader buffread = null;
        InputStreamReader instrread = null;
        try {
            /* Read data using an outbound tcp connection */
            sock = new Socket("host.example.org", 39544);

            /* read input from socket */
            instrread = new InputStreamReader(sock.getInputStream());
            buffread = new BufferedReader(instrread);

            data = buffread.readLine();
        }
        catch( IOException ioe )
        {
            log_bad.warning("Error with stream reading");
        }
        finally {
            /* clean up stream reading objects */
            try {
                if( buffread != null )
                {
                    buffread.close();
                }
            }
            catch( IOException ioe )
            {
                log_bad.warning("Error closing buffread");
            }
            finally {
                try {
                    if( instrread != null )
                    {
                        instrread.close();
                    }
                }
                catch( IOException ioe )
                {
                    log_bad.warning("Error closing instrread");
                }
            }

            /* clean up socket objects */
            try {
                if( sock != null )
                {
                    sock.close();
                }
            }
            catch( IOException e )
            {
                log_bad.warning("Error closing sock");
            }
        }

        bad_data = data;
        bad_sink();
    }

    public void good() throws Throwable
    {
        goodG2B();
        goodB2G();
    }

    private void goodG2B_sink() throws Throwable
    {
        String data = goodG2B_data;

        Class<?> c = Class.forName(data); /* FLAW: loading arbitrary class */
        Object instance = c.newInstance();

        IO.writeLine(instance.toString());

    }

    /* goodG2B() - use goodsource and badsink */
    private void goodG2B() throws Throwable
    {
        String data;

        data = "Testing.test";

        goodG2B_data = data;
        goodG2B_sink();
    }

    private void goodB2G_sink() throws Throwable
    {
        String data = goodB2G_data;

        if (!data.equals("Testing.test") &&  /* FIX: classname must be one of 2 values */
        !data.equals("Test.test"))
        {
            return;
        }

        Class<?> c = Class.forName(data);
        Object instance = c.newInstance();

        IO.writeLine(instance.toString());

    }

    /* goodB2G() - use badsource and goodsink */
    private void goodB2G() throws Throwable
    {
        String data;

        Logger log_bad = Logger.getLogger("local-logger");

        data = ""; /* init data */

        Socket sock = null;
        BufferedReader buffread = null;
        InputStreamReader instrread = null;
        try {
            /* Read data using an outbound tcp connection */
            sock = new Socket("host.example.org", 39544);

            /* read input from socket */
            instrread = new InputStreamReader(sock.getInputStream());
            buffread = new BufferedReader(instrread);

            data = buffread.readLine();
        }
        catch( IOException ioe )
        {
            log_bad.warning("Error with stream reading");
        }
        finally {
            /* clean up stream reading objects */
            try {
                if( buffread != null )
                {
                    buffread.close();
                }
            }
            catch( IOException ioe )
            {
                log_bad.warning("Error closing buffread");
            }
            finally {
                try {
                    if( instrread != null )
                    {
                        instrread.close();
                    }
                }
                catch( IOException ioe )
                {
                    log_bad.warning("Error closing instrread");
                }
            }

            /* clean up socket objects */
            try {
                if( sock != null )
                {
                    sock.close();
                }
            }
            catch( IOException e )
            {
                log_bad.warning("Error closing sock");
            }
        }

        goodB2G_data = data;
        goodB2G_sink();
    }

    /* Below is the main(). It is only used when building this testcase on
       its own for testing or for building a binary to use in testing binary
       analysis tools. It is not used when compiling all the testcases as one
       application, which is how source code analysis tools are tested. */
    public static void main(String[] args) throws ClassNotFoundException,
           InstantiationException, IllegalAccessException
    {
        mainFromParent(args);
    }

}
