package invmod.util;

public abstract interface ISelect<T> {
	public abstract T selectNext();

	public abstract void reset();
}