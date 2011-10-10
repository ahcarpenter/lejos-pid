import lejos.subsumption.Behavior;

public class WorkingArbitrator {

	private final int NONE = -1;
	private Behavior[] _behavior;
	private int _highestPriority = NONE;
	private int _current = NONE; // currently active behavior
	private boolean _returnWhenInactive;
	private Monitor monitor;
	private Object lock = new Object();

	public WorkingArbitrator(Behavior[] behaviorList, boolean returnWhenInactive) {
		_behavior = behaviorList;
		_returnWhenInactive = returnWhenInactive;
		monitor = new Monitor();
	}

	public WorkingArbitrator(Behavior[] behaviorList) {
		this(behaviorList, true);
	}

	public void start() {
		monitor.start();
		
		synchronized (lock) {
			while (_highestPriority == NONE) {
				try {
					lock.wait();
				} catch (InterruptedException ex) {
					// Do nothing.
				}
			}
		}

		while (true) {
			synchronized (lock) {
				if (_highestPriority != NONE) {
					_current = _highestPriority;
				} else if (_returnWhenInactive) {
					return;
				}
			}
			
			_behavior[_current].action();
			_current = NONE; // no active behavior at the moment
			Thread.yield();
		}
	}

	private class Monitor extends Thread {

		boolean more = true;
		int maxPriority = _behavior.length - 1;

		public void run() {
			while (more) {
				// FIND HIGHEST PRIORITY BEHAVIOR THAT WANTS CONTROL
				synchronized (lock) {
					_highestPriority = NONE;
					for (int i = maxPriority; i >= 0; i--) {
						if (_behavior[i].takeControl()) {
							_highestPriority = i;
							lock.notifyAll();
							break;
						}
					}
					if (_highestPriority > _current && _current != NONE) {
						_behavior[_current].suppress();
					}
				}
				Thread.yield();
			}
		}
	}
}

