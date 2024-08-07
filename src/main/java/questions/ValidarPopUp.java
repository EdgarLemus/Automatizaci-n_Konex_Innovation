package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ValidarPopUp implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        Path projectPath = Paths.get("").toAbsolutePath();
        Path relativePath = projectPath.resolve("src/main/resources/image/popup.PNG");
        File file = relativePath.toFile();

        Screen s = new Screen();
        Pattern fileInputTextBox = new Pattern(file.getAbsolutePath());

        if(s.exists(fileInputTextBox) != null){
            return true;
        }else{
            return false;
        }
    }

    public static ValidarPopUp enPantalla(){
        return new ValidarPopUp();
    }
}
