package me.nullbyte.selfbot.log;

import java.util.HashSet;
import java.util.Set;

public class Task {

	private Runnable execute;
	private Set<Runnable> then;
	private volatile boolean started;

	public Task(Runnable runnable) {
		execute = runnable;
		this.started = false;
	}

	public Task then(Runnable runnable) {
		if (started) {
			return this;
		}
		if (then == null) {
			then = new HashSet<>();
		}
		then.add(runnable);
		return this;
	}

	public void start() {
		execute().then().finish();
	}

	protected Task execute() {
		execute.run();
		return this;
	}

	protected Task then() {
		if (then != null) {
			then.forEach(Runnable::run);
		}
		return this;
	}

	protected void finish() {
		this.started = false;
	}

}
