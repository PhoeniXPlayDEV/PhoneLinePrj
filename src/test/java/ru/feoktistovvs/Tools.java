package ru.feoktistovvs;

import java.io.*;
import java.util.*;

public class Tools {

    private static final Map<String, InputSignal> INPUT_SIGNAL_MAP = new HashMap<>() {{
        put("offHook", InputSignal.OFF_HOOK);
        put("onHook", InputSignal.ON_HOOK);
        put("validNumber", InputSignal.VALID_NUMBER);
        put("invalidNumber", InputSignal.INVALID_NUMBER);
        put("I", InputSignal.NEXT_TICK);
    }};

    private static final Map<String, OutputSignal> OUTPUT_SIGNAL_MAP = new HashMap<>() {{
        put("soundDialTone", OutputSignal.SOUND_DIAL_TONE);
        put("disconnectedLine", OutputSignal.DISCONNECTED_LINE);
        put("slowBusyTone", OutputSignal.SLOW_BUSY_TONE);
        put("playMessage", OutputSignal.PLAY_MESSAGE);
        put("findConnection", OutputSignal.FIND_CONNECTION);
        put("continues", OutputSignal.CONTINUES);
        put("I", OutputSignal.TICK);
    }};

    public static Stack<OutputSignal> runTest(List<InputSignal> inputSignals) {
        PhoneLine phoneLine = new PhoneLine();
        for(InputSignal inputSignal : inputSignals) {
            switch (inputSignal) {
                case OFF_HOOK -> phoneLine.offHook();
                case ON_HOOK -> phoneLine.onHook();
                case VALID_NUMBER -> phoneLine.validNumber();
                case INVALID_NUMBER -> phoneLine.invalidNumber();
                case NEXT_TICK -> phoneLine.nextTick();
            }
        }
        return phoneLine.get_responsesStack();
    }

    public static List<TestSequenceContainer> parseTestsSequences(String path) {
        File file = new File(path);
        List<TestSequenceContainer> testsSequences = new ArrayList<>();
        try(BufferedReader in = new BufferedReader(new FileReader(file))) {
            for(String line : in.lines().toList()) {
                Stack<InputSignal> input = new Stack<>();
                Stack<OutputSignal> output = new Stack<>();
                for(String io : line.split(" ")) {
                    String[] s = io.split("/");
                    if(s[1].equals("nul"))
                        continue;
                    input.push(INPUT_SIGNAL_MAP.get(s[0]));
                    output.push(OUTPUT_SIGNAL_MAP.get(s[1]));
                }
                TestSequenceContainer container = new TestSequenceContainer(input, output, line);
                testsSequences.add(container);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return testsSequences;
    }

}
