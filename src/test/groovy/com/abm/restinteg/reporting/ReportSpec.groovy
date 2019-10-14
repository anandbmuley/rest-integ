package com.abm.restinteg.reporting

import com.abm.restinteg.models.TestResult
import spock.lang.Specification

class ReportSpec extends Specification {

    Report report
    List<TestResult> results

    TemplateLoader mockTemplateLoader

    void setup() {
        0 * _
        mockTemplateLoader = Mock(TemplateLoader)
        report = new Report(mockTemplateLoader)
        setUpTestData()
    }

    private void setUpTestData() {
        String detailsTestOne = """
        frequency
            Expected: MONTHLYs
            got: MONTHLY
        """
        String detailsTestTwo = """
        name
            Expected: John Doe
            got: Johny Doe
        """
        results = [
                TestResult.createFailure("Test One", "API One", detailsTestOne, "Something went wrong"),
                TestResult.createFailure("Test Two", "API One", detailsTestTwo, "Something went wrong")
        ]
    }

    def "generate - should generate report"() {
        given:
        def reportContent = "Hello World !"
        1 * mockTemplateLoader.load("reporting/html-report.vm", results) >> reportContent

        when:
        report.generate(results)

        then:
        noExceptionThrown()
    }
}
