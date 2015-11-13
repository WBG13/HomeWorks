import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;

public class Closer {
    static Logger log = LoggerFactory.getLogger(Closer.class);

    static void closeResource(Closeable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                log.warn("Failed to close resource correctly");
            }
        }
    }
}
