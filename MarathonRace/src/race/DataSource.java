package race;

import java.util.ArrayList;
/**
 * This interface represents an arbitrary data source.
 * @author Divya Veerapandian
 *
 */
public interface DataSource {
	ArrayList<Runner> read(String location) throws Exception;
	}
