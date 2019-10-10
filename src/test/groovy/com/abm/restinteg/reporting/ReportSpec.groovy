package com.abm.restinteg.reporting

import com.abm.restinteg.models.TestResult
import spock.lang.Specification

class ReportSpec extends Specification {

    Report report
    List<TestResult> results

    void setup() {
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
        report = new Report()
        results = [
                new TestResult("Test One", "FAILED", detailsTestOne),
                new TestResult("Test Two", "FAILED", detailsTestTwo)
        ]
    }

    def "generate - should generate report"() {
        when:
        report.generate(results)

        then:
        notThrown(Exception)
    }
}
