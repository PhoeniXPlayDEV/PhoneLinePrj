package ru.feoktistovvs;

import java.util.Stack;

public record TestSequenceContainer(Stack<InputSignal> input, Stack<OutputSignal> output, String testSequence) { }
