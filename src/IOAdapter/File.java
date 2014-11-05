package IOAdapter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class File implements IOutput {

    String filename;

    public File(String filename) throws IOException {
        this.filename = filename;
    }

    @Override
    public void writeLine(String str) {

        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.filename));
            try {
                writer.write(str);
            } catch (IOException e) {
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException e1) {
        }
    }

    @Override
    public void write(String str) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(this.filename));
            try {
                writer.write(str);
            } catch (IOException e) {
            } finally {
                try {
                    writer.close();
                } catch (IOException e) {
                }
            }
        } catch (IOException e1) {
        }
    }
}
