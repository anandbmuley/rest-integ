package com.abm.jsondiff.finders

import com.abm.jsondiff.DiffType

class MissingFieldFinderTest extends FinderTestSetup {

    MissingFieldFinder finder

    def setup() {
        finder = new MissingFieldFinder(expected, actual)
    }

    def "find - should find missing fields"() {
        when:
        finder.find()

        then:
        finder.fieldDiffs.size() == 6

        ["contact", "address", "salary", "emailId", "insurances"].each { String fieldToSearch ->
            assert finder.fieldDiffs.find { it.fieldname == fieldToSearch }.differences[0] == DiffType.MISSING_FIELD_101
        }

        ["dob"].each { String fieldToSearch ->
            assert finder.fieldDiffs.find { it.fieldname == fieldToSearch }.differences[0] == DiffType.MISSING_FIELD_102
        }

    }
}
