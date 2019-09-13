package connecttodb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry
 */
public class service {

    public static String getSDB() {
        String str = "";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(Constants.SDB_FILE_NAME);
            str = new String(fis.readAllBytes());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    Logger.getLogger(service.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return str;
    }

}
