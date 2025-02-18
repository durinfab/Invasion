package invmod.util;

public class InjectionUtil {
	/**
	 * Returns <code>null</code>.
	 * <p>
	 * This is used in the initialisers of <code>static final</code> fields instead
	 * of using <code>null</code> directly to suppress the "Argument might be null"
	 * warnings from IntelliJ IDEA's "Constant conditions &amp; exceptions"
	 * inspection.
	 * 
	 * Note by Toaster: That seems to be an intelliJ only problem, for eclipse:
	 * There is @SuppressWarnings for that stuff ;)
	 * 
	 * <p>
	 * Based on diesieben07's solution <a href=
	 * "http://www.minecraftforge.net/forum/topic/60980-solved-disable-%E2%80%9Cconstant-conditions-exceptions%E2%80%9D-inspection-for-field-in-intellij-idea/?do=findCommentcomment=285024">here</a>.
	 *
	 * @param <T> The field's type.
	 * @return null
	 */
	@SuppressWarnings({ "ConstantConditions", "SameReturnValue" })
	public static <T> T Null() {
		return null;
	}
}
