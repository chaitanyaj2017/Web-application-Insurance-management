package com.lic.filter;

import java.io.IOException;
import java.text.Normalizer;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XSSFilter implements Filter {

    class XSSRequestWrapper extends HttpServletRequestWrapper {

        XSSRequestWrapper(HttpServletRequest httpServletRequest) {
            super(httpServletRequest);
        }

        @Override
        public String[] getParameterValues(String parameter) {

            String[] values = super.getParameterValues(parameter);
            if (values == null) {
                return null;
            }
            int count = values.length;
            String[] encodedValues = new String[count];
            for (int i = 0; i < count; i++) {
                encodedValues[i] = cleanXSS(values[i]);
            }
            return encodedValues;
        }

        @Override
        public String getParameter(String parameter) {
            String value = super.getParameter(parameter);
            if (value == null) {
                return null;
            }
            return cleanXSS(value);
        }

        private String cleanXSS(String value) {
        	 if (value != null) {

                 value = value.replaceAll("", "");

                 // Avoid anything between script tags
                 Pattern scriptPattern = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
                 value = scriptPattern.matcher(value).replaceAll("");

                 // Avoid anything in a src='...' type of expression
                 scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                 value = scriptPattern.matcher(value).replaceAll("");
                 scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                 value = scriptPattern.matcher(value).replaceAll("");

                 // Remove any lonesome </script> tag
                 scriptPattern = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
                 value = scriptPattern.matcher(value).replaceAll("");

                 // Remove any lonesome <script ...> tag
                 scriptPattern = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                 value = scriptPattern.matcher(value).replaceAll("");

                 scriptPattern = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
                 value = scriptPattern.matcher(value).replaceAll("");

                 scriptPattern = Pattern.compile("(INSERT INTO|UPDATE|SELECT|WITH|DELETE)(?:[^;']|(?:'[^']+'))+;\\s*$", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
                 value = scriptPattern.matcher(value).replaceAll("");

             }
             return value;
        }

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc) throws IOException, ServletException {
        fc.doFilter(new XSSRequestWrapper((HttpServletRequest) request), response);
       
        //XSSRequestWrapper
    }

    @Override
    public void destroy() {

    }
}

