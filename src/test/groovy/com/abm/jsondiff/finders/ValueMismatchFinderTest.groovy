package com.abm.jsondiff.finders

class ValueMismatchFinderTest extends FinderTestSetup {

    ValueMismatchFinder finder

    def setup() {
        finder = new ValueMismatchFinder(expected, actual)
    }

    def "find - should find mismatch value"() {
        when:
        finder.find()

        then:
        finder.fieldDiffs.size() == 1
        def differences = finder.fieldDiffs[0].differences
        differences.size() == 1
        differences[0] == "Expected value did not match with actual"
    }
}
