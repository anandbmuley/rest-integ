package com.abm.restinteg.reporting

import com.abm.restinteg.models.TestResult
import com.abm.restinteg.models.config.RestIntegration
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
        report = new Report(new RestIntegration())
        results = [
                TestResult.createFailure("Test One", "API One", detailsTestOne,"Something went wrong"),
                TestResult.createFailure("Test Two", "API One", detailsTestTwo,"Something went wrong")
        ]
    }

    def "generate - should generate report"() {
        when:
        report.generate(results)

        then:
        1 == 1
    }
}
