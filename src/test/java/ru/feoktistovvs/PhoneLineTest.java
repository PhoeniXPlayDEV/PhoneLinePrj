package ru.feoktistovvs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PhoneLineTest {

    @Test
    public void testSequence1() {
        var path = System.getProperty("user.dir") + "\\files\\TestsSequences.txt";
        var testsSequences = Tools.parseTestsSequences(path);
        for(TestSequenceContainer container : testsSequences) {
            var expected = container.output();
            var actual = Tools.runTest(container.input());
            String testSequence = container.testSequence();
            Assertions.assertEquals(expected, actual, testSequence);
        }
    }

}
