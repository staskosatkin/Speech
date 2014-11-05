package IOAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class InputStream implements IInput {
    
    private BufferedReader bufferedReader;
    
    public InputStream(java.io.InputStream stream) {
        this.bufferedReader = new BufferedReader( new InputStreamReader(stream));
    }
    
    public InputStream(FileReader fileReader) {
        this.bufferedReader = new BufferedReader(fileReader);
    }
    
    @Override
    public String readLine() {
        // TODO Auto-generated method stub
        try {
            return this.bufferedReader.readLine();
        } catch (IOException e) {
            return "";
        }
    }
}
