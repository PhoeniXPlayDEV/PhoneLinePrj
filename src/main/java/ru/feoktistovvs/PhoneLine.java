package ru.feoktistovvs;

import java.util.Stack;

public class PhoneLine {

	private PhoneLineContext _fsm;
	private int _timer = 0;
	private Stack<OutputSignal> _responsesStack = new Stack<OutputSignal>();

	public PhoneLine() {
		_fsm = new PhoneLineContext(this);
	}

	public PhoneLineContext getContext() {
		return _fsm;
	}

    public void offHook() {
        _fsm.offHook();
    }

    public void onHook() {
        _fsm.onHook();
    }

    public void validNumber() {
        _fsm.validNumber();
    }

    public void invalidNumber() {
        _fsm.invalidNumber();
    }

	public void nextTick() {
		_timer++;
		tick();
		if(_fsm.getState().getName().endsWith("Ready") && _timer >= 3) {
			_fsm.getState().Exit(_fsm);
			_fsm.clearState();
			_fsm.setState(PhoneLineContext.PhoneLineFSM.Warning);
			_fsm.getState().Entry(_fsm);
		}
	}

	public void soundDialTone() {
		_responsesStack.push(OutputSignal.SOUND_DIAL_TONE);
	}

	public void disconnectedLine() {
		_responsesStack.push(OutputSignal.DISCONNECTED_LINE);
	}

	public void slowBusyTone() {
		_responsesStack.push(OutputSignal.SLOW_BUSY_TONE);
	}

	public void playMessage() {
		_responsesStack.push(OutputSignal.PLAY_MESSAGE);
	}

	public void findConnection() {
		_responsesStack.push(OutputSignal.FIND_CONNECTION);
	}

	public void continues() {
		_responsesStack.push(OutputSignal.CONTINUES);
	}

	public void tick() {
		_responsesStack.push(OutputSignal.TICK);
	}

	public void resetTimer() {
		_timer = 0;
	}

	public Stack<OutputSignal> get_responsesStack() {
		return _responsesStack;
	}

}