package com.abm.restinteg.reporting

import org.apache.velocity.Template
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import spock.lang.Specification

class TemplateLoaderSpec extends Specification {

    TemplateLoader templateLoader

    VelocityContext mockVelocityContext
    VelocityEngine mockVelocityEngine

    void setup() {
        0 * _
        mockVelocityContext = Mock(VelocityContext)
        mockVelocityEngine = Mock(VelocityEngine)
        templateLoader = new TemplateLoader(mockVelocityContext, mockVelocityEngine)
    }

    def "load - should generate the string of the html report generated"() {
        given:
        String templateName = "html-report.vm"
        def results = []
        String expected = "Hello World !"
        Template mockTemplate = Mock(Template)

        1 * mockVelocityEngine.getTemplate(templateName) >> mockTemplate
        1 * mockVelocityContext.put('results', results)
        1 * mockVelocityContext.put('resultMap', [:])
        1 * mockTemplate.merge(mockVelocityContext, { StringWriter it ->
            it.append(expected)
        })

        when:
        String actual = templateLoader.load(templateName, results)

        then:
        expected == actual
    }
}
